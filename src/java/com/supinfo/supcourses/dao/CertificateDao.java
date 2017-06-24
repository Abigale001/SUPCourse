package com.supinfo.supcourses.dao;

import com.supinfo.supcourses.entity.Certificate;
import com.supinfo.supcourses.entity.Course;
import com.supinfo.supcourses.entity.Student;
import java.util.List;
import javax.ejb.Local;
/**
 *
 * @author Yicong
 */
@Local
public interface CertificateDao {
    Certificate addCertificate(Certificate certificate);
    List<Certificate> listCertificatesByStudentId(Long id);
    Certificate findCertificateByCertificateId(Long id);
}
