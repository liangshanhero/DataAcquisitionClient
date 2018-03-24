package cn.edu.scau.cmi.wuweijie.utils;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PopUp {

	public static void window(String tip) {
		Stage stage = new Stage();
		stage.setResizable(false);
		BorderPane pane = new BorderPane();
		pane.setMinSize(300, 180);
		Label label = new Label(tip);
		VBox box = new VBox(10);
		box.setAlignment(Pos.CENTER);
		pane.setCenter(box);
		Button ok = new Button("确定");
		box.getChildren().addAll(label, ok);
		Scene scene = new Scene(pane);
		stage.setScene(scene);
		stage.setTitle("提示");
		stage.show();
		ok.setOnAction(i -> {
			stage.close();
		});
	}
	
}
