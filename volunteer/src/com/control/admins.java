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
import com.serivce.userService;
import com.serivce.vo_adminService;

public class admins extends HttpServlet {

	public admins() {
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
		//���root��session
		HttpSession session = request.getSession();
		
		String admin_account=request.getParameter("admin_account");
		String admin_password=request.getParameter("admin_password");
		String decl=request.getParameter("decl");
		
		loginSerive adm = new loginSerive();
		vo_adminService vo=new vo_adminService();
		
		if(decl!=null&&decl.equals("decl"))
		{
			String user_name=request.getParameter("user_name");
			boolean b=vo.vo_deleteUser(user_name);
			if(b) 
			{
				//�û���Ϣ����
				List<Map<String, Object>> res=vo.vo_adminSelectUsers();
				session.setAttribute("rootUsers", res);
				request.getRequestDispatcher("volunteer_admin/vo_rootUser.jsp").forward(request, response);
			}
		}
		//������Ա
		if(admin_account.equals("volunteer") && admin_password.equals("volunteer"))
		{
			//��ѯ־Ը��������Ϣ
			List<Map<String, Object>> re=vo.vo_adminProjectSelect();
			
			session.setAttribute("publishedProject", re);
			
			List<Map<String, Object>> result = adm.loginright_admin();
			session.setAttribute("superAdmin", result);
			session.setAttribute("admin", "superAdmin(��������Ա)");
			
			//�û���Ϣ����
			List<Map<String, Object>> res=vo.vo_adminSelectUsers();
			session.setAttribute("rootUsers", res);
			
			request.getRequestDispatcher("volunteer_admin/vo_root.jsp").forward(request, response);
		}
		//��ͨ����Ա��֤
		boolean b = adm.loginright_admin(admin_account, admin_password);

		if(b)
		{
			session.setAttribute("admin", admin_account);
			
			userService user=new userService();
			List<Map<String,Object>> result = user.loginright_activity(admin_account);
			session.setAttribute("admin_info", result);
			//�鿴��������Ŀ����Ϊ�������ֵ
			vo_adminService voP=new vo_adminService();
			List<Map<String,Object>> reP=voP.vo_adminProjectSelect();
			session.setAttribute("publishedProject", reP);
			
			request.getRequestDispatcher("volunteer_admin/vo_admin.jsp").forward(request, response);
		}
		else
		{
			request.getRequestDispatcher("volunteer_admin/vo_admin_login.jsp").forward(request, response);
		}
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
