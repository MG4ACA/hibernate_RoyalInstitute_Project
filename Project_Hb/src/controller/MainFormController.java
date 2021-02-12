package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainFormController {
    public Button btnRegistration;
    public Button btnStudent;
    public Button btnCourse;
    public Button btnUserManage;
    public AnchorPane subAnchorPane;
    public Button btnLogOff;
    public AnchorPane anchorPane;

    /**
     * @param location
     * load fxml files to the subAnchorPane of the MainForm.fxml
     * @throws IOException
     */
    public void setSubAnchorPane ( String location ) throws IOException {
        this.subAnchorPane.getChildren().clear();
        this.subAnchorPane.getChildren().add(FXMLLoader.load(this.getClass().getResource("../view/"+location)));
    }
    /**
     * @param location
     * load fxml file in new {@link Scene}
     * @throws IOException
     */
    public void setAnchorPane(String location) throws IOException {
        Stage stage = (Stage) anchorPane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/view/" + location))));
    }
    public void registerOnAction(ActionEvent actionEvent) throws IOException {
        setSubAnchorPane("registrationForm.fxml");
    }

    public void studentOnAction(ActionEvent actionEvent) throws IOException {
        setSubAnchorPane("studentForm.fxml");
    }

    public void courseOnAction(ActionEvent actionEvent) throws IOException {
        setSubAnchorPane("courseForm.fxml");
    }

    public void userManageOnAction(ActionEvent actionEvent) throws IOException {
        setSubAnchorPane("userManageForm.fxml");
    }

    public void logOffOnAction(ActionEvent actionEvent) throws IOException {
        setAnchorPane("loginForm.fxml");
    }

}
