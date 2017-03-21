package com.upload;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.enterprisedt.net.ftp.FTPClient;
import com.enterprisedt.net.ftp.FTPConnectMode;
import com.enterprisedt.net.ftp.FTPException;
import com.enterprisedt.net.ftp.FTPTransferType;

public class FtpClient {
	private FTPClient ftpClient = null;

	public void connectServer(String server, String user, String password) throws IOException, FTPException {
		if (ftpClient == null || !ftpClient.connected()) {
			ftpClient = new FTPClient();
				ftpClient.setRemoteHost(server);
				ftpClient.setTimeout(500000);
				ftpClient.connect();
				ftpClient.login(user, password);
				ftpClient.setConnectMode(FTPConnectMode.PASV);
				ftpClient.setType(FTPTransferType.BINARY);
		}
	}
	
	public void changeDir(String path) {
		if (path == null || path.equals("")) {
			return;
		}
		String temp[] = path.split("/");
		for (int i = 0; i < temp.length; i++) {
			try {
				if (temp[i] == null || temp[i].equals("")) {
					continue;
				}
				ftpClient.chdir(temp[i]);
			} catch (FTPException fe) {
				try {
					ftpClient.mkdir(temp[i]);
					ftpClient.chdir(temp[i]);
				} catch (Exception e) {

				}
			} catch (Exception ee) {

			}
		}
	}

	public void uploadFile(InputStream in, String filename) {
		try {
			ftpClient.put(in, filename);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void uploadFile(byte[] bytes, String filename) {
		try {
			ftpClient.put(bytes, filename);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void downLoad(OutputStream out, String filename) {
		try {
			ftpClient.get(out, filename);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void close() {
		try {
			if (ftpClient != null && ftpClient.connected()) {
				ftpClient.quit();
				ftpClient = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
