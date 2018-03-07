package com.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.util.JDBC;

public class volunteerEnterDao {
	
	public boolean volunteerright(String name,String volunteer_name,String select_time){
		
		String sql_1 = "select root_name from volunteer_activity where volunteer_name='"+ volunteer_name +"'";
		List<Map<String,Object>> result=JDBC.doQuery(sql_1);
		String root_name = (String)result.get(0).get("root_name");
		
		//����
		String sql="INSERT INTO entry_form(root_name,volunteer_name,user_name,user_select_time) VALUES ('"+root_name+"','"+volunteer_name+"','"+name+"','"+select_time+"')";
		int i=JDBC.doUpdate(sql);//ִ�гɹ�����1
		if(i==0){
			//��������ɾ����¼
			sql="delete from entry_form where root_name='"+root_name+"' and volunteer_name='"+volunteer_name+"' and user_name='"+name+"'";
			JDBC.doUpdate(sql);
			//ɾ��־Ը�߱�����¼��
			//־Ը��������һ
			sql="update volunteer_activity set volunteer_num=volunteer_num-1 where volunteer_name='"+volunteer_name+"'";
			JDBC.doUpdate(sql);
			return false;
		}
		else
		{
			//־Ը��������һ
			sql="update volunteer_activity set volunteer_num=volunteer_num+1 where volunteer_name='"+volunteer_name+"'";
			JDBC.doUpdate(sql);
			return true;
		}
	}
	
	//admin��ѯ������־Ը����Ϣ
	public List<Map<String,Object>> volunteerselect(String volunteer_name){
		
		String sql = "select *from entry_form join client where entry_form.user_name=client.user_name and volunteer_name='"+volunteer_name+"'";
		
		List<Map<String,Object>> result = JDBC.doQuery(sql);

		return result;
		
	}
	
	//��ѯ־Ը�߸��˵ı�����Ϣ,�������Ⱥ�����
	public List<Map<String,Object>> volunteerselectClient(String name){
			
		String sql = "select *from entry_form join volunteer_activity where entry_form.volunteer_name=volunteer_activity.volunteer_name and user_name='"+name+"' order by user_name desc";
			
		List<Map<String,Object>> result = JDBC.doQuery(sql);

		return result;
			
	}
	//�ղ���Ϣ
	public boolean volunteerCollection(String name,String volunteer_name){
		
		String sql = "insert into collection(user_name,volunteer_name) values('"+name+"','"+ volunteer_name +"')";
	
		//����
		int i=JDBC.doUpdate(sql);//ִ�гɹ�����1
		if(i==0){
			//˵���Ѿ�����
			//ɾ��
			sql="delete from collection where user_name='"+name+"'and volunteer_name='"+volunteer_name+"'";
			JDBC.doUpdate(sql);
			return false;
		}
		else return true;
	}
	//��ѯ־Ը���ղػ��Ϣ
		public List<Map<String,Object>> volunteerSelectCollection(String name){
				
			String sql = "select *from collection join volunteer_activity where collection.volunteer_name=volunteer_activity.volunteer_name and collection.user_name='"+name+"' order by user_name desc";
				
			List<Map<String,Object>> result = JDBC.doQuery(sql);

			return result;
				
		}
		//�ղ���Ŀ��Ϣ
		public boolean volunteerCollectionProject(String name,String project_name){
			
			String sql = "insert into collection_project(user_name,project_name) values('"+name+"','"+ project_name +"')";
		
			//����
			int i=JDBC.doUpdate(sql);//ִ�гɹ�����1
			if(i==0){
				//˵���Ѿ�����
				//ɾ��
				sql="delete from collection_project where user_name='"+name+"'and project_name='"+project_name+"'";
				JDBC.doUpdate(sql);
				return false;
			}
			else return true;
		}
		//��ѯ־Ը���ղ���Ŀ��Ϣ
			public List<Map<String,Object>> volunteerSelectCollectionProject(String name){
					
				String sql = "select *from collection_project join publishproject where collection_project.project_name=publishproject.project_name and user_name='"+name+"' order by user_name desc";
					
				List<Map<String,Object>> result = JDBC.doQuery(sql);

				return result;
					
			}	
		//�����չʾ��Ϣ��ѯ
		public List<Map<String,Object>> volunteerDetail(String volunteer_name){
			
			String sql = "select *from volunteer_activity where volunteer_name='"+volunteer_name+"'";
				
			List<Map<String,Object>> result = JDBC.doQuery(sql);

			return result;
				
		}
		//�ղ���Ϣ
		public boolean volunteerAddlook(String volunteer_name){
			
			String sql = "update volunteer_activity set volunteer_look_num=volunteer_look_num+1 "
					+ "where volunteer_name='"+ volunteer_name +"'";
		
			//����
			int i=JDBC.doUpdate(sql);//ִ�гɹ�����1
			if(i==0){
				return false;
			}
			else return true;
		}
		
		//�鿴�û�Ƿ񷢲����������ܽ�
		public List<Map<String, Object>> volunteerSelectPublishAndSum(String volunteer_name){
			
			String sql="select *from publishandsum where volunteer_name='"+volunteer_name+"'";
			
			List<Map<String, Object>> result=JDBC.doQuery(sql);
			return result;
		}
		/*public List<Map<String, Object>> volunteerSelectPublishAndSum(){
			
			String sql="select *from publishandsum";
			
			List<Map<String, Object>> result=JDBC.doQuery(sql);
			return result;
		}*/
}
