package com.frame.common.frame.base.enums;

/**
 * 查询连接符
 * @author duancq
 * 2016年8月26日 下午5:45:06
 */
public enum AndOr {

	AND("AND", "并且"),
	OR("OR", "或者");

	private String name;
	private String text;
	private AndOr(String name, String text) {
		this.name = name;
		this.text = text;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
}
