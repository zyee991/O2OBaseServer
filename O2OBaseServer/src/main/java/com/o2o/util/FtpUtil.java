package com.o2o.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

/**
 * ftp上传下载工具类
 * <p>Title: FtpUtil</p>
 * <p>Description: </p>
 * <p>Company:</p> 
 * @author    
 * @date 
 * @version 1.0
 */
public class FtpUtil {

    /** 
     * Description: 向FTP服务器上传文件 
     * @param host FTP服务器hostname 
     * @param port FTP服务器端口 
     * @param username FTP登录账号 
     * @param password FTP登录密码 
     * @param basePath FTP服务器基础目录
     * @param filePath FTP服务器文件存放路径。例如分日期存放：/2015/01/01。文件的路径为basePath+filePath
     * @param filename 上传到FTP服务器上的文件名 
     * @param input 输入流 
     * @return 成功返回true，否则返回false 
     */  
    public static String uploadFile( String filePath, String filename, InputStream input) {
        boolean result = false;
        String host="";
        int port=0;
        String username="";
        String password="";
        String basePath="";
        Properties ps=new Properties();
        try {
			FileInputStream is=new FileInputStream("src/main/resources/ftpconfig.properties");
			ps.load(is);
			host=ps.getProperty("host");
			port=Integer.parseInt(ps.getProperty("port"));
			username=ps.getProperty("username");
			password=ps.getProperty("password");
			basePath=ps.getProperty("basePath");
		} catch (Exception e1) {
		
			e1.printStackTrace();
		}
        FTPClient ftp = new FTPClient();
        try {
            int reply;
            ftp.connect(host, port);// 连接FTP服务器
            // 如果采用默认端口，可以使用ftp.connect(host)的方式直接连接FTP服务器
            ftp.login(username, password);// 登录
            reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                //return result;
                return "";
            }
            //切换到上传目录
            if (!ftp.changeWorkingDirectory(basePath+filePath)) {
                //如果目录不存在创建目录
                String[] dirs = filePath.split("/");
                String tempPath = basePath;
                for (String dir : dirs) {
                    if (null == dir || "".equals(dir)) continue;
                    tempPath += "/" + dir;
                    if (!ftp.changeWorkingDirectory(tempPath)) {
                        if (!ftp.makeDirectory(tempPath)) {
                        	System.out.println(ftp.makeDirectory(tempPath)+"-------ftp.makeDirectory(tempPath)");
                            return tempPath;
                        } else {
                            ftp.changeWorkingDirectory(tempPath);
                        }
                    }
                }
            }
            //设置上传文件的类型为二进制类型
            ftp.setFileType(FTP.BINARY_FILE_TYPE);
            //上传文件
            if (!ftp.storeFile(filename, input)) {
            	System.out.println(ftp.storeFile(filename, input)+"----------ftp.storeFile(filename, input)");
                return "";
            }
            input.close();
            ftp.logout();
            result = true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException ioe) {
                }
            }
        }
        return filePath+filename;
    }
    
    public static void main(String[] args) {
        try {  
            FileInputStream in=new FileInputStream(new File("D:\\logs\\debug.log"));  
            String result= uploadFile("/2015/01/21", "debug.log", in);  
            System.out.println(result+"!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        }  
    }
}