package com.qq.client.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JWindow;

@SuppressWarnings("serial")
public class FileTransUI extends JWindow {
	
	public static void main(String[] args){
		FileTransUI jdb = new FileTransUI("文件上传", "/picture/online.png");
		String[] uploadInfo = new String[] { "正在上传文件：" + "c://test.txt",
				"文件名称：" + "test.txt", "上传文件大小：" + 1024 + "字节",
				"已上传：0字节", "上传比例：0%" };
		JLabel[] labels = jdb.getLabels();
		for (int lxb = 0; lxb < labels.length; lxb++) {
			labels[lxb].setText(uploadInfo[lxb]);
		}
	}
	
	private JProgressBar progress;
	private JButton btn;
	private JLabel[] labels = new JLabel[5];
	private JButton closeBtn;

	public JProgressBar getProgress() {
		return progress;
	}

	public void setProgress(JProgressBar progress) {
		this.progress = progress;
	}

	public JButton getBtn() {
		return btn;
	}

	public void setBtn(JButton btn) {
		this.btn = btn;
	}

	public JLabel[] getLabels() {
		return labels;
	}

	public void setLabels(JLabel[] labels) {
		this.labels = labels;
	}

	public JButton getCloseBtn() {
		return closeBtn;
	}

	public void setCloseBtn(JButton closeBtn) {
		this.closeBtn = closeBtn;
	}

	public FileTransUI(String title, String bgImg) {
		setAlwaysOnTop(true);
		setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		JPanel splash = new JPanel(new BorderLayout());
		JPanel top = new JPanel();
		top.setBackground(new Color(255, 153, 204));
		BorderLayout toplay = new BorderLayout();
		top.setLayout(toplay);
		JLabel tt = new JLabel(title);
		ImageIcon cloImg = new ImageIcon(System.getProperty("user.dir")
				+ "/bin/image/4.png");
		closeBtn = new JButton("close ");
		
		/**
		 * 监听close点击时间
		 */
		closeBtn.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e){
				
			}
		});
		
		closeBtn.setBackground(new Color(255, 153, 204));
		top.add(tt, BorderLayout.CENTER);
		top.add(closeBtn, BorderLayout.EAST);
		top.add(new JLabel("   "), BorderLayout.WEST);
		splash.add(top, BorderLayout.NORTH);
		ImageIcon img = new ImageIcon(bgImg);
		btn = new JButton(img);
		getLayeredPane().add(btn, new Integer(Integer.MIN_VALUE));
		btn.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
		GridLayout gl = new GridLayout(this.getLabels().length, 1); // labels.length行1列
		btn.setLayout(gl);
		for (int i = 0; i < labels.length; i++) {
			this.getLabels()[i] = new JLabel("000" + i);
			btn.add(labels[i]);
		}
		splash.add(btn, BorderLayout.CENTER);
		progress = new JProgressBar(1, 100);
		progress.setStringPainted(true);
		progress.setBorderPainted(false);
		progress.setString("0%");
		progress.setBackground(Color.WHITE);
		splash.add(progress, BorderLayout.SOUTH);
		setContentPane(splash);
		Dimension screen = getToolkit().getScreenSize();
		setSize(img.getIconWidth(), img.getIconHeight() + 60);
		setLocation((screen.width - getSize().width) / 2,
				(screen.height - getSize().height) / 2);
		new DragJWindow(this, splash); // 设置窗口可拖动
		this.setSize(300, 400);
		setVisible(true);
	}
}