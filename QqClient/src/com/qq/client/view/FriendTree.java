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
	 * 定义DefaultTreeModel
	 */
	DefaultTreeModel treemode;

	/**
	 * 定义根节点"我的好友"
	 */
	JTreeBean root_bean = new JTreeBean("我的好友", new ImageIcon(Toolkit
			.getDefaultToolkit().getImage(
					getClass().getResource("/picture/client.gif"))),
			Color.BLACK);
	JTreeBean online_bean = new JTreeBean("在线好友", new ImageIcon(Toolkit
			.getDefaultToolkit().getImage(
					getClass().getResource("/picture/client.gif"))),
			Color.BLACK);
	JTreeBean quitline_bean = new JTreeBean("离线好友", new ImageIcon(Toolkit
			.getDefaultToolkit().getImage(
					getClass().getResource("/picture/client.gif"))),
			Color.BLACK);
	DefaultMutableTreeNode root = new DefaultMutableTreeNode(root_bean);
	/**
	 * 以节点"我的好友"构造一棵树
	 */
	JTree tree = new JTree(root);

	/**
	 * 定义节点"在线的好友"
	 */
	DefaultMutableTreeNode online;

	/**
	 * 定义节点"不在线的好友"
	 */
	DefaultMutableTreeNode quitline;

	/**
	 * 添加"在线的好友"和"不在线的好友"节点
	 */
	public JMenuItem menuItem1;
	JMenuItem menuItem2;
	public JMenuItem menuItem3;
	public JMenuItem menuItem4;
	JMenuItem menuItem5;
	JMenuItem menuItem6;

	/**
	 * 树初始化
	 */
	public FriendTree(String souceid){
		this.souceid = souceid;
	}
	
	public JTree chushi()// 未加任何好友的情况
	{
		online = new DefaultMutableTreeNode(online_bean);
		quitline = new DefaultMutableTreeNode(quitline_bean);
		treemode = (DefaultTreeModel) tree.getModel();// 得到DefaultTreeModel
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
					menuItem1.setText("聊天");
					// menuItem2.setText("企业消息");
					menuItem3.setText("删除好友");
					menuItem4.setText("查看信息");
					// menuItem5.setText("聊天记录");
					// menuItem6.setText("查看好友IP");
					//menuItem1.setLabel("聊天");
					// menuItem2.setLabel("企业消息");
					//menuItem3.setLabel("删除好友");
					//menuItem4.setLabel("查看信息");
					// menuItem5.setLabel("聊天记录");
					// menuItem6.setLabel("查看好友IP");
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
	 * 根据id在“在线的好友"的子节点中添加该以id为节点的子节点
	 */
	public void addonlinefriend(int id) {
		treemode = (DefaultTreeModel) tree.getModel();// 得到DefaultTreeModel
		JTreeBean online_ico = new JTreeBean(String.valueOf(id), new ImageIcon(
				Toolkit.getDefaultToolkit().getImage(
						getClass().getResource("/picture/online.png"))),
				Color.BLACK);
		DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(online_ico);
		treemode.insertNodeInto(newNode, online, online.getChildCount());
//		tree.setRowHeight(20);
		tree.getSelectionModel().setSelectionMode(
				TreeSelectionModel.SINGLE_TREE_SELECTION);// 只能选取一个节点
//		tree.setRowHeight(20);
		tree.scrollPathToVisible(new TreePath(newNode.getPath()));
	}

	/**
	 * 根据id在“不在线的好友"的子节点中添加该以id为节点的子节点
	 */
	public void addnotonlinefriend(int id) {
		JTreeBean quitline_ico = new JTreeBean(String.valueOf(id),
				new ImageIcon(Toolkit.getDefaultToolkit().getImage(
						getClass().getResource("/picture/quitline.png"))),
				Color.BLACK);
		treemode = (DefaultTreeModel) tree.getModel();// 得到DefaultTreeModel
		DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(
				quitline_ico);
		treemode.insertNodeInto(newNode, quitline, quitline.getChildCount());
//		tree.setRowHeight(20);
		tree.scrollPathToVisible(new TreePath(newNode.getPath()));
	}

	/**
	 * 首先在在线的好友节点中 删除离线的那位好友 再在不在线的好友节点中 添加离线的那位好友
	 */
	public void liveline(int id) {
		delNode(id);
		addNode(id);
		// tree.addMouseListener(new MouseListenerimp());
	}

	/**
	 * 首先在不在线的好友节点中 删除离线的那位好友 再在在线的好友节点中 添加离线的那位好友
	 */
	public void shangxian(int id) {
		delNode2(id);
		addNode2(id);
		// tree.addMouseListener(new MouseListenerimp());
	}

	/**
	 * 根据id在“不在线的好友"的子节点将此id删除
	 */
	@SuppressWarnings("unchecked")
	public void delNode2( int id)// 删除节点
	{
		DefaultMutableTreeNode n = new DefaultMutableTreeNode();
		if (quitline.getChildCount() > 0) {// 若在线好友大于零
			for (Enumeration e = quitline.children(); e.hasMoreElements();) {
				n = (DefaultMutableTreeNode) e.nextElement();
				JTreeBean n1 = (JTreeBean) n.getUserObject();
				if (n1.getString().equals(String.valueOf(id))) {
					quitline.remove(n);// 查找在线的列表中有没有此ID有则删除
					treemode.reload();
					tree.scrollPathToVisible(new TreePath(n.getPath()));
				}
			}
		}
	}

	/**
	 * 根据id在“在线的好友"的子节点将此id删除
	 */
	@SuppressWarnings("unchecked")
	public void delNode( int id)// 删除节点
	{
		DefaultMutableTreeNode n = new DefaultMutableTreeNode();
		if (online.getChildCount() > 0) {// 若在线好友大于零
			for (Enumeration e = online.children(); e.hasMoreElements();) {
				n = (DefaultMutableTreeNode) e.nextElement();
				JTreeBean n1 = (JTreeBean) n.getUserObject();
				if (n1.getString().equals(String.valueOf(id))) {
					online.remove(n);// 查找在线的列表中有没有此ID有则删除
					treemode.reload();
					tree.scrollPathToVisible(new TreePath(n.getPath()));
				}
			}
		}
	}

	/**
	 * 根据id在“不在线的好友"节点添加该以id为节点的子节点
	 */
	public void addNode(int id) {
		treemode = (DefaultTreeModel) tree.getModel();// 得到DefaultTreeModel
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
	 * 根据id在“在线的好友"节点添加该以id为节点的子节点
	 */
	public void addNode2(int id) {
		treemode = (DefaultTreeModel) tree.getModel();// 得到DefaultTreeModel
		JTreeBean online_ico = new JTreeBean(String.valueOf(id), new ImageIcon(
				Toolkit.getDefaultToolkit().getImage(
						getClass().getResource("/picture/online.png"))),
				Color.BLACK);
		DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(online_ico);
		treemode.insertNodeInto(newNode, online, online.getChildCount());
		tree.scrollPathToVisible(new TreePath(newNode.getPath()));
	}

	/**
	 * 根据id在“在线的好友"节点或"不在线的好友"节点中 删除该以id为节点的子节点
	 */
	@SuppressWarnings( { "unchecked", "unchecked" })
	public void delfriend(int id) {
		DefaultMutableTreeNode n = new DefaultMutableTreeNode();
		for (Enumeration e = online.children(); e.hasMoreElements();) {
			n = (DefaultMutableTreeNode) e.nextElement();
			JTreeBean JTB_r = (JTreeBean) n.getUserObject();
			if (JTB_r.getString().equals(String.valueOf(id))) {
				online.remove(n);// 查找在线的列表中有没有此ID有则删除
				treemode.reload();
				tree.scrollPathToVisible(new TreePath(n.getPath()));
			}
		}
		for (Enumeration e = quitline.children(); e.hasMoreElements();) {
			n = (DefaultMutableTreeNode) e.nextElement();
			JTreeBean n1 = (JTreeBean) n.getUserObject();

			if (n1.getString().equals(String.valueOf(id))) {
				quitline.remove(n);// 查找在线的列表中有没有此ID有则删除
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
			 * 开启聊天窗口
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
			 * 删除好友
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
			 * 查看好友信息
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

