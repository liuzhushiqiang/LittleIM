package com.qq.server.service;

import java.util.ArrayList;

import org.junit.Test;

import com.qq.common.User;

public class TestService {

	@Test
	/*
	 * ≤‚ ‘getfriend()
	 */
	public void testUsers1(){
		UsersService us = new UsersService();
		ArrayList<User> users = us.getFriends("1001");
		for(User u : users){
			System.out.println(u.getUserID());
		}
	}
	
	@Test
	/**
	 *
	 * ≤‚ ‘getonlinefriend()
	 */
	public void testUsers2(){
		UsersService us = new UsersService();
		ArrayList<User> users = us.getFriends("1001");
		System.out.println(us.getOnlineFriend(users));
	}
	
	@Test
	/*
	 * ≤‚ ‘setLogout()
	 */
	public void testUsers3(){
		UsersService us = new UsersService();
		us.setLogout("1000");
	}
}
