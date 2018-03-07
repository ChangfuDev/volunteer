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
 * Servlet implementation class detail_activity
 */
@WebServlet("/detail_activity")
public class detail_activity extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public detail_activity() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		HttpSession session=request.getSession();
		//ServletContext application = this.getServletContext();
		volunteerEnterService vo = new volunteerEnterService();
		
		String volunteer_name = request.getParameter("volunteer_name");
		String user_look = request.getParameter("user_look");//�û����
		String detail = request.getParameter("detail");//����Ա���
		//String edit = request.getParameter("edit");//����Ա���
		//��־��user_look
		//������ø���λuser_look��ֵ
		//����Ϊ�գ�null���Ƕ����ܽ���equals�Ƚ�
		if(user_look==null)user_look="";
		//System.out.println("����"+user_look);
		if(detail==null)detail="";
		//if(edit==null)edit="";
		
		
		List<Map<String,Object>> result = vo.volunteerDetailService(volunteer_name);
		
		session.setAttribute("detail_activity", result);
		
		if(user_look.equals("user_look"))
		{
			//���������
			vo.volunteerAddlook(volunteer_name);
			//�����ܽ�
			List<Map<String,Object>> re=publishAndSum(volunteer_name);
			//System.out.println(re);
			session.setAttribute("publishAndSum", re);
			//**�����ڲ�һ��Ϊ��
			if(re!=null&&re.size()!=0) request.getRequestDispatcher("publishAndSum.jsp").forward(request, response);
			
			request.getRequestDispatcher("detail_activity.jsp").forward(request, response);
		}
		
		if(detail.equals("detail"))
		{
			request.getRequestDispatcher("volunteer_admin/vo_activity.jsp").forward(request, response);
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
	
	public List<Map<String, Object>> publishAndSum(String volunteer_name){
		volunteerEnterService vo = new volunteerEnterService();
		//�鿴�Ƿ񷢲����������ܽ���Ϣ��������ת����Ӧҳ��
		List<Map<String, Object>> re=vo.volunteerSelectPublishAndSum(volunteer_name);
		
		return re;
	}

}
