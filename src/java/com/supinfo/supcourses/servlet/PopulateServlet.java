package com.supinfo.supcourses.servlet;

import com.supinfo.supcourses.dao.CertificateDao;
import com.supinfo.supcourses.dao.CoursesDao;
import com.supinfo.supcourses.dao.QuizDao;
import com.supinfo.supcourses.dao.StudentCourseDao;
import com.supinfo.supcourses.dao.StudentDao;
import com.supinfo.supcourses.entity.Certificate;
import com.supinfo.supcourses.entity.Course;
import com.supinfo.supcourses.entity.Quiz;
import com.supinfo.supcourses.entity.Student;
import java.io.IOException;
import java.util.Date;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.supinfo.supcourses.tohash.PasswordToHash;

/**
 *
 * @author Yicong
 */
@WebServlet(urlPatterns = "/populate")
public class PopulateServlet extends HttpServlet{
    @EJB
    StudentDao studentDao;
    
    @EJB
    CoursesDao coursesDao;
    
    @EJB
    QuizDao quizDao;
    
    @EJB
    CertificateDao certificateDao;
    
    @EJB
    StudentCourseDao studentCourseDao;
    
    //just to create the tables
    //create the student: username(asdf) password(asdf)
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Course course = new Course("4JVA", "It's the 4JVA description.", true, new Date(), new Date(), "It's the 4JVA module list.");
        Course course1 = new Course("4VTZ","It's the 4VTZ description.", true, new Date(), new Date(), "It's the 4VTZ module list.");
        Course course2 = new Course("4MOS","It's the 4MOS description.", true, new Date(), new Date(), "It's the 4MOS module list.");
        Course course3 = new Course("4PJT","It's the 4PJT description.", true, new Date(), new Date(), "It's the 4PJT module list.");
        Course course4 = new Course("4VIP","It's the 4VIP description.", true, new Date(), new Date(), "It's the 4VIP module list.");

        coursesDao.addCourse(course);
        coursesDao.addCourse(course1);
        coursesDao.addCourse(course2);
        coursesDao.addCourse(course3);
        coursesDao.addCourse(course4);  
        
        String password = "asdf";        
        Student student = new Student("asdf", PasswordToHash.passwordToHash(password), "Yicong", "Li");   

        studentDao.addStudent(student);
        
        Course courseVTZ = coursesDao.findCourseByCourseName("4VTZ");
        Student studentLyc = studentDao.findStudent("asdf");
        studentCourseDao.addStudent(studentLyc);
        studentCourseDao.addCourseToStudent(studentLyc, courseVTZ);
        Quiz quiz = new Quiz(true, "It's the quiz content.", false,studentLyc, courseVTZ);
        quizDao.addQuiz(quiz);
        
        quizDao.takeQuizChangeStatus(studentLyc, courseVTZ);
     
        Quiz quizCertificate = quizDao.findQuizByQuizId(quiz.getId());
        Certificate certificate = new Certificate(quizCertificate, new Date());
        certificateDao.addCertificate(certificate);
    }
}
