package cn.edu.scau.cmi.wuweijie.application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.edu.scau.cmi.wuweijie.Bootstrap;
import cn.edu.scau.cmi.wuweijie.entity.server.Project;
import cn.edu.scau.cmi.wuweijie.entity.server.ProjectDAO;
import cn.edu.scau.cmi.wuweijie.quartz.ScheduleLauncher;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.*;

/**
 * 设置程序运行所需参数的界面
 * 
 * @author TESLA_CN
 * @deprecated 此界面已废弃
 */
@Deprecated
public class ConfigureProperty {

	/**
	 * 配置文件名
	 */
	private String propertyFileName;

	/**
	 * 必要参数
	 */
	private String[] propertyNames;

	private Properties properties;

	public ConfigureProperty() {
	}

	/**
	 * 构造界面 <br>
	 * NOTE: JavaFX 相关操作必须在 JavaFX专属线程 中执行，否则程序会出错
	 */
	private void constructInterface() {
		ResourceBundle resource = ResourceBundle.getBundle("cn.edu.scau.cmi.wuweijie.application.ConfigureInterface",
				Locale.getDefault());
		properties = new Properties();

		Log log = LogFactory.getLog(ConfigureProperty.class);
		log.info(String.format("Properties File: '%s'", getPropertyFileName()));

		// 读取配置文件
		if (getPropertyFileName() != null && !getPropertyFileName().equals("")) {
			try {
				File propertiesFile = new File(getPropertyFileName());
				if (propertiesFile.exists()) {
					properties.load(new FileInputStream(propertiesFile));
				} else {
					// 配置文件不存在，创建新配置文件
					propertiesFile.createNewFile();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		setStage(new Stage());
		setBorderPane(new BorderPane());
		setScene(new Scene(getBorderPane()));
		getStage().setScene(getScene());
		rows = new HBox[getPropertyNames().length];

		// 为所需配置参数生成输入框
		for (int i = 0; i < getPropertyNames().length; i++) {
			rows[i] = new HBox(10);
			rows[i].getChildren().add(new Label(resource.getString(getPropertyNames()[i])));
			// 将已有的参数读入输入框中
			String property = properties.getProperty(getPropertyNames()[i], "");
			log.info(String.format("Property: %s = %s", getPropertyNames()[i], property));
			TextField textField = new TextField(property);
			textField.setId(getPropertyNames()[i]);
			rows[i].getChildren().add(textField);
		}
		vBox = new VBox(10);
		vBox.getChildren().addAll(rows);
		getBorderPane().setCenter(vBox);
		getBorderPane().setPadding(new Insets(20));

		// 确认按钮 启动批处理调度器 和 FTP目录监控读写框架
		buttonConfirm = new Button("确定");
		buttonConfirm.setOnAction(i -> {

			// 确定按钮被点击后，从输入框中读取配置参数，并保存配置文件
			for (HBox row : rows) {
				TextField textField = (TextField) row.getChildren().get(1);
				String key = textField.getId();
				String value = textField.getText();
				properties.setProperty(key, value);
			}
			try {
				properties.store(new FileOutputStream(getPropertyFileName()), "");
				File batchRecord = new File(properties.getProperty("batch.record.file"));
				if (!batchRecord.exists())
					batchRecord.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			getStage().close();

			// 参数配置已完成，Spring 框架加载配置后的 context
			Bootstrap.applicationContext = new ClassPathXmlApplicationContext(new String[] {
					"classpath:applicationContext.xml", "classpath:daoContext.xml", "classpath:batchContext.xml" },
					Bootstrap.applicationContext);
			Project project = (Project) Bootstrap.applicationContext.getBean("currentProject");
			ProjectDAO projectDAO = (ProjectDAO) Bootstrap.applicationContext.getBean("ProjectDAO");
			project = (Project) projectDAO.findAll().get(0);

			// 新线程：启动 批处理调度
			new Thread((Runnable) Bootstrap.applicationContext.getBean("scheduleLauncher")).start();
			// 新线程：启动 FTP 监控读写框架
//			new Thread((Runnable) Bootstrap.applicationContext.getBean("ftpLauncher")).start();
		});
		getBorderPane().setBottom(buttonConfirm);
	}

	private Button buttonConfirm;

	private Stage stage;

	private Scene scene;

	private BorderPane borderPane;

	private VBox vBox;

	private HBox[] rows;

	/**
	 * 显示属性设置界面
	 */
	public void showPropertiesSetter() {

		getStage().show();
	}

	public String[] getPropertyNames() {
		return propertyNames;
	}

	/**
	 * 设置运行必须参数的参数名
	 * 
	 * @param propertyNames
	 *            参数名
	 */
	public void setPropertyNames(String[] propertyNames) {
		this.propertyNames = propertyNames;
	}

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public BorderPane getBorderPane() {
		return borderPane;
	}

	public void setBorderPane(BorderPane borderPane) {
		this.borderPane = borderPane;
	}

	public Scene getScene() {
		return scene;
	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}

	public String getPropertyFileName() {
		return propertyFileName;
	}

	public void setPropertyFileName(String propertyFileName) {
		this.propertyFileName = propertyFileName;
	}

}
