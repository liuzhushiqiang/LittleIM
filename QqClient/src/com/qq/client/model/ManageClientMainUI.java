/**
 * ����qq���ѣ�İ���ˣ�������������
 */

package com.qq.client.model;

import java.util.*;
import java.io.*;
import com.qq.client.view.*;

public class ManageClientMainUI {
	
	private static HashMap hm = new HashMap<String, ClientMainUI>();
	
	public static void addQqFriendList(String qqid, ClientMainUI qqFriendList)
	{
		hm.put(qqid, qqFriendList);
	}
	
	public static ClientMainUI getQqFriendList(String qqId)
	{
		return (ClientMainUI)hm.get(qqId);
	}
}
