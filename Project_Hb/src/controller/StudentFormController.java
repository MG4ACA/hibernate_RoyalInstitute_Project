package controller;

import business.BOFactory;
import business.BOType;
import business.custom.StudentBO;
import com.jfoenix.controls.*;
import dto.StudentDTO;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import model.StudentTM;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

public class StudentFormController {
    public JFXTextField txt_sId;
    public JFXTextField txt_sName;
    public JFXTextField txt_sAddress;
    public JFXTextField txt_sContact;
    public JFXDatePicker dp_DOB;
    public HBox rbtnGender;
    public JFXRadioButton btnFemale;
    public JFXRadioButton btnMale;
    public String gender;
    public Button btnDelete;
    public TableView tblStudent;
    public TableColumn s_Id;
    public TableColumn s_Name;
    public TableColumn s_Address;
    public TableColumn s_Contact;
    public TableColumn s_Gender;
    public TableColumn s_Dob;

    StudentBO studentBO = BOFactory.getInstance().getBO(BOType.STUDENT);

    public void initialize() {
        s_Id.setCellValueFactory(new PropertyValueFactory<>("id"));
        s_Name.setCellValueFactory (new PropertyValueFactory<> ("studentName"));
        s_Address.setCellValueFactory(new PropertyValueFactory<> ("address"));
        s_Contact.setCellValueFactory(new PropertyValueFactory<> ("contact"));
        s_Gender.setCellValueFactory(new PropertyValueFactory<> ("gender"));
        s_Dob.setCellValueFactory(new PropertyValueFactory<> ("Dob"));
        try {
            loadTable ();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void loadTable() {
        ObservableList<StudentTM> items = tblStudent.getItems();
        items.clear();
        try {
            List<StudentDTO> allStudents = studentBO.getAllStudents();
            for (StudentDTO student : allStudents) {
                items.add(new StudentTM(student.getId(), student.getStudentName(), student.getAddress()
                        , student.getContact(), student.getDob(), student.getGender()));
            }
            tblStudent.refresh();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void saveOnAction(ActionEvent actionEvent) {
        StudentDTO studentDTO = new StudentDTO(txt_sId.getText(), txt_sName.getText(), txt_sAddress.getText(), txt_sContact.getText(), dp_DOB.getValue(), gender);

        if (Pattern.compile("^(S0)[0-9]{2}$").matcher(txt_sId.getText()).matches()) {
            if (Pattern.compile("^[A-z]{1,100}\\s|[A-z]{1,100}$").matcher(txt_sName.getText()).matches()) {
                if (Pattern.compile("^[A-z]{1,100}\\s|[A-z]+$").matcher(txt_sAddress.getText()).matches()) {
                    if (Pattern.compile("^[0-9]{9,10}$").matcher(txt_sContact.getText()).matches()) {
                        try {
                            if (btnMale.isSelected()) gender = "Male";
                            else gender = "Female";
                            boolean isAdded = studentBO.addStudent(new StudentDTO(txt_sId.getText(), txt_sName.getText(), txt_sAddress.getText(), txt_sContact.getText(), dp_DOB.getValue(), gender));
                            if (isAdded) {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Student Saved Successfully");
                                alert.show();
                                clear();
                                loadTable();
                            } else {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Student Not Saved");
                                alert.show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Alert alert = new Alert(Alert.AlertType.WARNING, "Duplicate Student ID");
                            alert.show();
                        }
                    } else {
                        txt_sContact.setFocusColor(Paint.valueOf("red"));
                        txt_sContact.requestFocus();
                    }
                } else {
                    txt_sAddress.setFocusColor(Paint.valueOf("red"));
                    txt_sAddress.requestFocus();
                }
            } else {
                txt_sName.setFocusColor(Paint.valueOf("red"));
                txt_sName.requestFocus();
            }
        } else {
            txt_sId.setFocusColor(Paint.valueOf("red"));
            txt_sId.requestFocus();
        }
    }

    public void cancelOnAction(ActionEvent actionEvent) {
    }

    public void updateOnAction(ActionEvent actionEvent) {
        if (Pattern.compile("^(S0)[0-9]{2}$").matcher(txt_sId.getText()).matches()) {
            if (Pattern.compile("^[A-z]{1,100}\\s|[A-z]{1,100}$").matcher(txt_sName.getText()).matches()) {
                if (Pattern.compile("^[A-z]{1,100}\\s|[A-z]+$").matcher(txt_sAddress.getText()).matches()) {
                    if (Pattern.compile("^[0-9]{9,10}$").matcher(txt_sContact.getText()).matches()) {
                        boolean isUpdated = false;
                        try{
                            if (btnMale.isSelected()) gender = "Male";
                            else gender = "Female";
                            isUpdated = studentBO.updateStudent(new StudentDTO(txt_sId.getText(), txt_sName.getText(),
                                    txt_sAddress.getText(), txt_sContact.getText(), dp_DOB.getValue(), gender));
                            if (isUpdated) {
                                new Alert(Alert.AlertType.INFORMATION, "Updated Successfully").show();
                                clear();
                                loadTable();
                            } else {
                                new Alert(Alert.AlertType.INFORMATION, "Updated Fail !").show();
                            }
                        }catch(Exception e){
                            e.printStackTrace();
                        }
                    } else {
                        txt_sContact.setFocusColor(Paint.valueOf("red"));
                        txt_sContact.requestFocus();
                    }
                } else {
                    txt_sAddress.setFocusColor(Paint.valueOf("red"));
                    txt_sAddress.requestFocus();
                }
            } else {
                txt_sName.setFocusColor(Paint.valueOf("red"));
                txt_sName.requestFocus();
            }
        } else {
            txt_sId.setFocusColor(Paint.valueOf("red"));
            txt_sId.requestFocus();
        }
    }
    public void deleteOnAction(ActionEvent actionEvent) {
        try {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Are sure ..?", ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> buttonType = alert.showAndWait();

            if (buttonType.get().equals(ButtonType.YES)) {
                studentBO.deleteStudent(new StudentDTO(txt_sId.getText(),txt_sName.getText(),txt_sAddress.getText(),txt_sContact.getText(), dp_DOB.getValue(), gender));
                loadTable();
                clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void searchOnAction(ActionEvent actionEvent) {
        try{
            StudentDTO student = studentBO.getStudent(txt_sId.getText());
            if (null != student) {
                txt_sName.setText(student.getStudentName());
                txt_sAddress.setText(student.getAddress());
                txt_sContact.setText(student.getContact());
                if (student.getGender().equals("Male")){
                    btnMale.setSelected(true);
                }else{
                    System.out.println(student.getGender());
                    btnFemale.setSelected(true);
                }
                dp_DOB.setValue(LocalDate.parse (student.getDob ().toString ()));
            } else {
            new Alert(Alert.AlertType.WARNING, "Search Does not Match").show();
            }
        }catch(Exception e){
          //  e.printStackTrace();
            new Alert(Alert.AlertType.WARNING,"Search Does not Match" ).show();
        }
    }
    private void clear() {
        txt_sId.clear();
        txt_sName.clear();
        txt_sAddress.clear();
        txt_sContact.clear();
        dp_DOB.getEditor().clear();
        btnFemale.setSelected(false);
        btnMale.setSelected(false);
    }

}
