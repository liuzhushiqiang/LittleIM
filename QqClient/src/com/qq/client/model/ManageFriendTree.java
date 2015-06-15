package com.qq.client.model;

import java.util.HashMap;
import javax.swing.JTree;

import com.qq.client.view.FriendTree;

public class ManageFriendTree {
	public static HashMap<String, FriendTree> hm = new HashMap<String, FriendTree>();
	
	public static void addFriendTree(String srcid, FriendTree ft){
		hm.put(srcid, ft);
	}
	
	public static FriendTree getFriendTree(String srcid){
		return (FriendTree)hm.get(srcid);
	}
}
