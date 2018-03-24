package cn.edu.scau.cmi.wuweijie;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.edu.scau.cmi.wuweijie.application.Tray;
import cn.edu.scau.cmi.wuweijie.entity.server.Project;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * 程序启动入口
 *
 */
public class Bootstrap extends Application {

	private static Log log = LogFactory.getLog(Bootstrap.class);

	/**
	 * Spring Context
	 */
	public static ApplicationContext applicationContext;

	public static Stage mainStage;
	
	public static Project currentProject;

	public static ExecutorService executorService = Executors.newCachedThreadPool();

	@Override
	public void start(Stage primaryStage) throws Exception {
		mainStage = primaryStage;
		Platform.setImplicitExit(false);
		applicationContext = new ClassPathXmlApplicationContext("classpath:initContext.xml");
		applicationContext = new ClassPathXmlApplicationContext(new String[]{"classpath:initContext.xml"}, applicationContext);

		Parent root = applicationContext.getBean("serverDatabaseConfigureInterface", Parent.class);
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.setOnCloseRequest(i -> {
			primaryStage.hide();
		});

		primaryStage.show();

		Tray.enableTray();

	}// NOTE: 应用程序界面只有跑完 start 方法才会显示

	public static void main(String[] args) throws Exception {
		launch(args);
	}
}
