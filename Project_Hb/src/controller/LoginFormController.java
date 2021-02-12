package controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginFormController {
    public JFXPasswordField txtPassword;
    public JFXTextField txtUserName;
    public JFXTextField txtPw;
    public Button btnCancel;
    public Button btnLogin;
    public AnchorPane anchorPane;

    public void pwImgOnAction(MouseEvent mouseEvent) {
        txtPassword.setVisible(false);
        txtPw.setVisible(true);
        txtPw.setText(txtPassword.getText());
    }

    public void CancelOnAction(ActionEvent actionEvent) {
    }

    public void loginOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) anchorPane.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/view/mainForm.fxml"))));
            stage.setTitle("Main Form");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
