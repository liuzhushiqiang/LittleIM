package com.qq.server.model;

import java.util.*;

import javax.print.attribute.standard.Severity;
import javax.swing.text.html.HTMLDocument.Iterator;

public class ManageServerConnClientThread {

	public static HashMap hm = new HashMap<String, ServerConnClientThread>();
	
	//向hm中添加一个客户端通讯线程
	public static void addClientThread(String uid, ServerConnClientThread ct)
	{
		hm.put(uid, ct);
	}
	
	public static ServerConnClientThread getClientThread(String uid)
	{
		return (ServerConnClientThread)hm.get(uid);
	}
}
