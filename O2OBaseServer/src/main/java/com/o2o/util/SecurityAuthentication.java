package com.o2o.util;

import org.apache.commons.codec.digest.DigestUtils;

public class SecurityAuthentication {
	private static String publicSalt = "";
	
	public final static String publicKey = "";
	/**
	 * 密码加密 保存到数据库的密码
	 * 
	 * @param password
	 * @return
	 */
	public static String crypt(String password) {
		return DigestUtils.md5Hex(DigestUtils.md5Hex(password));
	}

	/**
	 * 数据加密
	 * @param key
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static String encode(String key,String data) throws Exception{
		DES des = null;
		try {
			des = new DES(key);
			return des.encrypt(data);
		} catch (Exception e) {
			throw new Exception(e.getMessage() + "认证失败,请重新操作");
		}
	}
	
	/**
	 * 数据解密
	 * @param key
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static String decode(String key,String data) throws Exception {
		DES des = null;
		try {
			des = new DES(key);
			return des.decrypt(data);
		} catch (Exception e) {
			throw new Exception(e.getMessage() + "认证失败,请重新操作");
		}
	}
}
