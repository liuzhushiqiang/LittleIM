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

public class DownloadFile{

	//File f = null;

	String friendid;

	String userid;

	String fileName;

	String filePath;

	long fileLength;
	
	//String fileDir = "G://test";

	public DownloadFile(String userid, String friendid) {
		FileChooser fc = new FileChooser();
		File f = fc.savefile();
		filePath = f.getPath();
		//this.f = f;
		this.userid = userid;
		this.friendid = friendid;
		//this.fileName = f.getName();
		//this.filePath = f.getPath();
		//this.fileLength = f.length();
	}

	public void run() {
		try {			
			Socket s = ManageClientConnServerThread
					.getClientConnServerThread(userid).s;
			
			DataInputStream dis = new DataInputStream(s.getInputStream());
			
			fileName = dis.readUTF();
			fileLength = dis.readLong();
			
			//filePath = fileDir+File.separator+fileName;
			
			FileOutputStream fos = new FileOutputStream(filePath);
			byte[] buffer = new byte[2048];
			int len = 0;
			double xzbl = 0; // 文件下载比例
			int xzdx = 0; // 文件上传大小
			final FileTransUI jdb = new FileTransUI("文件下载", "/picture/online.png");
			String[] uploadInfo = new String[] { "正在下载文件：" + filePath,
					"文件名称：" + fileName, "下载文件大小：" + fileLength + "字节",
					"已下载：0字节", "上传比例：0%" };
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
			while (xzdx < fileLength && (len = dis.read(buffer)) > 0) {
				//Thread.sleep(100);
				xzdx += len;
				xzbl = (double) xzdx / fileLength;
				int bl = (int) (xzbl * 100);
				fos.write(buffer, 0, len);
				p.setValue(bl);
				p.setString(bl + "%");
				String[] gxsc = new String[] { "已下载：" + xzdx + "字节",
						"下载比例：" + bl + "%" };
				for (int lxb = 0; lxb < gxsc.length; lxb++) {
					labels[lxb + 3].setText(gxsc[lxb]);
				}
			}
			
			jdb.dispose();
			fos.close();
			JOptionPane.showMessageDialog(null, "文件发送成功", "发送文件", 1);
			//fis.close();
			// client.close();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
}

