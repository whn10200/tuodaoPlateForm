package com.tuodao.bp.task.server.constant;

/**
 * Description:
 * User: sxj
 * Date: 2017/5/22 0022
 * Time: 17:43
 * Copyright:拓道金服 Copyright (c) 2017
 */
public interface EnumConstant {

	int getType();

	String getName();

	//执行策略
	enum Mathematics implements EnumConstant {
		MATHEMATICS_1(1, "执行一次"),
		MATHEMATICS_2(2, "保证成功"),
		MATHEMATICS_3(3, "保证成功(任务不在执行中)");
		
		/** 属性值 */
		private int type;
		
		/** 属性名 */
		private String name;
		
		private String repay = "REPAY_TYPE_";

		Mathematics(int type, String name) {
			this.type = type;
			this.name = name;
		}
		
		public int getType() {
			return type;
		}
		
		public String getName() {
			return name;
		}
		
		public String getName(int type){
			return Mathematics.valueOf(repay+type).getName();
		}
	}

	//调度策略
	enum Dispatch implements EnumConstant {
		DISPATCH_1(1, "随机调度"),
		DISPATCH_2(2, "指定设备"),
		DISPATCH_3(3, "全体执行");
		
		/** 属性值 */
		private int type;
		
		/** 属性名 */
		private String name;

		Dispatch(int type, String name) {
			this.type = type;
			this.name = name;
		}
		
		public int getType() {
			return type;
		}
		
		public String getName() {
			return name;
		}
		
	}

	//通知策略
	enum Notify implements EnumConstant {
		NOTIFY_1(1, "HTTP"),
		NOTIFY_2(2, "MQ");
		
		/** 属性值 */
		private int type;
		
		/** 属性名 */
		private String name;

		Notify(int type, String name) {
			this.type = type;
			this.name = name;
		}
		
		public int getType() {
			return type;
		}
		
		public String getName() {
			return name;
		}
		
	}

	//运行状态
	enum ResultStatus implements EnumConstant {
		RESULT_STATUS_1(1, "成功"),
		RESULT_STATUS_2(2, "失败"),
		RESULT_STATUS_3(3, "处理中");

		/** 属性值 */
		private int type;

		/** 属性名 */
		private String name;

		ResultStatus(int type, String name) {
			this.type = type;
			this.name = name;
		}

		public int getType() {
			return type;
		}

		public String getName() {
			return name;
		}

	}

	//任务状态
	enum TaskStatus implements EnumConstant {
		TASK_STATUS_1(1, "启用"),
		TASK_STATUS_2(2, "暂停"),
		TASK_STATUS_3(3, "作废");

		/** 属性值 */
		private int type;

		/** 属性名 */
		private String name;

		TaskStatus(int type, String name) {
			this.type = type;
			this.name = name;
		}

		public int getType() {
			return type;
		}

		public String getName() {
			return name;
		}

	}

	// 回调结果返回
	enum CallBackResult{

		CALL_BACK_RESULT_SUCCESS("SUCCESS"),

		CALL_BACK_RESULT_FAILED("FAILED");

		private String name;

		CallBackResult(String name){
			this.name = name;
		}

		public String getName(){
			return name;
		}
	}
}
