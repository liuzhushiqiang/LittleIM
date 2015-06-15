package com.qq.client.view;

//AWT: FileDialog类 + FilenameFilter类 可以实现本功能
//Swing: JFileChooser类 + FileFilter类     可以实现本功能
//
//该类用来测试打开文件和保存文件的对话框   

import java.awt.*; //为了使用布局管理器   
import java.awt.event.*;//用来处理事件   
import javax.swing.*; //最新的GUI组件   
import java.io.*; //读写文件用   

public class FileChooser {

	private File f;
	private JFileChooser fc;
	private int flag;

	public FileChooser() {
		fc = new JFileChooser();
	}

	public File openFile() // 打开文件
	{
		// 设置打开文件对话框的标题
		fc.setDialogTitle("Open File");

		// 这里显示打开文件的对话框
		try {
			flag = fc.showOpenDialog(null);
		} catch (HeadlessException head) {

			System.out.println("Open File Dialog ERROR!");
		}

		// 如果按下确定按钮，则获得该文件。
		if (flag == JFileChooser.APPROVE_OPTION) {
			// 获得该文件
			f = fc.getSelectedFile();
			System.out.println("open file----" + f.getName());
		}
		return f;
	}

	public File savefile() // 保存文件
	{
		String fileName = "";

		// 设置保存文件对话框的标题
		fc.setDialogTitle("Save File");

		// 这里将显示保存文件的对话框
		try {
			flag = fc.showSaveDialog(null);
		} catch (HeadlessException he) {
			System.out.println("Save File Dialog ERROR!");
		}

		// 如果按下确定按钮，则获得该文件。
		if (flag == JFileChooser.APPROVE_OPTION) {
			// 获得你输入要保存的文件
			f = fc.getSelectedFile();
			// 获得文件名
			fileName = fc.getName(f);
			// 也可以使用fileName=f.getName();
			System.out.println(fileName);
		}
		return f;
	}

	public static void main(String[] args) {
		new FileChooser();
	}
}
