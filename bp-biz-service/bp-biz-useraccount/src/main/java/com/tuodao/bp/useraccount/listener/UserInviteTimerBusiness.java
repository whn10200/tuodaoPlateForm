package com.tuodao.bp.useraccount.listener;

import com.tuodao.bp.activemq.constant.MqContstant;
import com.tuodao.bp.model.business.useraccount.output.FinancialPlannerOutput;
import com.tuodao.bp.useraccount.persistence.mapper.basic.UserInfoMapper;
import com.tuodao.bp.useraccount.persistence.mapper.basic.UserInviteInfoMapper;
import com.tuodao.bp.useraccount.persistence.model.basic.UserInviteInfo;
import com.tuodao.bp.useraccount.service.IAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * author yinping
 * <p>
 * created on 2017/10/25
 * <p>
 * since V1.0.0
 * 统计用户邀请信息定时任务
 */
@Component
public class UserInviteTimerBusiness {

    private static final Logger logger = LoggerFactory.getLogger(UserInviteTimerBusiness.class);

    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private UserInviteInfoMapper userInviteInfoMapper;

    @Resource
    private IAccountService accountService;

    @JmsListener(destination = MqContstant.TASK_TIMER_SEND_QUEUE,selector = "taskQueue='userInviteTimer'")
    public void execute(Map<String,Object> map) {
        logger.info("map ：{}",map);
        //1.查询要插入的数据
        List<UserInviteInfo> list = userInfoMapper.selectAll();
        if(list!=null && list.size()>0){
            logger.info("do task......begin;;");
            //2.插入之前先删除user_invite_info表的数据
            userInviteInfoMapper.deleteAll();
            for(int i=0;i<list.size();i++){
                String userId = list.get(i).getUserId();
                //查询理财师等级
                if(userId !=null){
                    FinancialPlannerOutput financialPlannerOutput = null;
                    try {
                        financialPlannerOutput = accountService.selectUserFinancialPlanner(userId);
                        list.get(i).setFinancialPlannerLevel(financialPlannerOutput.getCurrentLevel());
                        list.get(i).setFinancialPlannerLevelName(financialPlannerOutput.getCurrentLevelName());
                    } catch (Exception e) {
                        logger.error("要查询理的财师等级对象不存在！");
                    }
                }
                //3.插入到op_user_invite_info
                userInviteInfoMapper.insert(list.get(i));
            }
        }
        logger.info("do task......end;");
    }
}
