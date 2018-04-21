package com.cmcc.dpi;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

import com.alibaba.fastjson.JSON;

public class Boot_report {
	 static String sql = null;
	 static String inset = null;
	 static DBHelper db1 = null; 
	 static DBHelper db2 = null;
	 static ResultSet ret = null;
	 
	  
	 public static void main(String[] args) {  
		 //��ȡǰһ��ʱ��
		 	Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.DATE, -1); //�õ�ǰһ��
			java.util.Date date = calendar.getTime();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
			DateFormat midnight = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
			System.out.println(df.format(date));
			System.out.println(midnight.format(date));
		//����sql���
	        sql = "select * from boot_report where report_time BETWEEN '"+df.format(date)+"' and '"+midnight.format(date)+"' order by report_time";//SQL���  
	        db1 = new DBHelper(sql);//����DBHelper���� 			

	        try {
	        	//ִ����䣬�õ������
	            ret = db1.pst.executeQuery();  
	            while (ret.next()) {
	            	String id = ret.getString(1);
	                String deviceId = ret.getString(2);  
	                String manufacturer = ret.getString(3);  
	                String model = ret.getString(4);  
	                String report_time = ret.getString(5);
	                String information = ret.getString(6);
	                String insert = "INSERT INTO boot_error VALUES('" + deviceId + "','" + report_time + "','" + information + "','cpu')";
	                System.out.println(deviceId + "\t" + report_time + "\t" + information );  
	                try {
	                    Map<String,Object> mapData = JSON.parseObject(information, Map.class);
	                    if(null != mapData && mapData.size()>0){
	                    	String str = String.valueOf(mapData.get("deviceStatus"));
	                    	if(!str.isEmpty() && !"null".equalsIgnoreCase(str)){
	                    		mapData = JSON.parseObject(str, Map.class);
	                    		if(null != mapData && mapData.size()>0){
	                    			 System.out.println("mapData: "+mapData);
	                    			 String CPU = String.valueOf(mapData.get("CPU"));
	                    			 String RAM = String.valueOf(mapData.get("RAM"));
	                    			 System.out.println("CPU: "+CPU);
	                    			 System.out.println("RAM: "+RAM);
	                    			 int cpu = Integer.parseInt(CPU);
	                    			 if(cpu>10){
	                    				 String info = "CPU���ع��ߣ�";
	                    				 System.out.println(deviceId);
	                 	                // String insert = "INSERT INTO boot_error VALUES(deviceId,report_time,information,'cpu')";
	                	                 db2 = new DBHelper(insert);	                   				 	 
	                   				 	 db2.pst.execute(insert);
	                    				
	                    				 System.out.println(insert);
	                    				 System.out.println("CPU���ع��ߣ�");
	                    			 }	 
	                    		}
	                    	}
	                    }   
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	            }//��ʾ����  
	          
	            ret.close();  
	            db1.close();//�ر�����  
	        } catch (SQLException e) {  
	            e.printStackTrace();  
	        }  

	 }	            
}
	 
