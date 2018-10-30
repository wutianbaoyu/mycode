package dao;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.nullValue;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.apache.commons.collections15.map.CaseInsensitiveMap;
import org.h2.command.dml.Update;
import org.spongycastle.asn1.x509.qualified.QCStatement;

import java.sql.Statement;
import java.sql.Types;
import java.text.NumberFormat;

import db.DBUtil;
import edu.uci.ics.jung.algorithms.scoring.PageRankWithPriors;
import jadex.bdiv3.runtime.impl.RProcessableElement.State;
import jadex.bridge.service.types.threadpool.IDaemonThreadPoolService;
import jadex.rules.rulesystem.rules.AndCondition;
import model.Goddess;
import model.i;
import dao.ycDao;

public class GoddessDao {

	public void addGoddess(Goddess g) throws Exception{
		Connection conn=DBUtil.getConnection();
		String sql="" +
				"insert into zero" +
				"(chusanbai,chusanshi,chusange)" +
				"values(" +
				"?,?,?)";
		PreparedStatement ptmt=conn.prepareStatement(sql);
		
		ptmt.setInt(1, g.getBai());
		ptmt.setInt(2, g.getShi());
		ptmt.setInt(3, g.getGe());
		ptmt.execute();
	}
	
	public void readGoddess(Goddess g)throws Exception{
		Connection conn=DBUtil.getConnection();
		Statement stm=conn.createStatement();
		ResultSet rs=stm.executeQuery("select * from zero");
		ResultSetMetaData rsmd=rs.getMetaData();
		String[] cols=new String[rsmd.getColumnCount()];
		int len=cols.length;
		for(int i=0;i<len;i++){
			cols[i]=rsmd.getColumnName(i+1);
		}
		PrintWriter pw=new PrintWriter(new FileWriter("zero"),true);
		while(rs.next()){
			for(int i=0;i<4;i++){
				System.out.print("\t\t<"+cols[i]+">");
				StringBuffer sb=new StringBuffer("<"+cols[i]+">");
				System.out.print(getString2(rsmd,rs,i+1).trim());
				sb.append(getString2(rsmd,rs,i+1).trim());
				System.out.println("</"+cols[i]+">");
				sb.append("</"+cols[i]+">");
				pw.println(sb.toString());
			}
		}
	}

	public static String getString2(ResultSetMetaData rsmd,ResultSet rs,
			int colIndex)throws SQLException{
		String result=null;
		int type=rsmd.getColumnType(colIndex);
		switch(type){
		case Types.CHAR:
		case Types.VARCHAR:
		case Types.LONGNVARCHAR:
			result=rs.getString(colIndex);
			break;
		case Types.NUMERIC:
		case Types.DECIMAL:
			result=""+rs.getBigDecimal(colIndex);
			break;
		case Types.INTEGER:
			result=""+rs.getInt(colIndex);
			break;
		case Types.FLOAT:
		case Types.DOUBLE:
			result=""+rs.getDouble(colIndex);
			break;
		case Types.BIT:
			result=""+rs.getBoolean(colIndex);
			break;
		case Types.SMALLINT:
			result=""+rs.getShort(colIndex);
			break;
		case Types.TINYINT:
			result=""+rs.getByte(colIndex);
			break;
		case Types.BIGINT:
			result=""+rs.getLong(colIndex);
			break;
		case Types.REAL:
			result=""+rs.getFloat(colIndex);
			break;
		case Types.DATE:
			result=""+rs.getDate(colIndex);
			break;
		case Types.TIME:
			result=""+rs.getTime(colIndex);
			break;
		case Types.TIMESTAMP:
			result=""+rs.getTimestamp(colIndex);
			break;
		default:
			throw new RuntimeException("Not supported data type");
		}
		return result;
	}
	public void getBai(Goddess g)throws Exception{
		Connection conn=DBUtil.getConnection();
		Statement stm=conn.createStatement();
		ResultSet rs=stm.executeQuery("select * from zero");
		ResultSetMetaData rsmd=rs.getMetaData();
		String[] cols=new String[rsmd.getColumnCount()];
		int len=cols.length;
		for(int i=0;i<len;i++){
			cols[i]=rsmd.getColumnName(i+1);
		}
		PrintWriter pw=new PrintWriter(new FileWriter("zero"),true);
		while(rs.next()){
			for(int i=0;i<1;i++){
				System.out.print("\t\t<"+cols[i]+">");
				StringBuffer sb=new StringBuffer("<"+cols[i]+">");
				System.out.print(getString2(rsmd,rs,i+1).trim());
				sb.append(getString2(rsmd,rs,i+1).trim());
				System.out.println("</"+cols[i]+">");
				sb.append("</"+cols[i]+">");
				pw.println(sb.toString());
			}
		}
	}

	
	public void getcolsone(Goddess g)throws Exception{
		 try {
		   Connection conn = DBUtil.getConnection();//建立connection
		   Statement stmt = conn.createStatement();
		   PreparedStatement pre=null;
		   conn.setAutoCommit(false);// 更改jdbc事务的默认提交方式 
		   String sql = "select * from zero";//查询语句
		   ResultSet rs = stmt.executeQuery(sql);//得到结果集
		   conn.commit();//事务提交
		   conn.setAutoCommit(true);// 更改jdbc事务的默认提交方式 
		   List<String> list=new ArrayList<String>();//创建取结果的列表，之所以使用列表，不用数组，因为现在还不知道结果有多少，不能确定数组长度，所有先用list接收，然后转为数组
		   while (rs.next()) {//如果有数据，取第一列添加如list
		    list.add(rs.getString(1));
		   }
		   if(list != null && list.size()>0){//如果list中存入了数据，转化为数组
		    String[] arr=new String[list.size()];//创建一个和list长度一样的数组
		    for(int i=0;i<list.size();i++){
		     arr[i]=list.get(i);//数组赋值了。
		    }
		       //输出数组
		       for(int i=0;i<arr.length;i++){
		    	 
		    	   if(i<11){
		    		   for(int q=i;q>0;q--){ //应该计入q(i)个数进首行,q<256
		    		  String stringq=String.valueOf(q);

				      String shouhang="";                  //清空首行
		    		  if(validateTableNameExist("a"+stringq)==false){
		    			  //如果table q 不存在,创建一个新table q（i代表前面有i个数）
		    			
		    			  String sql2="CREATE TABLE "+"a"+stringq
		    					  +"(shouhang TEXT,"+
		    					  "jieguo TEXT,shenglv int(5))";
		    			  stmt.executeUpdate(sql2);
		    		  }
	
		    		  


			    		  for(int j=(i-1);(i-1)-j<q;j--){                  //这个for记录0到i-1，即起点数
			    			  
			    			  shouhang +=arr[j];		//赋值不同q下的首行
			    		  }
			    		  
			    	String sqlselect="select count(*) from "+"a"+stringq+" where shouhang='"+shouhang+"'"+"and jieguo="+"'"+arr[i]+"'";
			    	
			    	try { 
			    		ResultSet rs2 = stmt.executeQuery(sqlselect); 
			    		int count = 0; 
			    		while (rs2.next()) { 
			    		count = rs2.getInt(1); 
			    		} 
			    		if(count == 0) { 
			    		//没同样的记录,该干什么干什么 
			    			pre=conn.prepareStatement("insert into a"+stringq+"(shouhang,jieguo,shenglv) values (?,?,?)");
			       		    pre.setString(1, shouhang);
			       		    pre.setString(2, arr[i]);
			       		    pre.setInt(3, 0);
			       		    pre.executeUpdate();	
			    		} else { 
			    		//有同样的记录,该干什么干什么 
			    		//首先读取胜率的数据，然后进行updatae修改
			    			
			    		String sql4="UPDATE "+"a"+stringq+" SET shenglv =shenglv+1 WHERE shouhang='"+shouhang+"'"+"and jieguo="+"'"+arr[i]+"'";
			    		stmt.executeUpdate(sql4);
			    		} 
			    	} catch (SQLException e) { 
			    	e.printStackTrace(); 
			    	}	
			    		  

		    	  }	
		    	   }
		    	    
  
		    	   if(i>10){
		    		   for(int q=10;q>0;q--){ //应该计入q(i)个数进首行,q<256
				    		  String stringq=String.valueOf(q);

						      String shouhang="";                  //清空首行
				    		  if(validateTableNameExist("a"+stringq)==false){
				    			  //如果table q 不存在,创建一个新table q（i代表前面有i个数）
				    			
				    			  String sql2="CREATE TABLE "+"a"+stringq
				    					  +"(shouhang TEXT,"+
				    					  "jieguo TEXT,shenglv int(5))";
				    			  stmt.executeUpdate(sql2);
				    		  }
			
				    		  


					    		  for(int j=(i-1);(i-1)-j<q;j--){                  //这个for记录0到i-1，即起点数
					    			  
					    			  shouhang +=arr[j];		//赋值不同q下的首行
					    		  }
					    		  
					    	String sqlselect="select count(*) from "+"a"+stringq+" where shouhang='"+shouhang+"'"+"and jieguo="+"'"+arr[i]+"'";
					    	
					    	try { 
					    		ResultSet rs2 = stmt.executeQuery(sqlselect); 
					    		int count = 0; 
					    		while (rs2.next()) { 
					    		count = rs2.getInt(1); 
					    		} 
					    		if(count == 0) { 
					    		//没同样的记录,该干什么干什么 
					    			pre=conn.prepareStatement("insert into a"+stringq+"(shouhang,jieguo,shenglv) values (?,?,?)");
					       		    pre.setString(1, shouhang);
					       		    pre.setString(2, arr[i]);
					       		    pre.setInt(3, 0);
					       		    pre.executeUpdate();	
					    		} else { 
					    		//有同样的记录,该干什么干什么 
					    		//首先读取胜率的数据，然后进行updatae修改
					    			
					    		String sql4="UPDATE "+"a"+stringq+" SET shenglv =shenglv+1 WHERE shouhang='"+shouhang+"'"+"and jieguo="+"'"+arr[i]+"'";
					    		stmt.executeUpdate(sql4);
					    		} 
					    	} catch (SQLException e) { 
					    	e.printStackTrace(); 
					    	}	
					    		  

				    	  }	
		    		   
		    	   }
		    	  
		    	  
		       System.out.println(arr[i]+"   baiwei:  "+i);
		     }
		       

		   }
		   
		  } catch (Exception e) {
		   // TODO Auto-generated catch block   
		   e.printStackTrace();
		  }
		}
	
	public boolean validateTableNameExist(String tableName) throws SQLException {  
        Connection con = DBUtil.getConnection(); 
        ResultSet rs = con.getMetaData().getTables(null, null, tableName, null);  
        if (rs.next()) {  
              return true;  
        }else {  
              return false;  
        }  
    } 
	

	public void getcolstwo(Goddess g)throws Exception{
		 try {
		   Connection conn = DBUtil.getConnection();//建立connection
		   Statement stmt = conn.createStatement();
		   PreparedStatement pre=null;
		   conn.setAutoCommit(false);// 更改jdbc事务的默认提交方式 
		   String sql = "select * from zero";//查询语句
		   ResultSet rs = stmt.executeQuery(sql);//得到结果集
		   conn.commit();//事务提交
		   conn.setAutoCommit(true);// 更改jdbc事务的默认提交方式 
		   List<String> list=new ArrayList<String>();//创建取结果的列表，之所以使用列表，不用数组，因为现在还不知道结果有多少，不能确定数组长度，所有先用list接收，然后转为数组
		   while (rs.next()) {//如果有数据，取第一列添加如list
		    list.add(rs.getString(2));
		   }
		   if(list != null && list.size()>0){//如果list中存入了数据，转化为数组
		    String[] arr=new String[list.size()];//创建一个和list长度一样的数组
		    for(int i=0;i<list.size();i++){
		     arr[i]=list.get(i);//数组赋值了。
		    }
		       //输出数组
		       for(int i=0;i<arr.length;i++){
		    	   if(i<11){
		    		   for(int q=i;q>0;q--){ //应该计入q(i)个数进首行,q<256
				    		  String stringq=String.valueOf(q);

						      String shouhang="";                  //清空首行
				    		  if(validateTableNameExist("b"+stringq)==false){
				    			  //如果table q 不存在,创建一个新table q（i代表前面有i个数）
				    			
				    			  String sql2="CREATE TABLE "+"b"+stringq
				    					  +"(shouhang TEXT,"+
				    					  "jieguo TEXT,shenglv int(5))";
				    			  stmt.executeUpdate(sql2);
				    		  }
			
				    		  


					    		  for(int j=(i-1);(i-1)-j<q;j--){                  //这个for记录0到i-1，即起点数
					    			  
					    			  shouhang +=arr[j];		//赋值不同q下的首行
					    		  }
					    		  
					    	String sqlselect="select count(*) from "+"b"+stringq+" where shouhang='"+shouhang+"'"+"and jieguo="+"'"+arr[i]+"'";
					    	
					    	try { 
					    		ResultSet rs2 = stmt.executeQuery(sqlselect); 
					    		int count = 0; 
					    		while (rs2.next()) { 
					    		count = rs2.getInt(1); 
					    		} 
					    		if(count == 0) { 
					    		//没同样的记录,该干什么干什么 
					    			pre=conn.prepareStatement("insert into b"+stringq+"(shouhang,jieguo,shenglv) values (?,?,?)");
					       		    pre.setString(1, shouhang);
					       		    pre.setString(2, arr[i]);
					       		    pre.setInt(3, 0);
					       		    pre.executeUpdate();	
					    		} else { 
					    		//有同样的记录,该干什么干什么 
					    		//首先读取胜率的数据，然后进行updatae修改
					    			
					    		String sql4="UPDATE "+"b"+stringq+" SET shenglv =shenglv+1 WHERE shouhang='"+shouhang+"'"+"and jieguo="+"'"+arr[i]+"'";
					    		stmt.executeUpdate(sql4);
					    		} 
					    	} catch (SQLException e) { 
					    	e.printStackTrace(); 
					    	}	
					    		  

				    	  }
		    	   }
		    	  if(i>10){
		    		  for(int q=10;q>0;q--){ //应该计入q(i)个数进首行,q<256
			    		  String stringq=String.valueOf(q);

					      String shouhang="";                  //清空首行
			    		  if(validateTableNameExist("b"+stringq)==false){
			    			  //如果table q 不存在,创建一个新table q（i代表前面有i个数）
			    			
			    			  String sql2="CREATE TABLE "+"b"+stringq
			    					  +"(shouhang TEXT,"+
			    					  "jieguo TEXT,shenglv int(5))";
			    			  stmt.executeUpdate(sql2);
			    		  }
		
			    		  


				    		  for(int j=(i-1);(i-1)-j<q;j--){                  //这个for记录0到i-1，即起点数
				    			  
				    			  shouhang +=arr[j];		//赋值不同q下的首行
				    		  }
				    		  
				    	String sqlselect="select count(*) from "+"b"+stringq+" where shouhang='"+shouhang+"'"+"and jieguo="+"'"+arr[i]+"'";
				    	
				    	try { 
				    		ResultSet rs2 = stmt.executeQuery(sqlselect); 
				    		int count = 0; 
				    		while (rs2.next()) { 
				    		count = rs2.getInt(1); 
				    		} 
				    		if(count == 0) { 
				    		//没同样的记录,该干什么干什么 
				    			pre=conn.prepareStatement("insert into b"+stringq+"(shouhang,jieguo,shenglv) values (?,?,?)");
				       		    pre.setString(1, shouhang);
				       		    pre.setString(2, arr[i]);
				       		    pre.setInt(3, 0);
				       		    pre.executeUpdate();	
				    		} else { 
				    		//有同样的记录,该干什么干什么 
				    		//首先读取胜率的数据，然后进行updatae修改
				    			
				    		String sql4="UPDATE "+"b"+stringq+" SET shenglv =shenglv+1 WHERE shouhang='"+shouhang+"'"+"and jieguo="+"'"+arr[i]+"'";
				    		stmt.executeUpdate(sql4);
				    		} 
				    	} catch (SQLException e) { 
				    	e.printStackTrace(); 
				    	}	
				    		  

			    	  }
		    	  }
		    	  	  
 
		       System.out.println(arr[i]+"   shiwei:  "+i);
		     }
		       

		   }
		   
		  } catch (Exception e) {
		   // TODO Auto-generated catch block   
		   e.printStackTrace();
		  }
		}
		
	public void getcolsthree(Goddess g)throws Exception{
		 try {
		   Connection conn = DBUtil.getConnection();//建立connection
		   Statement stmt = conn.createStatement();
		   PreparedStatement pre=null;
		   conn.setAutoCommit(false);// 更改jdbc事务的默认提交方式 
		   String sql = "select * from zero";//查询语句
		   ResultSet rs = stmt.executeQuery(sql);//得到结果集
		   conn.commit();//事务提交
		   conn.setAutoCommit(true);// 更改jdbc事务的默认提交方式 
		   List<String> list=new ArrayList<String>();//创建取结果的列表，之所以使用列表，不用数组，因为现在还不知道结果有多少，不能确定数组长度，所有先用list接收，然后转为数组
		   while (rs.next()) {//如果有数据，取第一列添加如list
		    list.add(rs.getString(3));
		   }
		   if(list != null && list.size()>0){//如果list中存入了数据，转化为数组
		    String[] arr=new String[list.size()];//创建一个和list长度一样的数组
		    for(int i=0;i<list.size();i++){
		     arr[i]=list.get(i);//数组赋值了。
		    }
		       //输出数组
		       for(int i=0;i<arr.length;i++){
		     if(i<11){
		    	 for(int q=i;q>0;q--){ //应该计入q(i)个数进首行,q<256
		    		  String stringq=String.valueOf(q);

				      String shouhang="";                  //清空首行
		    		  if(validateTableNameExist("c"+stringq)==false){
		    			  //如果table q 不存在,创建一个新table q（i代表前面有i个数）
		    			
		    			  String sql2="CREATE TABLE "+"c"+stringq
		    					  +"(shouhang TEXT,"+
		    					  "jieguo TEXT,shenglv int(5))";
		    			  stmt.executeUpdate(sql2);
		    		  }
	
		    		  


			    		  for(int j=(i-1);(i-1)-j<q;j--){                  //这个for记录0到i-1，即起点数
			    			  
			    			  shouhang +=arr[j];		//赋值不同q下的首行
			    		  }
			    		  
			    	String sqlselect="select count(*) from "+"c"+stringq+" where shouhang='"+shouhang+"'"+"and jieguo="+"'"+arr[i]+"'";
			    	
			    	try { 
			    		ResultSet rs2 = stmt.executeQuery(sqlselect); 
			    		int count = 0; 
			    		while (rs2.next()) { 
			    		count = rs2.getInt(1); 
			    		} 
			    		if(count == 0) { 
			    		//没同样的记录,该干什么干什么 
			    			pre=conn.prepareStatement("insert into c"+stringq+"(shouhang,jieguo,shenglv) values (?,?,?)");
			       		    pre.setString(1, shouhang);
			       		    pre.setString(2, arr[i]);
			       		    pre.setInt(3, 0);
			       		    pre.executeUpdate();	
			    		} else { 
			    		//有同样的记录,该干什么干什么 
			    		//首先读取胜率的数据，然后进行updatae修改
			    			
			    		String sql4="UPDATE "+"c"+stringq+" SET shenglv =shenglv+1 WHERE shouhang='"+shouhang+"'"+"and jieguo="+"'"+arr[i]+"'";
			    		stmt.executeUpdate(sql4);
			    		} 
			    	} catch (SQLException e) { 
			    	e.printStackTrace(); 
			    	}	
			    		  

		    	  }
		     }
		     
		     if(i>10){
		    	 for(int q=10;q>0;q--){ //应该计入q(i)个数进首行,q<256
		    		  String stringq=String.valueOf(q);

				      String shouhang="";                  //清空首行
		    		  if(validateTableNameExist("c"+stringq)==false){
		    			  //如果table q 不存在,创建一个新table q（i代表前面有i个数）
		    			
		    			  String sql2="CREATE TABLE "+"c"+stringq
		    					  +"(shouhang TEXT,"+
		    					  "jieguo TEXT,shenglv int(5))";
		    			  stmt.executeUpdate(sql2);
		    		  }
	
		    		  


			    		  for(int j=(i-1);(i-1)-j<q;j--){                  //这个for记录0到i-1，即起点数
			    			  
			    			  shouhang +=arr[j];		//赋值不同q下的首行
			    		  }
			    		  
			    	String sqlselect="select count(*) from "+"c"+stringq+" where shouhang='"+shouhang+"'"+"and jieguo="+"'"+arr[i]+"'";
			    	
			    	try { 
			    		ResultSet rs2 = stmt.executeQuery(sqlselect); 
			    		int count = 0; 
			    		while (rs2.next()) { 
			    		count = rs2.getInt(1); 
			    		} 
			    		if(count == 0) { 
			    		//没同样的记录,该干什么干什么 
			    			pre=conn.prepareStatement("insert into c"+stringq+"(shouhang,jieguo,shenglv) values (?,?,?)");
			       		    pre.setString(1, shouhang);
			       		    pre.setString(2, arr[i]);
			       		    pre.setInt(3, 0);
			       		    pre.executeUpdate();	
			    		} else { 
			    		//有同样的记录,该干什么干什么 
			    		//首先读取胜率的数据，然后进行updatae修改
			    			
			    		String sql4="UPDATE "+"c"+stringq+" SET shenglv =shenglv+1 WHERE shouhang='"+shouhang+"'"+"and jieguo="+"'"+arr[i]+"'";
			    		stmt.executeUpdate(sql4);
			    		} 
			    	} catch (SQLException e) { 
			    	e.printStackTrace(); 
			    	}	
			    		  

		    	  }
		     }
		    	  	  
 
		       System.out.println(arr[i]+"   gewei:  "+i);
		     }
		       

		   }
		   
		  } catch (Exception e) {
		   // TODO Auto-generated catch block   
		   e.printStackTrace();
		  }
		}
	
	public void yc(Goddess g)throws Exception{
		 try {
		   Connection conn = DBUtil.getConnection();//建立connection
		   Statement stmt = conn.createStatement();
		   Statement stmt2 = conn.createStatement();
		   Statement stmt3 = conn.createStatement();
		   Statement stmt4 = conn.createStatement();
		   PreparedStatement pre=null;
		   conn.setAutoCommit(false);// 更改jdbc事务的默认提交方式 
		   String sql = "select * from zero";//查询语句
		   ResultSet rs = stmt.executeQuery(sql);//得到结果集
		   conn.commit();//事务提交
		   conn.setAutoCommit(true);// 更改jdbc事务的默认提交方式 
		   List<String> list=new ArrayList<String>();//创建取结果的列表，之所以使用列表，不用数组，因为现在还不知道结果有多少，不能确定数组长度，所有先用list接收，然后转为数组
		   while (rs.next()) {//如果有数据，取第一列添加如list
		    list.add(rs.getString(1));
		   }
		   if(list != null && list.size()>0){//如果list中存入了数据，转化为数组
		    String[] arr=new String[list.size()];//创建一个和list长度一样的数组
		    for(int i=0;i<list.size();i++){
		     arr[i]=list.get(i);//数组赋值了。
		    }
		      int zeroshenglv=0;
			  int oneshenglv=0;
			  int twoshenglv=0;
		       //输出数组
		       for(int i=0;i<arr.length+100;i++){
		    	int yaoyucededata=1201;//i+1;
		       if(i==(yaoyucededata-1)){
			    	  for(int q=10;q>0;q--){ //应该计入q(i)个数进首行,q<256
			    		  String stringq=String.valueOf(q);

					      String shouhang="";                  //清空首行

				    		  for(int j=(i-1);(i-1)-j<q;j--){                  //这个for记录0到i-1，即起点数
				    			  
				    			  shouhang +=arr[j];		//赋值不同q下的首行
				    		  }
				    		 System.out.println(shouhang);
				    	   	//0 的胜率
					  	    String sqlselect0="select shenglv from "+"a"+stringq+" where (shouhang='"+shouhang+"')"
					  	    			+"and"+"(jieguo=0"+")";
					    	ResultSet rs00 = stmt2.executeQuery(sqlselect0);
					    		
				    		  //1 的胜率
				  	    	String sqlselect1="select shenglv from "+"a"+stringq+" where (shouhang='"+shouhang+"')"
				  	    			+"and"+"(jieguo=1"+")";
				    		ResultSet rs01 = stmt3.executeQuery(sqlselect1);
				    		
				    		//2 的胜率
				  	    	String sqlselect2="select shenglv from "+"a"+stringq+" where (shouhang='"+shouhang+"')"
				  	    			+"and"+"(jieguo=2"+")";
				    		ResultSet rs02 = stmt4.executeQuery(sqlselect2);
				  
				    		if(rs01.next())
				    			do{
							    
							    oneshenglv +=rs01.getInt(1);
				    			}while(rs01.next());
				    		if(rs00.next())
				    			do{
						    		
						    		zeroshenglv +=rs00.getInt(1);
				    			}while(rs00.next());
				    		if(rs02.next())
				    			do{
				    				
						    		twoshenglv +=rs02.getInt(1);
				    			}while(rs02.next());
				    		
				    		
			    	  }	
		       }
		       
		       
		     }
		        NumberFormat numberFormat = NumberFormat.getInstance();  
		        
		        // 设置精确到小数点后2位  
		   
		        numberFormat.setMaximumFractionDigits(2); 
		        String result1 = numberFormat.format((float) zeroshenglv / (float) (zeroshenglv+oneshenglv+twoshenglv) * 100);
		        String result2 = numberFormat.format((float) oneshenglv / (float) (zeroshenglv+oneshenglv+twoshenglv) * 100);
		        String result3 = numberFormat.format((float) twoshenglv / (float) (zeroshenglv+oneshenglv+twoshenglv) * 100);
		       System.out.println("百位0的胜率 :"+ result1 + "%"+"   "+zeroshenglv);
		       System.out.println("百位1的胜率 :"+ result2 + "%"+"   "+oneshenglv);
		       System.out.println("百位2的胜率 :"+ result3 + "%"+"   "+twoshenglv);
		   }
		   
		  } catch (Exception e) {
		   // TODO Auto-generated catch block   
		   e.printStackTrace();
		  }
		}
	
	
	public void yc2(Goddess g)throws Exception{
		 try {
		   Connection conn = DBUtil.getConnection();//建立connection
		   Statement stmt = conn.createStatement();
		   Statement stmt2 = conn.createStatement();
		   Statement stmt3 = conn.createStatement();
		   Statement stmt4 = conn.createStatement();
		   PreparedStatement pre=null;
		   conn.setAutoCommit(false);// 更改jdbc事务的默认提交方式 
		   String sql = "select * from zero";//查询语句
		   ResultSet rs = stmt.executeQuery(sql);//得到结果集
		   conn.commit();//事务提交
		   conn.setAutoCommit(true);// 更改jdbc事务的默认提交方式 
		   List<String> list=new ArrayList<String>();//创建取结果的列表，之所以使用列表，不用数组，因为现在还不知道结果有多少，不能确定数组长度，所有先用list接收，然后转为数组
		   while (rs.next()) {//如果有数据，取第一列添加如list
		    list.add(rs.getString(2));
		   }
		   if(list != null && list.size()>0){//如果list中存入了数据，转化为数组
		    String[] arr=new String[list.size()];//创建一个和list长度一样的数组
		    for(int i=0;i<list.size();i++){
		     arr[i]=list.get(i);//数组赋值了。
		    }
		      int zeroshenglv=0;
			  int oneshenglv=0;
			  int twoshenglv=0;
		       //输出数组
		       for(int i=0;i<arr.length+100;i++){
		    	int yaoyucededata=1201;
		       if(i==(yaoyucededata-1)){
			    	  for(int q=10;q>0;q--){ //应该计入q(i)个数进首行,q<256
			    		  String stringq=String.valueOf(q);

					      String shouhang="";                  //清空首行

				    		  for(int j=(i-1);(i-1)-j<q;j--){                  //这个for记录0到i-1，即起点数
				    			  
				    			  shouhang +=arr[j];		//赋值不同q下的首行
				    		  }
				    		  System.out.println(shouhang);
				    	   	//0 的胜率
					  	    String sqlselect0="select shenglv from "+"b"+stringq+" where (shouhang='"+shouhang+"')"
					  	    			+"and"+"(jieguo=0"+")";
					    	ResultSet rs00 = stmt2.executeQuery(sqlselect0);
					    		
				    		  //1 的胜率
				  	    	String sqlselect1="select shenglv from "+"b"+stringq+" where (shouhang='"+shouhang+"')"
				  	    			+"and"+"(jieguo=1"+")";
				    		ResultSet rs01 = stmt3.executeQuery(sqlselect1);
				    		
				    		//2 的胜率
				  	    	String sqlselect2="select shenglv from "+"b"+stringq+" where (shouhang='"+shouhang+"')"
				  	    			+"and"+"(jieguo=2"+")";
				    		ResultSet rs02 = stmt4.executeQuery(sqlselect2);
				  
				    		if(rs01.next())
				    			do{
							   
							    oneshenglv +=rs01.getInt(1);
				    			}while(rs01.next());
				    		if(rs00.next())
				    			do{
						    		
						    		zeroshenglv +=rs00.getInt(1);
				    			}while(rs00.next());
				    		if(rs02.next())
				    			do{
				    				
						    		twoshenglv +=rs02.getInt(1);
				    			}while(rs02.next());
				    		
				    		
			    	  }	
		       }
		       
		       
		     }
		        NumberFormat numberFormat = NumberFormat.getInstance();  
		        
		        // 设置精确到小数点后2位  
		   
		        numberFormat.setMaximumFractionDigits(2); 
		        String result1 = numberFormat.format((float) zeroshenglv / (float) (zeroshenglv+oneshenglv+twoshenglv) * 100);
		        String result2 = numberFormat.format((float) oneshenglv / (float) (zeroshenglv+oneshenglv+twoshenglv) * 100);
		        String result3 = numberFormat.format((float) twoshenglv / (float) (zeroshenglv+oneshenglv+twoshenglv) * 100);
		       System.out.println("十位0的胜率 :"+ result1 + "%"+"   "+zeroshenglv);
		       System.out.println("十位1的胜率 :"+ result2 + "%"+"   "+oneshenglv);
		       System.out.println("十位2的胜率 :"+ result3 + "%"+"   "+twoshenglv);
		   }
		   
		  } catch (Exception e) {
		   // TODO Auto-generated catch block   
		   e.printStackTrace();
		  }
		}
	
	
	public void yc3(Goddess g)throws Exception{
		 try {
		   Connection conn = DBUtil.getConnection();//建立connection
		   Statement stmt = conn.createStatement();
		   Statement stmt2 = conn.createStatement();
		   Statement stmt3 = conn.createStatement();
		   Statement stmt4 = conn.createStatement();
		   PreparedStatement pre=null;
		   conn.setAutoCommit(false);// 更改jdbc事务的默认提交方式 
		   String sql = "select * from zero";//查询语句
		   ResultSet rs = stmt.executeQuery(sql);//得到结果集
		   conn.commit();//事务提交
		   conn.setAutoCommit(true);// 更改jdbc事务的默认提交方式 
		   List<String> list=new ArrayList<String>();//创建取结果的列表，之所以使用列表，不用数组，因为现在还不知道结果有多少，不能确定数组长度，所有先用list接收，然后转为数组
		   while (rs.next()) {//如果有数据，取第一列添加如list
		    list.add(rs.getString(3));
		   }
		   if(list != null && list.size()>0){//如果list中存入了数据，转化为数组
		    String[] arr=new String[list.size()];//创建一个和list长度一样的数组
		    for(int i=0;i<list.size();i++){
		     arr[i]=list.get(i);//数组赋值了。
		    }
		      int zeroshenglv=0;
			  int oneshenglv=0;
			  int twoshenglv=0;
		       //输出数组
		       for(int i=0;i<arr.length+100;i++){
		    	int yaoyucededata=1201;
		       if(i==(yaoyucededata-1)){
			    	  for(int q=10;q>0;q--){ //应该计入q(i)个数进首行,q<256
			    		  String stringq=String.valueOf(q);

					      String shouhang="";                  //清空首行

				    		  for(int j=(i-1);(i-1)-j<q;j--){                  //这个for记录0到i-1，即起点数
				    			  
				    			  shouhang +=arr[j];		//赋值不同q下的首行
				    		  }
				    		  System.out.println(shouhang);
				    	   	//0 的胜率
					  	    String sqlselect0="select shenglv from "+"c"+stringq+" where (shouhang='"+shouhang+"')"
					  	    			+"and"+"(jieguo=0"+")";
					    	ResultSet rs00 = stmt2.executeQuery(sqlselect0);
					    		
				    		  //1 的胜率
				  	    	String sqlselect1="select shenglv from "+"c"+stringq+" where (shouhang='"+shouhang+"')"
				  	    			+"and"+"(jieguo=1"+")";
				    		ResultSet rs01 = stmt3.executeQuery(sqlselect1);
				    		
				    		//2 的胜率
				  	    	String sqlselect2="select shenglv from "+"c"+stringq+" where (shouhang='"+shouhang+"')"
				  	    			+"and"+"(jieguo=2"+")";
				    		ResultSet rs02 = stmt4.executeQuery(sqlselect2);
				  
				    		if(rs01.next())
				    			do{
							    
							    oneshenglv +=rs01.getInt(1);
				    			}while(rs01.next());
				    		if(rs00.next())
				    			do{
						    		
						    		zeroshenglv +=rs00.getInt(1);
				    			}while(rs00.next());
				    		if(rs02.next())
				    			do{
				    				
						    		twoshenglv +=rs02.getInt(1);
				    			}while(rs02.next());
				    		
				    		
			    	  }	
		       }
		       
		       
		     }
		        NumberFormat numberFormat = NumberFormat.getInstance();  
		        
		        // 设置精确到小数点后2位  
		   
		        numberFormat.setMaximumFractionDigits(2); 
		        String result1 = numberFormat.format((float) zeroshenglv / (float) (zeroshenglv+oneshenglv+twoshenglv) * 100);
		        String result2 = numberFormat.format((float) oneshenglv / (float) (zeroshenglv+oneshenglv+twoshenglv) * 100);
		        String result3 = numberFormat.format((float) twoshenglv / (float) (zeroshenglv+oneshenglv+twoshenglv) * 100);
		       System.out.println("个位0的胜率 :"+ result1 + "%"+"   "+zeroshenglv);
		       System.out.println("个位1的胜率 :"+ result2 + "%"+"   "+oneshenglv);
		       System.out.println("个位2的胜率 :"+ result3 + "%"+"   "+twoshenglv);
		   }
		   
		  } catch (Exception e) {
		   // TODO Auto-generated catch block   
		   e.printStackTrace();
		  }
		}
	
	
	
	public void getjioucolsone(Goddess g)throws Exception{
		 try {
		   Connection conn = DBUtil.getConnection();//建立connection
		   Statement stmt = conn.createStatement();
		   PreparedStatement pre=null;
		   conn.setAutoCommit(false);// 更改jdbc事务的默认提交方式 
		   String sql = "select * from zero2";//查询语句
		   ResultSet rs = stmt.executeQuery(sql);//得到结果集
		   conn.commit();//事务提交
		   conn.setAutoCommit(true);// 更改jdbc事务的默认提交方式 
		   List<String> list=new ArrayList<String>();//创建取结果的列表，之所以使用列表，不用数组，因为现在还不知道结果有多少，不能确定数组长度，所有先用list接收，然后转为数组
		   while (rs.next()) {//如果有数据，取第一列添加如list
		    list.add(rs.getString(1));
		   }
		   if(list != null && list.size()>0){//如果list中存入了数据，转化为数组
		    String[] arr=new String[list.size()];//创建一个和list长度一样的数组
		    for(int i=0;i<list.size();i++){
		     arr[i]=list.get(i);//数组赋值了。
		    }
		       //输出数组
		       for(int i=0;i<arr.length;i++){
		    	 
		    	   if(i<16){
		    		   for(int q=i;q>0;q--){ //应该计入q(i)个数进首行,q<256
		    		  String stringq=String.valueOf(q);

				      String shouhang="";                  //清空首行
		    		  if(validateTableNameExist("jioua"+stringq)==false){
		    			  //如果table q 不存在,创建一个新table q（i代表前面有i个数）
		    			
		    			  String sql2="CREATE TABLE "+"jioua"+stringq
		    					  +"(shouhang TEXT,"+
		    					  "jieguo TEXT,shenglv int(5))";
		    			  stmt.executeUpdate(sql2);
		    		  }
	
		    		  


			    		  for(int j=(i-1);(i-1)-j<q;j--){                  //这个for记录0到i-1，即起点数
			    			  
			    			  shouhang +=arr[j];		//赋值不同q下的首行
			    		  }
			    		  
			    	String sqlselect="select count(*) from "+"jioua"+stringq+" where shouhang='"+shouhang+"'"+"and jieguo="+"'"+arr[i]+"'";
			    	
			    	try { 
			    		ResultSet rs2 = stmt.executeQuery(sqlselect); 
			    		int count = 0; 
			    		while (rs2.next()) { 
			    		count = rs2.getInt(1); 
			    		} 
			    		if(count == 0) { 
			    		//没同样的记录,该干什么干什么 
			    			pre=conn.prepareStatement("insert into jioua"+stringq+"(shouhang,jieguo,shenglv) values (?,?,?)");
			       		    pre.setString(1, shouhang);
			       		    pre.setString(2, arr[i]);
			       		    pre.setInt(3, 0);
			       		    pre.executeUpdate();	
			    		} else { 
			    		//有同样的记录,该干什么干什么 
			    		//首先读取胜率的数据，然后进行updatae修改
			    			
			    		String sql4="UPDATE "+"jioua"+stringq+" SET shenglv =shenglv+1 WHERE shouhang='"+shouhang+"'"+"and jieguo="+"'"+arr[i]+"'";
			    		stmt.executeUpdate(sql4);
			    		} 
			    	} catch (SQLException e) { 
			    	e.printStackTrace(); 
			    	}	
			    		  

		    	  }	
		    	   }
		    	    
 
		    	   if(i>15){
		    		   for(int q=15;q>0;q--){ //应该计入q(i)个数进首行,q<256
				    		  String stringq=String.valueOf(q);

						      String shouhang="";                  //清空首行
				    		  if(validateTableNameExist("jioua"+stringq)==false){
				    			  //如果table q 不存在,创建一个新table q（i代表前面有i个数）
				    			
				    			  String sql2="CREATE TABLE "+"jioua"+stringq
				    					  +"(shouhang TEXT,"+
				    					  "jieguo TEXT,shenglv int(5))";
				    			  stmt.executeUpdate(sql2);
				    		  }
			
				    		  


					    		  for(int j=(i-1);(i-1)-j<q;j--){                  //这个for记录0到i-1，即起点数
					    			  
					    			  shouhang +=arr[j];		//赋值不同q下的首行
					    		  }
					    		  
					    	String sqlselect="select count(*) from "+"jioua"+stringq+" where shouhang='"+shouhang+"'"+"and jieguo="+"'"+arr[i]+"'";
					    	
					    	try { 
					    		ResultSet rs2 = stmt.executeQuery(sqlselect); 
					    		int count = 0; 
					    		while (rs2.next()) { 
					    		count = rs2.getInt(1); 
					    		} 
					    		if(count == 0) { 
					    		//没同样的记录,该干什么干什么 
					    			pre=conn.prepareStatement("insert into jioua"+stringq+"(shouhang,jieguo,shenglv) values (?,?,?)");
					       		    pre.setString(1, shouhang);
					       		    pre.setString(2, arr[i]);
					       		    pre.setInt(3, 0);
					       		    pre.executeUpdate();	
					    		} else { 
					    		//有同样的记录,该干什么干什么 
					    		//首先读取胜率的数据，然后进行updatae修改
					    			
					    		String sql4="UPDATE "+"jioua"+stringq+" SET shenglv =shenglv+1 WHERE shouhang='"+shouhang+"'"+"and jieguo="+"'"+arr[i]+"'";
					    		stmt.executeUpdate(sql4);
					    		} 
					    	} catch (SQLException e) { 
					    	e.printStackTrace(); 
					    	}	
					    		  

				    	  }	
		    		   
		    	   }
		    	  
		    	  
		       System.out.println(arr[i]+"   baiwei:  "+i);
		     }
		       

		   }
		   
		  } catch (Exception e) {
		   // TODO Auto-generated catch block   
		   e.printStackTrace();
		  }
		}
	
	
	
	public void getjioucolstwo(Goddess g)throws Exception{
		 try {
		   Connection conn = DBUtil.getConnection();//建立connection
		   Statement stmt = conn.createStatement();
		   PreparedStatement pre=null;
		   conn.setAutoCommit(false);// 更改jdbc事务的默认提交方式 
		   String sql = "select * from zero2";//查询语句
		   ResultSet rs = stmt.executeQuery(sql);//得到结果集
		   conn.commit();//事务提交
		   conn.setAutoCommit(true);// 更改jdbc事务的默认提交方式 
		   List<String> list=new ArrayList<String>();//创建取结果的列表，之所以使用列表，不用数组，因为现在还不知道结果有多少，不能确定数组长度，所有先用list接收，然后转为数组
		   while (rs.next()) {//如果有数据，取第一列添加如list
		    list.add(rs.getString(2));
		   }
		   if(list != null && list.size()>0){//如果list中存入了数据，转化为数组
		    String[] arr=new String[list.size()];//创建一个和list长度一样的数组
		    for(int i=0;i<list.size();i++){
		     arr[i]=list.get(i);//数组赋值了。
		    }
		       //输出数组
		       for(int i=0;i<arr.length;i++){
		    	 
		    	   if(i<16){
		    		   for(int q=i;q>0;q--){ //应该计入q(i)个数进首行,q<256
		    		  String stringq=String.valueOf(q);

				      String shouhang="";                  //清空首行
		    		  if(validateTableNameExist("jioub"+stringq)==false){
		    			  //如果table q 不存在,创建一个新table q（i代表前面有i个数）
		    			
		    			  String sql2="CREATE TABLE "+"jioub"+stringq
		    					  +"(shouhang TEXT,"+
		    					  "jieguo TEXT,shenglv int(5))";
		    			  stmt.executeUpdate(sql2);
		    		  }
	
		    		  


			    		  for(int j=(i-1);(i-1)-j<q;j--){                  //这个for记录0到i-1，即起点数
			    			  
			    			  shouhang +=arr[j];		//赋值不同q下的首行
			    		  }
			    		  
			    	String sqlselect="select count(*) from "+"jioub"+stringq+" where shouhang='"+shouhang+"'"+"and jieguo="+"'"+arr[i]+"'";
			    	
			    	try { 
			    		ResultSet rs2 = stmt.executeQuery(sqlselect); 
			    		int count = 0; 
			    		while (rs2.next()) { 
			    		count = rs2.getInt(1); 
			    		} 
			    		if(count == 0) { 
			    		//没同样的记录,该干什么干什么 
			    			pre=conn.prepareStatement("insert into jioub"+stringq+"(shouhang,jieguo,shenglv) values (?,?,?)");
			       		    pre.setString(1, shouhang);
			       		    pre.setString(2, arr[i]);
			       		    pre.setInt(3, 0);
			       		    pre.executeUpdate();	
			    		} else { 
			    		//有同样的记录,该干什么干什么 
			    		//首先读取胜率的数据，然后进行updatae修改
			    			
			    		String sql4="UPDATE "+"jioub"+stringq+" SET shenglv =shenglv+1 WHERE shouhang='"+shouhang+"'"+"and jieguo="+"'"+arr[i]+"'";
			    		stmt.executeUpdate(sql4);
			    		} 
			    	} catch (SQLException e) { 
			    	e.printStackTrace(); 
			    	}	
			    		  

		    	  }	
		    	   }
		    	    

		    	   if(i>15){
		    		   for(int q=15;q>0;q--){ //应该计入q(i)个数进首行,q<256
				    		  String stringq=String.valueOf(q);

						      String shouhang="";                  //清空首行
				    		  if(validateTableNameExist("jioub"+stringq)==false){
				    			  //如果table q 不存在,创建一个新table q（i代表前面有i个数）
				    			
				    			  String sql2="CREATE TABLE "+"jioub"+stringq
				    					  +"(shouhang TEXT,"+
				    					  "jieguo TEXT,shenglv int(5))";
				    			  stmt.executeUpdate(sql2);
				    		  }
			
				    		  


					    		  for(int j=(i-1);(i-1)-j<q;j--){                  //这个for记录0到i-1，即起点数
					    			  
					    			  shouhang +=arr[j];		//赋值不同q下的首行
					    		  }
					    		  
					    	String sqlselect="select count(*) from "+"jioub"+stringq+" where shouhang='"+shouhang+"'"+"and jieguo="+"'"+arr[i]+"'";
					    	
					    	try { 
					    		ResultSet rs2 = stmt.executeQuery(sqlselect); 
					    		int count = 0; 
					    		while (rs2.next()) { 
					    		count = rs2.getInt(1); 
					    		} 
					    		if(count == 0) { 
					    		//没同样的记录,该干什么干什么 
					    			pre=conn.prepareStatement("insert into jioub"+stringq+"(shouhang,jieguo,shenglv) values (?,?,?)");
					       		    pre.setString(1, shouhang);
					       		    pre.setString(2, arr[i]);
					       		    pre.setInt(3, 0);
					       		    pre.executeUpdate();	
					    		} else { 
					    		//有同样的记录,该干什么干什么 
					    		//首先读取胜率的数据，然后进行updatae修改
					    			
					    		String sql4="UPDATE "+"jioub"+stringq+" SET shenglv =shenglv+1 WHERE shouhang='"+shouhang+"'"+"and jieguo="+"'"+arr[i]+"'";
					    		stmt.executeUpdate(sql4);
					    		} 
					    	} catch (SQLException e) { 
					    	e.printStackTrace(); 
					    	}	
					    		  

				    	  }	
		    		   
		    	   }
		    	  
		    	  
		       System.out.println(arr[i]+"   shiwei:  "+i);
		     }
		       

		   }
		   
		  } catch (Exception e) {
		   // TODO Auto-generated catch block   
		   e.printStackTrace();
		  }
		}
	
	
	public void getjioucolsthree(Goddess g)throws Exception{
		 try {
		   Connection conn = DBUtil.getConnection();//建立connection
		   Statement stmt = conn.createStatement();
		   PreparedStatement pre=null;
		   conn.setAutoCommit(false);// 更改jdbc事务的默认提交方式 
		   String sql = "select * from zero2";//查询语句
		   ResultSet rs = stmt.executeQuery(sql);//得到结果集
		   conn.commit();//事务提交
		   conn.setAutoCommit(true);// 更改jdbc事务的默认提交方式 
		   List<String> list=new ArrayList<String>();//创建取结果的列表，之所以使用列表，不用数组，因为现在还不知道结果有多少，不能确定数组长度，所有先用list接收，然后转为数组
		   while (rs.next()) {//如果有数据，取第一列添加如list
		    list.add(rs.getString(3));
		   }
		   if(list != null && list.size()>0){//如果list中存入了数据，转化为数组
		    String[] arr=new String[list.size()];//创建一个和list长度一样的数组
		    for(int i=0;i<list.size();i++){
		     arr[i]=list.get(i);//数组赋值了。
		    }
		       //输出数组
		       for(int i=0;i<arr.length;i++){
		    	 
		    	   if(i<16){
		    		   for(int q=i;q>0;q--){ //应该计入q(i)个数进首行,q<256
		    		  String stringq=String.valueOf(q);

				      String shouhang="";                  //清空首行
		    		  if(validateTableNameExist("jiouc"+stringq)==false){
		    			  //如果table q 不存在,创建一个新table q（i代表前面有i个数）
		    			
		    			  String sql2="CREATE TABLE "+"jiouc"+stringq
		    					  +"(shouhang TEXT,"+
		    					  "jieguo TEXT,shenglv int(5))";
		    			  stmt.executeUpdate(sql2);
		    		  }
	
		    		  


			    		  for(int j=(i-1);(i-1)-j<q;j--){                  //这个for记录0到i-1，即起点数
			    			  
			    			  shouhang +=arr[j];		//赋值不同q下的首行
			    		  }
			    		  
			    	String sqlselect="select count(*) from "+"jiouc"+stringq+" where shouhang='"+shouhang+"'"+"and jieguo="+"'"+arr[i]+"'";
			    	
			    	try { 
			    		ResultSet rs2 = stmt.executeQuery(sqlselect); 
			    		int count = 0; 
			    		while (rs2.next()) { 
			    		count = rs2.getInt(1); 
			    		} 
			    		if(count == 0) { 
			    		//没同样的记录,该干什么干什么 
			    			pre=conn.prepareStatement("insert into jiouc"+stringq+"(shouhang,jieguo,shenglv) values (?,?,?)");
			       		    pre.setString(1, shouhang);
			       		    pre.setString(2, arr[i]);
			       		    pre.setInt(3, 0);
			       		    pre.executeUpdate();	
			    		} else { 
			    		//有同样的记录,该干什么干什么 
			    		//首先读取胜率的数据，然后进行updatae修改
			    			
			    		String sql4="UPDATE "+"jiouc"+stringq+" SET shenglv =shenglv+1 WHERE shouhang='"+shouhang+"'"+"and jieguo="+"'"+arr[i]+"'";
			    		stmt.executeUpdate(sql4);
			    		} 
			    	} catch (SQLException e) { 
			    	e.printStackTrace(); 
			    	}	
			    		  

		    	  }	
		    	   }
		    	    

		    	   if(i>15){
		    		   for(int q=15;q>0;q--){ //应该计入q(i)个数进首行,q<256
				    		  String stringq=String.valueOf(q);

						      String shouhang="";                  //清空首行
				    		  if(validateTableNameExist("jiouc"+stringq)==false){
				    			  //如果table q 不存在,创建一个新table q（i代表前面有i个数）
				    			
				    			  String sql2="CREATE TABLE "+"jiouc"+stringq
				    					  +"(shouhang TEXT,"+
				    					  "jieguo TEXT,shenglv int(5))";
				    			  stmt.executeUpdate(sql2);
				    		  }
			
				    		  


					    		  for(int j=(i-1);(i-1)-j<q;j--){                  //这个for记录0到i-1，即起点数
					    			  
					    			  shouhang +=arr[j];		//赋值不同q下的首行
					    		  }
					    		  
					    	String sqlselect="select count(*) from "+"jiouc"+stringq+" where shouhang='"+shouhang+"'"+"and jieguo="+"'"+arr[i]+"'";
					    	
					    	try { 
					    		ResultSet rs2 = stmt.executeQuery(sqlselect); 
					    		int count = 0; 
					    		while (rs2.next()) { 
					    		count = rs2.getInt(1); 
					    		} 
					    		if(count == 0) { 
					    		//没同样的记录,该干什么干什么 
					    			pre=conn.prepareStatement("insert into jiouc"+stringq+"(shouhang,jieguo,shenglv) values (?,?,?)");
					       		    pre.setString(1, shouhang);
					       		    pre.setString(2, arr[i]);
					       		    pre.setInt(3, 0);
					       		    pre.executeUpdate();	
					    		} else { 
					    		//有同样的记录,该干什么干什么 
					    		//首先读取胜率的数据，然后进行updatae修改
					    			
					    		String sql4="UPDATE "+"jiouc"+stringq+" SET shenglv =shenglv+1 WHERE shouhang='"+shouhang+"'"+"and jieguo="+"'"+arr[i]+"'";
					    		stmt.executeUpdate(sql4);
					    		} 
					    	} catch (SQLException e) { 
					    	e.printStackTrace(); 
					    	}	
					    		  

				    	  }	
		    		   
		    	   }
		    	  
		    	  
		       System.out.println(arr[i]+"   gewei:  "+i);
		     }
		       

		   }
		   
		  } catch (Exception e) {
		   // TODO Auto-generated catch block   
		   e.printStackTrace();
		  }
		}
	
	
	public void ycjioubai(Goddess g)throws Exception{
		 try {
		   Connection conn = DBUtil.getConnection();//建立connection
		   Statement stmt = conn.createStatement();
		   Statement stmt2 = conn.createStatement();
		   Statement stmt3 = conn.createStatement();
		   Statement stmt4 = conn.createStatement();
		   PreparedStatement pre=null;
		   conn.setAutoCommit(false);// 更改jdbc事务的默认提交方式 
		   String sql = "select * from zero2";//查询语句
		   ResultSet rs = stmt.executeQuery(sql);//得到结果集
		   conn.commit();//事务提交
		   conn.setAutoCommit(true);// 更改jdbc事务的默认提交方式 
		   List<String> list=new ArrayList<String>();//创建取结果的列表，之所以使用列表，不用数组，因为现在还不知道结果有多少，不能确定数组长度，所有先用list接收，然后转为数组
		   while (rs.next()) {//如果有数据，取第一列添加如list
		    list.add(rs.getString(1));
		   }
		   if(list != null && list.size()>0){//如果list中存入了数据，转化为数组
		    String[] arr=new String[list.size()];//创建一个和list长度一样的数组
		    for(int i=0;i<list.size();i++){
		     arr[i]=list.get(i);//数组赋值了。
		    }
		      int zeroshenglv=0;
			  int oneshenglv=0;

		       //输出数组
		       for(int i=0;i<arr.length+100;i++){
		    	int yaoyucededata=1201;//i+1;
		       if(i==(yaoyucededata-1)){
			    	  for(int q=15;q>0;q--){ //应该计入q(i)个数进首行,q<256
			    		  String stringq=String.valueOf(q);

					      String shouhang="";                  //清空首行

				    		  for(int j=(i-1);(i-1)-j<q;j--){                  //这个for记录0到i-1，即起点数
				    			  
				    			  shouhang +=arr[j];		//赋值不同q下的首行
				    		  }
				    		 System.out.println(shouhang);
				    	   	//0 的胜率
					  	    String sqlselect0="select shenglv from "+"jioua"+stringq+" where (shouhang='"+shouhang+"')"
					  	    			+"and"+"(jieguo=0"+")";
					    	ResultSet rs00 = stmt2.executeQuery(sqlselect0);
					    		
				    		  //1 的胜率
				  	    	String sqlselect1="select shenglv from "+"jioua"+stringq+" where (shouhang='"+shouhang+"')"
				  	    			+"and"+"(jieguo=1"+")";
				    		ResultSet rs01 = stmt3.executeQuery(sqlselect1);
				    		

				  
				    		if(rs01.next())
				    			do{
							    System.out.println("oneshenglv"+q+":  "+rs01.getInt(1));
							    oneshenglv +=rs01.getInt(1);
				    			}while(rs01.next());
				    		if(rs00.next())
				    			do{
						    		System.out.println("zeroshenglv"+q+":  "+rs00.getInt(1));
						    		zeroshenglv +=rs00.getInt(1);
				    			}while(rs00.next());

				    		
				    		
			    	  }	
		       }
		       
		       
		     }
		        NumberFormat numberFormat = NumberFormat.getInstance();  
		        
		        // 设置精确到小数点后2位  
		   
		        numberFormat.setMaximumFractionDigits(2); 
		        String result1 = numberFormat.format((float) zeroshenglv / (float) (zeroshenglv+oneshenglv) * 100);
		        String result2 = numberFormat.format((float) oneshenglv / (float) (zeroshenglv+oneshenglv) * 100);

		       System.out.println("百位0的胜率 :"+ result1 + "%"+"   "+zeroshenglv);
		       System.out.println("百位1的胜率 :"+ result2 + "%"+"   "+oneshenglv);

		   }
		   
		  } catch (Exception e) {
		   // TODO Auto-generated catch block   
		   e.printStackTrace();
		  }
		}
	
	
	public void ycjioushi(Goddess g)throws Exception{
		 try {
		   Connection conn = DBUtil.getConnection();//建立connection
		   Statement stmt = conn.createStatement();
		   Statement stmt2 = conn.createStatement();
		   Statement stmt3 = conn.createStatement();
		   Statement stmt4 = conn.createStatement();
		   PreparedStatement pre=null;
		   conn.setAutoCommit(false);// 更改jdbc事务的默认提交方式 
		   String sql = "select * from zero2";//查询语句
		   ResultSet rs = stmt.executeQuery(sql);//得到结果集
		   conn.commit();//事务提交
		   conn.setAutoCommit(true);// 更改jdbc事务的默认提交方式 
		   List<String> list=new ArrayList<String>();//创建取结果的列表，之所以使用列表，不用数组，因为现在还不知道结果有多少，不能确定数组长度，所有先用list接收，然后转为数组
		   while (rs.next()) {//如果有数据，取第一列添加如list
		    list.add(rs.getString(2));
		   }
		   if(list != null && list.size()>0){//如果list中存入了数据，转化为数组
		    String[] arr=new String[list.size()];//创建一个和list长度一样的数组
		    for(int i=0;i<list.size();i++){
		     arr[i]=list.get(i);//数组赋值了。
		    }
		      int zeroshenglv=0;
			  int oneshenglv=0;
		       //输出数组
		       for(int i=0;i<arr.length+100;i++){
		    	int yaoyucededata=1201;//i+1;
		       if(i==(yaoyucededata-1)){
			    	  for(int q=15;q>0;q--){ //应该计入q(i)个数进首行,q<256
			    		  String stringq=String.valueOf(q);

					      String shouhang="";                  //清空首行

				    		  for(int j=(i-1);(i-1)-j<q;j--){                  //这个for记录0到i-1，即起点数
				    			  
				    			  shouhang +=arr[j];		//赋值不同q下的首行
				    		  }
				    		 System.out.println(shouhang);
				    	   	//0 的胜率
					  	    String sqlselect0="select shenglv from "+"jioub"+stringq+" where (shouhang='"+shouhang+"')"
					  	    			+"and"+"(jieguo=0"+")";
					    	ResultSet rs00 = stmt2.executeQuery(sqlselect0);
					    		
				    		  //1 的胜率
				  	    	String sqlselect1="select shenglv from "+"jioub"+stringq+" where (shouhang='"+shouhang+"')"
				  	    			+"and"+"(jieguo=1"+")";
				    		ResultSet rs01 = stmt3.executeQuery(sqlselect1);
				    		

				  
				    		if(rs01.next())
				    			do{
							    System.out.println("oneshenglv"+q+":  "+rs01.getInt(1));
							    oneshenglv +=rs01.getInt(1);
				    			}while(rs01.next());
				    		if(rs00.next())
				    			do{
						    		System.out.println("zeroshenglv"+q+":  "+rs00.getInt(1));
						    		zeroshenglv +=rs00.getInt(1);
				    			}while(rs00.next());

				    		
				    		
			    	  }	
		       }
		       
		       
		     }
		        NumberFormat numberFormat = NumberFormat.getInstance();  
		        
		        // 设置精确到小数点后2位  
		   
		        numberFormat.setMaximumFractionDigits(2); 
		        String result1 = numberFormat.format((float) zeroshenglv / (float) (zeroshenglv+oneshenglv) * 100);
		        String result2 = numberFormat.format((float) oneshenglv / (float) (zeroshenglv+oneshenglv) * 100);

		       System.out.println("十位0的胜率 :"+ result1 + "%"+"   "+zeroshenglv);
		       System.out.println("十位1的胜率 :"+ result2 + "%"+"   "+oneshenglv);

		   }
		   
		  } catch (Exception e) {
		   // TODO Auto-generated catch block   
		   e.printStackTrace();
		  }
		}
	
	
	public void ycjiouge(Goddess g)throws Exception{
		 try {
		   Connection conn = DBUtil.getConnection();//建立connection
		   Statement stmt = conn.createStatement();
		   Statement stmt2 = conn.createStatement();
		   Statement stmt3 = conn.createStatement();
		   Statement stmt4 = conn.createStatement();
		   PreparedStatement pre=null;
		   conn.setAutoCommit(false);// 更改jdbc事务的默认提交方式 
		   String sql = "select * from zero2";//查询语句
		   ResultSet rs = stmt.executeQuery(sql);//得到结果集
		   conn.commit();//事务提交
		   conn.setAutoCommit(true);// 更改jdbc事务的默认提交方式 
		   List<String> list=new ArrayList<String>();//创建取结果的列表，之所以使用列表，不用数组，因为现在还不知道结果有多少，不能确定数组长度，所有先用list接收，然后转为数组
		   while (rs.next()) {//如果有数据，取第一列添加如list
		    list.add(rs.getString(3));
		   }
		   if(list != null && list.size()>0){//如果list中存入了数据，转化为数组
		    String[] arr=new String[list.size()];//创建一个和list长度一样的数组
		    for(int i=0;i<list.size();i++){
		     arr[i]=list.get(i);//数组赋值了。
		    }
		      int zeroshenglv=0;
			  int oneshenglv=0;
		       //输出数组
		       for(int i=0;i<arr.length+100;i++){
		    	int yaoyucededata=1201;//i+1;
		       if(i==(yaoyucededata-1)){
			    	  for(int q=15;q>0;q--){ //应该计入q(i)个数进首行,q<256
			    		  String stringq=String.valueOf(q);

					      String shouhang="";                  //清空首行

				    		  for(int j=(i-1);(i-1)-j<q;j--){                  //这个for记录0到i-1，即起点数
				    			  
				    			  shouhang +=arr[j];		//赋值不同q下的首行
				    		  }
				    		 System.out.println(shouhang);
				    	   	//0 的胜率
					  	    String sqlselect0="select shenglv from "+"jiouc"+stringq+" where (shouhang='"+shouhang+"')"
					  	    			+"and"+"(jieguo=0"+")";
					    	ResultSet rs00 = stmt2.executeQuery(sqlselect0);
					    		
				    		  //1 的胜率
				  	    	String sqlselect1="select shenglv from "+"jiouc"+stringq+" where (shouhang='"+shouhang+"')"
				  	    			+"and"+"(jieguo=1"+")";
				    		ResultSet rs01 = stmt3.executeQuery(sqlselect1);
				    		
				    		//2 的胜率
				  	    	String sqlselect2="select shenglv from "+"jiouc"+stringq+" where (shouhang='"+shouhang+"')"
				  	    			+"and"+"(jieguo=2"+")";
				    		ResultSet rs02 = stmt4.executeQuery(sqlselect2);
				  
				    		if(rs01.next())
				    			do{
							    System.out.println("oneshenglv"+q+":  "+rs01.getInt(1));
							    oneshenglv +=rs01.getInt(1);
				    			}while(rs01.next());
				    		if(rs00.next())
				    			do{
						    		System.out.println("zeroshenglv"+q+":  "+rs00.getInt(1));
						    		zeroshenglv +=rs00.getInt(1);
				    			}while(rs00.next());

				    		
				    		
			    	  }	
		       }
		       
		       
		     }
		        NumberFormat numberFormat = NumberFormat.getInstance();  
		        
		        // 设置精确到小数点后2位  
		   
		        numberFormat.setMaximumFractionDigits(2); 
		        String result1 = numberFormat.format((float) zeroshenglv / (float) (zeroshenglv+oneshenglv) * 100);
		        String result2 = numberFormat.format((float) oneshenglv / (float) (zeroshenglv+oneshenglv) * 100);

		       System.out.println("个位0的胜率 :"+ result1 + "%"+"   "+zeroshenglv);
		       System.out.println("个位1的胜率 :"+ result2 + "%"+"   "+oneshenglv);

		   }
		   
		  } catch (Exception e) {
		   // TODO Auto-generated catch block   
		   e.printStackTrace();
		  }
		}
	
	public void getbenweicolsone(Goddess g)throws Exception{
		 try {
		   Connection conn = DBUtil.getConnection();//建立connection
		   Statement stmt = conn.createStatement();
		   PreparedStatement pre=null;
		   conn.setAutoCommit(false);// 更改jdbc事务的默认提交方式 
		   String sql = "select * from zero4";//查询语句
		   ResultSet rs = stmt.executeQuery(sql);//得到结果集
		   conn.commit();//事务提交
		   conn.setAutoCommit(true);// 更改jdbc事务的默认提交方式 
		   List<String> list=new ArrayList<String>();//创建取结果的列表，之所以使用列表，不用数组，因为现在还不知道结果有多少，不能确定数组长度，所有先用list接收，然后转为数组
		   while (rs.next()) {//如果有数据，取第一列添加如list
		    list.add(rs.getString(1));
		   }
		   if(list != null && list.size()>0){//如果list中存入了数据，转化为数组
		    String[] arr=new String[list.size()];//创建一个和list长度一样的数组
		    for(int i=0;i<list.size();i++){
		     arr[i]=list.get(i);//数组赋值了。
		    }
		       //输出数组
		       for(int i=0;i<arr.length;i++){
		    	 
		    	   if(i<6){
		    		   for(int q=i;q>0;q--){ //应该计入q(i)个数进首行,q<256
		    		  String stringq=String.valueOf(q);

				      String shouhang="";                  //清空首行
		    		  if(validateTableNameExist("benweia"+stringq)==false){
		    			  //如果table q 不存在,创建一个新table q（i代表前面有i个数）
		    			
		    			  String sql2="CREATE TABLE "+"benweia"+stringq
		    					  +"(shouhang TEXT,"+
		    					  "jieguo TEXT,shenglv int(5))";
		    			  stmt.executeUpdate(sql2);
		    		  }
	
		    		  


			    		  for(int j=(i-1);(i-1)-j<q;j--){                  //这个for记录0到i-1，即起点数
			    			  
			    			  shouhang +=arr[j];		//赋值不同q下的首行
			    		  }
			    		  
			    	String sqlselect="select count(*) from "+"benweia"+stringq+" where shouhang='"+shouhang+"'"+"and jieguo="+"'"+arr[i]+"'";
			    	
			    	try { 
			    		ResultSet rs2 = stmt.executeQuery(sqlselect); 
			    		int count = 0; 
			    		while (rs2.next()) { 
			    		count = rs2.getInt(1); 
			    		} 
			    		if(count == 0) { 
			    		//没同样的记录,该干什么干什么 
			    			pre=conn.prepareStatement("insert into benweia"+stringq+"(shouhang,jieguo,shenglv) values (?,?,?)");
			       		    pre.setString(1, shouhang);
			       		    pre.setString(2, arr[i]);
			       		    pre.setInt(3, 0);
			       		    pre.executeUpdate();	
			    		} else { 
			    		//有同样的记录,该干什么干什么 
			    		//首先读取胜率的数据，然后进行updatae修改
			    			
			    		String sql4="UPDATE "+"benweia"+stringq+" SET shenglv =shenglv+1 WHERE shouhang='"+shouhang+"'"+"and jieguo="+"'"+arr[i]+"'";
			    		stmt.executeUpdate(sql4);
			    		} 
			    	} catch (SQLException e) { 
			    	e.printStackTrace(); 
			    	}	
			    		  

		    	  }	
		    	   }
		    	    
 
		    	   if(i>5){
		    		   for(int q=5;q>0;q--){ //应该计入q(i)个数进首行,q<256
				    		  String stringq=String.valueOf(q);

						      String shouhang="";                  //清空首行
				    		  if(validateTableNameExist("benweia"+stringq)==false){
				    			  //如果table q 不存在,创建一个新table q（i代表前面有i个数）
				    			
				    			  String sql2="CREATE TABLE "+"benweia"+stringq
				    					  +"(shouhang TEXT,"+
				    					  "jieguo TEXT,shenglv int(5))";
				    			  stmt.executeUpdate(sql2);
				    		  }
			
				    		  


					    		  for(int j=(i-1);(i-1)-j<q;j--){                  //这个for记录0到i-1，即起点数
					    			  
					    			  shouhang +=arr[j];		//赋值不同q下的首行
					    		  }
					    		  
					    	String sqlselect="select count(*) from "+"benweia"+stringq+" where shouhang='"+shouhang+"'"+"and jieguo="+"'"+arr[i]+"'";
					    	
					    	try { 
					    		ResultSet rs2 = stmt.executeQuery(sqlselect); 
					    		int count = 0; 
					    		while (rs2.next()) { 
					    		count = rs2.getInt(1); 
					    		} 
					    		if(count == 0) { 
					    		//没同样的记录,该干什么干什么 
					    			pre=conn.prepareStatement("insert into benweia"+stringq+"(shouhang,jieguo,shenglv) values (?,?,?)");
					       		    pre.setString(1, shouhang);
					       		    pre.setString(2, arr[i]);
					       		    pre.setInt(3, 0);
					       		    pre.executeUpdate();	
					    		} else { 
					    		//有同样的记录,该干什么干什么 
					    		//首先读取胜率的数据，然后进行updatae修改
					    			
					    		String sql4="UPDATE "+"benweia"+stringq+" SET shenglv =shenglv+1 WHERE shouhang='"+shouhang+"'"+"and jieguo="+"'"+arr[i]+"'";
					    		stmt.executeUpdate(sql4);
					    		} 
					    	} catch (SQLException e) { 
					    	e.printStackTrace(); 
					    	}	
					    		  

				    	  }	
		    		   
		    	   }
		    	  
		    	  
		       System.out.println(arr[i]+"   baiwei:  "+i);
		     }
		       

		   }
		   
		  } catch (Exception e) {
		   // TODO Auto-generated catch block   
		   e.printStackTrace();
		  }
		}
	
	
	
	public void getbenweicolstwo(Goddess g)throws Exception{
		 try {
		   Connection conn = DBUtil.getConnection();//建立connection
		   Statement stmt = conn.createStatement();
		   PreparedStatement pre=null;
		   conn.setAutoCommit(false);// 更改jdbc事务的默认提交方式 
		   String sql = "select * from zero4";//查询语句
		   ResultSet rs = stmt.executeQuery(sql);//得到结果集
		   conn.commit();//事务提交
		   conn.setAutoCommit(true);// 更改jdbc事务的默认提交方式 
		   List<String> list=new ArrayList<String>();//创建取结果的列表，之所以使用列表，不用数组，因为现在还不知道结果有多少，不能确定数组长度，所有先用list接收，然后转为数组
		   while (rs.next()) {//如果有数据，取第一列添加如list
		    list.add(rs.getString(2));
		   }
		   if(list != null && list.size()>0){//如果list中存入了数据，转化为数组
		    String[] arr=new String[list.size()];//创建一个和list长度一样的数组
		    for(int i=0;i<list.size();i++){
		     arr[i]=list.get(i);//数组赋值了。
		    }
		       //输出数组
		       for(int i=0;i<arr.length;i++){
		    	 
		    	   if(i<6){
		    		   for(int q=i;q>0;q--){ //应该计入q(i)个数进首行,q<256
		    		  String stringq=String.valueOf(q);

				      String shouhang="";                  //清空首行
		    		  if(validateTableNameExist("benweib"+stringq)==false){
		    			  //如果table q 不存在,创建一个新table q（i代表前面有i个数）
		    			
		    			  String sql2="CREATE TABLE "+"benweib"+stringq
		    					  +"(shouhang TEXT,"+
		    					  "jieguo TEXT,shenglv int(5))";
		    			  stmt.executeUpdate(sql2);
		    		  }
	
		    		  


			    		  for(int j=(i-1);(i-1)-j<q;j--){                  //这个for记录0到i-1，即起点数
			    			  
			    			  shouhang +=arr[j];		//赋值不同q下的首行
			    		  }
			    		  
			    	String sqlselect="select count(*) from "+"benweib"+stringq+" where shouhang='"+shouhang+"'"+"and jieguo="+"'"+arr[i]+"'";
			    	
			    	try { 
			    		ResultSet rs2 = stmt.executeQuery(sqlselect); 
			    		int count = 0; 
			    		while (rs2.next()) { 
			    		count = rs2.getInt(1); 
			    		} 
			    		if(count == 0) { 
			    		//没同样的记录,该干什么干什么 
			    			pre=conn.prepareStatement("insert into benweib"+stringq+"(shouhang,jieguo,shenglv) values (?,?,?)");
			       		    pre.setString(1, shouhang);
			       		    pre.setString(2, arr[i]);
			       		    pre.setInt(3, 0);
			       		    pre.executeUpdate();	
			    		} else { 
			    		//有同样的记录,该干什么干什么 
			    		//首先读取胜率的数据，然后进行updatae修改
			    			
			    		String sql4="UPDATE "+"benweib"+stringq+" SET shenglv =shenglv+1 WHERE shouhang='"+shouhang+"'"+"and jieguo="+"'"+arr[i]+"'";
			    		stmt.executeUpdate(sql4);
			    		} 
			    	} catch (SQLException e) { 
			    	e.printStackTrace(); 
			    	}	
			    		  

		    	  }	
		    	   }
		    	    

		    	   if(i>5){
		    		   for(int q=5;q>0;q--){ //应该计入q(i)个数进首行,q<256
				    		  String stringq=String.valueOf(q);

						      String shouhang="";                  //清空首行
				    		  if(validateTableNameExist("benweib"+stringq)==false){
				    			  //如果table q 不存在,创建一个新table q（i代表前面有i个数）
				    			
				    			  String sql2="CREATE TABLE "+"benweib"+stringq
				    					  +"(shouhang TEXT,"+
				    					  "jieguo TEXT,shenglv int(5))";
				    			  stmt.executeUpdate(sql2);
				    		  }
			
				    		  


					    		  for(int j=(i-1);(i-1)-j<q;j--){                  //这个for记录0到i-1，即起点数
					    			  
					    			  shouhang +=arr[j];		//赋值不同q下的首行
					    		  }
					    		  
					    	String sqlselect="select count(*) from "+"benweib"+stringq+" where shouhang='"+shouhang+"'"+"and jieguo="+"'"+arr[i]+"'";
					    	
					    	try { 
					    		ResultSet rs2 = stmt.executeQuery(sqlselect); 
					    		int count = 0; 
					    		while (rs2.next()) { 
					    		count = rs2.getInt(1); 
					    		} 
					    		if(count == 0) { 
					    		//没同样的记录,该干什么干什么 
					    			pre=conn.prepareStatement("insert into benweib"+stringq+"(shouhang,jieguo,shenglv) values (?,?,?)");
					       		    pre.setString(1, shouhang);
					       		    pre.setString(2, arr[i]);
					       		    pre.setInt(3, 0);
					       		    pre.executeUpdate();	
					    		} else { 
					    		//有同样的记录,该干什么干什么 
					    		//首先读取胜率的数据，然后进行updatae修改
					    			
					    		String sql4="UPDATE "+"benweib"+stringq+" SET shenglv =shenglv+1 WHERE shouhang='"+shouhang+"'"+"and jieguo="+"'"+arr[i]+"'";
					    		stmt.executeUpdate(sql4);
					    		} 
					    	} catch (SQLException e) { 
					    	e.printStackTrace(); 
					    	}	
					    		  

				    	  }	
		    		   
		    	   }
		    	  
		    	  
		       System.out.println(arr[i]+"   shiwei:  "+i);
		     }
		       

		   }
		   
		  } catch (Exception e) {
		   // TODO Auto-generated catch block   
		   e.printStackTrace();
		  }
		}
	
	public void getbenweicolsthree(Goddess g)throws Exception{
		 try {
		   Connection conn = DBUtil.getConnection();//建立connection
		   Statement stmt = conn.createStatement();
		   PreparedStatement pre=null;
		   conn.setAutoCommit(false);// 更改jdbc事务的默认提交方式 
		   String sql = "select * from zero4";//查询语句
		   ResultSet rs = stmt.executeQuery(sql);//得到结果集
		   conn.commit();//事务提交
		   conn.setAutoCommit(true);// 更改jdbc事务的默认提交方式 
		   List<String> list=new ArrayList<String>();//创建取结果的列表，之所以使用列表，不用数组，因为现在还不知道结果有多少，不能确定数组长度，所有先用list接收，然后转为数组
		   while (rs.next()) {//如果有数据，取第一列添加如list
		    list.add(rs.getString(3));
		   }
		   if(list != null && list.size()>0){//如果list中存入了数据，转化为数组
		    String[] arr=new String[list.size()];//创建一个和list长度一样的数组
		    for(int i=0;i<list.size();i++){
		     arr[i]=list.get(i);//数组赋值了。
		    }
		       //输出数组
		       for(int i=0;i<arr.length;i++){
		    	 
		    	   if(i<6){
		    		   for(int q=i;q>0;q--){ //应该计入q(i)个数进首行,q<256
		    		  String stringq=String.valueOf(q);

				      String shouhang="";                  //清空首行
		    		  if(validateTableNameExist("benweic"+stringq)==false){
		    			  //如果table q 不存在,创建一个新table q（i代表前面有i个数）
		    			
		    			  String sql2="CREATE TABLE "+"benweic"+stringq
		    					  +"(shouhang TEXT,"+
		    					  "jieguo TEXT,shenglv int(5))";
		    			  stmt.executeUpdate(sql2);
		    		  }
	
		    		  


			    		  for(int j=(i-1);(i-1)-j<q;j--){                  //这个for记录0到i-1，即起点数
			    			  
			    			  shouhang +=arr[j];		//赋值不同q下的首行
			    		  }
			    		  
			    	String sqlselect="select count(*) from "+"benweic"+stringq+" where shouhang='"+shouhang+"'"+"and jieguo="+"'"+arr[i]+"'";
			    	
			    	try { 
			    		ResultSet rs2 = stmt.executeQuery(sqlselect); 
			    		int count = 0; 
			    		while (rs2.next()) { 
			    		count = rs2.getInt(1); 
			    		} 
			    		if(count == 0) { 
			    		//没同样的记录,该干什么干什么 
			    			pre=conn.prepareStatement("insert into benweic"+stringq+"(shouhang,jieguo,shenglv) values (?,?,?)");
			       		    pre.setString(1, shouhang);
			       		    pre.setString(2, arr[i]);
			       		    pre.setInt(3, 0);
			       		    pre.executeUpdate();	
			    		} else { 
			    		//有同样的记录,该干什么干什么 
			    		//首先读取胜率的数据，然后进行updatae修改
			    			
			    		String sql4="UPDATE "+"benweic"+stringq+" SET shenglv =shenglv+1 WHERE shouhang='"+shouhang+"'"+"and jieguo="+"'"+arr[i]+"'";
			    		stmt.executeUpdate(sql4);
			    		} 
			    	} catch (SQLException e) { 
			    	e.printStackTrace(); 
			    	}	
			    		  

		    	  }	
		    	   }
		    	    

		    	   if(i>5){
		    		   for(int q=5;q>0;q--){ //应该计入q(i)个数进首行,q<256
				    		  String stringq=String.valueOf(q);

						      String shouhang="";                  //清空首行
				    		  if(validateTableNameExist("benweic"+stringq)==false){
				    			  //如果table q 不存在,创建一个新table q（i代表前面有i个数）
				    			
				    			  String sql2="CREATE TABLE "+"benweic"+stringq
				    					  +"(shouhang TEXT,"+
				    					  "jieguo TEXT,shenglv int(5))";
				    			  stmt.executeUpdate(sql2);
				    		  }
			
				    		  


					    		  for(int j=(i-1);(i-1)-j<q;j--){                  //这个for记录0到i-1，即起点数
					    			  
					    			  shouhang +=arr[j];		//赋值不同q下的首行
					    		  }
					    		  
					    	String sqlselect="select count(*) from "+"benweic"+stringq+" where shouhang='"+shouhang+"'"+"and jieguo="+"'"+arr[i]+"'";
					    	
					    	try { 
					    		ResultSet rs2 = stmt.executeQuery(sqlselect); 
					    		int count = 0; 
					    		while (rs2.next()) { 
					    		count = rs2.getInt(1); 
					    		} 
					    		if(count == 0) { 
					    		//没同样的记录,该干什么干什么 
					    			pre=conn.prepareStatement("insert into benweic"+stringq+"(shouhang,jieguo,shenglv) values (?,?,?)");
					       		    pre.setString(1, shouhang);
					       		    pre.setString(2, arr[i]);
					       		    pre.setInt(3, 0);
					       		    pre.executeUpdate();	
					    		} else { 
					    		//有同样的记录,该干什么干什么 
					    		//首先读取胜率的数据，然后进行updatae修改
					    			
					    		String sql4="UPDATE "+"benweic"+stringq+" SET shenglv =shenglv+1 WHERE shouhang='"+shouhang+"'"+"and jieguo="+"'"+arr[i]+"'";
					    		stmt.executeUpdate(sql4);
					    		} 
					    	} catch (SQLException e) { 
					    	e.printStackTrace(); 
					    	}	
					    		  

				    	  }	
		    		   
		    	   }
		    	  
		    	  
		       System.out.println(arr[i]+"   gewei:  "+i);
		     }
		       

		   }
		   
		  } catch (Exception e) {
		   // TODO Auto-generated catch block   
		   e.printStackTrace();
		  }
		}
	
	public void ycbai(Goddess g)throws Exception{
		 try {
		   Connection conn = DBUtil.getConnection();//建立connection
		   Statement stmt = conn.createStatement();
		   Statement stmt2 = conn.createStatement();
		   Statement stmt3 = conn.createStatement();
		   Statement stmt4 = conn.createStatement();
		   Statement stmt5 = conn.createStatement();
		   Statement stmt6 = conn.createStatement();
		   Statement stmt7 = conn.createStatement();
		   Statement stmt8 = conn.createStatement();
		   Statement stmt9 = conn.createStatement();
		   Statement stmt10 = conn.createStatement();
		   Statement stmt11 = conn.createStatement();
		   PreparedStatement pre=null;
		   conn.setAutoCommit(false);// 更改jdbc事务的默认提交方式 
		   String sql = "select * from zero4";//查询语句
		   ResultSet rs = stmt.executeQuery(sql);//得到结果集
		   conn.commit();//事务提交
		   conn.setAutoCommit(true);// 更改jdbc事务的默认提交方式 
		   List<String> list=new ArrayList<String>();//创建取结果的列表，之所以使用列表，不用数组，因为现在还不知道结果有多少，不能确定数组长度，所有先用list接收，然后转为数组
		   while (rs.next()) {//如果有数据，取第一列添加如list
		    list.add(rs.getString(1));
		   }
		   if(list != null && list.size()>0){//如果list中存入了数据，转化为数组
		    String[] arr=new String[list.size()];//创建一个和list长度一样的数组
		    for(int i=0;i<list.size();i++){
		     arr[i]=list.get(i);//数组赋值了。
		    }
		      int zeroshenglv=0;
			  int oneshenglv=0;
			  int twoshenglv=0;
			  int threeshenglv=0;
			  int fourshenglv=0;
			  int fiveshenglv=0;
			  int sixshenglv=0;
			  int sevenshenglv=0;
			  int eightshenglv=0;
			  int nineshenglv=0;
		       //输出数组
		       for(int i=0;i<arr.length+100;i++){
		    	int yaoyucededata=1201;//i+1;
		       if(i==(yaoyucededata-1)){
			    	  for(int q=5;q>0;q--){ //应该计入q(i)个数进首行,q<256
			    		  String stringq=String.valueOf(q);

					      String shouhang="";                  //清空首行

				    		  for(int j=(i-1);(i-1)-j<q;j--){                  //这个for记录0到i-1，即起点数
				    			  
				    			  shouhang +=arr[j];		//赋值不同q下的首行
				    		  }
				    		 System.out.println(shouhang);
				    	   	//0 的胜率
					  	    String sqlselect0="select shenglv from "+"benweia"+stringq+" where (shouhang='"+shouhang+"')"
					  	    			+"and"+"(jieguo=0"+")";
					    	ResultSet rs00 = stmt2.executeQuery(sqlselect0);
					    		
				    		  //1 的胜率
				  	    	String sqlselect1="select shenglv from "+"benweia"+stringq+" where (shouhang='"+shouhang+"')"
				  	    			+"and"+"(jieguo=1"+")";
				    		ResultSet rs01 = stmt3.executeQuery(sqlselect1);
				    		
				    		//2 的胜率
				  	    	String sqlselect2="select shenglv from "+"benweia"+stringq+" where (shouhang='"+shouhang+"')"
				  	    			+"and"+"(jieguo=2"+")";
				    		ResultSet rs02 = stmt4.executeQuery(sqlselect2);
		
				    		//3 的胜率
				  	    	String sqlselect3="select shenglv from "+"benweia"+stringq+" where (shouhang='"+shouhang+"')"
				  	    			+"and"+"(jieguo=3"+")";
				    		ResultSet rs03 = stmt5.executeQuery(sqlselect3);
				    		
				    		
				    		//4 的胜率
				  	    	String sqlselect4="select shenglv from "+"benweia"+stringq+" where (shouhang='"+shouhang+"')"
				  	    			+"and"+"(jieguo=4"+")";
				    		ResultSet rs04 = stmt6.executeQuery(sqlselect4);
				    		
				    		//5的胜率
				  	    	String sqlselect5="select shenglv from "+"benweia"+stringq+" where (shouhang='"+shouhang+"')"
				  	    			+"and"+"(jieguo=5"+")";
				    		ResultSet rs05 = stmt7.executeQuery(sqlselect5);
				    		
				    		//6 的胜率
				  	    	String sqlselect6="select shenglv from "+"benweia"+stringq+" where (shouhang='"+shouhang+"')"
				  	    			+"and"+"(jieguo=6"+")";
				    		ResultSet rs06 = stmt8.executeQuery(sqlselect6);
				    		
				    		//7 的胜率
				  	    	String sqlselect7="select shenglv from "+"benweia"+stringq+" where (shouhang='"+shouhang+"')"
				  	    			+"and"+"(jieguo=7"+")";
				    		ResultSet rs07 = stmt9.executeQuery(sqlselect7);
				    		
				    		//8 的胜率
				  	    	String sqlselect8="select shenglv from "+"benweia"+stringq+" where (shouhang='"+shouhang+"')"
				  	    			+"and"+"(jieguo=8"+")";
				    		ResultSet rs08 = stmt10.executeQuery(sqlselect8);
				  
				    		//9 的胜率
				  	    	String sqlselect9="select shenglv from "+"benweia"+stringq+" where (shouhang='"+shouhang+"')"
				  	    			+"and"+"(jieguo=9"+")";
				    		ResultSet rs09 = stmt11.executeQuery(sqlselect9);
				    		
				    		if(rs00.next())
				    			do{
						    		System.out.println("zeroshenglv"+q+":  "+rs00.getInt(1));
						    		zeroshenglv +=rs00.getInt(1);
				    			}while(rs00.next());
				    		
				    		if(rs01.next())
				    			do{
							    System.out.println("oneshenglv"+q+":  "+rs01.getInt(1));
							    oneshenglv +=rs01.getInt(1);
				    			}while(rs01.next());
				    		
				    		

				    		if(rs02.next())
				    			do{
				    				System.out.println("twoshenglv"+q+":  "+rs02.getInt(1));
						    		twoshenglv +=rs02.getInt(1);
				    			}while(rs02.next());
				    		
				    		if(rs03.next())
				    			do{
				    				System.out.println("threeshenglv"+q+":  "+rs03.getInt(1));
						    		threeshenglv +=rs03.getInt(1);
				    			}while(rs03.next());
				    		
				    		if(rs04.next())
				    			do{
				    				System.out.println("fourshenglv"+q+":  "+rs04.getInt(1));
						    		fourshenglv +=rs04.getInt(1);
				    			}while(rs04.next());
				    		
				    		if(rs05.next())
				    			do{
				    				System.out.println("fiveshenglv"+q+":  "+rs05.getInt(1));
						    		fiveshenglv +=rs05.getInt(1);
				    			}while(rs05.next());
				    		
				    		if(rs06.next())
				    			do{
				    				System.out.println("sixshenglv"+q+":  "+rs06.getInt(1));
						    		sixshenglv +=rs06.getInt(1);
				    			}while(rs06.next());
				    		
				    		if(rs07.next())
				    			do{
				    				System.out.println("sevenshenglv"+q+":  "+rs07.getInt(1));
						    		sevenshenglv +=rs07.getInt(1);
				    			}while(rs07.next());
				    		
				    		if(rs08.next())
				    			do{
				    				System.out.println("eightshenglv"+q+":  "+rs08.getInt(1));
						    		eightshenglv +=rs08.getInt(1);
				    			}while(rs08.next());
				    		
				    		if(rs09.next())
				    			do{
				    				System.out.println("nineshenglv"+q+":  "+rs09.getInt(1));
						    		nineshenglv +=rs09.getInt(1);
				    			}while(rs09.next());
				    		
			    	  }	
		       }
		       
		       
		     }
		        NumberFormat numberFormat = NumberFormat.getInstance();  
		        
		        // 设置精确到小数点后2位  
		   
		        numberFormat.setMaximumFractionDigits(2); 
		        String result1 = numberFormat.format((float) zeroshenglv / (float) (zeroshenglv+oneshenglv+twoshenglv+threeshenglv+fourshenglv+fiveshenglv+sixshenglv+sevenshenglv+eightshenglv+nineshenglv) * 100);
		        String result2 = numberFormat.format((float) oneshenglv / (float) (zeroshenglv+oneshenglv+twoshenglv+threeshenglv+fourshenglv+fiveshenglv+sixshenglv+sevenshenglv+eightshenglv+nineshenglv) * 100);
		        String result3 = numberFormat.format((float) twoshenglv / (float) (zeroshenglv+oneshenglv+twoshenglv+threeshenglv+fourshenglv+fiveshenglv+sixshenglv+sevenshenglv+eightshenglv+nineshenglv) * 100);
		        String result4 = numberFormat.format((float) threeshenglv / (float) (zeroshenglv+oneshenglv+twoshenglv+threeshenglv+fourshenglv+fiveshenglv+sixshenglv+sevenshenglv+eightshenglv+nineshenglv) * 100);
		        String result5 = numberFormat.format((float) fourshenglv / (float) (zeroshenglv+oneshenglv+twoshenglv+threeshenglv+fourshenglv+fiveshenglv+sixshenglv+sevenshenglv+eightshenglv+nineshenglv) * 100);
		        String result6 = numberFormat.format((float) fiveshenglv / (float) (zeroshenglv+oneshenglv+twoshenglv+threeshenglv+fourshenglv+fiveshenglv+sixshenglv+sevenshenglv+eightshenglv+nineshenglv) * 100);
		        String result7 = numberFormat.format((float) sixshenglv / (float) (zeroshenglv+oneshenglv+twoshenglv+threeshenglv+fourshenglv+fiveshenglv+sixshenglv+sevenshenglv+eightshenglv+nineshenglv) * 100);
		        String result8 = numberFormat.format((float) sevenshenglv / (float) (zeroshenglv+oneshenglv+twoshenglv+threeshenglv+fourshenglv+fiveshenglv+sixshenglv+sevenshenglv+eightshenglv+nineshenglv) * 100);
		        String result9 = numberFormat.format((float) eightshenglv / (float) (zeroshenglv+oneshenglv+twoshenglv+threeshenglv+fourshenglv+fiveshenglv+sixshenglv+sevenshenglv+eightshenglv+nineshenglv) * 100);
		        String result10 = numberFormat.format((float) nineshenglv / (float) (zeroshenglv+oneshenglv+twoshenglv+threeshenglv+fourshenglv+fiveshenglv+sixshenglv+sevenshenglv+eightshenglv+nineshenglv) * 100);

		        
		       System.out.println("百位0的胜率 :"+ result1 + "%"+"   "+zeroshenglv);
		       System.out.println("百位1的胜率 :"+ result2 + "%"+"   "+oneshenglv);
		       System.out.println("百位2的胜率 :"+ result3 + "%"+"   "+twoshenglv);
		       System.out.println("百位3的胜率 :"+ result4 + "%"+"   "+threeshenglv);
		       System.out.println("百位4的胜率 :"+ result5 + "%"+"   "+fourshenglv);
		       System.out.println("百位5的胜率 :"+ result6 + "%"+"   "+fiveshenglv);
		       System.out.println("百位6的胜率 :"+ result7 + "%"+"   "+sixshenglv);
		       System.out.println("百位7的胜率 :"+ result8 + "%"+"   "+sevenshenglv);
		       System.out.println("百位8的胜率 :"+ result9 + "%"+"   "+eightshenglv);
		       System.out.println("百位9的胜率 :"+ result10 + "%"+"   "+nineshenglv);
		   }
		   
		  } catch (Exception e) {
		   // TODO Auto-generated catch block   
		   e.printStackTrace();
		  }
		}
	
	public void ycshi(Goddess g)throws Exception{
		 try {
		   Connection conn = DBUtil.getConnection();//建立connection
		   Statement stmt = conn.createStatement();
		   Statement stmt2 = conn.createStatement();
		   Statement stmt3 = conn.createStatement();
		   Statement stmt4 = conn.createStatement();
		   Statement stmt5 = conn.createStatement();
		   Statement stmt6 = conn.createStatement();
		   Statement stmt7 = conn.createStatement();
		   Statement stmt8 = conn.createStatement();
		   Statement stmt9 = conn.createStatement();
		   Statement stmt10 = conn.createStatement();
		   Statement stmt11 = conn.createStatement();
		   PreparedStatement pre=null;
		   conn.setAutoCommit(false);// 更改jdbc事务的默认提交方式 
		   String sql = "select * from zero4";//查询语句
		   ResultSet rs = stmt.executeQuery(sql);//得到结果集
		   conn.commit();//事务提交
		   conn.setAutoCommit(true);// 更改jdbc事务的默认提交方式 
		   List<String> list=new ArrayList<String>();//创建取结果的列表，之所以使用列表，不用数组，因为现在还不知道结果有多少，不能确定数组长度，所有先用list接收，然后转为数组
		   while (rs.next()) {//如果有数据，取第一列添加如list
		    list.add(rs.getString(2));
		   }
		   if(list != null && list.size()>0){//如果list中存入了数据，转化为数组
		    String[] arr=new String[list.size()];//创建一个和list长度一样的数组
		    for(int i=0;i<list.size();i++){
		     arr[i]=list.get(i);//数组赋值了。
		    }
		      int zeroshenglv=0;
			  int oneshenglv=0;
			  int twoshenglv=0;
			  int threeshenglv=0;
			  int fourshenglv=0;
			  int fiveshenglv=0;
			  int sixshenglv=0;
			  int sevenshenglv=0;
			  int eightshenglv=0;
			  int nineshenglv=0;
		       //输出数组
		       for(int i=0;i<arr.length+100;i++){
		    	int yaoyucededata=1201;//i+1;
		       if(i==(yaoyucededata-1)){
			    	  for(int q=5;q>0;q--){ //应该计入q(i)个数进首行,q<256
			    		  String stringq=String.valueOf(q);

					      String shouhang="";                  //清空首行

				    		  for(int j=(i-1);(i-1)-j<q;j--){                  //这个for记录0到i-1，即起点数
				    			  
				    			  shouhang +=arr[j];		//赋值不同q下的首行
				    		  }
				    		 System.out.println(shouhang);
				    	   	//0 的胜率
					  	    String sqlselect0="select shenglv from "+"benweib"+stringq+" where (shouhang='"+shouhang+"')"
					  	    			+"and"+"(jieguo=0"+")";
					    	ResultSet rs00 = stmt2.executeQuery(sqlselect0);
					    		
				    		  //1 的胜率
				  	    	String sqlselect1="select shenglv from "+"benweib"+stringq+" where (shouhang='"+shouhang+"')"
				  	    			+"and"+"(jieguo=1"+")";
				    		ResultSet rs01 = stmt3.executeQuery(sqlselect1);
				    		
				    		//2 的胜率
				  	    	String sqlselect2="select shenglv from "+"benweib"+stringq+" where (shouhang='"+shouhang+"')"
				  	    			+"and"+"(jieguo=2"+")";
				    		ResultSet rs02 = stmt4.executeQuery(sqlselect2);
		
				    		//3 的胜率
				  	    	String sqlselect3="select shenglv from "+"benweib"+stringq+" where (shouhang='"+shouhang+"')"
				  	    			+"and"+"(jieguo=3"+")";
				    		ResultSet rs03 = stmt5.executeQuery(sqlselect3);
				    		
				    		
				    		//4 的胜率
				  	    	String sqlselect4="select shenglv from "+"benweib"+stringq+" where (shouhang='"+shouhang+"')"
				  	    			+"and"+"(jieguo=4"+")";
				    		ResultSet rs04 = stmt6.executeQuery(sqlselect4);
				    		
				    		//5的胜率
				  	    	String sqlselect5="select shenglv from "+"benweib"+stringq+" where (shouhang='"+shouhang+"')"
				  	    			+"and"+"(jieguo=5"+")";
				    		ResultSet rs05 = stmt7.executeQuery(sqlselect5);
				    		
				    		//6 的胜率
				  	    	String sqlselect6="select shenglv from "+"benweib"+stringq+" where (shouhang='"+shouhang+"')"
				  	    			+"and"+"(jieguo=6"+")";
				    		ResultSet rs06 = stmt8.executeQuery(sqlselect6);
				    		
				    		//7 的胜率
				  	    	String sqlselect7="select shenglv from "+"benweib"+stringq+" where (shouhang='"+shouhang+"')"
				  	    			+"and"+"(jieguo=7"+")";
				    		ResultSet rs07 = stmt9.executeQuery(sqlselect7);
				    		
				    		//8 的胜率
				  	    	String sqlselect8="select shenglv from "+"benweib"+stringq+" where (shouhang='"+shouhang+"')"
				  	    			+"and"+"(jieguo=8"+")";
				    		ResultSet rs08 = stmt10.executeQuery(sqlselect8);
				  
				    		//9 的胜率
				  	    	String sqlselect9="select shenglv from "+"benweib"+stringq+" where (shouhang='"+shouhang+"')"
				  	    			+"and"+"(jieguo=9"+")";
				    		ResultSet rs09 = stmt11.executeQuery(sqlselect9);
				    		
				    		if(rs00.next())
				    			do{
						    		System.out.println("zeroshenglv"+q+":  "+rs00.getInt(1));
						    		zeroshenglv +=rs00.getInt(1);
				    			}while(rs00.next());
				    		
				    		if(rs01.next())
				    			do{
							    System.out.println("oneshenglv"+q+":  "+rs01.getInt(1));
							    oneshenglv +=rs01.getInt(1);
				    			}while(rs01.next());
				    		
				    		

				    		if(rs02.next())
				    			do{
				    				System.out.println("twoshenglv"+q+":  "+rs02.getInt(1));
						    		twoshenglv +=rs02.getInt(1);
				    			}while(rs02.next());
				    		
				    		if(rs03.next())
				    			do{
				    				System.out.println("threeshenglv"+q+":  "+rs03.getInt(1));
						    		threeshenglv +=rs03.getInt(1);
				    			}while(rs03.next());
				    		
				    		if(rs04.next())
				    			do{
				    				System.out.println("fourshenglv"+q+":  "+rs04.getInt(1));
						    		fourshenglv +=rs04.getInt(1);
				    			}while(rs04.next());
				    		
				    		if(rs05.next())
				    			do{
				    				System.out.println("fiveshenglv"+q+":  "+rs05.getInt(1));
						    		fiveshenglv +=rs05.getInt(1);
				    			}while(rs05.next());
				    		
				    		if(rs06.next())
				    			do{
				    				System.out.println("sixshenglv"+q+":  "+rs06.getInt(1));
						    		sixshenglv +=rs06.getInt(1);
				    			}while(rs06.next());
				    		
				    		if(rs07.next())
				    			do{
				    				System.out.println("sevenshenglv"+q+":  "+rs07.getInt(1));
						    		sevenshenglv +=rs07.getInt(1);
				    			}while(rs07.next());
				    		
				    		if(rs08.next())
				    			do{
				    				System.out.println("eightshenglv"+q+":  "+rs08.getInt(1));
						    		eightshenglv +=rs08.getInt(1);
				    			}while(rs08.next());
				    		
				    		if(rs09.next())
				    			do{
				    				System.out.println("nineshenglv"+q+":  "+rs09.getInt(1));
						    		nineshenglv +=rs09.getInt(1);
				    			}while(rs09.next());
				    		
			    	  }	
		       }
		       
		       
		     }
		        NumberFormat numberFormat = NumberFormat.getInstance();  
		        
		        // 设置精确到小数点后2位  
		   
		        numberFormat.setMaximumFractionDigits(2); 
		        String result1 = numberFormat.format((float) zeroshenglv / (float) (zeroshenglv+oneshenglv+twoshenglv+threeshenglv+fourshenglv+fiveshenglv+sixshenglv+sevenshenglv+eightshenglv+nineshenglv) * 100);
		        String result2 = numberFormat.format((float) oneshenglv / (float) (zeroshenglv+oneshenglv+twoshenglv+threeshenglv+fourshenglv+fiveshenglv+sixshenglv+sevenshenglv+eightshenglv+nineshenglv) * 100);
		        String result3 = numberFormat.format((float) twoshenglv / (float) (zeroshenglv+oneshenglv+twoshenglv+threeshenglv+fourshenglv+fiveshenglv+sixshenglv+sevenshenglv+eightshenglv+nineshenglv) * 100);
		        String result4 = numberFormat.format((float) threeshenglv / (float) (zeroshenglv+oneshenglv+twoshenglv+threeshenglv+fourshenglv+fiveshenglv+sixshenglv+sevenshenglv+eightshenglv+nineshenglv) * 100);
		        String result5 = numberFormat.format((float) fourshenglv / (float) (zeroshenglv+oneshenglv+twoshenglv+threeshenglv+fourshenglv+fiveshenglv+sixshenglv+sevenshenglv+eightshenglv+nineshenglv) * 100);
		        String result6 = numberFormat.format((float) fiveshenglv / (float) (zeroshenglv+oneshenglv+twoshenglv+threeshenglv+fourshenglv+fiveshenglv+sixshenglv+sevenshenglv+eightshenglv+nineshenglv) * 100);
		        String result7 = numberFormat.format((float) sixshenglv / (float) (zeroshenglv+oneshenglv+twoshenglv+threeshenglv+fourshenglv+fiveshenglv+sixshenglv+sevenshenglv+eightshenglv+nineshenglv) * 100);
		        String result8 = numberFormat.format((float) sevenshenglv / (float) (zeroshenglv+oneshenglv+twoshenglv+threeshenglv+fourshenglv+fiveshenglv+sixshenglv+sevenshenglv+eightshenglv+nineshenglv) * 100);
		        String result9 = numberFormat.format((float) eightshenglv / (float) (zeroshenglv+oneshenglv+twoshenglv+threeshenglv+fourshenglv+fiveshenglv+sixshenglv+sevenshenglv+eightshenglv+nineshenglv) * 100);
		        String result10 = numberFormat.format((float) nineshenglv / (float) (zeroshenglv+oneshenglv+twoshenglv+threeshenglv+fourshenglv+fiveshenglv+sixshenglv+sevenshenglv+eightshenglv+nineshenglv) * 100);

		        
		       System.out.println("十位0的胜率 :"+ result1 + "%"+"   "+zeroshenglv);
		       System.out.println("十位1的胜率 :"+ result2 + "%"+"   "+oneshenglv);
		       System.out.println("十位2的胜率 :"+ result3 + "%"+"   "+twoshenglv);
		       System.out.println("十位3的胜率 :"+ result4 + "%"+"   "+threeshenglv);
		       System.out.println("十位4的胜率 :"+ result5 + "%"+"   "+fourshenglv);
		       System.out.println("十位5的胜率 :"+ result6 + "%"+"   "+fiveshenglv);
		       System.out.println("十位6的胜率 :"+ result7 + "%"+"   "+sixshenglv);
		       System.out.println("十位7的胜率 :"+ result8 + "%"+"   "+sevenshenglv);
		       System.out.println("十位8的胜率 :"+ result9 + "%"+"   "+eightshenglv);
		       System.out.println("十位9的胜率 :"+ result10 + "%"+"   "+nineshenglv);
		   }
		   
		  } catch (Exception e) {
		   // TODO Auto-generated catch block   
		   e.printStackTrace();
		  }
		}
	
	
	public void ycge(Goddess g)throws Exception{
		 try {
		   Connection conn = DBUtil.getConnection();//建立connection
		   Statement stmt = conn.createStatement();
		   Statement stmt2 = conn.createStatement();
		   Statement stmt3 = conn.createStatement();
		   Statement stmt4 = conn.createStatement();
		   Statement stmt5 = conn.createStatement();
		   Statement stmt6 = conn.createStatement();
		   Statement stmt7 = conn.createStatement();
		   Statement stmt8 = conn.createStatement();
		   Statement stmt9 = conn.createStatement();
		   Statement stmt10 = conn.createStatement();
		   Statement stmt11 = conn.createStatement();
		   PreparedStatement pre=null;
		   conn.setAutoCommit(false);// 更改jdbc事务的默认提交方式 
		   String sql = "select * from zero4";//查询语句
		   ResultSet rs = stmt.executeQuery(sql);//得到结果集
		   conn.commit();//事务提交
		   conn.setAutoCommit(true);// 更改jdbc事务的默认提交方式 
		   List<String> list=new ArrayList<String>();//创建取结果的列表，之所以使用列表，不用数组，因为现在还不知道结果有多少，不能确定数组长度，所有先用list接收，然后转为数组
		   while (rs.next()) {//如果有数据，取第一列添加如list
		    list.add(rs.getString(3));
		   }
		   if(list != null && list.size()>0){//如果list中存入了数据，转化为数组
		    String[] arr=new String[list.size()];//创建一个和list长度一样的数组
		    for(int i=0;i<list.size();i++){
		     arr[i]=list.get(i);//数组赋值了。
		    }
		      int zeroshenglv=0;
			  int oneshenglv=0;
			  int twoshenglv=0;
			  int threeshenglv=0;
			  int fourshenglv=0;
			  int fiveshenglv=0;
			  int sixshenglv=0;
			  int sevenshenglv=0;
			  int eightshenglv=0;
			  int nineshenglv=0;
		       //输出数组
		       for(int i=0;i<arr.length+100;i++){
		    	int yaoyucededata=1201;//i+1;
		       if(i==(yaoyucededata-1)){
			    	  for(int q=5;q>0;q--){ //应该计入q(i)个数进首行,q<256
			    		  String stringq=String.valueOf(q);

					      String shouhang="";                  //清空首行

				    		  for(int j=(i-1);(i-1)-j<q;j--){                  //这个for记录0到i-1，即起点数
				    			  
				    			  shouhang +=arr[j];		//赋值不同q下的首行
				    		  }
				    		 System.out.println(shouhang);
				    	   	//0 的胜率
					  	    String sqlselect0="select shenglv from "+"benweic"+stringq+" where (shouhang='"+shouhang+"')"
					  	    			+"and"+"(jieguo=0"+")";
					    	ResultSet rs00 = stmt2.executeQuery(sqlselect0);
					    		
				    		  //1 的胜率
				  	    	String sqlselect1="select shenglv from "+"benweic"+stringq+" where (shouhang='"+shouhang+"')"
				  	    			+"and"+"(jieguo=1"+")";
				    		ResultSet rs01 = stmt3.executeQuery(sqlselect1);
				    		
				    		//2 的胜率
				  	    	String sqlselect2="select shenglv from "+"benweic"+stringq+" where (shouhang='"+shouhang+"')"
				  	    			+"and"+"(jieguo=2"+")";
				    		ResultSet rs02 = stmt4.executeQuery(sqlselect2);
		
				    		//3 的胜率
				  	    	String sqlselect3="select shenglv from "+"benweic"+stringq+" where (shouhang='"+shouhang+"')"
				  	    			+"and"+"(jieguo=3"+")";
				    		ResultSet rs03 = stmt5.executeQuery(sqlselect3);
				    		
				    		
				    		//4 的胜率
				  	    	String sqlselect4="select shenglv from "+"benweic"+stringq+" where (shouhang='"+shouhang+"')"
				  	    			+"and"+"(jieguo=4"+")";
				    		ResultSet rs04 = stmt6.executeQuery(sqlselect4);
				    		
				    		//5的胜率
				  	    	String sqlselect5="select shenglv from "+"benweic"+stringq+" where (shouhang='"+shouhang+"')"
				  	    			+"and"+"(jieguo=5"+")";
				    		ResultSet rs05 = stmt7.executeQuery(sqlselect5);
				    		
				    		//6 的胜率
				  	    	String sqlselect6="select shenglv from "+"benweic"+stringq+" where (shouhang='"+shouhang+"')"
				  	    			+"and"+"(jieguo=6"+")";
				    		ResultSet rs06 = stmt8.executeQuery(sqlselect6);
				    		
				    		//7 的胜率
				  	    	String sqlselect7="select shenglv from "+"benweic"+stringq+" where (shouhang='"+shouhang+"')"
				  	    			+"and"+"(jieguo=7"+")";
				    		ResultSet rs07 = stmt9.executeQuery(sqlselect7);
				    		
				    		//8 的胜率
				  	    	String sqlselect8="select shenglv from "+"benweic"+stringq+" where (shouhang='"+shouhang+"')"
				  	    			+"and"+"(jieguo=8"+")";
				    		ResultSet rs08 = stmt10.executeQuery(sqlselect8);
				  
				    		//9 的胜率
				  	    	String sqlselect9="select shenglv from "+"benweic"+stringq+" where (shouhang='"+shouhang+"')"
				  	    			+"and"+"(jieguo=9"+")";
				    		ResultSet rs09 = stmt11.executeQuery(sqlselect9);
				    		
				    		if(rs00.next())
				    			do{
						    		System.out.println("zeroshenglv"+q+":  "+rs00.getInt(1));
						    		zeroshenglv +=rs00.getInt(1);
				    			}while(rs00.next());
				    		
				    		if(rs01.next())
				    			do{
							    System.out.println("oneshenglv"+q+":  "+rs01.getInt(1));
							    oneshenglv +=rs01.getInt(1);
				    			}while(rs01.next());
				    		
				    		

				    		if(rs02.next())
				    			do{
				    				System.out.println("twoshenglv"+q+":  "+rs02.getInt(1));
						    		twoshenglv +=rs02.getInt(1);
				    			}while(rs02.next());
				    		
				    		if(rs03.next())
				    			do{
				    				System.out.println("threeshenglv"+q+":  "+rs03.getInt(1));
						    		threeshenglv +=rs03.getInt(1);
				    			}while(rs03.next());
				    		
				    		if(rs04.next())
				    			do{
				    				System.out.println("fourshenglv"+q+":  "+rs04.getInt(1));
						    		fourshenglv +=rs04.getInt(1);
				    			}while(rs04.next());
				    		
				    		if(rs05.next())
				    			do{
				    				System.out.println("fiveshenglv"+q+":  "+rs05.getInt(1));
						    		fiveshenglv +=rs05.getInt(1);
				    			}while(rs05.next());
				    		
				    		if(rs06.next())
				    			do{
				    				System.out.println("sixshenglv"+q+":  "+rs06.getInt(1));
						    		sixshenglv +=rs06.getInt(1);
				    			}while(rs06.next());
				    		
				    		if(rs07.next())
				    			do{
				    				System.out.println("sevenshenglv"+q+":  "+rs07.getInt(1));
						    		sevenshenglv +=rs07.getInt(1);
				    			}while(rs07.next());
				    		
				    		if(rs08.next())
				    			do{
				    				System.out.println("eightshenglv"+q+":  "+rs08.getInt(1));
						    		eightshenglv +=rs08.getInt(1);
				    			}while(rs08.next());
				    		
				    		if(rs09.next())
				    			do{
				    				System.out.println("nineshenglv"+q+":  "+rs09.getInt(1));
						    		nineshenglv +=rs09.getInt(1);
				    			}while(rs09.next());
				    		
			    	  }	
		       }
		       
		       
		     }
		        NumberFormat numberFormat = NumberFormat.getInstance();  
		        
		        // 设置精确到小数点后2位  
		   
		        numberFormat.setMaximumFractionDigits(2); 
		        String result1 = numberFormat.format((float) zeroshenglv / (float) (zeroshenglv+oneshenglv+twoshenglv+threeshenglv+fourshenglv+fiveshenglv+sixshenglv+sevenshenglv+eightshenglv+nineshenglv) * 100);
		        String result2 = numberFormat.format((float) oneshenglv / (float) (zeroshenglv+oneshenglv+twoshenglv+threeshenglv+fourshenglv+fiveshenglv+sixshenglv+sevenshenglv+eightshenglv+nineshenglv) * 100);
		        String result3 = numberFormat.format((float) twoshenglv / (float) (zeroshenglv+oneshenglv+twoshenglv+threeshenglv+fourshenglv+fiveshenglv+sixshenglv+sevenshenglv+eightshenglv+nineshenglv) * 100);
		        String result4 = numberFormat.format((float) threeshenglv / (float) (zeroshenglv+oneshenglv+twoshenglv+threeshenglv+fourshenglv+fiveshenglv+sixshenglv+sevenshenglv+eightshenglv+nineshenglv) * 100);
		        String result5 = numberFormat.format((float) fourshenglv / (float) (zeroshenglv+oneshenglv+twoshenglv+threeshenglv+fourshenglv+fiveshenglv+sixshenglv+sevenshenglv+eightshenglv+nineshenglv) * 100);
		        String result6 = numberFormat.format((float) fiveshenglv / (float) (zeroshenglv+oneshenglv+twoshenglv+threeshenglv+fourshenglv+fiveshenglv+sixshenglv+sevenshenglv+eightshenglv+nineshenglv) * 100);
		        String result7 = numberFormat.format((float) sixshenglv / (float) (zeroshenglv+oneshenglv+twoshenglv+threeshenglv+fourshenglv+fiveshenglv+sixshenglv+sevenshenglv+eightshenglv+nineshenglv) * 100);
		        String result8 = numberFormat.format((float) sevenshenglv / (float) (zeroshenglv+oneshenglv+twoshenglv+threeshenglv+fourshenglv+fiveshenglv+sixshenglv+sevenshenglv+eightshenglv+nineshenglv) * 100);
		        String result9 = numberFormat.format((float) eightshenglv / (float) (zeroshenglv+oneshenglv+twoshenglv+threeshenglv+fourshenglv+fiveshenglv+sixshenglv+sevenshenglv+eightshenglv+nineshenglv) * 100);
		        String result10 = numberFormat.format((float) nineshenglv / (float) (zeroshenglv+oneshenglv+twoshenglv+threeshenglv+fourshenglv+fiveshenglv+sixshenglv+sevenshenglv+eightshenglv+nineshenglv) * 100);

		        
		       System.out.println("个位0的胜率 :"+ result1 + "%"+"   "+zeroshenglv);
		       System.out.println("个位1的胜率 :"+ result2 + "%"+"   "+oneshenglv);
		       System.out.println("个位2的胜率 :"+ result3 + "%"+"   "+twoshenglv);
		       System.out.println("个位3的胜率 :"+ result4 + "%"+"   "+threeshenglv);
		       System.out.println("个位4的胜率 :"+ result5 + "%"+"   "+fourshenglv);
		       System.out.println("个位5的胜率 :"+ result6 + "%"+"   "+fiveshenglv);
		       System.out.println("个位6的胜率 :"+ result7 + "%"+"   "+sixshenglv);
		       System.out.println("个位7的胜率 :"+ result8 + "%"+"   "+sevenshenglv);
		       System.out.println("个位8的胜率 :"+ result9 + "%"+"   "+eightshenglv);
		       System.out.println("个位9的胜率 :"+ result10 + "%"+"   "+nineshenglv);
		   }
		   
		  } catch (Exception e) {
		   // TODO Auto-generated catch block   
		   e.printStackTrace();
		  }
		}
	
	
	public void getwucolsone(Goddess g)throws Exception{
		 try {
		   Connection conn = DBUtil.getConnection();//建立connection
		   Statement stmt = conn.createStatement();
		   PreparedStatement pre=null;
		   conn.setAutoCommit(false);// 更改jdbc事务的默认提交方式 
		   String sql = "select * from zero5";//查询语句
		   ResultSet rs = stmt.executeQuery(sql);//得到结果集
		   conn.commit();//事务提交
		   conn.setAutoCommit(true);// 更改jdbc事务的默认提交方式 
		   List<String> list=new ArrayList<String>();//创建取结果的列表，之所以使用列表，不用数组，因为现在还不知道结果有多少，不能确定数组长度，所有先用list接收，然后转为数组
		   while (rs.next()) {//如果有数据，取第一列添加如list
		    list.add(rs.getString(1));
		   }
		   if(list != null && list.size()>0){//如果list中存入了数据，转化为数组
		    String[] arr=new String[list.size()];//创建一个和list长度一样的数组
		    for(int i=0;i<list.size();i++){
		     arr[i]=list.get(i);//数组赋值了。
		    }
		       //输出数组
		       for(int i=0;i<arr.length;i++){
		    	 
		    	   if(i<9){
		    		   for(int q=i;q>0;q--){ //应该计入q(i)个数进首行,q<256
		    		  String stringq=String.valueOf(q);

				      String shouhang="";                  //清空首行
		    		  if(validateTableNameExist("wua"+stringq)==false){
		    			  //如果table q 不存在,创建一个新table q（i代表前面有i个数）
		    			
		    			  String sql2="CREATE TABLE "+"wua"+stringq
		    					  +"(shouhang TEXT,"+
		    					  "jieguo TEXT,shenglv int(5))";
		    			  stmt.executeUpdate(sql2);
		    		  }
	
		    		  


			    		  for(int j=(i-1);(i-1)-j<q;j--){                  //这个for记录0到i-1，即起点数
			    			  
			    			  shouhang +=arr[j];		//赋值不同q下的首行
			    		  }
			    		  
			    	String sqlselect="select count(*) from "+"wua"+stringq+" where shouhang='"+shouhang+"'"+"and jieguo="+"'"+arr[i]+"'";
			    	
			    	try { 
			    		ResultSet rs2 = stmt.executeQuery(sqlselect); 
			    		int count = 0; 
			    		while (rs2.next()) { 
			    		count = rs2.getInt(1); 
			    		} 
			    		if(count == 0) { 
			    		//没同样的记录,该干什么干什么 
			    			pre=conn.prepareStatement("insert into wua"+stringq+"(shouhang,jieguo,shenglv) values (?,?,?)");
			       		    pre.setString(1, shouhang);
			       		    pre.setString(2, arr[i]);
			       		    pre.setInt(3, 0);
			       		    pre.executeUpdate();	
			    		} else { 
			    		//有同样的记录,该干什么干什么 
			    		//首先读取胜率的数据，然后进行updatae修改
			    			
			    		String sql4="UPDATE "+"wua"+stringq+" SET shenglv =shenglv+1 WHERE shouhang='"+shouhang+"'"+"and jieguo="+"'"+arr[i]+"'";
			    		stmt.executeUpdate(sql4);
			    		} 
			    	} catch (SQLException e) { 
			    	e.printStackTrace(); 
			    	}	
			    		  

		    	  }	
		    	   }
		    	    

		    	   if(i>8){
		    		   for(int q=8;q>0;q--){ //应该计入q(i)个数进首行,q<256
				    		  String stringq=String.valueOf(q);

						      String shouhang="";                  //清空首行
				    		  if(validateTableNameExist("wua"+stringq)==false){
				    			  //如果table q 不存在,创建一个新table q（i代表前面有i个数）
				    			
				    			  String sql2="CREATE TABLE "+"wua"+stringq
				    					  +"(shouhang TEXT,"+
				    					  "jieguo TEXT,shenglv int(5))";
				    			  stmt.executeUpdate(sql2);
				    		  }
			
				    		  


					    		  for(int j=(i-1);(i-1)-j<q;j--){                  //这个for记录0到i-1，即起点数
					    			  
					    			  shouhang +=arr[j];		//赋值不同q下的首行
					    		  }
					    		  
					    	String sqlselect="select count(*) from "+"wua"+stringq+" where shouhang='"+shouhang+"'"+"and jieguo="+"'"+arr[i]+"'";
					    	
					    	try { 
					    		ResultSet rs2 = stmt.executeQuery(sqlselect); 
					    		int count = 0; 
					    		while (rs2.next()) { 
					    		count = rs2.getInt(1); 
					    		} 
					    		if(count == 0) { 
					    		//没同样的记录,该干什么干什么 
					    			pre=conn.prepareStatement("insert into wua"+stringq+"(shouhang,jieguo,shenglv) values (?,?,?)");
					       		    pre.setString(1, shouhang);
					       		    pre.setString(2, arr[i]);
					       		    pre.setInt(3, 0);
					       		    pre.executeUpdate();	
					    		} else { 
					    		//有同样的记录,该干什么干什么 
					    		//首先读取胜率的数据，然后进行updatae修改
					    			
					    		String sql4="UPDATE "+"wua"+stringq+" SET shenglv =shenglv+1 WHERE shouhang='"+shouhang+"'"+"and jieguo="+"'"+arr[i]+"'";
					    		stmt.executeUpdate(sql4);
					    		} 
					    	} catch (SQLException e) { 
					    	e.printStackTrace(); 
					    	}	
					    		  

				    	  }	
		    		   
		    	   }
		    	  
		    	  
		       System.out.println(arr[i]+"   baiwei:  "+i);
		     }
		       

		   }
		   
		  } catch (Exception e) {
		   // TODO Auto-generated catch block   
		   e.printStackTrace();
		  }
		}
	
	public void getwucolstwo(Goddess g)throws Exception{
		 try {
		   Connection conn = DBUtil.getConnection();//建立connection
		   Statement stmt = conn.createStatement();
		   PreparedStatement pre=null;
		   conn.setAutoCommit(false);// 更改jdbc事务的默认提交方式 
		   String sql = "select * from zero5";//查询语句
		   ResultSet rs = stmt.executeQuery(sql);//得到结果集
		   conn.commit();//事务提交
		   conn.setAutoCommit(true);// 更改jdbc事务的默认提交方式 
		   List<String> list=new ArrayList<String>();//创建取结果的列表，之所以使用列表，不用数组，因为现在还不知道结果有多少，不能确定数组长度，所有先用list接收，然后转为数组
		   while (rs.next()) {//如果有数据，取第一列添加如list
		    list.add(rs.getString(2));
		   }
		   if(list != null && list.size()>0){//如果list中存入了数据，转化为数组
		    String[] arr=new String[list.size()];//创建一个和list长度一样的数组
		    for(int i=0;i<list.size();i++){
		     arr[i]=list.get(i);//数组赋值了。
		    }
		       //输出数组
		       for(int i=0;i<arr.length;i++){
		    	 
		    	   if(i<9){
		    		   for(int q=i;q>0;q--){ //应该计入q(i)个数进首行,q<256
		    		  String stringq=String.valueOf(q);

				      String shouhang="";                  //清空首行
		    		  if(validateTableNameExist("wub"+stringq)==false){
		    			  //如果table q 不存在,创建一个新table q（i代表前面有i个数）
		    			
		    			  String sql2="CREATE TABLE "+"wub"+stringq
		    					  +"(shouhang TEXT,"+
		    					  "jieguo TEXT,shenglv int(5))";
		    			  stmt.executeUpdate(sql2);
		    		  }
	
		    		  


			    		  for(int j=(i-1);(i-1)-j<q;j--){                  //这个for记录0到i-1，即起点数
			    			  
			    			  shouhang +=arr[j];		//赋值不同q下的首行
			    		  }
			    		  
			    	String sqlselect="select count(*) from "+"wub"+stringq+" where shouhang='"+shouhang+"'"+"and jieguo="+"'"+arr[i]+"'";
			    	
			    	try { 
			    		ResultSet rs2 = stmt.executeQuery(sqlselect); 
			    		int count = 0; 
			    		while (rs2.next()) { 
			    		count = rs2.getInt(1); 
			    		} 
			    		if(count == 0) { 
			    		//没同样的记录,该干什么干什么 
			    			pre=conn.prepareStatement("insert into wub"+stringq+"(shouhang,jieguo,shenglv) values (?,?,?)");
			       		    pre.setString(1, shouhang);
			       		    pre.setString(2, arr[i]);
			       		    pre.setInt(3, 0);
			       		    pre.executeUpdate();	
			    		} else { 
			    		//有同样的记录,该干什么干什么 
			    		//首先读取胜率的数据，然后进行updatae修改
			    			
			    		String sql4="UPDATE "+"wub"+stringq+" SET shenglv =shenglv+1 WHERE shouhang='"+shouhang+"'"+"and jieguo="+"'"+arr[i]+"'";
			    		stmt.executeUpdate(sql4);
			    		} 
			    	} catch (SQLException e) { 
			    	e.printStackTrace(); 
			    	}	
			    		  

		    	  }	
		    	   }
		    	    

		    	   if(i>8){
		    		   for(int q=8;q>0;q--){ //应该计入q(i)个数进首行,q<256
				    		  String stringq=String.valueOf(q);

						      String shouhang="";                  //清空首行
				    		  if(validateTableNameExist("wub"+stringq)==false){
				    			  //如果table q 不存在,创建一个新table q（i代表前面有i个数）
				    			
				    			  String sql2="CREATE TABLE "+"wub"+stringq
				    					  +"(shouhang TEXT,"+
				    					  "jieguo TEXT,shenglv int(5))";
				    			  stmt.executeUpdate(sql2);
				    		  }
			
				    		  


					    		  for(int j=(i-1);(i-1)-j<q;j--){                  //这个for记录0到i-1，即起点数
					    			  
					    			  shouhang +=arr[j];		//赋值不同q下的首行
					    		  }
					    		  
					    	String sqlselect="select count(*) from "+"wub"+stringq+" where shouhang='"+shouhang+"'"+"and jieguo="+"'"+arr[i]+"'";
					    	
					    	try { 
					    		ResultSet rs2 = stmt.executeQuery(sqlselect); 
					    		int count = 0; 
					    		while (rs2.next()) { 
					    		count = rs2.getInt(1); 
					    		} 
					    		if(count == 0) { 
					    		//没同样的记录,该干什么干什么 
					    			pre=conn.prepareStatement("insert into wub"+stringq+"(shouhang,jieguo,shenglv) values (?,?,?)");
					       		    pre.setString(1, shouhang);
					       		    pre.setString(2, arr[i]);
					       		    pre.setInt(3, 0);
					       		    pre.executeUpdate();	
					    		} else { 
					    		//有同样的记录,该干什么干什么 
					    		//首先读取胜率的数据，然后进行updatae修改
					    			
					    		String sql4="UPDATE "+"wub"+stringq+" SET shenglv =shenglv+1 WHERE shouhang='"+shouhang+"'"+"and jieguo="+"'"+arr[i]+"'";
					    		stmt.executeUpdate(sql4);
					    		} 
					    	} catch (SQLException e) { 
					    	e.printStackTrace(); 
					    	}	
					    		  

				    	  }	
		    		   
		    	   }
		    	  
		    	  
		       System.out.println(arr[i]+"   shiwei:  "+i);
		     }
		       

		   }
		   
		  } catch (Exception e) {
		   // TODO Auto-generated catch block   
		   e.printStackTrace();
		  }
		}
	
	public void getwucolsthree(Goddess g)throws Exception{
		 try {
		   Connection conn = DBUtil.getConnection();//建立connection
		   Statement stmt = conn.createStatement();
		   PreparedStatement pre=null;
		   conn.setAutoCommit(false);// 更改jdbc事务的默认提交方式 
		   String sql = "select * from zero5";//查询语句
		   ResultSet rs = stmt.executeQuery(sql);//得到结果集
		   conn.commit();//事务提交
		   conn.setAutoCommit(true);// 更改jdbc事务的默认提交方式 
		   List<String> list=new ArrayList<String>();//创建取结果的列表，之所以使用列表，不用数组，因为现在还不知道结果有多少，不能确定数组长度，所有先用list接收，然后转为数组
		   while (rs.next()) {//如果有数据，取第一列添加如list
		    list.add(rs.getString(3));
		   }
		   if(list != null && list.size()>0){//如果list中存入了数据，转化为数组
		    String[] arr=new String[list.size()];//创建一个和list长度一样的数组
		    for(int i=0;i<list.size();i++){
		     arr[i]=list.get(i);//数组赋值了。
		    }
		       //输出数组
		       for(int i=0;i<arr.length;i++){
		    	 
		    	   if(i<9){
		    		   for(int q=i;q>0;q--){ //应该计入q(i)个数进首行,q<256
		    		  String stringq=String.valueOf(q);

				      String shouhang="";                  //清空首行
		    		  if(validateTableNameExist("wuc"+stringq)==false){
		    			  //如果table q 不存在,创建一个新table q（i代表前面有i个数）
		    			
		    			  String sql2="CREATE TABLE "+"wuc"+stringq
		    					  +"(shouhang TEXT,"+
		    					  "jieguo TEXT,shenglv int(5))";
		    			  stmt.executeUpdate(sql2);
		    		  }
	
		    		  


			    		  for(int j=(i-1);(i-1)-j<q;j--){                  //这个for记录0到i-1，即起点数
			    			  
			    			  shouhang +=arr[j];		//赋值不同q下的首行
			    		  }
			    		  
			    	String sqlselect="select count(*) from "+"wuc"+stringq+" where shouhang='"+shouhang+"'"+"and jieguo="+"'"+arr[i]+"'";
			    	
			    	try { 
			    		ResultSet rs2 = stmt.executeQuery(sqlselect); 
			    		int count = 0; 
			    		while (rs2.next()) { 
			    		count = rs2.getInt(1); 
			    		} 
			    		if(count == 0) { 
			    		//没同样的记录,该干什么干什么 
			    			pre=conn.prepareStatement("insert into wuc"+stringq+"(shouhang,jieguo,shenglv) values (?,?,?)");
			       		    pre.setString(1, shouhang);
			       		    pre.setString(2, arr[i]);
			       		    pre.setInt(3, 0);
			       		    pre.executeUpdate();	
			    		} else { 
			    		//有同样的记录,该干什么干什么 
			    		//首先读取胜率的数据，然后进行updatae修改
			    			
			    		String sql4="UPDATE "+"wuc"+stringq+" SET shenglv =shenglv+1 WHERE shouhang='"+shouhang+"'"+"and jieguo="+"'"+arr[i]+"'";
			    		stmt.executeUpdate(sql4);
			    		} 
			    	} catch (SQLException e) { 
			    	e.printStackTrace(); 
			    	}	
			    		  

		    	  }	
		    	   }
		    	    

		    	   if(i>8){
		    		   for(int q=8;q>0;q--){ //应该计入q(i)个数进首行,q<256
				    		  String stringq=String.valueOf(q);

						      String shouhang="";                  //清空首行
				    		  if(validateTableNameExist("wuc"+stringq)==false){
				    			  //如果table q 不存在,创建一个新table q（i代表前面有i个数）
				    			
				    			  String sql2="CREATE TABLE "+"wuc"+stringq
				    					  +"(shouhang TEXT,"+
				    					  "jieguo TEXT,shenglv int(5))";
				    			  stmt.executeUpdate(sql2);
				    		  }
			
				    		  


					    		  for(int j=(i-1);(i-1)-j<q;j--){                  //这个for记录0到i-1，即起点数
					    			  
					    			  shouhang +=arr[j];		//赋值不同q下的首行
					    		  }
					    		  
					    	String sqlselect="select count(*) from "+"wuc"+stringq+" where shouhang='"+shouhang+"'"+"and jieguo="+"'"+arr[i]+"'";
					    	
					    	try { 
					    		ResultSet rs2 = stmt.executeQuery(sqlselect); 
					    		int count = 0; 
					    		while (rs2.next()) { 
					    		count = rs2.getInt(1); 
					    		} 
					    		if(count == 0) { 
					    		//没同样的记录,该干什么干什么 
					    			pre=conn.prepareStatement("insert into wuc"+stringq+"(shouhang,jieguo,shenglv) values (?,?,?)");
					       		    pre.setString(1, shouhang);
					       		    pre.setString(2, arr[i]);
					       		    pre.setInt(3, 0);
					       		    pre.executeUpdate();	
					    		} else { 
					    		//有同样的记录,该干什么干什么 
					    		//首先读取胜率的数据，然后进行updatae修改
					    			
					    		String sql4="UPDATE "+"wuc"+stringq+" SET shenglv =shenglv+1 WHERE shouhang='"+shouhang+"'"+"and jieguo="+"'"+arr[i]+"'";
					    		stmt.executeUpdate(sql4);
					    		} 
					    	} catch (SQLException e) { 
					    	e.printStackTrace(); 
					    	}	
					    		  

				    	  }	
		    		   
		    	   }
		    	  
		    	  
		       System.out.println(arr[i]+"   gewei:  "+i);
		     }
		       

		   }
		   
		  } catch (Exception e) {
		   // TODO Auto-generated catch block   
		   e.printStackTrace();
		  }
		}
	
	public void ycwubai(Goddess g)throws Exception{
		 try {
		   Connection conn = DBUtil.getConnection();//建立connection
		   Statement stmt = conn.createStatement();
		   Statement stmt2 = conn.createStatement();
		   Statement stmt3 = conn.createStatement();
		   Statement stmt4 = conn.createStatement();
		   Statement stmt5 = conn.createStatement();
		   Statement stmt6 = conn.createStatement();
		   Statement stmt7 = conn.createStatement();
		   Statement stmt8 = conn.createStatement();
		   Statement stmt9 = conn.createStatement();
		   Statement stmt10 = conn.createStatement();
		   Statement stmt11 = conn.createStatement();
		   PreparedStatement pre=null;
		   conn.setAutoCommit(false);// 更改jdbc事务的默认提交方式 
		   String sql = "select * from zero5";//查询语句
		   ResultSet rs = stmt.executeQuery(sql);//得到结果集
		   conn.commit();//事务提交
		   conn.setAutoCommit(true);// 更改jdbc事务的默认提交方式 
		   List<String> list=new ArrayList<String>();//创建取结果的列表，之所以使用列表，不用数组，因为现在还不知道结果有多少，不能确定数组长度，所有先用list接收，然后转为数组
		   while (rs.next()) {//如果有数据，取第一列添加如list
		    list.add(rs.getString(1));
		   }
		   if(list != null && list.size()>0){//如果list中存入了数据，转化为数组
		    String[] arr=new String[list.size()];//创建一个和list长度一样的数组
		    for(int i=0;i<list.size();i++){
		     arr[i]=list.get(i);//数组赋值了。
		    }

			  int oneshenglv=0;
			  int twoshenglv=0;
			  int threeshenglv=0;
			  int fourshenglv=0;
			  int fiveshenglv=0;

		       //输出数组
		       for(int i=0;i<arr.length+100;i++){
		    	int yaoyucededata=1201;//i+1;
		       if(i==(yaoyucededata-1)){
			    	  for(int q=8;q>0;q--){ //应该计入q(i)个数进首行,q<256
			    		  String stringq=String.valueOf(q);

					      String shouhang="";                  //清空首行

				    		  for(int j=(i-1);(i-1)-j<q;j--){                  //这个for记录0到i-1，即起点数
				    			  
				    			  shouhang +=arr[j];		//赋值不同q下的首行
				    		  }
				    		 System.out.println(shouhang);

					    		
				    		  //1 的胜率
				  	    	String sqlselect1="select shenglv from "+"wua"+stringq+" where (shouhang='"+shouhang+"')"
				  	    			+"and"+"(jieguo=0"+")";
				    		ResultSet rs01 = stmt3.executeQuery(sqlselect1);
				    		
				    		//2 的胜率
				  	    	String sqlselect2="select shenglv from "+"wua"+stringq+" where (shouhang='"+shouhang+"')"
				  	    			+"and"+"(jieguo=1"+")";
				    		ResultSet rs02 = stmt4.executeQuery(sqlselect2);
		
				    		//3 的胜率
				  	    	String sqlselect3="select shenglv from "+"wua"+stringq+" where (shouhang='"+shouhang+"')"
				  	    			+"and"+"(jieguo=2"+")";
				    		ResultSet rs03 = stmt5.executeQuery(sqlselect3);
				    		
				    		
				    		//4 的胜率
				  	    	String sqlselect4="select shenglv from "+"wua"+stringq+" where (shouhang='"+shouhang+"')"
				  	    			+"and"+"(jieguo=3"+")";
				    		ResultSet rs04 = stmt6.executeQuery(sqlselect4);
				    		
				    		//5的胜率
				  	    	String sqlselect5="select shenglv from "+"wua"+stringq+" where (shouhang='"+shouhang+"')"
				  	    			+"and"+"(jieguo=4"+")";
				    		ResultSet rs05 = stmt7.executeQuery(sqlselect5);
				    		

				    		
				    		if(rs01.next())
				    			do{
							    System.out.println("oneshenglv"+q+":  "+rs01.getInt(1));
							    oneshenglv +=rs01.getInt(1);
				    			}while(rs01.next());
				    		
				    		

				    		if(rs02.next())
				    			do{
				    				System.out.println("twoshenglv"+q+":  "+rs02.getInt(1));
						    		twoshenglv +=rs02.getInt(1);
				    			}while(rs02.next());
				    		
				    		if(rs03.next())
				    			do{
				    				System.out.println("threeshenglv"+q+":  "+rs03.getInt(1));
						    		threeshenglv +=rs03.getInt(1);
				    			}while(rs03.next());
				    		
				    		if(rs04.next())
				    			do{
				    				System.out.println("fourshenglv"+q+":  "+rs04.getInt(1));
						    		fourshenglv +=rs04.getInt(1);
				    			}while(rs04.next());
				    		
				    		if(rs05.next())
				    			do{
				    				System.out.println("fiveshenglv"+q+":  "+rs05.getInt(1));
						    		fiveshenglv +=rs05.getInt(1);
				    			}while(rs05.next());
				    		
				    		
				    		
			    	  }	
		       }
		       
		       
		     }
		        NumberFormat numberFormat = NumberFormat.getInstance();  
		        
		        // 设置精确到小数点后2位  
		   
		        numberFormat.setMaximumFractionDigits(2); 
		        String result2 = numberFormat.format((float) oneshenglv / (float) (oneshenglv+twoshenglv+threeshenglv+fourshenglv+fiveshenglv) * 100);
		        String result3 = numberFormat.format((float) twoshenglv / (float) (oneshenglv+twoshenglv+threeshenglv+fourshenglv+fiveshenglv) * 100);
		        String result4 = numberFormat.format((float) threeshenglv / (float) (oneshenglv+twoshenglv+threeshenglv+fourshenglv+fiveshenglv) * 100);
		        String result5 = numberFormat.format((float) fourshenglv / (float) (oneshenglv+twoshenglv+threeshenglv+fourshenglv+fiveshenglv) * 100);
		        String result6 = numberFormat.format((float) fiveshenglv / (float) (oneshenglv+twoshenglv+threeshenglv+fourshenglv+fiveshenglv) * 100);
	
		        

		       System.out.println("百位0的胜率 :"+ result2 + "%"+"   "+oneshenglv);
		       System.out.println("百位1的胜率 :"+ result3 + "%"+"   "+twoshenglv);
		       System.out.println("百位2的胜率 :"+ result4 + "%"+"   "+threeshenglv);
		       System.out.println("百位3的胜率 :"+ result5 + "%"+"   "+fourshenglv);
		       System.out.println("百位4的胜率 :"+ result6 + "%"+"   "+fiveshenglv);
		   }
		   
		  } catch (Exception e) {
		   // TODO Auto-generated catch block   
		   e.printStackTrace();
		  }
		}
	
	public void ycwushi(Goddess g)throws Exception{
		 try {
		   Connection conn = DBUtil.getConnection();//建立connection
		   Statement stmt = conn.createStatement();
		   Statement stmt2 = conn.createStatement();
		   Statement stmt3 = conn.createStatement();
		   Statement stmt4 = conn.createStatement();
		   Statement stmt5 = conn.createStatement();
		   Statement stmt6 = conn.createStatement();
		   Statement stmt7 = conn.createStatement();
		   Statement stmt8 = conn.createStatement();
		   Statement stmt9 = conn.createStatement();
		   Statement stmt10 = conn.createStatement();
		   Statement stmt11 = conn.createStatement();
		   PreparedStatement pre=null;
		   conn.setAutoCommit(false);// 更改jdbc事务的默认提交方式 
		   String sql = "select * from zero5";//查询语句
		   ResultSet rs = stmt.executeQuery(sql);//得到结果集
		   conn.commit();//事务提交
		   conn.setAutoCommit(true);// 更改jdbc事务的默认提交方式 
		   List<String> list=new ArrayList<String>();//创建取结果的列表，之所以使用列表，不用数组，因为现在还不知道结果有多少，不能确定数组长度，所有先用list接收，然后转为数组
		   while (rs.next()) {//如果有数据，取第一列添加如list
		    list.add(rs.getString(2));
		   }
		   if(list != null && list.size()>0){//如果list中存入了数据，转化为数组
		    String[] arr=new String[list.size()];//创建一个和list长度一样的数组
		    for(int i=0;i<list.size();i++){
		     arr[i]=list.get(i);//数组赋值了。
		    }

			  int oneshenglv=0;
			  int twoshenglv=0;
			  int threeshenglv=0;
			  int fourshenglv=0;
			  int fiveshenglv=0;

		       //输出数组
		       for(int i=0;i<arr.length+100;i++){
		    	int yaoyucededata=1201;//i+1;
		       if(i==(yaoyucededata-1)){
			    	  for(int q=8;q>0;q--){ //应该计入q(i)个数进首行,q<256
			    		  String stringq=String.valueOf(q);

					      String shouhang="";                  //清空首行

				    		  for(int j=(i-1);(i-1)-j<q;j--){                  //这个for记录0到i-1，即起点数
				    			  
				    			  shouhang +=arr[j];		//赋值不同q下的首行
				    		  }
				    		 System.out.println(shouhang);

					    		
				    		  //1 的胜率
				  	    	String sqlselect1="select shenglv from "+"wub"+stringq+" where (shouhang='"+shouhang+"')"
				  	    			+"and"+"(jieguo=0"+")";
				    		ResultSet rs01 = stmt3.executeQuery(sqlselect1);
				    		
				    		//2 的胜率
				  	    	String sqlselect2="select shenglv from "+"wub"+stringq+" where (shouhang='"+shouhang+"')"
				  	    			+"and"+"(jieguo=1"+")";
				    		ResultSet rs02 = stmt4.executeQuery(sqlselect2);
		
				    		//3 的胜率
				  	    	String sqlselect3="select shenglv from "+"wub"+stringq+" where (shouhang='"+shouhang+"')"
				  	    			+"and"+"(jieguo=2"+")";
				    		ResultSet rs03 = stmt5.executeQuery(sqlselect3);
				    		
				    		
				    		//4 的胜率
				  	    	String sqlselect4="select shenglv from "+"wub"+stringq+" where (shouhang='"+shouhang+"')"
				  	    			+"and"+"(jieguo=3"+")";
				    		ResultSet rs04 = stmt6.executeQuery(sqlselect4);
				    		
				    		//5的胜率
				  	    	String sqlselect5="select shenglv from "+"wub"+stringq+" where (shouhang='"+shouhang+"')"
				  	    			+"and"+"(jieguo=4"+")";
				    		ResultSet rs05 = stmt7.executeQuery(sqlselect5);
				    		

				    		
				    		if(rs01.next())
				    			do{
							    System.out.println("oneshenglv"+q+":  "+rs01.getInt(1));
							    oneshenglv +=rs01.getInt(1);
				    			}while(rs01.next());
				    		
				    		

				    		if(rs02.next())
				    			do{
				    				System.out.println("twoshenglv"+q+":  "+rs02.getInt(1));
						    		twoshenglv +=rs02.getInt(1);
				    			}while(rs02.next());
				    		
				    		if(rs03.next())
				    			do{
				    				System.out.println("threeshenglv"+q+":  "+rs03.getInt(1));
						    		threeshenglv +=rs03.getInt(1);
				    			}while(rs03.next());
				    		
				    		if(rs04.next())
				    			do{
				    				System.out.println("fourshenglv"+q+":  "+rs04.getInt(1));
						    		fourshenglv +=rs04.getInt(1);
				    			}while(rs04.next());
				    		
				    		if(rs05.next())
				    			do{
				    				System.out.println("fiveshenglv"+q+":  "+rs05.getInt(1));
						    		fiveshenglv +=rs05.getInt(1);
				    			}while(rs05.next());
				    		
				    		
				    		
			    	  }	
		       }
		       
		       
		     }
		        NumberFormat numberFormat = NumberFormat.getInstance();  
		        
		        // 设置精确到小数点后2位  
		   
		        numberFormat.setMaximumFractionDigits(2); 
		        String result2 = numberFormat.format((float) oneshenglv / (float) (oneshenglv+twoshenglv+threeshenglv+fourshenglv+fiveshenglv) * 100);
		        String result3 = numberFormat.format((float) twoshenglv / (float) (oneshenglv+twoshenglv+threeshenglv+fourshenglv+fiveshenglv) * 100);
		        String result4 = numberFormat.format((float) threeshenglv / (float) (oneshenglv+twoshenglv+threeshenglv+fourshenglv+fiveshenglv) * 100);
		        String result5 = numberFormat.format((float) fourshenglv / (float) (oneshenglv+twoshenglv+threeshenglv+fourshenglv+fiveshenglv) * 100);
		        String result6 = numberFormat.format((float) fiveshenglv / (float) (oneshenglv+twoshenglv+threeshenglv+fourshenglv+fiveshenglv) * 100);
	
		        

		       System.out.println("十位0的胜率 :"+ result2 + "%"+"   "+oneshenglv);
		       System.out.println("十位1的胜率 :"+ result3 + "%"+"   "+twoshenglv);
		       System.out.println("十位2的胜率 :"+ result4 + "%"+"   "+threeshenglv);
		       System.out.println("十位3的胜率 :"+ result5 + "%"+"   "+fourshenglv);
		       System.out.println("十位4的胜率 :"+ result6 + "%"+"   "+fiveshenglv);
		   }
		   
		  } catch (Exception e) {
		   // TODO Auto-generated catch block   
		   e.printStackTrace();
		  }
		}
	
	public void ycwuge(Goddess g)throws Exception{
		 try {
		   Connection conn = DBUtil.getConnection();//建立connection
		   Statement stmt = conn.createStatement();
		   Statement stmt2 = conn.createStatement();
		   Statement stmt3 = conn.createStatement();
		   Statement stmt4 = conn.createStatement();
		   Statement stmt5 = conn.createStatement();
		   Statement stmt6 = conn.createStatement();
		   Statement stmt7 = conn.createStatement();
		   Statement stmt8 = conn.createStatement();
		   Statement stmt9 = conn.createStatement();
		   Statement stmt10 = conn.createStatement();
		   Statement stmt11 = conn.createStatement();
		   PreparedStatement pre=null;
		   conn.setAutoCommit(false);// 更改jdbc事务的默认提交方式 
		   String sql = "select * from zero5";//查询语句
		   ResultSet rs = stmt.executeQuery(sql);//得到结果集
		   conn.commit();//事务提交
		   conn.setAutoCommit(true);// 更改jdbc事务的默认提交方式 
		   List<String> list=new ArrayList<String>();//创建取结果的列表，之所以使用列表，不用数组，因为现在还不知道结果有多少，不能确定数组长度，所有先用list接收，然后转为数组
		   while (rs.next()) {//如果有数据，取第一列添加如list
		    list.add(rs.getString(3));
		   }
		   if(list != null && list.size()>0){//如果list中存入了数据，转化为数组
		    String[] arr=new String[list.size()];//创建一个和list长度一样的数组
		    for(int i=0;i<list.size();i++){
		     arr[i]=list.get(i);//数组赋值了。
		    }

			  int oneshenglv=0;
			  int twoshenglv=0;
			  int threeshenglv=0;
			  int fourshenglv=0;
			  int fiveshenglv=0;

		       //输出数组
		       for(int i=0;i<arr.length+100;i++){
		    	int yaoyucededata=1201;//i+1;
		       if(i==(yaoyucededata-1)){
			    	  for(int q=8;q>0;q--){ //应该计入q(i)个数进首行,q<256
			    		  String stringq=String.valueOf(q);

					      String shouhang="";                  //清空首行

				    		  for(int j=(i-1);(i-1)-j<q;j--){                  //这个for记录0到i-1，即起点数
				    			  
				    			  shouhang +=arr[j];		//赋值不同q下的首行
				    		  }
				    		 System.out.println(shouhang);

					    		
				    		  //1 的胜率
				  	    	String sqlselect1="select shenglv from "+"wuc"+stringq+" where (shouhang='"+shouhang+"')"
				  	    			+"and"+"(jieguo=0"+")";
				    		ResultSet rs01 = stmt3.executeQuery(sqlselect1);
				    		
				    		//2 的胜率
				  	    	String sqlselect2="select shenglv from "+"wuc"+stringq+" where (shouhang='"+shouhang+"')"
				  	    			+"and"+"(jieguo=1"+")";
				    		ResultSet rs02 = stmt4.executeQuery(sqlselect2);
		
				    		//3 的胜率
				  	    	String sqlselect3="select shenglv from "+"wuc"+stringq+" where (shouhang='"+shouhang+"')"
				  	    			+"and"+"(jieguo=2"+")";
				    		ResultSet rs03 = stmt5.executeQuery(sqlselect3);
				    		
				    		
				    		//4 的胜率
				  	    	String sqlselect4="select shenglv from "+"wuc"+stringq+" where (shouhang='"+shouhang+"')"
				  	    			+"and"+"(jieguo=3"+")";
				    		ResultSet rs04 = stmt6.executeQuery(sqlselect4);
				    		
				    		//5的胜率
				  	    	String sqlselect5="select shenglv from "+"wuc"+stringq+" where (shouhang='"+shouhang+"')"
				  	    			+"and"+"(jieguo=4"+")";
				    		ResultSet rs05 = stmt7.executeQuery(sqlselect5);
				    		

				    		
				    		if(rs01.next())
				    			do{
							    System.out.println("oneshenglv"+q+":  "+rs01.getInt(1));
							    oneshenglv +=rs01.getInt(1);
				    			}while(rs01.next());
				    		
				    		

				    		if(rs02.next())
				    			do{
				    				System.out.println("twoshenglv"+q+":  "+rs02.getInt(1));
						    		twoshenglv +=rs02.getInt(1);
				    			}while(rs02.next());
				    		
				    		if(rs03.next())
				    			do{
				    				System.out.println("threeshenglv"+q+":  "+rs03.getInt(1));
						    		threeshenglv +=rs03.getInt(1);
				    			}while(rs03.next());
				    		
				    		if(rs04.next())
				    			do{
				    				System.out.println("fourshenglv"+q+":  "+rs04.getInt(1));
						    		fourshenglv +=rs04.getInt(1);
				    			}while(rs04.next());
				    		
				    		if(rs05.next())
				    			do{
				    				System.out.println("fiveshenglv"+q+":  "+rs05.getInt(1));
						    		fiveshenglv +=rs05.getInt(1);
				    			}while(rs05.next());
				    		
				    		
				    		
			    	  }	
		       }
		       
		       
		     }
		        NumberFormat numberFormat = NumberFormat.getInstance();  
		        
		        // 设置精确到小数点后2位  
		   
		        numberFormat.setMaximumFractionDigits(2); 
		        String result2 = numberFormat.format((float) oneshenglv / (float) (oneshenglv+twoshenglv+threeshenglv+fourshenglv+fiveshenglv) * 100);
		        String result3 = numberFormat.format((float) twoshenglv / (float) (oneshenglv+twoshenglv+threeshenglv+fourshenglv+fiveshenglv) * 100);
		        String result4 = numberFormat.format((float) threeshenglv / (float) (oneshenglv+twoshenglv+threeshenglv+fourshenglv+fiveshenglv) * 100);
		        String result5 = numberFormat.format((float) fourshenglv / (float) (oneshenglv+twoshenglv+threeshenglv+fourshenglv+fiveshenglv) * 100);
		        String result6 = numberFormat.format((float) fiveshenglv / (float) (oneshenglv+twoshenglv+threeshenglv+fourshenglv+fiveshenglv) * 100);
	
		        

		       System.out.println("个位0的胜率 :"+ result2 + "%"+"   "+oneshenglv);
		       System.out.println("个位1的胜率 :"+ result3 + "%"+"   "+twoshenglv);
		       System.out.println("个位2的胜率 :"+ result4 + "%"+"   "+threeshenglv);
		       System.out.println("个位3的胜率 :"+ result5 + "%"+"   "+fourshenglv);
		       System.out.println("个位4的胜率 :"+ result6 + "%"+"   "+fiveshenglv);
		   }
		   
		  } catch (Exception e) {
		   // TODO Auto-generated catch block   
		   e.printStackTrace();
		  }
		}
	
	
	public void getshunxucolsone(Goddess g)throws Exception{
		 try {
		   Connection conn = DBUtil.getConnection();//建立connection
		   Statement stmt = conn.createStatement();
		   PreparedStatement pre=null;
		   conn.setAutoCommit(false);// 更改jdbc事务的默认提交方式 
		   String sql = "select * from zero6";//查询语句
		   ResultSet rs = stmt.executeQuery(sql);//得到结果集
		   conn.commit();//事务提交
		   conn.setAutoCommit(true);// 更改jdbc事务的默认提交方式 
		   List<String> list=new ArrayList<String>();//创建取结果的列表，之所以使用列表，不用数组，因为现在还不知道结果有多少，不能确定数组长度，所有先用list接收，然后转为数组
		   while (rs.next()) {//如果有数据，取第一列添加如list
		    list.add(rs.getString(1));
		   }
		   if(list != null && list.size()>0){//如果list中存入了数据，转化为数组
		    String[] arr=new String[list.size()];//创建一个和list长度一样的数组
		    for(int i=0;i<list.size();i++){
		     arr[i]=list.get(i);//数组赋值了。
		    }
		       //输出数组
		       for(int i=0;i<arr.length;i++){
		    	 
		    	   if(i<11){
		    		   for(int q=i;q>0;q--){ //应该计入q(i)个数进首行,q<256
		    		  String stringq=String.valueOf(q);

				      String shouhang="";                  //清空首行
		    		  if(validateTableNameExist("ashunxu"+stringq)==false){
		    			  //如果table q 不存在,创建一个新table q（i代表前面有i个数）
		    			
		    			  String sql2="CREATE TABLE "+"ashunxu"+stringq
		    					  +"(shouhang TEXT,"+
		    					  "jieguo TEXT,shenglv int(5))";
		    			  stmt.executeUpdate(sql2);
		    		  }
	
		    		  


			    		  for(int j=(i-1);(i-1)-j<q;j--){                  //这个for记录0到i-1，即起点数
			    			  
			    			  shouhang +=arr[j];		//赋值不同q下的首行
			    		  }
			    		  
			    	String sqlselect="select count(*) from "+"ashunxu"+stringq+" where shouhang='"+shouhang+"'"+"and jieguo="+"'"+arr[i]+"'";
			    	
			    	try { 
			    		ResultSet rs2 = stmt.executeQuery(sqlselect); 
			    		int count = 0; 
			    		while (rs2.next()) { 
			    		count = rs2.getInt(1); 
			    		} 
			    		if(count == 0) { 
			    		//没同样的记录,该干什么干什么 
			    			pre=conn.prepareStatement("insert into ashunxu"+stringq+"(shouhang,jieguo,shenglv) values (?,?,?)");
			       		    pre.setString(1, shouhang);
			       		    pre.setString(2, arr[i]);
			       		    pre.setInt(3, 0);
			       		    pre.executeUpdate();	
			    		} else { 
			    		//有同样的记录,该干什么干什么 
			    		//首先读取胜率的数据，然后进行updatae修改
			    			
			    		String sql4="UPDATE "+"ashunxu"+stringq+" SET shenglv =shenglv+1 WHERE shouhang='"+shouhang+"'"+"and jieguo="+"'"+arr[i]+"'";
			    		stmt.executeUpdate(sql4);
			    		} 
			    	} catch (SQLException e) { 
			    	e.printStackTrace(); 
			    	}	
			    		  

		    	  }	
		    	   }
		    	    
 
		    	   if(i>10){
		    		   for(int q=10;q>0;q--){ //应该计入q(i)个数进首行,q<256
				    		  String stringq=String.valueOf(q);

						      String shouhang="";                  //清空首行
				    		  if(validateTableNameExist("ashunxu"+stringq)==false){
				    			  //如果table q 不存在,创建一个新table q（i代表前面有i个数）
				    			
				    			  String sql2="CREATE TABLE "+"ashunxu"+stringq
				    					  +"(shouhang TEXT,"+
				    					  "jieguo TEXT,shenglv int(5))";
				    			  stmt.executeUpdate(sql2);
				    		  }
			
				    		  


					    		  for(int j=(i-1);(i-1)-j<q;j--){                  //这个for记录0到i-1，即起点数
					    			  
					    			  shouhang +=arr[j];		//赋值不同q下的首行
					    		  }
					    		  
					    	String sqlselect="select count(*) from "+"ashunxu"+stringq+" where shouhang='"+shouhang+"'"+"and jieguo="+"'"+arr[i]+"'";
					    	
					    	try { 
					    		ResultSet rs2 = stmt.executeQuery(sqlselect); 
					    		int count = 0; 
					    		while (rs2.next()) { 
					    		count = rs2.getInt(1); 
					    		} 
					    		if(count == 0) { 
					    		//没同样的记录,该干什么干什么 
					    			pre=conn.prepareStatement("insert into ashunxu"+stringq+"(shouhang,jieguo,shenglv) values (?,?,?)");
					       		    pre.setString(1, shouhang);
					       		    pre.setString(2, arr[i]);
					       		    pre.setInt(3, 0);
					       		    pre.executeUpdate();	
					    		} else { 
					    		//有同样的记录,该干什么干什么 
					    		//首先读取胜率的数据，然后进行updatae修改
					    			
					    		String sql4="UPDATE "+"ashunxu"+stringq+" SET shenglv =shenglv+1 WHERE shouhang='"+shouhang+"'"+"and jieguo="+"'"+arr[i]+"'";
					    		stmt.executeUpdate(sql4);
					    		} 
					    	} catch (SQLException e) { 
					    	e.printStackTrace(); 
					    	}	
					    		  

				    	  }	
		    		   
		    	   }
		    	  
		    	  
		       System.out.println(arr[i]+"   baiwei:  "+i);
		     }
		       

		   }
		   
		  } catch (Exception e) {
		   // TODO Auto-generated catch block   
		   e.printStackTrace();
		  }
		}
	
	
	public void getshunxucolstwo(Goddess g)throws Exception{
		 try {
		   Connection conn = DBUtil.getConnection();//建立connection
		   Statement stmt = conn.createStatement();
		   PreparedStatement pre=null;
		   conn.setAutoCommit(false);// 更改jdbc事务的默认提交方式 
		   String sql = "select * from zero6";//查询语句
		   ResultSet rs = stmt.executeQuery(sql);//得到结果集
		   conn.commit();//事务提交
		   conn.setAutoCommit(true);// 更改jdbc事务的默认提交方式 
		   List<String> list=new ArrayList<String>();//创建取结果的列表，之所以使用列表，不用数组，因为现在还不知道结果有多少，不能确定数组长度，所有先用list接收，然后转为数组
		   while (rs.next()) {//如果有数据，取第一列添加如list
		    list.add(rs.getString(2));
		   }
		   if(list != null && list.size()>0){//如果list中存入了数据，转化为数组
		    String[] arr=new String[list.size()];//创建一个和list长度一样的数组
		    for(int i=0;i<list.size();i++){
		     arr[i]=list.get(i);//数组赋值了。
		    }
		       //输出数组
		       for(int i=0;i<arr.length;i++){
		    	   if(i<11){
		    		   for(int q=i;q>0;q--){ //应该计入q(i)个数进首行,q<256
				    		  String stringq=String.valueOf(q);

						      String shouhang="";                  //清空首行
				    		  if(validateTableNameExist("shunxub"+stringq)==false){
				    			  //如果table q 不存在,创建一个新table q（i代表前面有i个数）
				    			
				    			  String sql2="CREATE TABLE "+"shunxub"+stringq
				    					  +"(shouhang TEXT,"+
				    					  "jieguo TEXT,shenglv int(5))";
				    			  stmt.executeUpdate(sql2);
				    		  }
			
				    		  


					    		  for(int j=(i-1);(i-1)-j<q;j--){                  //这个for记录0到i-1，即起点数
					    			  
					    			  shouhang +=arr[j];		//赋值不同q下的首行
					    		  }
					    		  
					    	String sqlselect="select count(*) from "+"shunxub"+stringq+" where shouhang='"+shouhang+"'"+"and jieguo="+"'"+arr[i]+"'";
					    	
					    	try { 
					    		ResultSet rs2 = stmt.executeQuery(sqlselect); 
					    		int count = 0; 
					    		while (rs2.next()) { 
					    		count = rs2.getInt(1); 
					    		} 
					    		if(count == 0) { 
					    		//没同样的记录,该干什么干什么 
					    			pre=conn.prepareStatement("insert into shunxub"+stringq+"(shouhang,jieguo,shenglv) values (?,?,?)");
					       		    pre.setString(1, shouhang);
					       		    pre.setString(2, arr[i]);
					       		    pre.setInt(3, 0);
					       		    pre.executeUpdate();	
					    		} else { 
					    		//有同样的记录,该干什么干什么 
					    		//首先读取胜率的数据，然后进行updatae修改
					    			
					    		String sql4="UPDATE "+"shunxub"+stringq+" SET shenglv =shenglv+1 WHERE shouhang='"+shouhang+"'"+"and jieguo="+"'"+arr[i]+"'";
					    		stmt.executeUpdate(sql4);
					    		} 
					    	} catch (SQLException e) { 
					    	e.printStackTrace(); 
					    	}	
					    		  

				    	  }
		    	   }
		    	  if(i>10){
		    		  for(int q=10;q>0;q--){ //应该计入q(i)个数进首行,q<256
			    		  String stringq=String.valueOf(q);

					      String shouhang="";                  //清空首行
			    		  if(validateTableNameExist("shunxub"+stringq)==false){
			    			  //如果table q 不存在,创建一个新table q（i代表前面有i个数）
			    			
			    			  String sql2="CREATE TABLE "+"shunxub"+stringq
			    					  +"(shouhang TEXT,"+
			    					  "jieguo TEXT,shenglv int(5))";
			    			  stmt.executeUpdate(sql2);
			    		  }
		
			    		  


				    		  for(int j=(i-1);(i-1)-j<q;j--){                  //这个for记录0到i-1，即起点数
				    			  
				    			  shouhang +=arr[j];		//赋值不同q下的首行
				    		  }
				    		  
				    	String sqlselect="select count(*) from "+"shunxub"+stringq+" where shouhang='"+shouhang+"'"+"and jieguo="+"'"+arr[i]+"'";
				    	
				    	try { 
				    		ResultSet rs2 = stmt.executeQuery(sqlselect); 
				    		int count = 0; 
				    		while (rs2.next()) { 
				    		count = rs2.getInt(1); 
				    		} 
				    		if(count == 0) { 
				    		//没同样的记录,该干什么干什么 
				    			pre=conn.prepareStatement("insert into shunxub"+stringq+"(shouhang,jieguo,shenglv) values (?,?,?)");
				       		    pre.setString(1, shouhang);
				       		    pre.setString(2, arr[i]);
				       		    pre.setInt(3, 0);
				       		    pre.executeUpdate();	
				    		} else { 
				    		//有同样的记录,该干什么干什么 
				    		//首先读取胜率的数据，然后进行updatae修改
				    			
				    		String sql4="UPDATE "+"shunxub"+stringq+" SET shenglv =shenglv+1 WHERE shouhang='"+shouhang+"'"+"and jieguo="+"'"+arr[i]+"'";
				    		stmt.executeUpdate(sql4);
				    		} 
				    	} catch (SQLException e) { 
				    	e.printStackTrace(); 
				    	}	
				    		  

			    	  }
		    	  }
		    	  	  

		       System.out.println(arr[i]+"   shiwei:  "+i);
		     }
		       

		   }
		   
		  } catch (Exception e) {
		   // TODO Auto-generated catch block   
		   e.printStackTrace();
		  }
		}
	
	public void getshunxucolsthree(Goddess g)throws Exception{
		 try {
		   Connection conn = DBUtil.getConnection();//建立connection
		   Statement stmt = conn.createStatement();
		   PreparedStatement pre=null;
		   conn.setAutoCommit(false);// 更改jdbc事务的默认提交方式 
		   String sql = "select * from zero6";//查询语句
		   ResultSet rs = stmt.executeQuery(sql);//得到结果集
		   conn.commit();//事务提交
		   conn.setAutoCommit(true);// 更改jdbc事务的默认提交方式 
		   List<String> list=new ArrayList<String>();//创建取结果的列表，之所以使用列表，不用数组，因为现在还不知道结果有多少，不能确定数组长度，所有先用list接收，然后转为数组
		   while (rs.next()) {//如果有数据，取第一列添加如list
		    list.add(rs.getString(3));
		   }
		   if(list != null && list.size()>0){//如果list中存入了数据，转化为数组
		    String[] arr=new String[list.size()];//创建一个和list长度一样的数组
		    for(int i=0;i<list.size();i++){
		     arr[i]=list.get(i);//数组赋值了。
		    }
		       //输出数组
		       for(int i=0;i<arr.length;i++){
		     if(i<11){
		    	 for(int q=i;q>0;q--){ //应该计入q(i)个数进首行,q<256
		    		  String stringq=String.valueOf(q);

				      String shouhang="";                  //清空首行
		    		  if(validateTableNameExist("shunxuc"+stringq)==false){
		    			  //如果table q 不存在,创建一个新table q（i代表前面有i个数）
		    			
		    			  String sql2="CREATE TABLE "+"shunxuc"+stringq
		    					  +"(shouhang TEXT,"+
		    					  "jieguo TEXT,shenglv int(5))";
		    			  stmt.executeUpdate(sql2);
		    		  }
	
		    		  


			    		  for(int j=(i-1);(i-1)-j<q;j--){                  //这个for记录0到i-1，即起点数
			    			  
			    			  shouhang +=arr[j];		//赋值不同q下的首行
			    		  }
			    		  
			    	String sqlselect="select count(*) from "+"shunxuc"+stringq+" where shouhang='"+shouhang+"'"+"and jieguo="+"'"+arr[i]+"'";
			    	
			    	try { 
			    		ResultSet rs2 = stmt.executeQuery(sqlselect); 
			    		int count = 0; 
			    		while (rs2.next()) { 
			    		count = rs2.getInt(1); 
			    		} 
			    		if(count == 0) { 
			    		//没同样的记录,该干什么干什么 
			    			pre=conn.prepareStatement("insert into shunxuc"+stringq+"(shouhang,jieguo,shenglv) values (?,?,?)");
			       		    pre.setString(1, shouhang);
			       		    pre.setString(2, arr[i]);
			       		    pre.setInt(3, 0);
			       		    pre.executeUpdate();	
			    		} else { 
			    		//有同样的记录,该干什么干什么 
			    		//首先读取胜率的数据，然后进行updatae修改
			    			
			    		String sql4="UPDATE "+"shunxuc"+stringq+" SET shenglv =shenglv+1 WHERE shouhang='"+shouhang+"'"+"and jieguo="+"'"+arr[i]+"'";
			    		stmt.executeUpdate(sql4);
			    		} 
			    	} catch (SQLException e) { 
			    	e.printStackTrace(); 
			    	}	
			    		  

		    	  }
		     }
		     
		     if(i>10){
		    	 for(int q=10;q>0;q--){ //应该计入q(i)个数进首行,q<256
		    		  String stringq=String.valueOf(q);

				      String shouhang="";                  //清空首行
		    		  if(validateTableNameExist("shunxuc"+stringq)==false){
		    			  //如果table q 不存在,创建一个新table q（i代表前面有i个数）
		    			
		    			  String sql2="CREATE TABLE "+"shunxuc"+stringq
		    					  +"(shouhang TEXT,"+
		    					  "jieguo TEXT,shenglv int(5))";
		    			  stmt.executeUpdate(sql2);
		    		  }
	
		    		  


			    		  for(int j=(i-1);(i-1)-j<q;j--){                  //这个for记录0到i-1，即起点数
			    			  
			    			  shouhang +=arr[j];		//赋值不同q下的首行
			    		  }
			    		  
			    	String sqlselect="select count(*) from "+"shunxuc"+stringq+" where shouhang='"+shouhang+"'"+"and jieguo="+"'"+arr[i]+"'";
			    	
			    	try { 
			    		ResultSet rs2 = stmt.executeQuery(sqlselect); 
			    		int count = 0; 
			    		while (rs2.next()) { 
			    		count = rs2.getInt(1); 
			    		} 
			    		if(count == 0) { 
			    		//没同样的记录,该干什么干什么 
			    			pre=conn.prepareStatement("insert into shunxuc"+stringq+"(shouhang,jieguo,shenglv) values (?,?,?)");
			       		    pre.setString(1, shouhang);
			       		    pre.setString(2, arr[i]);
			       		    pre.setInt(3, 0);
			       		    pre.executeUpdate();	
			    		} else { 
			    		//有同样的记录,该干什么干什么 
			    		//首先读取胜率的数据，然后进行updatae修改
			    			
			    		String sql4="UPDATE "+"shunxuc"+stringq+" SET shenglv =shenglv+1 WHERE shouhang='"+shouhang+"'"+"and jieguo="+"'"+arr[i]+"'";
			    		stmt.executeUpdate(sql4);
			    		} 
			    	} catch (SQLException e) { 
			    	e.printStackTrace(); 
			    	}	
			    		  

		    	  }
		     }
		    	  	  

		       System.out.println(arr[i]+"   gewei:  "+i);
		     }
		       

		   }
		   
		  } catch (Exception e) {
		   // TODO Auto-generated catch block   
		   e.printStackTrace();
		  }
		}
	
	
	
	
	public void ycshunxubai(Goddess g)throws Exception{
		 try {
		   Connection conn = DBUtil.getConnection();//建立connection
		   Statement stmt = conn.createStatement();
		   Statement stmt2 = conn.createStatement();
		   Statement stmt3 = conn.createStatement();
		   Statement stmt4 = conn.createStatement();
		   PreparedStatement pre=null;
		   conn.setAutoCommit(false);// 更改jdbc事务的默认提交方式 
		   String sql = "select * from zero6";//查询语句
		   ResultSet rs = stmt.executeQuery(sql);//得到结果集
		   conn.commit();//事务提交
		   conn.setAutoCommit(true);// 更改jdbc事务的默认提交方式 
		   List<String> list=new ArrayList<String>();//创建取结果的列表，之所以使用列表，不用数组，因为现在还不知道结果有多少，不能确定数组长度，所有先用list接收，然后转为数组
		   while (rs.next()) {//如果有数据，取第一列添加如list
		    list.add(rs.getString(1));
		   }
		   if(list != null && list.size()>0){//如果list中存入了数据，转化为数组
		    String[] arr=new String[list.size()];//创建一个和list长度一样的数组
		    for(int i=0;i<list.size();i++){
		     arr[i]=list.get(i);//数组赋值了。
		    }
		      int zeroshenglv=0;
			  int oneshenglv=0;
			  int twoshenglv=0;
		       //输出数组
		       for(int i=0;i<arr.length+100;i++){
		    	int yaoyucededata=1201;//i+1;
		       if(i==(yaoyucededata-1)){
			    	  for(int q=10;q>0;q--){ //应该计入q(i)个数进首行,q<256
			    		  String stringq=String.valueOf(q);

					      String shouhang="";                  //清空首行

				    		  for(int j=(i-1);(i-1)-j<q;j--){                  //这个for记录0到i-1，即起点数
				    			  
				    			  shouhang +=arr[j];		//赋值不同q下的首行
				    		  }
				    		 System.out.println(shouhang);
				    	   	//0 的胜率
					  	    String sqlselect0="select shenglv from "+"ashunxu"+stringq+" where (shouhang='"+shouhang+"')"
					  	    			+"and"+"(jieguo=0"+")";
					    	ResultSet rs00 = stmt2.executeQuery(sqlselect0);
					    		
				    		  //1 的胜率
				  	    	String sqlselect1="select shenglv from "+"ashunxu"+stringq+" where (shouhang='"+shouhang+"')"
				  	    			+"and"+"(jieguo=1"+")";
				    		ResultSet rs01 = stmt3.executeQuery(sqlselect1);
				    		
				    		//2 的胜率
				  	    	String sqlselect2="select shenglv from "+"ashunxu"+stringq+" where (shouhang='"+shouhang+"')"
				  	    			+"and"+"(jieguo=2"+")";
				    		ResultSet rs02 = stmt4.executeQuery(sqlselect2);
				  
				    		if(rs01.next())
				    			do{
							    System.out.println("oneshenglv"+q+":  "+rs01.getInt(1));
							    oneshenglv +=rs01.getInt(1);
				    			}while(rs01.next());
				    		if(rs00.next())
				    			do{
						    		System.out.println("zeroshenglv"+q+":  "+rs00.getInt(1));
						    		zeroshenglv +=rs00.getInt(1);
				    			}while(rs00.next());
				    		if(rs02.next())
				    			do{
				    				System.out.println("twoshenglv"+q+":  "+rs02.getInt(1));
						    		twoshenglv +=rs02.getInt(1);
				    			}while(rs02.next());
				    		
				    		
			    	  }	
		       }
		       
		       
		     }
		        NumberFormat numberFormat = NumberFormat.getInstance();  
		        
		        // 设置精确到小数点后2位  
		   
		        numberFormat.setMaximumFractionDigits(2); 
		        String result1 = numberFormat.format((float) zeroshenglv / (float) (zeroshenglv+oneshenglv+twoshenglv) * 100);
		        String result2 = numberFormat.format((float) oneshenglv / (float) (zeroshenglv+oneshenglv+twoshenglv) * 100);
		        String result3 = numberFormat.format((float) twoshenglv / (float) (zeroshenglv+oneshenglv+twoshenglv) * 100);
		       System.out.println("百位0的胜率 :"+ result1 + "%"+"   "+zeroshenglv);
		       System.out.println("百位1的胜率 :"+ result2 + "%"+"   "+oneshenglv);
		       System.out.println("百位2的胜率 :"+ result3 + "%"+"   "+twoshenglv);
		   }
		   
		  } catch (Exception e) {
		   // TODO Auto-generated catch block   
		   e.printStackTrace();
		  }
		}
	
	
	public void ycshunxushi(Goddess g)throws Exception{
		 try {
		   Connection conn = DBUtil.getConnection();//建立connection
		   Statement stmt = conn.createStatement();
		   Statement stmt2 = conn.createStatement();
		   Statement stmt3 = conn.createStatement();
		   Statement stmt4 = conn.createStatement();
		   PreparedStatement pre=null;
		   conn.setAutoCommit(false);// 更改jdbc事务的默认提交方式 
		   String sql = "select * from zero6";//查询语句
		   ResultSet rs = stmt.executeQuery(sql);//得到结果集
		   conn.commit();//事务提交
		   conn.setAutoCommit(true);// 更改jdbc事务的默认提交方式 
		   List<String> list=new ArrayList<String>();//创建取结果的列表，之所以使用列表，不用数组，因为现在还不知道结果有多少，不能确定数组长度，所有先用list接收，然后转为数组
		   while (rs.next()) {//如果有数据，取第一列添加如list
		    list.add(rs.getString(2));
		   }
		   if(list != null && list.size()>0){//如果list中存入了数据，转化为数组
		    String[] arr=new String[list.size()];//创建一个和list长度一样的数组
		    for(int i=0;i<list.size();i++){
		     arr[i]=list.get(i);//数组赋值了。
		    }
		      int zeroshenglv=0;
			  int oneshenglv=0;
			  int twoshenglv=0;
		       //输出数组
		       for(int i=0;i<arr.length+100;i++){
		    	int yaoyucededata=1201;
		       if(i==(yaoyucededata-1)){
			    	  for(int q=10;q>0;q--){ //应该计入q(i)个数进首行,q<256
			    		  String stringq=String.valueOf(q);

					      String shouhang="";                  //清空首行

				    		  for(int j=(i-1);(i-1)-j<q;j--){                  //这个for记录0到i-1，即起点数
				    			  
				    			  shouhang +=arr[j];		//赋值不同q下的首行
				    		  }
				    		 
				    	   	//0 的胜率
					  	    String sqlselect0="select shenglv from "+"shunxub"+stringq+" where (shouhang='"+shouhang+"')"
					  	    			+"and"+"(jieguo=0"+")";
					    	ResultSet rs00 = stmt2.executeQuery(sqlselect0);
					    		
				    		  //1 的胜率
				  	    	String sqlselect1="select shenglv from "+"shunxub"+stringq+" where (shouhang='"+shouhang+"')"
				  	    			+"and"+"(jieguo=1"+")";
				    		ResultSet rs01 = stmt3.executeQuery(sqlselect1);
				    		
				    		//2 的胜率
				  	    	String sqlselect2="select shenglv from "+"shunxub"+stringq+" where (shouhang='"+shouhang+"')"
				  	    			+"and"+"(jieguo=2"+")";
				    		ResultSet rs02 = stmt4.executeQuery(sqlselect2);
				  
				    		if(rs01.next())
				    			do{
							    System.out.println("oneshenglv"+q+":  "+rs01.getInt(1));
							    oneshenglv +=rs01.getInt(1);
				    			}while(rs01.next());
				    		if(rs00.next())
				    			do{
						    		System.out.println("zeroshenglv"+q+":  "+rs00.getInt(1));
						    		zeroshenglv +=rs00.getInt(1);
				    			}while(rs00.next());
				    		if(rs02.next())
				    			do{
				    				System.out.println("twoshenglv"+q+":  "+rs02.getInt(1));
						    		twoshenglv +=rs02.getInt(1);
				    			}while(rs02.next());
				    		
				    		
			    	  }	
		       }
		       
		       
		     }
		        NumberFormat numberFormat = NumberFormat.getInstance();  
		        
		        // 设置精确到小数点后2位  
		   
		        numberFormat.setMaximumFractionDigits(2); 
		        String result1 = numberFormat.format((float) zeroshenglv / (float) (zeroshenglv+oneshenglv+twoshenglv) * 100);
		        String result2 = numberFormat.format((float) oneshenglv / (float) (zeroshenglv+oneshenglv+twoshenglv) * 100);
		        String result3 = numberFormat.format((float) twoshenglv / (float) (zeroshenglv+oneshenglv+twoshenglv) * 100);
		       System.out.println("十位0的胜率 :"+ result1 + "%"+"   "+zeroshenglv);
		       System.out.println("十位1的胜率 :"+ result2 + "%"+"   "+oneshenglv);
		       System.out.println("十位2的胜率 :"+ result3 + "%"+"   "+twoshenglv);
		   }
		   
		  } catch (Exception e) {
		   // TODO Auto-generated catch block   
		   e.printStackTrace();
		  }
		}
	
	public void ycshunxuge(Goddess g)throws Exception{
		 try {
		   Connection conn = DBUtil.getConnection();//建立connection
		   Statement stmt = conn.createStatement();
		   Statement stmt2 = conn.createStatement();
		   Statement stmt3 = conn.createStatement();
		   Statement stmt4 = conn.createStatement();
		   PreparedStatement pre=null;
		   conn.setAutoCommit(false);// 更改jdbc事务的默认提交方式 
		   String sql = "select * from zero6";//查询语句
		   ResultSet rs = stmt.executeQuery(sql);//得到结果集
		   conn.commit();//事务提交
		   conn.setAutoCommit(true);// 更改jdbc事务的默认提交方式 
		   List<String> list=new ArrayList<String>();//创建取结果的列表，之所以使用列表，不用数组，因为现在还不知道结果有多少，不能确定数组长度，所有先用list接收，然后转为数组
		   while (rs.next()) {//如果有数据，取第一列添加如list
		    list.add(rs.getString(3));
		   }
		   if(list != null && list.size()>0){//如果list中存入了数据，转化为数组
		    String[] arr=new String[list.size()];//创建一个和list长度一样的数组
		    for(int i=0;i<list.size();i++){
		     arr[i]=list.get(i);//数组赋值了。
		    }
		      int zeroshenglv=0;
			  int oneshenglv=0;
			  int twoshenglv=0;
		       //输出数组
		       for(int i=0;i<arr.length+100;i++){
		    	int yaoyucededata=1201;
		       if(i==(yaoyucededata-1)){
			    	  for(int q=10;q>0;q--){ //应该计入q(i)个数进首行,q<256
			    		  String stringq=String.valueOf(q);

					      String shouhang="";                  //清空首行

				    		  for(int j=(i-1);(i-1)-j<q;j--){                  //这个for记录0到i-1，即起点数
				    			  
				    			  shouhang +=arr[j];		//赋值不同q下的首行
				    		  }
				    		 
				    	   	//0 的胜率
					  	    String sqlselect0="select shenglv from "+"shunxuc"+stringq+" where (shouhang='"+shouhang+"')"
					  	    			+"and"+"(jieguo=0"+")";
					    	ResultSet rs00 = stmt2.executeQuery(sqlselect0);
					    		
				    		  //1 的胜率
				  	    	String sqlselect1="select shenglv from "+"shunxuc"+stringq+" where (shouhang='"+shouhang+"')"
				  	    			+"and"+"(jieguo=1"+")";
				    		ResultSet rs01 = stmt3.executeQuery(sqlselect1);
				    		
				    		//2 的胜率
				  	    	String sqlselect2="select shenglv from "+"shunxuc"+stringq+" where (shouhang='"+shouhang+"')"
				  	    			+"and"+"(jieguo=2"+")";
				    		ResultSet rs02 = stmt4.executeQuery(sqlselect2);
				  
				    		if(rs01.next())
				    			do{
							    System.out.println("oneshenglv"+q+":  "+rs01.getInt(1));
							    oneshenglv +=rs01.getInt(1);
				    			}while(rs01.next());
				    		if(rs00.next())
				    			do{
						    		System.out.println("zeroshenglv"+q+":  "+rs00.getInt(1));
						    		zeroshenglv +=rs00.getInt(1);
				    			}while(rs00.next());
				    		if(rs02.next())
				    			do{
				    				System.out.println("twoshenglv"+q+":  "+rs02.getInt(1));
						    		twoshenglv +=rs02.getInt(1);
				    			}while(rs02.next());
				    		
				    		
			    	  }	
		       }
		       
		       
		     }
		        NumberFormat numberFormat = NumberFormat.getInstance();  
		        
		        // 设置精确到小数点后2位  
		   
		        numberFormat.setMaximumFractionDigits(2); 
		        String result1 = numberFormat.format((float) zeroshenglv / (float) (zeroshenglv+oneshenglv+twoshenglv) * 100);
		        String result2 = numberFormat.format((float) oneshenglv / (float) (zeroshenglv+oneshenglv+twoshenglv) * 100);
		        String result3 = numberFormat.format((float) twoshenglv / (float) (zeroshenglv+oneshenglv+twoshenglv) * 100);
		       System.out.println("个位0的胜率 :"+ result1 + "%"+"   "+zeroshenglv);
		       System.out.println("个位1的胜率 :"+ result2 + "%"+"   "+oneshenglv);
		       System.out.println("个位2的胜率 :"+ result3 + "%"+"   "+twoshenglv);
		   }
		   
		  } catch (Exception e) {
		   // TODO Auto-generated catch block   
		   e.printStackTrace();
		  }
		}
	
	public void getchazhicolsone(Goddess g)throws Exception{
		 try {
		   Connection conn = DBUtil.getConnection();//建立connection
		   Statement stmt = conn.createStatement();
		   PreparedStatement pre=null;
		   conn.setAutoCommit(false);// 更改jdbc事务的默认提交方式 
		   String sql = "select * from zero7";//查询语句
		   ResultSet rs = stmt.executeQuery(sql);//得到结果集
		   conn.commit();//事务提交
		   conn.setAutoCommit(true);// 更改jdbc事务的默认提交方式 
		   List<String> list=new ArrayList<String>();//创建取结果的列表，之所以使用列表，不用数组，因为现在还不知道结果有多少，不能确定数组长度，所有先用list接收，然后转为数组
		   while (rs.next()) {//如果有数据，取第一列添加如list
		    list.add(rs.getString(1));
		   }
		   if(list != null && list.size()>0){//如果list中存入了数据，转化为数组
		    String[] arr=new String[list.size()];//创建一个和list长度一样的数组
		    for(int i=0;i<list.size();i++){
		     arr[i]=list.get(i);//数组赋值了。
		    }
		       //输出数组
		       for(int i=0;i<arr.length;i++){
		    	 
		    	   if(i<11){
		    		   for(int q=i;q>0;q--){ //应该计入q(i)个数进首行,q<256
		    		  String stringq=String.valueOf(q);

				      String shouhang="";                  //清空首行
		    		  if(validateTableNameExist("ac"+stringq)==false){
		    			  //如果table q 不存在,创建一个新table q（i代表前面有i个数）
		    			
		    			  String sql2="CREATE TABLE "+"ac"+stringq
		    					  +"(shouhang TEXT,"+
		    					  "jieguo TEXT,shenglv int(5))";
		    			  stmt.executeUpdate(sql2);
		    		  }
	
		    		  


			    		  for(int j=(i-1);(i-1)-j<q;j--){                  //这个for记录0到i-1，即起点数
			    			  
			    			  shouhang +=arr[j];		//赋值不同q下的首行
			    		  }
			    		  
			    	String sqlselect="select count(*) from "+"ac"+stringq+" where shouhang='"+shouhang+"'"+"and jieguo="+"'"+arr[i]+"'";
			    	
			    	try { 
			    		ResultSet rs2 = stmt.executeQuery(sqlselect); 
			    		int count = 0; 
			    		while (rs2.next()) { 
			    		count = rs2.getInt(1); 
			    		} 
			    		if(count == 0) { 
			    		//没同样的记录,该干什么干什么 
			    			pre=conn.prepareStatement("insert into ac"+stringq+"(shouhang,jieguo,shenglv) values (?,?,?)");
			       		    pre.setString(1, shouhang);
			       		    pre.setString(2, arr[i]);
			       		    pre.setInt(3, 0);
			       		    pre.executeUpdate();	
			    		} else { 
			    		//有同样的记录,该干什么干什么 
			    		//首先读取胜率的数据，然后进行updatae修改
			    			
			    		String sql4="UPDATE "+"ac"+stringq+" SET shenglv =shenglv+1 WHERE shouhang='"+shouhang+"'"+"and jieguo="+"'"+arr[i]+"'";
			    		stmt.executeUpdate(sql4);
			    		} 
			    	} catch (SQLException e) { 
			    	e.printStackTrace(); 
			    	}	
			    		  

		    	  }	
		    	   }
		    	    
 
		    	   if(i>10){
		    		   for(int q=10;q>0;q--){ //应该计入q(i)个数进首行,q<256
				    		  String stringq=String.valueOf(q);

						      String shouhang="";                  //清空首行
				    		  if(validateTableNameExist("ac"+stringq)==false){
				    			  //如果table q 不存在,创建一个新table q（i代表前面有i个数）
				    			
				    			  String sql2="CREATE TABLE "+"ac"+stringq
				    					  +"(shouhang TEXT,"+
				    					  "jieguo TEXT,shenglv int(5))";
				    			  stmt.executeUpdate(sql2);
				    		  }
			
				    		  


					    		  for(int j=(i-1);(i-1)-j<q;j--){                  //这个for记录0到i-1，即起点数
					    			  
					    			  shouhang +=arr[j];		//赋值不同q下的首行
					    		  }
					    		  
					    	String sqlselect="select count(*) from "+"ac"+stringq+" where shouhang='"+shouhang+"'"+"and jieguo="+"'"+arr[i]+"'";
					    	
					    	try { 
					    		ResultSet rs2 = stmt.executeQuery(sqlselect); 
					    		int count = 0; 
					    		while (rs2.next()) { 
					    		count = rs2.getInt(1); 
					    		} 
					    		if(count == 0) { 
					    		//没同样的记录,该干什么干什么 
					    			pre=conn.prepareStatement("insert into ac"+stringq+"(shouhang,jieguo,shenglv) values (?,?,?)");
					       		    pre.setString(1, shouhang);
					       		    pre.setString(2, arr[i]);
					       		    pre.setInt(3, 0);
					       		    pre.executeUpdate();	
					    		} else { 
					    		//有同样的记录,该干什么干什么 
					    		//首先读取胜率的数据，然后进行updatae修改
					    			
					    		String sql4="UPDATE "+"ac"+stringq+" SET shenglv =shenglv+1 WHERE shouhang='"+shouhang+"'"+"and jieguo="+"'"+arr[i]+"'";
					    		stmt.executeUpdate(sql4);
					    		} 
					    	} catch (SQLException e) { 
					    	e.printStackTrace(); 
					    	}	
					    		  

				    	  }	
		    		   
		    	   }
		    	  
		    	  
		       System.out.println(arr[i]+"   chazhibaiwei:  "+i);
		     }
		       

		   }
		   
		  } catch (Exception e) {
		   // TODO Auto-generated catch block   
		   e.printStackTrace();
		  }
		}
	
	public void getchazhicolstwo(Goddess g)throws Exception{
		 try {
		   Connection conn = DBUtil.getConnection();//建立connection
		   Statement stmt = conn.createStatement();
		   PreparedStatement pre=null;
		   conn.setAutoCommit(false);// 更改jdbc事务的默认提交方式 
		   String sql = "select * from zero7";//查询语句
		   ResultSet rs = stmt.executeQuery(sql);//得到结果集
		   conn.commit();//事务提交
		   conn.setAutoCommit(true);// 更改jdbc事务的默认提交方式 
		   List<String> list=new ArrayList<String>();//创建取结果的列表，之所以使用列表，不用数组，因为现在还不知道结果有多少，不能确定数组长度，所有先用list接收，然后转为数组
		   while (rs.next()) {//如果有数据，取第一列添加如list
		    list.add(rs.getString(2));
		   }
		   if(list != null && list.size()>0){//如果list中存入了数据，转化为数组
		    String[] arr=new String[list.size()];//创建一个和list长度一样的数组
		    for(int i=0;i<list.size();i++){
		     arr[i]=list.get(i);//数组赋值了。
		    }
		       //输出数组
		       for(int i=0;i<arr.length;i++){
		    	   if(i<11){
		    		   for(int q=i;q>0;q--){ //应该计入q(i)个数进首行,q<256
				    		  String stringq=String.valueOf(q);

						      String shouhang="";                  //清空首行
				    		  if(validateTableNameExist("bc"+stringq)==false){
				    			  //如果table q 不存在,创建一个新table q（i代表前面有i个数）
				    			
				    			  String sql2="CREATE TABLE "+"bc"+stringq
				    					  +"(shouhang TEXT,"+
				    					  "jieguo TEXT,shenglv int(5))";
				    			  stmt.executeUpdate(sql2);
				    		  }
			
				    		  


					    		  for(int j=(i-1);(i-1)-j<q;j--){                  //这个for记录0到i-1，即起点数
					    			  
					    			  shouhang +=arr[j];		//赋值不同q下的首行
					    		  }
					    		  
					    	String sqlselect="select count(*) from "+"bc"+stringq+" where shouhang='"+shouhang+"'"+"and jieguo="+"'"+arr[i]+"'";
					    	
					    	try { 
					    		ResultSet rs2 = stmt.executeQuery(sqlselect); 
					    		int count = 0; 
					    		while (rs2.next()) { 
					    		count = rs2.getInt(1); 
					    		} 
					    		if(count == 0) { 
					    		//没同样的记录,该干什么干什么 
					    			pre=conn.prepareStatement("insert into bc"+stringq+"(shouhang,jieguo,shenglv) values (?,?,?)");
					       		    pre.setString(1, shouhang);
					       		    pre.setString(2, arr[i]);
					       		    pre.setInt(3, 0);
					       		    pre.executeUpdate();	
					    		} else { 
					    		//有同样的记录,该干什么干什么 
					    		//首先读取胜率的数据，然后进行updatae修改
					    			
					    		String sql4="UPDATE "+"bc"+stringq+" SET shenglv =shenglv+1 WHERE shouhang='"+shouhang+"'"+"and jieguo="+"'"+arr[i]+"'";
					    		stmt.executeUpdate(sql4);
					    		} 
					    	} catch (SQLException e) { 
					    	e.printStackTrace(); 
					    	}	
					    		  

				    	  }
		    	   }
		    	  if(i>10){
		    		  for(int q=10;q>0;q--){ //应该计入q(i)个数进首行,q<256
			    		  String stringq=String.valueOf(q);

					      String shouhang="";                  //清空首行
			    		  if(validateTableNameExist("bc"+stringq)==false){
			    			  //如果table q 不存在,创建一个新table q（i代表前面有i个数）
			    			
			    			  String sql2="CREATE TABLE "+"bc"+stringq
			    					  +"(shouhang TEXT,"+
			    					  "jieguo TEXT,shenglv int(5))";
			    			  stmt.executeUpdate(sql2);
			    		  }
		
			    		  


				    		  for(int j=(i-1);(i-1)-j<q;j--){                  //这个for记录0到i-1，即起点数
				    			  
				    			  shouhang +=arr[j];		//赋值不同q下的首行
				    		  }
				    		  
				    	String sqlselect="select count(*) from "+"bc"+stringq+" where shouhang='"+shouhang+"'"+"and jieguo="+"'"+arr[i]+"'";
				    	
				    	try { 
				    		ResultSet rs2 = stmt.executeQuery(sqlselect); 
				    		int count = 0; 
				    		while (rs2.next()) { 
				    		count = rs2.getInt(1); 
				    		} 
				    		if(count == 0) { 
				    		//没同样的记录,该干什么干什么 
				    			pre=conn.prepareStatement("insert into bc"+stringq+"(shouhang,jieguo,shenglv) values (?,?,?)");
				       		    pre.setString(1, shouhang);
				       		    pre.setString(2, arr[i]);
				       		    pre.setInt(3, 0);
				       		    pre.executeUpdate();	
				    		} else { 
				    		//有同样的记录,该干什么干什么 
				    		//首先读取胜率的数据，然后进行updatae修改
				    			
				    		String sql4="UPDATE "+"bc"+stringq+" SET shenglv =shenglv+1 WHERE shouhang='"+shouhang+"'"+"and jieguo="+"'"+arr[i]+"'";
				    		stmt.executeUpdate(sql4);
				    		} 
				    	} catch (SQLException e) { 
				    	e.printStackTrace(); 
				    	}	
				    		  

			    	  }
		    	  }
		    	  	  

		       System.out.println(arr[i]+"   chazhishiwei:  "+i);
		     }
		       

		   }
		   
		  } catch (Exception e) {
		   // TODO Auto-generated catch block   
		   e.printStackTrace();
		  }
		}
	
	public void getchazhicolsthree(Goddess g)throws Exception{
		 try {
		   Connection conn = DBUtil.getConnection();//建立connection
		   Statement stmt = conn.createStatement();
		   PreparedStatement pre=null;
		   conn.setAutoCommit(false);// 更改jdbc事务的默认提交方式 
		   String sql = "select * from zero7";//查询语句
		   ResultSet rs = stmt.executeQuery(sql);//得到结果集
		   conn.commit();//事务提交
		   conn.setAutoCommit(true);// 更改jdbc事务的默认提交方式 
		   List<String> list=new ArrayList<String>();//创建取结果的列表，之所以使用列表，不用数组，因为现在还不知道结果有多少，不能确定数组长度，所有先用list接收，然后转为数组
		   while (rs.next()) {//如果有数据，取第一列添加如list
		    list.add(rs.getString(3));
		   }
		   if(list != null && list.size()>0){//如果list中存入了数据，转化为数组
		    String[] arr=new String[list.size()];//创建一个和list长度一样的数组
		    for(int i=0;i<list.size();i++){
		     arr[i]=list.get(i);//数组赋值了。
		    }
		       //输出数组
		       for(int i=0;i<arr.length;i++){
		     if(i<11){
		    	 for(int q=i;q>0;q--){ //应该计入q(i)个数进首行,q<256
		    		  String stringq=String.valueOf(q);

				      String shouhang="";                  //清空首行
		    		  if(validateTableNameExist("cc"+stringq)==false){
		    			  //如果table q 不存在,创建一个新table q（i代表前面有i个数）
		    			
		    			  String sql2="CREATE TABLE "+"cc"+stringq
		    					  +"(shouhang TEXT,"+
		    					  "jieguo TEXT,shenglv int(5))";
		    			  stmt.executeUpdate(sql2);
		    		  }
	
		    		  


			    		  for(int j=(i-1);(i-1)-j<q;j--){                  //这个for记录0到i-1，即起点数
			    			  
			    			  shouhang +=arr[j];		//赋值不同q下的首行
			    		  }
			    		  
			    	String sqlselect="select count(*) from "+"cc"+stringq+" where shouhang='"+shouhang+"'"+"and jieguo="+"'"+arr[i]+"'";
			    	
			    	try { 
			    		ResultSet rs2 = stmt.executeQuery(sqlselect); 
			    		int count = 0; 
			    		while (rs2.next()) { 
			    		count = rs2.getInt(1); 
			    		} 
			    		if(count == 0) { 
			    		//没同样的记录,该干什么干什么 
			    			pre=conn.prepareStatement("insert into cc"+stringq+"(shouhang,jieguo,shenglv) values (?,?,?)");
			       		    pre.setString(1, shouhang);
			       		    pre.setString(2, arr[i]);
			       		    pre.setInt(3, 0);
			       		    pre.executeUpdate();	
			    		} else { 
			    		//有同样的记录,该干什么干什么 
			    		//首先读取胜率的数据，然后进行updatae修改
			    			
			    		String sql4="UPDATE "+"cc"+stringq+" SET shenglv =shenglv+1 WHERE shouhang='"+shouhang+"'"+"and jieguo="+"'"+arr[i]+"'";
			    		stmt.executeUpdate(sql4);
			    		} 
			    	} catch (SQLException e) { 
			    	e.printStackTrace(); 
			    	}	
			    		  

		    	  }
		     }
		     
		     if(i>10){
		    	 for(int q=10;q>0;q--){ //应该计入q(i)个数进首行,q<256
		    		  String stringq=String.valueOf(q);

				      String shouhang="";                  //清空首行
		    		  if(validateTableNameExist("cc"+stringq)==false){
		    			  //如果table q 不存在,创建一个新table q（i代表前面有i个数）
		    			
		    			  String sql2="CREATE TABLE "+"cc"+stringq
		    					  +"(shouhang TEXT,"+
		    					  "jieguo TEXT,shenglv int(5))";
		    			  stmt.executeUpdate(sql2);
		    		  }
	
		    		  


			    		  for(int j=(i-1);(i-1)-j<q;j--){                  //这个for记录0到i-1，即起点数
			    			  
			    			  shouhang +=arr[j];		//赋值不同q下的首行
			    		  }
			    		  
			    	String sqlselect="select count(*) from "+"cc"+stringq+" where shouhang='"+shouhang+"'"+"and jieguo="+"'"+arr[i]+"'";
			    	
			    	try { 
			    		ResultSet rs2 = stmt.executeQuery(sqlselect); 
			    		int count = 0; 
			    		while (rs2.next()) { 
			    		count = rs2.getInt(1); 
			    		} 
			    		if(count == 0) { 
			    		//没同样的记录,该干什么干什么 
			    			pre=conn.prepareStatement("insert into cc"+stringq+"(shouhang,jieguo,shenglv) values (?,?,?)");
			       		    pre.setString(1, shouhang);
			       		    pre.setString(2, arr[i]);
			       		    pre.setInt(3, 0);
			       		    pre.executeUpdate();	
			    		} else { 
			    		//有同样的记录,该干什么干什么 
			    		//首先读取胜率的数据，然后进行updatae修改
			    			
			    		String sql4="UPDATE "+"cc"+stringq+" SET shenglv =shenglv+1 WHERE shouhang='"+shouhang+"'"+"and jieguo="+"'"+arr[i]+"'";
			    		stmt.executeUpdate(sql4);
			    		} 
			    	} catch (SQLException e) { 
			    	e.printStackTrace(); 
			    	}	
			    		  

		    	  }
		     }
		    	  	  

		       System.out.println(arr[i]+"   chazhigewei:  "+i);
		     }
		       

		   }
		   
		  } catch (Exception e) {
		   // TODO Auto-generated catch block   
		   e.printStackTrace();
		  }
		}
	
	public void ycchazhi(Goddess g)throws Exception{
		 try {
		   Connection conn = DBUtil.getConnection();//建立connection
		   Statement stmt = conn.createStatement();
		   Statement stmt2 = conn.createStatement();
		   Statement stmt3 = conn.createStatement();
		   Statement stmt4 = conn.createStatement();
		   Statement stmt5=conn.createStatement();
		   Statement stmt6=conn.createStatement();
		   PreparedStatement pre=null;
		   conn.setAutoCommit(false);// 更改jdbc事务的默认提交方式 
		   String sql = "select * from zero7";//查询语句
		   ResultSet rs = stmt.executeQuery(sql);//得到结果集
		   conn.commit();//事务提交
		   conn.setAutoCommit(true);// 更改jdbc事务的默认提交方式 
		   List<String> list=new ArrayList<String>();//创建取结果的列表，之所以使用列表，不用数组，因为现在还不知道结果有多少，不能确定数组长度，所有先用list接收，然后转为数组
		   while (rs.next()) {//如果有数据，取第一列添加如list
		    list.add(rs.getString(1));
		   }
		   if(list != null && list.size()>0){//如果list中存入了数据，转化为数组
		    String[] arr=new String[list.size()];//创建一个和list长度一样的数组
		    for(int i=0;i<list.size();i++){
		     arr[i]=list.get(i);//数组赋值了。
		    }
		      int fuyishenglv=0;
		      int fuershenglv=0;
		      int zeroshenglv=0;
			  int oneshenglv=0;
			  int twoshenglv=0;
		       //输出数组
		       for(int i=0;i<arr.length+100;i++){
		    	int yaoyucededata=1201;//i+1;
		       if(i==(yaoyucededata-1)){
			    	  for(int q=10;q>0;q--){ //应该计入q(i)个数进首行,q<256
			    		  String stringq=String.valueOf(q);

					      String shouhang="";                  //清空首行

				    		  for(int j=(i-1);(i-1)-j<q;j--){                  //这个for记录0到i-1，即起点数
				    			  
				    			  shouhang +=arr[j];		//赋值不同q下的首行
				    		  }
				    		 System.out.println(shouhang);
				    	   	//0 的胜率
					  	    String sqlselect0="select shenglv from "+"ac"+stringq+" where (shouhang='"+shouhang+"')"
					  	    			+"and"+"(jieguo=3"+")";
					    	ResultSet rs00 = stmt2.executeQuery(sqlselect0);
					    		
				    		  //1 的胜率
				  	    	String sqlselect1="select shenglv from "+"ac"+stringq+" where (shouhang='"+shouhang+"')"
				  	    			+"and"+"(jieguo=4"+")";
				    		ResultSet rs01 = stmt3.executeQuery(sqlselect1);
				    		
				    		//2 的胜率
				  	    	String sqlselect2="select shenglv from "+"ac"+stringq+" where (shouhang='"+shouhang+"')"
				  	    			+"and"+"(jieguo=5"+")";
				    		ResultSet rs02 = stmt4.executeQuery(sqlselect2);
				    		
				  	    	String sqlselect3="select shenglv from "+"ac"+stringq+" where (shouhang='"+shouhang+"')"
				  	    			+"and"+"(jieguo=2"+")";
				    		ResultSet rs03 = stmt5.executeQuery(sqlselect3);
				    		
				  	    	String sqlselect4="select shenglv from "+"ac"+stringq+" where (shouhang='"+shouhang+"')"
				  	    			+"and"+"(jieguo=1"+")";
				    		ResultSet rs04 = stmt6.executeQuery(sqlselect4);
				  
				    		if(rs01.next())
				    			do{
							   
							    oneshenglv +=rs01.getInt(1);
				    			}while(rs01.next());
				    		if(rs00.next())
				    			do{
						    		
						    		zeroshenglv +=rs00.getInt(1);
				    			}while(rs00.next());
				    		if(rs02.next())
				    			do{
				    			
						    		twoshenglv +=rs02.getInt(1);
				    			}while(rs02.next());
				    		
				    		if(rs03.next())
				    			do{
				    			
						    		fuyishenglv +=rs03.getInt(1);
				    			}while(rs03.next());
				    		
				    		if(rs04.next())
				    			do{
				    			
						    		fuershenglv +=rs04.getInt(1);
				    			}while(rs04.next());
				    		
			    	  }	
		       }
		       
		       
		     }
		        NumberFormat numberFormat = NumberFormat.getInstance();  
		        
		        // 设置精确到小数点后2位  
		   
		        numberFormat.setMaximumFractionDigits(2); 
		        String result1 = numberFormat.format((float) zeroshenglv / (float) (zeroshenglv+oneshenglv+twoshenglv+fuyishenglv+fuershenglv) * 100);
		        String result2 = numberFormat.format((float) oneshenglv / (float) (zeroshenglv+oneshenglv+twoshenglv+fuyishenglv+fuershenglv) * 100);
		        String result3 = numberFormat.format((float) twoshenglv / (float) (zeroshenglv+oneshenglv+twoshenglv+fuyishenglv+fuershenglv) * 100);
		        String result4 = numberFormat.format((float) fuyishenglv / (float) (zeroshenglv+oneshenglv+twoshenglv+fuyishenglv+fuershenglv) * 100);
		        String result5 = numberFormat.format((float) fuershenglv / (float) (zeroshenglv+oneshenglv+twoshenglv+fuyishenglv+fuershenglv) * 100);
		       
		       System.out.println("差值百位-2的胜率 :"+ result5 + "%"+"   "+fuershenglv);
		       System.out.println("差值百位-1的胜率 :"+ result4 + "%"+"   "+fuyishenglv); 

		       System.out.println("差值百位0的胜率 :"+ result1 + "%"+"   "+zeroshenglv);
		       System.out.println("差值百位1的胜率 :"+ result2 + "%"+"   "+oneshenglv);
		       System.out.println("差值百位2的胜率 :"+ result3 + "%"+"   "+twoshenglv);
		       
		   }
		   
		  } catch (Exception e) {
		   // TODO Auto-generated catch block   
		   e.printStackTrace();
		  }
		}
	
	public void ycchazhi2(Goddess g)throws Exception{
		 try {
		   Connection conn = DBUtil.getConnection();//建立connection
		   Statement stmt = conn.createStatement();
		   Statement stmt2 = conn.createStatement();
		   Statement stmt3 = conn.createStatement();
		   Statement stmt4 = conn.createStatement();
		   Statement stmt5=conn.createStatement();
		   Statement stmt6=conn.createStatement();
		   PreparedStatement pre=null;
		   conn.setAutoCommit(false);// 更改jdbc事务的默认提交方式 
		   String sql = "select * from zero7";//查询语句
		   ResultSet rs = stmt.executeQuery(sql);//得到结果集
		   conn.commit();//事务提交
		   conn.setAutoCommit(true);// 更改jdbc事务的默认提交方式 
		   List<String> list=new ArrayList<String>();//创建取结果的列表，之所以使用列表，不用数组，因为现在还不知道结果有多少，不能确定数组长度，所有先用list接收，然后转为数组
		   while (rs.next()) {//如果有数据，取第一列添加如list
		    list.add(rs.getString(2));
		   }
		   if(list != null && list.size()>0){//如果list中存入了数据，转化为数组
		    String[] arr=new String[list.size()];//创建一个和list长度一样的数组
		    for(int i=0;i<list.size();i++){
		     arr[i]=list.get(i);//数组赋值了。
		    }
		      int fuyishenglv=0;
		      int fuershenglv=0;
		      int zeroshenglv=0;
			  int oneshenglv=0;
			  int twoshenglv=0;
		       //输出数组
		       for(int i=0;i<arr.length+100;i++){
		    	int yaoyucededata=1201;//i+1;
		       if(i==(yaoyucededata-1)){
			    	  for(int q=10;q>0;q--){ //应该计入q(i)个数进首行,q<256
			    		  String stringq=String.valueOf(q);

					      String shouhang="";                  //清空首行

				    		  for(int j=(i-1);(i-1)-j<q;j--){                  //这个for记录0到i-1，即起点数
				    			  
				    			  shouhang +=arr[j];		//赋值不同q下的首行
				    		  }
				    		 System.out.println(shouhang);
				    	   	//0 的胜率
					  	    String sqlselect0="select shenglv from "+"bc"+stringq+" where (shouhang='"+shouhang+"')"
					  	    			+"and"+"(jieguo=3"+")";
					    	ResultSet rs00 = stmt2.executeQuery(sqlselect0);
					    		
				    		  //1 的胜率
				  	    	String sqlselect1="select shenglv from "+"bc"+stringq+" where (shouhang='"+shouhang+"')"
				  	    			+"and"+"(jieguo=4"+")";
				    		ResultSet rs01 = stmt3.executeQuery(sqlselect1);
				    		
				    		//2 的胜率
				  	    	String sqlselect2="select shenglv from "+"bc"+stringq+" where (shouhang='"+shouhang+"')"
				  	    			+"and"+"(jieguo=5"+")";
				    		ResultSet rs02 = stmt4.executeQuery(sqlselect2);
				    		
				  	    	String sqlselect3="select shenglv from "+"bc"+stringq+" where (shouhang='"+shouhang+"')"
				  	    			+"and"+"(jieguo=2"+")";
				    		ResultSet rs03 = stmt5.executeQuery(sqlselect3);
				    		
				  	    	String sqlselect4="select shenglv from "+"bc"+stringq+" where (shouhang='"+shouhang+"')"
				  	    			+"and"+"(jieguo=1"+")";
				    		ResultSet rs04 = stmt6.executeQuery(sqlselect4);
				  
				    		if(rs01.next())
				    			do{
							   
							    oneshenglv +=rs01.getInt(1);
				    			}while(rs01.next());
				    		if(rs00.next())
				    			do{
						    		
						    		zeroshenglv +=rs00.getInt(1);
				    			}while(rs00.next());
				    		if(rs02.next())
				    			do{
				    			
						    		twoshenglv +=rs02.getInt(1);
				    			}while(rs02.next());
				    		
				    		if(rs03.next())
				    			do{
				    			
						    		fuyishenglv +=rs03.getInt(1);
				    			}while(rs03.next());
				    		
				    		if(rs04.next())
				    			do{
				    			
						    		fuershenglv +=rs04.getInt(1);
				    			}while(rs04.next());
				    		
			    	  }	
		       }
		       
		       
		     }
		        NumberFormat numberFormat = NumberFormat.getInstance();  
		        
		        // 设置精确到小数点后2位  
		   
		        numberFormat.setMaximumFractionDigits(2); 
		        String result1 = numberFormat.format((float) zeroshenglv / (float) (zeroshenglv+oneshenglv+twoshenglv+fuyishenglv+fuershenglv) * 100);
		        String result2 = numberFormat.format((float) oneshenglv / (float) (zeroshenglv+oneshenglv+twoshenglv+fuyishenglv+fuershenglv) * 100);
		        String result3 = numberFormat.format((float) twoshenglv / (float) (zeroshenglv+oneshenglv+twoshenglv+fuyishenglv+fuershenglv) * 100);
		        String result4 = numberFormat.format((float) fuyishenglv / (float) (zeroshenglv+oneshenglv+twoshenglv+fuyishenglv+fuershenglv) * 100);
		        String result5 = numberFormat.format((float) fuershenglv / (float) (zeroshenglv+oneshenglv+twoshenglv+fuyishenglv+fuershenglv) * 100);
		       
		       System.out.println("差值十位-2的胜率 :"+ result5 + "%"+"   "+fuershenglv);
		       System.out.println("差值十位-1的胜率 :"+ result4 + "%"+"   "+fuyishenglv); 

		       System.out.println("差值十位0的胜率 :"+ result1 + "%"+"   "+zeroshenglv);
		       System.out.println("差值十位1的胜率 :"+ result2 + "%"+"   "+oneshenglv);
		       System.out.println("差值十位2的胜率 :"+ result3 + "%"+"   "+twoshenglv);
		       
		   }
		   
		  } catch (Exception e) {
		   // TODO Auto-generated catch block   
		   e.printStackTrace();
		  }
		}

	public void ycchazhi3(Goddess g)throws Exception{
		 try {
		   Connection conn = DBUtil.getConnection();//建立connection
		   Statement stmt = conn.createStatement();
		   Statement stmt2 = conn.createStatement();
		   Statement stmt3 = conn.createStatement();
		   Statement stmt4 = conn.createStatement();
		   Statement stmt5=conn.createStatement();
		   Statement stmt6=conn.createStatement();
		   PreparedStatement pre=null;
		   conn.setAutoCommit(false);// 更改jdbc事务的默认提交方式 
		   String sql = "select * from zero7";//查询语句
		   ResultSet rs = stmt.executeQuery(sql);//得到结果集
		   conn.commit();//事务提交
		   conn.setAutoCommit(true);// 更改jdbc事务的默认提交方式 
		   List<String> list=new ArrayList<String>();//创建取结果的列表，之所以使用列表，不用数组，因为现在还不知道结果有多少，不能确定数组长度，所有先用list接收，然后转为数组
		   while (rs.next()) {//如果有数据，取第一列添加如list
		    list.add(rs.getString(3));
		   }
		   if(list != null && list.size()>0){//如果list中存入了数据，转化为数组
		    String[] arr=new String[list.size()];//创建一个和list长度一样的数组
		    for(int i=0;i<list.size();i++){
		     arr[i]=list.get(i);//数组赋值了。
		    }
		    
		      int fuyishenglv=0;
		      int fuershenglv=0;
		      int zeroshenglv=0;
			  int oneshenglv=0;
			  int twoshenglv=0;
		       //输出数组
		       for(int i=0;i<arr.length+100;i++){
		    	int yaoyucededata=1201;//i+1;
		       if(i==(yaoyucededata-1)){
			    	  for(int q=10;q>0;q--){ //应该计入q(i)个数进首行,q<256
			    		  String stringq=String.valueOf(q);

					      String shouhang="";                  //清空首行

				    		  for(int j=(i-1);(i-1)-j<q;j--){                  //这个for记录0到i-1，即起点数
				    			  
				    			  shouhang +=arr[j];		//赋值不同q下的首行
				    		  }
				    		 System.out.println(shouhang);
				    	   	//0 的胜率
					  	    String sqlselect0="select shenglv from "+"cc"+stringq+" where (shouhang='"+shouhang+"')"
					  	    			+"and"+"(jieguo=3"+")";
					    	ResultSet rs00 = stmt2.executeQuery(sqlselect0);
					    		
				    		  //1 的胜率
				  	    	String sqlselect1="select shenglv from "+"cc"+stringq+" where (shouhang='"+shouhang+"')"
				  	    			+"and"+"(jieguo=4"+")";
				    		ResultSet rs01 = stmt3.executeQuery(sqlselect1);
				    		
				    		//2 的胜率
				  	    	String sqlselect2="select shenglv from "+"cc"+stringq+" where (shouhang='"+shouhang+"')"
				  	    			+"and"+"(jieguo=5"+")";
				    		ResultSet rs02 = stmt4.executeQuery(sqlselect2);
				    		
				  	    	String sqlselect3="select shenglv from "+"cc"+stringq+" where (shouhang='"+shouhang+"')"
				  	    			+"and"+"(jieguo=2"+")";
				    		ResultSet rs03 = stmt5.executeQuery(sqlselect3);
				    		
				  	    	String sqlselect4="select shenglv from "+"cc"+stringq+" where (shouhang='"+shouhang+"')"
				  	    			+"and"+"(jieguo=1"+")";
				    		ResultSet rs04 = stmt6.executeQuery(sqlselect4);
				  
				    		if(rs01.next())
				    			do{
							   
							    oneshenglv +=rs01.getInt(1);
				    			}while(rs01.next());
				    		if(rs00.next())
				    			do{
						    		
						    		zeroshenglv +=rs00.getInt(1);
				    			}while(rs00.next());
				    		if(rs02.next())
				    			do{
				    			
						    		twoshenglv +=rs02.getInt(1);
				    			}while(rs02.next());
				    		
				    		if(rs03.next())
				    			do{
				    			
						    		fuyishenglv +=rs03.getInt(1);
				    			}while(rs03.next());
				    		
				    		if(rs04.next())
				    			do{
				    			
						    		fuershenglv +=rs04.getInt(1);
				    			}while(rs04.next());
				    		
			    	  }	
		       }
		       
		       
		     }
		        NumberFormat numberFormat = NumberFormat.getInstance();  
		        
		        // 设置精确到小数点后2位  
		   
		        numberFormat.setMaximumFractionDigits(2); 
		        String result1 = numberFormat.format((float) zeroshenglv / (float) (zeroshenglv+oneshenglv+twoshenglv+fuyishenglv+fuershenglv) * 100);
		        String result2 = numberFormat.format((float) oneshenglv / (float) (zeroshenglv+oneshenglv+twoshenglv+fuyishenglv+fuershenglv) * 100);
		        String result3 = numberFormat.format((float) twoshenglv / (float) (zeroshenglv+oneshenglv+twoshenglv+fuyishenglv+fuershenglv) * 100);
		        String result4 = numberFormat.format((float) fuyishenglv / (float) (zeroshenglv+oneshenglv+twoshenglv+fuyishenglv+fuershenglv) * 100);
		        String result5 = numberFormat.format((float) fuershenglv / (float) (zeroshenglv+oneshenglv+twoshenglv+fuyishenglv+fuershenglv) * 100);
		       
		       System.out.println("差值个位-2的胜率 :"+ result5 + "%"+"   "+fuershenglv);
		       System.out.println("差值个位-1的胜率 :"+ result4 + "%"+"   "+fuyishenglv); 

		       System.out.println("差值个位0的胜率 :"+ result1 + "%"+"   "+zeroshenglv);
		       System.out.println("差值个位1的胜率 :"+ result2 + "%"+"   "+oneshenglv);
		       System.out.println("差值个位2的胜率 :"+ result3 + "%"+"   "+twoshenglv);
		       
		   }
		   
		  } catch (Exception e) {
		   // TODO Auto-generated catch block   
		   e.printStackTrace();
		  }
		}
	
	public void gethxcolsone(Goddess g)throws Exception{
		 try {
		   Connection conn = DBUtil.getConnection();//建立connection
		   Statement stmt = conn.createStatement();
		   PreparedStatement pre=null;
		   conn.setAutoCommit(false);// 更改jdbc事务的默认提交方式 
		   String sql = "select * from zero8";//查询语句
		   ResultSet rs = stmt.executeQuery(sql);//得到结果集
		   conn.commit();//事务提交
		   conn.setAutoCommit(true);// 更改jdbc事务的默认提交方式 
		   List<String> list=new ArrayList<String>();//创建取结果的列表，之所以使用列表，不用数组，因为现在还不知道结果有多少，不能确定数组长度，所有先用list接收，然后转为数组
		   while (rs.next()) {//如果有数据，取第一列添加如list
		    list.add(rs.getString(1));
		   }
		   if(list != null && list.size()>0){//如果list中存入了数据，转化为数组
		    String[] arr=new String[list.size()];//创建一个和list长度一样的数组
		    for(int i=0;i<list.size();i++){
		     arr[i]=list.get(i);//数组赋值了。
		    }
		       //输出数组
		       for(int i=0;i<arr.length;i++){
		    	 
		    	   if(i<11){
		    		   for(int q=i;q>0;q--){ //应该计入q(i)个数进首行,q<256
		    		  String stringq=String.valueOf(q);

				      String shouhang="";                  //清空首行
		    		  if(validateTableNameExist("aac"+stringq)==false){
		    			  //如果table q 不存在,创建一个新table q（i代表前面有i个数）
		    			
		    			  String sql2="CREATE TABLE "+"aac"+stringq
		    					  +"(shouhang TEXT,"+
		    					  "jieguo TEXT,shenglv int(5))";
		    			  stmt.executeUpdate(sql2);
		    		  }
	
		    		  


			    		  for(int j=(i-1);(i-1)-j<q;j--){                  //这个for记录0到i-1，即起点数
			    			  
			    			  shouhang +=arr[j];		//赋值不同q下的首行
			    		  }
			    		  
			    	String sqlselect="select count(*) from "+"aac"+stringq+" where shouhang='"+shouhang+"'"+"and jieguo="+"'"+arr[i]+"'";
			    	
			    	try { 
			    		ResultSet rs2 = stmt.executeQuery(sqlselect); 
			    		int count = 0; 
			    		while (rs2.next()) { 
			    		count = rs2.getInt(1); 
			    		} 
			    		if(count == 0) { 
			    		//没同样的记录,该干什么干什么 
			    			pre=conn.prepareStatement("insert into aac"+stringq+"(shouhang,jieguo,shenglv) values (?,?,?)");
			       		    pre.setString(1, shouhang);
			       		    pre.setString(2, arr[i]);
			       		    pre.setInt(3, 0);
			       		    pre.executeUpdate();	
			    		} else { 
			    		//有同样的记录,该干什么干什么 
			    		//首先读取胜率的数据，然后进行updatae修改
			    			
			    		String sql4="UPDATE "+"aac"+stringq+" SET shenglv =shenglv+1 WHERE shouhang='"+shouhang+"'"+"and jieguo="+"'"+arr[i]+"'";
			    		stmt.executeUpdate(sql4);
			    		} 
			    	} catch (SQLException e) { 
			    	e.printStackTrace(); 
			    	}	
			    		  

		    	  }	
		    	   }
		    	    

		    	   if(i>10){
		    		   for(int q=10;q>0;q--){ //应该计入q(i)个数进首行,q<256
				    		  String stringq=String.valueOf(q);

						      String shouhang="";                  //清空首行
				    		  if(validateTableNameExist("aac"+stringq)==false){
				    			  //如果table q 不存在,创建一个新table q（i代表前面有i个数）
				    			
				    			  String sql2="CREATE TABLE "+"aac"+stringq
				    					  +"(shouhang TEXT,"+
				    					  "jieguo TEXT,shenglv int(5))";
				    			  stmt.executeUpdate(sql2);
				    		  }
			
				    		  


					    		  for(int j=(i-1);(i-1)-j<q;j--){                  //这个for记录0到i-1，即起点数
					    			  
					    			  shouhang +=arr[j];		//赋值不同q下的首行
					    		  }
					    		  
					    	String sqlselect="select count(*) from "+"aac"+stringq+" where shouhang='"+shouhang+"'"+"and jieguo="+"'"+arr[i]+"'";
					    	
					    	try { 
					    		ResultSet rs2 = stmt.executeQuery(sqlselect); 
					    		int count = 0; 
					    		while (rs2.next()) { 
					    		count = rs2.getInt(1); 
					    		} 
					    		if(count == 0) { 
					    		//没同样的记录,该干什么干什么 
					    			pre=conn.prepareStatement("insert into aac"+stringq+"(shouhang,jieguo,shenglv) values (?,?,?)");
					       		    pre.setString(1, shouhang);
					       		    pre.setString(2, arr[i]);
					       		    pre.setInt(3, 0);
					       		    pre.executeUpdate();	
					    		} else { 
					    		//有同样的记录,该干什么干什么 
					    		//首先读取胜率的数据，然后进行updatae修改
					    			
					    		String sql4="UPDATE "+"aac"+stringq+" SET shenglv =shenglv+1 WHERE shouhang='"+shouhang+"'"+"and jieguo="+"'"+arr[i]+"'";
					    		stmt.executeUpdate(sql4);
					    		} 
					    	} catch (SQLException e) { 
					    	e.printStackTrace(); 
					    	}	
					    		  

				    	  }	
		    		   
		    	   }
		    	  
		    	  
		       System.out.println(arr[i]+"   hxbaiwei:  "+i);
		     }
		       

		   }
		   
		  } catch (Exception e) {
		   // TODO Auto-generated catch block   
		   e.printStackTrace();
		  }
		}
	
	public void gethxcolstwo(Goddess g)throws Exception{
		 try {
		   Connection conn = DBUtil.getConnection();//建立connection
		   Statement stmt = conn.createStatement();
		   PreparedStatement pre=null;
		   conn.setAutoCommit(false);// 更改jdbc事务的默认提交方式 
		   String sql = "select * from zero8";//查询语句
		   ResultSet rs = stmt.executeQuery(sql);//得到结果集
		   conn.commit();//事务提交
		   conn.setAutoCommit(true);// 更改jdbc事务的默认提交方式 
		   List<String> list=new ArrayList<String>();//创建取结果的列表，之所以使用列表，不用数组，因为现在还不知道结果有多少，不能确定数组长度，所有先用list接收，然后转为数组
		   while (rs.next()) {//如果有数据，取第一列添加如list
		    list.add(rs.getString(2));
		   }
		   if(list != null && list.size()>0){//如果list中存入了数据，转化为数组
		    String[] arr=new String[list.size()];//创建一个和list长度一样的数组
		    for(int i=0;i<list.size();i++){
		     arr[i]=list.get(i);//数组赋值了。
		    }
		       //输出数组
		       for(int i=0;i<arr.length;i++){
		    	   if(i<11){
		    		   for(int q=i;q>0;q--){ //应该计入q(i)个数进首行,q<256
				    		  String stringq=String.valueOf(q);

						      String shouhang="";                  //清空首行
				    		  if(validateTableNameExist("bbc"+stringq)==false){
				    			  //如果table q 不存在,创建一个新table q（i代表前面有i个数）
				    			
				    			  String sql2="CREATE TABLE "+"bbc"+stringq
				    					  +"(shouhang TEXT,"+
				    					  "jieguo TEXT,shenglv int(5))";
				    			  stmt.executeUpdate(sql2);
				    		  }
			
				    		  


					    		  for(int j=(i-1);(i-1)-j<q;j--){                  //这个for记录0到i-1，即起点数
					    			  
					    			  shouhang +=arr[j];		//赋值不同q下的首行
					    		  }
					    		  
					    	String sqlselect="select count(*) from "+"bbc"+stringq+" where shouhang='"+shouhang+"'"+"and jieguo="+"'"+arr[i]+"'";
					    	
					    	try { 
					    		ResultSet rs2 = stmt.executeQuery(sqlselect); 
					    		int count = 0; 
					    		while (rs2.next()) { 
					    		count = rs2.getInt(1); 
					    		} 
					    		if(count == 0) { 
					    		//没同样的记录,该干什么干什么 
					    			pre=conn.prepareStatement("insert into bbc"+stringq+"(shouhang,jieguo,shenglv) values (?,?,?)");
					       		    pre.setString(1, shouhang);
					       		    pre.setString(2, arr[i]);
					       		    pre.setInt(3, 0);
					       		    pre.executeUpdate();	
					    		} else { 
					    		//有同样的记录,该干什么干什么 
					    		//首先读取胜率的数据，然后进行updatae修改
					    			
					    		String sql4="UPDATE "+"bbc"+stringq+" SET shenglv =shenglv+1 WHERE shouhang='"+shouhang+"'"+"and jieguo="+"'"+arr[i]+"'";
					    		stmt.executeUpdate(sql4);
					    		} 
					    	} catch (SQLException e) { 
					    	e.printStackTrace(); 
					    	}	
					    		  

				    	  }
		    	   }
		    	  if(i>10){
		    		  for(int q=10;q>0;q--){ //应该计入q(i)个数进首行,q<256
			    		  String stringq=String.valueOf(q);

					      String shouhang="";                  //清空首行
			    		  if(validateTableNameExist("bbc"+stringq)==false){
			    			  //如果table q 不存在,创建一个新table q（i代表前面有i个数）
			    			
			    			  String sql2="CREATE TABLE "+"bbc"+stringq
			    					  +"(shouhang TEXT,"+
			    					  "jieguo TEXT,shenglv int(5))";
			    			  stmt.executeUpdate(sql2);
			    		  }
		
			    		  


				    		  for(int j=(i-1);(i-1)-j<q;j--){                  //这个for记录0到i-1，即起点数
				    			  
				    			  shouhang +=arr[j];		//赋值不同q下的首行
				    		  }
				    		  
				    	String sqlselect="select count(*) from "+"bbc"+stringq+" where shouhang='"+shouhang+"'"+"and jieguo="+"'"+arr[i]+"'";
				    	
				    	try { 
				    		ResultSet rs2 = stmt.executeQuery(sqlselect); 
				    		int count = 0; 
				    		while (rs2.next()) { 
				    		count = rs2.getInt(1); 
				    		} 
				    		if(count == 0) { 
				    		//没同样的记录,该干什么干什么 
				    			pre=conn.prepareStatement("insert into bbc"+stringq+"(shouhang,jieguo,shenglv) values (?,?,?)");
				       		    pre.setString(1, shouhang);
				       		    pre.setString(2, arr[i]);
				       		    pre.setInt(3, 0);
				       		    pre.executeUpdate();	
				    		} else { 
				    		//有同样的记录,该干什么干什么 
				    		//首先读取胜率的数据，然后进行updatae修改
				    			
				    		String sql4="UPDATE "+"bbc"+stringq+" SET shenglv =shenglv+1 WHERE shouhang='"+shouhang+"'"+"and jieguo="+"'"+arr[i]+"'";
				    		stmt.executeUpdate(sql4);
				    		} 
				    	} catch (SQLException e) { 
				    	e.printStackTrace(); 
				    	}	
				    		  

			    	  }
		    	  }
		    	  	  

		       System.out.println(arr[i]+"   hxshiwei:  "+i);
		     }
		       

		   }
		   
		  } catch (Exception e) {
		   // TODO Auto-generated catch block   
		   e.printStackTrace();
		  }
		}
	
	public void gethxcolsthree(Goddess g)throws Exception{
		 try {
		   Connection conn = DBUtil.getConnection();//建立connection
		   Statement stmt = conn.createStatement();
		   PreparedStatement pre=null;
		   conn.setAutoCommit(false);// 更改jdbc事务的默认提交方式 
		   String sql = "select * from zero8";//查询语句
		   ResultSet rs = stmt.executeQuery(sql);//得到结果集
		   conn.commit();//事务提交
		   conn.setAutoCommit(true);// 更改jdbc事务的默认提交方式 
		   List<String> list=new ArrayList<String>();//创建取结果的列表，之所以使用列表，不用数组，因为现在还不知道结果有多少，不能确定数组长度，所有先用list接收，然后转为数组
		   while (rs.next()) {//如果有数据，取第一列添加如list
		    list.add(rs.getString(3));
		   }
		   if(list != null && list.size()>0){//如果list中存入了数据，转化为数组
		    String[] arr=new String[list.size()];//创建一个和list长度一样的数组
		    for(int i=0;i<list.size();i++){
		     arr[i]=list.get(i);//数组赋值了。
		    }
		       //输出数组
		       for(int i=0;i<arr.length;i++){
		     if(i<11){
		    	 for(int q=i;q>0;q--){ //应该计入q(i)个数进首行,q<256
		    		  String stringq=String.valueOf(q);

				      String shouhang="";                  //清空首行
		    		  if(validateTableNameExist("ccc"+stringq)==false){
		    			  //如果table q 不存在,创建一个新table q（i代表前面有i个数）
		    			
		    			  String sql2="CREATE TABLE "+"ccc"+stringq
		    					  +"(shouhang TEXT,"+
		    					  "jieguo TEXT,shenglv int(5))";
		    			  stmt.executeUpdate(sql2);
		    		  }
	
		    		  


			    		  for(int j=(i-1);(i-1)-j<q;j--){                  //这个for记录0到i-1，即起点数
			    			  
			    			  shouhang +=arr[j];		//赋值不同q下的首行
			    		  }
			    		  
			    	String sqlselect="select count(*) from "+"ccc"+stringq+" where shouhang='"+shouhang+"'"+"and jieguo="+"'"+arr[i]+"'";
			    	
			    	try { 
			    		ResultSet rs2 = stmt.executeQuery(sqlselect); 
			    		int count = 0; 
			    		while (rs2.next()) { 
			    		count = rs2.getInt(1); 
			    		} 
			    		if(count == 0) { 
			    		//没同样的记录,该干什么干什么 
			    			pre=conn.prepareStatement("insert into ccc"+stringq+"(shouhang,jieguo,shenglv) values (?,?,?)");
			       		    pre.setString(1, shouhang);
			       		    pre.setString(2, arr[i]);
			       		    pre.setInt(3, 0);
			       		    pre.executeUpdate();	
			    		} else { 
			    		//有同样的记录,该干什么干什么 
			    		//首先读取胜率的数据，然后进行updatae修改
			    			
			    		String sql4="UPDATE "+"ccc"+stringq+" SET shenglv =shenglv+1 WHERE shouhang='"+shouhang+"'"+"and jieguo="+"'"+arr[i]+"'";
			    		stmt.executeUpdate(sql4);
			    		} 
			    	} catch (SQLException e) { 
			    	e.printStackTrace(); 
			    	}	
			    		  

		    	  }
		     }
		     
		     if(i>10){
		    	 for(int q=10;q>0;q--){ //应该计入q(i)个数进首行,q<256
		    		  String stringq=String.valueOf(q);

				      String shouhang="";                  //清空首行
		    		  if(validateTableNameExist("ccc"+stringq)==false){
		    			  //如果table q 不存在,创建一个新table q（i代表前面有i个数）
		    			
		    			  String sql2="CREATE TABLE "+"ccc"+stringq
		    					  +"(shouhang TEXT,"+
		    					  "jieguo TEXT,shenglv int(5))";
		    			  stmt.executeUpdate(sql2);
		    		  }
	
		    		  


			    		  for(int j=(i-1);(i-1)-j<q;j--){                  //这个for记录0到i-1，即起点数
			    			  
			    			  shouhang +=arr[j];		//赋值不同q下的首行
			    		  }
			    		  
			    	String sqlselect="select count(*) from "+"ccc"+stringq+" where shouhang='"+shouhang+"'"+"and jieguo="+"'"+arr[i]+"'";
			    	
			    	try { 
			    		ResultSet rs2 = stmt.executeQuery(sqlselect); 
			    		int count = 0; 
			    		while (rs2.next()) { 
			    		count = rs2.getInt(1); 
			    		} 
			    		if(count == 0) { 
			    		//没同样的记录,该干什么干什么 
			    			pre=conn.prepareStatement("insert into ccc"+stringq+"(shouhang,jieguo,shenglv) values (?,?,?)");
			       		    pre.setString(1, shouhang);
			       		    pre.setString(2, arr[i]);
			       		    pre.setInt(3, 0);
			       		    pre.executeUpdate();	
			    		} else { 
			    		//有同样的记录,该干什么干什么 
			    		//首先读取胜率的数据，然后进行updatae修改
			    			
			    		String sql4="UPDATE "+"ccc"+stringq+" SET shenglv =shenglv+1 WHERE shouhang='"+shouhang+"'"+"and jieguo="+"'"+arr[i]+"'";
			    		stmt.executeUpdate(sql4);
			    		} 
			    	} catch (SQLException e) { 
			    	e.printStackTrace(); 
			    	}	
			    		  

		    	  }
		     }
		    	  	  

		       System.out.println(arr[i]+"   hxgewei:  "+i);
		     }
		       

		   }
		   
		  } catch (Exception e) {
		   // TODO Auto-generated catch block   
		   e.printStackTrace();
		  }
		}
	
	public void ychx(Goddess g)throws Exception{
		 try {
		   Connection conn = DBUtil.getConnection();//建立connection
		   Statement stmt = conn.createStatement();
		   Statement stmt2 = conn.createStatement();
		   Statement stmt3 = conn.createStatement();
		   Statement stmt4 = conn.createStatement();
		   Statement stmt5=conn.createStatement();
		   Statement stmt6=conn.createStatement();
		   PreparedStatement pre=null;
		   conn.setAutoCommit(false);// 更改jdbc事务的默认提交方式 
		   String sql = "select * from zero8";//查询语句
		   ResultSet rs = stmt.executeQuery(sql);//得到结果集
		   conn.commit();//事务提交
		   conn.setAutoCommit(true);// 更改jdbc事务的默认提交方式 
		   List<String> list=new ArrayList<String>();//创建取结果的列表，之所以使用列表，不用数组，因为现在还不知道结果有多少，不能确定数组长度，所有先用list接收，然后转为数组
		   while (rs.next()) {//如果有数据，取第一列添加如list
		    list.add(rs.getString(1));
		   }
		   if(list != null && list.size()>0){//如果list中存入了数据，转化为数组
		    String[] arr=new String[list.size()];//创建一个和list长度一样的数组
		    for(int i=0;i<list.size();i++){
		     arr[i]=list.get(i);//数组赋值了。
		    }
		      int fuyishenglv=0;
		      int fuershenglv=0;
		      int zeroshenglv=0;
			  int oneshenglv=0;
			  int twoshenglv=0;
		       //输出数组
		       for(int i=0;i<arr.length+100;i++){
		    	int yaoyucededata=1201;//i+1;
		       if(i==(yaoyucededata-1)){
			    	  for(int q=10;q>0;q--){ //应该计入q(i)个数进首行,q<256
			    		  String stringq=String.valueOf(q);

					      String shouhang="";                  //清空首行

				    		  for(int j=(i-1);(i-1)-j<q;j--){                  //这个for记录0到i-1，即起点数
				    			  
				    			  shouhang +=arr[j];		//赋值不同q下的首行
				    		  }
				    		 System.out.println(shouhang);
				    	   	//0 的胜率
					  	    String sqlselect0="select shenglv from "+"aac"+stringq+" where (shouhang='"+shouhang+"')"
					  	    			+"and"+"(jieguo=3"+")";
					    	ResultSet rs00 = stmt2.executeQuery(sqlselect0);
					    		
				    		  //1 的胜率
				  	    	String sqlselect1="select shenglv from "+"aac"+stringq+" where (shouhang='"+shouhang+"')"
				  	    			+"and"+"(jieguo=4"+")";
				    		ResultSet rs01 = stmt3.executeQuery(sqlselect1);
				    		
				    		//2 的胜率
				  	    	String sqlselect2="select shenglv from "+"aac"+stringq+" where (shouhang='"+shouhang+"')"
				  	    			+"and"+"(jieguo=5"+")";
				    		ResultSet rs02 = stmt4.executeQuery(sqlselect2);
				    		
				  	    	String sqlselect3="select shenglv from "+"aac"+stringq+" where (shouhang='"+shouhang+"')"
				  	    			+"and"+"(jieguo=2"+")";
				    		ResultSet rs03 = stmt5.executeQuery(sqlselect3);
				    		
				  	    	String sqlselect4="select shenglv from "+"aac"+stringq+" where (shouhang='"+shouhang+"')"
				  	    			+"and"+"(jieguo=1"+")";
				    		ResultSet rs04 = stmt6.executeQuery(sqlselect4);
				  
				    		if(rs01.next())
				    			do{
							   
							    oneshenglv +=rs01.getInt(1);
				    			}while(rs01.next());
				    		if(rs00.next())
				    			do{
						    		
						    		zeroshenglv +=rs00.getInt(1);
				    			}while(rs00.next());
				    		if(rs02.next())
				    			do{
				    			
						    		twoshenglv +=rs02.getInt(1);
				    			}while(rs02.next());
				    		
				    		if(rs03.next())
				    			do{
				    			
						    		fuyishenglv +=rs03.getInt(1);
				    			}while(rs03.next());
				    		
				    		if(rs04.next())
				    			do{
				    			
						    		fuershenglv +=rs04.getInt(1);
				    			}while(rs04.next());
				    		
			    	  }	
		       }
		       
		       
		     }
		        NumberFormat numberFormat = NumberFormat.getInstance();  
		        
		        // 设置精确到小数点后2位  
		   
		        numberFormat.setMaximumFractionDigits(2); 
		        String result1 = numberFormat.format((float) zeroshenglv / (float) (zeroshenglv+oneshenglv+twoshenglv+fuyishenglv+fuershenglv) * 100);
		        String result2 = numberFormat.format((float) oneshenglv / (float) (zeroshenglv+oneshenglv+twoshenglv+fuyishenglv+fuershenglv) * 100);
		        String result3 = numberFormat.format((float) twoshenglv / (float) (zeroshenglv+oneshenglv+twoshenglv+fuyishenglv+fuershenglv) * 100);
		        String result4 = numberFormat.format((float) fuyishenglv / (float) (zeroshenglv+oneshenglv+twoshenglv+fuyishenglv+fuershenglv) * 100);
		        String result5 = numberFormat.format((float) fuershenglv / (float) (zeroshenglv+oneshenglv+twoshenglv+fuyishenglv+fuershenglv) * 100);
		       
		       System.out.println("横向百位-2的胜率 :"+ result5 + "%"+"   "+fuershenglv);
		       System.out.println("横向百位-1的胜率 :"+ result4 + "%"+"   "+fuyishenglv); 

		       System.out.println("横向百位0的胜率 :"+ result1 + "%"+"   "+zeroshenglv);
		       System.out.println("横向百位1的胜率 :"+ result2 + "%"+"   "+oneshenglv);
		       System.out.println("横向百位2的胜率 :"+ result3 + "%"+"   "+twoshenglv);
		       
		   }
		   
		  } catch (Exception e) {
		   // TODO Auto-generated catch block   
		   e.printStackTrace();
		  }
		}
	
	public void ychx2(Goddess g)throws Exception{
		 try {
		   Connection conn = DBUtil.getConnection();//建立connection
		   Statement stmt = conn.createStatement();
		   Statement stmt2 = conn.createStatement();
		   Statement stmt3 = conn.createStatement();
		   Statement stmt4 = conn.createStatement();
		   Statement stmt5=conn.createStatement();
		   Statement stmt6=conn.createStatement();
		   PreparedStatement pre=null;
		   conn.setAutoCommit(false);// 更改jdbc事务的默认提交方式 
		   String sql = "select * from zero8";//查询语句
		   ResultSet rs = stmt.executeQuery(sql);//得到结果集
		   conn.commit();//事务提交
		   conn.setAutoCommit(true);// 更改jdbc事务的默认提交方式 
		   List<String> list=new ArrayList<String>();//创建取结果的列表，之所以使用列表，不用数组，因为现在还不知道结果有多少，不能确定数组长度，所有先用list接收，然后转为数组
		   while (rs.next()) {//如果有数据，取第一列添加如list
		    list.add(rs.getString(2));
		   }
		   if(list != null && list.size()>0){//如果list中存入了数据，转化为数组
		    String[] arr=new String[list.size()];//创建一个和list长度一样的数组
		    for(int i=0;i<list.size();i++){
		     arr[i]=list.get(i);//数组赋值了。
		    }
		      int fuyishenglv=0;
		      int fuershenglv=0;
		      int zeroshenglv=0;
			  int oneshenglv=0;
			  int twoshenglv=0;
		       //输出数组
		       for(int i=0;i<arr.length+100;i++){
		    	int yaoyucededata=1201;//i+1;
		       if(i==(yaoyucededata-1)){
			    	  for(int q=10;q>0;q--){ //应该计入q(i)个数进首行,q<256
			    		  String stringq=String.valueOf(q);

					      String shouhang="";                  //清空首行

				    		  for(int j=(i-1);(i-1)-j<q;j--){                  //这个for记录0到i-1，即起点数
				    			  
				    			  shouhang +=arr[j];		//赋值不同q下的首行
				    		  }
				    		 System.out.println(shouhang);
				    	   	//0 的胜率
					  	    String sqlselect0="select shenglv from "+"bbc"+stringq+" where (shouhang='"+shouhang+"')"
					  	    			+"and"+"(jieguo=3"+")";
					    	ResultSet rs00 = stmt2.executeQuery(sqlselect0);
					    		
				    		  //1 的胜率
				  	    	String sqlselect1="select shenglv from "+"bbc"+stringq+" where (shouhang='"+shouhang+"')"
				  	    			+"and"+"(jieguo=4"+")";
				    		ResultSet rs01 = stmt3.executeQuery(sqlselect1);
				    		
				    		//2 的胜率
				  	    	String sqlselect2="select shenglv from "+"bbc"+stringq+" where (shouhang='"+shouhang+"')"
				  	    			+"and"+"(jieguo=5"+")";
				    		ResultSet rs02 = stmt4.executeQuery(sqlselect2);
				    		
				  	    	String sqlselect3="select shenglv from "+"bbc"+stringq+" where (shouhang='"+shouhang+"')"
				  	    			+"and"+"(jieguo=2"+")";
				    		ResultSet rs03 = stmt5.executeQuery(sqlselect3);
				    		
				  	    	String sqlselect4="select shenglv from "+"bbc"+stringq+" where (shouhang='"+shouhang+"')"
				  	    			+"and"+"(jieguo=1"+")";
				    		ResultSet rs04 = stmt6.executeQuery(sqlselect4);
				  
				    		if(rs01.next())
				    			do{
							   
							    oneshenglv +=rs01.getInt(1);
				    			}while(rs01.next());
				    		if(rs00.next())
				    			do{
						    		
						    		zeroshenglv +=rs00.getInt(1);
				    			}while(rs00.next());
				    		if(rs02.next())
				    			do{
				    			
						    		twoshenglv +=rs02.getInt(1);
				    			}while(rs02.next());
				    		
				    		if(rs03.next())
				    			do{
				    			
						    		fuyishenglv +=rs03.getInt(1);
				    			}while(rs03.next());
				    		
				    		if(rs04.next())
				    			do{
				    			
						    		fuershenglv +=rs04.getInt(1);
				    			}while(rs04.next());
				    		
			    	  }	
		       }
		       
		       
		     }
		        NumberFormat numberFormat = NumberFormat.getInstance();  
		        
		        // 设置精确到小数点后2位  
		   
		        numberFormat.setMaximumFractionDigits(2); 
		        String result1 = numberFormat.format((float) zeroshenglv / (float) (zeroshenglv+oneshenglv+twoshenglv+fuyishenglv+fuershenglv) * 100);
		        String result2 = numberFormat.format((float) oneshenglv / (float) (zeroshenglv+oneshenglv+twoshenglv+fuyishenglv+fuershenglv) * 100);
		        String result3 = numberFormat.format((float) twoshenglv / (float) (zeroshenglv+oneshenglv+twoshenglv+fuyishenglv+fuershenglv) * 100);
		        String result4 = numberFormat.format((float) fuyishenglv / (float) (zeroshenglv+oneshenglv+twoshenglv+fuyishenglv+fuershenglv) * 100);
		        String result5 = numberFormat.format((float) fuershenglv / (float) (zeroshenglv+oneshenglv+twoshenglv+fuyishenglv+fuershenglv) * 100);
		       
		       System.out.println("横向十位-2的胜率 :"+ result5 + "%"+"   "+fuershenglv);
		       System.out.println("横向十位-1的胜率 :"+ result4 + "%"+"   "+fuyishenglv); 

		       System.out.println("横向十位0的胜率 :"+ result1 + "%"+"   "+zeroshenglv);
		       System.out.println("横向十位1的胜率 :"+ result2 + "%"+"   "+oneshenglv);
		       System.out.println("横向十位2的胜率 :"+ result3 + "%"+"   "+twoshenglv);
		       
		   }
		   
		  } catch (Exception e) {
		   // TODO Auto-generated catch block   
		   e.printStackTrace();
		  }
		}

	public void ychx3(Goddess g)throws Exception{
		 try {
		   Connection conn = DBUtil.getConnection();//建立connection
		   Statement stmt = conn.createStatement();
		   Statement stmt2 = conn.createStatement();
		   Statement stmt3 = conn.createStatement();
		   Statement stmt4 = conn.createStatement();
		   Statement stmt5=conn.createStatement();
		   Statement stmt6=conn.createStatement();
		   PreparedStatement pre=null;
		   conn.setAutoCommit(false);// 更改jdbc事务的默认提交方式 
		   String sql = "select * from zero8";//查询语句
		   ResultSet rs = stmt.executeQuery(sql);//得到结果集
		   conn.commit();//事务提交
		   conn.setAutoCommit(true);// 更改jdbc事务的默认提交方式 
		   List<String> list=new ArrayList<String>();//创建取结果的列表，之所以使用列表，不用数组，因为现在还不知道结果有多少，不能确定数组长度，所有先用list接收，然后转为数组
		   while (rs.next()) {//如果有数据，取第一列添加如list
		    list.add(rs.getString(3));
		   }
		   if(list != null && list.size()>0){//如果list中存入了数据，转化为数组
		    String[] arr=new String[list.size()];//创建一个和list长度一样的数组
		    for(int i=0;i<list.size();i++){
		     arr[i]=list.get(i);//数组赋值了。
		    }
		    
		      int fuyishenglv=0;
		      int fuershenglv=0;
		      int zeroshenglv=0;
			  int oneshenglv=0;
			  int twoshenglv=0;
		       //输出数组
		       for(int i=0;i<arr.length+100;i++){
		    	int yaoyucededata=1201;//i+1;
		       if(i==(yaoyucededata-1)){
			    	  for(int q=10;q>0;q--){ //应该计入q(i)个数进首行,q<256
			    		  String stringq=String.valueOf(q);

					      String shouhang="";                  //清空首行

				    		  for(int j=(i-1);(i-1)-j<q;j--){                  //这个for记录0到i-1，即起点数
				    			  
				    			  shouhang +=arr[j];		//赋值不同q下的首行
				    		  }
				    		 System.out.println(shouhang);
				    	   	//0 的胜率
					  	    String sqlselect0="select shenglv from "+"ccc"+stringq+" where (shouhang='"+shouhang+"')"
					  	    			+"and"+"(jieguo=3"+")";
					    	ResultSet rs00 = stmt2.executeQuery(sqlselect0);
					    		
				    		  //1 的胜率
				  	    	String sqlselect1="select shenglv from "+"ccc"+stringq+" where (shouhang='"+shouhang+"')"
				  	    			+"and"+"(jieguo=4"+")";
				    		ResultSet rs01 = stmt3.executeQuery(sqlselect1);
				    		
				    		//2 的胜率
				  	    	String sqlselect2="select shenglv from "+"ccc"+stringq+" where (shouhang='"+shouhang+"')"
				  	    			+"and"+"(jieguo=5"+")";
				    		ResultSet rs02 = stmt4.executeQuery(sqlselect2);
				    		
				  	    	String sqlselect3="select shenglv from "+"ccc"+stringq+" where (shouhang='"+shouhang+"')"
				  	    			+"and"+"(jieguo=2"+")";
				    		ResultSet rs03 = stmt5.executeQuery(sqlselect3);
				    		
				  	    	String sqlselect4="select shenglv from "+"ccc"+stringq+" where (shouhang='"+shouhang+"')"
				  	    			+"and"+"(jieguo=1"+")";
				    		ResultSet rs04 = stmt6.executeQuery(sqlselect4);
				  
				    		if(rs01.next())
				    			do{
							   
							    oneshenglv +=rs01.getInt(1);
				    			}while(rs01.next());
				    		if(rs00.next())
				    			do{
						    		
						    		zeroshenglv +=rs00.getInt(1);
				    			}while(rs00.next());
				    		if(rs02.next())
				    			do{
				    			
						    		twoshenglv +=rs02.getInt(1);
				    			}while(rs02.next());
				    		
				    		if(rs03.next())
				    			do{
				    			
						    		fuyishenglv +=rs03.getInt(1);
				    			}while(rs03.next());
				    		
				    		if(rs04.next())
				    			do{
				    			
						    		fuershenglv +=rs04.getInt(1);
				    			}while(rs04.next());
				    		
			    	  }	
		       }
		       
		       
		     }
		        NumberFormat numberFormat = NumberFormat.getInstance();  
		        
		        // 设置精确到小数点后2位  
		   
		        numberFormat.setMaximumFractionDigits(2); 
		        String result1 = numberFormat.format((float) zeroshenglv / (float) (zeroshenglv+oneshenglv+twoshenglv+fuyishenglv+fuershenglv) * 100);
		        String result2 = numberFormat.format((float) oneshenglv / (float) (zeroshenglv+oneshenglv+twoshenglv+fuyishenglv+fuershenglv) * 100);
		        String result3 = numberFormat.format((float) twoshenglv / (float) (zeroshenglv+oneshenglv+twoshenglv+fuyishenglv+fuershenglv) * 100);
		        String result4 = numberFormat.format((float) fuyishenglv / (float) (zeroshenglv+oneshenglv+twoshenglv+fuyishenglv+fuershenglv) * 100);
		        String result5 = numberFormat.format((float) fuershenglv / (float) (zeroshenglv+oneshenglv+twoshenglv+fuyishenglv+fuershenglv) * 100);
		       
		       System.out.println("横向个位-2的胜率 :"+ result5 + "%"+"   "+fuershenglv);
		       System.out.println("横向个位-1的胜率 :"+ result4 + "%"+"   "+fuyishenglv); 

		       System.out.println("横向个位0的胜率 :"+ result1 + "%"+"   "+zeroshenglv);
		       System.out.println("横向个位1的胜率 :"+ result2 + "%"+"   "+oneshenglv);
		       System.out.println("横向个位2的胜率 :"+ result3 + "%"+"   "+twoshenglv);
		       
		   }
		   
		  } catch (Exception e) {
		   // TODO Auto-generated catch block   
		   e.printStackTrace();
		  }
		}
	
	
	public void ycsj(Goddess g)throws Exception{
		 try {
		   Connection conn = DBUtil.getConnection();//建立connection
		   Statement sjstmt = conn.createStatement();
		   Statement sjstmt2 = conn.createStatement();
		   Statement sjstmt3 = conn.createStatement();
		   Statement sjstmt4 = conn.createStatement();
		   PreparedStatement pre=null;
		   conn.setAutoCommit(false);// 更改jdbc事务的默认提交方式 
		   String sql = "select * from zero9";//查询语句
		   ResultSet rs = sjstmt.executeQuery(sql);//得到结果集
		   conn.commit();//事务提交
		   conn.setAutoCommit(true);// 更改jdbc事务的默认提交方式 
		   List<String> list=new ArrayList<String>();//创建取结果的列表，之所以使用列表，不用数组，因为现在还不知道结果有多少，不能确定数组长度，所有先用list接收，然后转为数组
		   while (rs.next()) {//如果有数据，取第一列添加如list
		    list.add(rs.getString(1));
		   }
		   if(list != null && list.size()>0){//如果list中存入了数据，转化为数组
		    String[] arr=new String[list.size()];//创建一个和list长度一样的数组
		    for(int i=0;i<list.size();i++){
		     arr[i]=list.get(i);//数组赋值了。
		    }
		      int zeroshenglv=0;
			  int oneshenglv=0;
			  int twoshenglv=0;
		       //输出数组
			  
			  for(int yaoyucededata=812;yaoyucededata>8;yaoyucededata=yaoyucededata-2){
		       for(int i=0;i<arr.length+100;i++){
		      
		       if(i==(yaoyucededata-1)){
			    	  for(int q=8;q>0;q--){ //应该计入q(i)个数进首行,q<256
			    		  String stringq=String.valueOf(q);

					      String shouhang="";                  //清空首行

				    		  for(int j=(i-1);(i-1)-j<q;j--){                  //这个for记录0到i-1，即起点数
				    			  
				    			  shouhang +=arr[j];		//赋值不同q下的首行
				    		  }
				    		 System.out.println(shouhang);
				    	   	//0 的胜率
					  	    String sqlselect0="select shenglv from "+"sja"+stringq+" where (shouhang='"+shouhang+"')"
					  	    			+"and"+"(jieguo=0"+")";
					    	ResultSet rs00 = sjstmt2.executeQuery(sqlselect0);
					    		
				    		  //1 的胜率
				  	    	String sqlselect1="select shenglv from "+"sja"+stringq+" where (shouhang='"+shouhang+"')"
				  	    			+"and"+"(jieguo=1"+")";
				    		ResultSet rs01 = sjstmt3.executeQuery(sqlselect1);
				    		
				    		//2 的胜率
				  	    	String sqlselect2="select shenglv from "+"sja"+stringq+" where (shouhang='"+shouhang+"')"
				  	    			+"and"+"(jieguo=2"+")";
				    		ResultSet rs02 = sjstmt4.executeQuery(sqlselect2);
				    		
						      zeroshenglv=0;
							  oneshenglv=0;
							  twoshenglv=0;
				    		if(rs01.next())
				    			do{
							    
							    oneshenglv +=rs01.getInt(1);
							    System.out.println("1sheng"+oneshenglv);
				    			}while(rs01.next());
				    		if(rs00.next())
				    			do{
				    			
						    		zeroshenglv +=rs00.getInt(1);
						    		System.out.println("0sheng"+zeroshenglv);
				    			}while(rs00.next());
				    		if(rs02.next())
				    			do{
				    			
						    		twoshenglv +=rs02.getInt(1);
						    		System.out.println("2sheng"+twoshenglv);
				    			}while(rs02.next());
				    		
				    		
			    	  }	
		       }
		       
		       
		     }
		       
		        NumberFormat numberFormat = NumberFormat.getInstance();  
		        
		        // 设置精确到小数点后2位  
		   
		        numberFormat.setMaximumFractionDigits(2); 
		        String result1 = numberFormat.format((float) zeroshenglv / (float) (zeroshenglv+oneshenglv+twoshenglv) * 100);
		        String result2 = numberFormat.format((float) oneshenglv / (float) (zeroshenglv+oneshenglv+twoshenglv) * 100);
		        String result3 = numberFormat.format((float) twoshenglv / (float) (zeroshenglv+oneshenglv+twoshenglv) * 100);
		       System.out.println("百位0的胜率 :"+ result1 + "%"+"   "+zeroshenglv);
		       System.out.println("百位1的胜率 :"+ result2 + "%"+"   "+oneshenglv);
		       System.out.println("百位2的胜率 :"+ result3 + "%"+"   "+twoshenglv);
		       
		       
			 }

		   }
		   
		  } catch (Exception e) {
		   // TODO Auto-generated catch block   
		   e.printStackTrace();
		  }
		}
	
	
	public void ycsj2(Goddess g)throws Exception{
		 try {
		   Connection conn = DBUtil.getConnection();//建立connection
		   Statement stmt = conn.createStatement();
		   Statement stmt2 = conn.createStatement();
		   Statement stmt3 = conn.createStatement();
		   Statement stmt4 = conn.createStatement();
		   PreparedStatement pre=null;
		   conn.setAutoCommit(false);// 更改jdbc事务的默认提交方式 
		   String sql = "select * from zero9";//查询语句
		   ResultSet rs = stmt.executeQuery(sql);//得到结果集
		   conn.commit();//事务提交
		   conn.setAutoCommit(true);// 更改jdbc事务的默认提交方式 
		   List<String> list=new ArrayList<String>();//创建取结果的列表，之所以使用列表，不用数组，因为现在还不知道结果有多少，不能确定数组长度，所有先用list接收，然后转为数组
		   while (rs.next()) {//如果有数据，取第一列添加如list
		    list.add(rs.getString(2));
		   }
		   if(list != null && list.size()>0){//如果list中存入了数据，转化为数组
		    String[] arr=new String[list.size()];//创建一个和list长度一样的数组
		    for(int i=0;i<list.size();i++){
		     arr[i]=list.get(i);//数组赋值了。
		    }
		      int zeroshenglv=0;
			  int oneshenglv=0;
			  int twoshenglv=0;
		       //输出数组
		       for(int i=0;i<arr.length+100;i++){
		    	int yaoyucededata=812;
		       if(i==(yaoyucededata-1)){
			    	  for(int q=8;q>0;q--){ //应该计入q(i)个数进首行,q<256
			    		  String stringq=String.valueOf(q);

					      String shouhang="";                  //清空首行

				    		  for(int j=(i-1);(i-1)-j<q;j--){                  //这个for记录0到i-1，即起点数
				    			  
				    			  shouhang +=arr[j];		//赋值不同q下的首行
				    		  }
				    		  System.out.println(shouhang);
				    	   	//0 的胜率
					  	    String sqlselect0="select shenglv from "+"sjb"+stringq+" where (shouhang='"+shouhang+"')"
					  	    			+"and"+"(jieguo=0"+")";
					    	ResultSet rs00 = stmt2.executeQuery(sqlselect0);
					    		
				    		  //1 的胜率
				  	    	String sqlselect1="select shenglv from "+"sjb"+stringq+" where (shouhang='"+shouhang+"')"
				  	    			+"and"+"(jieguo=1"+")";
				    		ResultSet rs01 = stmt3.executeQuery(sqlselect1);
				    		
				    		//2 的胜率
				  	    	String sqlselect2="select shenglv from "+"sjb"+stringq+" where (shouhang='"+shouhang+"')"
				  	    			+"and"+"(jieguo=2"+")";
				    		ResultSet rs02 = stmt4.executeQuery(sqlselect2);
				  
				    		if(rs01.next())
				    			do{
							   
							    oneshenglv +=rs01.getInt(1);
							    System.out.println(oneshenglv);
				    			}while(rs01.next());
				    		if(rs00.next())
				    			do{
						    		
						    		zeroshenglv +=rs00.getInt(1);
						    	    System.out.println(zeroshenglv);
				    			}while(rs00.next());
				    		if(rs02.next())
				    			do{
				    				
						    		twoshenglv +=rs02.getInt(1);
						    	    System.out.println(twoshenglv);
				    			}while(rs02.next());
				    		
				    		
			    	  }	
		       }
		       
		       
		     }
		        NumberFormat numberFormat = NumberFormat.getInstance();  
		        
		        // 设置精确到小数点后2位  
		   
		        numberFormat.setMaximumFractionDigits(2); 
		        String result1 = numberFormat.format((float) zeroshenglv / (float) (zeroshenglv+oneshenglv+twoshenglv) * 100);
		        String result2 = numberFormat.format((float) oneshenglv / (float) (zeroshenglv+oneshenglv+twoshenglv) * 100);
		        String result3 = numberFormat.format((float) twoshenglv / (float) (zeroshenglv+oneshenglv+twoshenglv) * 100);
		       System.out.println("十位0的胜率 :"+ result1 + "%"+"   "+zeroshenglv);
		       System.out.println("十位1的胜率 :"+ result2 + "%"+"   "+oneshenglv);
		       System.out.println("十位2的胜率 :"+ result3 + "%"+"   "+twoshenglv);
		   }
		   
		  } catch (Exception e) {
		   // TODO Auto-generated catch block   
		   e.printStackTrace();
		  }
		}
	
	
	public void ycsj3(Goddess g)throws Exception{
		 try {
		   Connection conn = DBUtil.getConnection();//建立connection
		   Statement stmt = conn.createStatement();
		   Statement stmt2 = conn.createStatement();
		   Statement stmt3 = conn.createStatement();
		   Statement stmt4 = conn.createStatement();
		   PreparedStatement pre=null;
		   conn.setAutoCommit(false);// 更改jdbc事务的默认提交方式 
		   String sql = "select * from zero9";//查询语句
		   ResultSet rs = stmt.executeQuery(sql);//得到结果集
		   conn.commit();//事务提交
		   conn.setAutoCommit(true);// 更改jdbc事务的默认提交方式 
		   List<String> list=new ArrayList<String>();//创建取结果的列表，之所以使用列表，不用数组，因为现在还不知道结果有多少，不能确定数组长度，所有先用list接收，然后转为数组
		   while (rs.next()) {//如果有数据，取第一列添加如list
		    list.add(rs.getString(3));
		   }
		   if(list != null && list.size()>0){//如果list中存入了数据，转化为数组
		    String[] arr=new String[list.size()];//创建一个和list长度一样的数组
		    for(int i=0;i<list.size();i++){
		     arr[i]=list.get(i);//数组赋值了。
		    }
		      int zeroshenglv=0;
			  int oneshenglv=0;
			  int twoshenglv=0;
		       //输出数组
		       for(int i=0;i<arr.length+100;i++){
		    	int yaoyucededata=812;
		       if(i==(yaoyucededata-1)){
			    	  for(int q=8;q>0;q--){ //应该计入q(i)个数进首行,q<256
			    		  String stringq=String.valueOf(q);

					      String shouhang="";                  //清空首行

				    		  for(int j=(i-1);(i-1)-j<q;j--){                  //这个for记录0到i-1，即起点数
				    			  
				    			  shouhang +=arr[j];		//赋值不同q下的首行
				    		  }
				    		  System.out.println(shouhang);
				    	   	//0 的胜率
					  	    String sqlselect0="select shenglv from "+"sjc"+stringq+" where (shouhang='"+shouhang+"')"
					  	    			+"and"+"(jieguo=0"+")";
					    	ResultSet rs00 = stmt2.executeQuery(sqlselect0);
					    		
				    		  //1 的胜率
				  	    	String sqlselect1="select shenglv from "+"sjc"+stringq+" where (shouhang='"+shouhang+"')"
				  	    			+"and"+"(jieguo=1"+")";
				    		ResultSet rs01 = stmt3.executeQuery(sqlselect1);
				    		
				    		//2 的胜率
				  	    	String sqlselect2="select shenglv from "+"sjc"+stringq+" where (shouhang='"+shouhang+"')"
				  	    			+"and"+"(jieguo=2"+")";
				    		ResultSet rs02 = stmt4.executeQuery(sqlselect2);
				  
				    		if(rs01.next())
				    			do{
							    
							    oneshenglv +=rs01.getInt(1);
							    System.out.println("1sheng"+oneshenglv);
				    			}while(rs01.next());
				    		if(rs00.next())
				    			do{
						    		
						    		zeroshenglv +=rs00.getInt(1);
						    	    System.out.println("0sheng"+zeroshenglv);
				    			}while(rs00.next());
				    		if(rs02.next())
				    			do{
				    				
						    		twoshenglv +=rs02.getInt(1);
						    	    System.out.println("2sheng"+twoshenglv);
				    			}while(rs02.next());
				    		
				    		
			    	  }	
		       }
		       
		       
		     }
		        NumberFormat numberFormat = NumberFormat.getInstance();  
		        
		        // 设置精确到小数点后2位  
		   
		        numberFormat.setMaximumFractionDigits(2); 
		        String result1 = numberFormat.format((float) zeroshenglv / (float) (zeroshenglv+oneshenglv+twoshenglv) * 100);
		        String result2 = numberFormat.format((float) oneshenglv / (float) (zeroshenglv+oneshenglv+twoshenglv) * 100);
		        String result3 = numberFormat.format((float) twoshenglv / (float) (zeroshenglv+oneshenglv+twoshenglv) * 100);
		       System.out.println("个位0的胜率 :"+ result1 + "%"+"   "+zeroshenglv);
		       System.out.println("个位1的胜率 :"+ result2 + "%"+"   "+oneshenglv);
		       System.out.println("个位2的胜率 :"+ result3 + "%"+"   "+twoshenglv);
		   }
		   
		  } catch (Exception e) {
		   // TODO Auto-generated catch block   
		   e.printStackTrace();
		  }
		}
	
	public void getsjcolsone(Goddess g)throws Exception{
		 try {
		   Connection conn = DBUtil.getConnection();//建立connection
		   Statement sjstmt = conn.createStatement();
		   Statement sjstmt2 = conn.createStatement();
		   Statement sjstmt3 = conn.createStatement();
		   Statement sjstmt4 = conn.createStatement();
		   Statement stmt = conn.createStatement();
		   PreparedStatement pre=null;
		   PreparedStatement pre2=null;
		   conn.setAutoCommit(false);// 更改jdbc事务的默认提交方式 
		   String sql = "select * from zero9";//查询语句
		   ResultSet rs = stmt.executeQuery(sql);//得到结果集
		   conn.commit();//事务提交
		   conn.setAutoCommit(true);// 更改jdbc事务的默认提交方式 
		   List<String> list=new ArrayList<String>();//创建取结果的列表，之所以使用列表，不用数组，因为现在还不知道结果有多少，不能确定数组长度，所有先用list接收，然后转为数组
		   while (rs.next()) {//如果有数据，取第一列添加如list
		    list.add(rs.getString(1));
		   }
		   if(list != null && list.size()>0){//如果list中存入了数据，转化为数组
		    String[] arr=new String[list.size()];//创建一个和list长度一样的数组
		    for(int i=0;i<list.size();i++){
		     arr[i]=list.get(i);//数组赋值了。
		    }
		       //输出数组
		       for(int i=0;i<arr.length;i++){
		    	 
		    	   if(i<9){
		    		   for(int q=i;q>0;q--){ //应该计入q(i)个数进首行,q<256
		    		  String stringq=String.valueOf(q);

				      String shouhang="";                  //清空首行
		    		  if(validateTableNameExist("sja"+stringq)==false){
		    			  //如果table q 不存在,创建一个新table q（i代表前面有i个数）
		    			
		    			  String sql2="CREATE TABLE "+"sja"+stringq
		    					  +"(shouhang TEXT,"+
		    					  "jieguo TEXT,shenglv int(5))";
		    			  stmt.executeUpdate(sql2);
		    		  }
	
		    		  


			    		  for(int j=(i-1);(i-1)-j<q;j--){                  //这个for记录0到i-1，即起点数
			    			  
			    			  shouhang +=arr[j];		//赋值不同q下的首行
			    		  }
			    		  
			    	String sqlselect="select count(*) from "+"sja"+stringq+" where shouhang='"+shouhang+"'"+"and jieguo="+"'"+arr[i]+"'";
			    	
			    	try { 
			    		ResultSet rs2 = stmt.executeQuery(sqlselect); 
			    		int count = 0; 
			    		while (rs2.next()) { 
			    		count = rs2.getInt(1); 
			    		} 
			    		if(count == 0) { 
			    		//没同样的记录,该干什么干什么 
			    			pre=conn.prepareStatement("insert into sja"+stringq+"(shouhang,jieguo,shenglv) values (?,?,?)");
			       		    pre.setString(1, shouhang);
			       		    pre.setString(2, arr[i]);
			       		    pre.setInt(3, 0);
			       		    pre.executeUpdate();	
			    		} else { 
			    		//有同样的记录,该干什么干什么 
			    		//首先读取胜率的数据，然后进行updatae修改
			    			
			    		String sql4="UPDATE "+"sja"+stringq+" SET shenglv =shenglv+1 WHERE shouhang='"+shouhang+"'"+"and jieguo="+"'"+arr[i]+"'";
			    		stmt.executeUpdate(sql4);
			    		} 
			    	} catch (SQLException e) { 
			    	e.printStackTrace(); 
			    	}	
			    		  

		    	  }	
		    	   }
		    	    
 
		    	   if(i>8){
		    		   for(int q=8;q>0;q--){ //应该计入q(i)个数进首行,q<256
				    		  String stringq=String.valueOf(q);

						      String shouhang="";                  //清空首行
				    		  if(validateTableNameExist("sja"+stringq)==false){
				    			  //如果table q 不存在,创建一个新table q（i代表前面有i个数）
				    			
				    			  String sql2="CREATE TABLE "+"sja"+stringq
				    					  +"(shouhang TEXT,"+
				    					  "jieguo TEXT,shenglv int(5))";
				    			  stmt.executeUpdate(sql2);
				    		  }
			
				    		  


					    		  for(int j=(i-1);(i-1)-j<q;j--){                  //这个for记录0到i-1，即起点数
					    			  
					    			  shouhang +=arr[j];		//赋值不同q下的首行
					    		  }
					    		  
					    	String sqlselect="select count(*) from "+"sja"+stringq+" where shouhang='"+shouhang+"'"+"and jieguo="+"'"+arr[i]+"'";
					    	
					    	try { 
					    		ResultSet rs2 = stmt.executeQuery(sqlselect); 
					    		int count = 0; 
					    		while (rs2.next()) { 
					    		count = rs2.getInt(1); 
					    		} 
					    		if(count == 0) { 
					    		//没同样的记录,该干什么干什么 
					    			pre=conn.prepareStatement("insert into sja"+stringq+"(shouhang,jieguo,shenglv) values (?,?,?)");
					       		    pre.setString(1, shouhang);
					       		    pre.setString(2, arr[i]);
					       		    pre.setInt(3, 0);
					       		    pre.executeUpdate();	
					    		} else { 
					    		//有同样的记录,该干什么干什么 
					    		//首先读取胜率的数据，然后进行updatae修改
					    			
					    		String sql4="UPDATE "+"sja"+stringq+" SET shenglv =shenglv+1 WHERE shouhang='"+shouhang+"'"+"and jieguo="+"'"+arr[i]+"'";
					    		stmt.executeUpdate(sql4);
					    		} 
					    	} catch (SQLException e) { 
					    	e.printStackTrace(); 
					    	}	
					    		  

				    	  }	
		    		   
		    	   }
		    	  
				      int zeroshenglv=0;
					  int oneshenglv=0;
					  int twoshenglv=0;
				       //输出数组
					  
					  for(int yaoyucededata=i+1;yaoyucededata>100;yaoyucededata=yaoyucededata-2){
				     
				      
				       if(i==(yaoyucededata-1)){
					    	  for(int q=8;q>0;q--){ //应该计入q(i)个数进首行,q<256
					    		  String stringq=String.valueOf(q);

							      String shouhang="";                  //清空首行

						    		  for(int j=(i-1);(i-1)-j<q;j--){                  //这个for记录0到i-1，即起点数
						    			  
						    			  shouhang +=arr[j];		//赋值不同q下的首行
						    		  }
						    		 System.out.println(shouhang);
						    	   	//0 的胜率
							  	    String sqlselect0="select shenglv from "+"sja"+stringq+" where (shouhang='"+shouhang+"')"
							  	    			+"and"+"(jieguo=0"+")";
							    	ResultSet rs00 = sjstmt2.executeQuery(sqlselect0);
							    		
						    		  //1 的胜率
						  	    	String sqlselect1="select shenglv from "+"sja"+stringq+" where (shouhang='"+shouhang+"')"
						  	    			+"and"+"(jieguo=1"+")";
						    		ResultSet rs01 = sjstmt3.executeQuery(sqlselect1);
						    		
						    		//2 的胜率
						  	    	String sqlselect2="select shenglv from "+"sja"+stringq+" where (shouhang='"+shouhang+"')"
						  	    			+"and"+"(jieguo=2"+")";
						    		ResultSet rs02 = sjstmt4.executeQuery(sqlselect2);
						    		
								      zeroshenglv=0;
									  oneshenglv=0;
									  twoshenglv=0;
						    		if(rs01.next())
						    			do{
									    
									    oneshenglv +=rs01.getInt(1);
									    System.out.println("1sheng"+oneshenglv);
						    			}while(rs01.next());
						    		if(rs00.next())
						    			do{
						    			
								    		zeroshenglv +=rs00.getInt(1);
								    		System.out.println("0sheng"+zeroshenglv);
						    			}while(rs00.next());
						    		if(rs02.next())
						    			do{
						    			
								    		twoshenglv +=rs02.getInt(1);
								    		System.out.println("2sheng"+twoshenglv);
						    			}while(rs02.next());
						    		
						    		
					    	  }	
				       }
				       
				       
				     
				       
				        NumberFormat numberFormat = NumberFormat.getInstance();  
				        
				        // 设置精确到小数点后2位  
				   
				        numberFormat.setMaximumFractionDigits(2); 
				        String result1 = numberFormat.format((float) zeroshenglv / (float) (zeroshenglv+oneshenglv+twoshenglv) * 100);
				        String result2 = numberFormat.format((float) oneshenglv / (float) (zeroshenglv+oneshenglv+twoshenglv) * 100);
				        String result3 = numberFormat.format((float) twoshenglv / (float) (zeroshenglv+oneshenglv+twoshenglv) * 100);
				       System.out.println("百位0的胜率 :"+ result1 + "%"+"   "+zeroshenglv);
				       System.out.println("百位1的胜率 :"+ result2 + "%"+"   "+oneshenglv);
				       System.out.println("百位2的胜率 :"+ result3 + "%"+"   "+twoshenglv);
				       Double  inresult1=Double.parseDouble(result1);
				       Double  inresult2=Double.parseDouble(result2);
				       Double  inresult3=Double.parseDouble(result3);
				       double panduan=0;
				       double chabi2=inresult3-inresult2;
				       double chabi1=inresult2-inresult3;
				       double chabi0=inresult1-inresult2;
				       if(inresult3>inresult2){
				    	   panduan=2;
				       }else if(inresult2>inresult3){
				    	   panduan=1;
				       }else{
				    	   panduan=0;
				       }
				       if(i==arr.length-1){
				    	   double bbb=0;
				    	   if(panduan==2){
				    		   bbb=chabi2;
				    	   }
				    	   if(panduan==1){
				    		   bbb=chabi1;
				    	   }
				    	   if(panduan==0){
				    		   bbb=chabi0;
				    	   }
				    	   int arrrr=Integer.parseInt("0");
					    	pre=conn.prepareStatement("insert into baidata"+"(panduan1,chabi1,jieguo1) values (?,?,?)");
			       		    pre.setDouble(1, panduan);
			       		    pre.setDouble(2, bbb);
			       		    pre.setDouble(3, arrrr);
			      
			       		    pre.executeUpdate();
				       }else{
				    	   double bbb=0;
				    	   if(panduan==2){
				    		   bbb=chabi2;
				    	   }
				    	   if(panduan==1){
				    		   bbb=chabi1;
				    	   }
				    	   if(panduan==0){
				    		   bbb=chabi0;
				    	   }
				    	   int arrrr=Integer.parseInt(arr[i+1]);
				    	   int arrrrr=Integer.parseInt(arr[i]);
				    	   int pppppppp=0;
				    	   if(arrrr==0&&panduan==1||arrrr==1&&panduan==1||arrrr==1&&panduan==2){
				    		   arrrr=1;
				    	   }else if(arrrr==0&&panduan==2||arrrr==2&&panduan==2||arrrr==2&&panduan==1){
				    		   arrrr=2;
				    	   }
				    	pre=conn.prepareStatement("insert into baidata"+"(panduan1,chabi1,jieguo1) values (?,?,?)");
				    	pre.setDouble(1, panduan);
		       		    pre.setDouble(2, bbb);
		       		    pre.setDouble(3, arrrr);
		       		    pre.executeUpdate();
				       }

				       
				       
				       
					   pre2 = conn.prepareStatement("delete from baidata where panduan1='0'");//查询语句
					   pre2.executeUpdate();//得到结果集
				       break;
				       
					 }
		       System.out.println(arr[i]+"   baiwei:  "+i);
		     }
		       

		   }
		   
		  } catch (Exception e) {
		   // TODO Auto-generated catch block   
		   e.printStackTrace();
		  }
		}









	public void getsjcolstwo(Goddess g)throws Exception{
		 try {
		   Connection conn = DBUtil.getConnection();//建立connection
		   Statement stmt = conn.createStatement();
		   Statement sjstmt2 = conn.createStatement();
		   Statement sjstmt3 = conn.createStatement();
		   Statement sjstmt4 = conn.createStatement();
		   PreparedStatement pre=null;
		   PreparedStatement pre2=null;
		   conn.setAutoCommit(false);// 更改jdbc事务的默认提交方式 
		   String sql = "select * from zero9";//查询语句
		   ResultSet rs = stmt.executeQuery(sql);//得到结果集
		   conn.commit();//事务提交
		   conn.setAutoCommit(true);// 更改jdbc事务的默认提交方式 
		   List<String> list=new ArrayList<String>();//创建取结果的列表，之所以使用列表，不用数组，因为现在还不知道结果有多少，不能确定数组长度，所有先用list接收，然后转为数组
		   while (rs.next()) {//如果有数据，取第一列添加如list
		    list.add(rs.getString(2));
		   }
		   if(list != null && list.size()>0){//如果list中存入了数据，转化为数组
		    String[] arr=new String[list.size()];//创建一个和list长度一样的数组
		    for(int i=0;i<list.size();i++){
		     arr[i]=list.get(i);//数组赋值了。
		    }
		       //输出数组
		       for(int i=0;i<arr.length;i++){
		    	   if(i<9){
		    		   for(int q=i;q>0;q--){ //应该计入q(i)个数进首行,q<256
				    		  String stringq=String.valueOf(q);

						      String shouhang="";                  //清空首行
				    		  if(validateTableNameExist("sjb"+stringq)==false){
				    			  //如果table q 不存在,创建一个新table q（i代表前面有i个数）
				    			
				    			  String sql2="CREATE TABLE "+"sjb"+stringq
				    					  +"(shouhang TEXT,"+
				    					  "jieguo TEXT,shenglv int(5))";
				    			  stmt.executeUpdate(sql2);
				    		  }
			
				    		  


					    		  for(int j=(i-1);(i-1)-j<q;j--){                  //这个for记录0到i-1，即起点数
					    			  
					    			  shouhang +=arr[j];		//赋值不同q下的首行
					    		  }
					    		  
					    	String sqlselect="select count(*) from "+"sjb"+stringq+" where shouhang='"+shouhang+"'"+"and jieguo="+"'"+arr[i]+"'";
					    	
					    	try { 
					    		ResultSet rs2 = stmt.executeQuery(sqlselect); 
					    		int count = 0; 
					    		while (rs2.next()) { 
					    		count = rs2.getInt(1); 
					    		} 
					    		if(count == 0) { 
					    		//没同样的记录,该干什么干什么 
					    			pre=conn.prepareStatement("insert into sjb"+stringq+"(shouhang,jieguo,shenglv) values (?,?,?)");
					       		    pre.setString(1, shouhang);
					       		    pre.setString(2, arr[i]);
					       		    pre.setInt(3, 0);
					       		    pre.executeUpdate();	
					    		} else { 
					    		//有同样的记录,该干什么干什么 
					    		//首先读取胜率的数据，然后进行updatae修改
					    			
					    		String sql4="UPDATE "+"sjb"+stringq+" SET shenglv =shenglv+1 WHERE shouhang='"+shouhang+"'"+"and jieguo="+"'"+arr[i]+"'";
					    		stmt.executeUpdate(sql4);
					    		} 
					    	} catch (SQLException e) { 
					    	e.printStackTrace(); 
					    	}	
					    		  

				    	  }
		    	   }
		    	  if(i>8){
		    		  for(int q=8;q>0;q--){ //应该计入q(i)个数进首行,q<256
			    		  String stringq=String.valueOf(q);

					      String shouhang="";                  //清空首行
			    		  if(validateTableNameExist("sjb"+stringq)==false){
			    			  //如果table q 不存在,创建一个新table q（i代表前面有i个数）
			    			
			    			  String sql2="CREATE TABLE "+"sjb"+stringq
			    					  +"(shouhang TEXT,"+
			    					  "jieguo TEXT,shenglv int(5))";
			    			  stmt.executeUpdate(sql2);
			    		  }
		
			    		  


				    		  for(int j=(i-1);(i-1)-j<q;j--){                  //这个for记录0到i-1，即起点数
				    			  
				    			  shouhang +=arr[j];		//赋值不同q下的首行
				    		  }
				    		  
				    	String sqlselect="select count(*) from "+"sjb"+stringq+" where shouhang='"+shouhang+"'"+"and jieguo="+"'"+arr[i]+"'";
				    	
				    	try { 
				    		ResultSet rs2 = stmt.executeQuery(sqlselect); 
				    		int count = 0; 
				    		while (rs2.next()) { 
				    		count = rs2.getInt(1); 
				    		} 
				    		if(count == 0) { 
				    		//没同样的记录,该干什么干什么 
				    			pre=conn.prepareStatement("insert into sjb"+stringq+"(shouhang,jieguo,shenglv) values (?,?,?)");
				       		    pre.setString(1, shouhang);
				       		    pre.setString(2, arr[i]);
				       		    pre.setInt(3, 0);
				       		    pre.executeUpdate();	
				    		} else { 
				    		//有同样的记录,该干什么干什么 
				    		//首先读取胜率的数据，然后进行updatae修改
				    			
				    		String sql4="UPDATE "+"sjb"+stringq+" SET shenglv =shenglv+1 WHERE shouhang='"+shouhang+"'"+"and jieguo="+"'"+arr[i]+"'";
				    		stmt.executeUpdate(sql4);
				    		} 
				    	} catch (SQLException e) { 
				    	e.printStackTrace(); 
				    	}	
				    		  

			    	  }
		    	  }
		    	  	  
		    	  
			      int zeroshenglv=0;
				  int oneshenglv=0;
				  int twoshenglv=0;
			       //输出数组
				  
				  for(int yaoyucededata=i+1;yaoyucededata>100;yaoyucededata=yaoyucededata-2){
			     
			      
			       if(i==(yaoyucededata-1)){
				    	  for(int q=8;q>0;q--){ //应该计入q(i)个数进首行,q<256
				    		  String stringq=String.valueOf(q);

						      String shouhang="";                  //清空首行

					    		  for(int j=(i-1);(i-1)-j<q;j--){                  //这个for记录0到i-1，即起点数
					    			  
					    			  shouhang +=arr[j];		//赋值不同q下的首行
					    		  }
					    		 System.out.println(shouhang);
					    	   	//0 的胜率
						  	    String sqlselect0="select shenglv from "+"sjb"+stringq+" where (shouhang='"+shouhang+"')"
						  	    			+"and"+"(jieguo=0"+")";
						    	ResultSet rs00 = sjstmt2.executeQuery(sqlselect0);
						    		
					    		  //1 的胜率
					  	    	String sqlselect1="select shenglv from "+"sjb"+stringq+" where (shouhang='"+shouhang+"')"
					  	    			+"and"+"(jieguo=1"+")";
					    		ResultSet rs01 = sjstmt3.executeQuery(sqlselect1);
					    		
					    		//2 的胜率
					  	    	String sqlselect2="select shenglv from "+"sjb"+stringq+" where (shouhang='"+shouhang+"')"
					  	    			+"and"+"(jieguo=2"+")";
					    		ResultSet rs02 = sjstmt4.executeQuery(sqlselect2);
					    		
							      zeroshenglv=0;
								  oneshenglv=0;
								  twoshenglv=0;
					    		if(rs01.next())
					    			do{
								    
								    oneshenglv +=rs01.getInt(1);
								    System.out.println("1sheng"+oneshenglv);
					    			}while(rs01.next());
					    		if(rs00.next())
					    			do{
					    			
							    		zeroshenglv +=rs00.getInt(1);
							    		System.out.println("0sheng"+zeroshenglv);
					    			}while(rs00.next());
					    		if(rs02.next())
					    			do{
					    			
							    		twoshenglv +=rs02.getInt(1);
							    		System.out.println("2sheng"+twoshenglv);
					    			}while(rs02.next());
					    		
					    		
				    	  }	
			       }
			       
			       
			     
			       
			        NumberFormat numberFormat = NumberFormat.getInstance();  
			        
			        // 设置精确到小数点后2位  
			   
			        numberFormat.setMaximumFractionDigits(2); 
			        String result1 = numberFormat.format((float) zeroshenglv / (float) (zeroshenglv+oneshenglv+twoshenglv) * 100);
			        String result2 = numberFormat.format((float) oneshenglv / (float) (zeroshenglv+oneshenglv+twoshenglv) * 100);
			        String result3 = numberFormat.format((float) twoshenglv / (float) (zeroshenglv+oneshenglv+twoshenglv) * 100);
			       System.out.println("十位0的胜率 :"+ result1 + "%"+"   "+zeroshenglv);
			       System.out.println("十位1的胜率 :"+ result2 + "%"+"   "+oneshenglv);
			       System.out.println("十位2的胜率 :"+ result3 + "%"+"   "+twoshenglv);
			       Double  inresult1=Double.parseDouble(result1);
			       Double  inresult2=Double.parseDouble(result2);
			       Double  inresult3=Double.parseDouble(result3);
			       double panduan=0;
			       double chabi2=inresult3-inresult2;
			       double chabi1=inresult2-inresult3;
			       double chabi0=inresult1-inresult2;
			       if(inresult3>inresult2){
			    	   panduan=2;
			       }else if(inresult2>inresult3){
			    	   panduan=1;
			       }else{
			    	   panduan=0;
			       }
			       if(i==arr.length-1){
			    	   double bbb=0;
			    	   if(panduan==2){
			    		   bbb=chabi2;
			    	   }
			    	   if(panduan==1){
			    		   bbb=chabi1;
			    	   }
			    	   if(panduan==0){
			    		   bbb=chabi0;
			    	   }
			    	   int arrrr=Integer.parseInt("0");
				    	pre=conn.prepareStatement("insert into shidata"+"(panduan2,chabi2,jieguo2) values (?,?,?)");
		       		    pre.setDouble(1, panduan);
		       		    pre.setDouble(2, bbb);
		       		    pre.setDouble(3, arrrr);
		      
		       		    pre.executeUpdate();
			       }else{
			    	   double bbb=0;
			    	   if(panduan==2){
			    		   bbb=chabi2;
			    	   }
			    	   if(panduan==1){
			    		   bbb=chabi1;
			    	   }
			    	   if(panduan==0){
			    		   bbb=chabi0;
			    	   }
			    	   int arrrr=Integer.parseInt(arr[i+1]);
			    	   int arrrrr=Integer.parseInt(arr[i]);
			    	   int pppppppp=0;
			    	   if(arrrr==0&&panduan==1||arrrr==1&&panduan==1||arrrr==1&&panduan==2){
			    		   arrrr=1;
			    	   }else if(arrrr==0&&panduan==2||arrrr==2&&panduan==2||arrrr==2&&panduan==1){
			    		   arrrr=2;
			    	   }
			    	pre=conn.prepareStatement("insert into shidata"+"(panduan2,chabi2,jieguo2) values (?,?,?)");
			    	
			    	pre.setDouble(1, panduan);
	       		    pre.setDouble(2, bbb);
	       		    pre.setDouble(3, arrrr);
	       		    pre.executeUpdate();
			       }

				   pre2 = conn.prepareStatement("delete from shidata where panduan2='0'");//查询语句
				   pre2.executeUpdate();//得到结果集
			       break;
			       
				 }
		    	  
		    	  
		    	  
		       System.out.println(arr[i]+"   shiwei:  "+i);
		     }
		       

		   }
		   
		  } catch (Exception e) {
		   // TODO Auto-generated catch block   
		   e.printStackTrace();
		  }
		}
		
	public void getsjcolsthree(Goddess g)throws Exception{
		 try {
		   Connection conn = DBUtil.getConnection();//建立connection
		   Statement stmt = conn.createStatement();
		   Statement sjstmt2 = conn.createStatement();
		   Statement sjstmt3 = conn.createStatement();
		   Statement sjstmt4 = conn.createStatement();
		   PreparedStatement pre=null;
		   PreparedStatement pre2=null;
		   conn.setAutoCommit(false);// 更改jdbc事务的默认提交方式 
		   String sql = "select * from zero9";//查询语句
		   ResultSet rs = stmt.executeQuery(sql);//得到结果集
		   conn.commit();//事务提交
		   conn.setAutoCommit(true);// 更改jdbc事务的默认提交方式 
		   List<String> list=new ArrayList<String>();//创建取结果的列表，之所以使用列表，不用数组，因为现在还不知道结果有多少，不能确定数组长度，所有先用list接收，然后转为数组
		   while (rs.next()) {//如果有数据，取第一列添加如list
		    list.add(rs.getString(3));
		   }
		   if(list != null && list.size()>0){//如果list中存入了数据，转化为数组
		    String[] arr=new String[list.size()];//创建一个和list长度一样的数组
		    for(int i=0;i<list.size();i++){
		     arr[i]=list.get(i);//数组赋值了。
		    }
		       //输出数组
		       for(int i=0;i<arr.length;i++){
		     if(i<9){
		    	 for(int q=i;q>0;q--){ //应该计入q(i)个数进首行,q<256
		    		  String stringq=String.valueOf(q);

				      String shouhang="";                  //清空首行
		    		  if(validateTableNameExist("sjc"+stringq)==false){
		    			  //如果table q 不存在,创建一个新table q（i代表前面有i个数）
		    			
		    			  String sql2="CREATE TABLE "+"sjc"+stringq
		    					  +"(shouhang TEXT,"+
		    					  "jieguo TEXT,shenglv int(5))";
		    			  stmt.executeUpdate(sql2);
		    		  }
	
		    		  


			    		  for(int j=(i-1);(i-1)-j<q;j--){                  //这个for记录0到i-1，即起点数
			    			  
			    			  shouhang +=arr[j];		//赋值不同q下的首行
			    		  }
			    		  
			    	String sqlselect="select count(*) from "+"sjc"+stringq+" where shouhang='"+shouhang+"'"+"and jieguo="+"'"+arr[i]+"'";
			    	
			    	try { 
			    		ResultSet rs2 = stmt.executeQuery(sqlselect); 
			    		int count = 0; 
			    		while (rs2.next()) { 
			    		count = rs2.getInt(1); 
			    		} 
			    		if(count == 0) { 
			    		//没同样的记录,该干什么干什么 
			    			pre=conn.prepareStatement("insert into sjc"+stringq+"(shouhang,jieguo,shenglv) values (?,?,?)");
			       		    pre.setString(1, shouhang);
			       		    pre.setString(2, arr[i]);
			       		    pre.setInt(3, 0);
			       		    pre.executeUpdate();	
			    		} else { 
			    		//有同样的记录,该干什么干什么 
			    		//首先读取胜率的数据，然后进行updatae修改
			    			
			    		String sql4="UPDATE "+"sjc"+stringq+" SET shenglv =shenglv+1 WHERE shouhang='"+shouhang+"'"+"and jieguo="+"'"+arr[i]+"'";
			    		stmt.executeUpdate(sql4);
			    		} 
			    	} catch (SQLException e) { 
			    	e.printStackTrace(); 
			    	}	
			    		  

		    	  }
		     }
		     
		     if(i>8){
		    	 for(int q=8;q>0;q--){ //应该计入q(i)个数进首行,q<256
		    		  String stringq=String.valueOf(q);

				      String shouhang="";                  //清空首行
		    		  if(validateTableNameExist("sjc"+stringq)==false){
		    			  //如果table q 不存在,创建一个新table q（i代表前面有i个数）
		    			
		    			  String sql2="CREATE TABLE "+"sjc"+stringq
		    					  +"(shouhang TEXT,"+
		    					  "jieguo TEXT,shenglv int(5))";
		    			  stmt.executeUpdate(sql2);
		    		  }
	
		    		  


			    		  for(int j=(i-1);(i-1)-j<q;j--){                  //这个for记录0到i-1，即起点数
			    			  
			    			  shouhang +=arr[j];		//赋值不同q下的首行
			    		  }
			    		  
			    	String sqlselect="select count(*) from "+"sjc"+stringq+" where shouhang='"+shouhang+"'"+"and jieguo="+"'"+arr[i]+"'";
			    	
			    	try { 
			    		ResultSet rs2 = stmt.executeQuery(sqlselect); 
			    		int count = 0; 
			    		while (rs2.next()) { 
			    		count = rs2.getInt(1); 
			    		} 
			    		if(count == 0) { 
			    		//没同样的记录,该干什么干什么 
			    			pre=conn.prepareStatement("insert into sjc"+stringq+"(shouhang,jieguo,shenglv) values (?,?,?)");
			       		    pre.setString(1, shouhang);
			       		    pre.setString(2, arr[i]);
			       		    pre.setInt(3, 0);
			       		    pre.executeUpdate();	
			    		} else { 
			    		//有同样的记录,该干什么干什么 
			    		//首先读取胜率的数据，然后进行updatae修改
			    			
			    		String sql4="UPDATE "+"sjc"+stringq+" SET shenglv =shenglv+1 WHERE shouhang='"+shouhang+"'"+"and jieguo="+"'"+arr[i]+"'";
			    		stmt.executeUpdate(sql4);
			    		} 
			    	} catch (SQLException e) { 
			    	e.printStackTrace(); 
			    	}	
			    		  

		    	  }
		     }
		    	  	  
   	  	  
	    	  
	      int zeroshenglv=0;
		  int oneshenglv=0;
		  int twoshenglv=0;
	       //输出数组
		  
		  for(int yaoyucededata=i+1;yaoyucededata>100;yaoyucededata=yaoyucededata-2){
	     
	      
	       if(i==(yaoyucededata-1)){
		    	  for(int q=8;q>0;q--){ //应该计入q(i)个数进首行,q<256
		    		  String stringq=String.valueOf(q);

				      String shouhang="";                  //清空首行

			    		  for(int j=(i-1);(i-1)-j<q;j--){                  //这个for记录0到i-1，即起点数
			    			  
			    			  shouhang +=arr[j];		//赋值不同q下的首行
			    		  }
			    		 System.out.println(shouhang);
			    	   	//0 的胜率
				  	    String sqlselect0="select shenglv from "+"sjc"+stringq+" where (shouhang='"+shouhang+"')"
				  	    			+"and"+"(jieguo=0"+")";
				    	ResultSet rs00 = sjstmt2.executeQuery(sqlselect0);
				    		
			    		  //1 的胜率
			  	    	String sqlselect1="select shenglv from "+"sjc"+stringq+" where (shouhang='"+shouhang+"')"
			  	    			+"and"+"(jieguo=1"+")";
			    		ResultSet rs01 = sjstmt3.executeQuery(sqlselect1);
			    		
			    		//2 的胜率
			  	    	String sqlselect2="select shenglv from "+"sjc"+stringq+" where (shouhang='"+shouhang+"')"
			  	    			+"and"+"(jieguo=2"+")";
			    		ResultSet rs02 = sjstmt4.executeQuery(sqlselect2);
			    		
					      zeroshenglv=0;
						  oneshenglv=0;
						  twoshenglv=0;
			    		if(rs01.next())
			    			do{
						    
						    oneshenglv +=rs01.getInt(1);
						    System.out.println("1sheng"+oneshenglv);
			    			}while(rs01.next());
			    		if(rs00.next())
			    			do{
			    			
					    		zeroshenglv +=rs00.getInt(1);
					    		System.out.println("0sheng"+zeroshenglv);
			    			}while(rs00.next());
			    		if(rs02.next())
			    			do{
			    			
					    		twoshenglv +=rs02.getInt(1);
					    		System.out.println("2sheng"+twoshenglv);
			    			}while(rs02.next());
			    		
			    		
		    	  }	
	       }
	       
	       
	     
	       
	        NumberFormat numberFormat = NumberFormat.getInstance();  
	        
	        // 设置精确到小数点后2位  
	   
	        numberFormat.setMaximumFractionDigits(2); 
	        String result1 = numberFormat.format((float) zeroshenglv / (float) (zeroshenglv+oneshenglv+twoshenglv) * 100);
	        String result2 = numberFormat.format((float) oneshenglv / (float) (zeroshenglv+oneshenglv+twoshenglv) * 100);
	        String result3 = numberFormat.format((float) twoshenglv / (float) (zeroshenglv+oneshenglv+twoshenglv) * 100);
	       System.out.println("个位0的胜率 :"+ result1 + "%"+"   "+zeroshenglv);
	       System.out.println("个位1的胜率 :"+ result2 + "%"+"   "+oneshenglv);
	       System.out.println("个位2的胜率 :"+ result3 + "%"+"   "+twoshenglv);
	       Double  inresult1=Double.parseDouble(result1);
	       Double  inresult2=Double.parseDouble(result2);
	       Double  inresult3=Double.parseDouble(result3);
	       double panduan=0;
	       double chabi2=inresult3-inresult2;
	       double chabi1=inresult2-inresult3;
	       double chabi0=inresult1-inresult2;
	       if(inresult3>inresult2){
	    	   panduan=2;
	       }else if(inresult2>inresult3){
	    	   panduan=1;
	       }else{
	    	   panduan=0;
	       }
	       if(i==arr.length-1){
	    	   double bbb=0;
	    	   if(panduan==2){
	    		   bbb=chabi2;
	    	   }
	    	   if(panduan==1){
	    		   bbb=chabi1;
	    	   }
	    	   if(panduan==0){
	    		   bbb=chabi0;
	    	   }
	    	   int arrrr=Integer.parseInt("0");
		    	pre=conn.prepareStatement("insert into gedata"+"(panduan3,chabi3,jieguo3) values (?,?,?)");
       		    pre.setDouble(1, panduan);
       		    pre.setDouble(2, bbb);
       		    pre.setDouble(3, arrrr);
      
       		    pre.executeUpdate();
	       }else{
	    	   double bbb=0;
	    	   if(panduan==2){
	    		   bbb=chabi2;
	    	   }
	    	   if(panduan==1){
	    		   bbb=chabi1;
	    	   }
	    	   if(panduan==0){
	    		   bbb=chabi0;
	    	   }
	    	   int arrrr=Integer.parseInt(arr[i+1]);
	    	   int arrrrr=Integer.parseInt(arr[i]);
	    	   int pppppppp=0;
	    	   if(arrrr==0&&panduan==1||arrrr==1&&panduan==1||arrrr==1&&panduan==2){
	    		   arrrr=1;
	    	   }else if(arrrr==0&&panduan==2||arrrr==2&&panduan==2||arrrr==2&&panduan==1){
	    		   arrrr=2;
	    	   }
	    	pre=conn.prepareStatement("insert into gedata"+"(panduan3,chabi3,jieguo3) values (?,?,?)");
	    	pre.setDouble(1, panduan);
   		    pre.setDouble(2, bbb);
   		    pre.setDouble(3, arrrr);
   		    pre.executeUpdate();
	       }

		   pre2 = conn.prepareStatement("delete from gedata where panduan3='0'");
		   pre2.executeUpdate();
	       break;
	       
		 }
		     
		     
		     
		     
		       System.out.println(arr[i]+"   gewei:  "+i);
		     }
		       

		   }
		   
		  } catch (Exception e) {
		   // TODO Auto-generated catch block   
		   e.printStackTrace();
		  }
		}


	
	public void test(Goddess g)throws Exception{
		   Connection conn = DBUtil.getConnection();//建立connection
		   Statement stmt = conn.createStatement();
		   Statement stmt2 = conn.createStatement();
		   PreparedStatement pre=null;
		   PreparedStatement pre2=null;
//		   String stringq=String.valueOf(1);
//		   String shouhang="0";
//		   
//		   String sql2="CREATE TABLE "+"a"+stringq
//					  +"(shouhang TEXT,"+
//					  "jieguo TEXT,shenglv int(5))";
//			  stmt.executeUpdate(sql2);
		   
//		   String[] arr=new String[2];
//		   arr[1]="1";
////			pre=conn.prepareStatement("insert into a"+stringq+"(shouhang,jieguo,shenglv) values (?,?,?)");
////   		    pre.setString(1, shouhang);
////   		    pre.setString(2, arr[1]);
////   		    pre.setInt(3, 0);
////   		    pre.executeUpdate();
////   		String sql4="UPDATE "+"a"+stringq+" SET shenglv =shenglv+1 WHERE shouhang='"+shouhang+"'"+"and jieguo="+"'"+arr[1]+"'";
////   		stmt.executeUpdate(sql4);
//	    	String sqlselect="select shenglv from "+"a"+stringq+" where shouhang='"+shouhang+"'";
//    		ResultSet rs2 = stmt.executeQuery(sqlselect);
//    		if(rs2.next())
//    		System.out.println(rs2.getString(1));
		   String sql1 = "insert into shuru(panduan1,chabi1,panduan2,chabi2,panduan3,chabi3,jieguo1,jieguo2,jieguo3,shurudata)"+
				   "select baidata.panduan1, baidata.chabi1, shidata.panduan2, shidata.chabi2, gedata.panduan3, gedata.chabi3,baidata.jieguo1,shidata.jieguo2,gedata.jieguo3,shidata.data"+

" from baidata,shidata,gedata "+
" where baidata.data = shidata.data and baidata.data = gedata.data "
		   		;

		   System.out.println(sql1);

		   pre=conn.prepareStatement(sql1);

		   pre.execute();

		   
	}
}

