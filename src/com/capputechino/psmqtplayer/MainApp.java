package com.capputechino.psmqtplayer;

import com.capputechino.psmqtplayer.layoutcontrollers.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {
    private static final String APP_TITLE = "PSM Quran Translation Player";
    private Parent root;

    @Override
    public void start(Stage primaryStage) throws Exception{
        root = FXMLLoader.load(getClass().getResource("layouts/Root.fxml"));
        loadMainLayout();
        primaryStage.setTitle(APP_TITLE);
        Scene scene = new Scene(root, 300, 275);
//        scene.setUserAgentStylesheet(getClass().getResource("css/JMetroDarkTheme.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    public Parent getRoot() {
        return root;
    }

    private void loadMainLayout() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("layouts/Main.fxml"));
        try {
            loader.load();
            MainController controller = loader.getController();
            controller.setMainApp(this);
            controller.setLayout();
        } catch (IOException e) {

        }
    }
}
