package tools2;



//��ʵ������������һ����������Ȼ����javaȥ���������������Ȼ����������ܻ�ȡ���������ip��ֻ��Ҫ�����ݽ��н����������������Լ������������Ҳ�кܶ��ṩ�����������վ������ֵĲ�ip����վ������ʾ�Լ�����ip�Ĺ��ܡ��±��Ǹ����ӣ�����ֱ������


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class GetIP {

/**
* @param args
*/
//public static void main(String[] args) {
//// TODO Auto-generated method stub  
//System.out.println("����������IP�ǣ�"+GetIP.getWebIp("http://city.ip138.com/city0.asp"));
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
		  System.err.println("��������ʧ��");
		  return "error open url:" + strUrl;
	
	  }
	}
} 
