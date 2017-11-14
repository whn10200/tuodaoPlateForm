package com.tuodao.bp.api.facade.service.transaction;

import com.github.pagehelper.PageInfo;
import com.tuodao.bp.model.PagePojo;
import com.tuodao.bp.model.business.operation.output.UserDiscountOutput;
import com.tuodao.bp.model.business.product.output.ProductOutput;
import com.tuodao.bp.model.facade.traningcenter.input.AutoTenderParam;
import com.tuodao.bp.model.facade.traningcenter.output.AutoTenderListVo;
import com.tuodao.bp.model.facade.traningcenter.output.AutoTenderVo;

import java.util.List;

/**
 * @author 王艳兵
 */
public interface AutoTenderService  {


    /**
     * 保存自动投标设置信息
     * @param param  自动投标设置参数
     */
    void saveAutoTender(AutoTenderParam param);

    /**
     * 根据用户ID获取自动投标设置参数
     * @param userId
     * @return
     */
    AutoTenderVo getUserAutoTender(String userId);

    /**
     * 关闭自动投标
     * @param userId
     */
    void closeAutoTender(String userId);

    /**
     * 总的自动投标开启的个数
     * @return
     */
    long getTotalAutoTender();

    /**
     * 自动投标 定时任务调用该方法
     */
    void autoTender();

    /**
     * 获取用户最佳使用加息券
     * @param list  要投的标的列表,针对自动投标 该列表可能大于1 同时只会获取一张最优加息券
     * @param tenderMoney 投标金额
     * @param userId
     * @return
     */
    UserDiscountOutput getBestVoucher(List<ProductOutput> list, double tenderMoney, String userId);

    /**
     * 分页获取用户自动投标数据
     * @param pojo
     * @return
     */
    PageInfo<AutoTenderListVo> getAutoTenderListByPage(PagePojo pojo);

    /**
     * 获取自动投标的详情
     * @param userId
     * @param autoTenderId
     * @return
     */
    AutoTenderListVo getAutoTenderDetail(String userId,Integer autoTenderId);
}
