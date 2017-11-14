package com.tuodao.bp.api.core.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.annotation.Resource;

/**
 * 将操作日志记录到数据库
 * 
 * @author hechuan
 *
 * @created 2017年11月10日
 *
 * @since V1.0.0
 */
public class DbLogRecordHandler extends LogRecordHandler {

	/** 日志 */
	private static final Logger logger = LoggerFactory.getLogger(DbLogRecordHandler.class);

	/** 数据库插入模板 */
	private static final String INSERT_LOG_TEMPLATE = "INSERT INTO system_operate_log\n"
			+ "\t(USER_ID,MOBILE, ACTION_CODE, CONTENT, `STATUS`, CODE, REQUEST_TIME, COMPLETE_TIME, "
			+ "REQUEST_DURATION, SERVER_TIME, REQUESET_IP, SERVICE_TYPE, REQUEST_URL, ACCESSID, REMARK)\n"
			+ "\tVALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	/** 数据库操作类 */
	@Resource
	private JdbcTemplate jdbcTemplate;

	/** 事务 */
	@Resource
	protected PlatformTransactionManager transactionManager;

	/**
	 * 记录操作日志
	 *
	 * @param userOperateLog
	 *            操作日志
	 */
	@Override
	public void recordLog(SystemOperateLog userOperateLog) {

		// 用户操作日志没被spring管理事务，自己手动控制
		DefaultTransactionDefinition userOperateLogDefinition = new DefaultTransactionDefinition();
		userOperateLogDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		TransactionStatus status = transactionManager.getTransaction(userOperateLogDefinition);

		try{
			// 新增日志
			jdbcTemplate.update(INSERT_LOG_TEMPLATE,
					new Object[] { userOperateLog.getUserId(),userOperateLog.getMobile(), userOperateLog.getActionCode(), userOperateLog.getContent(),
							userOperateLog.getStatus(), userOperateLog.getCode(), userOperateLog.getRequestTime(),
							userOperateLog.getCompleteTime(), userOperateLog.getRequestDuration(),userOperateLog.getServerTime(),
							userOperateLog.getRequesetIp(), userOperateLog.getServiceType(),
							userOperateLog.getRequestUrl(), userOperateLog.getAccessid(), userOperateLog.getRemark() });

			transactionManager.commit(status);
			logger.info("用户接口请求日志入库成功！");
		}catch (Exception e){
			logger.error("用户接口请求日志入库失败..e:[{}],userOperateLog:[{}]",e.getMessage(),userOperateLog,e);
			transactionManager.rollback(status);
		}

	}
}
