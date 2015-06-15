/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.qq.client.view;

import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Enumeration;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import com.qq.client.model.ManageChatUI;
import com.qq.client.model.ManageClientConnServerThread;
import com.qq.client.model.ManageFriendTree;
import com.qq.common.Message;
import com.qq.common.MessageType;
import com.qq.common.User;

import contrib.com.blogofbug.swing.components.JCarouselMenu.MenuItem;

//import setting.User;
/**
 * 
 * @author
 */
public class FriendTree {
	public static void main(String[] args) {
		FriendTree ft = new FriendTree("1000");
	}

	String souceid = "";
	
	/**
	 * ����DefaultTreeModel
	 */
	DefaultTreeModel treemode;

	/**
	 * ������ڵ�"�ҵĺ���"
	 */
	JTreeBean root_bean = new JTreeBean("�ҵĺ���", new ImageIcon(Toolkit
			.getDefaultToolkit().getImage(
					getClass().getResource("/picture/client.gif"))),
			Color.BLACK);
	JTreeBean online_bean = new JTreeBean("���ߺ���", new ImageIcon(Toolkit
			.getDefaultToolkit().getImage(
					getClass().getResource("/picture/client.gif"))),
			Color.BLACK);
	JTreeBean quitline_bean = new JTreeBean("���ߺ���", new ImageIcon(Toolkit
			.getDefaultToolkit().getImage(
					getClass().getResource("/picture/client.gif"))),
			Color.BLACK);
	DefaultMutableTreeNode root = new DefaultMutableTreeNode(root_bean);
	/**
	 * �Խڵ�"�ҵĺ���"����һ����
	 */
	JTree tree = new JTree(root);

	/**
	 * ����ڵ�"���ߵĺ���"
	 */
	DefaultMutableTreeNode online;

	/**
	 * ����ڵ�"�����ߵĺ���"
	 */
	DefaultMutableTreeNode quitline;

	/**
	 * ���"���ߵĺ���"��"�����ߵĺ���"�ڵ�
	 */
	public JMenuItem menuItem1;
	JMenuItem menuItem2;
	public JMenuItem menuItem3;
	public JMenuItem menuItem4;
	JMenuItem menuItem5;
	JMenuItem menuItem6;

	/**
	 * ����ʼ��
	 */
	public FriendTree(String souceid){
		this.souceid = souceid;
	}
	
	public JTree chushi()// δ���κκ��ѵ����
	{
		online = new DefaultMutableTreeNode(online_bean);
		quitline = new DefaultMutableTreeNode(quitline_bean);
		treemode = (DefaultTreeModel) tree.getModel();// �õ�DefaultTreeModel
//		tree.setBackground(SystemColor.inactiveCaptionText);
		treemode.insertNodeInto(online, root, 0);
		treemode.insertNodeInto(quitline, root, 1);
		tree.setCellRenderer(new JTreeCellRenderer());
//		tree.setRowHeight(20);
//		tree.setBorder(new LineBorder(Color.BLUE));
		tree.scrollPathToVisible(new TreePath(quitline.getPath()));
		tree.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("deprecation")
			public void mousePressed(MouseEvent e) {
				@SuppressWarnings("unused")
//				int selRow = tree.getRowForLocation(e.getX(), e.getY());
				TreePath selPath = tree.getPathForLocation(e.getX(), e.getY());
				if (e.getClickCount() == 2) {
					JTree tree = (JTree) e.getSource();
					int rowLocation = tree
							.getRowForLocation(e.getX(), e.getY());
					TreePath treepath = tree.getPathForRow(rowLocation);
					DefaultMutableTreeNode treenode = (DefaultMutableTreeNode) treepath
							.getLastPathComponent();
					JTreeBean jtb = (JTreeBean) treenode.getUserObject();
					String id = jtb.getString();
					// Client.client.openChatUI(id);
					System.out.println(jtb.getString() + " in FriendTree.java");
					ChatUI cui = ManageChatUI.getQqChat(souceid + " " + jtb.getString());
					if(cui == null){
						cui = new ChatUI(souceid, jtb.getString());
						ManageChatUI.addQqChat(souceid + " " + jtb.getString(), cui);
					}
					cui.getJFrame();
				}
				if (SwingUtilities.isRightMouseButton(e)) {

					JPopupMenu popupMenu1 = new JPopupMenu();

					ItemListenerimp Itemimp = new ItemListenerimp(e);
					menuItem1 = new JMenuItem();
					// menuItem2 = new JMenuItem();
					menuItem3 = new JMenuItem();
					menuItem4 = new JMenuItem();
					// menuItem5 = new JMenuItem();
					// menuItem6 = new JMenuItem();
					//menuItem1.addMouseListener(this);
					menuItem1.addActionListener(Itemimp);
					// menuItem2.addActionListener(Itemimp);
					//menuItem3.addMouseListener(this);
					menuItem3.addActionListener(Itemimp);
					//menuItem4.addMouseListener(this);
					menuItem4.addActionListener(Itemimp);
					// menuItem5.addActionListener(Itemimp);
					// menuItem6.addActionListener(Itemimp);
					menuItem1.setText("����");
					// menuItem2.setText("��ҵ��Ϣ");
					menuItem3.setText("ɾ������");
					menuItem4.setText("�鿴��Ϣ");
					// menuItem5.setText("�����¼");
					// menuItem6.setText("�鿴����IP");
					//menuItem1.setLabel("����");
					// menuItem2.setLabel("��ҵ��Ϣ");
					//menuItem3.setLabel("ɾ������");
					//menuItem4.setLabel("�鿴��Ϣ");
					// menuItem5.setLabel("�����¼");
					// menuItem6.setLabel("�鿴����IP");
					popupMenu1.add(menuItem1);
					// popupMenu1.add(menuItem2);
					popupMenu1.add(menuItem3);
					popupMenu1.add(menuItem4);
					
					// popupMenu1.add(menuItem5);
					// popupMenu1.add(menuItem6);

					DefaultMutableTreeNode treenode = (DefaultMutableTreeNode) selPath
							.getLastPathComponent();
					JTreeBean jtb = (JTreeBean) treenode.getUserObject();
					try {
						Integer.parseInt(jtb.getString());
						popupMenu1.show(e.getComponent(), e.getX(), e.getY());
					} catch (Exception ecast) {
						return;
					}
				}
			}
		});
//		menuItem1.addActionListener(this);
//		menuItem3.addActionListener(this);
//		menuItem4.addActionListener(this);
		return tree;
	}

	/**
	 * ����id�ڡ����ߵĺ���"���ӽڵ�����Ӹ���idΪ�ڵ���ӽڵ�
	 */
	public void addonlinefriend(int id) {
		treemode = (DefaultTreeModel) tree.getModel();// �õ�DefaultTreeModel
		JTreeBean online_ico = new JTreeBean(String.valueOf(id), new ImageIcon(
				Toolkit.getDefaultToolkit().getImage(
						getClass().getResource("/picture/online.png"))),
				Color.BLACK);
		DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(online_ico);
		treemode.insertNodeInto(newNode, online, online.getChildCount());
//		tree.setRowHeight(20);
		tree.getSelectionModel().setSelectionMode(
				TreeSelectionModel.SINGLE_TREE_SELECTION);// ֻ��ѡȡһ���ڵ�
//		tree.setRowHeight(20);
		tree.scrollPathToVisible(new TreePath(newNode.getPath()));
	}

	/**
	 * ����id�ڡ������ߵĺ���"���ӽڵ�����Ӹ���idΪ�ڵ���ӽڵ�
	 */
	public void addnotonlinefriend(int id) {
		JTreeBean quitline_ico = new JTreeBean(String.valueOf(id),
				new ImageIcon(Toolkit.getDefaultToolkit().getImage(
						getClass().getResource("/picture/quitline.png"))),
				Color.BLACK);
		treemode = (DefaultTreeModel) tree.getModel();// �õ�DefaultTreeModel
		DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(
				quitline_ico);
		treemode.insertNodeInto(newNode, quitline, quitline.getChildCount());
//		tree.setRowHeight(20);
		tree.scrollPathToVisible(new TreePath(newNode.getPath()));
	}

	/**
	 * ���������ߵĺ��ѽڵ��� ɾ�����ߵ���λ���� ���ڲ����ߵĺ��ѽڵ��� ������ߵ���λ����
	 */
	public void liveline(int id) {
		delNode(id);
		addNode(id);
		// tree.addMouseListener(new MouseListenerimp());
	}

	/**
	 * �����ڲ����ߵĺ��ѽڵ��� ɾ�����ߵ���λ���� �������ߵĺ��ѽڵ��� ������ߵ���λ����
	 */
	public void shangxian(int id) {
		delNode2(id);
		addNode2(id);
		// tree.addMouseListener(new MouseListenerimp());
	}

	/**
	 * ����id�ڡ������ߵĺ���"���ӽڵ㽫��idɾ��
	 */
	@SuppressWarnings("unchecked")
	public void delNode2( int id)// ɾ���ڵ�
	{
		DefaultMutableTreeNode n = new DefaultMutableTreeNode();
		if (quitline.getChildCount() > 0) {// �����ߺ��Ѵ�����
			for (Enumeration e = quitline.children(); e.hasMoreElements();) {
				n = (DefaultMutableTreeNode) e.nextElement();
				JTreeBean n1 = (JTreeBean) n.getUserObject();
				if (n1.getString().equals(String.valueOf(id))) {
					quitline.remove(n);// �������ߵ��б�����û�д�ID����ɾ��
					treemode.reload();
					tree.scrollPathToVisible(new TreePath(n.getPath()));
				}
			}
		}
	}

	/**
	 * ����id�ڡ����ߵĺ���"���ӽڵ㽫��idɾ��
	 */
	@SuppressWarnings("unchecked")
	public void delNode( int id)// ɾ���ڵ�
	{
		DefaultMutableTreeNode n = new DefaultMutableTreeNode();
		if (online.getChildCount() > 0) {// �����ߺ��Ѵ�����
			for (Enumeration e = online.children(); e.hasMoreElements();) {
				n = (DefaultMutableTreeNode) e.nextElement();
				JTreeBean n1 = (JTreeBean) n.getUserObject();
				if (n1.getString().equals(String.valueOf(id))) {
					online.remove(n);// �������ߵ��б�����û�д�ID����ɾ��
					treemode.reload();
					tree.scrollPathToVisible(new TreePath(n.getPath()));
				}
			}
		}
	}

	/**
	 * ����id�ڡ������ߵĺ���"�ڵ���Ӹ���idΪ�ڵ���ӽڵ�
	 */
	public void addNode(int id) {
		treemode = (DefaultTreeModel) tree.getModel();// �õ�DefaultTreeModel
		JTreeBean quitline_ico = new JTreeBean(String.valueOf(id),
				new ImageIcon(Toolkit.getDefaultToolkit().getImage(
						getClass().getResource("/picture/quitline.png"))),
				Color.BLACK);
		DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(
				quitline_ico);
		treemode.insertNodeInto(newNode, quitline, quitline.getChildCount());
		tree.scrollPathToVisible(new TreePath(newNode.getPath()));
	}

	/**
	 * ����id�ڡ����ߵĺ���"�ڵ���Ӹ���idΪ�ڵ���ӽڵ�
	 */
	public void addNode2(int id) {
		treemode = (DefaultTreeModel) tree.getModel();// �õ�DefaultTreeModel
		JTreeBean online_ico = new JTreeBean(String.valueOf(id), new ImageIcon(
				Toolkit.getDefaultToolkit().getImage(
						getClass().getResource("/picture/online.png"))),
				Color.BLACK);
		DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(online_ico);
		treemode.insertNodeInto(newNode, online, online.getChildCount());
		tree.scrollPathToVisible(new TreePath(newNode.getPath()));
	}

	/**
	 * ����id�ڡ����ߵĺ���"�ڵ��"�����ߵĺ���"�ڵ��� ɾ������idΪ�ڵ���ӽڵ�
	 */
	@SuppressWarnings( { "unchecked", "unchecked" })
	public void delfriend(int id) {
		DefaultMutableTreeNode n = new DefaultMutableTreeNode();
		for (Enumeration e = online.children(); e.hasMoreElements();) {
			n = (DefaultMutableTreeNode) e.nextElement();
			JTreeBean JTB_r = (JTreeBean) n.getUserObject();
			if (JTB_r.getString().equals(String.valueOf(id))) {
				online.remove(n);// �������ߵ��б�����û�д�ID����ɾ��
				treemode.reload();
				tree.scrollPathToVisible(new TreePath(n.getPath()));
			}
		}
		for (Enumeration e = quitline.children(); e.hasMoreElements();) {
			n = (DefaultMutableTreeNode) e.nextElement();
			JTreeBean n1 = (JTreeBean) n.getUserObject();

			if (n1.getString().equals(String.valueOf(id))) {
				quitline.remove(n);// �������ߵ��б�����û�д�ID����ɾ��
				treemode.reload();
				tree.scrollPathToVisible(new TreePath(n.getPath()));
			}
		}
	}

	
	class ItemListenerimp implements ActionListener {
		MouseEvent me;
		String id;
		JTreeBean jtb;
		
		ItemListenerimp(MouseEvent e) {
			me = e;
			JTree tree = (JTree) me.getSource();
			int rowLocation = tree.getRowForLocation(me.getX(), me.getY());
			TreePath treepath = tree.getPathForRow(rowLocation);
			DefaultMutableTreeNode treenode = (DefaultMutableTreeNode) treepath
					.getLastPathComponent();
			jtb = (JTreeBean) treenode.getUserObject();
			id = jtb.getString();
//			TreeNode treenode1 = (TreeNode) treepath.getLastPathComponent();
//			id = treenode1.toString();
		}

		public void actionPerformed(ActionEvent e) {
			/**
			 * �������촰��
			 */
			if (e.getSource() == menuItem1) {
				// Client.client.openChatUI(id);
				ChatUI cui = ManageChatUI.getQqChat(souceid + " " + jtb.getString());
				if(cui != null){
					cui.getJFrame();
				}else{
					cui = new ChatUI(souceid, jtb.getString());
					ManageChatUI.addQqChat(souceid + " " + jtb.getString(), cui);
					cui.getJFrame();
				}
			}

			/**
			 * ɾ������
			 */
			if (e.getSource() == menuItem3) {
				// Client.client.delFriend(Integer.parseInt(id));
				FriendTree ft = ManageFriendTree.getFriendTree(souceid);
				ft.delfriend(Integer.parseInt(id));
				Message m = new Message();
				m.setSender(souceid);
				m.setGetter(id);
				m.setMsType(MessageType.message_notify_delfriend);
				try {
					ObjectOutputStream oos = new ObjectOutputStream(
							ManageClientConnServerThread.getClientConnServerThread
							(souceid).s.getOutputStream());
					oos.writeObject(m);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			/**
			 * �鿴������Ϣ
			 */
			if (e.getSource() == menuItem4) {
				// Client.client.checkfriendInfo(Integer.parseInt(id));
				String friendid = jtb.getString();
				FriendInfoUI fiui = new FriendInfoUI(souceid, friendid);
				fiui.getJFrame();
			}
		}
	}

}

