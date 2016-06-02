package com.jack.common.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.TimeZone;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import com.jack.common.StringUtil;
import com.jack.common.SystemConfigure;

/**
 * FTP上传工具
 * 
 */
public class FtpFile {
	private static final Log log = LogFactory.getLog(FtpFile.class);
	// FTP客户端
	private FTPClient ftpClient;
	// 服务地址
	public static String ip;
	// 端口
	public static int port = 21;
	// 用户名
	public static String user;
	// 密码
	public static String password;
	// 工作目录
	public static String fileDir;

	public void init(){
		this.init(null,21,null,null,null);
	}
	/**
	 * 初始化读取配置文件中的设置
	 */
	public void init(String ip, int port, String user,String password,String fileDir) {
		if (ip == null) {
			ip = SystemConfigure.getValue("ftpHost");
		}
		if (port < 0) {
			String ftpPort = SystemConfigure.getValue("ftpPort");
			port = StringUtil.isNotBlank(ftpPort) ? Integer.valueOf(ftpPort)
					: 21;
		}
		if (user == null) {
			user = SystemConfigure.getValue("ftpUserName");
		}
		if (password == null) {
			password = SystemConfigure.getValue("ftpPassword");
		}
		if (fileDir == null) {
			fileDir = SystemConfigure.getValue("resfileDir");
		}
	}

	/**
	 * 服务器连接
	 * 
	 * @param ip
	 *            服务器IP
	 * @param port
	 *            服务器端口
	 * @param user
	 *            用户名
	 * @param password
	 *            密码
	 * @param path
	 *            服务器路径
	 */
	public boolean connectServer() {
		boolean isLogin = false;
		ftpClient = new FTPClient();
		FTPClientConfig ftpClientConfig = new FTPClientConfig();
		ftpClientConfig.setServerTimeZoneId(TimeZone.getDefault().getID());
		this.ftpClient.setControlEncoding("GBK");
		this.ftpClient.configure(ftpClientConfig);
		try {
			this.ftpClient.connect(ip, port);
			int reply = this.ftpClient.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				this.ftpClient.disconnect();
				log.error("登录FTP服务失败！");
				return isLogin;
			}
			this.ftpClient.login(user, password);
			// 设置传输协议
			this.ftpClient.enterLocalPassiveMode();
			this.ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
			log.info("[" + user + "] 成功登陆FTP服务器");
			isLogin = true;

			if (fileDir.length() != 0) {
				// 把远程系统上的目录切换到参数path所指定的目录
				this.ftpClient.changeWorkingDirectory(fileDir);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("[" + user + "] 登录FTP服务失败！" + e.getMessage());
		}
		this.ftpClient.setBufferSize(1024 * 2);
		this.ftpClient.setDataTimeout(30 * 1000);
		return isLogin;
	}

	/**
	 * 上传Ftp文件
	 * 
	 * @param localFile
	 *            本地文件
	 * @param remoteFile
	 *            远程文件 上传服务器路径 - 应该以/结束
	 */
	public boolean upload(String localFile, String remoteFile){
		BufferedInputStream inStream = null;
		boolean success = false;
		try {
			File file = new File(localFile);
			this.ftpClient.changeWorkingDirectory(remoteFile);// 改变工作路径
			inStream = new BufferedInputStream(new FileInputStream(file));
			log.info(file.getName() + "开始上传.....");
			success = this.ftpClient.storeFile(file.getName(), inStream);
			if (success == true) {
				log.info(file.getName() + "上传成功");
				return success;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			log.error(localFile + "未找到");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (inStream != null) {
				try {
					inStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return success;
	}
	
	/**
	 * 上传Ftp文件
	 * 
	 * @param localFile
	 *            本地文件
	 * @param remoteFile
	 *            远程文件 上传服务器路径 - 应该以/结束
	 */
	public boolean upload(String dir, String fileName, InputStream input){
		BufferedInputStream inStream = null;
		boolean success = false;
		try {
			this.ftpClient.changeWorkingDirectory(dir);// 改变工作路径
			inStream = new BufferedInputStream(input);
			log.info(fileName + "开始上传.....");
			success = this.ftpClient.storeFile(fileName, inStream);
			if (success == true) {
				log.info(fileName + "上传成功");
				return success;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			log.error(e);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (inStream != null) {
				try {
					inStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return success;
	}
	

	/***
	 * 下载文件
	 * 
	 * @param remoteFile
	 *            远程文件
	 * @param localFile
	 *            本地文件
	 * */

	public boolean download(String remoteFile, String localFile) {
		BufferedOutputStream outStream = null;
		boolean success = false;
		try {
			this.ftpClient.changeWorkingDirectory(remoteFile);
			outStream = new BufferedOutputStream(
					new FileOutputStream(localFile));
			log.info(remoteFile + "开始下载....");
			success = this.ftpClient.retrieveFile(remoteFile, outStream);
			if (success == true) {
				log.info(remoteFile + "成功下载到" + localFile);
				return success;
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(remoteFile + "下载失败");
		} finally {
			if (null != outStream) {
				try {
					outStream.flush();
					outStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		if (success == false) {
			log.error(remoteFile + "下载失败!!!");
		}
		return success;
	}

	/**
	 * 文件下载
	 * 
	 * @param remoteFile
	 *            远程文件
	 * @param localFile
	 *            本地文件
	 */
	public ByteArrayOutputStream downloadFileStream(String remoteFile,
			String localFile) {
		InputStream is = null;
		FileOutputStream os = null;
		ByteArrayOutputStream bytestream = null;
		try {
			try {
				// 获取远程机器上的文件filename，借助TelnetInputStream把该文件传送到本地
				this.ftpClient.changeWorkingDirectory(remoteFile);
				log.info(remoteFile + "开始下载....");
				is = this.ftpClient.retrieveFileStream(remoteFile);
			} catch (Exception e) {
				e.printStackTrace();
				log.error(remoteFile + "下载失败");
			}
			os = new FileOutputStream(new File(localFile));
			byte[] bytes = new byte[1024];
			int c;
			if (os != null) {
				bytestream = new ByteArrayOutputStream();
				while ((c = is.read(bytes)) != -1) {
					os.write(bytes, 0, c);
					bytestream.write(c);
				}
				bytestream.close();
			}
			log.error(remoteFile + "下载成功");
		} catch (IOException ex) {
			log.error(remoteFile + "下载失败");
			ex.printStackTrace();
			throw new RuntimeException(ex);
		} finally {
			try {
				if (is != null) {
					is.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (os != null) {
						os.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return bytestream;
	}

	/***
	 * @上传文件夹
	 * @param localDirectory
	 *            当地文件夹
	 * @param remoteDirectoryPath
	 *            Ftp 服务器路径 以目录"/"结束
	 * */
	public boolean uploadDirectory(String localDirectory,
			String remoteDirectoryPath) {
		File src = new File(localDirectory);
		try {
			remoteDirectoryPath = remoteDirectoryPath + src.getName() + "/";
			this.ftpClient.makeDirectory(remoteDirectoryPath);
			// ftpClient.listDirectories();
		} catch (IOException e) {
			e.printStackTrace();
			log.info(remoteDirectoryPath + "目录创建失败");
		}
		File[] allFile = src.listFiles();
		for (int currentFile = 0; currentFile < allFile.length; currentFile++) {
			if (!allFile[currentFile].isDirectory()) {
				String localFile = allFile[currentFile].getPath().toString();
				upload(localFile, remoteDirectoryPath);
			}
		}
		for (int currentFile = 0; currentFile < allFile.length; currentFile++) {
			if (allFile[currentFile].isDirectory()) {
				// 递归
				uploadDirectory(allFile[currentFile].getPath().toString(),
						remoteDirectoryPath);
			}
		}
		return true;
	}

	/***
	 * @下载文件夹
	 * @param localDirectoryPath本地地址
	 * @param remoteDirectory
	 *            远程文件夹
	 * */
	public boolean downLoadDirectory(String localDirectoryPath,
			String remoteDirectory) {
		try {
			String fileName = new File(remoteDirectory).getName();
			localDirectoryPath = localDirectoryPath + fileName + "//";
			new File(localDirectoryPath).mkdirs();
			FTPFile[] allFile = this.ftpClient.listFiles(remoteDirectory);
			for (int currentFile = 0; currentFile < allFile.length; currentFile++) {
				if (!allFile[currentFile].isDirectory()) {
					String strFilePath = remoteDirectory
							+ allFile[currentFile].getName();
					download(strFilePath, localDirectoryPath);
				}
			}
			for (int currentFile = 0; currentFile < allFile.length; currentFile++) {
				if (allFile[currentFile].isDirectory()) {
					String strremoteDirectoryPath = remoteDirectory + "/"
							+ allFile[currentFile].getName();
					downLoadDirectory(localDirectoryPath,
							strremoteDirectoryPath);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			log.info("下载文件夹失败");
			return false;
		}
		return true;
	}

	/**
	 * 关闭连接
	 */
	public void closeConnect() {
		if (null != this.ftpClient && this.ftpClient.isConnected()) {
			try {
				boolean reuslt = this.ftpClient.logout();// 退出FTP服务器
				if (reuslt) {
					log.info("成功退出服务器");
				}
			} catch (IOException e) {
				e.printStackTrace();
				log.warn("退出FTP服务器异常！" + e.getMessage());
			} finally {
				try {
					this.ftpClient.disconnect();// 关闭FTP服务器的连接
				} catch (IOException e) {
					e.printStackTrace();
					log.warn("关闭FTP服务器的连接异常！");
				}
			}
		}
	}

	public static void main(String agrs[]) {
		FtpFile fu = new FtpFile();
		fu.init();
		fu.connectServer();
		String localfile = "D:\\ReadMe.htm";
		String remotefile = "ReadMe.htm";
		fu.upload(localfile, remotefile);
		fu.closeConnect();
	}
}
