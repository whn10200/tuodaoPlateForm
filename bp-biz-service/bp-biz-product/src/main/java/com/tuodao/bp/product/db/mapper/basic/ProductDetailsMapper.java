package com.tuodao.bp.product.db.mapper.basic;

import com.tuodao.bp.product.db.model.basic.ProductDetails;
import com.tuodao.bp.product.db.model.basic.ProductDetailsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProductDetailsMapper {
    int countByExample(ProductDetailsExample example);

    int deleteByExample(ProductDetailsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProductDetails record);

    int insertSelective(ProductDetails record);

    List<ProductDetails> selectByExample(ProductDetailsExample example);

    ProductDetails selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProductDetails record, @Param("example") ProductDetailsExample example);

    int updateByExample(@Param("record") ProductDetails record, @Param("example") ProductDetailsExample example);

    int updateByPrimaryKeySelective(ProductDetails record);

    int updateByPrimaryKey(ProductDetails record);
}