package com.tuodao.bp.traningcenter.db.mapper.basic;

import com.tuodao.bp.traningcenter.db.model.basic.AutoTender;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 王艳兵
 */
public interface AutoTenderMapper {
    /**
     * 根据主键删除数据库的记录
     *
     * @param id 主键
     * @return
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入数据库记录
     *
     * @param record
     * @return
     */
    int insert(AutoTender record);

    /**
     * 插入不为空的记录
     *
     * @param record
     * @return
     */
    int insertSelective(AutoTender record);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param id
     * @return
     */
    AutoTender selectByPrimaryKey(Integer id);

    /**
     * 根据主键来更新部分数据库记录
     *
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(AutoTender record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     * @return
     */
    int updateByPrimaryKey(AutoTender record);

    /**
     * 根据用户ID删除自动投标设置信息
     * @param userId
     * @return
     */
    int deleteByUserId(@Param("userId")String userId);

    /**
     * 根据用户Id获取自动投标设置信息
     * @param userId
     * @return
     */
    AutoTender getByUserId(@Param("userId") String userId);

    /**
     * 根据排名获取weight之前自动投标个数
     * @param weight 排名
     * @return
     */
    long getTotalByWeight(@Param("weight")Long weight);

    /**
     * 获取最大权重值
     * @return
     */
    long getMaxWeight();

    /**
     * 根据weight升序 获取指定条数的自动投标信息
     * @param limit
     * @return
     */
    List<AutoTender> getList(int limit);

    /**
     * 根据用户id更新weight值
     * @param userId 用户Id
     * @param weight 排名 权重
     * @return
     */
    int updateWeightByUserId(@Param("userId")String userId,@Param("weight")Long weight);
}