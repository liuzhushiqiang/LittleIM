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
	 * �������־����������֪ͨͼ���Ƿ�����
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
	 * ����
	 */
	public JFrame jFrame = null;

	/**
	 * ���JButton��JButton1��JButton2�� JButton3��jLabel��jLabel1��jPanel2
	 */
	private JPanel jContentPane = null;// ����������
	/**
	 * ϵͳ��ʾ����
	 */
	private JLabel jLabel = null;

	/**
	 * �������
	 */
	private JLabel jLabel1 = null;

	/**
	 * �û�ͷ��
	 */
	private JLabel headpic = null;

	/**
	 * ���������ذ�ť
	 */
	private JButton jButton1 = null;

	/**
	 * �������ð�ť
	 */
	private JButton jButton2 = null;

	/**
	 * ���JTree
	 */
	private JPanel jPanel2 = null;

	/**
	 * �˷�����ʼ������
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
			jFrame.setFont(new Font("����", Font.PLAIN, 12));
			jFrame.setIconImage(Toolkit.getDefaultToolkit().getImage(
					getClass().getResource("/picture/client.gif")));
			Toolkit toolkit = jFrame.getToolkit();
			Dimension screen = toolkit.getScreenSize();
			jFrame.setBounds(screen.width - 227, 0, 227, 680);// //�ô�������Ļ�Ҳ�����ʾ
			jFrame.setVisible(true);
			jFrame.addWindowListener(new java.awt.event.WindowAdapter() {
				@Override
				public void windowClosing(java.awt.event.WindowEvent e) {
					// Client.client.logout();
					// ������������Լ����ߵ�����
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
	 * ���ú����б�
	 */
	public void setFriend(JTree tree)// ����һ���ڵ�
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
	 * �˷�����ʼ�����������jContentPane
	 */
	public JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jLabel1 = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit()
					.getImage(getClass().getResource("/picture/online.png"))));
			jLabel1.setBounds(new Rectangle(8, 8, 40, 40));
			// headpic = new JLabel("�û��ǳ�");
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
			jLabel.setFont(new Font("����", Font.PLAIN, 15));
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
	 * �����������Ѱ�ť
	 */
	private JButton getJButton1() {
		if (jButton1 == null) {
			jButton1 = new JButton();
			jButton1.setFocusPainted(false);
			jButton1.setBounds(new Rectangle(10, 580, 86, 26));
			jButton1.setText("��������");
		}
		return jButton1;
	}

	/**
	 * �˷�����ʼ����Ӻ����б�jPanel2
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
	 * �˷�����ʼ���������ð�ť
	 */
	private JButton getJButton2() {
		if (jButton2 == null) {
			jButton2 = new JButton();
			jButton2.setFocusPainted(false);
			jButton2.setBounds(new Rectangle(10, 610, 86, 26));
			jButton2.setFont(new Font("����", Font.BOLD, 12));
			jButton2.setText("��������");
		}
		return jButton2;
	}

	/**
	 * �������� JLabel
	 */
	public JLabel getJLabel() {
		return jLabel;
	}

	/**
	 * �������� JLabel
	 */
	public JLabel getheadpic() {
		if (headpic == null) {
			headpic = new JLabel();
			// headpic.setFocusPainted(false);
			headpic.setFocusable(false);
			headpic.setBounds(new Rectangle(52, 10, 200, 20));
			headpic.setForeground(Color.BLUE);
			headpic.setFont(new Font("�����п�", Font.PLAIN, 16));
			String nickname = null;

			// �������������ȡ�Լ����ǳ�
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
			headpic.setText("�ǳ�  / " + nickname);
		}
		return headpic;
	}

	/**
	 * ��������jLabel
	 */
	public void setJLabel(JLabel label) {
		jLabel = label;
	}

	/**
	 * �����û��سƱ�ǩheadpic
	 */
	// public void setheadpic(JLabel setheadpic)
	// {
	// headpic=setheadpic;
	// }
	/**
	 * ʵ��ActionListener�ӿ�����ʵ�ֵķ���
	 */
	public void actionPerformed(ActionEvent e) {
		/**
		 * ��������
		 */
		if (e.getSource() == jButton1) {
			// Client.client.addFriend();
			String s = JOptionPane.showInputDialog(null, "������Ҫ�ӵ��û�����", "��Ӻ���",
					3);
			if (s == null) {
				return;
			}
			int id = 0;
			try {
				id = Integer.parseInt(s);
			} catch (Exception e1) {
				 ap.soundPlay("/music/system.wav");
				JOptionPane.showMessageDialog(null, "���벻��ȷ", "����", 2);
				return;
			}

			//�������������Ӻ��ѵ�����
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
		 * ��������
		 */
		if (e.getSource() == jButton2) {
			// Client.client.ModifyUserInfo();
			ModifyUserUI muui = new ModifyUserUI(userid);
			muui.getUserInfo(userid);
			muui.getJFrame();
		}
	}

	/*
	 * ����"���ߺ���"��"�����ߺ���"�б�
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
			// ����"���ߺ����б�"
			// System.out.println(onlineFriend[0]);
			if (res.length != 0 && !res[0].equals("")) {
				String onlineFriends[] = res[0].split(" ");
				for (int i = 0; i < onlineFriends.length; i++) {
					if (!onlineFriends[i].equals("")) {
						ft.addonlinefriend(Integer.parseInt(onlineFriends[i]));
					}
				}
			}

			// ����"�����ߺ����б�"
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
//	 * �յ���Ϣʱ����������
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
				// ������������Ƿ����ĳ�˺����������Ϣ
				if(JOptionPane.showConfirmDialog(getjFrame(), m.getSender() 
						+ "�����������������Ƿ���ܣ�", "��������", JOptionPane.OK_CANCEL_OPTION)
						== JOptionPane.OK_OPTION){
					//ͬ��Է������������Լ��ĺ����б�����ӶԷ����ٸ�������������Ϣ
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
					// ��������������ҵ���������ֻ����SourceID��DestID
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
							+ "ͨ�������ĺ�������", "֪ͨ", 1);
					//���Լ��ĺ����б�����ӶԷ�
					FriendTree ft = ManageFriendTree.getFriendTree(userid);
					ft.addNode2(Integer.parseInt(m.getSender()));
					getjFrame();	
				}else if(m.getContent().equals("no")){
					//do no operation
					JOptionPane.showMessageDialog(null, m.getSender() 
							+ "�ܾ������ĺ�������", "֪ͨ", 1);
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
