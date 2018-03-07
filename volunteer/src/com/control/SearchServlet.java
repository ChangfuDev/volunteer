package com.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.serivce.userService;

import net.sf.json.JSONArray;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class SearchServlet extends HttpServlet {
	
	//ģ������
	/*static List<String> datas = new ArrayList<String>();
	static
	{
		
		datas.add("ajax");
		datas.add("־Ը�");
		datas.add("����־Ը�");
		
	}*/

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		//System.out.println("123");
		String keyword = request.getParameter("keyword");
		//System.out.println("hadfj"+keyword);
		List<String> listData = getData(keyword);
		//����json����
		response.getWriter().write(JSONArray.fromObject(listData).toString());
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		doGet(request,response);
	}
	
	//��ù�������
	public List<String> getData(String keyword)
	{
		List<String> list = new ArrayList<String>();
		userService user=new userService();
		List<Map<String,Object>> result = user.loginright_activity();
		
		for(Map<String, Object> obj:result)
		{
			String vname=obj.get("volunteer_name").toString();
			if(vname.contains(keyword))
				list.add(vname);
		}
		
		return list;
	}
}
