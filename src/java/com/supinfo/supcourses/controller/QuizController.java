package com.supinfo.supcourses.controller;

import com.supinfo.supcourses.entity.Certificate;
import com.supinfo.supcourses.service.CertificateService;
import com.supinfo.supcourses.service.QuizService;
import java.util.Date;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Yicong
 */
@ManagedBean @SessionScoped
public class QuizController {
    
    @ManagedProperty(value = "#{listCoursesForStudentController}")
    private ListCoursesForStudentController listCoursesForStudentController;
    
    private String quizContent;
    private String answerFirst;
    private String answerSecond;
    private String answerThird;
    private String answerFourth;
    private String answerFifth;
    private int count = 0;

    @EJB
    private QuizService quizService;
    
    @EJB
    private CertificateService certificateService;

    public QuizService getQuizService() {
        return quizService;
    }

    public void setQuizService(QuizService quizService) {
        this.quizService = quizService;
    }  
    
    public String getAnswerFirst() {
        return answerFirst;
    }

    public void setAnswerFirst(String answerFirst) {
        this.answerFirst = answerFirst;
    }

    public String getAnswerSecond() {
        return answerSecond;
    }

    public void setAnswerSecond(String answerSecond) {
        this.answerSecond = answerSecond;
    }

    public String getAnswerThird() {
        return answerThird;
    }

    public void setAnswerThird(String answerThird) {
        this.answerThird = answerThird;
    }

    public String getAnswerFourth() {
        return answerFourth;
    }

    public void setAnswerFourth(String answerFourth) {
        this.answerFourth = answerFourth;
    }

    public String getAnswerFifth() {
        return answerFifth;
    }

    public void setAnswerFifth(String answerFifth) {
        this.answerFifth = answerFifth;
    }

    public ListCoursesForStudentController getListCoursesForStudentController() {
        return listCoursesForStudentController;
    }

    public void setListCoursesForStudentController(ListCoursesForStudentController listCoursesForStudentController) {
        this.listCoursesForStudentController = listCoursesForStudentController;
    }   

    public String getQuizContent() {
        return listCoursesForStudentController.getQuiz().getQuizContent();
    }

    public void setQuizContent(String quizContent) {
        this.quizContent = quizContent;
    }

    public CertificateService getCertificateService() {
        return certificateService;
    }

    public void setCertificateService(CertificateService certificateService) {
        this.certificateService = certificateService;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }    
    
    //When the student answers more than 2 questions, he/her can pass.
    public String handOn(){   
        //judge each answer is correct
        try{
            if(Integer.parseInt(answerFirst) == 2) {
                this.count++;
            }
        }catch(NumberFormatException e){  
        }
        try{
            if(Integer.parseInt(answerSecond) == 4) {
                this.count++;
            }
        }catch(NumberFormatException e){  
        }
        try{
            if(Integer.parseInt(answerThird) == 6) {
                this.count++;
            }
        }catch(NumberFormatException e){  
        }
        try{
            if(Integer.parseInt(answerFourth) == 8) {
                this.count++;
            }
        }catch(NumberFormatException e){   
        }
        try{
            if(Integer.parseInt(answerFifth) == 10) {
                this.count++;
            }
        }catch(NumberFormatException e){   
        }    
        //judge if student can pass
        if(this.count >= 3){
            System.out.println("pass");
            quizService.passQuiz(listCoursesForStudentController.getQuiz().getId());
            Certificate certificate = new Certificate(listCoursesForStudentController.getQuiz(),new Date());
            certificateService.addCertificate(certificate);
        }
        else {  
            System.out.println("not pass");
        }  
        //set all the information to null to avoid next time show the same information
        this.answerFirst = null;
        this.answerSecond = null;
        this.answerThird = null;
        this.answerFourth = null;
        this.answerFifth = null;
        this.count = 0;
        //the student will be back to listMyCourses page anyway.
        return "quiz_goto_listMyCourse";
    }  
}
