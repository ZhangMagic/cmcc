package com.cmcc.dpi;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;


//检测软探针开机上报数量
public class Report_count {
	 static String boot = null;
	 static DBHelper db1 = null;
	 static String periodic = null;
	 static DBHelper db2 = null;
	 static String sn = null;
	 static DBHelper db3 = null;
	 static ResultSet ret = null;
	 
	 
	  
	 public static void main(String[] args) {  
		 	//获取前一天时间
		 	Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.DATE, -1); //得到前一天
			java.util.Date date = calendar.getTime();
			DateFormat day = new SimpleDateFormat("yyyy-MM-dd");
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
			DateFormat midnight = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
			//System.out.println(df.format(date));
			//System.out.println(midnight.format(date));
			//定义sql语句
			boot = "select deviceId,count(*) from boot_report where report_time BETWEEN '"+df.format(date)+"' and '"+midnight.format(date)+"' group by deviceId ";//SQL语句  
	        db1 = new DBHelper(boot);//创建DBHelper对象 
	        periodic = "select deviceId,count(*) from periodic_report where report_time BETWEEN '"+df.format(date)+"' and '"+midnight.format(date)+"' group by deviceId ";//SQL语句  
	        db2 = new DBHelper(periodic);//创建DBHelper对象 
	        sn = "SELECT DISTINCT deviceId FROM periodic_report";
	        db3 = new DBHelper(sn);
	        //System.out.println(boot);
	        //System.out.println(periodic);
	        try {
	        	//执行语句，得到结果集
	            ret = db1.pst.executeQuery();  
	            while (ret.next()) {
	            	String deviceId = ret.getString(1);
	                String num = ret.getString(2);  	                
	                //System.out.println(deviceId + "\t" + num ); 
	                int boot_id = Integer.parseInt(num);
	                if(boot_id>1){
       				 String info = "软探针开机上报异常！";
       				 System.out.println(deviceId+":"+day.format(date)+info+"	异常次数"+boot_id);       				 
       		            }
       		        } 
    
	          
	        	//执行语句，得到结果集
	            System.out.println("一天正常的周期上报次数为288次");
	            ret = db2.pst.executeQuery();  
	            while (ret.next()) {
	            	String deviceId = ret.getString(1);
	                String num = ret.getString(2);  	                
	                //System.out.println(deviceId + "\t" + num ); 
	                System.out.println(deviceId+":"+day.format(date)+"	上报次数"+num); 
	                /*if(periodic_id<288){
	                	String info = "软探针周期上报异常！";
	       				System.out.println(deviceId+":"+day.format(date)+info+"	上报次数"+periodic_id);       				 
       		            }*/
       		        } 
	            System.out.println("周期上报中的网关");
	            ret = db3.pst.executeQuery();  
	            while (ret.next()) {
	            	String deviceId = ret.getString(1);              
	                //System.out.println(deviceId + "\t" + num ); 
	                System.out.println(deviceId); 
	            //显示数据 
	            }
	            ret.close();  
	            db1.close();
	            db2.close();
	            db3.close();
	        }
	        
	            //关闭连接  
	            catch (SQLException e) {  
	            e.printStackTrace();  
	        }  

	 }
}

	 
