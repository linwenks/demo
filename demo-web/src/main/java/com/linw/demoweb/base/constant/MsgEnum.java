package com.linw.demoweb.base.constant;

import lombok.Getter;


@Getter
public enum MsgEnum {
	
	MSG_00002("00002", "系统繁忙，请稍候再试！")
	, MSG_00003("00003", "数据为空")
	, MSG_00004("00004", "数据异常")
	, MSG_00005("00005", "数据转换JSON异常")
	, MSG_00006("00006", "token为空")
	, MSG_00007("00007", "token不存在")
	, MSG_00008("00008", "redis事务失败")
	, MSG_00009("00009", "版本号为空")
	, MSG_00010("00010", "数据已跟新")
	
	// 登录注册。。。
	, MSG_01000("01000", "账号为空")
	, MSG_01001("01001", "设备ID为空")
	, MSG_01002("01002", "设备ID超过{0}字")
	, MSG_01003("01003", "账号格式错误")
	, MSG_01004("01004", "账号不存在")
	, MSG_01008("01008", "密码为空")
	, MSG_01009("01009", "密码为{0}到{1}位之间")
	, MSG_01010("01010", "密码格式错误")
	, MSG_01011("01011", "密码错误")
	, MSG_01012("01012", "验证码为空")
	, MSG_01013("01013", "验证码错误")
	, MSG_01014("01014", "账号已注册，请登录")
	, MSG_01015("01015", "账号已停用")
	, MSG_01016("01016", "登录验证码为空")
	, MSG_01017("01017", "登录验证码错误")
	, MSG_01018("01018", "第三方登录，以前未注册，跳转注册页面")
	, MSG_01019("01019", "第三方登录账号已被绑定")
	, MSG_01020("01020", "账号已绑定第三方登录账号")
	, MSG_01021("01021", "游客不能绑定第三方登录账号")
	
	, MSG_01005("01005", "一定时间内请勿重复发送验证码，请稍后再试")
	, MSG_01006("01006", "手机号为空")
	, MSG_01007("01007", "手机号格式错误")
	
	// 我的
	, MSG_03000("03000", "内容为空")
	, MSG_03001("03001", "内容超过{0}字")
	, MSG_03016("03016", "内容含有敏感词汇")
	, MSG_03017("03017", "请登录后再评论")
	, MSG_03002("03002", "文章已收藏")
	, MSG_03003("03003", "昵称为{0}到{1}字")
	, MSG_03004("03004", "用户现金不足")
	, MSG_03005("03005", "支付宝账号为空")
	, MSG_03006("03006", "支付宝账号长度6-20位")
	, MSG_03007("03007", "真实姓名为空")
	, MSG_03008("03008", "真实姓名2-10位")
	, MSG_03009("03009", "身份证为空")
	, MSG_03010("03010", "身份证格式错误")
	, MSG_03011("03011", "微信验证码不存在")
	, MSG_03012("03012", "请注册")
	, MSG_03013("03013", "微信没有绑定")
	, MSG_03014("03014", "连续签到不满{0}天")
	, MSG_03015("03015", "距离上次提现，连续签到不满{0}天")
	, MSG_03018("03018", "最少选择3个")
	, MSG_03019("03019", "账号已停用")
	, MSG_03020("03020", "每月{0}才能提现")
	
	// 任务
	, MSG_04000("04000", "已签到")
	, MSG_04001("04001", "红包已打开过")
	, MSG_04002("04002", "任务已经做过")
	, MSG_04003("04003", "该微信号已被其他账号绑定，请切换微信号再试")
	, MSG_04004("04004", "已拜师")
	, MSG_04005("04005", "请登录")
	, MSG_04006("04006", "不能拜比自己注册晚的用户为师傅")
	, MSG_04021("04021", "账号已停用")
	, MSG_04007("04007", "不能拜师自己的下级")
	, MSG_04008("04008", "不能拜自己为师傅")
	, MSG_04009("04009", "已抽奖")
	, MSG_04010("04010", "签到不足7日")
	, MSG_04011("04011", "最小投注100金币")
	, MSG_04012("04012", "竞猜未开始")
	, MSG_04013("04013", "竞猜已结束")
	, MSG_04014("04014", "不是世界杯竞猜活动")
	, MSG_04015("04015", "竞猜活动已停止")
	, MSG_04016("04016", "已参与竞猜")
	, MSG_04017("04017", "您当前金币不足，到任务大厅赚金币吧！")
	, MSG_04018("04018", "已分享")
	, MSG_04019("04019", "领取条件不符合，无法领取红包")
	, MSG_04020("04020", "超过最大播放次数")
	
	// 文章
	, MSG_05000("05000", "文章已投诉")
	, MSG_05001("05001", "不能回复自己")
	
	, MSG_06000("06000", "接收成功")
	, MSG_06001("06001", "订单已接收")
	, MSG_06002("06002", "校验失败")
	;
	
	private String key;
    private String value;
    
    MsgEnum(String key, String value) {
    	this.key = key;
    	this.value = value;
    }
}