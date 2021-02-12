package entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Student implements SuperEntity {
    @Id
    private String id;
    private String studentName;
    private String Address;
    private String Contact;
    private LocalDate dob;
    private String Gender;

    public Student() {
    }

    public Student(String id, String studentName, String address, String contact, LocalDate dob, String gender) {
        this.id = id;
        this.studentName = studentName;
        Address = address;
        Contact = contact;
        this.dob = dob;
        Gender = gender;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getContact() {
        return Contact;
    }

    public void setContact(String contact) {
        Contact = contact;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", studentName='" + studentName + '\'' +
                ", Address='" + Address + '\'' +
                ", Contact='" + Contact + '\'' +
                ", dob=" + dob +
                ", Gender='" + Gender + '\'' +
                '}';
    }
}
