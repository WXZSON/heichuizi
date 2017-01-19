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

import org.jsoup.helper.Validate;

	/** 
	 * 基于HtmlClient抓取网页内容 
	 * 
	 * @author www.yshjava.cn 
	 */   
	class GetPage{
		public String getPage(String pageurl) throws Exception {  
	        //目标页面     
	        String url=pageurl;
	        String responseBody;
	        System.out.println("grap request " +  pageurl);  
	        String pattern = "/p/\\d+";
			// 创建 Pattern 对象
		    Pattern r = Pattern.compile(pattern);
		    Matcher m = r.matcher(pageurl);
		    String fileName="C:\\Users\\ruizhewu\\heichuizi\\";
		    if (m.find( )) {
		    	String pattern1 = "(\\d)+";
		    	Pattern r1 = Pattern.compile(pattern1);
			    Matcher m1 = r1.matcher(pageurl);
			    System.out.println("pageurl = "+pageurl);
			    if(m1.find()){
			    	System.out.println("GROUP="+m1.group());
			    	fileName=fileName+m1.group();
			    	System.out.println("pageurlfileName= = "+fileName);
			    }
			    else{
			    	System.out.println("NO post item found");
			    }
		    }else{
		    	fileName=fileName+"MainPage";
		    }
		    	
	        //创建一个默认的HttpClient  
	        HttpClient httpclient = HttpClientBuilder.create().build();  
	        try {  
	            //以get方式请求网页http://www.yshjava.cn  
	            HttpGet httpget = new HttpGet(url);  
	            //打印请求地址  
	            System.out.println("executing request " + httpget.getURI());  
	            //创建响应处理器处理服务器响应内容  
	            ResponseHandler responseHandler = new BasicResponseHandler();  
	            //执行请求并获取结果  
	            responseBody = (String) httpclient.execute(httpget, responseHandler);   
	            //System.out.println(responseBody);  
	            try
	            {
	            	//使用这个构造函数时，如果存在kuka.txt文件，
	            	//则直接往kuka.txt中追加字符串
	            	SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
	            	String time=format.format(new Date());
	            	fileName=fileName+"###"+time;
	            	FileWriter writer=new FileWriter(fileName);
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
	        return responseBody;
		}
	}
	class ParsePage{
		public void parsePage(String responseBody, List<String> postName){
			
			String pattern = "/p/\\d+";
			// 创建 Pattern 对象
		    Pattern r = Pattern.compile(pattern);
			final String linkBeginne="javascript:;";
			
			Document doc = Jsoup.parse(responseBody);
            Elements links =doc.select("a[href]"); //doc.select("a");//.first();
            Elements fool =doc.getElementsByTag("body"); //doc.select("a");//.first();
            //String relHref = link.attr("href"); // == "/"
            //System.out.println("-********************-");
            Boolean flag_link=false;
            Boolean flag_link2=false;
            for (Element link : links) { 
            	  String linkHref = link.attr("href");
            	  String linkText = link.text();
            	  if(linkText.equals("群组"))
            		  flag_link=true;
            	  
            	  if(flag_link2){
            		  Matcher m = r.matcher(link.toString());
            	      if (m.find( )) {
            	         System.out.println("Found value: " + m.group(0) );
            	         postName.add(m.group(0));
            	      } else {
            	         //System.out.println("NO MATCH");
            	     }

            	  }
            	  if(flag_link && linkHref.equals(linkBeginne))
            		  flag_link2=true;
            }
            
		}
	}
	public class SocketGetPage {  
	  
	    public static void main(String[] args)  {  
	    	GetPage a = new GetPage();
	    	ParsePage b=new ParsePage();
	    	PostList c=new PostList();
	    	Extract ex=new Extract();
	    	String tmp="";
	    	String url = "http://tieba.baidu.com/f?kw=%E9%94%A4%E9%BB%91";
	    	String urltieba="http://tieba.baidu.com";
	    	try{
	    		tmp=a.getPage(url);
	    	}catch(Exception e)
	    	{
	    		System.out.println("Exception");  
	    	}
	    	b.parsePage(tmp, c.postName);
	    	for(String postPage:c.postName)
			{	
	    		try{
		    		a.getPage(urltieba+postPage);
		    		ex.extractPage("test.html");
		    	}catch(Exception e)
		    	{
		    		e.printStackTrace();
		    	}
			}
	    	System.out.println("END"); 
	    }  
	}  

