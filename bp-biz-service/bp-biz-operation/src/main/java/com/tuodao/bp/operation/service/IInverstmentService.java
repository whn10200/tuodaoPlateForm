package com.tuodao.bp.operation.service;

import com.tuodao.bp.model.business.operation.input.InverstmentInput;
import com.tuodao.bp.model.business.operation.input.InverstmentQueryInput;
import com.tuodao.bp.model.business.operation.output.InverstmentOutput;
import com.tuodao.bp.model.business.operation.output.InverstmentQueryOutput;

import java.util.List;

/**
 * 抽奖service
 * author hechuan
 * <p>
 * created on 2017/9/28
 * <p>
 * since V1.0.0
 */
public interface IInverstmentService {
    /**
     * 查询抽奖列表
     * @param input
     * @return
     */
    List<InverstmentQueryOutput> getInverstQueryList(InverstmentQueryInput input);

    /**
     * 获取抽奖结果
     * @param input
     * @return
     */
    InverstmentOutput getInverstResult(InverstmentInput input);
}
