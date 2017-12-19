package com.se.global.dao;

//import packages
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import com.se.global.service.SqlService;
import com.se.global.domain.File;

/**
 * @author Yusen
 * @version 1.0
 * @since 1.0
 */
@Repository
public class FileDAO {
    protected JdbcTemplate jdbcTemplate;
    private final String STORE_SQL = "INSERT INTO file(" + SqlService.FILE_NAME + "," + SqlService.FILE_LOCATION + "," +
            SqlService.FILE_SIZE + "," + SqlService.FILE_DATE + "," + SqlService.FILE_COURSE_ID + "," + SqlService.FILE_USER_ID + ") VALUES(?,?,?,?,?,?)";
    private final String REMOVE_SQL = "DELETE FROM file WHERE " + SqlService.FILE_ID + " = ?";
    private final String GET_MAX_FILE_ID_SQL = "SELECT MAX(" + SqlService.FILE_ID + ") AS " + SqlService.FILE_ID + " FROM file";
    private final String GET_FILE_NAME_SQL = "SELECT * FROM file WHERE " + SqlService.FILE_ID + " = ?";
    private final String IS_FILE_EXIST_SQL = "SELECT * FROM file WHERE " + SqlService.FILE_ID + " = ? and " + SqlService.FILE_USER_ID + " = ?";
    private final String GET_FILE_ID_SQL = "SELECT * FROM file WHERE " + SqlService.FILE_LOCATION + " = ?";
    private final Logger logger = LoggerFactory.getLogger("FileDAO.class");

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate; }

    /**
     * 删除文件
     *
     * @param fileId 文件id
     * @param userId 用户id
     * @throws DataAccessException 数据库访问出错
     */
    public void remove(int fileId, String userId) throws DataAccessException {
        jdbcTemplate.update(REMOVE_SQL, fileId);
    }

    /**
     * 获取文件列表
     *
     * @param courseId 课程id
     * @return 以Object形式返回，之后需要进行类型转换
     */
    public Object getFiles(int courseId) {
        return null;
    }

    /**
     * 获取文件名
     *
     * @param fileId 文件id
     * @return 文件id不存在则返回null，否则返回对应的文件名
     */
    public String getFileName(int fileId) {
        try {
            return jdbcTemplate.query(GET_FILE_NAME_SQL, new Object[]{fileId}, new ResultSetExtractor<String>() {
                @Override
                public String extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                    if (resultSet.next()) {
                        return resultSet.getString(SqlService.FILE_NAME);
                    }

                    return null;
                }
            });
        } catch (Exception exception) {
            logger.error("getFileName fail! " + exception.getCause());
            return null;
        }
    }

    /**
     * 获取文件id
     *
     * @param relativeFilePath 文件的相对路径(如/video/.../hello.MP4)
     * @return 文件不存在则返回-1，否则返回对应的文件id
     */
    public int getFileId(String relativeFilePath) {
        try {
            return jdbcTemplate.query(GET_FILE_ID_SQL, new Object[]{relativeFilePath}, new ResultSetExtractor<Integer>() {
                @Override
                public Integer extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                    if (resultSet.next()) {
                        return resultSet.getInt(SqlService.FILE_ID);
                    } else {
                        return -1;
                    }
                }
            });
        } catch (Exception exception) {
            logger.error("getFileId fail! " + exception.getCause());
            return -1;
        }
    }

    /**
     * 设置File对象
     *
     * @param resultSet ResultSet对象
     * @return File对象
     */
    protected File setFile(ResultSet resultSet) {
        try {
            File file = new File();
            file.setId(resultSet.getInt(SqlService.FILE_ID));
            file.setName(resultSet.getString(SqlService.FILE_NAME));
            file.setLocation(resultSet.getString(SqlService.FILE_LOCATION));
            file.setSize(resultSet.getInt(SqlService.FILE_SIZE));
            file.setSize1(convertSize(file.getSize()));
            file.setDate(resultSet.getDate(SqlService.FILE_DATE));
            file.setUserId(resultSet.getString(SqlService.FILE_USER_ID));
            return file;
        } catch (Exception exception) {
            logger.error("setFile fail! " + exception.getCause());
            return new File();
        }
    }

    /**
     * 存储File对象
     *
     * @param file File对象
     * @return 文件id
     * @throws DataAccessException 数据库访问出错
     */
    protected int store(File file) throws DataAccessException {
        jdbcTemplate.update(STORE_SQL, file.getName(), file.getLocation(), file.getSize(), file.getDate(), file.getCourseId(), file.getUserId());
        return jdbcTemplate.query(GET_MAX_FILE_ID_SQL, new ResultSetExtractor<Integer>() {
            @Override
            public Integer extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                resultSet.next();
                return resultSet.getInt(SqlService.FILE_ID);
            }
        });
    }

    /**
     * 判断文件是否存在
     *
     * @param fileId 文件id
     * @param userId 用户id
     * @return true表示存在，false表示不存在
     */
    protected boolean isFileExist(int fileId, String userId) {
        try {
            return jdbcTemplate.query(IS_FILE_EXIST_SQL, new Object[]{fileId, userId}, new ResultSetExtractor<Boolean>() {
                @Override
                public Boolean extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                    return resultSet.next();
                }
            });
        } catch (Exception exception) {
            logger.error("isFileExist fail! " + exception.getCause());
            return false;
        }
    }
    //将以B为单位的文件大小转为以B、KB、MB、GB为单位的文件大小
    private String convertSize(long size) {
        String[] arr = new String[] {"B", "KB", "MB", "GB"};
        int index = 0;
        long remain = 0;

        while (size / 1024 > 0) {
            index++;
            remain = size % 1024;
            size /= 1024;
        }

        float res = size + (float)remain / 1024;
        DecimalFormat format = new DecimalFormat("##0.00");
        return format.format(res) + arr[index];
    }
}
