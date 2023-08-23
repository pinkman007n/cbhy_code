package pojo;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BaseInfo {
    private String father;
    private String fact;
    private String prod_ord_nr;
    private String t_date;

    private String son;
    private String project_name;
    private String outputmount;
    private String real_expend_qty_weht;
    private String byprod_qty_weht;
    private String real_unit_expend;
    private String plan_price;
    private String real_price;
    private String real_mout;
    private String eaut_type;
    private String fact2;



    public BaseInfo(ResultSet resultSet) {
        try {
            this.father = resultSet.getString("father");
            this.fact = resultSet.getString("fact");
            this.prod_ord_nr = resultSet.getString("prod_ord_nr");
            this.t_date = resultSet.getString("t_date");

            this.son = resultSet.getString("son");
            this.project_name = resultSet.getString("project_name");
            this.outputmount = resultSet.getString("outputmount");
            this.real_expend_qty_weht = resultSet.getString("real_expend_qty_weht");
            this.byprod_qty_weht = resultSet.getString("byprod_qty_weht");
            this.real_unit_expend = resultSet.getString("real_unit_expend");
            this.plan_price = resultSet.getString("plan_price");
            this.real_price = resultSet.getString("real_price");
            this.real_mout = resultSet.getString("real_mout");
            this.eaut_type = resultSet.getString("eaut_type");
            this.fact2 = resultSet.getString("fact2");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getFather() {
        return father;
    }

    public String getFact() {
        return fact;
    }

    public String getProd_ord_nr() {
        return prod_ord_nr;
    }

    public String getT_date() {
        return t_date;
    }

    public String getSon() {
        return son;
    }

    public String getProject_name() {
        return project_name;
    }

    public String getOutputmount() {
        return outputmount;
    }

    public String getReal_expend_qty_weht() {
        return real_expend_qty_weht;
    }

    public String getByprod_qty_weht() {
        return byprod_qty_weht;
    }

    public String getReal_unit_expend() {
        return real_unit_expend;
    }

    public String getPlan_price() {
        return plan_price;
    }

    public String getReal_price() {
        return real_price;
    }

    public String getReal_mout() {
        return real_mout;
    }

    public String getEaut_type() {
        return eaut_type;
    }

    public String getFact2() {
        return fact2;
    }

    public void setFather(String father) {
        this.father = father;
    }

    public void setFact(String fact) {
        this.fact = fact;
    }

    public void setProd_ord_nr(String prod_ord_nr) {
        this.prod_ord_nr = prod_ord_nr;
    }

    public void setT_date(String t_date) {
        this.t_date = t_date;
    }

    public void setSon(String son) {
        this.son = son;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public void setOutputmount(String outputmount) {
        this.outputmount = outputmount;
    }

    public void setReal_expend_qty_weht(String real_expend_qty_weht) {
        this.real_expend_qty_weht = real_expend_qty_weht;
    }

    public void setByprod_qty_weht(String byprod_qty_weht) {
        this.byprod_qty_weht = byprod_qty_weht;
    }

    public void setReal_unit_expend(String real_unit_expend) {
        this.real_unit_expend = real_unit_expend;
    }

    public void setPlan_price(String plan_price) {
        this.plan_price = plan_price;
    }

    public void setReal_price(String real_price) {
        this.real_price = real_price;
    }

    public void setReal_mout(String real_mout) {
        this.real_mout = real_mout;
    }

    public void setEaut_type(String eaut_type) {
        this.eaut_type = eaut_type;
    }

    public void setFact2(String fact2) {
        this.fact2 = fact2;
    }
}
