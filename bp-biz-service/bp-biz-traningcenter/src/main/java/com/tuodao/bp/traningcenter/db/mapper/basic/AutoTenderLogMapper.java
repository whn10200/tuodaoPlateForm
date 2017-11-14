package com.tuodao.bp.traningcenter.db.mapper.basic;

import com.tuodao.bp.traningcenter.db.model.basic.AutoTenderLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 王艳兵
 */
public interface AutoTenderLogMapper {
    /**
     * 根据主键删除数据库的记录
     *
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入数据库记录
     *
     * @param record
     * @return
     */
    int insert(AutoTenderLog record);

    /**
     * 插入不为空的记录
     *
     * @param record
     * @return
     */
    int insertSelective(AutoTenderLog record);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param id
     */
    AutoTenderLog selectByPrimaryKey(Integer id);

    /**
     * 根据主键来更新部分数据库记录
     *
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(AutoTenderLog record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     * @return
     */
    int updateByPrimaryKey(AutoTenderLog record);

    /**
     * 根据投标ID获取投标设置日志
     * @param autoTenderId
     * @return
     */
    AutoTenderLog getByAutoTenderId(Integer autoTenderId);

    /**
     * 根据用户获取自动投标日志列表
     * @param userId
     * @return
     */
    List<AutoTenderLog> getList(String userId);

    /**
     * 获取用户自动投标详情
     * @param userId 用户ID
     * @param autoTenderId 自动投标id
     * @return
     */
    AutoTenderLog getByUserIdAndAutoTenderId(@Param("userId")String userId,@Param("autoTenderId")Integer autoTenderId);

    /**
     * 获取投标设置总数
     * @param userId
     * @return
     */
    long getCountAutoTenderLog(@Param("userId")String userId);
}