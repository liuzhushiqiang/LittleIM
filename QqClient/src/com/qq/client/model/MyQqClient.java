/**
 * 这是客户端连接服务器的后台
 */
package com.qq.client.model;

import java.net.*;
import java.io.*;
import java.util.*;

import com.qq.client.model.*;
import com.qq.common.*;


public class MyQqClient {
	
	
	public boolean checkLogin(Object o)
	{
		boolean b = false;
		try {
			Socket s = new Socket("127.0.0.1", 9999);
			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(o);
			
			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
			Message ms = (Message)ois.readObject();
			//这里就是验证用户登录的地方
			if(ms.getMsType().equals("1"))
			{
				//创建一个该qq号和服务器端保持通讯连接的线程
				ClientConnServerThread ccst = new ClientConnServerThread(s);
				//启动该通讯线程
				ccst.start();
				ManageClientConnServerThread.addClientConnServerThread
				(((User)o).getUserID(), ccst);
				b = true;
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}finally{
			
		}
		return b;
	}
}
