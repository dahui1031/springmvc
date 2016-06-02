package com.jack.common.file;

import java.io.InputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.LogFactoryImpl;

import com.jack.common.SystemConfigure;

public class FileUp {
	private static final Log log = LogFactoryImpl.getLog(FileUp.class);
	
	private String dir;
	
	private String fileName;
	
	private InputStream inputStream;

	public static int FILE_SIZE = 101920;

	public FileUp(String dir,String fileName,InputStream input){
		this.dir = dir;
		this.fileName = fileName;
		this.inputStream = input;
	}
	
	/**
	 * 上传附件（文件上传路径由系统默认为: upload/账号/时间）
	 * @return
	 * @throws Exception
	 */
	public void upload() throws Exception{
		String flag = "http";
		try {
			flag = SystemConfigure.getValue("ftpOrHttp");
		} catch (Exception e) {
			flag = "http";
		}
		if (flag.equals("ftp")){
			log.info("开始ftp上传......");
			FtpFile ftpFile =new FtpFile();
			ftpFile.init();
			ftpFile.connectServer();
			ftpFile.upload(dir, fileName, inputStream);
			ftpFile.closeConnect();
		}else{ 
			log.info("开始http上传......");
			HttpFile httpFile =new HttpFile();
			httpFile.init();
			httpFile.uploadFile(dir, fileName, inputStream);
		}
	}
}