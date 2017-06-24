package com.supinfo.supcourses.dao.jpa;

import com.supinfo.supcourses.dao.QuizDao;
import com.supinfo.supcourses.entity.Course;
import com.supinfo.supcourses.entity.Quiz;
import com.supinfo.supcourses.entity.Quiz_;
import com.supinfo.supcourses.entity.Student;
import com.supinfo.supcourses.entity.Student_;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author Yicong
 */
@Stateless
public class JpaQuizDao implements QuizDao{

    @PersistenceContext
    private EntityManager em;
    
    //add quiz
    @Override
    public Quiz addQuiz(Quiz quiz) {
        em.persist(quiz);
        return quiz;
    }

    //find quiz by studentId
    @Override
    public List<Quiz> findQuizByStudentId(Long id) {
        /*
        //first version using JPQL
        Query query = em.createQuery("SELECT q FROM Quiz q WHERE q.student.id = :id");
        query.setParameter("id", id);
        return query.getResultList();
        */
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Quiz> query = cb.createQuery(Quiz.class);
        Root<Quiz> quiz = query.from(Quiz.class);
        List<Predicate> p=new ArrayList<>();
        p.add(cb.equal(quiz.get(Quiz_.student).get(Student_.id),id));
        query.where(p.toArray(new Predicate[p.size()]));
        return em.createQuery(query).getResultList();
    }

    //find quiz by quizId
    @Override
    public Quiz findQuizByQuizId(Long id) {
        /*
        //first version using JPQL
        Query query = em.createQuery("SELECT q FROM Quiz q WHERE q.id = :id");
        query.setParameter("id", id);
        return (Quiz) query.getSingleResult();
        */
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Quiz> query = cb.createQuery(Quiz.class);
        Root<Quiz> quiz = query.from(Quiz.class);
        List<Predicate> p=new ArrayList<>();
        p.add(cb.equal(quiz.get(Quiz_.id),id));
        query.where(p.toArray(new Predicate[p.size()]));
        return (Quiz)em.createQuery(query).getResultList();
    }

    //take specific quiz and update takeQuiz status in Quiz entity
    @Override
    public int takeQuizChangeStatus(Student student, Course course) {
        Query query = em.createQuery("UPDATE Quiz q SET q.takeQuiz = TRUE WHERE q.student = :student AND q.course = :course");
        query.setParameter("student", student);
        query.setParameter("course", course);
        return query.executeUpdate();
        
        
        
    }

    //find quiz by student and course
    @Override
    public Quiz findQuizByStudentAndCourse(Student student, Course course) {
       /* Query query = em.createQuery("SELECT q FROM Quiz q WHERE q.student = :student AND q.course = :course");
        query.setParameter("student", student);
        query.setParameter("course", course);
        try {
            return (Quiz) query.getSingleResult();
        }catch(Exception e) {  
        }
        return null;*/
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Quiz> query = cb.createQuery(Quiz.class);
        Root<Quiz> quiz = query.from(Quiz.class);
         List<Predicate> p=new ArrayList<>();
        p.add(cb.equal(quiz.get(Quiz_.student),student));
        p.add(cb.equal(quiz.get(Quiz_.course),course));
        query.where(p.toArray(new Predicate[p.size()]));
        return (Quiz)em.createQuery(query).getResultList();
    }

    //update the passQuiz status in Quiz entity
    @Override
    public int passQuiz(Long id) {
        Query query = em.createQuery("UPDATE Quiz q SET q.pass = TRUE WHERE q.id = :id");
        query.setParameter("id", id);
        return query.executeUpdate();
    }
}
