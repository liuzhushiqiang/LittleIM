package com.qq.common;

import java.io.*;

public class Message implements Serializable {
	private String msType;
	
	private String sender;
	private String getter;
	private String content;
	private String sendTime;

	public String getMsType() {
		return msType;
	}

	public void setMsType(String msType) {
		this.msType = msType;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getGetter() {
		return getter;
	}

	public void setGetter(String getter) {
		this.getter = getter;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSendTime() {
		return sendTime;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}
	
}
