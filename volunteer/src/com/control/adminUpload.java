package com.control;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.serivce.loginSerive;
import com.serivce.userService;
import com.serivce.volunteerEnterService;

public class adminUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public adminUpload() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		//session����
		HttpSession session = request.getSession();
		
		String activity_name = request.getParameter("title");
		String project_name = request.getParameter("project_name");
		String activity_place = request.getParameter("place");
		String activity_time = request.getParameter("time");
		String activity_volunteer_number = request.getParameter("number");
		String activity_introduce = request.getParameter("introduce");
		String activity_content = request.getParameter("content");
		String address=null;
		
		String admin = (String)session.getAttribute("admin");
		
		Part part = request.getPart("fileName");
		//System.out.println(part);
		//��ȡ�������Ϣ
		String name = part.getHeader("content-disposition");
		//System.out.println(name);
		String root = request.getServletContext().getRealPath("/upload");
		//System.out.println(root);
		//�ж��Ƿ��ϴ����ļ�����ͼƬ
		if(name.lastIndexOf(".")!=-1)
		{
			String str = name.substring(name.lastIndexOf("."),name.length()-1);
			String filename = root+"\\"+UUID.randomUUID().toString()+str;
			part.write(filename);
			address = "upload"+filename.substring(filename.lastIndexOf("\\"));
			//System.out.println(address);
			session.setAttribute("address", address);
		}
		else
		{
			address = (String)session.getAttribute("address");
		}
		
		//�ϴ�־Ը���Ϣ
		loginSerive ll=new loginSerive();
		boolean b=ll.loginright5(admin,activity_name,project_name,activity_place,activity_time,address,
		activity_volunteer_number,activity_introduce,activity_content);
		//��ѯ��Ӧ����Ա������־Ը���Ϣ
		userService user=new userService();
		List<Map<String,Object>> result = user.loginright_activity(admin);
		session.setAttribute("admin_info", result);
		//System.out.println("hahhasdjk"+result);
		
		//��ѯ��������º�Ļ��Ϣ�������������Ա���Ļ��Ϣ
		volunteerEnterService vo = new volunteerEnterService();
		List<Map<String,Object>> re = vo.volunteerDetailService(activity_name);
		session.setAttribute("detail_activity", re);
		if(b)
		{
			request.getRequestDispatcher("volunteer_admin/vo_admin.jsp").forward(request, response);
		}
		else {
			//session.setAttribute("activity_info", "�Ѿ���������");
			request.getRequestDispatcher("volunteer_admin/vo_activity.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		 
		doGet(request,response);
	}
	public void init() throws ServletException {
		// Put your code here
	}

}
