package com.supinfo.supcourses.service;

import com.supinfo.supcourses.dao.StudentDao;
import com.supinfo.supcourses.entity.Student;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Yicong
 */
@Stateless
public class StudentService {   
    @EJB
    StudentDao studentDao;
    
    public Student addStudent(Student student){
        return studentDao.addStudent(student);
    }
    
    public Student findStudent(String username, String password) {
        return studentDao.findStudent(username, password);
    }
    
    public Student findStudent(String username){
        return studentDao.findStudent(username);
    }
    public Student findStudentById(Long id){
        return studentDao.findStudentById(id);
    }
}
