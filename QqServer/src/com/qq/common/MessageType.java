/**
 * �����������
 */

package com.qq.common;

public interface MessageType {

	String message_login_succeed = "1";//������½�ɹ�
	String message_login_fail = "2";//������½ʧ��
	String message_comm_mes = "3";//��ͨ��Ϣ��
	String message_get_onLineFriend = "4";//Ҫ�����ߺ�����Ϣ
	String message_ret_onLineFriend = "5";//�������ߺ�����Ϣ
	String message_ret_friend_login = "6";//����ĳλ�������ߵ�֪ͨ
	String message_ret_friend_logout = "7";//����ĳλ�������ߵ�֪ͨ
	String message_notify_self_logout = "8";//������������Լ����ߵ���Ϣ
	String message_get_reg = "9";//�����������ע������
	String message_ret_reg = "10";//���շ�������ע�����Ӧ
	String message_notify_delfriend = "11";//�����������ɾ�����ѵ���Ϣ
	String message_recieve_delfriend = "12";//���ܷ�����������ɾ�����ѵ���Ϣ
	String message_get_userinfo = "13";//����������ͻ�ȡ�û���Ϣ������
	String message_modify_user_info = "14";//������������޸ĸ������õ�����
	String message_ret_modify_user_info_result = "15";//���������ظ��������޸ĵĽ��
	String message_add_friend_request = "16";//�������������Ӻ��ѵ�����
	String message_add_friend_result = "17";//������������Ӻ��ѵĽ��
	String message_file_trans_request = "18";//����������ʹ��ļ�����
	String message_file_trans_response = "19";//��Ӧ�Ƿ�����ļ�����
}
