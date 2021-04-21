package com.donaldy.download.api;

public interface ConnectionManager {
	/**
	 * 给定一个url , 打开一个连接
	 * @param url 连接
	 * @return 连接
	 */
	Connection open(String url) throws ConnectionException;
}
