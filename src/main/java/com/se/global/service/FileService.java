package com.se.global.service;

//import packages
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import com.se.global.dao.FileDAO;

/**
 * @author Yusen
 * @version 1.0
 * @since 1.0
 */
@Service
public class FileService {
    private FileDAO fileDAO;
    private final Logger logger = LoggerFactory.getLogger("FileService.class");

    @Autowired
    public void setFileDAO(FileDAO fileDAO) { this.fileDAO = fileDAO; }

    /**
     * 存储文件
     *
     * @param file 文件
     * @param path 绝对路径(不包括文件名)
     * @return true表示存储成功，false表示存储失败
     */
    protected boolean store(MultipartFile file, String path) {
        if (file.isEmpty()) {
            return false;
        }

        File file1 = new File(path);

        if (!file1.exists()) {
            file1.mkdirs();
        }

        try {
            file.transferTo(new File(path + file.getOriginalFilename()));
            return true;
        } catch (Exception exception) {
            logger.error("store file fail! " + exception.getCause());
            return false;
        }
    }

    /**
     * 删除文件
     *
     * @param fileId 文件id
     * @param userId 用户id
     * @param dirPath 绝对路径(不包括文件名)
     * @param fileDAO FileDAO对象
     * @return true表示删除成功，false表示删除失败
     */
    protected boolean remove(int fileId, String userId, String dirPath, FileDAO fileDAO) {
        try {
            fileDAO.remove(fileId, userId);
            String fileName = fileDAO.getFileName(fileId);
            remove(dirPath + fileName);
            return true;
        } catch (Exception exception) {
            logger.error("remove fail! " + exception.getCause());
            return false;
        }
    }

    /**
     * 下载文件
     *
     * @param fileId 文件id
     * @param dirPath 绝对路径(不包括文件名)
     * @param response 响应
     */
    protected void download(int fileId, String dirPath, HttpServletResponse response) {
        String fileName = fileDAO.getFileName(fileId);

        if (fileName != null) {
            download(dirPath + fileName, response);
        }
    }

    /**
     * 获取文件列表
     *
     * @param courseId 课程id
     * @param fileDAO FileDAO对象
     * @return 以Object的形式返回，之后需要进行类型转换
     */
    protected Object getFiles(int courseId, FileDAO fileDAO) {
        try {
            return fileDAO.getFiles(courseId);
        } catch (Exception exception) {
            logger.error("getVideos fail! " + exception.getCause());
            return null;
        }
    }

    /**
     * 判断文件是否存在
     *
     * @param path 绝对路径(包括文件名)
     * @return true表示存在，false表示不存在
     */
    protected boolean isFileExist(String path) {
        File file = new File(path);
        return file.exists();
    }

    //根据绝对路径删除文件
    private void remove(String path) {
        File file = new File(path);
        file.delete();
    }

    //根据绝对路径下载文件
    private void download(String path, HttpServletResponse response) {
        try {
            File file = new File(path);
            InputStream inputStream = new FileInputStream(file);
            response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(file.getName(), "UTF-8"));
            response.setHeader("Content-length", String.valueOf(file.length()));
            IOUtils.copy(inputStream, response.getOutputStream());
            response.flushBuffer();
        } catch (Exception exception) {
            logger.error("download file fail! " + exception.getCause());
        }
    }
}
