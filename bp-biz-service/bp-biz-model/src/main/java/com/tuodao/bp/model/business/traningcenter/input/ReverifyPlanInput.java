package com.tuodao.bp.model.business.traningcenter.input;

import com.tuodao.bp.model.business.product.output.ProductOutput;

import java.io.Serializable;
import java.util.List;

/**
 * @author wuzf
 * @date 2017/11/03
 * @description 理财计划复审入参
 */
public class ReverifyPlanInput implements Serializable{

    private static final long serialVersionUID = -6594896948511297743L;

    private ProductOutput productOutput;

    private List<PlanListInput> list;

    public ProductOutput getProductOutput() {
        return productOutput;
    }

    public void setProductOutput(ProductOutput productOutput) {
        this.productOutput = productOutput;
    }

    public List<PlanListInput> getList() {
        return list;
    }

    public void setList(List<PlanListInput> list) {
        this.list = list;
    }
}
