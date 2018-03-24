package cn.edu.scau.cmi.wuweijie.application;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.edu.scau.cmi.wuweijie.Bootstrap;
import cn.edu.scau.cmi.wuweijie.entity.server.Company;
import cn.edu.scau.cmi.wuweijie.entity.server.CompanyDAO;
import cn.edu.scau.cmi.wuweijie.entity.server.Project;
import cn.edu.scau.cmi.wuweijie.entity.server.ProjectDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;

public class ClientConfigureController extends ConfigureControllerTemplate implements Initializable {

	private static Log log = LogFactory.getLog(ClientConfigureController.class);

	@FXML
	private ComboBox<String> types;

	@FXML
	private ComboBox<Project> projects;

	@FXML
	private ComboBox<Company> companies;

	private ProjectDAO projectDAO;

	private CompanyDAO companyDAO;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setProjectDAO(Bootstrap.applicationContext.getBean("ProjectDAO", ProjectDAO.class));
		setCompanyDAO(Bootstrap.applicationContext.getBean("CompanyDAO", CompanyDAO.class));

		List<Company> companyList = (List<Company>) getCompanyDAO().findAll();
		ObservableList<Company> observableCompanies = FXCollections.observableArrayList(companyList);
		companies.setItems(observableCompanies);
		companies.setOnAction(i -> {
			projects.getItems().clear();
			Company selectedCompany = companies.getSelectionModel().getSelectedItem();
			log.info("Companies selected: " + selectedCompany);

			Set<Project> projectSet = selectedCompany.getProjects();
			List<String> typeList = new ArrayList<>();
			projectSet.forEach(e -> {
				String type = e.getType();
				if (!typeList.contains(type)) {
					typeList.add(type);
				}
			});
			types.getItems().setAll(FXCollections.observableArrayList(typeList));
		});

		types.setOnAction(i -> {
			projects.getItems().clear();
			String selectedType = types.getSelectionModel().getSelectedItem();
			log.info("Type selected: " + selectedType);

			Project example = new Project();
			example.setCompany(companies.getSelectionModel().getSelectedItem());
			example.setType(selectedType);
			if (example.getCompany() == null || example.getType() == null) {
				return;
			}
			List<Project> projectList = getProjectDAO().findByExample(example);

			projects.getItems().setAll(FXCollections.observableArrayList(projectList));

		});

		projects.setOnAction(i -> {
			Project selectedProject = projects.getSelectionModel().getSelectedItem();

			log.info("Project selected: " + selectedProject);
			if (selectedProject == null) {
				return;
			}
			
			if (selectedProject != null) {
				Bootstrap.currentProject = selectedProject;
			}

		});
		
		projects.getSelectionModel().selectedItemProperty().addListener(l -> {
			Parent waterProjectInterface = Bootstrap.applicationContext.getBean("waterProjectInterface", Parent.class);
			Parent ledProjectInterface = Bootstrap.applicationContext.getBean("ledProjectInterface", Parent.class);
			
			String selectedType = types.getSelectionModel().getSelectedItem();
			BorderPane pane = (BorderPane) Bootstrap.mainStage.getScene().getRoot();
			switch(selectedType) {
			case "热水":
				pane.setBottom(waterProjectInterface);
				break;
			case "LED":
				pane.setBottom(ledProjectInterface);
				break;
			default:
				pane.setBottom(null);
			}
		});

	}

	public ProjectDAO getProjectDAO() {
		return projectDAO;
	}

	public void setProjectDAO(ProjectDAO projectDAO) {
		this.projectDAO = projectDAO;
	}

	public CompanyDAO getCompanyDAO() {
		return companyDAO;
	}

	public void setCompanyDAO(CompanyDAO companyDAO) {
		this.companyDAO = companyDAO;
	}
}
