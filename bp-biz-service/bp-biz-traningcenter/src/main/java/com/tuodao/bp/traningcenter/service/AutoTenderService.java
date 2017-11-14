package com.tuodao.bp.traningcenter.service;

import com.github.pagehelper.PageInfo;
import com.tuodao.bp.model.PagePojo;
import com.tuodao.bp.model.business.traningcenter.output.AutoTenderOutput;
import com.tuodao.bp.model.facade.traningcenter.input.AutoTenderParam;
import com.tuodao.bp.model.facade.traningcenter.output.AutoTenderListVo;
import com.tuodao.bp.model.facade.traningcenter.output.AutoTenderVo;
import com.tuodao.bp.traningcenter.db.model.basic.AutoTender;
import com.tuodao.bp.traningcenter.db.model.basic.BorrowTender;

import java.util.List;

/**
 * @author 王艳兵
 */
public interface AutoTenderService {


    /**
     * 保存用户自动投标设置信息
     * @param param
     */
    void saveAutoTender(AutoTenderParam param);

    /**
     * 备份自动投标设置日志
     * @param autoTender   自动投标设置信息
     */
    void insertAutoTenderLog(AutoTender autoTender);

    /**
     * 根据用户获取自动投标设置参数
     * @param userId
     * @return
     */
    AutoTenderVo getUserAutoTender(String userId);

    /**
     * 自动投标关闭,将自动投标设置信息删除,同时将自动投标记录表新增一条
     * @param userId
     */
    void closeAutoTender(String userId);

    /**
     * 开启自动投标的人数
     * @return
     */
    long getTotalAutoTender();

    /**
     * 获取指定条数的自动投标列表
     * @param limit
     * @return
     */
    List<AutoTenderOutput> getList(int limit);

    /**
     * 自动投标成功后置处理
     * @param borrowTender
     */
    void addAutoTenderSuccess(BorrowTender borrowTender);

    /**
     * 分页获取自动投标成功列表
     * @param pagePojo
     * @return
     */
    PageInfo<AutoTenderListVo> getAutoTenderListByPage(PagePojo pagePojo);

    /**
     * 获取自动投标的详情
     * @param userId
     * @param autoTenderId
     * @return
     */
    AutoTenderListVo getAutoTenderDetail(String userId,Integer autoTenderId);

    /**
     * 判断当次投标使用的券类型 0:不使用 1:使用抵用券 2:使用加息券
     * @param tender
     * @return
     */
    int getVoucherType(BorrowTender tender);
}
