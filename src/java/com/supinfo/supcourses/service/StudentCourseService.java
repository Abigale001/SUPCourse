package com.supinfo.supcourses.service;

import com.supinfo.supcourses.dao.StudentCourseDao;
import com.supinfo.supcourses.entity.Course;
import com.supinfo.supcourses.entity.Student;
import com.supinfo.supcourses.entity.StudentCourse;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Yicong
 */
@Stateless
public class StudentCourseService {  
    @EJB
    private StudentCourseDao studentCourseDao;
    
    public StudentCourse addStudent(Student student){
        return studentCourseDao.addStudent(student);
    }
    public StudentCourse findStudentCourseByStudentId(Long id){
        return studentCourseDao.findStudentCourseByStudentId(id);
    }
    public Course addCourseToStudent(Student student, Course course){
        return studentCourseDao.addCourseToStudent(student, course);
    }
    public Boolean isStudentHasThisCourse(Student student, Course course){
        return studentCourseDao.isStudentHasThisCourse(student, course);
    }
}
