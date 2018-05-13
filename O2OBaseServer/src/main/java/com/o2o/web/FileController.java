package com.o2o.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.jfinal.core.Controller;
import com.jfinal.upload.UploadFile;
import com.o2o.util.FtpUtil;

public class FileController extends Controller  {
	
	public void save() {
		UploadFile uf = getFile();
		Map<String,String> resultMap = new HashMap<>();
		String path = "";
		if(uf != null) {
			File file = uf.getFile();
			String suffix = file.getName().substring(file.getName().lastIndexOf("."));
			try {
				InputStream in = new FileInputStream(file);
				FtpUtil ftpUtil = new FtpUtil();
				path = ftpUtil.uploadFile( new Date().getTime()+""+suffix, in);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		resultMap.put("path", path);
		renderJson(resultMap);
	}
}
