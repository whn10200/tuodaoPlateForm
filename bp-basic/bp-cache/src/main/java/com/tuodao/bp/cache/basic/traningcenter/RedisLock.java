package com.tuodao.bp.cache.basic.traningcenter;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.Jedis;


/**
 * @description:使用redis实现分布式锁
 * @author: 王艳兵
 * @date: 2017年8月2日 
 * @time: 上午11:35:08
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class RedisLock {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RedisLock.class);
	
	
	private RedisTemplate<String, Object> redisTemplate;
	
	/**
	 * 获取锁的间隔时间 
	 */
	private int interval = 100;
	
	/**
	 * 锁的key 
	 */
	private String lockKey;
	
	/**
	 * 获取锁的时间 10s,即10秒后如果依旧没有获取到锁,则返回false
	 */
	private int waitTime = 10 * 1000; 
	
	 /**
     * 锁超时时间，防止线程在入锁以后，无限的执行等待(即:单次业务处理最长时间为60s,超过后自动放弃)
     */
    private int expireTime = 60 * 1000;
    
    /**
     * 锁的状态
     */
    private volatile boolean locked = false;
	
    
    /**
     * 锁过期的时间 未来的某个时间
     */
    private String expireStr;
    
    //本地测试
    private Jedis jedis;
    
	
	public RedisLock(String lockKey){
		this.lockKey = lockKey;
        jedis = RedisConnection.getRedis();
	}
	
	public RedisLock(String lockKey,int waitTime){
		this(lockKey);
		this.waitTime = waitTime;
	}
	
	private String newGet(String key){
	    String result = null;
	    try {
	        result = jedis.get(key);
        }finally {
	        jedis.close();
        }
        return result;
	}
	
	private boolean newSetNx(String key,String value){
	    boolean flag = false;
	    try {
            Long result = jedis.setnx(key, value);
            flag = (result == null)? false: result == 1;
        }finally {
	        jedis.close();
        }
		return flag;
	}
	
	private String newGetSet(String key ,String value){
	    String result = null;
	    try {
            result = jedis.getSet(key, value);
        }finally {
	        jedis.close();
        }
        return result;
	}
	
	
	/**
	 * 默认构造方法
	 * @param redisTemplate 用于处理与redis交互的模板对象
	 * @param lockKey 锁的key
	 */
	public RedisLock(RedisTemplate<String, Object> redisTemplate,String lockKey ){
		this.redisTemplate = redisTemplate;
		this.lockKey = lockKey;
	}
	
	/**
	 * 构造方法
	 * @param redisTemplate 用于处理与redis交互的模板对象
	 * @param lockKey 要获取的锁的key
	 * @param waitTime 尝试多次反复获取锁的总时间 
	 */
	public RedisLock(RedisTemplate<String, Object> redisTemplate,String lockKey,int waitTime ){
		this(redisTemplate,lockKey);
		this.waitTime = waitTime;
	}
	
	
	
	/**
	 * 根据key获取redis中存放的值
	 * @param key
	 * @return
	 */
	private String get(final String key){
		Object obj = null;
		try {
			
			obj = redisTemplate.execute(new RedisCallback<Object>() {
				@Override
				public Object doInRedis(org.springframework.data.redis.connection.RedisConnection connection) throws DataAccessException {
                    StringRedisSerializer serializer = new StringRedisSerializer();
                    byte[] resultByte = connection.get(serializer.serialize(key));
                    if(resultByte == null){
                        return null;
                    }
                    return serializer.deserialize(resultByte);
				}
			});
			
		} catch (Exception e) {
			LOGGER.error("获取redis错误,key:{}",key);
		}
		return obj != null ? obj.toString():null;
	}
	
	/**
	 * 设置redis值,该方法如果key不存在则设置并返回true,否则返回false,在分布式下适用
	 * @param key
	 * @param value
	 * @return
	 */
	private boolean setNx(final String key ,final String value ){
		Object obj = null;
		try {

			obj = redisTemplate.execute(new RedisCallback<Object>() {

                @Override
                public Object doInRedis(org.springframework.data.redis.connection.RedisConnection connection) throws DataAccessException {
                    StringRedisSerializer serializer = new StringRedisSerializer();
                    Boolean result = connection.setNX(serializer.serialize(key),serializer.serialize(value));
                    connection.close();
                    return result;
                }
            });
			
		} catch (Exception e) {
			LOGGER.error("设置redis.nx错误,key:{},value:{}",key,value);
		}
		return obj != null ? true : false;
	}
	
	/**
	 * 设置值,并返回老的值(如果存在key覆盖)
	 * @param key 
	 * @param value
	 * @return
	 */
	private String getSet(final String key,final String value ){
		Object obj = null;
		try {
		    obj = redisTemplate.execute(new RedisCallback<Object>() {
                @Override
                public Object doInRedis(org.springframework.data.redis.connection.RedisConnection connection) throws DataAccessException {
                    StringRedisSerializer serializer = new StringRedisSerializer();
                    byte[] ret = connection.getSet(serializer.serialize(key), serializer.serialize(value));
                    connection.close();
                    return serializer.deserialize(ret);
                }
		    });
		} catch (Exception e) {
			LOGGER.error("设置redis.getSet错误,key:{},value:{}",key,value);
		}
		return obj != null ? (String) obj : null;
	}
	
	/**
	 * 获取锁
	 * @return
	 * synchronized 保证同应用下线程的安全 
	 */
	public synchronized boolean lock(){
		int wait = waitTime;
		while(wait > 0){
			long expire = System.currentTimeMillis() + expireTime + 1;
			String expireStr = String.valueOf(expire);
            //分布式下,该方法只会成功一次.直接获取到锁
			if(this.newSetNx(lockKey, expireStr)){
				this.expireStr = expireStr;
				locked = true;
				return true;
			}
			//没有获取到锁,有线程已经在使用该锁
			String currentValue = this.newGet(lockKey);
            //锁已经过期
			if(currentValue  != null && Long.parseLong(currentValue) < System.currentTimeMillis()){
                //该方法可以理解类似数据库的乐观锁,此处即使两个线程同时设置时间后一个会覆盖前一个,则oldValue是上一次set的值
				String oldValueStr = this.newGetSet(lockKey, expireStr);
                //该出再次判断如果新值和旧值则证明两次访问之间没有其他线程覆盖超时时间
				if(oldValueStr != null && oldValueStr.equals(currentValue)){
                    //将过期时间保存
					this.expireStr = expireStr;
					locked = true;
                    return true;
				}//如果值不一样,证明该线程获取到的旧值是其他线程已经更新的线程;则尝试暂停等待其他线程处理再尝试获取
			}
			//锁没有过期,则等待片刻尝试再次获取
            //随机等待时间
			int random = (int) (Math.random() * interval);
			
			wait -= random;
			try {
				Thread.sleep(random);
			} catch (InterruptedException e) {
				LOGGER.error("redisLock获取锁失败,key:{}" + lockKey);
				e.printStackTrace();
			}
		}
		LOGGER.info("{}毫秒中尝试获取锁失败,lockKey:{}",waitTime,lockKey);
		return false;
	}
	
	/**
	 * 释放锁
	 */
	public synchronized void unlock(){
        //假如一个业务执行时间超过60s 则在处理过程中会出现删除的锁可能是另一个线程获取的锁
		if(locked){
			String currentValue = newGet(lockKey);
			
			if(currentValue != null && currentValue.equals(expireStr)){
				/**
				 * 1.释放锁的时候判断该锁过期时间是不是和之前保留的过期时间一样 不一样则说明,另一个线程已经将该值覆盖
				 * 该线程可能出现处理业务时间过长,超时后其他线程获取到了该锁.假如业务处理真的超过60s(需自行修改业务逻辑),
				 * 则事务的一致性无法保证,此处只是额外判断防止错误删除其他的锁导致更多线程事务问题
				 * 2.该处不判断当前时间>expireStr原因:因为超时另一个线程已经获取到锁,而在此处会出现删除了不该删除的锁
				 * 3.该处不判断当前时间>currentValue原因:如果当前线程处理超过120s甚至更多时,当前时间可能会大于下个线程设置的currentValue
				 * 而下个线程可能还在执行中
				 */
				jedis.del(lockKey);
				locked = false;
			}else{
				LOGGER.warn("redisLock 过期时间已被覆盖,不做删除操作,key:{}",lockKey);
			}
		}
	}

}
