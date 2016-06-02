package com.jack.common.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.jack.common.SystemConfigure;


/**
 * 基于HTTP请求上传文件
 * 
 * @author liqp
 * 
 */
public class HttpFile {
	private static final Log log = LogFactory.getLog(HttpFile.class);
	// 工作目录
	public static String resfileDir;

	// 下载文件时使用
	private HttpServletResponse response;

	public HttpFile() {

	}

	public HttpFile(HttpServletResponse response) {
		this.response = response;
	}

	public void init() {
		if (resfileDir == null) {
			resfileDir = SystemConfigure.getValue("resfileDir");
		}
	}

	/**
	 * 上传文件
	 * @param dir
	 * @param fileName
	 * @param input
	 * @return
	 * @throws Exception
	 */
	public boolean uploadFile(String dir, String fileName, InputStream input)
			throws Exception {
		try {
			if(resfileDir==null || resfileDir.equals("")){
				init();
			}
			String uploadPath= resfileDir;
			java.io.File mydir = new java.io.File(uploadPath);
			if (!mydir.exists()) {
				mydir.mkdirs();
			}
			if (dir != null && dir.indexOf(";") != -1) {
				String[] dirs = dir.split(";");
				int length = dirs.length;
				int i = 0;
				while (i < length) {
					String tmp = dirs[i];
					uploadPath = uploadPath + File.separator + tmp;
					java.io.File tempDir = new java.io.File(uploadPath);
					if (!tempDir.exists()) {
						if (!tempDir.mkdir()) {
							throw new Exception("创建文件目录出现错误：" + uploadPath);
						}
						;
					}
					i++;
				}
			} else {
				uploadPath = uploadPath + "/" + dir;
				java.io.File tempDir = new java.io.File(uploadPath);
				if (!tempDir.exists()) {
					if (!tempDir.mkdirs()) {
						throw new Exception("创建文件目录出现错误：" + uploadPath);
					}
					;
				}
			}

			java.io.BufferedInputStream bis;
			String savedFileName = "";
			bis = new BufferedInputStream(input);
			// String extendName =
			// file.getFileName().substring(file.getFileName().lastIndexOf("."));
			savedFileName = uploadPath + "/" + fileName;// + extendName;
			OutputStream bos = new FileOutputStream(savedFileName);// 建立一个上传文件的输出流
			int bytesRead = 0;
			byte[] buffer = new byte[81920];
			while ((bytesRead = bis.read(buffer, 0, 81920)) != -1) {
				bos.write(buffer, 0, bytesRead);// 将文件写入服务器
			}
			bos.close();
			bis.close();
		} catch (Exception ex) {
			throw new Exception("文件上传过程中出现错误! " + ex);
		}
		return true;
	}

	/**
	 * 删除文件
	 * 
	 * @param dir
	 * @param filePathName
	 * @throws Exception
	 */
	public boolean deleteFile(String dir, String fileName) throws Exception {
		boolean flag = false;
		if (fileName == null || "".equals(fileName)) {
			return true;
		}
		try {
			if (dir != null && dir.indexOf(";") != -1) {
				String[] dirs = dir.split(";");
				int length = dirs.length;
				int i = 0;
				while (i < length) {
					String tmp = dirs[i];
					resfileDir = resfileDir + File.separator + tmp;
					i++;
				}
			} else {
				resfileDir = resfileDir + File.separator + dir;
			}

			String path = resfileDir + File.separator + fileName;
			java.io.File file = new java.io.File(path);
			if (file.exists()) {
				flag = file.delete();
			}
		} catch (Exception e) {
			log.error("删除文件出错:fileName=" + fileName);
			throw new Exception(e);
		}
		return true;
	}

	/**
	 * 下载文件
	 * 
	 * @param dir
	 * @param fileName
	 * @return
	 * @throws Exception
	 */
	public boolean downloadToHTTP(String dir, String fileName) throws Exception {
		java.io.BufferedInputStream iin;
		BufferedOutputStream dout;
		OutputStream output;
		try {
			if (dir != null && dir.indexOf(";") != -1) {
				String[] dirs = dir.split(";");
				int length = dirs.length;
				int i = 0;
				while (i < length) {
					String tmp = dirs[i];
					resfileDir = resfileDir + File.separator + tmp;
					i++;
				}
			} else {
				resfileDir = resfileDir + File.separator + dir;
			}
			resfileDir = resfileDir + File.separator + fileName;
			File file = new File(resfileDir);
			if (!file.exists()) {
				throw new Exception("对不起! 您下载的文件不存在");
			} else {
				byte[] buffer;
				int length = (new Long(file.length())).intValue();
				buffer = new byte[length];
				try {
					iin = new BufferedInputStream(new java.io.FileInputStream(
							file));
					// 传送数据
					output = response.getOutputStream();
					dout = new BufferedOutputStream(output);
					int once = 0;
					int total = 0;
					while ((total < length) && (once >= 0)) {
						once = iin.read(buffer, total, length);
						total += once;
						dout.write(buffer, 0, length);
					}
					if (iin != null) {
						iin.close();
					}
					if (dout != null) {
						dout.close();
					}
				} catch (Exception ex) {
					throw new Exception("文件下载过程中出现错误! " + ex);
				}
			}
		} catch (Exception ex) {
			throw new Exception("文件下载过程中出现错误! " + ex);
		}
		return true;
	}

	/**
	 * 下载文件,返回文件流
	 * 
	 * @param dir
	 * @param fileName
	 * @return
	 * @throws Exception
	 */
	public InputStream downloadFileToHTTP(String dir, String fileName)
			throws Exception {
		InputStream input = null;
		try {
			if (dir != null && dir.indexOf(";") != -1) {
				String[] dirs = dir.split(";");
				int length = dirs.length;
				int i = 0;
				while (i < length) {
					String tmp = dirs[i];
					resfileDir = resfileDir + File.separator + tmp;
					i++;
				}
			} else {
				resfileDir = resfileDir + File.separator + dir;
			}
			resfileDir = resfileDir + "/" + fileName;
			File file = new File(resfileDir);
			if (!file.exists()) {
				throw new Exception("对不起! 您下载的文件不存在");
			} else {
				int length = (new Long(file.length())).intValue();
				try {
					input = new BufferedInputStream(
							new java.io.FileInputStream(file));
					if (input != null) {
						ByteArrayOutputStream bytestream = new ByteArrayOutputStream();
						int ch;
						while ((ch = input.read()) != -1) {
							bytestream.write(ch);
						}
						byte[] imgdata = bytestream.toByteArray();
						bytestream.close();
						input.close();
						input = new ByteArrayInputStream(imgdata);
					}
				} catch (Exception ex) {
					throw new Exception("文件下载过程中出现错误! " + ex);
				}
			}
		} catch (Exception ex) {
			throw new Exception("文件下载过程中出现错误! " + ex);
		}
		return input;
	}

}
