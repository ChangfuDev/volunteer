package com.control;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.serivce.loginSerive;
import com.serivce.vo_adminService;

public class declc extends HttpServlet {


	public declc() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		
		//ɾ����Ŀ
		String project=request.getParameter("project");
		if(project==null)project="";
		if(project.equals("project"))
		{
			String project_name=request.getParameter("project_name");
			vo_adminService vo=new vo_adminService();
			vo.vo_deleteProject(project_name);
			
			//����ɾ�������Ϣ
			List<Map<String, Object>> result=vo.vo_adminProjectSelect();
			
			session.setAttribute("publishedProject", result);
			request.getRequestDispatcher("volunteer_admin/vo_rootpublished.jsp").forward(request, response);
		}
		
		//ɾ��־Ը�
		String volunteer_name = request.getParameter("volunteer_name");
		
		loginSerive ll=new loginSerive();
		//ɾ������û������־Ը����Ϣ
		ll.loginright_activityDec(volunteer_name);
		//ȡ�أ�ɾ�����º�ģ�����־Ը���Ϣ
		List<Map<String, Object>> result = ll.loginright_admin();
		session.setAttribute("superAdmin", result);
		//����ɾ��ʧ�ܣ�����
		request.getRequestDispatcher("volunteer_admin/vo_root.jsp").forward(request, response);
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		doGet(request,response);
	}

	public void init() throws ServletException {
		// Put your code here
	}

}
