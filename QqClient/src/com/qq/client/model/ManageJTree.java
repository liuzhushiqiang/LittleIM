package com.qq.client.model;

import java.util.HashMap;
import javax.swing.JTree;

public class ManageJTree {
	public static HashMap<String, JTree> hm = new HashMap<String, JTree>();
	
	public static void addJTree(String srcid, JTree jtree){
		hm.put(srcid, jtree);
	}
	
	public static JTree getJTree(String srcid){
		return (JTree)hm.get(srcid);
	}
}
