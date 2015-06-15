/**
 * ���ܣ��Ƿ�������ĳ���ͻ��˵�ͨѶ�߳�
 */

package com.qq.server.model;

import java.util.*;
import java.net.*;
import java.util.HashMap;
import java.util.Iterator;
import java.awt.TrayIcon.MessageType;
import java.io.*;

import com.qq.common.*;
import com.qq.server.domain.Users;
import com.qq.server.service.UsersService;

public class ServerConnClientThread extends Thread {

	Socket s;
	String srcid;

	public ServerConnClientThread(Socket s, String srcid) {
		// �ѷ������͸ÿͻ��˵����Ӹ���s
		this.s = s;
		this.srcid = srcid;
	}

	// �ø��߳�ȥ֪ͨ�����û�
	public void notifyOther(String srcid) {
		UsersService us = new UsersService();
		String content = us.getOnlineFriend(us.getFriends(srcid));
		String[] onlineFriends = content.split(" ");
		for (int i = 0; i < onlineFriends.length; i++) {
			Message m2 = new Message();
			m2.setSender(srcid);
			m2.setGetter(onlineFriends[i]);
			m2.setMsType(com.qq.common.MessageType.message_ret_friend_login);
			try {
				ServerConnClientThread sc = ManageServerConnClientThread
						.getClientThread(onlineFriends[i]);
				if (sc != null) {
					ObjectOutputStream oos = new ObjectOutputStream(sc.s
							.getOutputStream());
					oos.writeObject(m2);
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}

	}

	// ��ȡ�����б�
	public void getFriendList(String srcid) {
		String res1 = null;
		String res2 = null;

		UsersService us = new UsersService();
		res1 = us.getOnlineFriend(us.getFriends(srcid));
		res2 = us.getNotOnlineFriend(us.getFriends(srcid));
		String content = res1 + "_" + res2;
		Message m2 = new Message();
		m2.setMsType(com.qq.common.MessageType.message_ret_onLineFriend);
		m2.setContent(content);
		System.out.println(content + "in ServerConnClientThread.java");
		m2.setGetter(srcid);
		try {
			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(m2);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public void run() {
		while (true) {
			// ������߳̽��ܿͻ��˵���Ϣ
			try {
				ObjectInputStream ois = new ObjectInputStream(s
						.getInputStream());
				Message m = (Message) ois.readObject();

				// �Դӿͻ���ȡ�õ���Ϣ���������жϣ�Ȼ������Ӧ�Ĵ���
				if (m.getMsType().equals(
						com.qq.common.MessageType.message_comm_mes)) {
					// ���ת��
					// ȡ�ý����˵�ͨ���߳�
					ServerConnClientThread sc = ManageServerConnClientThread
							.getClientThread(m.getGetter());
					ObjectOutputStream oos = new ObjectOutputStream(sc.s
							.getOutputStream());
					oos.writeObject(m);
				} else if (m.getMsType().equals(
						com.qq.common.MessageType.message_notify_self_logout)) {
					// �Ȱ��Լ���״̬isonline��Ϊ0
					UsersService us = new UsersService();
					us.setLogout(srcid);

					// �����Լ��ĺ��ѷ����Լ����ߵ���Ϣ
					String sender = m.getSender();
					String content = us.getOnlineFriend(us.getFriends(sender));
					String[] onlineFriends = content.split(" ");
					for (int i = 0; i < onlineFriends.length; i++) {
						Message m2 = new Message();
						m2.setSender(sender);
						m2.setGetter(onlineFriends[i]);
						m2
								.setMsType(com.qq.common.MessageType.message_ret_friend_logout);
						try {
							ServerConnClientThread sc = ManageServerConnClientThread
									.getClientThread(onlineFriends[i]);
							if (sc != null) {
								ObjectOutputStream oos = new ObjectOutputStream(
										sc.s.getOutputStream());
								oos.writeObject(m2);
							}

						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}
					}
				} else if (m.getMsType().equals(
						com.qq.common.MessageType.message_notify_delfriend)) {
					// �ȵ����ݿ�ȥ���º��ѹ�ϵ
					UsersService us = new UsersService();
					String souceid = m.getSender();
					String destid = m.getGetter();
					us.delFriend(souceid, destid);
					// �ٸ���ɾ���ĺ��ѷ�ȥ֪ͨ������Ҳ���º����б�
					m
							.setMsType(com.qq.common.MessageType.message_recieve_delfriend);
					ServerConnClientThread sc = ManageServerConnClientThread
							.getClientThread(m.getGetter());
					ObjectOutputStream oos = new ObjectOutputStream(sc.s
							.getOutputStream());
					oos.writeObject(m);
				} else if (m.getMsType().equals(
						com.qq.common.MessageType.message_get_userinfo)) {
					UsersService us = new UsersService();
					User u = us.getUserinfo(m.getSender());
					System.out.println(u.getUserID() + u.getNickname()
							+ " in ServerConnClientThread.java");
					ObjectOutputStream oos = new ObjectOutputStream(s
							.getOutputStream());
					oos.writeObject(u);
				} else if (m.getMsType().equals(
						com.qq.common.MessageType.message_add_friend_request)) {
					ServerConnClientThread sc = ManageServerConnClientThread
							.getClientThread(m.getGetter());
					ObjectOutputStream oos = new ObjectOutputStream(sc.s
							.getOutputStream());
					oos.writeObject(m);
				} else if (m.getMsType().equals(
						com.qq.common.MessageType.message_add_friend_result)) {
					// ���ж��Ƿ���Ҫ�����ݿ�ȥ���º��ѹ�ϵ
					if (m.getContent().equals("yes")) {
						UsersService us = new UsersService();
						us.addFriend(m.getSender(), m.getGetter());
					} else if (m.getContent().equals("no")) {
						// no opereation
					}
					ServerConnClientThread sc = ManageServerConnClientThread
							.getClientThread(m.getGetter());
					ObjectOutputStream oos = new ObjectOutputStream(sc.s
							.getOutputStream());
					oos.writeObject(m);
				} else if (m.getMsType().equals(
						com.qq.common.MessageType.message_file_trans_request)) {
					ObjectOutputStream oos = new 
					ObjectOutputStream(ManageServerConnClientThread.
							getClientThread(m.getGetter()).s.getOutputStream());
					oos.writeObject(m);
					
					//String fileDir = "";
					//FileOutputStream fos = null;
					try {
						// in = client.getInputStream();
						DataInputStream dis = new DataInputStream(s
										.getInputStream());
						DataOutputStream dos = new DataOutputStream
						(ManageServerConnClientThread.getClientThread
								(m.getGetter()).s.getOutputStream());
						dos.writeUTF(dis.readUTF());
						long fileLength = dis.readLong();
						dos.writeLong(fileLength);

						long xzdx = 0;
						//fos = new FileOutputStream(fileDir + File.separator
						//		+ fileName);
						byte[] buffer = new byte[1024];
						int len = 0;
						while (xzdx < fileLength && (len = dis.read(buffer)) > 0) {
							dos.write(buffer, 0, len);
							xzdx += len;
						}
						//dos.write(buffer);
						//fos.flush();
						//fos.close();
						// in.close();
						// client.close() ;
//						if (xzdx != fileLength) { // ����ļ�δ���꣬��ɾ�������������˵��ļ�
//							File f = new File(fileDir + File.separator
//									+ fileName);
//							f.delete();
//						}
					} catch (Exception e) {
						System.out.println("�쳣��������");
					}
				}
//				else if(m.getMsType().equals(com.qq.common.MessageType.
//						message_file_trans_response)){
//						
//						ObjectOutputStream oos = new ObjectOutputStream
//						(ManageServerConnClientThread.
//								getClientThread(m.getGetter()).s.getOutputStream());
//						oos.writeObject(m);
//				}

			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			} finally {

			}

		}
	}
}
