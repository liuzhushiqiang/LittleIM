/**
 * ����һ������ͻ��˺ͷ���������ͨѶ���߳���
 */

package com.qq.client.model;

import java.util.*;

public class ManageClientConnServerThread {

	static HashMap hm = new HashMap<String, ClientConnServerThread>();
	//�Ѵ����õ�ClientConnServerThread���뵽hm
	public static void addClientConnServerThread(String qqId, ClientConnServerThread ccst)
	{
		hm.put(qqId, ccst);
	}
	
	public static ClientConnServerThread getClientConnServerThread(String qqId)
	{
		return (ClientConnServerThread)hm.get(qqId);
	}
}
