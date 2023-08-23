package untils;

import connection.OracleConnector;
import constant.SqlFileConstants;
import org.apache.commons.dbcp.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pojo.BaseInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FindBaseInfo {

    private final static Logger log = LoggerFactory.getLogger(OracleConnector.class);

    public static BaseInfo baseInfo;


    public static BaseInfo getSqlStream(Connection conn, String father, String prod_ord_nr, String fact, String t_date) {

        String sql = FileUtils.getSqlStream(FindBaseInfo.class, SqlFileConstants.BASE_INFO);

        try {
            PreparedStatement prepare = conn.prepareStatement(sql);
            prepare.setString(1,father);
            prepare.setString(2,fact);
            prepare.setString(3,prod_ord_nr);
            prepare.setString(4,t_date);
            // 获取基础数据结果集
            ResultSet resultSet = prepare.executeQuery();
            while (resultSet.next()){
                baseInfo = new BaseInfo(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            log.error("获取底层基础数据错误！");
        }

        return baseInfo;
    }
}
