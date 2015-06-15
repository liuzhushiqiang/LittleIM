package com.qq.client.view;

import java.io.*;
import java.net.Socket;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JProgressBar;

import com.qq.client.model.ManageClientConnServerThread;
import com.qq.common.Message;
import com.qq.common.MessageType;

public class UploadFile extends Thread{

	File f = null;

	String friendid;

	String userid;

	String fileName;

	String filePath;

	long fileLength;

	public UploadFile(File f, String userid, String friendid) {
		this.f = f;
		this.userid = userid;
		this.friendid = friendid;
		this.fileName = f.getName();
		this.filePath = f.getPath();
		this.fileLength = f.length();
	}

	public void run() {
		try {
			Socket s = ManageClientConnServerThread
					.getClientConnServerThread(userid).s;
			
			Message m = new Message();
			m.setSender(userid);
			m.setGetter(friendid);
			m.setMsType(MessageType.message_file_trans_request);
			try {
				ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
				oos.writeObject(m);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			DataOutputStream dos = new DataOutputStream(s.getOutputStream());
			dos.writeUTF(fileName);
			dos.writeLong(fileLength);
			FileInputStream fis = new FileInputStream(f);
			byte[] buffer = new byte[2048];
			int len = 0;
			double scbl = 0; // 文件上传比例
			int scdx = 0; // 文件上传大小
			final FileTransUI jdb = new FileTransUI("文件上传", "/picture/online.png");
			String[] uploadInfo = new String[] { "正在上传文件：" + filePath,
					"文件名称：" + fileName, "上传文件大小：" + fileLength + "字节",
					"已上传：0字节", "上传比例：0%" };
			JLabel[] labels = jdb.getLabels();
			for (int lxb = 0; lxb < labels.length; lxb++) {
				labels[lxb].setText(uploadInfo[lxb]);
			}
			JProgressBar p = jdb.getProgress();
			JButton closeBtn = jdb.getCloseBtn();

			closeBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					//jdb.dispose();
					// khd.setIsnoClose(false);
				}
			});
			while ((len = fis.read(buffer)) > 0) {
				Thread.sleep(100);			
				dos.write(buffer, 0, len);
				scdx += len;
				scbl = (double) scdx / fileLength;
				int bl = (int) (scbl * 100);
				p.setValue(bl);
				p.setString(bl + "%");
				String[] gxsc = new String[] { "已上传：" + scdx + "字节",
						"上传比例：" + bl + "%" };
				for (int lxb = 0; lxb < gxsc.length; lxb++) {
					labels[lxb + 3].setText(gxsc[lxb]);
				}
			}
			//buffer = "123";
			//dos.write(buffer);
			jdb.dispose();
			fis.close();
			JOptionPane.showMessageDialog(null, "文件发送成功", "发送文件", 1);
			// client.close();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
}
