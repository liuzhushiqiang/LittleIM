/**
 * 这是服务器的控制界面，可以完成启动、关闭服务器
 * 可以管理和监控用户
 */

package com.qq.server.view;

import javax.swing.*;

import com.qq.common.*;
import com.qq.server.domain.Users;
import com.qq.server.model.*;
import com.qq.server.service.UsersService;

import java.awt.*;
import java.awt.event.*;

public class MyServerUI extends JFrame implements ActionListener, WindowListener{
	
	JPanel jp1;
	JButton jb1, jb2;
	
	Thread t;
	MyQqServer myQqServer;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyServerUI myServerFrame = new MyServerUI();
	}
	
	public MyServerUI()
	{
		jp1 = new JPanel();
		jb1 = new JButton("启动服务器");
		jb1.addActionListener(this);
		jb2 = new JButton("关闭服务器");
		jb2.addActionListener(this);
		jp1.add(jb1);
		jp1.add(jb2);
		
		this.add(jp1);
		this.setSize(500, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == jb1)
		{
			myQqServer = new MyQqServer();
			t = new Thread(myQqServer);
			t.start();
		}
		else if(e.getSource() == jb2)
		{
			t.stop();
			System.out.println("服务器关闭！");
		}
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		UsersService us = new UsersService();
		us.logoutAll();
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

}
