package socketGet;
//package cn.ysh.studio.crawler.httpclient;  

import org.apache.http.client.HttpClient;  
import org.apache.http.client.ResponseHandler;  
import org.apache.http.client.methods.HttpGet;  
import org.apache.http.impl.client.BasicResponseHandler;  
//import org.apache.http.impl.client.DefaultHttpClient; 
import org.apache.http.impl.client.HttpClientBuilder;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.io.InputStreamReader;  
import java.net.MalformedURLException;  
import java.net.URL;  
import java.util.ArrayList;  
import java.util.List;  
import java.util.regex.Matcher;  
import java.util.regex.Pattern;

//import javax.swing.text.Document;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements; 

	/** 
	 * 基于HtmlClient抓取网页内容 
	 * 
	 * @author www.yshjava.cn 
	 */   
	class GetPage{
		public void getPage(String pageurl) throws Exception {  
	        //目标页面     
	        String url=pageurl;
	        //创建一个默认的HttpClient  
	        HttpClient httpclient = HttpClientBuilder.create().build();  
	        try {  
	            //以get方式请求网页http://www.yshjava.cn  
	            HttpGet httpget = new HttpGet(url);  
	            //打印请求地址  
	            System.out.println("executing request " + httpget.getURI());  
	            //创建响应处理器处理服务器响应内容  
	            ResponseHandler responseHandler = new BasicResponseHandler();  
	            System.out.println("--");  
	            //执行请求并获取结果  
	            String responseBody = (String) httpclient.execute(httpget, responseHandler);  
	            System.out.println("----------------------------------------");  
	            System.out.println(responseBody);  
	            
	            Document doc = Jsoup.parse(responseBody);
	            Elements links = doc.select("a");//.first();
	            //String relHref = link.attr("href"); // == "/"
	            System.out.println("-********************-");
	            for (Element link : links) { 
	            	  String linkHref = link.attr("href"); 
	            	  String linkText = link.text(); 
	            	  System.out.println("-**TTTTTTTTT*****-");
	            	  System.out.println(linkHref); 
	            	  System.out.println("-**PPPPPPPPP*****-");
	            	  System.out.println(linkText); 
	            }
	            
	             
	            String fileName="chuihei1.html";
	            try
	            {
	            	//使用这个构造函数时，如果存在kuka.txt文件，
	            	//则直接往kuka.txt中追加字符串
	            	FileWriter writer=new FileWriter(fileName,true);
	            	SimpleDateFormat format=new SimpleDateFormat();
	            	String time=format.format(new Date());
	            	writer.write("\n\t"+responseBody);
	            	writer.close();
	            } catch (IOException e)
	            	{
	            		e.printStackTrace();
	            }
	            
	            System.out.println("----------------------------------------");  
	        } finally {  
	            //关闭连接管理器  
	           // httpclient.getConnectionManager().shutdown();  
	        }  
	  
		}
	}
	public class SocketGetPage {  
	  
	    public static void main(String[] args)  {  
	    	GetPage a = new GetPage();
	    	String url = "http://tieba.baidu.com/f?kw=%E9%94%A4%E9%BB%91";  
	    	try{a.getPage(url);
	    	}catch(Exception e)
	    	{
	    		System.out.println("Exception");  
	    	}
	  
	    }  
	}  

