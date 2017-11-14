package com.tuodao.bp.model.admin;

import com.tuodao.bp.model.annotation.PhoneNum;
import com.tuodao.bp.model.constant.depository.TDFN;
import com.tuodao.bp.model.constant.facade.FacadeExceptionConstant;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;
import java.util.HashMap;

/**
 * @description: 修改用户手机号码
 * @author: mif
 * @date: 2017/11/9
 * @time: 14:55
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class UserModifyMobileInput extends AdminPojo implements Serializable {

    private static final long serialVersionUID = -3891563585909607811L;

    /**
     * 用户手机号码
     */
    @NotBlank(message = FacadeExceptionConstant.MOBILE_CAN_NOT_BE_NULL + "")
    @PhoneNum(message = FacadeExceptionConstant.MOBILE_FORMAT_ERROR + "")
    private String mobile;

    @Override
    public HashMap<String, String> toHashMap() {
        HashMap<String, String> hashMap = super.toHashMap();
        hashMap.put(TDFN.userId, getUserId());
        hashMap.put(TDFN.mobile, getMobile());
        return hashMap;
    }

    @Override
    public void fromHashMap(HashMap<String, String> map) {
        super.fromHashMap(map);
    }
}
