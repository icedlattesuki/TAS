package com.se.user.info.dao;

//import packages
import com.se.global.service.SqlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.se.global.domain.User;
import com.se.user.info.domain.EditableUserInfo;

/**
 * @author Yusen
 * @version 1.0
 * @since 1.0
 */
@Repository
public class UserInfoDAO {
    private JdbcTemplate jdbcTemplate;
    private static final String STUDENT_SQL = "UPDATE student SET " + SqlService.STUDENT_SIGNATURE + " = ?, " + SqlService.STUDENT_PROFILE + " = ?, " + SqlService.STUDENT_IMAGE_LOCATION + " = ? WHERE " + SqlService.STUDENT_ID + " = ?";
    private static final String TEACHER_SQL = "UPDATE teacher SET " + SqlService.TEACHER_SIGNATURE + " = ?, " + SqlService.TEACHER_PROFILE + " = ?, " + SqlService.TEACHER_IMAGE_LOCATION + " = ? WHERE " + SqlService.TEACHER_ID + " = ?";

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * 更新用户个人信息
     * @param user User对象
     * @param editableUserInfo EditableUserInfo对象
     * @throws DataAccessException 数据库访问出错
     */
    public void updateInfo(User user, EditableUserInfo editableUserInfo) throws DataAccessException{
        String id = user.getId();
        String signature = editableUserInfo.getSignature();
        String profile = editableUserInfo.getProfile();
        String imageLocation = editableUserInfo.getImageLocation();
        Object[] args = new Object[]{signature, profile, imageLocation, id};

        if (user.getType() == 1) {
            jdbcTemplate.update(STUDENT_SQL, args);
        } else {
            jdbcTemplate.update(TEACHER_SQL, args);
        }
    }
}
