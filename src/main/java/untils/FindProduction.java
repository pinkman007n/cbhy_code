package untils;

import connection.OracleConnector;
import constant.SqlFileConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pojo.BaseInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FindProduction {

    private final static Logger log = LoggerFactory.getLogger(OracleConnector.class);

    public static Double getOutPutStream(Connection conn, String finpro_code, String prod_ord_nr) {
        String sql = FileUtils.getSqlStream(FindProduction.class, SqlFileConstants.GET_PRODUCTOR);
        try {
            PreparedStatement pare = conn.prepareStatement(sql);
            pare.setString(1,finpro_code);
            pare.setString(2,prod_ord_nr);
            ResultSet result = pare.executeQuery();
            while (result.next())
            {
                return result.getDouble("output");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            log.info("产量数据获取异常！");
        }
        return null;
    }


    public static Double getConsumptionStream(Connection conn,String tag1,String tag2) {
        String sql = FileUtils.getSqlStream(FindProduction.class, SqlFileConstants.GET_CONSUMPTION);
        String[] tag1s = tag1.split("/");
        String[] tag2s = tag2.split("/");
        String finpro_code = tag1s[0];
        String prod_ord_nr = tag1s[1];
        String project = tag2s[0];
        String prod_ord_nr2 = tag2s[1];

        try {
            PreparedStatement pare = conn.prepareStatement(sql);
            pare.setString(1,finpro_code);
            pare.setString(2,prod_ord_nr);
            pare.setString(3,prod_ord_nr2);
            pare.setString(4,project);

            ResultSet res = pare.executeQuery();
            while (res.next()){
                return res.getDouble("real_unit_expend");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            log.info("获取单耗数据失败！");
        }
        return null;
    }
}
