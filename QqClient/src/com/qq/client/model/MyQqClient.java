/**
 * ���ǿͻ������ӷ������ĺ�̨
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
			//���������֤�û���¼�ĵط�
			if(ms.getMsType().equals("1"))
			{
				//����һ����qq�źͷ������˱���ͨѶ���ӵ��߳�
				ClientConnServerThread ccst = new ClientConnServerThread(s);
				//������ͨѶ�߳�
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
