package com.tuodao.bp.model.business.useraccount.input;

import com.google.common.base.MoreObjects;
import com.tuodao.bp.model.BasePojo;

import java.io.Serializable;
import java.util.Date;

/**
 * author hechuan
 * <p>
 * created on 2017/9/26
 * <p>
 * since V1.0.0
 */
public class DemoInput extends BasePojo implements Serializable {

    private static final long serialVersionUID = 4361435484213393731L;
    private Date begin = new Date();

    private Long x;

    private String address;

    public Date getBegin() {
        return begin;
    }

    public void setBegin(Date begin) {
        this.begin = begin;
    }

    public Long getX() {
        return x;
    }

    public void setX(Long x) {
        this.x = x;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("begin",begin)
                .add("x",x)
                .add("address",address)
                .toString();
    }
}
