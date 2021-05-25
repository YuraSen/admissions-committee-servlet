package com.senin.demo.listener;


import com.senin.demo.model.entity.Applicant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SessionListener implements HttpSessionListener {
    static final Logger LOG = LoggerFactory.getLogger(SessionListener.class);

    private static Map<String, String> applicantsInSessions = new ConcurrentHashMap<>();


    public static Map<String, String> getApplicantsInSessions() {
        return applicantsInSessions;
    }

    @Override
    public void sessionCreated(HttpSessionEvent arg0) {
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent arg0) {
        if (( arg0.getSession().getAttribute("applicant"))!=null){
            Applicant applicant = (Applicant) arg0.getSession().getAttribute("applicant");
            SessionListener.getApplicantsInSessions().remove(applicant.getUsername());
            LOG.info("User {} logout. Session destroyed", applicant.getUsername());
        }
    }


}
