package com.qq.server.dao;

import org.junit.Test;
import com.qq.server.tool.*;

public class TestJdbc {

	@Test
	public void testSqlHelper(){
		String sql = "insert into users(name, pwd, email, tel, grade) " +
				"values(?, ?, ?, ?, ?)";
		String parameters[] = 
		{"shiqiang", MD5Util.MD5("shiqiang"), "shiqiang@sohu.com", "111", "1"};
		SqlHelper.executeUpdate(sql, parameters);
	}
	
}
