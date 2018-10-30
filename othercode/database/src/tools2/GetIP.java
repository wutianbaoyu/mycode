package tools2;



//其实就是在外网搞一个服务器，然后用java去访问这个服务器，然后服务器就能获取到你的外网ip，只需要对数据进行解析。服务器可以自己架设或者网上也有很多提供这样服务的网站，如各种的查ip的网站都有显示自己外网ip的功能。下边是个例子，可以直接运行


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class GetIP {

/**
* @param args
*/
//public static void main(String[] args) {
//// TODO Auto-generated method stub  
//System.out.println("本机的外网IP是："+GetIP.getWebIp("http://city.ip138.com/city0.asp"));
//}

public static String getWebIp(String strUrl) {
try {
	
	   URL url = new URL(strUrl);
	
	   BufferedReader br = new BufferedReader(new InputStreamReader(url
	
	   .openStream()));
	
	   String s = "";
	
	   StringBuffer sb = new StringBuffer("");
	   
	   String webContent = "";

	   while ((s = br.readLine()) != null) {
	    sb.append(s + "\r\n");
	
	   }
	
	   br.close();
	   webContent = sb.toString();
	   int start = webContent.indexOf("[")+1;
	   int end = webContent.indexOf("]");
	   webContent = webContent.substring(start,end);
	   
	   return webContent;
	
	  } catch (Exception e) {
		  System.err.println("网络连接失败");
		  return "error open url:" + strUrl;
	
	  }
	}
} 
