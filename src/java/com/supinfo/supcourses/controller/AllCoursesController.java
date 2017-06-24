package com.supinfo.supcourses.controller;

import com.supinfo.supcourses.entity.Course;
import com.supinfo.supcourses.entity.Quiz;
import com.supinfo.supcourses.entity.Student;
import com.supinfo.supcourses.service.CoursesService;
import com.supinfo.supcourses.service.QuizService;
import com.supinfo.supcourses.service.StudentCourseService;
import com.supinfo.supcourses.service.StudentService;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
/**
 *
 * @author Yicong
 */
@ManagedBean @SessionScoped
public class AllCoursesController {
    
    @EJB
    private CoursesService coursesService;
    
    @EJB
    private StudentService studentService;
    
    @EJB
    private StudentCourseService studentCourseService;
    
    @EJB
    private QuizService quizService;
    
    @ManagedProperty(value = "#{studentLoginController}")
    private StudentLoginController studentLoginController;
    
    private DataModel<Course> courseDataModel;
    private Quiz quiz;
    private String message = ""; 
    
    public StudentLoginController getStudentLoginController() {
        return studentLoginController;
    }

    public void setStudentLoginController(StudentLoginController studentLoginController) {
        this.studentLoginController = studentLoginController;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }
   
    public CoursesService getCoursesService() {
        return coursesService;
    }

    public void setCoursesService(CoursesService coursesService) {
        this.coursesService = coursesService;
    }

    public DataModel<Course> getCourseDataModel() {
        courseDataModel = new ListDataModel<>(coursesService.listCourses());
        return courseDataModel;
    }

    public void setCourseDataModel(DataModel<Course> courseDataModel) {
        this.courseDataModel = courseDataModel;
    }

    public StudentService getStudentService() {
        return studentService;
    }

    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    //go back home
    public String backhome(){
        return "back_home";
    }
    
    //check if this student has a StudentCourse link in StudentCourse entity
    //check if this student already has taken this course
    public String takeCourse(Long id){
        Course course = coursesService.findCourseById(id);
        Student student = studentLoginController.getStu();
        System.out.println(course.toString());
        System.out.println(student.toString());
        if(studentCourseService.findStudentCourseByStudentId(student.getId()) == null){
            studentCourseService.addStudent(student);
            studentCourseService.addCourseToStudent(student, course);           
            String courseName = coursesService.findCourseById(id).getCourseName();
            quiz = new Quiz(false, "It's the content of " + courseName + " quiz: ", false, studentLoginController.getStu(), course);
            quizService.addQuiz(quiz);
            return "all_courses_goto_listMyCourses";
        }
        else if(studentCourseService.isStudentHasThisCourse(student, course)) {
            FacesContext.getCurrentInstance().addMessage(message, new FacesMessage("You have taken this course!"));
            return "";
        }
        else {
            studentCourseService.addCourseToStudent(student, course);
            String courseName = coursesService.findCourseById(id).getCourseName();
            quiz = new Quiz(false, "It's the content of " + courseName + " quiz: ", false, studentLoginController.getStu(), course);
            quizService.addQuiz(quiz);
            return "all_courses_goto_listMyCourses";
        }       
    }
}
