package com.tuodao.bp.traningcenter.cache;

import com.google.common.base.Function;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Multimaps;
import com.tuodao.bp.cache.basic.traningcenter.BankCache;
import com.tuodao.bp.cache.basic.traningcenter.OnlineBankInfo;
import com.tuodao.bp.traningcenter.db.mapper.basic.AccountBankPaymentMapper;
import com.tuodao.bp.traningcenter.db.model.basic.AccountBankPayment;
import com.tuodao.bp.traningcenter.db.model.basic.AccountBankPaymentExample;
import com.tuodao.bp.traningcenter.enums.BankPayMentType;
import com.tuodao.bp.utils.TransferUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 银行充值列表缓存
 * @author qingli.chen
 * @date 2017/10/12
 * @description
 */
@Component
public class OnlineBankCacheInit implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(OnlineBankCacheInit.class);

    @Autowired
    private AccountBankPaymentMapper accountBankPaymentMapper;

    @Autowired
    private BankCache bankCache;

    @Override
    public void run(String... args) throws Exception {
        logger.info("begin loading online bank to cache");
        AccountBankPaymentExample example = new AccountBankPaymentExample();
        example.createCriteria();
        List<AccountBankPayment> list = accountBankPaymentMapper.selectByExample(example);

        ListMultimap<Integer, AccountBankPayment> map = Multimaps.index(list.iterator(), p -> p.getType());

        List<OnlineBankInfo> bankList = TransferUtil.transferList(map.get(BankPayMentType.ONLINE_BANK.getValue()),
                OnlineBankInfo.class);
        List<OnlineBankInfo> fastBankList = TransferUtil.transferList(map.get(BankPayMentType.FAST_BANK.getValue()),
                OnlineBankInfo.class);

        bankCache.putOnlineBank(new ArrayList<>(bankList));
        fastBankList.forEach(f -> {
            bankCache.putFastBank(f);
        });


        logger.info("end loading online bank to cache");
    }
}
