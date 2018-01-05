package com.se.course.homework.dao;

import com.se.course.homework.domain.UploadHomework;
import com.se.global.dao.FileDAO;
import com.se.global.service.SqlService;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

@Repository
public class UploadHomeworkDAO extends FileDAO {

    private final String REMOVE_FILE_SQL = "delete from upload_homework where " + SqlService.UPLOAD_HOMEWORK_FILE_ID + " = ?";
    private final String UPLOAD_SQL = "insert into upload_homework(" + SqlService.UPLOAD_HOMEWORK_STUDENT_ID + ", " + SqlService.UPLOAD_HOMEWORK_HOMEWORK_ID
            + ", " + SqlService.UPLOAD_HOMEWORK_GET_SCORE + ", " + SqlService.UPLOAD_HOMEWORK_HANDLE_DATE + ", " + SqlService.UPLOAD_HOMEWORK_COURSE_ID
            + ", " + SqlService.UPLOAD_HOMEWORK_FILE_ID + ") values(?, ?, ?, ?, ?, ?)";
    private final String GET_UPLOAD_FILE_SQL = "select " + SqlService.UPLOAD_HOMEWORK_FILE_ID + " from upload_homework where " +
            SqlService.UPLOAD_HOMEWORK_STUDENT_ID + " = ? and " + SqlService.UPLOAD_HOMEWORK_HOMEWORK_ID + " = ?";
    private final String GET_UPLOAD_HOMEWORK_SQL = "select * from upload_homework, file where " + SqlService.UPLOAD_HOMEWORK_STUDENT_ID
            + " = ? and " + SqlService.UPLOAD_HOMEWORK_HOMEWORK_ID + " = ? and " + "upload_homework." + SqlService.UPLOAD_HOMEWORK_FILE_ID +
            " = file." + SqlService.FILE_ID;
    private final String GET_UPLOAD_HOMEWORK_LIST_SQL = "select * from upload_homework, file where upload_homework." + SqlService.UPLOAD_HOMEWORK_COURSE_ID +
            " = ? and upload_homework." + SqlService.UPLOAD_HOMEWORK_HOMEWORK_ID + " = ? AND upload_homework." + SqlService.UPLOAD_HOMEWORK_FILE_ID + " = file." +
            SqlService.FILE_ID;
    private final String REMOVE_UPLOAD_FILE_SQL = "DELETE t1, t2 FROM upload_homework AS t1 LEFT JOIN file AS t2 ON " +
            "t1.upload_homework_file = t2.id WHERE t1." + SqlService.UPLOAD_HOMEWORK_HOMEWORK_ID + " = ?";
    private final String UPDATE_SCORE_SQL = "update upload_homework set " + SqlService.UPLOAD_HOMEWORK_GET_SCORE + " = ? " +
            "where " + SqlService.UPLOAD_HOMEWORK_ID + " = ?";

    public void remove(int fileId, String userId) {
        if (isFileExist(fileId, userId)) {
            jdbcTemplate.update(REMOVE_FILE_SQL, fileId);
            super.remove(fileId, userId);
        }
    }

    public void upload(UploadHomework uploadHomework, int homeworkId) {
        int fileId = store(uploadHomework);
        Object[] args = new Object[] {uploadHomework.getUserId(), homeworkId, -1, new Date(), uploadHomework.getCourseId(), fileId};
        jdbcTemplate.update(UPLOAD_SQL, args);
    }

    public boolean isUploadExist(String userId, int homeworkId) {
        int fileId = getFileId(userId, homeworkId);
        return fileId != -1;
    }

    public int getFileId(String userId, int homeworkId) {
        Object[] args = new Object[] {userId, homeworkId};
        int fileId;
        try {
            fileId = jdbcTemplate.queryForObject(GET_UPLOAD_FILE_SQL, args, Integer.class);
        } catch (Exception e) {
            fileId = -1;
        }
        return fileId;
    }

    public UploadHomework getUploadHomework(String userId, int homeworkId) {
        final UploadHomework uploadHomework = new UploadHomework();
        Object[] args = new Object[] {userId, homeworkId};
        jdbcTemplate.query(GET_UPLOAD_HOMEWORK_SQL, args, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                setFile(rs, uploadHomework);
                uploadHomework.setCourse_id(rs.getInt(SqlService.UPLOAD_HOMEWORK_COURSE_ID));
                uploadHomework.setGet_score(rs.getInt(SqlService.UPLOAD_HOMEWORK_GET_SCORE));
                uploadHomework.setHandle_date(rs.getDate(SqlService.UPLOAD_HOMEWORK_HANDLE_DATE));
                uploadHomework.setHomework_id(rs.getInt(SqlService.UPLOAD_HOMEWORK_HOMEWORK_ID));
                uploadHomework.setId(rs.getInt(SqlService.UPLOAD_HOMEWORK_ID));
                uploadHomework.setStudent_id(rs.getString(SqlService.UPLOAD_HOMEWORK_STUDENT_ID));
                uploadHomework.setUpload_homework_file(rs.getInt(SqlService.UPLOAD_HOMEWORK_FILE_ID));
            }
        });
        if (uploadHomework.getCourse_id() == 0)
            return null;
        else
            return uploadHomework;
    }

    public void removeByHomework(int homework_id) {
        jdbcTemplate.update(REMOVE_UPLOAD_FILE_SQL, homework_id);
    }

    public ArrayList<UploadHomework> getUploadHomeworkList(int courseId, int homeworkId) {
        Object[] args = new Object[] {courseId, homeworkId};
        return jdbcTemplate.query(GET_UPLOAD_HOMEWORK_LIST_SQL, args, new ResultSetExtractor<ArrayList<UploadHomework>>() {
            @Override
            public ArrayList<UploadHomework> extractData(ResultSet rs) throws SQLException, DataAccessException {
                ArrayList<UploadHomework> uploadHomeworks = new ArrayList<UploadHomework>();

                while (rs.next()) {
                    UploadHomework uploadHomework = new UploadHomework();
                    setFile(rs, uploadHomework);
                    uploadHomework.setId(rs.getInt(SqlService.UPLOAD_HOMEWORK_ID));
                    uploadHomework.setUpload_homework_file(rs.getInt(SqlService.UPLOAD_HOMEWORK_FILE_ID));
                    uploadHomework.setStudent_id(rs.getString(SqlService.UPLOAD_HOMEWORK_STUDENT_ID));
                    uploadHomework.setHomework_id(rs.getInt(SqlService.UPLOAD_HOMEWORK_HOMEWORK_ID));
                    uploadHomework.setHandle_date(rs.getDate(SqlService.UPLOAD_HOMEWORK_HANDLE_DATE));
                    uploadHomework.setGet_score(rs.getInt(SqlService.UPLOAD_HOMEWORK_GET_SCORE));
                    uploadHomework.setCourse_id(rs.getInt(SqlService.UPLOAD_HOMEWORK_COURSE_ID));
                    uploadHomeworks.add(uploadHomework);
                }
                return uploadHomeworks;
            }
        });
    }

    public void updateScore(int score, int id) {
        Object[] args = new Object[] {score, id};
        jdbcTemplate.update(UPDATE_SCORE_SQL,args);
    }
}
