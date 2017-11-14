package com.tuodao.bp.api.facade.service.transaction;

import com.github.pagehelper.PageInfo;
import com.tuodao.bp.model.business.product.input.BorrowRepaymentInput;
import com.tuodao.bp.model.business.traningcenter.input.UserBackMoneyInput;
import com.tuodao.bp.model.facade.traningcenter.input.CollectionParam;
import com.tuodao.bp.model.facade.traningcenter.output.BackMoneyPlanVo;
import com.tuodao.bp.model.facade.traningcenter.output.CollectionVo;

import java.util.List;


/**
 * @author qingli.chen
 * @date 2017/9/19
 * @description
 */
public interface BorrowCollectionService {

    /**
     * 分页查询回款计划
     * @param input
     * @return
     */
    PageInfo<BackMoneyPlanVo> pageByTenderId(UserBackMoneyInput input);

    /**
     * 查询回款计划
     * @param input
     * @return
     */
    List<BackMoneyPlanVo> getByTenderId(UserBackMoneyInput input);

    /**
     * 根据天分页获取回款信息
     * @param param 某一天
     * @return
     */
    PageInfo<CollectionVo> getCollectionCalendarByPage(CollectionParam param);


}
