package com.tuodao.bp.api.facade.service.transaction;

import com.github.pagehelper.PageInfo;
import com.tuodao.bp.model.facade.traningcenter.input.AccountLogParam;
import com.tuodao.bp.model.facade.traningcenter.output.AccountLogVo;
import com.tuodao.bp.model.facade.traningcenter.output.AppAccountLogVo;

/**
 * @author 王艳兵
 */
public interface AccountLogService {


    /**
     * 根据条件分页获取用户资金记录
     * @param param 查询条件:资金类型,开始时间,结束时间
     * @return
     */
    PageInfo<AccountLogVo> getUserAccountLogByPage(AccountLogParam param);

    /**
     * 手机端 分页获取用户资金流水
     * @param param
     * @return
     */
    PageInfo<AppAccountLogVo> getAppUserAccountLogByPage(AccountLogParam param);
}
