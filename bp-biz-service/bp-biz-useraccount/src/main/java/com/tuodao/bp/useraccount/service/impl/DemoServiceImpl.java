package com.tuodao.bp.useraccount.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Function;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import com.tuodao.bp.model.business.demo.input.demo.DemoDbInput;
import com.tuodao.bp.model.business.demo.output.DemoBizOutput;
import com.tuodao.bp.model.business.useraccount.input.DemoInput;
import com.tuodao.bp.result.exception.BizFeignException;
import com.tuodao.bp.result.exception.MicroServiceException;
import com.tuodao.bp.useraccount.constant.UaRespExceptionConstant;
import com.tuodao.bp.useraccount.persistence.mapper.basic.DemoMapper;
import com.tuodao.bp.useraccount.persistence.model.basic.Demo;
import com.tuodao.bp.useraccount.persistence.model.basic.DemoExample;
import com.tuodao.bp.useraccount.service.IDemoService;
import org.apache.commons.lang.StringUtils;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * demo service 实现
 * 
 * @author hechuan
 *
 * @created 2017年8月30日
 *
 * @since 1.0.0
 */
@Transactional
@Service
public class DemoServiceImpl implements IDemoService {

	/** 日志 */
	private static final Logger logger = LoggerFactory.getLogger(DemoServiceImpl.class);

	@Autowired
	private DemoMapper demoMapper;

	@Autowired
	private RedissonClient redissonClient;

	/**
	 * {@link IDemoService#getDemoList(DemoDbInput)}
	 */
	@Override
	@Transactional
	public List<DemoBizOutput> getDemoList(DemoDbInput input) {
		DemoExample example = new DemoExample();
		DemoExample.Criteria cc = example.createCriteria();

		// 电话号码
		String phone = input.getPhone();

		logger.debug("phone =[{}]", phone);
		cc.andPhoneLike("%" + phone + "%");

		List<Demo> demoList = demoMapper.selectByExample(example);
		/*RedisLock redisLock = new RedisLock("123");
		redisLock.lock();
		try {
			demoList.stream().filter(demo -> StringUtils.contains(demo.getEmail(), "hellobike")).forEachOrdered(demo -> {
				demo.setEmail("ofo@tuodao.com");
				demoMapper.updateByPrimaryKeySelective(demo);
			});
		}finally {
			redisLock.unlock();
		}*/

		RLock lock = redissonClient.getLock("123");

		try {
			boolean b = lock.tryLock(5, 10, TimeUnit.SECONDS);
			if(!b){
				throw new RuntimeException("-------------");
			}
			demoList.stream().filter(demo -> StringUtils.contains(demo.getEmail(), "hellobike")).forEachOrdered(demo -> {
				demo.setEmail("ofo@tuodao.com");
				demoMapper.updateByPrimaryKeySelective(demo);
			});
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
		return FluentIterable.<Demo>from(demoList).transform(input1 -> {
            DemoBizOutput out = new DemoBizOutput();
            BeanUtils.copyProperties(input1, out);
            return out;
        }).toList();

	}

	/**
	 * {@link IDemoService#getDemoList(DemoDbInput)}
	 */
	@Override
	public DemoBizOutput getDemoEntity(DemoDbInput input) {
		DemoExample example = new DemoExample();
		DemoExample.Criteria cc = example.createCriteria();

		// 电话号码
		String phone = input.getPhone();

		logger.debug("phone =[{}]", phone);
		cc.andPhoneEqualTo(phone);

		List<Demo> demoList = demoMapper.selectByExample(example);

		if (demoList.size() > 1) {
			logger.error("demoList.size = [{}]", demoList.size());
			throw new MicroServiceException(UaRespExceptionConstant.USER_ACCOUNT_RECORD_NOT_ONLY);
		}
		Demo demo = demoList.get(0);
		// 类型转换
		DemoBizOutput out = new DemoBizOutput();
		BeanUtils.copyProperties(demo, out);
		return out;
	}

	/**
	 * {@link IDemoService#getDemoPageList(DemoDbInput)}
	 */
	@Override
	public PageInfo<DemoBizOutput> getDemoPageList(DemoDbInput input) {
		DemoExample example = new DemoExample();
		DemoExample.Criteria cc = example.createCriteria();
		// 电话号码
		String phone = input.getPhone();

		logger.debug("phone =[{}]", phone);
		cc.andPhoneLike("%" + phone + "%");
		
		PageHelper.startPage(input.getCurrentPage(), input.getPageSize());
		List<Demo> demoList = demoMapper.selectByExample(example);
		
		ImmutableList<DemoBizOutput> resultList = FluentIterable.<Demo>from(demoList).transform(new Function<Demo,DemoBizOutput>(){
			@Override
			public DemoBizOutput apply(Demo input) {
				DemoBizOutput out = new DemoBizOutput();
				BeanUtils.copyProperties(input, out);
				return out;
			}
		}).toList();
		
		 PageInfo<DemoBizOutput> result = new PageInfo<DemoBizOutput>(resultList);
	     Page<Demo> page = (Page<Demo>)demoList;
	     result.setTotal(page.getTotal());
		return result;
	}

	/**
	 * {@link IDemoService#saveDemo(DemoDbInput)}
	 */
	@Override
	public int saveDemo(DemoDbInput input) {
		Demo record = new Demo();
		String random = org.apache.commons.lang3.RandomStringUtils.random(5, "ABCDEFGHIJKLMN");
		record.setEmail(random + "@qq.com");
		record.setName(random);
		record.setPhone(input.getPhone());
		record.setUserName(random);
		int insertSelective = demoMapper.insertSelective(record);
		logger.debug("saveDemo Influence rows = [{}]",insertSelective);
		
		return insertSelective;
	}

	/**
	 * {@link IDemoService#updateDemo(DemoDbInput)}
	 */
	@Override
	public int updateDemo(DemoDbInput input) {
		Demo record = new Demo();
		record.setId(5);
		record.setUserName("cookie");
		int update = demoMapper.updateByPrimaryKeySelective(record);
		logger.debug("updateDemo Influence rows = [{}]",update);
		
		return update;
	}

	/**
	 * {@link IDemoService#testTransactional(DemoDbInput)}
	 */
	@Override
	public int testTransactional(DemoDbInput input){
		Demo demo = new Demo();
		BeanUtils.copyProperties(input, demo);
		int insertSelective = demoMapper.insertSelective(demo);
		if (StringUtils.equals(input.getPhone(), "1366666")) {
			throw new BizFeignException(UaRespExceptionConstant.USER_ACCOUNT_TRANSACTION_EXCEPTION);
		}
		logger.debug("testTransactional Influence rows = [{}]",insertSelective);
		return insertSelective;
	}

	/**
	 * @see IDemoService#resolveDemo(DemoInput)
	 * @param input
	 * @return
	 */
	@Override
	public boolean resolveDemo(DemoInput input) {
		if (1L == input.getX()){
			throw new BizFeignException(123456);
		}
		return true;
	}

	/**
	 * 测试死锁问题
	 *
	 * @param input
	 * @return
	 */
	@Override
	@Transactional
	public int saveAndUpdate(DemoDbInput input) {
		Demo demo = new Demo();
		demo.setPhone(input.getPhone());
		demo.setEmail(input.getPhone()+"@tuodao.com");
		String random = org.apache.commons.lang3.RandomStringUtils.random(5, "ABCDEFGHIJKLMN");
		demo.setName(random);
		demo.setUserName("ownerTuodao");

		/*Demo record = new Demo();
		record.setEmail("hellobike@tuodao.com");
		DemoExample example = new DemoExample();
		example.createCriteria().andIdGreaterThan(4);
		demoMapper.updateByExampleSelective(record,example);*/

		DemoExample example = new DemoExample();
		DemoExample.Criteria cc = example.createCriteria();

		// 电话号码
		String phone = input.getPhone();

		logger.debug("phone =[{}]", phone);
		cc.andPhoneLike("%" + phone + "%");

		List<Demo> demoList = demoMapper.selectByExample(example);

		String s = Arrays.toString(demoList.stream().map(Demo::getId).toArray());
		/*RedisLock redisLock = new RedisLock("123");
		redisLock.lock();
		try{
			demoList.stream().filter(x -> StringUtils.contains(x.getEmail(), "hellobike")).forEachOrdered(x -> {
				x.setEmail("mobike@tuodao.com");
				demoMapper.updateByPrimaryKeySelective(x);
			});

			demoMapper.insertSelective(demo);
		}finally {
			redisLock.unlock();
		}*/

		RLock lock = redissonClient.getLock("123");
		try{
			boolean b = lock.tryLock(5, 20, TimeUnit.SECONDS);
			if(!b){
				throw new RuntimeException("-------------");
			}
			demoList.stream().filter(x -> StringUtils.contains(x.getEmail(), "hellobike")).forEachOrdered(x -> {
				x.setEmail("mobike@tuodao.com");
				demoMapper.updateByPrimaryKeySelective(x);
			});

			demoMapper.insertSelective(demo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
		return 1;
	}

}
