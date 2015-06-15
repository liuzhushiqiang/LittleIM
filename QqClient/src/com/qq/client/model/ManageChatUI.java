/**
 * 这是一个管理用户聊天界面的类
 */

package com.qq.client.model;

import java.util.*;
import com.qq.client.view.*;

public class ManageChatUI {
	
	private static HashMap hm = new HashMap<String, ChatUI>();
	
	//加入
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
