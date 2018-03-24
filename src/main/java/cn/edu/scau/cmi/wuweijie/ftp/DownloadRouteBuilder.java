package cn.edu.scau.cmi.wuweijie.ftp;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.properties.PropertiesComponent;

public class DownloadRouteBuilder extends RouteBuilder {
	
	private String downloadFrom;
	
	private String downloadTo;

	@Override
	public void configure() throws Exception {
		from(getDownloadFrom()).log("Downloading: ${file:name}").to(getDownloadTo())
				.log("Downloaded: ${file:name}");
	}

	public String getDownloadFrom() {
		return downloadFrom;
	}

	public void setDownloadFrom(String downloadFrom) {
		this.downloadFrom = downloadFrom;
	}

	public String getDownloadTo() {
		return downloadTo;
	}

	public void setDownloadTo(String downloadTo) {
		this.downloadTo = downloadTo;
	}

}
