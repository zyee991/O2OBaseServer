package com.o2o.util;

import org.apache.commons.codec.digest.DigestUtils;

public class SecurityAuthentication {
	public final static String publicKey = "";
	/**
	 * 密码加密 保存到数据库的密码
	 * 
	 * @param password
	 * @return
	 */
	public static String crypt(String password) {
		return DigestUtils.md5Hex(password);
	}
}
