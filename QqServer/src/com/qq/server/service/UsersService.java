package com.qq.server.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.jws.soap.SOAPBinding.Use;
import javax.lang.model.SourceVersion;
import javax.naming.spi.DirStateFactory.Result;

import com.mysql.jdbc.PreparedStatement;
import com.qq.common.User;
import com.qq.server.dao.SqlHelper;
import com.qq.server.domain.Users;
import com.qq.server.tool.MD5Util;

public class UsersService {

	/**
	 * 登陆验证
	 * 
	 * @param u
	 * @return
	 */
	public boolean checklogin(User u) {
		String sql = "select * from users where userid = ? and pwd = ?";
		String[] parameters = { u.getUserID(), MD5Util.MD5(u.getPasswd()) };
		try {
			ResultSet rs = SqlHelper.executeQuery(sql, parameters);
			if (rs.next()) {
				// 设置在线标志
				String sql1 = "update users set isonline = 1 where userid = ? and pwd = ?";
				String[] parameters1 = { u.getUserID(),
						MD5Util.MD5(u.getPasswd()) };
				try {
					SqlHelper.executeUpdate(sql1, parameters1);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		} finally {
			SqlHelper.close(SqlHelper.getRs(), SqlHelper.getPs(), SqlHelper
					.getCt());
		}
	}

	public boolean signup() {
		return true;
	}

	/*
	 * 获取所有的好友
	 */
	public ArrayList<User> getFriends(String srcid) {
		String sql = "select userid1, userid2 from rels where userid1 = ? or userid2 = ?";
		String[] parameters = { srcid, srcid };
		ArrayList<User> al = null;
		try {
			al = new ArrayList<User>();
			ResultSet rs = SqlHelper.executeQuery(sql, parameters);
			while (rs.next()) {
				String userid1 = rs.getString(1);
				String userid2 = rs.getString(2);
				if (srcid.equals(userid1)) {
					User user = new User();
					user.setUserID(userid2);
					al.add(user);
				} else if (srcid.equals(userid2)) {
					User user = new User();
					user.setUserID(userid1);
					al.add(user);
				}
			}
			return al;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		} finally {
			SqlHelper.close(SqlHelper.getRs(), SqlHelper.getPs(), SqlHelper
					.getCt());
		}
	}

	/*
	 * 获取在线的好友
	 */
	public String getOnlineFriend(ArrayList<User> users) {
		String res = "";
		try {
			for (User u : users) {
				String sql = "select count(*) from users where userid = ?"
						+ " and isonline = 1";
				String[] parameters = { u.getUserID() };
				ResultSet rs = SqlHelper.executeQuery(sql, parameters);
				if (rs.next() && rs.getInt(1) == 1) {
					res += u.getUserID() + " ";
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			SqlHelper.close(SqlHelper.getRs(), SqlHelper.getPs(), SqlHelper
					.getCt());
		}
		return res;
	}

	/*
	 * 获取不在线的好友
	 * 
	 * @Return Arraylist<User>
	 */
	public String getNotOnlineFriend(ArrayList<User> users) {
		String res = "";
		try {
			for (User u : users) {
				String sql = "select count(*) from users where userid = ?"
						+ " and isonline = 0";
				String[] parameters = { u.getUserID() };
				ResultSet rs = SqlHelper.executeQuery(sql, parameters);
				if (rs.next() && rs.getInt(1) == 1) {
					res += u.getUserID() + " ";
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			SqlHelper.close(SqlHelper.getRs(), SqlHelper.getPs(), SqlHelper
					.getCt());
		}
		return res;
	}

	/*
	 * 用户退出时把自己的isonline状态设为0
	 */
	public void setLogout(String srcid) {
		String sql = "update users set isonline = 0 where userid = ?";
		String[] parameters = { srcid };
		try {
			SqlHelper.executeUpdate(sql, parameters);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			SqlHelper.close(SqlHelper.getRs(), SqlHelper.getPs(), SqlHelper
					.getCt());
		}
	}

	/*
	 * 关闭服务器时的善后处理，把所有的用户状态isonline设为0
	 */
	public void logoutAll() {
		String sql = "update users set isonline = 0";
		try {
			SqlHelper.executeUpdate(sql, null);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			SqlHelper.close(SqlHelper.getRs(), SqlHelper.getPs(), SqlHelper
					.getCt());
		}
	}

	public int register(String rel_info) {
		int ret = 0;
		String[] infos = rel_info.split("_");
		System.out.println(rel_info + " in UserService.java register");
		String sql = "insert into users(nickname, pwd, username, gender, age,"
				+ " constellation, signatrue, regdate) values(?, ?, ?, ?, ?, ?, ?, now())";
		String[] parameters = { infos[0], infos[1], infos[2], infos[3],
				infos[4], infos[5], infos[6] };
		String sql1 = "SELECT max(userid) from users";
		Connection ct = null;
		ResultSet rs = null;
		try {
			ct = SqlHelper.getConnection();
			ct.setAutoCommit(false);
			SqlHelper.executeUpdate(sql, parameters);
			rs = SqlHelper.executeQuery(sql1, null);
			if (rs.next()) {
				ret = rs.getInt(1);
				System.out.println(ret + " in UserService.java register");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			try {
				ct.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			SqlHelper.close(SqlHelper.getRs(), SqlHelper.getPs(), SqlHelper
					.getCt());
		}
		return ret;
	}

	public void delFriend(String souceid, String destid) {
		String sql = "delete from rels where userid1 = ? and userid2 = ?";
		String[] parameters = { souceid, destid };
		String[] parameters1 = { destid, souceid };
		try {
			SqlHelper.executeUpdate(sql, parameters);
			SqlHelper.executeUpdate(sql, parameters1);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			SqlHelper.close(SqlHelper.getRs(), SqlHelper.getPs(), SqlHelper
					.getCt());
		}
	}

	public User getUserinfo(String userid) {
		User u = null;
		String sql = "select * from users where userid = ?";
		String[] parameters = { userid };
		ResultSet rs = null;
		try {
			rs = SqlHelper.executeQuery(sql, parameters);
			if (rs.next()) {
				u = new User();
				u.setUserID(rs.getString(1));
				u.setPasswd(rs.getString(2));
				u.setUsername(rs.getString(3));
				u.setNickname(rs.getString(4));
				u.setGender(rs.getString(5));
				u.setAge(rs.getString(6));
				u.setSignatrue(rs.getString(7));
				u.setConstellation(rs.getString(8));
				u.setRegdate(rs.getString(9));
				u.setIsonline(rs.getString(10));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			SqlHelper.close(SqlHelper.getRs(), SqlHelper.getPs(), SqlHelper
					.getCt());
		}
		return u;
	}

	public boolean modifyUserInfo(User u) {
		boolean res = true;
		String sql = "update users set nickname = ?, pwd = ?, " +
				"username = ?, gender = ?, age = ?, constellation = ?, " +
				"signatrue = ? where userid = ?";
		String[] parameters = {u.getNickname(), u.getPasswd(), u.getUsername(),
				u.getGender(),
				u.getAge(), u.getConstellation(), u.getSignatrue(), u.getUserID()};
		try {
			SqlHelper.executeUpdate(sql, parameters);
		} catch (Exception e) {
			// TODO: handle exception
			res = false;
			e.printStackTrace();
		} finally {
			SqlHelper.close(SqlHelper.getRs(), SqlHelper.getPs(), SqlHelper
					.getCt());
		}
		return res;
	}
	
	public boolean addFriend(String srcid, String destid){
		boolean res = true;
		String sql = "insert into rels(userid1, userid2, createdate) " +
				"values(?, ?, now())";
		String[] parameters = {srcid, destid};
		try {
			SqlHelper.executeUpdate(sql, parameters);
		} catch (Exception e) {
			// TODO: handle exception
			res = false;
			e.printStackTrace();
		} finally {
			SqlHelper.close(SqlHelper.getRs(), SqlHelper.getPs(), SqlHelper
					.getCt());
		}
		return res;
	}
}
