package cn.edu.scau.cmi.wuweijie.application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.edu.scau.cmi.wuweijie.Bootstrap;
import javafx.scene.control.TextInputControl;

public class ConfigureControllerTemplate {

	private static Log log = LogFactory.getLog(ConfigureControllerTemplate.class);

	private Properties properties;

	private String propertyFileName;
	
	public ConfigureControllerTemplate() {
		setPropertyFileName(Bootstrap.applicationContext.getBean("propertyFileName", String.class));
	}

	/**
	 * 从 properties 文件中取出键值对并写入对应的界面输入框中
	 * 
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	protected void toTextFields()
			throws IllegalArgumentException, IllegalAccessException, FileNotFoundException, IOException {
		Class<?> clazz = getClass();
		Field[] fields = clazz.getDeclaredFields();
		Properties properties = new Properties();
		log.info("Load properties from file: " + getPropertyFileName());
		properties.load(new FileInputStream(getPropertyFileName()));
		for (Field field : fields) {
			field.setAccessible(true);
			Object obj = field.get(this);
			if (obj instanceof TextInputControl) {
				TextInputControl input = (TextInputControl) obj;
				String propertyName = input.getPromptText();
				String value = properties.getProperty(propertyName, "");
				if (input.getText().equals("")) {
					input.setText(value);
				}

			}
		}
		setProperties(properties);
	}

	/**
	 * 从界面输入框中读取键值对并且写入 properties 文件中
	 * 
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	protected void fromTextFields()
			throws IllegalArgumentException, IllegalAccessException, FileNotFoundException, IOException {
		Class<?> clazz = getClass();
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			Object obj = field.get(this);
			if (obj instanceof TextInputControl) {
				TextInputControl input = (TextInputControl) obj;
				String propertyName = input.getPromptText();
				String value = input.getText();
				properties.setProperty(propertyName, value);
				input.setDisable(true);
			}
		}
		properties.store(new FileOutputStream(getPropertyFileName()), "");
		log.info("Store properties to file: " + getPropertyFileName());
		setProperties(properties);
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public String getPropertyFileName() {
		return propertyFileName;
	}

	public void setPropertyFileName(String propertyFileName) {
		this.propertyFileName = propertyFileName;
	}
	
}
