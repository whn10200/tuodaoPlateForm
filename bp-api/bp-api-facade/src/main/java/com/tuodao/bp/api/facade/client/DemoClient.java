package com.tuodao.bp.api.facade.client;

import com.github.pagehelper.PageInfo;
import com.tuodao.bp.model.business.demo.input.demo.DemoDbInput;
import com.tuodao.bp.model.business.demo.output.DemoBizOutput;
import com.tuodao.bp.model.business.useraccount.input.DemoInput;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

/**
 * 客户端调用
 * 
 * @author hechuan
 *
 * @created 2017年8月30日
 *
 * @since 1.0.0
 */
@FeignClient(value = "BIZ-USER-ACCOUNT")
public interface DemoClient {

	/**
	 * 详情
	 * @param input 输入参数
	 * @return
	 */
	@RequestMapping(value = "/demo/demoDetail", method = RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE)
	public DemoBizOutput demoDetail(DemoDbInput input);
	
	/**
	 * 列表
	 * @param input 输入参数
	 * @return
	 */
	@RequestMapping(value = "/demo/demoList", method = RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE)
	public List<DemoBizOutput> getDemoList(DemoDbInput input);
	
	
	/**
	 * 分页列表
	 * @param input 输入参数
	 * @return
	 */
	@RequestMapping(value = "/demo/demoPageList", method = RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE)
	public PageInfo<DemoBizOutput> getDemoPageList(DemoDbInput input);
	
	
	/**
	 * 保存
	 * @param input 输入参数
	 */
	@RequestMapping(value = "/demo/saveDemo", method = RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE)
	public void saveDemo(DemoDbInput input);
	
	/**
	 * 更新
	 * @param input 输入参数
	 */
	@RequestMapping(value = "/demo/updateDemo", method = RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE)
	public void updateDemo(DemoDbInput input);

	/**
	 * 更新
	 * @param input 输入参数
	 */
	@RequestMapping(value = "/demo/saveAndUpdate", method = RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE)
	void saveAndUpdate(DemoDbInput input);
	
	/**
	 * 事物
	 * @param input 输入参数
	 */
	@RequestMapping(value = "/demo/transDemo", method = RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE)
	public void transDemo(DemoDbInput input);

	/**
	 * 事物
	 * @param input 输入参数
	 */
	@RequestMapping(value = "/demo/resolveDemo", method = RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE)
	public boolean resolveDemo(DemoInput input);

	/**
	 * 转换Map传递
	 * @param inputMap
	 * @return
	 */
	@RequestMapping(value = "/demo/transMap", method = RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE)
	String transMap(@RequestBody Map<String,Object> inputMap);
}
