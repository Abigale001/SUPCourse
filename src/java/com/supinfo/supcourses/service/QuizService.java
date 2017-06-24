package com.supinfo.supcourses.service;

import com.supinfo.supcourses.dao.QuizDao;
import com.supinfo.supcourses.entity.Course;
import com.supinfo.supcourses.entity.Quiz;
import com.supinfo.supcourses.entity.Student;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Yicong
 */
@Stateless
public class QuizService {  
    @EJB
    private QuizDao quizDao;
    
    public Quiz addQuiz(Quiz quiz) {
        return quizDao.addQuiz(quiz);
    }  
    public List<Quiz> findQuizByStudentId(Long id) {
        return quizDao.findQuizByStudentId(id);
    }
    public Quiz findQuizByQuizId(Long id) {
         return quizDao.findQuizByQuizId(id);
    }
    public int takeQuizChangeStatus(Student student, Course course){
        return quizDao.takeQuizChangeStatus(student, course);
    }
    public Quiz findQuizByStudentAndCourse(Student student, Course course){
        return quizDao.findQuizByStudentAndCourse(student, course);
    }
    public int passQuiz(Long id){
        return quizDao.passQuiz(id);
    }
}
