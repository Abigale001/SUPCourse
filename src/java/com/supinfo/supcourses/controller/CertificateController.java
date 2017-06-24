package com.supinfo.supcourses.controller;

import com.supinfo.supcourses.entity.Certificate;
import com.supinfo.supcourses.service.CertificateService;
import com.supinfo.supcourses.service.QuizService;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 *
 * @author Yicong
 */
@ManagedBean @SessionScoped
public class CertificateController {
    @EJB
    private CertificateService certificateService;
    
    @EJB
    private QuizService quizService;
    
    @ManagedProperty(value = "#{studentLoginController}")
    private StudentLoginController studentLoginController;
    
    @Resource(mappedName = "jms/SUPCoursesQueue")
    private Queue myQueue;
    
    @Resource(mappedName = "jms/SUPCoursesFactory")
    private ConnectionFactory myQueueFactory;
    
    private DataModel<Certificate> certificateDataModel;
    private String message;
    //the message will send to queue
    private String sendMessage;
    
    public CertificateService getCertificateService() {
        return certificateService;
    }

    public void setCertificateService(CertificateService certificateService) {
        this.certificateService = certificateService;
    }

    public QuizService getQuizService() {
        return quizService;
    }

    public void setQuizService(QuizService quizService) {
        this.quizService = quizService;
    }

    public StudentLoginController getStudentLoginController() {
        return studentLoginController;
    }

    public void setStudentLoginController(StudentLoginController studentLoginController) {
        this.studentLoginController = studentLoginController;
    }

    public DataModel<Certificate> getCertificateDataModel() {
        certificateDataModel = new ListDataModel<>(certificateService.listCertificatesByStudentId(studentLoginController.getStu().getId()));
        return certificateDataModel;
    }

    public void setCertificateDataModel(DataModel<Certificate> certificateDataModel) {
        this.certificateDataModel = certificateDataModel;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Queue getMyQueue() {
        return myQueue;
    }

    public void setMyQueue(Queue myQueue) {
        this.myQueue = myQueue;
    }

    public ConnectionFactory getMyQueueFactory() {
        return myQueueFactory;
    }

    public void setMyQueueFactory(ConnectionFactory myQueueFactory) {
        this.myQueueFactory = myQueueFactory;
    }

    public String getSendMessage() {
        return sendMessage;
    }

    public void setSendMessage(String sendMessage) {
        this.sendMessage = sendMessage;
    }
    
    public void print(Long id){
        System.out.println(certificateService.findCertificateByCertificateId(id).print());
        sendMessage = certificateService.findCertificateByCertificateId(id).print();
        FacesContext facesContext = FacesContext.getCurrentInstance();
        try {
            this.sendJMSMessageToMyQueue(sendMessage);
            FacesMessage facesMessage = new FacesMessage("Message sent: " + sendMessage);
            facesMessage.setSeverity(FacesMessage.SEVERITY_INFO);
            facesContext.addMessage(null, facesMessage);
        } catch (JMSException jmse) {
             FacesMessage facesMessage = new FacesMessage("Message NOT sent: " + sendMessage);
             facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
            facesContext.addMessage(null, facesMessage);
        }
        FacesContext.getCurrentInstance().addMessage(message, new FacesMessage("Certificate has been printed!"));
    }
    
    public Message createJMSMessageForjmsMyQueue(Session session, Object messageData) throws JMSException{
        TextMessage tm = session.createTextMessage();
        tm.setText(messageData.toString());
        return tm;
    }
    
    public void sendJMSMessageToMyQueue(Object messageData) throws JMSException{
        Connection connection = null;
        Session session = null;
        try{
            connection = myQueueFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer messageProducer = session.createProducer(myQueue);
            messageProducer.send(createJMSMessageForjmsMyQueue(session,messageData));
        } finally {
            if(session != null) {
                try {
                    session.close();
                } catch (JMSException e) {
                    
                }
            }
            if(connection != null){
                connection.close();
            }
        }
    }
    
    //go to my course page
    public String toMyCourse(){   
        return "certificate_goto_listMyCourses";
    }
    
}
