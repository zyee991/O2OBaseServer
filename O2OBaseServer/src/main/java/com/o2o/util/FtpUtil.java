package com.o2o.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.Properties;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

public class FtpUtil {
	// ftp服务器地址
	public static String hostname;
	// ftp服务器端口号默认为21
	public static Integer port;
	// ftp登录账号
	public static String username;
	// ftp登录密码
	public static String password;
	// 远程文件地址
	public static String remoteUrl;

	public FTPClient ftpClient = null;

	static {
		Properties properties = new Properties();
		try {
			properties.load(FtpUtil.class.getClassLoader().getResourceAsStream("ftpconfig.properties"));
			hostname = properties.getProperty("host");
			port = Integer.valueOf(properties.getProperty("port"));
			username = properties.getProperty("username");
			password = properties.getProperty("password");
			remoteUrl = properties.getProperty("remoteUrl");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 初始化ftp服务器
	 * @throws IOException 
	 */
	public void initFtpClient() throws IOException {
		ftpClient = new FTPClient();
		ftpClient.setControlEncoding("utf-8");
		try {
			System.out.println("connecting...ftp服务器:" + FtpUtil.hostname + ":" + FtpUtil.port);
			ftpClient.connect(hostname, port); // 连接ftp服务器
			ftpClient.login(username, password); // 登录ftp服务器
			int replyCode = ftpClient.getReplyCode(); // 是否成功登录服务器
			if (!FTPReply.isPositiveCompletion(replyCode)) {
				System.out.println("connect failed...ftp服务器:" + FtpUtil.hostname + ":" + FtpUtil.port);
			}
			System.out.println("connect successful...ftp服务器:" + FtpUtil.hostname + ":" + FtpUtil.port);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 上传文件
	 * 
	 * @param pathname
	 *            ftp服务保存地址
	 * @param fileName
	 *            上传到ftp的文件名
	 * @param inputStream
	 *            输入文件流
	 * @return
	 */
	public String uploadFile(String fileName, InputStream inputStream) {
		try {
			System.out.println("开始上传文件");
			initFtpClient();
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
			ftpClient.enterLocalPassiveMode();
			if(ftpClient.storeFile(fileName, inputStream)){
				System.out.println("上传文件成功");
			} else {
				System.out.println("上传文件失败");
			}
			inputStream.close();
			ftpClient.logout();
		} catch (Exception e) {
			System.out.println("上传文件失败");
			e.printStackTrace();
		} finally {
			if (ftpClient.isConnected()) {
				try {
					ftpClient.disconnect();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (null != inputStream) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		String result = new StringBuilder().append(remoteUrl).append("/").append(fileName).toString();
		return result;
	}

	// 改变目录路径
	public boolean changeWorkingDirectory(String directory) {
		boolean flag = true;
		try {
			flag = ftpClient.changeWorkingDirectory(directory);
			if (flag) {
				System.out.println("进入文件夹" + directory + " 成功！");

			} else {
				System.out.println("进入文件夹" + directory + " 失败！开始创建文件夹");
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return flag;
	}

	// 创建多层目录文件，如果有ftp服务器已存在该文件，则不创建，如果无，则创建
	public boolean CreateDirecroty(String remote) throws IOException {
		boolean success = true;
		String directory = remote + "/";
		// 如果远程目录不存在，则递归创建远程服务器目录
		if (!directory.equalsIgnoreCase("/") && !changeWorkingDirectory(new String(directory))) {
			int start = 0;
			int end = 0;
			if (directory.startsWith("/")) {
				start = 1;
			} else {
				start = 0;
			}
			end = directory.indexOf("/", start);
			String path = "";
			String paths = "";
			while (true) {
				String subDirectory = new String(remote.substring(start, end).getBytes("GBK"), "iso-8859-1");
				path = path + "/" + subDirectory;
				if (!existFile(path)) {
					if (makeDirectory(subDirectory)) {
						changeWorkingDirectory(subDirectory);
					} else {
						System.out.println("创建目录[" + subDirectory + "]失败");
						changeWorkingDirectory(subDirectory);
					}
				} else {
					changeWorkingDirectory(subDirectory);
				}

				paths = paths + "/" + subDirectory;
				start = end + 1;
				end = directory.indexOf("/", start);
				// 检查所有目录是否创建完毕
				if (end <= start) {
					break;
				}
			}
		}
		return success;
	}

	// 判断ftp服务器文件是否存在
	public boolean existFile(String path) throws IOException {
		boolean flag = false;
		FTPFile[] ftpFileArr = ftpClient.listFiles(path);
		if (ftpFileArr.length > 0) {
			flag = true;
		}
		return flag;
	}

	// 创建目录
	public boolean makeDirectory(String dir) {
		boolean flag = true;
		try {
			flag = ftpClient.makeDirectory(dir);
			if (flag) {
				System.out.println("创建文件夹" + dir + " 成功！");

			} else {
				System.out.println("创建文件夹" + dir + " 失败！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
}