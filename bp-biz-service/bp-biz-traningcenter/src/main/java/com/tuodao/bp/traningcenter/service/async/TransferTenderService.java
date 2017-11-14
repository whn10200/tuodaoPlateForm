package com.tuodao.bp.traningcenter.service.async;

import com.tuodao.bp.traningcenter.db.mapper.basic.BorrowCollectionMapper;
import com.tuodao.bp.traningcenter.db.mapper.basic.BorrowTransferMapper;
import com.tuodao.bp.traningcenter.db.model.basic.BorrowTransfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author qingli.chen
 * @date 2017/10/20
 * @description
 */
@Async
@Component
public class TransferTenderService {

    @Autowired
    BorrowTransferMapper borrowTransferMapper;

    @Autowired
    BorrowCollectionMapper borrowCollectionMapper;

    public void review() {
        BorrowTransfer borrowTransfer = borrowTransferMapper.selectByPrimaryKey(1);

    }
}
