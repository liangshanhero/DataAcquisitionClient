package cn.edu.scau.cmi.wuweijie.application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.edu.scau.cmi.wuweijie.Bootstrap;
import cn.edu.scau.cmi.wuweijie.utils.DatabaseConnectionTest;
import cn.edu.scau.cmi.wuweijie.utils.PopUp;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class WaterProjectController extends ConfigureControllerTemplate implements Initializable {

	private static Log log = LogFactory.getLog(WaterProjectController.class);

	@FXML
	private PasswordField clientDatabasePassword;

	@FXML
	private Button startProjectWater;

	@FXML
	private TextField clientDatabaseUser;

	@FXML
	private TextField quartzInterval;

	@FXML
	private ComboBox<?> projectsBox;

	@FXML
	private TextField clientDatabaseIp;

	@FXML
	private TextField clientDatabaseSchema;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		try {
			toTextFields();
		} catch (IllegalArgumentException | IllegalAccessException | IOException e) {
			e.printStackTrace();
			PopUp.window(e.getMessage());
		}

		startProjectWater.setOnAction(i -> {
			if (Bootstrap.currentProject == null) {
				PopUp.window("请选择项目");
				return;
			}
			String ip = clientDatabaseIp.getText();
			String schema = clientDatabaseSchema.getText();
			String url = String.format("jdbc:mysql://%s/%s?useSSL=false", ip, schema);

			String user = clientDatabaseUser.getText();
			String password = clientDatabasePassword.getText();

			try {
				DatabaseConnectionTest.mysqlConnectionTest(url, user, password);
			} catch (Exception e) {
				log.error(e.getMessage());
				PopUp.window(e.getMessage());
				return;
			}
			System.setProperty("client.database.url", url);

			try {
				fromTextFields();
			} catch (IllegalArgumentException | IllegalAccessException | IOException e) {
				e.printStackTrace();
				PopUp.window(e.getMessage());
				return;
			}

			Bootstrap.applicationContext = new ClassPathXmlApplicationContext(
					new String[] { "classpath:clientDaoContext.xml", "classpath:applicationContext.xml",
							"classpath:batchContext.xml" },
					Bootstrap.applicationContext);

			Runnable waterProjectScheduleLauncher = Bootstrap.applicationContext.getBean("waterProjectScheduleLauncher", Runnable.class);
			Bootstrap.executorService.execute(waterProjectScheduleLauncher);
			
			startProjectWater.setDisable(true);
		});

	}

}
