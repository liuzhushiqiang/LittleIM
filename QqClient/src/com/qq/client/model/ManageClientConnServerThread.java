/**
 * 这是一个管理客户端和服务器保持通讯的线程类
 */

package com.qq.client.model;

import java.util.*;

public class ManageClientConnServerThread {

	static HashMap hm = new HashMap<String, ClientConnServerThread>();
	//把创建好的ClientConnServerThread放入到hm
	public static void addClientConnServerThread(String qqId, ClientConnServerThread ccst)
	{
		hm.put(qqId, ccst);
	}
	
	public static ClientConnServerThread getClientConnServerThread(String qqId)
	{
		return (ClientConnServerThread)hm.get(qqId);
	}
}
