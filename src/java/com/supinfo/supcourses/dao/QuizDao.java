package com.supinfo.supcourses.dao;

import com.supinfo.supcourses.entity.Course;
import com.supinfo.supcourses.entity.Quiz;
import com.supinfo.supcourses.entity.Student;
import java.util.List;
import javax.ejb.Local;
/**
 *
 * @author Yicong
 */
@Local
public interface QuizDao {
    Quiz addQuiz(Quiz quiz);
    List<Quiz> findQuizByStudentId(Long id);
    Quiz findQuizByQuizId(Long id);
    int takeQuizChangeStatus(Student student, Course course);
    Quiz findQuizByStudentAndCourse(Student student, Course course);
    int passQuiz(Long id);
}
