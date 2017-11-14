package com.tuodao.bp.api.facade.client.transaction;

import com.github.pagehelper.PageInfo;
import com.tuodao.bp.model.PagePojo;
import com.tuodao.bp.model.business.traningcenter.output.AutoTenderOutput;
import com.tuodao.bp.model.facade.traningcenter.input.AutoTenderParam;
import com.tuodao.bp.model.facade.traningcenter.output.AutoTenderListVo;
import com.tuodao.bp.model.facade.traningcenter.output.AutoTenderVo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author 王艳兵
 */
@FeignClient("BIZ-TRANING-CENTER")
public interface AutoTenderClient {


    /**
     * 保存自动投标设置信息
     * @param param
     */
    @RequestMapping(value = "/tender/saveAutoTender",consumes = MediaType.APPLICATION_JSON_VALUE)
    void saveAutoTender(AutoTenderParam param);


    /**
     * 根据用户ID获取自动投标设置信息
     * @param userId
     * @return
     */
    @RequestMapping(value = "/tender/getUserAutoTender",consumes = MediaType.APPLICATION_JSON_VALUE)
    AutoTenderVo getUserAutoTender(@RequestParam("userId")String userId);

    /**
     * 关闭自动投标
     * @param userId
     */
    @RequestMapping(value = "/tender/closeAutoTender",consumes = MediaType.APPLICATION_JSON_VALUE)
    void closeAutoTender(@RequestParam("userId") String userId);

    /**
     * 已开启自动投标的人数
     * @return
     */
    @RequestMapping(value = "/tender/getTotalAutoTender",consumes = MediaType.APPLICATION_JSON_VALUE)
    long getTotalAutoTender();

    /**
     * 获取对应条数自动投标列表
     * @param limit 多少条
     * @return
     */
    @RequestMapping(value = "/tender/getAutoTenderList",consumes = MediaType.APPLICATION_JSON_VALUE)
    List<AutoTenderOutput> getList(@RequestParam("limit") Integer limit);


    /**
     * 分页获取自动投标成功列表
     * @param pagePojo
     * @return
     */
    @RequestMapping(value = "/tender/getAutoTenderListByPage",consumes = MediaType.APPLICATION_JSON_VALUE)
    PageInfo<AutoTenderListVo> getAutoTenderListByPage(PagePojo pagePojo);

    /**
     * 获取自动投标的详情
     * @param userId
     * @param autoTenderId
     * @return
     */
    @RequestMapping(value = "/tender/getAutoTenderDetail",consumes = MediaType.APPLICATION_JSON_VALUE)
    AutoTenderListVo getAutoTenderDetail(@RequestParam("userId") String userId,@RequestParam("autoTenderId") Integer autoTenderId);
}
