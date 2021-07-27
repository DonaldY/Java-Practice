package com.donaldy.download.impl;

import com.donaldy.download.api.Connection;
import com.donaldy.download.api.ConnectionException;
import com.donaldy.download.api.ConnectionManager;

public class ConnectionManagerImpl implements ConnectionManager {

	@Override
	public Connection open(String url) throws ConnectionException {
		
		return new ConnectionImpl(url);
	}

}
