package com.supinfo.supcourses.service;

import com.supinfo.supcourses.dao.CoursesDao;
import com.supinfo.supcourses.entity.Course;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Yicong
 */
@Stateless
public class CoursesService{ 
    @EJB
    private CoursesDao coursesDao;

    public List<Course> listCourses() {
        return coursesDao.listCourses();
    }
    public Course addCourse(Course course){
        return coursesDao.addCourse(course);
    }    
    public Course findCourseById(Long id) {
        return coursesDao.findCourseById(id);
    }    
    public Course findCourseByCourseName(String courseName){
        return coursesDao.findCourseByCourseName(courseName);
    }    
}
