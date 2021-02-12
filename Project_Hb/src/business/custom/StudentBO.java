package business.custom;

import business.SuperBO;
import dto.StudentDTO;

import java.util.List;

public interface StudentBO extends SuperBO {
    public boolean addStudent(StudentDTO student)throws Exception;

    public boolean deleteStudent(StudentDTO student)throws Exception;

    public boolean updateStudent(StudentDTO student)throws Exception;

    public StudentDTO getStudent(String id)throws Exception;

    public List<StudentDTO> getAllStudents()throws Exception;
}
