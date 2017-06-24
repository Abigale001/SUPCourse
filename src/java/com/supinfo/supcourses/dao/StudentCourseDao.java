package com.supinfo.supcourses.dao;

import com.supinfo.supcourses.entity.Course;
import com.supinfo.supcourses.entity.Student;
import com.supinfo.supcourses.entity.StudentCourse;
import javax.ejb.Local;
/**
 *
 * @author Yicong
 */
@Local
public interface StudentCourseDao {
    StudentCourse addStudent(Student student);
    StudentCourse findStudentCourseByStudentId(Long id);
    Course addCourseToStudent(Student student, Course course);
    Boolean isStudentHasThisCourse(Student student, Course course);
}
