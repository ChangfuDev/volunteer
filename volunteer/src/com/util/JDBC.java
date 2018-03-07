package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JDBC {

	public static final String url = "jdbc:mysql://localhost:3306/volunteer?characterEncoding=UTF-8";
	public static final String name = "root";
	public static final String password = "123456";	
	public static final String classname = "com.mysql.jdbc.Driver";

	private static Connection getConnection()//��ȡ����
	{

		try {
			//1 ��������
			Class.forName(classname);
			//2 ��ȡ����
			Connection con = DriverManager.getConnection(url, name, password);
			return con;
		} catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}		
	}



	public static List<Map<String,Object>> doQuery(String sql)  //ִ�в�ѯ
	{
		List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
		
		//1 ��ȡ����
		Connection con = getConnection();
		if(con==null)
			return null;
		
		Statement psm = null;
		ResultSet rs = null;
		try 
		{
			//2  ִ�в�ѯ 
			//����ִ�о�̬sql����ѯ��statement����~
			//��~�������õ��Ķ��Ǿ�̬sql��~
			psm = con.createStatement();		
			rs = psm.executeQuery(sql); 
			ResultSetMetaData rsmd = rs.getMetaData();//��ȡ����Ϣ
			int numberOfColumns =rsmd.getColumnCount();//����
			while(rs.next()){
				//����һ���������һ�����ݵ���Ϣ�� �������ݿ϶���һ��   �к�ֵ ��map����  ����
				Map<String,Object> rsTree = new HashMap<String,Object>();
				//��ÿһ�н���ѭ��
				for(int i =1 ;i<=numberOfColumns ;i++){
					//��Ŷ�Ӧ  ���� ��ֵ
					rsTree.put(rsmd.getColumnName(i), rs.getObject(i));
				}
				resultList.add(rsTree);
			}
			return resultList;
		}
		catch(Exception ex)
		{
			return null;
		}finally{
			close(con,psm,null);
		}
	}

	/**
	 * ����  �޸�  ɾ�� ����ͨ��sql ,0��ʾ�ɹ�
	 * @param sql
	 * @param paras
	 * @return
	 */
	public static int doUpdate(String sql)
	{
		Connection con = getConnection();
		if(con==null)
			return 0;

		Statement psm = null;
		try 
		{
			psm = con.createStatement();			
			int result = psm.executeUpdate(sql);
			return result;
		}
		catch(Exception ex)
		{
			return 0;
		}	
		finally
		{
			close(con,psm,null);
		}
	}

	//�ر�����
	private static void close(Connection con,Statement sm,ResultSet rs)
	{
		try {
			//5 �رա������ȡ�Ķ����ȹرա�
			if (rs != null)
				rs.close();
			if (sm != null)
				sm.close();
			if (con != null)
				con.close();
		} catch (Exception e2) 
		{
			
		}		
	}
	
}
