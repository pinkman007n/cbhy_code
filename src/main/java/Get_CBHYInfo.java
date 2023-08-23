import connection.OracleConnector;
import constant.SqlFileConstants;
import org.apache.flink.api.java.utils.ParameterTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pojo.BaseInfo;
import untils.FileUtils;
import untils.FindBaseInfo;
import untils.FindProduction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

public class Get_CBHYInfo {



    private final static Logger LOGGER = LoggerFactory.getLogger(Get_CBHYInfo.class);

    public static String fact  = "5002";
    public static String month = "202306";


    // 定义一个单耗数据
    public static Map<String,Double> consumption  = new HashMap<>();

    // 定义一个产量数据
    public static Double production = 1.0000;


    //

    public static void main(String[] args) throws Exception {
        LOGGER.info("成本还原处理逻辑启动");
        ParameterTool params = ParameterTool.fromArgs(args);
        //创建连接
        Connection conn = OracleConnector.getConnect(params);
        //调用sql文件
        String sql = FileUtils.getSqlStream(Get_CBHYInfo.class, SqlFileConstants.TEST_SQL_FILE);
        //执行sql
        PreparedStatement prepare = conn.prepareStatement(sql);
        for (int i = 2;i<=5;i++){

            prepare.setInt(1,i);
            ResultSet res = prepare.executeQuery();

            while (res.next()){

                //当i=2时获取需要还原产品的产量信息
                if(i==2)production= FindProduction.getOutPutStream(conn,res.getString("father"),res.getString("prod_ord_nr"));

                //定义一个叠加想乘的单耗变量
                Double hao = 1.00;

                String[] dep = res.getString("dep").split("-");

                for(int j = dep.length-1;j>=0;j--){
                        // 将需要还原产品的单号设置为1
                        consumption.put(dep[dep.length-1], 1.00);

                        // 每次处理上一层数据的单耗，为后面计算使用
                        if(consumption.get(dep[j])==null){
                            Double cption = FindProduction.getConsumptionStream(conn, dep[j + 1], dep[j]);
                            consumption.put(dep[j],cption);
                        }

                        //逐层对单耗数据进行叠加
                        hao = hao * consumption.get(dep[j]);

                        //针对最后的子节点数据进行还原逻辑计算基础物料的消耗结果
                        if(j==0){
                            String father = dep[j].split("/")[1];
                            String prod_ord_nr = dep[j].split("/")[1];

                            //返回结果模型数据，后续需要保存到map中在对得到的数据依据key进行聚合
                            BaseInfo sqlStream = FindBaseInfo.getSqlStream(conn, father, prod_ord_nr, fact, month);



                        }
                } // 循环结束tag



            } // 循环结束tag
        } // 循环结束tag

        for (Map.Entry<String, Double> m : consumption.entrySet()) {
//            System.out.println("key:" + m.getKey() + " value:" + m.getValue());
        }



        OracleConnector.closeDbPreparedStatement(prepare);
        //关闭连接
        OracleConnector.closeDbConnect(conn);
    }
}

