/**
 * ����һ�������û�����������
 */

package com.qq.client.model;

import java.util.*;
import com.qq.client.view.*;

public class ManageChatUI {
	
	private static HashMap hm = new HashMap<String, ChatUI>();
	
	//����
	public static void addQqChat(String loginIdAndFriendId, ChatUI qqchat)
	{
		hm.put(loginIdAndFriendId, qqchat);
	}
	
	public static ChatUI getQqChat(String loginIdAndFriendId)
	{
		return (ChatUI)hm.get(loginIdAndFriendId);
	}
	
	public static void delQqChat(String loginIdAndFriendId){
		hm.remove(loginIdAndFriendId);
	}

}
