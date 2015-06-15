package com.qq.client.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import sun.net.www.content.image.jpeg;

import com.qq.client.model.ManageChatUI;
import com.qq.client.model.ManageClientConnServerThread;
import com.qq.common.Message;
import com.qq.common.MessageType;

/**
 * 
 * 
 */
public class ChatUI extends JFrame {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ChatUI icui = new ChatUI("1000", "1001");
		icui.getJFrame();
	}

	private static final long serialVersionUID = 1453456465L;
	
	/**
	 * 查看聊天记录的label
	 */
	JLabel jl1 = null;
	
	/**
	 * 传送文件的jlabel
	 */
	JLabel jl2 = null;
	
	/**
	 * 文件
	 */
	File file;
	/**
	 * 文本域：存放聊天记录
	 */
	JTextArea jTextArea1 = null;

	/**
	 * 文本域：聊天输入
	 */
	JTextArea jTextArea2 = null;

	/**
	 * 关闭按钮
	 */
	JButton jButton1 = null;

	/**
	 * 发送按钮
	 */
	JButton jButton2 = null;
	/**
	 * 窗体
	 */
	JFrame jFrame = null;// 窗体

	/**
	 * 添加按钮等
	 */
	JPanel jPanel = null;

	/**
	 * 聊天发送者号码
	 */
	String ownerId = null;// 聊天发送者

	/**
	 * 聊天接收者号码
	 */
	String friendId = null;// 聊天目标

	/**
	 * 添加文本区
	 */
	JScrollPane jScrollPane1 = null;

	/**
	 * 添加文本区
	 */
	JScrollPane jScrollPane2 = null;

	/**
	 * 构造函数
	 */
	public ChatUI(String souceid, String destid) {
		this.ownerId = souceid;
		this.friendId = destid;
	}

	/**
	 * 此方法初始化
	 */
	public JFrame getJFrame() {
		if (jFrame == null) {
			jFrame = new JFrame();
			jFrame.setBackground(new Color(217, 242, 242));
			jFrame.setSize(460, 190);
			jFrame.setContentPane(getJPanel());
			Toolkit toolkit = jFrame.getToolkit();
			Dimension screen = toolkit.getScreenSize();
//			jFrame.setIconImage(Toolkit.getDefaultToolkit().getImage(
//					getClass().getResource("/picture/client.gif")));
			jFrame.setBounds(screen.width / 2 - 460 / 2,
					screen.height / 2 - 350 / 2, 379, 351);// //让窗体在屏幕正中央显示
			jFrame.setVisible(true);
			jFrame.setResizable(false);
			jFrame.getRootPane().setDefaultButton(jButton2);
			jFrame.setTitle(friendId);
//			/**
//			 * 响应
//			 */
//			jFrame.addWindowListener(new WindowAdapter() {
//				public void windowActivated(WindowEvent e) {
//					String name = ".//" + String.valueOf(ownerId) + ".txt";
//					PrintWriter outputStream = null;
//					try {
//						outputStream = new PrintWriter(new FileWriter(name));
//						outputStream.println(jTextArea1.getText());
//						outputStream.close();
//					} catch (IOException e1) {
//						e1.printStackTrace();
//					}
//				}
//			});
			/**
			 * 响应退出事件
			 */
			jFrame.addWindowListener(new java.awt.event.WindowAdapter() {
				@Override
				public void windowClosing(java.awt.event.WindowEvent e) {
					String name = ".//聊天记录/" + String.valueOf(ownerId + "_" + friendId) + ".txt";
					String name1 = ".//聊天记录";
					File file = new File(name);
					File file1 = new File(name1); 
					PrintWriter outputStream = null;
					try {
						if(!file1.exists()){
							file1.mkdir();
						}
						if(!file.exists()){
							file.createNewFile();
						}			
						outputStream = new PrintWriter(new FileWriter(name, true));
						outputStream.println(jTextArea1.getText());
						outputStream.close();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					
					ManageChatUI.delQqChat(ownerId + " " + friendId);
				}
			});
		}
		return jFrame;
	}

	/**
	 * 此方法初始化jPanel
	 */
	public JPanel getJPanel() {
		if (jPanel == null) {
			jPanel = new JPanel();
			jPanel.setBackground(new Color(217, 242, 242));
			jPanel.setLayout(null);
		}
		if (jTextArea1 == null) {
			jTextArea1 = new JTextArea();
			jTextArea1.setTabSize(8);
		}
		if (jTextArea2 == null) {
			jTextArea2 = new JTextArea();
			jTextArea2.setFocusCycleRoot(true);
		}
		if (jButton1 == null) {
			jButton1 = new JButton();
			// jButton1.setBackground(UIManager.getColor("Button.shadow"));
			jButton1.setFocusPainted(true);
			jButton1.setFont(new Font("", Font.PLAIN, 14));
		}
		if (jButton2 == null) {
			jButton2 = new JButton();
			jButton2.setFocusPainted(true);
		}
		if (jScrollPane1 == null) {
			jScrollPane1 = new JScrollPane();
		}
		if (jScrollPane2 == null) {
			jScrollPane2 = new JScrollPane();
		}
		if(jl1 == null){
			jl1 = new JLabel("聊天记录");
		}
		if(jl2 == null){
			jl2 = new JLabel("传送文件");
		}
		jTextArea1.setBackground(Color.white);
		jTextArea1.setFont(new java.awt.Font("Dialog", Font.PLAIN, 15));
		jTextArea1.setForeground(Color.black);
		jTextArea1.setBorder(BorderFactory.createLineBorder(new Color(217, 242,
				242)));
		jTextArea1.setLineWrap(true);
		jTextArea1.setBounds(6, 9, 100, 200);
		jTextArea1.setEditable(false);
		jScrollPane1.setBounds(new Rectangle(6, 9, 360, 180));
		jScrollPane1
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		jScrollPane1.getViewport().add(jTextArea1);// 聊天记录
		jTextArea2.setFont(new Font("宋体", Font.PLAIN, 15));
		jTextArea2.setBorder(BorderFactory.createLineBorder(new Color(217, 242,
				242)));
		jTextArea2.setLineWrap(true);
		jTextArea2.setBounds(new Rectangle(7, 207, 200, 61));
		jScrollPane2.setBounds(6, 207, 360, 60);
		jScrollPane2
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		jScrollPane2.getViewport().add(jTextArea2);
		jButton1.setBounds(new Rectangle(46, 278, 79, 24));
		jButton1.setForeground(SystemColor.menuText);
		jButton1.setText("关闭");
		jButton2.setBounds(new Rectangle(249, 278, 90, 24));
		jButton2.setFont(new Font("", Font.PLAIN, 14));
		jButton2.setForeground(SystemColor.menuText);
		jButton2.setText("发送(S)");
		jButton2.setMnemonic('S');
		
		jl1.setBounds(new Rectangle(10, 190, 60, 20));
		jl1.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e){
				String name = ".//聊天记录/" + String.valueOf(ownerId + "_" + friendId) + ".txt";
				String name1 = ".//聊天记录";
				File file = new File(name);
				File file1 = new File(name1);
				try {
					if(!file1.exists() || !file.exists()){
						JOptionPane.showMessageDialog(null, "聊天记录还未创建！", "警告", 2);
					}		
					FileInputStream fis=new FileInputStream(file);
			        byte[] buf = new byte[1024];
			        StringBuffer sb=new StringBuffer();
			        while((fis.read(buf)) != -1){
			            sb.append(new String(buf));    
			            buf=new byte[1024];//重新生成，避免和上次读取的数据重复
			        }
			        jTextArea1.setText(sb.toString());
				} catch (Exception e1) {
					e1.printStackTrace();
				}			
			}
			
			public void mouseEntered(MouseEvent e){
				jl1.setForeground(Color.blue);
			}
			
			public void mouseExited(MouseEvent e){
				jl1.setForeground(Color.black);
				
			}	
			
		});
		jl2.setBounds(new Rectangle(70, 190, 60, 20));
		jl2.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e){
				FileChooser fc = new FileChooser();
				File f = fc.openFile();
				UploadFile uf = new UploadFile(f, ownerId, friendId);
				uf.start();
//				Socket s = ManageClientConnServerThread
//				.getClientConnServerThread(ownerId).s;
//				Message m = new Message();
//				m.setSender(ownerId);
//				m.setGetter(friendId);
//				m.setMsType(MessageType.message_file_trans_request);
//				try {
//					ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
//					oos.writeObject(m);
//				} catch (Exception e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
			}
			
			public void mouseEntered(MouseEvent e){
				jl2.setForeground(Color.blue);
			}
			
			public void mouseExited(MouseEvent e){
				jl2.setForeground(Color.black);

			}
			
		});
		
		/**
		 * 发送按钮行为事件监听
		 */
		jButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == jButton2) {
					Message m = new Message();
					m.setSender(ownerId);
					m.setGetter(friendId);
					m.setContent(jTextArea2.getText());
					m.setSendTime(new Date().toString());
					m.setMsType(MessageType.message_comm_mes);
					//追加到自己的聊天窗口
					jTextArea1.append(ownerId + ":\r\n  " + jTextArea2.getText() + "\r\n");
					//清空输入窗口
					jTextArea2.setText("");
					//发送给服务器
					try {
						ObjectOutputStream oos = 
							new ObjectOutputStream
							(ManageClientConnServerThread.getClientConnServerThread
									(ownerId).getS().getOutputStream());
						oos.writeObject(m);
					} catch (Exception e2) {
						e2.printStackTrace();
						// TODO: handle exception
					}
				}
			}
		});
		/**
		 * 保存聊天记录按钮行为事件监听
		 */
		jButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == jButton1){
					String name = ".//聊天记录/" + String.valueOf(ownerId + "_" + friendId) + ".txt";
					String name1 = ".//聊天记录";
					File file = new File(name);
					File file1 = new File(name1); 
					PrintWriter outputStream = null;
					try {
						if(!file1.exists()){
							file1.mkdir();
						}
						if(!file.exists()){
							file.createNewFile();
						}			
						outputStream = new PrintWriter(new FileWriter(name, true));
						outputStream.println(jTextArea1.getText());
						outputStream.close();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					
					ManageChatUI.delQqChat(ownerId + " " + friendId);
					jFrame.dispose();
				}
			}
		});
		jPanel.setEnabled(true);
		
		jPanel.add(jl1);
		jPanel.add(jl2);
		
		jPanel.add(jButton1);
		jPanel.add(jButton2);
		jPanel.add(jScrollPane1);
		jPanel.add(jScrollPane2);
		return jPanel;
	}
	
	public void showMessage(Message m)
	{
		String info = m.getSender() + ":\r\n  " + m.getContent() + "\r\n";
		jTextArea1.append(info);
	}
}
