package com.supinfo.supcourses.controller;

import com.supinfo.supcourses.entity.Student;
import com.supinfo.supcourses.service.StudentService;
import com.supinfo.supcourses.tohash.PasswordToHash;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Yicong
 */
@ManagedBean @SessionScoped
public class StudentRegisterController {
    @EJB
    private StudentService studentService;
    
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String message = "";

    public StudentService getStudentService() {
        return studentService;
    }

    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
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

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }   
    
    //register new student
    //only different username can be registered successfully
    public String register(){
        if(studentService.findStudent(username) == null){
            //hash the password
            String hashPassword = PasswordToHash.passwordToHash(password);
            Student student = new Student(username, hashPassword, firstname, lastname);
            studentService.addStudent(student);
            return "register_success";
        }
        else {
            username = "";
            password = "";
            firstname = "";
            lastname = "";
            message = "Please change a new username. This username has been applyed";
            return "";
        }   
    }
}
