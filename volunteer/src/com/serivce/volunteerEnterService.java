package com.serivce;

import java.util.List;
import java.util.Map;

import com.dao.volunteerEnterDao;

public class volunteerEnterService {
	private volunteerEnterDao vo = new volunteerEnterDao();
	
	public boolean volunteerright(String name,String volunteer_name,String select_time) {
		
		return vo.volunteerright(name, volunteer_name,select_time);
	}
	
	//admin��ѯ������Ϣ
	public List<Map<String, Object>> volunteerselect(String volunteer_name){
		
		return vo.volunteerselect(volunteer_name);
	}
	/*//��ı�������
	public int volunteerNumber(String volunteer_name){
		
		return vo.volunteerNumber(volunteer_name);
	}*/
	//client��ѯ������Ϣ
	public List<Map<String, Object>> volunteerselectClient(String name){
			
		return vo.volunteerselectClient(name);
	}
	//client�ղ���Ϣ
	public boolean volunteerCollection(String name,String volunteer_name) {
		
		return vo.volunteerCollection(name, volunteer_name);
	}
	//��ѯclient�ղ���Ϣ
	public List<Map<String, Object>> volunteerSelectCollection(String name) {
		
		return vo.volunteerSelectCollection(name);
	}
	//client�ղ���Ŀ��Ϣ
		public boolean volunteerCollectionProject(String name,String project_name) {
			
			return vo.volunteerCollectionProject(name, project_name);
		}
		//��ѯclient�ղ���Ŀ��Ϣ
		public List<Map<String, Object>> volunteerSelectCollectionProject(String name) {
			
			return vo.volunteerSelectCollectionProject(name);
		}
	//�չʾ��Ϣ��ѯ
	public List<Map<String,Object>> volunteerDetailService(String volunteer_name){
		
		return vo.volunteerDetail(volunteer_name);
			
	}
	
	//�����
	public boolean volunteerAddlook(String volunteer_name){
		
		return vo.volunteerAddlook(volunteer_name);
			
	}
	//�鿴�����ܽ�
	public List<Map<String, Object>> volunteerSelectPublishAndSum(String volunteer_name){
		
		return vo.volunteerSelectPublishAndSum(volunteer_name);
	}
	/*//�鿴�����ܽ�
		public List<Map<String, Object>> volunteerSelectPublishAndSum(){
			
			return vo.volunteerSelectPublishAndSum();
		}*/
}
