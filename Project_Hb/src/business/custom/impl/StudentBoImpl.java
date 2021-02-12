package business.custom.impl;

import business.custom.StudentBO;
import dao.DAOFactory;
import dao.DAOType;
import dao.custom.StudentDAO;
import dto.StudentDTO;
import entity.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentBoImpl implements StudentBO {
    StudentDAO studentDAO= DAOFactory.getInstance().getDAO(DAOType.STUDENT);

    @Override
    public boolean addStudent(StudentDTO student) throws Exception {
        return studentDAO.add(new Student(student.getId(),student.getStudentName(),student.getAddress(),
                student.getContact(),student.getDob(),student.getGender()));
    }

    @Override
    public boolean deleteStudent(StudentDTO student) throws Exception {
        return studentDAO.delete(new Student(student.getId(),student.getStudentName(),student.getAddress(),
                student.getContact(),student.getDob(),student.getGender()));
    }

    @Override
    public boolean updateStudent(StudentDTO student) throws Exception {
        return studentDAO.update(new Student(student.getId(),student.getStudentName(),student.getAddress(),
                student.getContact(),student.getDob(),student.getGender()));
    }

    @Override
    public StudentDTO getStudent(String id) throws Exception {
        Student one = studentDAO.getOne(id);
        return new StudentDTO(one.getId(),one.getStudentName(),one.getAddress(),one.getContact(),one.getDob(),one.getGender());
    }

    @Override
    public List<StudentDTO> getAllStudents() throws Exception {
        List<Student> studentList = studentDAO.getAll();

        List<StudentDTO> studentDTOList = new ArrayList<>();

        for (Student student : studentList) {
            studentDTOList.add(new StudentDTO(student.getId(),student.getStudentName(),student.getAddress(),student.getContact(),student.getDob(),student.getGender()));
        }
        return studentDTOList;    }
}
