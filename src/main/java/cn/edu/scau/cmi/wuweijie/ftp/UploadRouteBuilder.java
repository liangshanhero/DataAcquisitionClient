package cn.edu.scau.cmi.wuweijie.ftp;

import java.io.FileInputStream;
import java.util.Properties;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.properties.PropertiesComponent;

public class UploadRouteBuilder extends RouteBuilder {

	private String uploadFrom;

	private String uploadTo;

	@Override
	public void configure() throws Exception {
		from(getUploadFrom()).log("Uploading: ${file:name}").to(getUploadTo()).log("Done: ${file:name}");

	}

	public String getUploadFrom() {
		return uploadFrom;
	}

	public void setUploadFrom(String uploadFrom) {
		this.uploadFrom = uploadFrom;
	}

	public String getUploadTo() {
		return uploadTo;
	}

	public void setUploadTo(String uploadTo) {
		this.uploadTo = uploadTo;
	}

}
