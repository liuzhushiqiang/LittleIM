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
	 * ����
	 */
	private JFrame jFrame = null;

	/**
	 * ���������������
	 */
	private JPanel jContentPane = null;

	/**
	 * �����ַ�������
	 */
	private JLabel toID = null;

	/**
	 * ���������
	 */
	private JTextField ID = null;

	/**
	 * �����ַ�������
	 */
	private JLabel topass = null;

	/**
	 * ���������
	 */
	public JPasswordField PasswordField = null;

	/**
	 * ��¼��ť
	 */
	private JButton login = null;

	/**
	 * ȡ����ť
	 */
	private JButton quit = null;

	/**
	 * ע�ᰴť
	 */
	private JButton regedit = null;

	/**
	 * ��¼IP
	 */
	private JLabel IP = null;

	/**
	 *��ȡ����IP
	 */
	public static InetAddress getLocalHost() throws UnknownHostException {
		InetAddress LocalIP = InetAddress.getLocalHost();
		return LocalIP;
	}

	/**
	 * �˷�����ʼ��jFrame
	 */
	public JFrame getJFrame() {
		if (jFrame == null) {
			jFrame = new JFrame();// ʵ����jFrame
			// jFrame.setAlwaysOnTop(true);
			jFrame.setSize(265, 140);// ��װjFrame����Ŀ�Ⱥ͸߶�
			jFrame.setTitle("�û���¼");// ���ô������
			Toolkit toolkit = jFrame.getToolkit();// Tookit��AWT����ʵ��ʵ�ֵĳ�����
			Dimension screen = toolkit.getScreenSize();// �õ���Ļ�Ĵ�С
			jFrame.setIconImage(Toolkit.getDefaultToolkit().getImage(
					getClass().getResource("/picture/client.gif")));// ���ô���ͼ��
			jFrame.setBounds(screen.width / 2 - 265 / 2,
					screen.height / 2 - 174 / 2, 318, 206);// //�ô�������Ļ��������ʾ
			jFrame.setContentPane(getJContentPane());// �Ѵ���������������ΪjContentPane
			jFrame.setResizable(false);// //�̶����ڴ�С
			jFrame.setVisible(true);// ���ô���Ϊ�ɼ�
			regedit.addActionListener(this);// Ϊ�����Ϊ�¼�����
			login.addActionListener(this);// Ϊ�����Ϊ�¼�����
			// quit.addActionListener(this);// Ϊ�����Ϊ�¼�����
			jFrame.addWindowListener(new WindowAdapter() {// ��Ӵ���ʱ�����
						@Override
						public void windowClosing(WindowEvent e) {
							System.exit(0);
						}
					});
			jFrame.setDefaultCloseOperation(1);
			jFrame.getRootPane().setDefaultButton(login);
		}
		return jFrame;// ����jFrame,��ʱ���������Ӻ�
	}

	/**
	 * �˷�����ʼ��jContentPane
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			topass = new JLabel();
			topass.setBounds(new Rectangle(36, 67, 80, 25));// Rectangleָ��������ռ��е�һ������
			topass.setText("�û����룺");
			toID = new JLabel();
			toID.setBounds(new Rectangle(36, 28, 80, 25));
			toID.setText("�û�ID�ţ�");
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
	 * ���õ�¼IP
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
	 * �˷�����ʼ�� jTextField
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
	 * �˷�����ʼ��jTextField1
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
	 * �˷�����ʼ��jButton
	 */
	private JButton getJButton() {
		if (login == null) {
			login = new JButton();
			login.setFocusPainted(false);
			login.setBounds(new Rectangle(40, 110, 60, 24));
			login.setText("��¼");
		}
		return login;
	}

	/**
	 * �˷�����ʼ��jButton1
	 */
	// private JButton getJButton1()
	// {
	// if (quit == null)
	// {
	// quit = new JButton();
	// quit.setFocusPainted(false);
	// quit.setBounds(new Rectangle(80, 110, 60, 24));
	// quit.setText("ȡ��");
	// }
	// return quit;
	// }

	/**
	 * �˷�����ʼ��jButton2
	 */
	public JButton getJButton2() {
		if (regedit == null) {
			regedit = new JButton();
			regedit.setFocusPainted(false);
			regedit.setBounds(new Rectangle(180, 110, 60, 24));
			regedit.setText("ע��");
		}
		return regedit;
	}

	/**
	 * ʵ��actionListener�ӿ�����ʵ�ֵķ���
	 */
	@SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent e) {// jButtonjButton1jButton2�¼�����
		/**
		 * ��ע�ᰴť�󵯳��û�ע�����
		 */
		if (e.getSource() == regedit) {
			// jFrame.setVisible(false);
			// Client.userRegUI = new UserRegUI();//
			// Client.userRegUI.getJFrame();
			UserRegUI urui = new UserRegUI();
			urui.getJFrame();
			
		}
		/**
		 * ����¼��ť��,ִ��Client��login()����
		 */
		if (e.getSource() == login) {
			User u = new User();
			u.setUserID((ID.getText().trim()));
			u.setPasswd(new String(PasswordField.getPassword()));
			
			if (new MyQqClient().checkLogin(u)) {
				// ����һ��Ҫ�󷵻����ߺ��ѵ������
				try {
					// �Ѵ��������б�������ǰ,�Ȳ���ʾ���ȵ��������˷����˺�����Ϣ֮������ʾ
					ClientMainUI qqlist = new ClientMainUI(u);
					//qqlist.getjFrame();
					
					ManageClientMainUI.addQqFriendList(u.getUserID(), qqlist);
					
					
					ObjectOutputStream oos = new ObjectOutputStream(
							ManageClientConnServerThread
									.getClientConnServerThread(u.getUserID())
									.getS().getOutputStream());

					// ��һ��Message
					Message m = new Message();
					m.setMsType(MessageType.message_get_onLineFriend);
					// ָ����Ҫ�������qq�ŵĺ������
					m.setSender(u.getUserID());
					oos.writeObject(m);
				} catch (Exception e2) {
					// TODO: handle exception
				}

				// �رյ�½����
				jFrame.setVisible(false);
			}else{
				JOptionPane.showMessageDialog(null, "�û������������", 
						"��½ʧ��", JOptionPane.INFORMATION_MESSAGE);
			}
			/**
			 * ��ȡ����ť�������û���¼����
			 */
			if (e.getSource() == quit) {
				jFrame.setVisible(false);
			}
		}
	}
}
