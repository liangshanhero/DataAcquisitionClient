package cn.edu.scau.cmi.wuweijie.application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.edu.scau.cmi.wuweijie.Bootstrap;
import cn.edu.scau.cmi.wuweijie.utils.PopUp;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class LedProjectController extends ConfigureControllerTemplate implements Initializable {

	private static Log log = LogFactory.getLog(LedProjectController.class);
	
	@FXML
	private Button startProjectLed;

	@FXML
	private TextField quartzInterval;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		try {
			toTextFields();
		} catch (IllegalArgumentException | IllegalAccessException | IOException e1) {
			e1.printStackTrace();
		}
		
		startProjectLed.setOnAction(i -> {
			Integer interval = Integer.parseInt(quartzInterval.getText());
			if (interval <= 0) {
				PopUp.window("间隔输入有误");
				return;
			}
			
			log.info("Start Led Project with interval = " + interval);
			
			try {
				fromTextFields();
			} catch (IllegalArgumentException | IllegalAccessException | IOException e) {
				e.printStackTrace();
			}
			
			Bootstrap.applicationContext = new ClassPathXmlApplicationContext(new String[]{"classpath:applicationContext.xml"}, Bootstrap.applicationContext);

			Runnable ledProjectScheduleLauncher = Bootstrap.applicationContext.getBean("ledProjectScheduleLauncher", Runnable.class);
			Bootstrap.executorService.execute(ledProjectScheduleLauncher);
			
			startProjectLed.setDisable(true);
			
		});
	}

}
