package com.qq.server.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.sql.*;
import javax.sql.*;

public class SqlHelper {
	// ������Ҫ�ı���
	private static Connection ct = null;

	// ����������£�����ʹ�õ���PreparedStatement����ֹsqlע��
	private static PreparedStatement ps = null;
	private static ResultSet rs = null;
	// �洢����
	private static CallableStatement cs = null;
	// �������ݿ����
	private static String url = "";
	private static String username = "";
	private static String driver = "";
	private static String password = "";

	private static Properties pp = null;
	private static FileInputStream fis = null;

	// ������������ҪҪһ��
	static {
		try {
			// ��dbinfo.propertis�ļ��ж�ȡ��������
			pp = new Properties();
			fis = new FileInputStream("dbinfo.properties");
			pp.load(fis);
			url = pp.getProperty("url");
			username = pp.getProperty("username");
			driver = pp.getProperty("driver");
			password = pp.getProperty("password");
			Class.forName(driver);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				fis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			fis = null;
		}
	}

	public static Connection getConnection() {
		try {
			ct = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ct;
	}
	
	public static Connection getCt() {
		return ct;
	}
	
	public static PreparedStatement getPs() {
		return ps;
	}
	
	public static ResultSet getRs() {
		return rs;
	}

	// ͳһ��select
	public static ResultSet executeQuery(String sql, String[] parameters) {

		try {
			ct = getConnection();
			ps = ct.prepareStatement(sql);
			if (parameters != null) {
				for (int i = 0; i < parameters.length; i++) {
					ps.setString(i + 1, parameters[i]);
				}
			}
			rs = ps.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
			// �׳��쳣���׳������쳣�����Ը����øú���һ��ѡ��
			throw new RuntimeException(e.getMessage());
		} finally {
			// �ر���Դ
			//close(rs,ps,ct);
		}
		return rs;
	}
	
	// ��ҳ������
	public static ResultSet executeQuery2() {
		return null;
	}

	//����������汾
	public static void executeUpdate(String sql, String[] parameters) {
		// ����һ��ps
		try {
			ct = getConnection();
			ps = ct.prepareStatement(sql);
			// ������ֵ
			if (parameters != null) {
				for (int i = 0; i < parameters.length; i++) {
					ps.setString(i + 1, parameters[i]);
				}
			}
			// ִ��
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// �׳��쳣���׳������쳣�����Ը����øú���һ��ѡ��
			throw new RuntimeException(e.getMessage());
		} finally {
			// �ر���Դ
			close(rs, ps, ct);
		}
	}
	
	// ����ж�� update / delete /insert ��䣬��Ҫ��������
	/*
	 * ct.setAutoCommit(false); update��� delete��� insert��� ... ct.commit();
	 */
	public static void executeUpdate2(String[] sql, String[][] parameters) {
		try {
			// �������
			ct = getConnection();
			// ��Ϊ��ʱ���û�����Ŀ����Ƕ��sql���
			ct.setAutoCommit(false);
			// ...
			for (int i = 0; i < sql.length; i++) {
				if (parameters[i] != null) {
					ps = ct.prepareStatement(sql[i]);
					for (int j = 0; j < parameters[i].length; j++) {
						ps.setString(j + 1, parameters[i][j]);
					}
					ps.executeUpdate();
				}
			}
			ct.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// �ع�
			try {
				ct.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
			// �׳��쳣���׳������쳣�����Ը����øú���һ��ѡ��
			throw new RuntimeException(e.getMessage());
		} finally {
			close(rs, ps, ct);
		}
	}
	
	// ���ô洢����
	// sql �� {call ����(?,?,?)}
	public static void callProl(String sql, String[] parameters) {
		try {
			ct = getConnection();
			ps = ct.prepareCall(sql);

			// ?�Ÿ�ֵ
			if (parameters != null) {
				for (int i = 0; i < parameters.length; i++) {
					cs.setObject(i + 1, parameters[i]);
				}
			}
			cs.execute();
		} catch (Exception e) {
			e.printStackTrace();
			// �׳��쳣���׳������쳣�����Ը����øú���һ��ѡ��
			throw new RuntimeException(e.getMessage());
		} finally {
			// �ر���Դ
			close(rs, cs, ct);
		}
	}

	// �����д洢���̵ķ���ֵ
	// sql call���� (?,?,?)
	public static void callPro2(String sql, String[] inparameters,
			Integer[] outparameters) {
		try {
			ct = getConnection();
			ps = ct.prepareCall(sql);
			if (inparameters != null) {
				for (int i = 0; i < inparameters.length; i++) {
					cs.setObject(i + 1, inparameters[i]);
				}
			}
			if (outparameters != null) {
				for (int i = 0; i < outparameters.length; i++) {
					cs.setObject(inparameters.length + i + 1, outparameters[i]);
				}
			}
			cs.execute();
		} catch (Exception e) {
			e.printStackTrace();
			// �׳��쳣���׳������쳣�����Ը����øú���һ��ѡ��
			throw new RuntimeException(e.getMessage());
		} finally {
			// �ر���Դ
			// close(rs,ps,ct);
		}
	}

	//�ر���Դ
	public static void close(ResultSet rs, Statement ps, Connection ct) {
		if (rs != null) {
			try {
				rs.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ps = null;
		}
		if (ct != null) {
			try {
				ct.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ct = null;
		}
	}
}


