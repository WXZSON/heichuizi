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
		// 创建 Pattern 对象
		Pattern r = Pattern.compile(pattern);
		//Matcher m = r.matcher(pageurl);
		String fileName="C:\\Users\\ruizhewu\\heichuizi\\";
		filename=fileName+filename;
		File file = new File(filename);
		String tempString = null;
		BufferedReader reader = null;
		String content=null;
		try {
			System.out.println("以行为单位读取文件内容，一次读一整行：");
			reader = new BufferedReader(new FileReader(file));
			int line = 1;
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				// 显示行号
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
		System.out.println(content);
		return content;
	}
}
