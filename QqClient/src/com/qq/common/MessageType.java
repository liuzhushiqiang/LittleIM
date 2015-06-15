/**
 * 定义包的种类
 */

package com.qq.common;

public interface MessageType {

	String message_login_succeed = "1";//表明登陆成功
	String message_login_fail = "2";//表明登陆失败
	String message_comm_mes = "3";//普通信息包
	String message_get_onLineFriend = "4";//要求在线好友信息
	String message_ret_onLineFriend = "5";//返回在线好友信息
	String message_ret_friend_login = "6";//返回某位好友上线的通知
	String message_ret_friend_logout = "7";//返回某位好友离线的通知
	String message_notify_self_logout = "8";//向服务器发出自己下线的消息
	String message_get_reg = "9";//向服务器发出注册请求
	String message_ret_reg = "10";//接收服务器对注册的响应
	String message_notify_delfriend = "11";//向服务器发送删除好友的消息
	String message_recieve_delfriend = "12";//接受服务器发来的删除好友的消息
	String message_get_userinfo = "13";//向服务器发送获取用户信息的请求
	String message_modify_user_info = "14";//向服务器发送修改个人设置的请求
	String message_ret_modify_user_info_result = "15";//服务器返回个人设置修改的结果
	String message_add_friend_request = "16";//向服务器发送添加好友的请求
	String message_add_friend_result = "17";//服务器返回添加好友的结果
	String message_file_trans_request = "18";//向服务器发送传文件请求
	String message_file_trans_response = "19";//回应是否接受文件传送
}
