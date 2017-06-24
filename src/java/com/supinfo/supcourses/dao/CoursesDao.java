package com.supinfo.supcourses.dao;

import com.supinfo.supcourses.entity.Course;
import java.util.List;
import javax.ejb.Local;
/**
 *
 * @author Yicong
 */
@Local
public interface CoursesDao {
    List<Course> listCourses();
    Course findCourseById(Long id);
    Course addCourse(Course course);
    Course findCourseByCourseName(String courseName);
}
