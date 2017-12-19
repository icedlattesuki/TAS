package com.se.course.resource.video.dao;

//import packages
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.se.course.resource.video.domain.Video;
import com.se.global.dao.FileDAO;
import com.se.global.service.SqlService;

/**
 * @author Yusen
 * @version 1.0
 * @since 1.0
 */
@Repository
public class VideoDAO extends FileDAO {
    private final String UPLOAD_SQL = "INSERT INTO video(" + SqlService.VIDEO_FILE_ID + "," + SqlService.VIDEO_TITLE + "," +
            SqlService.VIDEO_PROFILE + ") VALUES(?,?,?)";
    private final String REMOVE_SQL = "DELETE FROM video WHERE " + SqlService.VIDEO_FILE_ID + " = ?";
    private final String GET_VIDEOS_SQL = "SELECT * FROM video,file WHERE video." + SqlService.VIDEO_FILE_ID + " = file." +
            SqlService.FILE_ID + " and " + SqlService.FILE_COURSE_ID + " = ?";

    /**
     * 上传视频
     *
     * @param video Video对象
     * @throws DataAccessException 数据库访问出错
     */
    public void upload(Video video) throws DataAccessException {
        int fileId = store(video);
        jdbcTemplate.update(UPLOAD_SQL, fileId, video.getTitle(), video.getProfile());
    }

    /**
     * 删除视频
     *
     * @param fileId 文件id
     * @param userId 用户id
     * @throws DataAccessException 数据库访问出错
     */
    @Override
    public void remove(int fileId, String userId) throws DataAccessException {
        if (isFileExist(fileId, userId)) {
            jdbcTemplate.update(REMOVE_SQL, fileId);
            super.remove(fileId, userId);
        }
    }

    /**
     * 获取视频列表
     *
     * @param courseId 课程id
     * @return 以Object的形式返回，之后需要进行类型转换
     * @throws DataAccessException 数据库访问出错
     */
    @Override
    public Object getFiles(int courseId) throws DataAccessException {
        return jdbcTemplate.query(GET_VIDEOS_SQL, new Object[] {courseId}, new ResultSetExtractor<ArrayList<Video>>() {
            @Override
            public ArrayList<Video> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                ArrayList<Video> videos = new ArrayList<Video>();

                while (resultSet.next()) {
                    Video video = new Video(setFile(resultSet));
                    video.setTitle(resultSet.getString(SqlService.VIDEO_TITLE));
                    video.setProfile(resultSet.getString(SqlService.VIDEO_PROFILE));
                    videos.add(video);
                }

                return videos;
            }
        });
    }
}
