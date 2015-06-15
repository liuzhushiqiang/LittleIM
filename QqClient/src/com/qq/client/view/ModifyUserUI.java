package com.qq.client.view;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import com.qq.common.Message;
import com.qq.common.MessageType;
import com.qq.common.User;
/**
 *
 * @author
 */
public class ModifyUserUI implements ActionListener
{
	
	public static void main(String[] args){
		ModifyUserUI mui = new ModifyUserUI("1000");
		mui.getJFrame();
	}
	
	public ModifyUserUI(String userid){
		this.userid = userid;
	}
	
	private String userid;
	
	/**
	 * 窗体
	 */
	private JFrame jFrame = null;

	/**
	 * 添加生成后的所有组件
	 */
	private JPanel jContentPane = null;

	/**
	 * 放置"用户昵称"
	 */
	private JLabel jLabel = null;

	/**
	 * 放置"用户密码"
	 */
	private JLabel jLabel1 = null;

	/**
	 * 放置"姓名"
	 */
	private JLabel jLabel2 = null;

	/**
	 * 放置"性别"
	 */
	private JLabel jLabel3 = null;

	/**
	 * 放置"年龄"
	 */
	private JLabel jLabel4 = null;
	/**
	 * 放置"星座"
	 */
	private JLabel jLabel7 = null;


	/**
	 * 用户昵称输入框
	 */
	private JTextField jTextField = null;

	/**
	 * 用户密码输入框
	 */
	private JPasswordField jPasswordField = null;

	/**
	 * 用户性别选择
	 */
	private JComboBox jComboBox = null;
	/**
	 * 用户星座选择
	 */
	private JComboBox jComboBox3 = null;

	/**
	 * 用户姓名输入框
	 */
	private JTextField jTextField1 = null;

	/**
	 * 用户年龄输入框
	 */
	private JTextField jTextField2 = null;
	/**
	 * 个人说明输入文本域
	 */
	private JTextArea jTextArea = null;

	/**
	 * 取消按钮
	 */
	private JButton jButton = null;

	/**
	 * 修改按钮
	 */
	private JButton jButton1 = null;

	/**
	 * 显示号码
	 */
	public JLabel jLabel9 = null;// QQ

	/**
	 * 显示具体的号码
	 */
	public JLabel jLabel10 = null;// 号码

	/**
	 * 此方法初始化jFrame
	*/
	public JFrame getJFrame()
	{
		if (jFrame == null)
		{
			jFrame = new JFrame();
			jFrame.setSize(new Dimension(353, 335));
			jFrame.setTitle("修改信息");
			Toolkit toolkit = jFrame.getToolkit();
			Dimension screen = toolkit.getScreenSize();
			//jFrame.setIconImage(Toolkit.getDefaultToolkit().getImage(
			//		getClass().getResource("/iamge/")));
			jFrame.setBounds(screen.width / 2 - 371 / 2,
					screen.height / 2 - 353 / 2, 371, 353);// 让窗体在屏幕正中央显示
			jFrame.setContentPane(getJContentPane());
			jFrame.setResizable(false);// 固定窗口大小
			jFrame.setVisible(true);
			jButton.addActionListener(this);
			jButton1.addActionListener(this);
			jFrame.setDefaultCloseOperation(1);
		}
		return jFrame;
	}
	/**
	 * 此方法初始化jContentPane
	 */
	private JPanel getJContentPane()
	{
		if (jContentPane == null)
		{
			jLabel9 = new JLabel("  用户号");
//			jLabel9.setFont(new java.awt.Font("Dialog", Font.PLAIN, 13));
                        jLabel9.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
			jLabel9.setBounds(new Rectangle(194, 46, 56, 24));
//			jLabel10 = new JLabel();
//			jLabel10.setFont(new java.awt.Font("Dialog", Font.PLAIN, 26));
//			jLabel10.setForeground(Color.red);
//			jLabel10.setBorder(new LineBorder(Color.black, 1, false));
//			jLabel10.setBounds(new Rectangle(255, 46, 80, 24));
			jLabel7 = new JLabel();
			jLabel7.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
			jLabel7.setBounds(new Rectangle(194, 127, 56, 24));
			jLabel7.setText("  星    座");
			jLabel4 = new JLabel();
			jLabel4.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
			jLabel4.setBounds(new Rectangle(194, 85, 56, 24));
			jLabel4.setText("  性    别");
			jLabel3 = new JLabel();
			jLabel3.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
			jLabel3.setBounds(new Rectangle(23, 127, 56, 24));
			jLabel3.setText("  年    龄");
			jLabel2 = new JLabel();
			jLabel2.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
			jLabel2.setBounds(new Rectangle(23, 85, 56, 24));
			jLabel2.setText("  姓    名");
			jLabel1 = new JLabel();
			jLabel1.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
			jLabel1.setBounds(new Rectangle(23, 46, 56, 24));
			jLabel1.setText("用户密码");
			jLabel = new JLabel();
			jLabel.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
			jLabel.setBounds(new Rectangle(23, 13, 58, 24));
			jLabel.setText("用户昵称");
			jContentPane = new JPanel();
			jContentPane.setBackground(new Color(217,242,242));
			jContentPane.setLayout(null);
			jContentPane.setSize(new Dimension(344, 303));
			jContentPane.add(jLabel);
			jContentPane.add(jLabel1);
			jContentPane.add(jLabel2);
			jContentPane.add(jLabel3);
			jContentPane.add(getJTextField());
			jContentPane.add(getJPasswordField());
			jContentPane.add(jLabel4);
			jContentPane.add(jLabel7);
			jContentPane.add(jLabel9);
			jContentPane.add(jLabel10);
			jContentPane.add(getJComboBox());
			jContentPane.add(getJComboBox3());
			jContentPane.add(getJTextField1());
			jContentPane.add(getJTextField2());
			jContentPane.add(getJTextArea());
			jContentPane.add(getJButton(), null);
			jContentPane.add(getJButton1());
		}
		return jContentPane;
	}

	/**
	 * 此方法初始化 jTextField
	 */
	public JTextField getJTextField()
	{
		if (jTextField == null)
		{
			jTextField = new JTextField();
			jTextField.setBorder(new LineBorder(Color.black, 1, false));
			jTextField.setBounds(new Rectangle(85, 13, 105, 24));
		}
		return jTextField;
	}
	/**
	 * 此方法初始化jPasswordField
	 */
	public JPasswordField getJPasswordField()
	{
		if (jPasswordField == null)
		{
			jPasswordField = new JPasswordField();
			jPasswordField.setBorder(new LineBorder(Color.black, 1, false));
			jPasswordField.setBounds(new Rectangle(85, 46, 105, 24));
		}
		return jPasswordField;
	}
	/**
	 * 此方法初始化jComboBox
	 */
	public JComboBox getJComboBox()
	{
		if (jComboBox == null)
		{
			String[] sex = { "男", "女" };
			jComboBox = new JComboBox(sex);
			jComboBox.setSelectedIndex(0);
			jComboBox.setBounds(new Rectangle(258, 84, 80, 24));
		}
		return jComboBox;
	}
	/**
	 * 此方法初始化jComboBox3
	 */
	public JComboBox getJComboBox3()
	{
		if (jComboBox3 == null)
		{
			String[] xingzuo = { "射手座","水瓶座", "双鱼座", "白羊座", "金牛座", "双子座", "巨蟹座",
					"狮子座", "处女座", "天称座", "天蝎座", "魔蝎座" };
			jComboBox3 = new JComboBox(xingzuo);
			jComboBox3.setSelectedIndex(0);
			jComboBox3.setBounds(new Rectangle(256, 127, 80, 24));
		}
		return jComboBox3;
	}

	/**
	 * 此方法初始化jTextField1
	 */
	public JTextField getJTextField1()
	{
		if (jTextField1 == null)
		{
			jTextField1 = new JTextField();
			jTextField1.setBorder(new LineBorder(Color.black, 1, false));
			jTextField1.setBounds(new Rectangle(86, 85, 105, 24));
		}
		return jTextField1;
	}

	/**
	 * 此方法初始化jTextField2
	 */
	public JTextField getJTextField2()
	{
		if (jTextField2 == null)
		{
			jTextField2 = new JTextField();
			jTextField2.setBorder(new LineBorder(Color.black, 1, false));
			jTextField2.setBounds(new Rectangle(85, 127, 105, 24));
		}
		return jTextField2;
	}

	/**
	 * 此方法初始化jTextArea
	 */
	public JTextArea getJTextArea()
	{
		if (jTextArea == null)
		{
			jTextArea = new JTextArea();
			jTextArea.setLineWrap(true);
			jTextArea.setRows(3);
			jTextArea.setBorder(new TitledBorder(null, "个人说明", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
			jTextArea.setText("");
			jTextArea.setBounds(new Rectangle(23, 180, 310, 80));
		}
		return jTextArea;
	}

	/**
	 * 此方法初始化jButton
	 */
	public JButton getJButton()
	{
		if (jButton == null)
		{
			jButton = new JButton();
                        jButton.setFocusPainted(true);
			jButton.setBounds(new Rectangle(239, 272, 60, 25));
			jButton.setText("修改");
		}
		return jButton;
	}

	/**
	 * 此方法初始化jButton1
	 */
	public JButton getJButton1()
	{
		if (jButton1 == null)
		{
			jButton1 = new JButton();
                        jButton1.setFocusPainted(true);
			jButton1.setBounds(new Rectangle(81, 272, 60, 25));
			jButton1.setText("返回");
		}
		return jButton1;
	}

	/**
	 * 实现ActionListener所需实现的方法
	 */
	public void actionPerformed(ActionEvent e)
	{
		/**
		 * 按返回
		 */
		if (e.getSource() == jButton1)
		{
			// jFrame.setVisible(false);
			jFrame.dispose();
		}
		/**
		 * 按修改
		 */
		if (e.getSource() == jButton)
		{
			//Client.client.ModifyUserInfoOrder();
			Socket s = null;
			try {
				s = new Socket("127.0.0.1", 9999);
			} catch (UnknownHostException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			User u = new User();
			u.setNickname(jTextField.getText());
			u.setPasswd(jPasswordField.getText());
			u.setUsername(jTextField1.getText());
			u.setAge(jTextField2.getText());
			u.setSignatrue(jTextArea.getText());
			System.out.println(jComboBox.getSelectedItem().toString() + 
					jComboBox3.getSelectedItem().toString() 
					+ " in ModifyUserUI.java");
			u.setGender(jComboBox.getSelectedItem().toString());
			u.setConstellation(jComboBox3.getSelectedItem().toString());
			u.setIsonline("1");
			u.setUserID(userid);
			try {
				ObjectOutputStream oos = 
					new ObjectOutputStream(s.getOutputStream());
				oos.writeObject(u);
				//System.out.println(userid + " in FriendInfoUI.java");
				ObjectInputStream ois = 
					new ObjectInputStream(s.getInputStream());
				Message m = (Message)ois.readObject();
				if(m.getContent().equals("ok")){
					JOptionPane.showMessageDialog(null, "您的个人信息修改成功.", 
							"修改成功", JOptionPane.INFORMATION_MESSAGE);
				}else if(m.getContent().equals("error")){
					JOptionPane.showMessageDialog(null, "您的个人信息修改失败.", 
							"修改失败", JOptionPane.INFORMATION_MESSAGE);
				}
					
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	public void getUserInfo(String userid){
		//这里向服务器发送消息，获取好友的信息
		Socket s = null;
		try {
			s = new Socket("127.0.0.1", 9999);
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Message m = new Message();
		m.setMsType(MessageType.message_get_userinfo);
		m.setSender(userid);
		
		try {
			ObjectOutputStream oos = 
				new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(m);
			//System.out.println(userid + " in FriendInfoUI.java");
			ObjectInputStream ois = 
				new ObjectInputStream(s.getInputStream());
				User u = (User)ois.readObject();
				jTextField = getJTextField();
				jTextField.setText(u.getNickname());
				jTextField1 = getJTextField1();
				jTextField1.setText(u.getUsername());
				jTextField2 = getJTextField2();
				jTextField2.setText(u.getAge());
				jTextArea = getJTextArea();
				jTextArea.setText(u.getSignatrue());
				jLabel10 = new JLabel();
				jLabel10.setFont(new java.awt.Font("Dialog", Font.PLAIN, 26));
				jLabel10.setForeground(Color.red);
				jLabel10.setBorder(new LineBorder(Color.black, 1, false));
				jLabel10.setBounds(new Rectangle(255, 46, 80, 24));
				jLabel10.setText(u.getUserID());
				jPasswordField = getJPasswordField();
				jPasswordField.setText(u.getPasswd());
				System.out.println(u.getGender() + u.getConstellation() 
						+ " in FriendInfoUI.java");
				
				String[] gender = {"男", "女"};
				int j;
				for(j = 0; j < 2; j++){
					if(gender[j].equals(u.getGender())){
						break;
					}
				}
				jComboBox = getJComboBox();					
				jComboBox.setSelectedIndex(j);
				String[] constellation = {"射手座","水瓶座", "双鱼座", "白羊座", 
						"金牛座", "双子座", "巨蟹座",
						"狮子座", "处女座", "天称座", "天蝎座", "魔蝎座"};
				int i;
				for(i = 0; i < 12; i++){
					if(constellation[i].equals(u.getConstellation())){
						break;
					}
				}
				//System.out.println(i);
				jComboBox3 = getJComboBox3();
				jComboBox3.setSelectedIndex(i);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
