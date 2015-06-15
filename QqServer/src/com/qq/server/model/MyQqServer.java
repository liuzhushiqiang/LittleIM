/**
 * ����qq�����������ڼ������ȴ�ĳ��qq�ͻ���������
 */

package com.qq.server.model;

import java.net.*;
import java.io.*;
import java.util.*;

import com.qq.common.*;
import com.qq.server.service.UsersService;

public class MyQqServer implements Runnable {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			// �ڿ���̨��ӡ��ʾ���������
			System.out.println("���������ڼ���");
			// ��9999�˿ڼ���
			ServerSocket ss = new ServerSocket(9999);
			// �������ȴ�����
			while (true) {
				Socket s = ss.accept();
				// ���ܿͻ��˷�������Ϣ

				ObjectInputStream ois = new ObjectInputStream(s
						.getInputStream());
				Object o = ois.readObject();
				// �����жϴӿͻ��˷�������Ϣ�ǵ�½������ע������
				if (o instanceof User) {
					User u = (User) o;
					if("1".equals(u.getIsonline())){
						UsersService us = new UsersService();
						boolean res = us.modifyUserInfo(u);
						Message m = new Message();
						if(res){
							m.setContent("ok");
						}else{
							m.setContent("error");
						}
						ObjectOutputStream oos = new ObjectOutputStream
						(s.getOutputStream());
						oos.writeObject(m);
					}else{
						Message m = new Message();
						ObjectOutputStream oos = new ObjectOutputStream(s
								.getOutputStream());
						if (new UsersService().checklogin(u)) {
							// ����һ���ɹ���½����Ϣ��

							m.setMsType("1");
							oos.writeObject(m);

							// ���ﵥ��һ���̺߳Ϳͻ��˱���ͨѶ
							ServerConnClientThread scct = new ServerConnClientThread(
									s, u.getUserID());
							// ���̼߳��뵽hashmap��
							ManageServerConnClientThread.addClientThread(u
									.getUserID(), scct);
							// ������ÿͻ���ͨѶ���߳�
							scct.start();

							// ��֪ͨ�����û�
							scct.notifyOther(u.getUserID());
							// ��ȡ���ߺ����б�
							scct.getFriendList(u.getUserID());
						} else {
							// ����һ����½ʧ�ܵ���Ϣ��
							m.setMsType("2");
							oos.writeObject(m);
							// �ر�����
							s.close();
						}
					}
					
				} else if (o instanceof Message) {
					Message m = (Message) o;
					if (m.getMsType().equals(
							com.qq.common.MessageType.message_get_reg)) {
						UsersService us = new UsersService();
						int ret = us.register(m.getContent());
						if (ret != 0) {
							Message m1 = new Message();
							m1.setMsType(com.qq.common.MessageType.message_ret_reg);
							m1.setContent(ret + "");
							ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
							oos.writeObject(m1);
						} else {
							Message m1 = new Message();
							m1.setMsType(com.qq.common.MessageType.message_ret_reg);
							m1.setContent("0");
							ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
							oos.writeObject(m1);
						}
					}else if(m.getMsType().equals
							(com.qq.common.MessageType.message_get_userinfo)){
						UsersService us = new UsersService();
						User u = us.getUserinfo(m.getSender());
						System.out.println(u.getUserID() + u.getNickname() + 
								" in ServerConnClientThread.java");
						ObjectOutputStream oos = new ObjectOutputStream
						(s.getOutputStream());
						oos.writeObject(u);
					}
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {

		}
	}

}
