package com.donaldy.download.api;

import java.io.IOException;

public interface Connection {
	/**
	 * 给定开始和结束位置， 读取数据， 返回值是字节数组
	 * @param startPos 开始位置， 从0开始
	 * @param endPos 结束位置
	 * @return 字节数组
	 */
	byte[] read(int startPos, int endPos) throws IOException;
	/**
	 * 得到数据内容的长度
	 * @return 长度
	 */
	int getContentLength();
	
	/**
	 * 关闭连接
	 */
	void close();
}
