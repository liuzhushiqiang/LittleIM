/**
 * 这是qq服务器，它在监听，等待某个qq客户端来连接
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
			// 在控制台打印提示，方便调试
			System.out.println("服务器正在监听");
			// 在9999端口监听
			ServerSocket ss = new ServerSocket(9999);
			// 阻塞，等待连接
			while (true) {
				Socket s = ss.accept();
				// 接受客户端发来的信息

				ObjectInputStream ois = new ObjectInputStream(s
						.getInputStream());
				Object o = ois.readObject();
				// 这里判断从客户端发来的消息是登陆请求还是注册请求
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
							// 返回一个成功登陆的信息包

							m.setMsType("1");
							oos.writeObject(m);

							// 这里单开一个线程和客户端保持通讯
							ServerConnClientThread scct = new ServerConnClientThread(
									s, u.getUserID());
							// 把线程加入到hashmap中
							ManageServerConnClientThread.addClientThread(u
									.getUserID(), scct);
							// 启动与该客户端通讯的线程
							scct.start();

							// 并通知在线用户
							scct.notifyOther(u.getUserID());
							// 获取在线好友列表
							scct.getFriendList(u.getUserID());
						} else {
							// 返回一个登陆失败的信息包
							m.setMsType("2");
							oos.writeObject(m);
							// 关闭连接
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
