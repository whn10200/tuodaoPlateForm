package com.tuodao.bp.result.error;

public enum DepositoryError {

	//1310签名异常（包括验签时候的生成签名）
	PFX_LOAD_ERROR(131001,"pfx文件读取失败"),
	CERT_LOAD_ERROR(130002,"cert文件读取失败"),
	CONFIG_LOAD_ERROR(130002,"config文件读取失败"),
	PRIVATE_KEY_LOAD_ERROR(130002,"config文件读取失败"),
	PUBLIC_KEY_LOAD_ERROR(130002,"config文件读取失败"),
	//1320
	BEI_JING_BANKE_REQUES_OUT_TIME(132001,"北京银行请求超时");

    private int code;

    private String msg;

    DepositoryError(int code , String msg){
        this.code = code;
        this.msg = msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
