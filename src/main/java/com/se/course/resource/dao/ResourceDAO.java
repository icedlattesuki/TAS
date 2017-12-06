package com.se.course.resource.dao;

//import packages
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import com.se.domain.CourseKey;
import com.se.course.resource.domain.Resource;

import javax.xml.crypto.Data;

/**
 * @author Yusen
 * @version 1.0
 * @since 1.0
 */
@Repository
public class ResourceDAO {
    private JdbcTemplate jdbcTemplate;
    private static final String STORE_RESOURCE_SQL = "insert into resource(type,name,location,size,date,course_id,semester,time,place,title,profile) values(?,?,?,?,?,?,?,?,?,?,?)";
    private static final String GET_RESOURCE_LIST_SQL = "select * from resource where course_id = ? and semester = ? and time = ? and place = ?";
    private static final String IS_RESOURCE_EXIST_SQL = "select * from resource where course_id = ? and semester = ? and time = ? and place = ? and name = ?";
    private static final String UPDATE_RESOURCE_SQL = "update resource set size = ? where course_id = ? and semester = ? and time = ? and place = ?";
    private static final String DELETE_RESOURCE_SQL = "delete from resource where course_id = ? and semester = ? and time = ? and place = ? and name = ?";

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate; }

    /**
     * 存储资源到数据库
     *
     * @param resource Resource对象
     * @throws DataAccessException 数据库访问出错
     */
    public void storeResource(Resource resource) throws DataAccessException {
        CourseKey courseKey = resource.getCourseKey();
        Object[] args = new Object[] {resource.getType(), resource.getName(), resource.getLocation(), resource.getSize(), resource.getDate(), courseKey.getId(), courseKey.getSemester(), courseKey.getTime(), courseKey.getPlace(), resource.getTitle(), resource.getProfile()};
        jdbcTemplate.update(STORE_RESOURCE_SQL, args);
    }

    /**
     * 获取资源列表
     *
     * @param courseKey 课程主键
     * @param type 0表示资料，1表示视频
     * @return 课程列表
     * @throws SQLException SQL查询出错
     * @throws DataAccessException 数据库访问出错
     */
    public ArrayList<Resource> getResourceList(final CourseKey courseKey, final int type) throws SQLException, DataAccessException {
        Object[] args = new Object[] {courseKey.getId(), courseKey.getSemester(), courseKey.getTime(), courseKey.getPlace()};
        return jdbcTemplate.query(GET_RESOURCE_LIST_SQL, args, new ResultSetExtractor<ArrayList<Resource>>() {
            @Override
            public ArrayList<Resource> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                ArrayList<Resource> resourcesList = new ArrayList<Resource>();

                while (resultSet.next()) {
                    if (type != resultSet.getInt("type")) {
                        continue;
                    }

                    Resource resource = new Resource();
                    resource.setName(resultSet.getString("name"));
                    resource.setLocation(resultSet.getString("location"));
                    resource.setSize(resultSet.getLong("size"));
                    resource.setTitle(resultSet.getString("title"));
                    resource.setProfile(resultSet.getString("profile"));
                    resource.setCourseKey(courseKey);
                    resource.setSize1(convertSize(resource.getSize()));
                    resourcesList.add(resource);
                }

                return resourcesList;
            }
        });
    }

    /**
     * 判断资源是否已经存在
     *
     * @param resource Resource对象
     * @return true表示已经存在，false表示不存在
     */
    public boolean isResourceExist(Resource resource) {
        CourseKey courseKey = resource.getCourseKey();
        Object[] args = new Object[] {courseKey.getId(), courseKey.getSemester(), courseKey.getTime(), courseKey.getPlace(), resource.getName()};
        return jdbcTemplate.query(IS_RESOURCE_EXIST_SQL, args, new ResultSetExtractor<Boolean>() {
            @Override
            public Boolean extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                return resultSet.next();
            }
        });
    }

    /**
     * 更新资源信息
     *
     * @param resource Resource对象
     * @throws DataAccessException 数据库访问出错
     */
    public void updateResource(Resource resource) throws DataAccessException {
        CourseKey courseKey = resource.getCourseKey();
        Object[] args = new Object[] {resource.getSize(), courseKey.getId(), courseKey.getSemester(), courseKey.getTime(), courseKey.getPlace()};
        jdbcTemplate.update(UPDATE_RESOURCE_SQL, args);
    }

    /**
     * 删除资源
     *
     * @param courseKey 课程主键
     * @param fileName 文件名
     * @throws DataAccessException 数据库访问出错
     */
    public void deleteResource(CourseKey courseKey, String fileName) throws DataAccessException {
        Object[] args = new Object[]{courseKey.getId(), courseKey.getSemester(), courseKey.getTime(), courseKey.getPlace(), fileName};
        jdbcTemplate.update(DELETE_RESOURCE_SQL, args);
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
