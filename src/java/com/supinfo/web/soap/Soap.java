package com.supinfo.web.soap;

import com.supinfo.supcourses.entity.Certificate;
import com.supinfo.supcourses.entity.Course;
import com.supinfo.supcourses.entity.Quiz;
import com.supinfo.supcourses.entity.Student;
import com.supinfo.supcourses.service.CertificateService;
import com.supinfo.supcourses.service.CoursesService;
import com.supinfo.supcourses.service.QuizService;
import com.supinfo.supcourses.service.StudentCourseService;
import com.supinfo.supcourses.service.StudentService;
import com.supinfo.supcourses.tohash.PasswordToHash;
import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 *
 * @author 五苦。
 */

@WebService
public class Soap {
    @EJB
    StudentService ss;
    
    @EJB
    CoursesService cs;
    
    @EJB
    StudentCourseService scs;
    
    @EJB
    QuizService qs;
    
    @EJB
    CertificateService certificates;
    
    //if the user has authenticated
    @WebMethod
    public boolean Authenticate(String username, String password){
        if(ss.findStudent(username, PasswordToHash.passwordToHash(password)) == null) {
            System.out.println("Wrong Username or Password");
            return false;
        }   
        System.out.println("You have been authenticated");
        return true;    
    }
    
    //get this user by username and password
    @WebMethod
    public Student getUser(String username, String password){
        if(Authenticate(username, password) == true)
        {
            return ss.findStudent(username, PasswordToHash.passwordToHash(password));
        }
        System.out.println("Don't have this user.");
        return null;
    }
    
    //list all courses
    @WebMethod
    public List<Course> listCourses(){
        return cs.listCourses();
    }
    
    //get course by courseId
    @WebMethod
    public Course getCourse(Long courseId){
        if(cs.findCourseById(courseId) == null) {
            System.out.println("Don't have this course");
        }
        return cs.findCourseById(courseId);    
    }
    
    //A student takes a course
    //if the student has passed the course, print the information.
    //else if the student never taken any course, add ths student to the StudentCourse link
    //else just add the course
    //after addding the course, we add a new quiz entity to prepare.
    @WebMethod
    public void takeACourse(String username, String password, String courseName){
        if(Authenticate(username, password))
        {
            Student student = ss.findStudent(username);
            try {
                Course course = cs.findCourseByCourseName(courseName);
                if(scs.isStudentHasThisCourse(student, course)){
                    System.out.println("This student already HAS THE COURSE");
                }
                else if(scs.findStudentCourseByStudentId(student.getId()) == null){
                    scs.addStudent(student);
                    scs.addCourseToStudent(student, course);
                    Quiz q = new Quiz(false, "It's the content of " + courseName + " quiz: ", false, student, course);
                    qs.addQuiz(q);
                }
                else{
                    scs.addCourseToStudent(student, course);
                    Quiz q = new Quiz(false, "It's the content of " + courseName + " quiz: ", false, student, course);
                    qs.addQuiz(q);
                }
            }catch(NullPointerException e){
                System.out.println("Don't have this course");
            }
        }
    }
    
    //pass the quiz and add a certificate
    @WebMethod
    public void passQuiz(String username, String password,String courseName){
        if(Authenticate(username, password))
        {
            Student student = ss.findStudent(username);
            Course course = cs.findCourseByCourseName(courseName);
            Quiz q = qs.findQuizByStudentAndCourse(student, course);
            qs.passQuiz(q.getId());    
            Certificate certificate = new Certificate(q, new Date());
            certificates.addCertificate(certificate);
        }        
    }
    
    @WebMethod
    public void printCertification(String username, String password, String courseName){
        Student student = ss.findStudent(username, PasswordToHash.passwordToHash(password));
        Course course = cs.findCourseByCourseName(courseName);
        if(qs.findQuizByStudentAndCourse(student, course) != null){
            Quiz quiz = qs.findQuizByStudentAndCourse(student, course);
            if(quiz.getPass()){
                System.out.println("Congratulations! " 
                + quiz.getStudent().getFirstname() 
                + " " 
                + quiz.getStudent().getLastname() 
                + " has passed the Course: " 
                + quiz.getCourse().getCourseName() 
                + ". Date: " 
                + new Date()
                + ".");
            }
        }
    } 
}
