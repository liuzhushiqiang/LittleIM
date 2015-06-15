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

	/**
	 * ����"�û�����"
	 */
	private JLabel jLabel1 = null;

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

	/**
	 * �û����������
	 */
	private JPasswordField jPasswordField = null;

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
	private JButton jButton = null;

	/**
	 * ע�ᰴť
	 */
	private JButton jButton1 = null;

	/**
	 * ע��IP
	 */
	private JLabel IP = null;

	/**
	 *��ȡ����IP
	*/
		public static InetAddress getLocalHost() throws UnknownHostException
		{
			InetAddress LocalIP = InetAddress.getLocalHost();
			return LocalIP;
		}
	/**
	 * �˷�����ʼ��jFrame
	 */
	public JFrame getJFrame()
	{
		if (jFrame == null)
		{
			jFrame = new JFrame();
//                        jFrame.setAlwaysOnTop(true);
			jFrame.setSize(new Dimension(353, 335));
			jFrame.setTitle("�û�ע��");
			Toolkit toolkit = jFrame.getToolkit();
			Dimension screen = toolkit.getScreenSize();
			jFrame.setIconImage(Toolkit.getDefaultToolkit().getImage(
					getClass().getResource("/picture/client.gif")));
			jFrame.setBounds(screen.width / 2 - 371 / 2,
					screen.height / 2 - 353 / 2, 371, 353);// �ô�������Ļ��������ʾ
			jFrame.setContentPane(getJContentPane());
			jFrame.setResizable(false);// �̶����ڴ�С
			jFrame.setVisible(true);
			jButton.addActionListener(this);
			jButton1.addActionListener(this);
			jFrame.setDefaultCloseOperation(1);
                        jFrame.addWindowListener(new WindowAdapter() {// ��Ӵ���ʱ�����
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
	 * �˷�����ʼ��jContentPane
	 */
	private JPanel getJContentPane()
	{
		if (jContentPane == null)
		{
			jLabel7 = new JLabel();
			jLabel7.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
			jLabel7.setBounds(new Rectangle(193, 119, 56, 26));
			jLabel7.setText("  ��    ��");
			jLabel4 = new JLabel();
			jLabel4.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
			jLabel4.setBounds(new Rectangle(193, 80, 56, 26));
			jLabel4.setText("  ��    ��");
			jLabel3 = new JLabel();
			jLabel3.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
			jLabel3.setBounds(new Rectangle(23, 119, 56, 24));
			jLabel3.setText("  ��    ��");
			jLabel2 = new JLabel();
			jLabel2.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
			jLabel2.setBounds(new Rectangle(23, 80, 56, 24));
			jLabel2.setText("  ��    ��");
			jLabel1 = new JLabel();
			jLabel1.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
			jLabel1.setBounds(new Rectangle(23, 46, 56, 24));
			jLabel1.setText("�û�����");
			jLabel = new JLabel();
			jLabel.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
			jLabel.setBounds(new Rectangle(23, 13, 56, 24));
			jLabel.setText("�û��ǳ�");
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
	 * ����ע��IP
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
	 * �˷�����ʼ�� jTextField
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
	 * �˷�����ʼ��jPasswordField
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
	 * �˷�����ʼ��jComboBox
	 */
	public JComboBox getJComboBox()
	{
		if (jComboBox == null)
		{
			String[] sex = { "��", "Ů" };
			jComboBox = new JComboBox(sex);
			jComboBox.setSelectedIndex(0);
			jComboBox.setBounds(new Rectangle(255, 81, 63, 24));
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
			String[] xingzuo = { "˫����", "ˮƿ��", "������", "��ţ��", "˫����", "��з��",
					"ʨ����", "��Ů��", "�����", "��Ы��", "������", "ħ����" };
			jComboBox3 = new JComboBox(xingzuo);
			jComboBox3.setSelectedIndex(0);
			jComboBox3.setBounds(new Rectangle(255, 120, 78, 24));
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
			jTextField1.setBounds(new Rectangle(86, 81, 78, 24));
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
			jTextField2.setBounds(new Rectangle(85, 120, 79, 24));
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
			jTextArea.setBorder(new TitledBorder(null, "����˵��", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
			jTextArea.setText("");
			jTextArea.setBounds(new Rectangle(23, 166, 310, 100));
		}
		return jTextArea;
	}

	/**
	 * �˷�����ʼ��jButton
	 */
	public JButton getJButton()
	{
		if (jButton == null)
		{
			jButton = new JButton();
			jButton.setBounds(new Rectangle(220, 272, 60, 25));
			jButton.setText("ע��");
		}
		return jButton;
	}

	/**
	 * �˷�����ʼ��jButton1
	 */
	public JButton getJButton1()
	{
		if (jButton1 == null)
		{
			jButton1 = new JButton();
			jButton1.setBounds(new Rectangle(60, 272, 60, 25));
			jButton1.setText("����");
		}
		return jButton1;
	}

	/**
	 * ʵ��ActionListener����ʵ�ֵķ���
	 */
	public void actionPerformed(ActionEvent e)
	{
		/**
		 * ������
		 */
		if (e.getSource() == jButton1)
		{
//			this.jFrame.setVisible(false);
//			Client.loginui.getJFrame().setVisible(true);
			jFrame.dispose();
		}
		/**
		 * ��ע�ᰴť
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
						JOptionPane.showMessageDialog(null, "��ע���ID����" 
								+ m1.getContent() + ",�⽫��Ϊ���ĵ�½�˺ţ������Ʊ��ܡ�", 
								"ע��ɹ�", JOptionPane.INFORMATION_MESSAGE);
					}else{
						JOptionPane.showMessageDialog(null, "ע��ʧ��", 
								"ע��ʧ��", JOptionPane.INFORMATION_MESSAGE);
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