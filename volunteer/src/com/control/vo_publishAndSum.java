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

import com.serivce.vo_adminService;


public class vo_publishAndSum extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
    public vo_publishAndSum() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		//ServletContext application = request.getServletContext();
		
		String title = request.getParameter("vo_title");
		String content = request.getParameter("vo_content");
		String volunteer_name = (String)session.getAttribute("volunteer_name");
		int flag=0;
		try
		{
			flag = Integer.parseInt(request.getParameter("flag"));
		}catch(Exception e){}
		String address=null;
		//System.out.println("����"+content);
		Part part = request.getPart("fileName");
		//System.out.println(part);
		//��ȡ�������Ϣ
		String name = part.getHeader("content-disposition");
		//System.out.println(name);
		String root = request.getServletContext().getRealPath("/upload");
		//System.out.println(root);
		//�ж��Ƿ��ϴ���ȷ���ļ�����ͼƬ
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
		vo_adminService vo = new vo_adminService();
		
		//�洢�������ܽ������
		//boolean b = 
		vo.vo_adminUpload(volunteer_name, title, flag, content, address);
		
		//����һЩ��Ϣ
		List<Map<String, Object>> result = vo.vo_adminPublishAndSum(volunteer_name, flag);
		session.setAttribute("vo_publishAndSum", result);
		//�����Ƿ�ɹ�����ת
		request.getRequestDispatcher("volunteer_admin/vo_publishAndSum.jsp").forward(request, response);
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		doGet(request, response);
	}

}
