/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.qq.client.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import com.qq.client.model.ManageClientConnServerThread;
import com.qq.client.model.ManageClientMainUI;
import com.qq.client.model.MyQqClient;
import com.qq.common.Message;
import com.qq.common.MessageType;
import com.qq.common.User;

/**
 * 
 * @author
 */
public class LoginUI implements ActionListener {
	
	public static void main(String[] args){
		LoginUI lui = new LoginUI();
		lui.getJFrame();
	}
	
	/**
	 * 窗体
	 */
	private JFrame jFrame = null;

	/**
	 * 放置其他所有组件
	 */
	private JPanel jContentPane = null;

	/**
	 * 放置字符串号码
	 */
	private JLabel toID = null;

	/**
	 * 号码输入框
	 */
	private JTextField ID = null;

	/**
	 * 放置字符串密码
	 */
	private JLabel topass = null;

	/**
	 * 密码输入框
	 */
	public JPasswordField PasswordField = null;

	/**
	 * 登录按钮
	 */
	private JButton login = null;

	/**
	 * 取消按钮
	 */
	private JButton quit = null;

	/**
	 * 注册按钮
	 */
	private JButton regedit = null;

	/**
	 * 登录IP
	 */
	private JLabel IP = null;

	/**
	 *获取本地IP
	 */
	public static InetAddress getLocalHost() throws UnknownHostException {
		InetAddress LocalIP = InetAddress.getLocalHost();
		return LocalIP;
	}

	/**
	 * 此方法初始化jFrame
	 */
	public JFrame getJFrame() {
		if (jFrame == null) {
			jFrame = new JFrame();// 实例化jFrame
			// jFrame.setAlwaysOnTop(true);
			jFrame.setSize(265, 140);// 封装jFrame对象的宽度和高度
			jFrame.setTitle("用户登录");// 设置窗体标题
			Toolkit toolkit = jFrame.getToolkit();// Tookit是AWT所有实际实现的抽象超类
			Dimension screen = toolkit.getScreenSize();// 得到屏幕的大小
			jFrame.setIconImage(Toolkit.getDefaultToolkit().getImage(
					getClass().getResource("/picture/client.gif")));// 设置窗体图标
			jFrame.setBounds(screen.width / 2 - 265 / 2,
					screen.height / 2 - 174 / 2, 318, 206);// //让窗体在屏幕正中央显示
			jFrame.setContentPane(getJContentPane());// 把窗体的内容面板设置为jContentPane
			jFrame.setResizable(false);// //固定窗口大小
			jFrame.setVisible(true);// 设置窗体为可见
			regedit.addActionListener(this);// 为添加行为事件监听
			login.addActionListener(this);// 为添加行为事件监听
			// quit.addActionListener(this);// 为添加行为事件监听
			jFrame.addWindowListener(new WindowAdapter() {// 添加窗口时间监听
						@Override
						public void windowClosing(WindowEvent e) {
							System.exit(0);
						}
					});
			jFrame.setDefaultCloseOperation(1);
			jFrame.getRootPane().setDefaultButton(login);
		}
		return jFrame;// 返回jFrame,此时上面的以添加好
	}

	/**
	 * 此方法初始化jContentPane
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			topass = new JLabel();
			topass.setBounds(new Rectangle(36, 67, 80, 25));// Rectangle指定了坐标空间中的一个区域
			topass.setText("用户密码：");
			toID = new JLabel();
			toID.setBounds(new Rectangle(36, 28, 80, 25));
			toID.setText("用户ID号：");
			jContentPane = new JPanel();
			// jContentPane.setBackground(new Color(217,242,242));
			jContentPane.setLayout(null);
			jContentPane.add(toID);
			jContentPane.add(getJTextField());
			jContentPane.add(topass);
			jContentPane.add(getJPasswordField());
			jContentPane.add(getJButton());
			// jContentPane.add(getJButton1());
			jContentPane.add(getJButton2());
			jContentPane.add(getIP());
		}
		return jContentPane;
	}

	/**
	 * 设置登录IP
	 */
	public JLabel getIP() {
		if (IP == null) {
			IP = new JLabel();
			IP.setBounds(134, 150, 73, 18);
			try {
				IP.setText(getLocalHost().getHostAddress());
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
			IP.setVisible(false);
		}
		return IP;
	}

	/**
	 * 此方法初始化 jTextField
	 */
	public JTextField getJTextField() {
		if (ID == null) {
			ID = new JTextField();
			ID.setBorder(new LineBorder(Color.black, 1, false));
			ID.setBounds(new Rectangle(112, 30, 130, 21));
		}
		return ID;
	}

	/**
	 * 此方法初始化jTextField1
	 */
	public JPasswordField getJPasswordField() {
		if (PasswordField == null) {
			PasswordField = new JPasswordField();
			PasswordField.setBorder(new LineBorder(Color.black, 1, false));
			PasswordField.setBounds(new Rectangle(112, 69, 130, 21));
		}
		return PasswordField;
	}

	/**
	 * 此方法初始化jButton
	 */
	private JButton getJButton() {
		if (login == null) {
			login = new JButton();
			login.setFocusPainted(false);
			login.setBounds(new Rectangle(40, 110, 60, 24));
			login.setText("登录");
		}
		return login;
	}

	/**
	 * 此方法初始化jButton1
	 */
	// private JButton getJButton1()
	// {
	// if (quit == null)
	// {
	// quit = new JButton();
	// quit.setFocusPainted(false);
	// quit.setBounds(new Rectangle(80, 110, 60, 24));
	// quit.setText("取消");
	// }
	// return quit;
	// }

	/**
	 * 此方法初始化jButton2
	 */
	public JButton getJButton2() {
		if (regedit == null) {
			regedit = new JButton();
			regedit.setFocusPainted(false);
			regedit.setBounds(new Rectangle(180, 110, 60, 24));
			regedit.setText("注册");
		}
		return regedit;
	}

	/**
	 * 实现actionListener接口所需实现的方法
	 */
	@SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent e) {// jButtonjButton1jButton2事件处理
		/**
		 * 按注册按钮后弹出用户注册界面
		 */
		if (e.getSource() == regedit) {
			// jFrame.setVisible(false);
			// Client.userRegUI = new UserRegUI();//
			// Client.userRegUI.getJFrame();
			UserRegUI urui = new UserRegUI();
			urui.getJFrame();
			
		}
		/**
		 * 按登录按钮后,执行Client的login()方法
		 */
		if (e.getSource() == login) {
			User u = new User();
			u.setUserID((ID.getText().trim()));
			u.setPasswd(new String(PasswordField.getPassword()));
			
			if (new MyQqClient().checkLogin(u)) {
				// 发送一个要求返回在线好友的请求包
				try {
					// 把创建好友列表的语句提前,先不显示，等到服务器端返回了好友信息之后再显示
					ClientMainUI qqlist = new ClientMainUI(u);
					//qqlist.getjFrame();
					
					ManageClientMainUI.addQqFriendList(u.getUserID(), qqlist);
					
					
					ObjectOutputStream oos = new ObjectOutputStream(
							ManageClientConnServerThread
									.getClientConnServerThread(u.getUserID())
									.getS().getOutputStream());

					// 做一个Message
					Message m = new Message();
					m.setMsType(MessageType.message_get_onLineFriend);
					// 指明我要的是这个qq号的好友情况
					m.setSender(u.getUserID());
					oos.writeObject(m);
				} catch (Exception e2) {
					// TODO: handle exception
				}

				// 关闭登陆界面
				jFrame.setVisible(false);
			}else{
				JOptionPane.showMessageDialog(null, "用户名或密码错误！", 
						"登陆失败", JOptionPane.INFORMATION_MESSAGE);
			}
			/**
			 * 按取消按钮后，隐藏用户登录界面
			 */
			if (e.getSource() == quit) {
				jFrame.setVisible(false);
			}
		}
	}
}
