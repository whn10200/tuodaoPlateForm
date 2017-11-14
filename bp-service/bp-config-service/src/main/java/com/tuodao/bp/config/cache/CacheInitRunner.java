package com.tuodao.bp.config.cache;

import java.util.List;

import com.google.common.base.Joiner;
import com.tuodao.bp.cache.basic.AccountBankCache;
import com.tuodao.bp.cache.basic.ConfigDictionaryCache;
import com.tuodao.bp.cache.basic.MsgTempCache;
import com.tuodao.bp.db.mapper.basic.*;
import com.tuodao.bp.db.model.basic.*;
import com.tuodao.bp.model.constant.PublicConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.tuodao.bp.cache.basic.BasicDataDao;
import com.tuodao.bp.cache.constant.RedisConstans;


/**
 * 缓存初始化
 *
 * @author hechuan
 * @version 1.0.0
 * @created 2017年8月4日
 */
@Component
public class CacheInitRunner implements CommandLineRunner {

    /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(CacheInitRunner.class);

    @Autowired
    private BasicDataDao basicDataDao;

    @Autowired
    private SystemBasicDataMapper systemBasicDataMapper;

    @Autowired
    private ConfigDictionaryCache configDictionaryCache;

    @Autowired
    private ConfigDictionaryMapper configDictionaryMapper;

    @Autowired
    private ConfigSmsTempMapper configSmsTempMapper;

    @Autowired
    private ConfigPushTempMapper configPushTempMapper;

    @Autowired
    private AccountBankMapper accountBankMapper;

    @Autowired
    private MsgTempCache msgTempCache;

    @Autowired
    private AccountBankCache accountBankCache;

    @Override
    public void run(String... args) throws Exception {
        logger.info("basic data of query begin............");

        SystemBasicDataExample example = new SystemBasicDataExample();
        List<SystemBasicData> basicDataList = systemBasicDataMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(basicDataList)) {
            logger.warn("basic data init over because the basicDataList is Empty.....");
            return;
        }

        logger.info("basic data of cache init begin...");
        for (SystemBasicData data : basicDataList) {

            if (RedisConstans.BASIC_DATA_MODULE_BUSINESS.equals(data.getModule())) {
                basicDataDao.putBusiness(data);
            }
            if (RedisConstans.BASIC_DATA_MODULE_IPLIMIT.equals(data.getModule())) {
                basicDataDao.putIplimit(data);
            }

        }
        logger.info("basic data of cache init end...");

        logger.info("config dictionary  of cache init start...");
        ConfigDictionaryExample dictionaryExample = new ConfigDictionaryExample();
        dictionaryExample.createCriteria()
                .andIsDelEqualTo(1)
                .andDicStatusEqualTo(1);
        List<ConfigDictionary> configDictionaries = configDictionaryMapper.selectByExample(dictionaryExample);
        if (CollectionUtils.isEmpty(configDictionaries)) {
            logger.warn("config dictionary init over because the configDictionaries is Empty.....");
            return;
        }
        configDictionaries.forEach(dictionary ->
                configDictionaryCache.putDictionaryName(Joiner.on("_").join(dictionary.getDicType(), dictionary.getDicCode()), dictionary.getDicName())
        );
        logger.info("config dictionary  of cache init end...");


        logger.info("config sms template  of cache init start...");
        ConfigSmsTempExample configSmsTempExample = new ConfigSmsTempExample();
        configSmsTempExample.createCriteria()
                .andIsDelEqualTo(1)
                .andStatusEqualTo(1);
        List<ConfigSmsTemp> configSmsTemps = configSmsTempMapper.selectByExample(configSmsTempExample);
        if (CollectionUtils.isEmpty(configSmsTemps)) {
            logger.warn("config sms template init over  because the configDictionaries is Empty.....");
            return;
        }
        configSmsTemps.forEach(configSmsTemp ->
                msgTempCache.putSmsTemp(configSmsTemp)
        );

        logger.info("config sms template  of cache init end...");

        logger.info("config push template  of cache init start...");
        ConfigPushTempExample configPushTempExample = new ConfigPushTempExample();
        configPushTempExample.createCriteria()
                .andIsDelEqualTo(1)
                .andStatusEqualTo(1);
        List<ConfigPushTemp> configPushTemps = configPushTempMapper.selectByExample(configPushTempExample);
        if (CollectionUtils.isEmpty(configPushTemps)) {
            logger.warn("config push template  init over because the configDictionaries is Empty.....");
            return;
        }
        configPushTemps.forEach(configPushTemp ->
                msgTempCache.putPushTemp(configPushTemp)
        );

        logger.info("config push template  of cache init end...");


        logger.info("account bank of cache init begin...");
        AccountBankExample accountBankExample = new AccountBankExample();
        accountBankExample.createCriteria().andEnableEqualTo(PublicConstant.DEL_NO);
        List<AccountBank> accountBanks = accountBankMapper.selectByExample(accountBankExample);
        accountBankCache.putAccountBank(accountBanks);
        logger.info("account bank of cache init end...");
    }


}
