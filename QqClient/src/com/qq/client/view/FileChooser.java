package com.qq.client.view;

//AWT: FileDialog�� + FilenameFilter�� ����ʵ�ֱ�����
//Swing: JFileChooser�� + FileFilter��     ����ʵ�ֱ�����
//
//�����������Դ��ļ��ͱ����ļ��ĶԻ���   

import java.awt.*; //Ϊ��ʹ�ò��ֹ�����   
import java.awt.event.*;//���������¼�   
import javax.swing.*; //���µ�GUI���   
import java.io.*; //��д�ļ���   

public class FileChooser {

	private File f;
	private JFileChooser fc;
	private int flag;

	public FileChooser() {
		fc = new JFileChooser();
	}

	public File openFile() // ���ļ�
	{
		// ���ô��ļ��Ի���ı���
		fc.setDialogTitle("Open File");

		// ������ʾ���ļ��ĶԻ���
		try {
			flag = fc.showOpenDialog(null);
		} catch (HeadlessException head) {

			System.out.println("Open File Dialog ERROR!");
		}

		// �������ȷ����ť�����ø��ļ���
		if (flag == JFileChooser.APPROVE_OPTION) {
			// ��ø��ļ�
			f = fc.getSelectedFile();
			System.out.println("open file----" + f.getName());
		}
		return f;
	}

	public File savefile() // �����ļ�
	{
		String fileName = "";

		// ���ñ����ļ��Ի���ı���
		fc.setDialogTitle("Save File");

		// ���ｫ��ʾ�����ļ��ĶԻ���
		try {
			flag = fc.showSaveDialog(null);
		} catch (HeadlessException he) {
			System.out.println("Save File Dialog ERROR!");
		}

		// �������ȷ����ť�����ø��ļ���
		if (flag == JFileChooser.APPROVE_OPTION) {
			// ���������Ҫ������ļ�
			f = fc.getSelectedFile();
			// ����ļ���
			fileName = fc.getName(f);
			// Ҳ����ʹ��fileName=f.getName();
			System.out.println(fileName);
		}
		return f;
	}

	public static void main(String[] args) {
		new FileChooser();
	}
}
