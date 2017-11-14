package com.tuodao.bp.product.service.impl;

import com.tuodao.bp.model.business.product.output.BorrowExpandOutput;
import com.tuodao.bp.product.db.mapper.basic.BorrowExpandMapper;
import com.tuodao.bp.product.db.model.basic.BorrowExpandExample;
import com.tuodao.bp.product.db.model.basic.BorrowExpandWithBLOBs;
import com.tuodao.bp.product.service.IBorrowExpandService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @Author wuchengjie
 * @Date 2017/11/10 0010 16:20
 * @Introduction
 */

@Service
public class BorrowExpandServiceImpl implements IBorrowExpandService {


    @Autowired
    private BorrowExpandMapper borrowExpandMapper;


    @Override
    public BorrowExpandOutput getBorrowExpandByPcode(String pcode) {

        BorrowExpandExample expandExample = new BorrowExpandExample();
        expandExample.createCriteria().andProductCodeEqualTo(pcode);

        List<BorrowExpandWithBLOBs> expands = borrowExpandMapper.selectByExampleWithBLOBs(expandExample);

        if(CollectionUtils.isEmpty(expands))
        {
            return  null;
        }

        BorrowExpandOutput output = new BorrowExpandOutput();
        BeanUtils.copyProperties(expands.get(0),output);

        return output;
    }

    @Override
    public BorrowExpandOutput getBorrowExpandById(Integer id) {

        BorrowExpandWithBLOBs borrowExpandWithBLOBs =  borrowExpandMapper.selectByPrimaryKey(id);

        if(borrowExpandWithBLOBs == null)
        {
            return  null;
        }

        BorrowExpandOutput output = new BorrowExpandOutput();
        BeanUtils.copyProperties(borrowExpandWithBLOBs,output);

        return output;
    }
}
