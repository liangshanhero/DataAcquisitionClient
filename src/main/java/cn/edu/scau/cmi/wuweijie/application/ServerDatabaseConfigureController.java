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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class ServerDatabaseConfigureController extends ConfigureControllerTemplate implements Initializable {

	private static Log log = LogFactory.getLog(ServerDatabaseConfigureController.class);

	@FXML
	private PasswordField serverDatabasePassword;

	@FXML
	private Button serverDatabaseConnect;

	@FXML
	private TextField serverDatabaseUser;

	@FXML
	private TextField serverDatabaseIp;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			toTextFields();
		} catch (IllegalArgumentException | IllegalAccessException | IOException e1) {
			e1.printStackTrace();
		}

		serverDatabaseConnect.setOnAction(i -> {
			String ip = serverDatabaseIp.getText();
			String url = String.format("jdbc:mysql://%s/energydevice?useSSL=false", ip);
			String user = serverDatabaseUser.getText();
			String password = serverDatabasePassword.getText();
			try {
				DatabaseConnectionTest.mysqlConnectionTest(url, user, password);
			} catch (Exception e) {
				log.error(e.getMessage());
				PopUp.window("连接出错\n" + e.getMessage());
				return;
			}

			try {
				fromTextFields();
			} catch (IllegalArgumentException | IllegalAccessException | IOException e) {
				e.printStackTrace();
			}
			
			System.setProperty("server.database.url", url);
			
			Bootstrap.applicationContext = new ClassPathXmlApplicationContext(
					new String[] { "classpath:serverDaoContext.xml" }, Bootstrap.applicationContext);
			
			serverDatabaseConnect.setDisable(true);

			Parent clientConfigureInterface = Bootstrap.applicationContext.getBean("clientConfigureInterface", Parent.class);
			Scene scene = new Scene(clientConfigureInterface);
			Bootstrap.mainStage.setScene(scene);
			
		});
	}

}