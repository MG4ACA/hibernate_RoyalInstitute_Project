package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import entity.Course;
import javafx.event.ActionEvent;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.FactoryConfiguration;

public class CourseFormController {

    public JFXTextField txt_CourseCode;
    public JFXButton btnAddCourse;
    public JFXTextField txt_Duration;
    public JFXTextField txt_CourseName;
    public JFXTextField txt_CourseType;


    public void addCourseOnAction(ActionEvent actionEvent) {
        Course course = new Course();
        course.setCode(txt_CourseCode.getText());
        course.setCourseName(txt_CourseName.getText());
        course.setCourseType(txt_CourseType.getText());
        course.setDuration(txt_Duration.getText());

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.save(course);
        transaction.commit();
        session.close();
        txt_CourseCode.clear();
        txt_CourseName.clear();
        txt_CourseType.clear();
        txt_Duration.clear();
    }
}
