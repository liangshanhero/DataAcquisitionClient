package cn.edu.scau.cmi.wuweijie.ftp;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.main.Main;

public class FTPLauncher implements Runnable {

	private RouteBuilder uploadRouteBuilder;
	
	private RouteBuilder downloadRouteBuilder;
	
	@Override
	public void run() {
		Main main = new Main();
		main.addRouteBuilder(getUploadRouteBuilder());
		main.addRouteBuilder(getDownloadRouteBuilder());
		try {
			main.run();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public RouteBuilder getUploadRouteBuilder() {
		return uploadRouteBuilder;
	}

	public void setUploadRouteBuilder(RouteBuilder uploadRouteBuilder) {
		this.uploadRouteBuilder = uploadRouteBuilder;
	}

	public RouteBuilder getDownloadRouteBuilder() {
		return downloadRouteBuilder;
	}

	public void setDownloadRouteBuilder(RouteBuilder downloadRouteBuilder) {
		this.downloadRouteBuilder = downloadRouteBuilder;
	}

}
