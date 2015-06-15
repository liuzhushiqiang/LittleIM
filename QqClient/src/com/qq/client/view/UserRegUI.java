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
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
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

import com.qq.client.model.ClientConnServerThread;
import com.qq.client.model.ManageClientConnServerThread;
import com.qq.client.tool.MD5Util;
import com.qq.common.Message;
import com.qq.common.MessageType;
/**
 *
 * @author
 */
public class UserRegUI implements ActionListener
{
	public static void main(String[] args){
		UserRegUI urui = new UserRegUI();
		urui.getJFrame();
	}
	
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
	 * 注册按钮
	 */
	private JButton jButton1 = null;

	/**
	 * 注册IP
	 */
	private JLabel IP = null;

	/**
	 *获取本地IP
	*/
		public static InetAddress getLocalHost() throws UnknownHostException
		{
			InetAddress LocalIP = InetAddress.getLocalHost();
			return LocalIP;
		}
	/**
	 * 此方法初始化jFrame
	 */
	public JFrame getJFrame()
	{
		if (jFrame == null)
		{
			jFrame = new JFrame();
//                        jFrame.setAlwaysOnTop(true);
			jFrame.setSize(new Dimension(353, 335));
			jFrame.setTitle("用户注册");
			Toolkit toolkit = jFrame.getToolkit();
			Dimension screen = toolkit.getScreenSize();
			jFrame.setIconImage(Toolkit.getDefaultToolkit().getImage(
					getClass().getResource("/picture/client.gif")));
			jFrame.setBounds(screen.width / 2 - 371 / 2,
					screen.height / 2 - 353 / 2, 371, 353);// 让窗体在屏幕正中央显示
			jFrame.setContentPane(getJContentPane());
			jFrame.setResizable(false);// 固定窗口大小
			jFrame.setVisible(true);
			jButton.addActionListener(this);
			jButton1.addActionListener(this);
			jFrame.setDefaultCloseOperation(1);
                        jFrame.addWindowListener(new WindowAdapter() {// 添加窗口时间监听
                @Override
						public void windowClosing(WindowEvent e)
						{
							System.exit(0);
						}
					});
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
			jLabel7 = new JLabel();
			jLabel7.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
			jLabel7.setBounds(new Rectangle(193, 119, 56, 26));
			jLabel7.setText("  星    座");
			jLabel4 = new JLabel();
			jLabel4.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
			jLabel4.setBounds(new Rectangle(193, 80, 56, 26));
			jLabel4.setText("  性    别");
			jLabel3 = new JLabel();
			jLabel3.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
			jLabel3.setBounds(new Rectangle(23, 119, 56, 24));
			jLabel3.setText("  年    龄");
			jLabel2 = new JLabel();
			jLabel2.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
			jLabel2.setBounds(new Rectangle(23, 80, 56, 24));
			jLabel2.setText("  姓    名");
			jLabel1 = new JLabel();
			jLabel1.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
			jLabel1.setBounds(new Rectangle(23, 46, 56, 24));
			jLabel1.setText("用户密码");
			jLabel = new JLabel();
			jLabel.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
			jLabel.setBounds(new Rectangle(23, 13, 56, 24));
			jLabel.setText("用户昵称");
			jContentPane = new JPanel();
//			jContentPane.setBackground(new Color(153, 153, 255));
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
			jContentPane.add(getJComboBox());
			jContentPane.add(getJComboBox3());
			jContentPane.add(getJTextField1());
			jContentPane.add(getJTextField2());
			jContentPane.add(getJTextArea());
			jContentPane.add(getJButton());
			jContentPane.add(getJButton1());
			jContentPane.add(getIP());
		}
		return jContentPane;
	}

	/**
	 * 设置注册IP
	 */
	public JLabel getIP()
	{
		if(IP == null)
			IP = new JLabel();
		IP.setBounds(136, 275, 78, 18);
		IP.setVisible(false);
		try
		{
			IP.setText(getLocalHost().getHostAddress());
		} catch (UnknownHostException e)
		{
			e.printStackTrace();
		}
		return IP;
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
			jTextField.setBounds(new Rectangle(87, 14, 149, 24));
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
			jPasswordField.setBounds(new Rectangle(85, 47, 151, 24));
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
			jComboBox.setBounds(new Rectangle(255, 81, 63, 24));
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
			String[] xingzuo = { "双鱼座", "水瓶座", "白羊座", "金牛座", "双子座", "巨蟹座",
					"狮子座", "处女座", "天称座", "天蝎座", "射手座", "魔羯座" };
			jComboBox3 = new JComboBox(xingzuo);
			jComboBox3.setSelectedIndex(0);
			jComboBox3.setBounds(new Rectangle(255, 120, 78, 24));
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
			jTextField1.setBounds(new Rectangle(86, 81, 78, 24));
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
			jTextField2.setBounds(new Rectangle(85, 120, 79, 24));
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
			jTextArea.setBounds(new Rectangle(23, 166, 310, 100));
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
			jButton.setBounds(new Rectangle(220, 272, 60, 25));
			jButton.setText("注册");
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
			jButton1.setBounds(new Rectangle(60, 272, 60, 25));
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
//			this.jFrame.setVisible(false);
//			Client.loginui.getJFrame().setVisible(true);
			jFrame.dispose();
		}
		/**
		 * 按注册按钮
		 */
		if (e.getSource() == jButton)
		{
			//Client.client.regUser();
			Message m = new Message();
			
			m.setContent(jTextField.getText() + "_" + MD5Util.MD5(jPasswordField.getText()) + "_" +
					jTextField1.getText() + "_" + jComboBox.getSelectedItem().toString() + "_" +
					jTextField2.getText() + "_" + jComboBox3.getSelectedItem().toString() + "_" +
					jTextArea.getText());
			
			m.setMsType(MessageType.message_get_reg);
			try {
				Socket s = new Socket("127.0.0.1", 9999);
				ObjectOutputStream oos = new ObjectOutputStream
				(s.getOutputStream());
				oos.writeObject(m);
				
				ObjectInputStream ois= new ObjectInputStream(s.getInputStream());
				try {
					Message m1 = (Message)ois.readObject();
					if(!m1.getContent().equals("0")){
						JOptionPane.showMessageDialog(null, "您注册的ID号是" 
								+ m1.getContent() + ",这将作为您的登陆账号，请妥善保管。", 
								"注册成功", JOptionPane.INFORMATION_MESSAGE);
					}else{
						JOptionPane.showMessageDialog(null, "注册失败", 
								"注册失败", JOptionPane.INFORMATION_MESSAGE);
					}
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}