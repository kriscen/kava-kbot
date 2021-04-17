package com.kbot.entity;

import com.kbot.constant.CommonConstant;
import lombok.Data;

import java.io.Serializable;

/**
 * Program Name: kava-kbot
 * <p>
 * Description:
 * <p>
 * Created by kris on 2021/3/6
 *
 * @author kris
 */
@Data
public class R<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 成功标志
	 */
	private boolean success = true;

	/**
	 * 返回处理消息
	 */
	private String message = "操作成功！";

	/**
	 * 返回代码
	 */
	private Integer code = 0;
	
	/**
	 * 返回数据对象 data
	 */
	private T result;
	
	/**
	 * 时间戳
	 */
	private long timestamp = System.currentTimeMillis();

	public R() {
		
	}
	
	public R<T> error500(String message) {
		this.message = message;
		this.code = CommonConstant.SC_INTERNAL_SERVER_ERROR_500;
		this.success = false;
		return this;
	}
	
	public R<T> success(String message) {
		this.message = message;
		this.code = CommonConstant.SC_OK_200;
		this.success = true;
		return this;
	}
	
	
	public static R<Object> ok() {
		R<Object> r = new R<>();
		r.setSuccess(true);
		r.setCode(CommonConstant.SC_OK_200);
		r.setMessage("成功");
		return r;
	}
	
	public static R<Object> ok(String msg) {
		R<Object> r = new R<>();
		r.setSuccess(true);
		r.setCode(CommonConstant.SC_OK_200);
		r.setMessage(msg);
		return r;
	}
	
	public static R<Object> ok(Object data) {
		R<Object> r = new R<>();
		r.setSuccess(true);
		r.setCode(CommonConstant.SC_OK_200);
		r.setResult(data);
		return r;
	}
	
	public static R<Object> error(String msg) {
		return error(CommonConstant.SC_INTERNAL_SERVER_ERROR_500, msg);
	}
	
	public static R<Object> error(int code, String msg) {
		R<Object> r = new R<>();
		r.setCode(code);
		r.setMessage(msg);
		r.setSuccess(false);
		return r;
	}

}