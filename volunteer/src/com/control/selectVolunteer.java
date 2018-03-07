package com.control;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.serivce.vo_adminService;
import com.serivce.volunteerEnterService;

/**
 * Servlet implementation class selectVolunteer
 */
@WebServlet("/selectVolunteer")
public class selectVolunteer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public selectVolunteer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		HttpSession session=request.getSession();
		
		vo_adminService vo = new vo_adminService();
		
		//���Ӱ���ֵ
		String add=request.getParameter("add");
		if(add==null)add="";
		if(add.equals("add"))
		{
			String[] user_name = request.getParameterValues("productId2");
			
			//System.out.println(user_name);
			for(int i=0;i<user_name.length;i++)
			{
				vo.vo_addLove(user_name[i]);
			}
			
			//����
			request.getRequestDispatcher("volunteer_admin/manage_select.jsp").forward(request, response);;
		}
		
		
		String[] user = request.getParameterValues("productId");
	
		//����ɵð�˰ɣ��㲻��ѡ��ѡ�򣬵�Ȼ�õ���ֵΪ��������һֱ�������ţ���~
		//System.out.println(user_name);
		for(int i=0;i<user.length;i++)
		{
			String str_t=user[i];
			int p=str_t.indexOf('$');
			String user_name=str_t.substring(0,p);
			String volunteer_name=str_t.substring(p+1);
			vo.vo_volunteerIsSelect(volunteer_name, user_name);
		}
		
		//��ѯѡ�е���Ϣ
		volunteerEnterService vol = new volunteerEnterService();
		//admin��ѯ������Ϣ
		String volunteer_name=(String)session.getAttribute("volunteer_name");
		List<Map<String, Object>> result = vol.volunteerselect(volunteer_name);
		
		session.setAttribute("volunteerInfo", result);
		request.getRequestDispatcher("volunteer_admin/manage_select.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		doGet(request, response);
	}

}
