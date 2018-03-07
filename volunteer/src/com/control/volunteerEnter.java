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

import com.serivce.volunteerEnterService;

/**
 * Servlet implementation class volunteerEnter
 */
@WebServlet("/volunteerEnter")
public class volunteerEnter extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public volunteerEnter() {
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
		HttpSession session = request.getSession();
		
		String client_name = (String)session.getAttribute("user_info");
		String volunteer_name = request.getParameter("volunteer_name");
		String select_time = request.getParameter("select_time");
		if(select_time==null) select_time="Ĭ��ʱ��";
		if(volunteer_name==null)
		{
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		volunteerEnterService vo = new volunteerEnterService();
		
		boolean b =vo.volunteerright(client_name, volunteer_name,select_time);
		//�����Ƿ�ɹ�������һЩ��Ϣ
		List<Map<String, Object>> result = vo.volunteerselectClient(client_name);
		//System.out.println(result);
		session.setAttribute("clientEn", result);
		
		if(b)
		{
			response.getWriter().println("�����ɹ�����ȴ�֪ͨ");
			//return ;
		}

		else 
		{
			response.getWriter().println("ȡ���ɹ����������ź�");
		}

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
