package com.mingrisoft.result;

/**
 * 接口返回JSON的封装体 
 * error_code int 返回码
 * reason string 返回说明 
 * result string 返回结果集
 * 
 * @param <T> 想要返回的类型
 */
public class Result<T> {
	private String reason;//返回说明 
	private T result;//返回结果集
	private int error_code;//返回码
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public T getResult() {
		return result;
	}
	public void setResult(T result) {
		this.result = result;
	}
	public int getError_code() {
		return error_code;
	}
	public void setError_code(int error_code) {
		this.error_code = error_code;
	}
	
	
}
