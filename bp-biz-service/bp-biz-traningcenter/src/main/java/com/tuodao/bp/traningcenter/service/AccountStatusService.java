package com.tuodao.bp.traningcenter.service;

import com.tuodao.bp.model.business.traningcenter.input.JustIdInput;
import com.tuodao.bp.traningcenter.db.model.basic.AccountStatus;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/10/17 0017.
 */
public interface AccountStatusService {

    AccountStatus selectAccountStatus(JustIdInput justIdInput);

    int updateAccountStatus(AccountStatus accountStatus);

    int insertAccountStatus(AccountStatus accountStatus);

    Boolean updateAccountStatus(List<AccountStatus> list,int type);
}
