package com.dao;

import java.util.List;
import java.util.Map;

import com.util.JDBC;

public class loginDao {
	//client��Ϣ
	public boolean loginright(String name,String pwd){
		
		
		String sql="select user_name, password from client where user_name ='"+name+"' and password='"+pwd+"'";
		//��ѯ
		List<Map<String,Object>> result=JDBC.doQuery(sql);
		if(result!=null && result.size()==1)
		{
			return true;
		}
		else return false;
		
	}
	
	//����Ա��֤
	public boolean loginright_admin(String name,String pwd){
		
		
		String sql="select root_name, root_password from root_info where root_name ='"+name+"' and root_password='"+pwd+"'";
		//��ѯ
		List<Map<String,Object>> result=JDBC.doQuery(sql);
		if(result!=null && result.size()==1)
		{
			return true;
		}
		else return false;
		
	}
	//��ѯ־Ը���Ϣ
	public List<Map<String, Object>> loginright_admin(){
		
		
		String sql="select *from volunteer_activity order by volunteer_id desc";
		//��ѯ
		List<Map<String,Object>> result=JDBC.doQuery(sql);
		
		return result;
	}
	
	public boolean loginright1(String name,String pwd,String college){
		
		//System.out.println(name);
		//System.out.println(pwd);
		//����
		String sql1="INSERT INTO client(user_name,password,user_love,user_college) VALUES ('"+name+"','"+pwd+"',0,'"+college+"')";
		int i=JDBC.doUpdate(sql1);//ִ�гɹ�����1
		if(i==0){
			return false;
		}
		else return true;
	}
	public boolean loginright2(int pwd){
		
		//ɾ��
		String sql2="DELETE FROM client WHERE user_id="+pwd+"";
		int i=JDBC.doUpdate(sql2);
		if(i==1){
			return true;
		}
		else return false;
	}
	//ɾ��־Ը�
	public boolean loginright_activityDec(String name){
		
		//ɾ��
		String sql2="DELETE FROM volunteer_activity WHERE volunteer_name='"+name+"'";
		int i=JDBC.doUpdate(sql2);
		if(i==1){
			//ͬʱɾ����־Ը������־Ը�ߵı�����Ϣ
			String sql = "delete from entry_form where volunteer_name='"+name+"'";
			JDBC.doUpdate(sql);
			return true;
		}
		else return false;
	}
	
	public boolean loginright3(String name,String user_realname,String user_identify,String user_phone,String user_college,String user_college_sdept,String user_classroom,String user_introduce){
		
		//����
		String sql2="UPDATE client SET user_realname='"+user_realname+"', user_identify='"+user_identify+"',user_phone='"+user_phone+"',user_college='"+user_college+"',user_college_sdept='"+user_college_sdept+"',user_classroom='"+user_classroom+"',user_introduce='"+user_introduce+"' WHERE user_name='"+name+"'";
		int i=JDBC.doUpdate(sql2);
		if(i==1){
			return true;
		}
		else return false;

	}
	
	public boolean loginright4(String name,String address){
		
		//����
		String sql2="UPDATE client SET user_picture='"+address+"' WHERE user_name='"+name+"'";
		int i=JDBC.doUpdate(sql2);
		if(i==1){
			return true;
		}
		else return false;

	}
	
	public boolean loginright5(String admin,String name, String project_name, String place,String time,
		String address,String activity_volunteer_number,String activity_introduce,String activity_content){
		//�����µ�־Ը�
		String sql="insert into volunteer_activity(root_name,volunteer_name,project_name,volunteer_place,volunteer_time,"
				+ "volunteer_picture_path,activity_volunteer_number,activity_introduce,activity_content,volunteer_num,volunteer_look_num) "
				+ "values('"+admin+"','"+name+"','"+project_name+"','"+place+"','"+time+"','"+address+"',"
						+ "'"+activity_volunteer_number+"','"+activity_introduce+"','"+activity_content+"',0,0)";
		int i=JDBC.doUpdate(sql);//ִ�гɹ�����1
		if(i==0){
			//����ʧ�����Ը���
			sql="update volunteer_activity set project_name='"+project_name+"',volunteer_place='"+place+"',volunteer_time='"+time+"',"
				+ "volunteer_picture_path='"+address+"',activity_volunteer_number='"+activity_volunteer_number+"',"
						+ "activity_introduce='"+activity_introduce+"',activity_content='"+activity_content+"' "
								+ "where volunteer_name='"+name+"'";
			JDBC.doUpdate(sql);
			
			return false;
		}
		else return true;

	}

}
