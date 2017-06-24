package com.supinfo.supcourses.controller;

import com.supinfo.supcourses.entity.Course;
import com.supinfo.supcourses.entity.Quiz;
import com.supinfo.supcourses.service.CoursesService;
import com.supinfo.supcourses.service.QuizService;
import com.supinfo.supcourses.service.StudentCourseService;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.servlet.http.HttpSession;
/**
 *
 * @author Yicong
 */
@ManagedBean @SessionScoped
public class ListCoursesForStudentController {
    
    @EJB
    private CoursesService coursesService;
    
    @EJB
    private StudentCourseService studentCourseService;
    
    @EJB
    private QuizService quizService;
    
    private DataModel<Course> courseDataModel = null;
    
    //tell student if he/her has taken this course
    private String message = "";
    //keep the course, use it later
    private Course course;
    //keep the quiz, use it later
    private Quiz quiz;
    
    @ManagedProperty(value = "#{studentLoginController}")
    private StudentLoginController studentLoginController;

    public CoursesService getCoursesService() {
        return coursesService;
    }

    public void setCoursesService(CoursesService coursesService) {
        this.coursesService = coursesService;
    }

    //display student's courses
    public DataModel<Course> getCourseDataModel() {
        try {
            courseDataModel = new ListDataModel<>(studentCourseService.findStudentCourseByStudentId(studentLoginController.getStu().getId()).getCourses());
            return courseDataModel;
        }catch(NullPointerException e){
            System.out.println("nothing");
        }
        return null;  
    }

    public void setCourseDataModel(DataModel<Course> courseDataModel) {
        this.courseDataModel = courseDataModel;
    }

    public StudentLoginController getStudentLoginController() {
        return studentLoginController;
    }

    public void setStudentLoginController(StudentLoginController studentLoginController) {
        this.studentLoginController = studentLoginController;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public StudentCourseService getStudentCourseService() {
        return studentCourseService;
    }

    public void setStudentCourseService(StudentCourseService studentCourseService) {
        this.studentCourseService = studentCourseService;
    }

    public QuizService getQuizService() {
        return quizService;
    }

    public void setQuizService(QuizService quizService) {
        this.quizService = quizService;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String backhome(){
        return "back_home";
    }
    
    //student logout
    public String logout(){
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        session.invalidate();
        return "back_home";
    }
    
    //go to all courses page
    public String listAllCourses(){
        return "listCoursesForStudentController_goto_listAllCourses";
    }
    
    //need to check if the student has passed the quiz
    //need to check if the student 
    public String takeQuiz(Long id){
        course = coursesService.findCourseById(id);
        quiz = quizService.findQuizByStudentAndCourse(studentLoginController.getStu(), course);
        if(quiz.getPass() == true){
            FacesContext.getCurrentInstance().addMessage(message, new FacesMessage("You have passed this quiz"));
            return "";
        }
        else if(quiz.getTakeQuiz() == true){
            return "goto_quiz_page";
        }
        else {
            quizService.takeQuizChangeStatus(studentLoginController.getStu(), course);
            return "goto_quiz_page";
        }
    }
        
    public String printCertificate(){
        return "goto_printCertificate";
    }
}
