package com.tuodao.bp.product.controller;

import com.tuodao.bp.model.business.product.input.BorrowRepaymentInput;
import com.tuodao.bp.model.business.product.input.TransferInput;
import com.tuodao.bp.model.business.product.output.BorrowTypeOutput;
import com.tuodao.bp.model.business.product.output.ProductOutput;
import com.tuodao.bp.product.db.model.basic.BorrowDefineType;
import com.tuodao.bp.product.service.IBorrowDefineTypeService;
import com.tuodao.bp.product.service.IBorrowRepayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/borrowType")
public class BorrowDefineController {
	
	private static final Logger logger = LoggerFactory.getLogger(BorrowDefineController.class);

	@Autowired
	private IBorrowDefineTypeService borrowDefineTypeService;


	/**
	 * 查询自定义标标总
	 */
	@RequestMapping(value="/getEnableBorrowTypeById",method=RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public BorrowTypeOutput getEnableBorrowType(Integer id) {
		List<BorrowDefineType> typeList = borrowDefineTypeService.getEnableBorrowType();

		for (BorrowDefineType defineType : typeList) {
			if( defineType.getId().equals(id)){
				BorrowTypeOutput out = new BorrowTypeOutput();
				BeanUtils.copyProperties(defineType, out);
				return out;
			}
		}

		return  null;
	}
}
