package com.se.info.personal.dao;

import com.se.info.personal.domain.PersonalInfoEditable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.se.domain.*;

@Repository
public class PersonalInfoDAO {
    private JdbcTemplate jdbcTemplate;
    static final String studentSQL = "update student set email = ?, signature = ?, profile = ?, image_position = ? where id = ?";
    static final String teacherSQL = "update teacher set email = ?, signature = ?, profile = ?, image_position = ? where id = ?";

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void personalInfoUpdate(User user, PersonalInfoEditable personalInfoEditable) throws DataAccessException{
        String id = user.getId();
        String email = personalInfoEditable.getEmail();
        String signature = personalInfoEditable.getSignature();
        String profile = personalInfoEditable.getProfile();
        String imageLocation = personalInfoEditable.getImageLocation();
        Object[] args = new Object[]{email, signature, profile, imageLocation, id};

        if (user.getType() == 1)
            jdbcTemplate.update(studentSQL, args);
        else
            jdbcTemplate.update(teacherSQL, args);
    }
}
