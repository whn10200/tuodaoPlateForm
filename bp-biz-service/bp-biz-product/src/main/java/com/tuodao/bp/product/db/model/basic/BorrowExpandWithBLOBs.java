package com.tuodao.bp.product.db.model.basic;

import java.io.Serializable;

public class BorrowExpandWithBLOBs extends BorrowExpand implements Serializable {
    private String description;

    private String intro;

    private String explaind;

    private static final long serialVersionUID = 1L;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro == null ? null : intro.trim();
    }

    public String getExplaind() {
        return explaind;
    }

    public void setExplaind(String explaind) {
        this.explaind = explaind == null ? null : explaind.trim();
    }
}