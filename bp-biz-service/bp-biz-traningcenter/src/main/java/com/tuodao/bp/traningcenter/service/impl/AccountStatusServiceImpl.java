package com.tuodao.bp.traningcenter.service.impl;

import com.tuodao.bp.cache.basic.traningcenter.RedisLock;
import com.tuodao.bp.cache.constant.RedisConstans;
import com.tuodao.bp.model.business.traningcenter.input.JustIdInput;
import com.tuodao.bp.traningcenter.db.mapper.basic.AccountStatusMapper;
import com.tuodao.bp.traningcenter.db.model.basic.AccountStatus;
import com.tuodao.bp.traningcenter.service.AccountStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/10/17 0017.
 */
@Service
public class AccountStatusServiceImpl implements AccountStatusService{

    @Autowired
    AccountStatusMapper accountStatusMapper;

    @Override
    public AccountStatus selectAccountStatus(JustIdInput justIdInput)
    {
        return accountStatusMapper.selectByPrimaryKey(justIdInput.getJustId());
    }

    @Override
    public int insertAccountStatus(AccountStatus accountStatus)
    {
        return accountStatusMapper.insert(accountStatus);
    }

    @Override
    public int updateAccountStatus(AccountStatus accountStatus)
    {
        return accountStatusMapper.updateByPrimaryKey(accountStatus);
    }

    /**
     *理财计划下的状态更新（防止互斥）
     * @param list 理财计划的idlist
     * @param type 理财计划复审查询1 底层标的复审查询2 理财计划到期平账查询3 理财计划复审结束修改4
     *             底层标的复审修改5 理财计划到期平账修改6
     * @return Boolean
     */

    @Override
    public Boolean updateAccountStatus(List<AccountStatus> list,int type)
    {
        RedisLock lock = new RedisLock(RedisConstans.TC_ACCOUNT_STATUS);
        try {//加锁 防止其他
            if (lock.lock()) {
                if (type == 1 || type == 3) {
                    AccountStatus accountStatus = accountStatusMapper.selectByPrimaryKey(list.get(0).getProductId());
                    if (accountStatus != null) {
                        if (accountStatus.getStatus() == 1) {
                            return false;
                        } else {
                            accountStatus.setStatus(1);
                            if (type == 1) {
                                accountStatus.setStep(0);
                            } else {
                                accountStatus.setStep(2);
                            }
                            accountStatusMapper.updateByPrimaryKey(accountStatus);
                        }
                    } else {
                        accountStatus = new AccountStatus();
                        accountStatus.setProductId(list.get(0).getProductId());
                        accountStatus.setStatus(1);
                        if (type == 1) {
                            accountStatus.setStep(0);
                        } else {
                            accountStatus.setStep(2);
                        }
                        accountStatusMapper.insert(accountStatus);
                    }
                } else if (type == 2) {
                    for (int j = 0; j < list.size(); j++) {
                        //需要插入的
                        List<AccountStatus> addAccountStatusList = new ArrayList<>();
                        //需要更新的
                        List<AccountStatus> upAccountStatusList = new ArrayList<>();
                        AccountStatus accountStatus = accountStatusMapper.selectByPrimaryKey(list.get(j).getProductId());
                        if (accountStatus != null) {
                            if (accountStatus.getStatus() == 1 || accountStatus.getStep() != 1) {
                                return false;
                            } else {
                                accountStatus.setStatus(1);
                                accountStatus.setStep(1);
                                accountStatus.setLastProductId(accountStatus.getLastProductId());
                                accountStatus.setType(accountStatus.getType());
                                upAccountStatusList.add(accountStatus);
                            }
                        } else {
                            accountStatus = new AccountStatus();
                            accountStatus.setProductId(list.get(0).getProductId());
                            accountStatus.setStatus(1);
                            accountStatus.setStep(1);
                            accountStatus.setLastProductId(accountStatus.getLastProductId());
                            addAccountStatusList.add(accountStatus);
                            //                    accountStatusMapper.insert(accountStatus);
                        }


                    }
                } else if (type == 4 || type == 6) {
                    AccountStatus accountStatus = accountStatusMapper.selectByPrimaryKey(list.get(0).getProductId());
                    accountStatus.setStatus(0);
                    accountStatusMapper.updateByPrimaryKey(accountStatus);
                } else {
                    AccountStatus accountStatus = accountStatusMapper.selectByPrimaryKey(list.get(0).getProductId());
                    if (accountStatus.getLastProductId().equals(list.get(0).getLastProductId())) {
                        accountStatus.setStatus(0);
                        accountStatusMapper.updateByPrimaryKey(accountStatus);
                    }
                }
            }
        }
        catch (Exception e)
        {
            return false;
        }
        finally {
            lock.unlock();
        }
        return true;
    }


}
