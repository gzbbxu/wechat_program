package com.aimeikongjian.service;

import org.apache.commons.lang3.RandomStringUtils;

import com.aimeikongjian.util.RedisUtil;

public class WXService {
	/**
	 * 缓存微信openId和session_key
	 * @param wxOpenId		微信用户唯一标识
	 * @param wxSessionKey	微信服务器会话密钥
	 * @param expires		会话有效期, 以秒为单位, 例如2592000代表会话有效期为30天
	 * @return
	 */
	public  static String create3rdSession(String wxOpenId, String wxSessionKey){
		String thirdSessionKey = RandomStringUtils.randomAlphanumeric(64);
		StringBuffer sb = new StringBuffer();
		sb.append(wxSessionKey).append("#").append(wxOpenId);
		//redisUtil.add(thirdSessionKey, expires, sb.toString());
		//RedisUtil.setString(thirdSessionKey,sb.toString(),2592000);
		return thirdSessionKey;
	}
}
