package com.tuodao.bp.operation.service;

import com.tuodao.bp.model.BasePojo;
import com.tuodao.bp.model.business.operation.output.UserOperationStatisOutput;

import java.util.List;

/**
 * 用户运营数据Service
 * author hechuan
 * <p>
 * created on 2017/9/25
 * <p>
 * since V1.0.0
 */
public interface IUserOperationDataService {

    /**
     * 获取用户运营数据
     * @param input 基础用户信息
     * @return 列表
     */
    List<UserOperationStatisOutput> getUserOperationDataList(BasePojo input);
}
