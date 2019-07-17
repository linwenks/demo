package com.linw.demoweb.base.vo;

import com.linw.demoweb.base.constant.MsgEnum;

import lombok.Getter;

@Getter
public class MsgBean {
	
	private String code;
	
	private String field;
	
	private String des;

	public MsgBean() {
	}

	public MsgBean(MsgEnum msgEnum) {
		this(msgEnum, null, null);
	}
	
	public MsgBean(MsgEnum msgEnum, String field) {
		this(msgEnum, null, field);
	}
	
	public MsgBean(MsgEnum msgEnum, String[] params) {
		this(msgEnum, params, null);
	}
	
	public MsgBean(MsgEnum msgEnum, String[] params, String field) {
		this.code = msgEnum.getKey();
		this.field = field;
		
		this.des = msgEnum.getValue();
		if (params != null && params.length > 0) {
			for (int i=0; i<params.length; i++) {
				des = des.replaceAll("\\{" + i + "}", params[i]);
			}
		}
	}
}