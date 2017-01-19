package socketGet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Extract {
	public String extractPage(String filename){
		String pattern = "/p/\\d+";
		// åˆ›å»º Pattern å¯¹è±¡
		Pattern r = Pattern.compile(pattern);
		//Matcher m = r.matcher(pageurl);
		String fileName="C:\\Users\\ruizhewu\\heichuizi\\";
		filename=fileName+filename;
		File file = new File(filename);
		String tempString = null;
		BufferedReader reader = null;
		String content=null;
		try {
			System.out.println("*********************");
			reader = new BufferedReader(new FileReader(file));
			int line = 1;
			// ä¸€æ¬¡è¯»å…¥ä¸€è¡Œï¼Œç›´åˆ°è¯»å…¥nullä¸ºæ–‡ä»¶ç»“æ�Ÿ
			while ((tempString = reader.readLine()) != null) {
				// æ˜¾ç¤ºè¡Œå�·
				content=content+"\n"+tempString;
				//System.out.println("line " + line + ": " + tempString);
				line++;
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
		String c1=getNoHTMLString(content,10000);
		System.out.println(c1);
		return c1;
	}
	
	public static String getNoHTMLString(String content,int p){

		if(null==content) return "";
		if(0==p) return "";

		java.util.regex.Pattern p_script; 
		java.util.regex.Matcher m_script; 
		java.util.regex.Pattern p_style; 
		java.util.regex.Matcher m_style; 
		java.util.regex.Pattern p_html; 
		java.util.regex.Matcher m_html; 

		try { 
		String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>";
		//定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script> } 
		String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; 
		//定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style> } 
		String regEx_html = "<[^>]+>"; //定义HTML标签的正则表达式 

		p_script = Pattern.compile(regEx_script,Pattern.CASE_INSENSITIVE); 
		m_script = p_script.matcher(content); 
		content = m_script.replaceAll(""); //过滤script标签
		p_style = Pattern.compile(regEx_style,Pattern.CASE_INSENSITIVE); 
		m_style = p_style.matcher(content); 
		content = m_style.replaceAll(""); //过滤style标签 

		p_html = Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE); 
		m_html = p_html.matcher(content); 

		content = m_html.replaceAll(""); //过滤html标签 
		}catch(Exception e) { 
		return "";
		} 

		if(content.length()>p){
		content = content.substring(0, p)+"...";
		}else{
		content = content + "...";
		}
		return content;
		}
}
