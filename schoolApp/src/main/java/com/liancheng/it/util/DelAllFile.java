package com.liancheng.it.util;

import java.io.File;

/**
 * java删除�?有文件和文件�?
 * 创建人：FH Q313596790
 * 创建时间�?2015�?1�?12�?
 * @version
 */
public class DelAllFile {
	
	public static void main(String args[]) {
		deleteFile("D:/eclipse/workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp1/wtpwebapps/Yamm/ueditor/upload/logo/1478088098084-554765465");
//		delFolder("e:/e/a");			//只删除e下面a及a下面�?有文件和文件�?,e不会被删�?
		//delFolder("D:/WEBSerser/apache-tomcat-8.0.15/me-webapps/UIMYSQL/WEB-INF/classes/../../admin00/ftl/code");	
		//delFolder("D:\\WEBSerser\\apache-tomcat-8.0.15\\me-webapps\\UIMYSQL\\admin00\\ftl\\code");
		//delFolder("D:/WEBSerser/apache-tomcat-8.0.15/me-webapps/UIMYSQL/WEB-INF/classes/../../admin00/ftl/code");
		System.out.println("deleted");
	}
	
	public static boolean deleteFile(String sPath) {  
		boolean flag = false;  
	    File file = new File(sPath);  
	    // 路径为文件且不为空则进行删除  
	    if (file.isFile() && file.exists()) {  
	        file.delete();  
	        flag = true;  
	    }  
	    return flag;  
	}  
	/**
	 * @param folderPath 文件路径 (只删除此路径的最末路径下�?有文件和文件�?)
	 */
	public static void delFolder(String folderPath) {
		try {
			delAllFile(folderPath); 	// 删除完里面所有内�?
			String filePath = folderPath;
			filePath = filePath.toString();
			java.io.File myFilePath = new java.io.File(filePath);
			myFilePath.delete(); 		// 删除空文件夹
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 删除指定文件夹下�?有文�?
	 * @param path 文件夹完整绝对路�?
	 */
	public static boolean delAllFile(String path) {
		boolean flag = false;
		File file = new File(path);
		if (!file.exists()) {
			return flag;
		}
		if (!file.isDirectory()) {
			return flag;
		}
		String[] tempList = file.list();
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
			if (path.endsWith(File.separator)) {
				temp = new File(path + tempList[i]);
			} else {
				temp = new File(path + File.separator + tempList[i]);
			}
			if (temp.isFile()) {
				temp.delete();
			}
			if (temp.isDirectory()) {
				delAllFile(path + "/" + tempList[i]);	// 先删除文件夹里面的文�?
				delFolder(path + "/" + tempList[i]);	// 再删除空文件�?
				flag = true;
			}
		}
		return flag;
	}
}
