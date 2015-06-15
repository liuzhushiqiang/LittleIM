/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.qq.client.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.sound.sampled.AudioPermission;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;

import com.qq.client.model.ClientConnServerThread;
import com.qq.client.model.ManageChatUI;
import com.qq.client.model.ManageClientConnServerThread;
import com.qq.client.model.ManageFriendTree;
import com.qq.client.model.ManageJTree;
import com.qq.client.model.ManageClientMainUI;
import com.qq.client.tool.AudioPlay;
import com.qq.common.Message;
import com.qq.common.MessageType;
import com.qq.common.User;

/**
 * 
 * @author
 */
public class ClientMainUI implements ActionListener {
	public static void main(String[] args) {
		ClientMainUI cmui = new ClientMainUI(new User());
		cmui.getjFrame();
	}

	public ClientMainUI(User u) {
		this.userid = u.getUserID();
	}
	
	/**
	 * 用这个标志变量来控制通知图标是否闪动
	 */
	private boolean flag = false;
	
	private AudioPlay ap = new AudioPlay();

	public AudioPlay getAp() {
		return ap;
	}

	public void setAp(AudioPlay ap) {
		this.ap = ap;
	}

	private String userid;
	/**
	 * 窗体
	 */
	public JFrame jFrame = null;

	/**
	 * 添加JButton、JButton1、JButton2、 JButton3、jLabel、jLabel1、jPanel2
	 */
	private JPanel jContentPane = null;// 添加所有组件
	/**
	 * 系统提示喇叭
	 */
	private JLabel jLabel = null;

	/**
	 * 窗体界面
	 */
	private JLabel jLabel1 = null;

	/**
	 * 用户头像
	 */
	private JLabel headpic = null;

	/**
	 * 新增好友呢按钮
	 */
	private JButton jButton1 = null;

	/**
	 * 个人设置按钮
	 */
	private JButton jButton2 = null;

	/**
	 * 添加JTree
	 */
	private JPanel jPanel2 = null;

	/**
	 * 此方法初始化窗体
	 */
	
	public JFrame getjFrame() {
		if (jFrame == null) {
			jFrame = new JFrame();
			// jFrame.setAlwaysOnTop(true);
			jFrame.setSize(new Dimension(227, 680));
			jFrame.setResizable(false);
			jFrame.setTitle("MainWindow");
			try {
				jFrame.setContentPane(this.getJContentPane());
			} catch (Exception e) {
				// TODO: handle exception
			}
			jFrame.setFont(new Font("宋体", Font.PLAIN, 12));
			jFrame.setIconImage(Toolkit.getDefaultToolkit().getImage(
					getClass().getResource("/picture/client.gif")));
			Toolkit toolkit = jFrame.getToolkit();
			Dimension screen = toolkit.getScreenSize();
			jFrame.setBounds(screen.width - 227, 0, 227, 680);// //让窗体在屏幕右侧央显示
			jFrame.setVisible(true);
			jFrame.addWindowListener(new java.awt.event.WindowAdapter() {
				@Override
				public void windowClosing(java.awt.event.WindowEvent e) {
					// Client.client.logout();
					// 向服务器发出自己离线的信令
					Message m = new Message();
					m.setSender(userid);
					m.setMsType(MessageType.message_notify_self_logout);
					try {
						ObjectOutputStream oos = new ObjectOutputStream(
								ManageClientConnServerThread
										.getClientConnServerThread(userid).s
										.getOutputStream());
						oos.writeObject(m);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			jButton1.addActionListener(this);
			jButton2.addActionListener(this);
		}
		return jFrame;
	}

	/**
	 * 设置好友列表
	 */
	public void setFriend(JTree tree)// 传入一个节点
	{
		if (jPanel2 == null) {
			jPanel2 = new JPanel();
		}
		jPanel2.setLayout(new GridLayout(1, 1, 0, 0));
		jPanel2.setBounds(new Rectangle(1, 66, 226, 500));
		jPanel2.setBackground(SystemColor.inactiveCaptionText);
		JScrollPane js = new JScrollPane();
		js.getViewport().add(tree);
		jPanel2.add(js);
	}

	/**
	 * 此方法初始化添加组件后的jContentPane
	 */
	public JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jLabel1 = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit()
					.getImage(getClass().getResource("/picture/online.png"))));
			jLabel1.setBounds(new Rectangle(8, 8, 40, 40));
			// headpic = new JLabel("用户昵称");
			// headpic.setBounds(52,10,100,20);
			try {
				jContentPane.add(getheadpic());
			} catch (Exception e) {
				// TODO: handle exception
			}
			// headpic = new JLabel(new
			// ImageIcon(Toolkit.getDefaultToolkit().getImage(
			// getClass().getResource("/picture/head.png"))));
			jLabel = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit()
					.getImage(getClass().getResource("/picture/msg.jpg"))));
			jContentPane.add(jLabel);
			jLabel.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					// Client.client.acceptOrder();
					flag = true;
				}
			});
			jLabel.setBounds(new Rectangle(131, 600, 25, 30));
			jLabel.setFont(new Font("宋体", Font.PLAIN, 15));
			jContentPane.setBackground(new Color(204, 255, 255));

			jContentPane.setLayout(null);
			jContentPane.add(getJButton1());
			jContentPane.add(getJPanel());
			jContentPane.add(jLabel);
			jContentPane.add(jLabel1);
			jContentPane.add(getJButton2());
		}
		return jContentPane;
	}

	/**
	 * 返回新增好友按钮
	 */
	private JButton getJButton1() {
		if (jButton1 == null) {
			jButton1 = new JButton();
			jButton1.setFocusPainted(false);
			jButton1.setBounds(new Rectangle(10, 580, 86, 26));
			jButton1.setText("新增好友");
		}
		return jButton1;
	}

	/**
	 * 此方法初始化添加好友列表jPanel2
	 */
	private JPanel getJPanel() {
		if (jPanel2 == null) {
			jPanel2 = new JPanel();
			jPanel2.setLayout(new BoxLayout(jPanel2, BoxLayout.X_AXIS));
			jPanel2.setBounds(new Rectangle(0, 78, 226, 255));
			jPanel2.setBackground(SystemColor.inactiveCaptionText);
			jPanel2.repaint();
		}
		return jPanel2;
	}

	/**
	 * 此方法初始化个人设置按钮
	 */
	private JButton getJButton2() {
		if (jButton2 == null) {
			jButton2 = new JButton();
			jButton2.setFocusPainted(false);
			jButton2.setBounds(new Rectangle(10, 610, 86, 26));
			jButton2.setFont(new Font("宋体", Font.BOLD, 12));
			jButton2.setText("个人设置");
		}
		return jButton2;
	}

	/**
	 * 返回喇叭 JLabel
	 */
	public JLabel getJLabel() {
		return jLabel;
	}

	/**
	 * 返回喇叭 JLabel
	 */
	public JLabel getheadpic() {
		if (headpic == null) {
			headpic = new JLabel();
			// headpic.setFocusPainted(false);
			headpic.setFocusable(false);
			headpic.setBounds(new Rectangle(52, 10, 200, 20));
			headpic.setForeground(Color.BLUE);
			headpic.setFont(new Font("华文行楷", Font.PLAIN, 16));
			String nickname = null;

			// 这里向服务器获取自己的昵称
			ClientConnServerThread ccst = ManageClientConnServerThread
					.getClientConnServerThread(userid);
			Message m = new Message();
			m.setMsType(MessageType.message_get_userinfo);
			m.setSender(userid);

			try {
				ObjectOutputStream oos = new ObjectOutputStream(ccst.s
						.getOutputStream());
				oos.writeObject(m);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				ObjectInputStream ois = new ObjectInputStream(ccst.s
						.getInputStream());
				try {
					User u = (User) ois.readObject();
					nickname = u.getNickname();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			headpic.setText("昵称  / " + nickname);
		}
		return headpic;
	}

	/**
	 * 设置喇叭jLabel
	 */
	public void setJLabel(JLabel label) {
		jLabel = label;
	}

	/**
	 * 设置用户呢称标签headpic
	 */
	// public void setheadpic(JLabel setheadpic)
	// {
	// headpic=setheadpic;
	// }
	/**
	 * 实现ActionListener接口所需实现的方法
	 */
	public void actionPerformed(ActionEvent e) {
		/**
		 * 新增好友
		 */
		if (e.getSource() == jButton1) {
			// Client.client.addFriend();
			String s = JOptionPane.showInputDialog(null, "请输入要加的用户号码", "添加好友",
					3);
			if (s == null) {
				return;
			}
			int id = 0;
			try {
				id = Integer.parseInt(s);
			} catch (Exception e1) {
				 ap.soundPlay("/music/system.wav");
				JOptionPane.showMessageDialog(null, "输入不正确", "警告", 2);
				return;
			}

			//向服务器发送添加好友的请求
			try {
				ClientConnServerThread ccst = 
					ManageClientConnServerThread.getClientConnServerThread(userid);
				Message m = new Message();
				m.setMsType(MessageType.message_add_friend_request);
				m.setSender(userid);
				m.setGetter(s);

				ObjectOutputStream oos = new ObjectOutputStream(ccst.s
						.getOutputStream());
				oos.writeObject(m);
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		/**
		 * 个人设置
		 */
		if (e.getSource() == jButton2) {
			// Client.client.ModifyUserInfo();
			ModifyUserUI muui = new ModifyUserUI(userid);
			muui.getUserInfo(userid);
			muui.getJFrame();
		}
	}

	/*
	 * 更新"在线好友"或"不在线好友"列表
	 */
	public void getFriendList(Message m) {
		try {
			FriendTree ft = new FriendTree(userid);
			JTree jtree = ft.chushi();
			String content = m.getContent();
			String[] res = content.split("_");
			System.out.println("m.getcontent = " + m.getContent()
					+ ";res.length = " + res.length
					+ ";in ClientMainUI.java updateFriend");
			// 更新"在线好友列表"
			// System.out.println(onlineFriend[0]);
			if (res.length != 0 && !res[0].equals("")) {
				String onlineFriends[] = res[0].split(" ");
				for (int i = 0; i < onlineFriends.length; i++) {
					if (!onlineFriends[i].equals("")) {
						ft.addonlinefriend(Integer.parseInt(onlineFriends[i]));
					}
				}
			}

			// 更新"不在线好友列表"
			if (res.length == 2 && !res[1].equals("")) {
				String notOnlineFriends[] = res[1].split(" ");
				for (int i = 0; i < notOnlineFriends.length; i++) {
					if (!notOnlineFriends[i].equals("")) {
						ft.addnotonlinefriend(Integer
								.parseInt(notOnlineFriends[i]));
					}
				}
			}

			ManageFriendTree.addFriendTree(userid, ft);
			ManageJTree.addJTree(userid, jtree);
			this.setFriend(jtree);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
//	/**
//	 * 收到消息时的声音提醒
//	 */
//	public void notify_sound(){
//		ap.soundPlay("/music/shangxian.wav");
//	}
	
	class Notify extends Thread{
		
		private Message m = null;
		public Notify(Message m){
			this.m = m;
			flag = false;
			ap.soundPlay("/music/shangxian.wav");
		}
		
		public void run(){
			for (int i = 0;; i = i+5)
			{
				if (i % 2 == 0)
				{
					jLabel.setVisible(false);
				}
				if (i % 2 == 1)
				{
					jLabel.setVisible(true);
				}
				if (flag)
				{
					jLabel.setVisible(true);
					break;
				}
			}
			if(m.getMsType().equals(MessageType.message_add_friend_request)){
				// 向服务器发送是否接受某人好友请求的消息
				if(JOptionPane.showConfirmDialog(getjFrame(), m.getSender() 
						+ "向您发出好友请求，是否接受？", "好友请求", JOptionPane.OK_CANCEL_OPTION)
						== JOptionPane.OK_OPTION){
					//同意对方的请求，先在自己的好友列表中添加对方，再给服务器回送消息
					FriendTree ft = ManageFriendTree.getFriendTree(userid);
					ft.addNode2(Integer.parseInt(m.getSender()));
					getjFrame();			
					
					try {
						ClientConnServerThread ccst = 
							ManageClientConnServerThread.getClientConnServerThread(userid);
						Message m1 = new Message();
						m1.setMsType(MessageType.message_add_friend_result);
						m1.setContent("yes");
						m1.setSender(userid);
						m1.setGetter(m.getSender());

						ObjectOutputStream oos = new ObjectOutputStream(ccst.s
								.getOutputStream());
						oos.writeObject(m1);
						
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}else {
					// 发出向服务器查找的请求信令只包括SourceID和DestID
					try {
						ClientConnServerThread ccst = 
							ManageClientConnServerThread.getClientConnServerThread(userid);
						Message m1 = new Message();
						m1.setMsType(MessageType.message_add_friend_result);
						m1.setContent("no");
						m1.setSender(userid);
						m1.setGetter(m.getSender());

						ObjectOutputStream oos = new ObjectOutputStream(ccst.s
								.getOutputStream());
						oos.writeObject(m1);
						
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}else if(m.getMsType().equals(MessageType.message_add_friend_result)){
				if(m.getContent().equals("yes")){
					JOptionPane.showMessageDialog(null, m.getSender() 
							+ "通过了您的好友请求。", "通知", 1);
					//在自己的好友列表中添加对方
					FriendTree ft = ManageFriendTree.getFriendTree(userid);
					ft.addNode2(Integer.parseInt(m.getSender()));
					getjFrame();	
				}else if(m.getContent().equals("no")){
					//do no operation
					JOptionPane.showMessageDialog(null, m.getSender() 
							+ "拒绝了您的好友请求。", "通知", 1);
				}
			}else if(m.getMsType().equals(MessageType.message_comm_mes)){
				ChatUI cui = new ChatUI(m.getGetter(), m.getSender());
				ManageChatUI.addQqChat(m.getGetter() + " " + m.getSender(), cui);
				cui.getJFrame();
				cui.showMessage(m);
			}
		}
	}
	
	public void notify_msg(Message m){
		Notify n = new Notify(m);
		n.start();
	}
}
