package com.serivce;

import java.util.List;
import java.util.Map;

import com.dao.vo_adminDao;
public class vo_adminService {
	private vo_adminDao vo = new vo_adminDao();
	
	//����Ա�ϴ�
	public boolean vo_adminUpload(String volunteer_name,String vo_title,int flag,String vo_content,String address) {
		
		return vo.vo_adminUpload(volunteer_name,vo_title,flag,vo_content,address);
	}
	//��ѯ�������ܽ����Ϣ
	public List<Map<String,Object>> vo_adminPublishAndSum(String volunteer_name,int flag){
		return vo.vo_adminPublishAndSum(volunteer_name, flag);
	}
	
	public boolean vo_volunteerIsSelect(String volunteer_name,String user_name){
		return vo.vo_volunteerIsSelect(volunteer_name, user_name);
	}
	//������Ա�ϴ�־Ը��Ŀ
	public boolean vo_adminPublishProject(String project_name,String project_organization,String project_contact,String project_classify,String project_introduce,String address1,String address2) {
		
		return vo.vo_adminPublishProject(project_name,project_organization,project_contact,project_classify,project_introduce,address1,address2);
	}
	//�鿴��������Ŀ
	public List<Map<String, Object>> vo_adminProjectSelect(String project_name){
		return vo.vo_adminProjectSelect(project_name);
	}
	//�鿴��������Ŀ������־Ը���Ϣ
		public List<Map<String, Object>> vo_adminProject_VolunteerSelect(String project_name){
			return vo.vo_adminProject_VolunteerSelect(project_name);
		}
	//�鿴���з�������Ŀ
	public List<Map<String, Object>> vo_adminProjectSelect(){
		return vo.vo_adminProjectSelect();
	}
	//ɾ����Ŀ
	public boolean vo_deleteProject(String project_name){
		return vo.vo_deleteProject(project_name);
	}
	//���Ӱ���ֵ
	public boolean vo_addLove(String user_name){
		
		return vo.vo_addLove(user_name);
	}
	//������Ա��ѯ�����û���Ϣ
	public List<Map<String, Object>> vo_adminSelectUsers(){
		
		return vo.vo_adminSelectUsers();
	}
	public boolean vo_deleteUser(String user_name){
		return vo.vo_deleteUser(user_name);
	}
	
}
