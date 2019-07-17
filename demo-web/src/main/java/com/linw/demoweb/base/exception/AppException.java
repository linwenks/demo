package com.linw.demoweb.base.exception;

import java.util.List;

import com.google.common.collect.Lists;
import com.linw.demoweb.base.constant.MsgEnum;
import com.linw.demoweb.base.vo.MsgBean;

import lombok.Getter;
import lombok.Setter;

/**
 * 应用异常
 * @author linw
 *
 */
@Getter
@Setter
public class AppException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1222233942643293408L;
	
	// 消息 map
	protected List<MsgBean> msgList = Lists.newArrayList();
	
	private AppException() {
		super();
	}
	
	private AppException(Throwable throwable) {
		super(throwable);
	}
	
	private AppException(Throwable throwable, MsgEnum msgEnum) {
		super(throwable);
		msgList.add(new MsgBean(msgEnum));
	}
	
	private AppException(Throwable throwable, List<MsgBean> msgList) {
		super(throwable);
		msgList.addAll(msgList);
	}
	
	private AppException(Throwable throwable, MsgEnum msgEnum, String[] params) {
		super(throwable);
		msgList.add(new MsgBean(msgEnum, params));
	}
	
	private AppException(Throwable throwable, MsgEnum msgEnum, String[] params, String field) {
		super(throwable);
		msgList.add(new MsgBean(msgEnum, params, field));
	}
	
	private AppException(MsgEnum msgEnum) {
		super();
		msgList.add(new MsgBean(msgEnum));
	}
	
	private AppException(List<MsgBean> msgList) {
		super();
		msgList.addAll(msgList);
	}
	
	private AppException(MsgEnum msgEnum, String[] params) {
		super();
		msgList.add(new MsgBean(msgEnum, params));
	}
	
	private AppException(MsgEnum msgEnum, String[] params, String field) {
		super();
		msgList.add(new MsgBean(msgEnum, params, field));
	}
	
	
	
	// build
	public static AppException toThrow() {
		throw new AppException();
	}
	
	public static AppException toThrow(Throwable throwable) {
		throw new AppException(throwable);
	}
	
	public static AppException toThrow(MsgEnum msgEnum) {
		throw new AppException(msgEnum);
	}
	
	public static AppException toThrow(MsgEnum msgEnum, String param) {
		throw new AppException(msgEnum, new String[] {param});
	}
	
	public static AppException toThrow(MsgEnum msgEnum, String[] params) {
		throw new AppException(msgEnum, params);
	}
}