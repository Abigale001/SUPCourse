package com.supinfo.supcourses.dao;

import com.supinfo.supcourses.entity.Student;
import javax.ejb.Local;
/**
 *
 * @author Yicong
 */
@Local
public interface StudentDao {
    Student addStudent(Student student);
    Student findStudent(String username, String password);
    Student findStudent(String username); 
    Student findStudentById(Long id);
}
