package com.hik.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class FileUtil {

	/**
	 * 移动文件
	 * @param startPath
	 * @param endPath
	 */
	public void moveFile(String startPath, String endPath) {
		File sfile = new File(startPath);
		File efile = new File(endPath + sfile.getName());
		sfile.renameTo(efile);
	}
	
	/**
	 * 执行 bat 脚本
	 * @param strcmd
	 */
	public void runCmd(String strcmd) {
		Runtime rt = Runtime.getRuntime();
		Process ps = null;
		try {
			ps = rt.exec(strcmd);
			InputStream in = ps.getInputStream();
			while ((in.read()) != -1) {
//          	System.out.print(c);
            }
            in.close();
			ps.waitFor();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (InterruptedException e2) {
			e2.printStackTrace();
		}
		ps.exitValue();
	}
	
	public static void main(String[] args) {
		FileUtil fileUtil = new FileUtil();
		fileUtil.moveFile("d:\\demo-test.war", "d:\\tomcat\\webapps\\");
		fileUtil.runCmd("D:\\tomcat\\bin\\startup.bat");
	}
	
}
