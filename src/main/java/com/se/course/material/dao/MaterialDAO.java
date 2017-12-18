package com.se.course.material.dao;

//import packages
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.se.course.material.domain.Material;
import com.se.global.dao.FileDAO;
import com.se.global.domain.CourseKey;
import com.se.global.service.SqlService;

/**
 * @author Yusen
 * @version 1.0
 * @since 1.0
 */
@Repository
public class MaterialDAO extends FileDAO {
    private final String UPLOAD_SQL = "INSERT INTO material(" + SqlService.MATERIAL_FILE_ID + ") VALUES(?)";
    private final String REMOVE_SQL = "DELETE FROM material WHERE " + SqlService.MATERIAL_FILE_ID + " = ?";
    private final String GET_MATERIALS_SQL = "SELECT * FROM material,file WHERE material." + SqlService.MATERIAL_FILE_ID + " = file." +
            SqlService.FILE_ID + " and " + SqlService.courseKeyInWhereClause();

    /**
     * 上传资料
     *
     * @param material Material对象
     * @throws DataAccessException 数据库访问出错
     */
    public void upload(Material material) throws DataAccessException {
        int fileId = store(material);
        jdbcTemplate.update(UPLOAD_SQL, fileId);
    }

    /**
     * 删除资料
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
     * 获取资料列表
     *
     * @param courseKey 课程主键
     * @return 以Object的形式返回，之后需要进行类型转换
     * @throws DataAccessException 数据库访问出错
     */
    @Override
    public Object getFiles(CourseKey courseKey) throws DataAccessException {
        Object[] args = new Object[] {courseKey.getId(), courseKey.getSemester(), courseKey.getTime(), courseKey.getPlace()};
        return jdbcTemplate.query(GET_MATERIALS_SQL, args, new ResultSetExtractor<ArrayList<Material>>() {
            @Override
            public ArrayList<Material> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                ArrayList<Material> materials = new ArrayList<Material>();

                while (resultSet.next()) {
                    Material material = new Material(setFile(resultSet));
                    materials.add(material);
                }

                return materials;
            }
        });
    }
}
