package com.supinfo.supcourses.controller;

import com.supinfo.supcourses.dao.StudentDao;
import com.supinfo.supcourses.entity.Student;
import com.supinfo.supcourses.tohash.PasswordToHash;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Yicong
 */
@ManagedBean @SessionScoped
public class StudentLoginController {
    
    @EJB 
    private StudentDao studentDao;
    
    private String username;
    private String password;
    
    //the notification message
    private String message = "Please login.";
    private Student stu = null;
    

    public Student getStu() {
        return stu;
    }

    public void setStu(Student stu) {
        this.stu = stu;
    }

    
    public StudentDao getStudentDao() {
        return studentDao;
    }

    public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    //login successfully: go to listMyCourses page
    //login fail: wrong username and password, login again
    public String login(){
        String hashPassword = null;
        hashPassword = PasswordToHash.passwordToHash(password);
        if(studentDao.findStudent(username, hashPassword) != null) {
            message = "has login.";
            stu = studentDao.findStudent(username, hashPassword);
            return "login_success";
        }
        else {
            message = "Wrong username or password";
            return "";
        }
    }
    
    //before login, users can register
    public String register(){
        return "register";
    }
    
    //before login, anonymous can only see all courses
    public String toAllCourses(){
        return "index_goto_allCourses";
    }

    //after login, users can logout
    public void logout(){
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        session.invalidate();
    }

    //after login, users can go to and see all my courses
    public String toMyCourses(){
        return "index_goto_myCourses";
    }
    
    //after login, users can go to and print all my certificates
    public String toCertificate(){
        return "goto_printCertificate";
    }
}
