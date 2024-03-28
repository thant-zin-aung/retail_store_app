package com.blacksky.ko_yan_nyein_aung_retail_store;

import com.blacksky.ko_yan_nyein_aung_retail_store.controller.MainController;
import com.blacksky.ko_yan_nyein_aung_retail_store.model.SystemManipulator;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
//        Font.loadFont(getClass().getResource("assets/fonts/mustica-pro.otf").toString(),16);
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("view/MainView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Retail Store");
        stage.setScene(scene);
        stage.setMinWidth(950);
        stage.setMinHeight(480);
        stage.getIcons().add(new Image(getClass().getResource("assets/images/retail-store.png").toString()));
        stage.setOnCloseRequest( e -> {
            e.consume();
            SystemManipulator.backupAppResourceFiles();
            System.exit(0);
        });
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}