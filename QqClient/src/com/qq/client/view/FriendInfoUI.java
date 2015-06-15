/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.qq.client.view;

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
import java.lang.reflect.Array;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import com.qq.client.model.ClientConnServerThread;
import com.qq.client.model.ManageClientConnServerThread;
import com.qq.common.Message;
import com.qq.common.MessageType;
import com.qq.common.User;
/**
 *
 * 
 */
public class FriendInfoUI{
	public static void main(String[] args){
		FriendInfoUI fiui = new FriendInfoUI("1000", "1001");
		fiui.getJFrame();
	}
	
	public FriendInfoUI(String userid, String friendid){
		this.userid = userid;
		this.friendid = friendid;
	}
	
	private String userid;
	private String friendid;
	/**
	 * ����
	 */
	private JFrame jFrame = null;

	/**
	 * ������ɺ���������
	 */
	private JPanel jContentPane = null;

	/**
	 * ����"�û��ǳ�"
	 */
	private JLabel jLabel = null;

//	/**
//	 * ����"�û�����"
//	 */
//	private JLabel jLabel1 = null;

	/**
	 * ����"����"
	 */
	private JLabel jLabel2 = null;

	/**
	 * ����"�Ա�"
	 */
	private JLabel jLabel3 = null;

	/**
	 * ����"����"
	 */
	private JLabel jLabel4 = null;
	/**
	 * ����"����"
	 */
	private JLabel jLabel7 = null;


	/**
	 * �û��ǳ������
	 */
	private JTextField jTextField = null;

//	/**
//	 * �û����������
//	 */
//	private JPasswordField jPasswordField = null;

	/**
	 * �û��Ա�ѡ��
	 */
	private JComboBox jComboBox = null;
	/**
	 * �û�����ѡ��
	 */
	private JComboBox jComboBox3 = null;

	/**
	 * �û����������
	 */
	private JTextField jTextField1 = null;

	/**
	 * �û����������
	 */
	private JTextField jTextField2 = null;
	/**
	 * ����˵�������ı���
	 */
	private JTextArea jTextArea = null;

	/**
	 * ȡ����ť
	 */
	//private JButton jButton = null;

	/**
	 * �޸İ�ť
	 */
	//private JButton jButton1 = null;

	/**
	 * ��ʾ����
	 */
	public JLabel jLabel9 = null;// QQ

	/**
	 * ��ʾ����ĺ���
	 */
	public JLabel jLabel10 = null;// ����

	/**
	 * �˷�����ʼ��jFrame
	*/
	public JFrame getJFrame()
	{
		if (jFrame == null)
		{
			
			jFrame = new JFrame();
			jFrame.setSize(new Dimension(353, 335));
			jFrame.setTitle("������Ϣ");
			Toolkit toolkit = jFrame.getToolkit();
			Dimension screen = toolkit.getScreenSize();
			jFrame.setIconImage(Toolkit.getDefaultToolkit().getImage(
					getClass().getResource("/picture/client.gif")));
			jFrame.setBounds(screen.width / 2 - 371 / 2,
					screen.height / 2 - 353 / 2, 371, 353);// �ô�������Ļ��������ʾ
			jFrame.setContentPane(getJContentPane());
			jFrame.setResizable(false);// �̶����ڴ�С
			jFrame.setVisible(true);
			//jButton.addActionListener(this);
			//jButton1.addActionListener(this);
			jFrame.setDefaultCloseOperation(1);
		}
		return jFrame;
	}
	/**
	 * �˷�����ʼ��jContentPane
	 */
	private JPanel getJContentPane()
	{
		if (jContentPane == null)
		{
			jLabel9 = new JLabel("  �û���");
//			jLabel9.setFont(new java.awt.Font("Dialog", Font.PLAIN, 13));
                        jLabel9.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
			jLabel9.setBounds(new Rectangle(194, 46, 56, 24));
			jLabel10 = new JLabel();
			jLabel10.setFont(new java.awt.Font("Dialog", Font.PLAIN, 26));
			jLabel10.setForeground(Color.red);
			jLabel10.setBorder(new LineBorder(Color.black, 1, false));
			jLabel10.setBounds(new Rectangle(255, 46, 80, 24));
			jLabel7 = new JLabel();
			jLabel7.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
			jLabel7.setBounds(new Rectangle(194, 127, 56, 24));
			jLabel7.setText("  ��    ��");
			jLabel4 = new JLabel();
			jLabel4.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
			jLabel4.setBounds(new Rectangle(194, 85, 56, 24));
			jLabel4.setText("  ��    ��");
			jLabel3 = new JLabel();
			jLabel3.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
			jLabel3.setBounds(new Rectangle(23, 127, 56, 24));
			jLabel3.setText("  ��    ��");
			jLabel2 = new JLabel();
			jLabel2.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
			jLabel2.setBounds(new Rectangle(23, 85, 56, 24));
			jLabel2.setText("  ��    ��");
			//jLabel1 = new JLabel();
			//jLabel1.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
			//jLabel1.setBounds(new Rectangle(23, 46, 56, 24));
			//jLabel1.setText("�û�����");
			jLabel = new JLabel();
			jLabel.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
			jLabel.setBounds(new Rectangle(23, 13, 58, 24));
			jLabel.setText("�û��ǳ�");
			
			//�����������������Ϣ����ȡ���ѵ���Ϣ
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
			m.setSender(friendid);
			
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
					jLabel10.setText(u.getUserID());
					System.out.println(u.getGender() + u.getConstellation() 
							+ " in FriendInfoUI.java");
					
					String[] gender = {"��", "Ů"};
					int j;
					for(j = 0; j < 2; j++){
						if(gender[j].equals(u.getGender())){
							break;
						}
					}
					jComboBox = getJComboBox();					
					jComboBox.setSelectedIndex(j);
					String[] constellation = {"������","ˮƿ��", "˫����", "������", 
							"��ţ��", "˫����", "��з��",
							"ʨ����", "��Ů��", "�����", "��Ы��", "ħЫ��"};
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
			
			jContentPane = new JPanel();
			jContentPane.setBackground(new Color(217,242,242));
			jContentPane.setLayout(null);
			jContentPane.setSize(new Dimension(344, 303));
			jContentPane.add(jLabel);
			//jContentPane.add(jLabel1);
			jContentPane.add(jLabel2);
			jContentPane.add(jLabel3);
			jContentPane.add(getJTextField());
			//jContentPane.add(getJPasswordField());
			jContentPane.add(jLabel4);
			jContentPane.add(jLabel7);
			jContentPane.add(jLabel9);
			jContentPane.add(jLabel10);
			jContentPane.add(getJComboBox());
			jContentPane.add(getJComboBox3());
			jContentPane.add(getJTextField1());
			jContentPane.add(getJTextField2());
			jContentPane.add(getJTextArea());
			//jContentPane.add(getJButton(), null);
			//jContentPane.add(getJButton1());
		}
		return jContentPane;
	}

	/**
	 * �˷�����ʼ�� jTextField
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
//	/**
//	 * �˷�����ʼ��jPasswordField
//	 */
//	public JPasswordField getJPasswordField()
//	{
//		if (jPasswordField == null)
//		{
//			jPasswordField = new JPasswordField();
//			jPasswordField.setBorder(new LineBorder(Color.black, 1, false));
//			jPasswordField.setBounds(new Rectangle(85, 46, 105, 24));
//		}
//		return jPasswordField;
//	}
	/**
	 * �˷�����ʼ��jComboBox
	 */
	public JComboBox getJComboBox()
	{
		if (jComboBox == null)
		{
			String[] sex = { "��", "Ů" };
			jComboBox = new JComboBox(sex);
			jComboBox.setSelectedIndex(0);
			jComboBox.setBounds(new Rectangle(258, 84, 80, 24));
		}
		return jComboBox;
	}
	/**
	 * �˷�����ʼ��jComboBox3
	 */
	public JComboBox getJComboBox3()
	{
		if (jComboBox3 == null)
		{
			String[] xingzuo = { "������","ˮƿ��", "˫����", "������", 
					"��ţ��", "˫����", "��з��",
					"ʨ����", "��Ů��", "�����", "��Ы��", "ħЫ��" };
			jComboBox3 = new JComboBox(xingzuo);
			jComboBox3.setSelectedIndex(0);
			jComboBox3.setBounds(new Rectangle(256, 127, 80, 24));
		}
		return jComboBox3;
	}

	/**
	 * �˷�����ʼ��jTextField1
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
	 * �˷�����ʼ��jTextField2
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
	 * �˷�����ʼ��jTextArea
	 */
	public JTextArea getJTextArea()
	{
		if (jTextArea == null)
		{
			jTextArea = new JTextArea();
			jTextArea.setLineWrap(true);
			jTextArea.setRows(3);
			jTextArea.setBorder(new TitledBorder(null, "����˵��", 
					TitledBorder.DEFAULT_JUSTIFICATION, 
					TitledBorder.DEFAULT_POSITION, null, null));
			jTextArea.setText("");
			jTextArea.setBounds(new Rectangle(23, 180, 310, 80));
		}
		return jTextArea;
	}
}
