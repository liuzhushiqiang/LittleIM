/**
 *���ǿͻ��˺ͷ���˱���ͨѶ���߳�
 */

package com.qq.client.model;

import java.net.*;
import java.io.*;

import javax.swing.JOptionPane;
import javax.swing.JTree;

import com.qq.client.model.*;
import com.qq.client.tool.AudioPlay;
import com.qq.client.view.ChatUI;
import com.qq.client.view.ClientMainUI;
import com.qq.client.view.DownloadFile;
import com.qq.client.view.FriendTree;
import com.qq.client.view.UploadFile;
import com.qq.common.Message;
import com.qq.common.MessageType;


public class ClientConnServerThread extends Thread{

	public Socket s;
	
	public Socket getS() {
		return s;
	}

	public ClientConnServerThread(Socket s)
	{
		this.s = s;
	}
	
	public void run()
	{
		while(true)
		{
			//��ͣ�Ķ�ȡ�ӷ������˷�������Ϣ
			try {
				ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
				Message m = (Message)ois.readObject();
				
				if(m.getMsType().equals(MessageType.message_comm_mes))
				{
					//�Ѵӷ����������Ϣ����ʾ������ʾ���������
					ChatUI qqChat =  ManageChatUI.getQqChat
					(m.getGetter() + " " + m.getSender());
					if(qqChat != null){
						//��ʾ
						ClientMainUI cmui = ManageClientMainUI.getQqFriendList(m.getGetter());
						cmui.getAp().soundPlay("/music/shangxian.wav");
						qqChat.showMessage(m);
					}else{
						ClientMainUI cmui = ManageClientMainUI.getQqFriendList(m.getGetter());
						//cmui.notify_sound();
						cmui.notify_msg(m);
					}
					
				}else if(m.getMsType().equals(MessageType.message_ret_onLineFriend))
				{
					//����ĳ�˵����ߺ����б��û���½�ɹ���ʱ����ܸ���Ϣ ��
					String getter = m.getGetter();
					//�޸���Ӧ�ĺ����б�
					ClientMainUI qqFriendList = ManageClientMainUI.getQqFriendList(getter);
					
					qqFriendList.getFriendList(m);
					qqFriendList.getjFrame();
					System.out.println();
				}else if(m.getMsType().equals(MessageType.message_ret_friend_login))
				{
					String sender = m.getSender();
					String getter = m.getGetter();
					ClientMainUI cmui = ManageClientMainUI.getQqFriendList(getter);
					JTree jtree = ManageJTree.getJTree(getter);
					FriendTree ft = ManageFriendTree.getFriendTree(getter);
					ft.shangxian(Integer.parseInt(sender));
					//cmui.setFriend(jtree);
					cmui.getjFrame();
				}else if(m.getMsType().equals(MessageType.message_ret_friend_logout)){
					String sender = m.getSender();
					String getter = m.getGetter();
					ClientMainUI cmui = ManageClientMainUI.getQqFriendList(getter);
					JTree jtree = ManageJTree.getJTree(getter);
					FriendTree ft = ManageFriendTree.getFriendTree(getter);
					ft.liveline(Integer.parseInt(sender));
					//cmui.setFriend(jtree);
					cmui.getjFrame();
				}else if(m.getMsType().equals(MessageType.message_recieve_delfriend)){
					ClientMainUI cmui = ManageClientMainUI.getQqFriendList(m.getGetter());
					FriendTree ft = ManageFriendTree.getFriendTree(m.getGetter());
					ft.delfriend(Integer.parseInt(m.getSender()));
					cmui.getjFrame();
				}else if(m.getMsType().equals(MessageType.message_add_friend_result)){
					//����Ҫ��ClientMainUI����֪ͨ����ʾ�Ƿ���ӳɹ�
					ClientMainUI cmui = ManageClientMainUI.getQqFriendList(m.getGetter());
					//cmui.notify_sound();
					cmui.notify_msg(m);
				}else if(m.getMsType().equals(MessageType.message_add_friend_request)){
					//����Ҫ��ClientMainUI����֪ͨ����ʾ���µ���Ӻ�������
					ClientMainUI cmui = ManageClientMainUI.getQqFriendList(m.getGetter());
					//cmui.notify_sound();
					cmui.notify_msg(m);
				}else if(m.getMsType().equals(MessageType.message_file_trans_request)){
					JOptionPane.showMessageDialog(null, m.getSender() 
							+ "���������ļ�,���ȷ����ʼ���ܡ�", "�����ļ�", 1);
					DownloadFile df = new DownloadFile(m.getGetter(), m.getSender());
					df.run();
//						m.setSender(m.getGetter());
//						m.setGetter(m.getSender());
//						m.setContent("yes");
//						m.setMsType(MessageType.message_file_trans_response);
//						ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
//						oos.writeObject(m);
//						DownloadFile df = new DownloadFile(m.getGetter(), m.getSender());
//						df.run();
//					}else{
//						m.setSender(m.getGetter());
//						m.setGetter(m.getSender());
//						m.setContent("no");
//						m.setMsType(MessageType.message_file_trans_response);
//						ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
//						oos.writeObject(m);
//					}
				}
//				else if(m.getMsType().equals(MessageType.message_file_trans_response)){
//					if(m.getContent().equals("yes")){
//						//ManageChatUI.getQqChat(m.getGetter() + "_" + m.getSender()).file_trans();
//						//no operation
//					}else if(m.getContent().equals("no")){
//						JOptionPane.showMessageDialog(null, "�Է��ܾ����������ļ�������.");
//					}
//				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
	}
}
