package com.supinfo.supcourses.service;

import com.supinfo.supcourses.dao.CertificateDao;
import com.supinfo.supcourses.entity.Certificate;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Yicong
 */
@Stateless
public class CertificateService { 
    @EJB
    CertificateDao certificateDao;
    
    public Certificate addCertificate(Certificate certificate) {
        return certificateDao.addCertificate(certificate);
    }
    public List<Certificate> listCertificatesByStudentId(Long id) {
        return certificateDao.listCertificatesByStudentId(id);
    }
    public Certificate findCertificateByCertificateId(Long id) {
        return certificateDao.findCertificateByCertificateId(id);    
    }
}
