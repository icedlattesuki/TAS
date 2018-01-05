package com.se.course.homework.dao;

import com.se.course.homework.domain.Attachment;
import com.se.global.dao.FileDAO;
import com.se.global.service.SqlService;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class AttachmentDAO extends FileDAO{
    private final String UPLOAD_SQL = "insert into attachment(" + SqlService.ATTACHMENT_FILE_ID + ", " + SqlService.ATTACHMENT_HOMEWORK_ID
            + ") values(?, ?)";
    private final String GET_HOMEWORK_ATTACHMENT_SQL = "select * from attachment, file where "
            + SqlService.ATTACHMENT_HOMEWORK_ID + " = ? and " + SqlService.ATTACHMENT_FILE_ID + " = " + SqlService.FILE_ID;
    private final String REMOVE_FILE_SQL = "delete from attachment where " + SqlService.ATTACHMENT_FILE_ID + " = ?";
    private final String REMOVE_ATTACHMENT_FILE_SQL = "DELETE t1, t2 FROM attachment AS t1 LEFT JOIN file AS t2 ON t1.file_id = t2.id" +
            " WHERE t1." + SqlService.ATTACHMENT_HOMEWORK_ID + " = ?";

    public void remove(int fileId, String userId) {
        if (isFileExist(fileId, userId)) {
            jdbcTemplate.update(REMOVE_FILE_SQL, fileId);
            super.remove(fileId, userId);
        }
    }

    public void upload(Attachment attachment, int homework_id) {
        int fileId = store(attachment);
        Object[] args = new Object[] {fileId, homework_id};
        jdbcTemplate.update(UPLOAD_SQL, args);
    }

    public Attachment getAttachments(int homework_id) {
        final Attachment attachment = new Attachment();
        jdbcTemplate.query(GET_HOMEWORK_ATTACHMENT_SQL, new Object[]{homework_id},
                new RowCallbackHandler() {
                    @Override
                    public void processRow(ResultSet rs) throws SQLException {
                        setFile(rs, attachment);
                        attachment.setFile_id(rs.getInt(SqlService.ATTACHMENT_FILE_ID));
                        attachment.setHomework_id(rs.getInt(SqlService.ATTACHMENT_HOMEWORK_ID));
                    }
                });
        if (attachment.getFile_id() > 0)
            return attachment;
        else return null;
    }

    public void removeByHomework(int homework_id) {
        jdbcTemplate.update(REMOVE_ATTACHMENT_FILE_SQL, homework_id);
    }
}

