package untils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileUtils {
    private final static Logger LOGGER = LoggerFactory.getLogger(FileUtils.class);

    public static String getSqlStream(Class c, String testSqlFile) {
        //读取sql文件
        BufferedReader bufferedReader = null;
        StringBuffer sql = new StringBuffer();
        try {
            InputStream is = c.getClassLoader().getResourceAsStream(testSqlFile);
            String s = "";
            bufferedReader = new BufferedReader(new InputStreamReader(is));
            while ((s = bufferedReader.readLine()) != null) {
                sql.append(s).append("\n");
            }
        } catch (Exception e) {
            LOGGER.error("获取路径失败！");
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (Exception e) {
                    LOGGER.error("流关闭失败！");
                }
            }
        }
        return sql.toString();
    }
}
