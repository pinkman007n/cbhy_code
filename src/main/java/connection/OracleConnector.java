package connection;

import constant.Constants;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.flink.api.java.utils.ParameterTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OracleConnector {

    private final static Logger log = LoggerFactory.getLogger(OracleConnector.class);

    private static BasicDataSource ds = null;

    /*
     * 获取全局唯一的BasicDataSource实例
     *
     * */
    public static synchronized BasicDataSource getBasicDataSource(ParameterTool parameterTool) {

        try {
            if (ds == null) {
                ds = new BasicDataSource();
                String url = parameterTool.get(Constants.ORACLE_URL_KEY, Constants.ORACLE_URL_C);
                ds.setDriverClassName(parameterTool.get(Constants.ORACLE_DRIVER_KEY, Constants.ORACLE_DRIVER));
                ds.setUrl(url);
                ds.setUsername(parameterTool.get(Constants.ORACLE_USER_KEY, Constants.ORACLE_USER_C));
                ds.setPassword(parameterTool.get(Constants.ORACLE_PASSWORD_KEY, Constants.ORACLE_PASSWORD_C));
                ds.setInitialSize(parameterTool.getInt(Constants.ORACLE_INITIAL_SIZE_KEY, Constants.ORACLE_INITIAL_SIZE));
                ds.setMaxActive(parameterTool.getInt(Constants.ORACLE_MAX_ACTIVE_KEY, Constants.ORACLE_MAX_ACTIVE));
                log.info("oracle url:{}", url);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }

    public static void closeDbConnect(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public static void closeDbPreparedStatement(PreparedStatement ps) {
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }


    public static Connection getConnect(ParameterTool parameterTool) {
        BasicDataSource basicDataSource = getBasicDataSource(parameterTool);
        Connection connection = null;
        try {
            connection = basicDataSource.getConnection();
        } catch (SQLException e) {
            log.info("oracle数据库连接失败！");
            e.printStackTrace();
        }
        return connection;
    }

}
