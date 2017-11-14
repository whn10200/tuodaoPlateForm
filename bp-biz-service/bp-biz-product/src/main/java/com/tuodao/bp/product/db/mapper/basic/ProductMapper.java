package com.tuodao.bp.product.db.mapper.basic;

import com.tuodao.bp.model.business.product.input.ProductQueryInput;
import com.tuodao.bp.model.business.product.output.ProductOutput;
import com.tuodao.bp.product.db.model.basic.Product;
import com.tuodao.bp.product.db.model.basic.ProductExample;
import com.tuodao.bp.product.db.model.basic.ProductWithBLOBs;

import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProductMapper {
	int countByExample(ProductExample example);

	int deleteByExample(ProductExample example);

	int deleteByPrimaryKey(Integer id);

	int insert(ProductWithBLOBs record);

	int insertSelective(ProductWithBLOBs record);

	List<ProductWithBLOBs> selectByExampleWithBLOBs(ProductExample example);

	List<Product> selectByExample(ProductExample example);

	ProductWithBLOBs selectByPrimaryKey(Integer id);

	int updateByExampleSelective(@Param("record") ProductWithBLOBs record, @Param("example") ProductExample example);

	int updateByExampleWithBLOBs(@Param("record") ProductWithBLOBs record, @Param("example") ProductExample example);

	int updateByExample(@Param("record") Product record, @Param("example") ProductExample example);

	int updateByPrimaryKeySelective(ProductWithBLOBs record);

	int updateByPrimaryKeyWithBLOBs(ProductWithBLOBs record);

	int updateByPrimaryKey(Product record);

	/**
	 * 根据输入的id 查询理财计划中标的 信息
	 * 
	 * @param productId
	 * @return
	 */
	List<HashMap<String, String>> getOrginalByPlanId(Integer productId);

	List<ProductWithBLOBs> selectTenderingBorrow(ProductQueryInput input);

	List<ProductWithBLOBs> selectTimeBorrow(ProductQueryInput input);

	List<ProductWithBLOBs> selectFullBorrow(ProductQueryInput input);

	Integer getBorrowAmount(ProductQueryInput input);

	List<ProductWithBLOBs> selectPlanByPage(ProductQueryInput input);

	Integer selectTenderingBorrowAmount(ProductQueryInput input);

	Integer selectTimeBorrowAmount(ProductQueryInput input);

	Integer selectFullBorrowAmount(ProductQueryInput input);

	ProductWithBLOBs selectProduct(ProductQueryInput input);

    List<ProductWithBLOBs> selectExpiredList(@Param("contain")Boolean contain);

	/**
	 *  查询理财计划下需要复审的普通标的
	 */
    List<ProductWithBLOBs> getPlanReverifyBorrowList();

    List<ProductWithBLOBs> getReverifyPlanList();
}
