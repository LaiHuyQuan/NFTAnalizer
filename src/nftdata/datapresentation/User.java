package nftdata.datapresentation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import nftdata.dataprocessing.ReadData;
import nftdata.dataprocessing.datacollector.*;
import screen.controller.MainScreenController;

import java.io.IOException;

public class User extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        final String MAINSCREEN_FXML_FILE_PATH = "/screen/view/MainScreen.fxml";
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(MAINSCREEN_FXML_FILE_PATH));
        MainScreenController mainScreenController = new MainScreenController();
        fxmlLoader.setController(mainScreenController);
        Parent root = fxmlLoader.load();

        primaryStage.setTitle("NFT");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
