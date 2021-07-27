package com.donaldy.download.api;

/**
 * 连接管理
 */
public interface ConnectionManager {
	/**
	 * 给定一个url , 打开一个连接
	 * @param url 连接
	 * @return 连接
	 */
	Connection open(String url) throws ConnectionException;
}
