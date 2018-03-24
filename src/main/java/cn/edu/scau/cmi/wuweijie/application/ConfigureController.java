package cn.edu.scau.cmi.wuweijie.application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.edu.scau.cmi.wuweijie.Bootstrap;
import cn.edu.scau.cmi.wuweijie.entity.server.Project;
import cn.edu.scau.cmi.wuweijie.entity.server.ProjectDAO;
import cn.edu.scau.cmi.wuweijie.utils.DatabaseConnectionTest;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;

/**
 * 界面控制器
 * 
 * @author TESLA_CN
 *
 */
@Deprecated
public class ConfigureController implements Initializable {

	private static final Log log = LogFactory.getLog(ConfigureController.class);

	@FXML
	private TextField clientDatabaseUrl;

	@FXML
	private PasswordField clientDatabasePassword;

	@FXML
	private PasswordField serverDatabasePassword;

	@FXML
	private TextField serverDatabaseUser;

	@FXML
	private Button startProjectWater;

	@FXML
	private Button startProjectLed;

	@FXML
	private TextField clientDatabaseUser;

	@FXML
	private Button testClientDatabaseConnection;

	@FXML
	private Button testServerDatabaseConnection;

	@FXML
	private Button connectDatabase;

	@FXML
	private Button getProjects;

	@FXML
	private TextField serverDatabaseUrl;

	@FXML
	private ComboBox<Project> projectsBox;

	@FXML
	private TextField quartzLedInterval;

	@FXML
	private TextField quartzWaterInterval;

	@FXML
	private TextField ledGeneratorFluctuation;

	private Properties properties;

	private String propertyFileName;

	private ProjectDAO projectDAO;

	public static Project currentProject;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setPropertyFileName((String) Bootstrap.applicationContext.getBean("propertyFileName"));
		try {
			toTextFields();
		} catch (IllegalArgumentException | IllegalAccessException | IOException e) {
			e.printStackTrace();
		}

		getProjects.setDisable(true);
		getProjects.setOnAction(i -> {
			log.info("Load projects from server database.");
			List<Project> projectList = getProjectDAO().findAll();
			ObservableList<Project> observableProjects = FXCollections.observableArrayList(projectList);
			projectsBox.getItems().setAll(observableProjects);
			startProjectWater.setDisable(false);
		});

		testClientDatabaseConnection.setOnAction(i -> {
			log.info("Test client database connection.");
			String url = clientDatabaseUrl.getText();
			String user = clientDatabaseUser.getText();
			String password = clientDatabasePassword.getText();
			try {
				DatabaseConnectionTest.mysqlConnectionTest(url, user, password);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		testServerDatabaseConnection.setOnAction(i -> {
			log.info("Test server database connection.");
			String url = serverDatabaseUrl.getText();
			String user = serverDatabaseUser.getText();
			String password = serverDatabasePassword.getText();
			try {
				DatabaseConnectionTest.mysqlConnectionTest(url, user, password);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		connectDatabase.setOnAction(i -> {
			connectDatabase.setDisable(true);
			log.info("Initialize Spring Context");
			try {
				fromTextFields();
			} catch (IllegalArgumentException | IllegalAccessException | IOException e1) {
				e1.printStackTrace();
			}

			Bootstrap.applicationContext = new ClassPathXmlApplicationContext(new String[] {
					"classpath:applicationContext.xml", "classpath:*DaoContext.xml", "classpath:batchContext.xml" },
					Bootstrap.applicationContext);
			setProjectDAO((ProjectDAO) Bootstrap.applicationContext.getBean("ProjectDAO"));
			getProjects.setDisable(false);
			startProjectLed.setDisable(false);
			testClientDatabaseConnection.setDisable(true);
			testServerDatabaseConnection.setDisable(true);
		});

		startProjectWater.setDisable(true);
		startProjectWater.setOnAction(i -> {

			currentProject = projectsBox.getSelectionModel().getSelectedItem();
			log.info("Start running project: " + currentProject);
			if (currentProject == null) {
				return;
			}

			// 开始运行项目后其他按钮都设置为不可用
			startProjectWater.setDisable(true);
			projectsBox.setDisable(true);
			getProjects.setDisable(true);
			startProjectWater.setText("Running...");

			// 创建批处理线程，加入线程池
			Runnable waterProjectScheduleLauncher = Bootstrap.applicationContext
					.getBean("waterProjectScheduleLauncher", Runnable.class);
			Bootstrap.executorService.execute(waterProjectScheduleLauncher);
			Runnable strategyScheduleLauncher = Bootstrap.applicationContext.getBean("strategyScheduleLauncher",
					Runnable.class);
			Bootstrap.executorService.execute(strategyScheduleLauncher);
			
		});

		startProjectLed.setDisable(true);
		startProjectLed.setOnAction(i -> {

			log.info("Start Led data generator");
			startProjectLed.setDisable(true);

			Runnable ledProjectScheduleLauncher = Bootstrap.applicationContext
					.getBean("ledProjectScheduleLauncher", Runnable.class);
			Bootstrap.executorService.execute(ledProjectScheduleLauncher);
		});
	}

	/**
	 * 从 properties 文件中取出键值对并写入对应的界面输入框中
	 * 
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private void toTextFields()
			throws IllegalArgumentException, IllegalAccessException, FileNotFoundException, IOException {
		Class<ConfigureController> clazz = ConfigureController.class;
		Field[] fields = clazz.getDeclaredFields();
		Properties properties = new Properties();
		log.info("Load properties from file: " + getPropertyFileName());
		properties.load(new FileInputStream(getPropertyFileName()));
		for (Field field : fields) {
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
	private void fromTextFields()
			throws IllegalArgumentException, IllegalAccessException, FileNotFoundException, IOException {
		Class<ConfigureController> clazz = ConfigureController.class;
		Properties properties = new Properties();
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
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

	public ProjectDAO getProjectDAO() {
		return projectDAO;
	}

	public void setProjectDAO(ProjectDAO projectDAO) {
		this.projectDAO = projectDAO;
	}

}
