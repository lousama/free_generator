package com.lousama.generator.jdbc;


import com.lousama.generator.model.Column;
import com.lousama.generator.model.Table;
import com.lousama.generator.util.ResourceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by lousama on 5/15/16
 *
 * @author lousama<me@lousama.com>
 */
public class MyJdbc {
    private static Logger logger = LoggerFactory.getLogger(MyJdbc.class);

    private static final String SQL_PREFIX = "SELECT * FROM ";
    private static String sqlSuffix = "";

    private static String schema = ResourceUtil.getString("jdbc_username").toUpperCase();
    private static String db = ResourceUtil.getString("jdbc_database");
    private static String host = ResourceUtil.getString("jdbc_url");
    private static String user = ResourceUtil.getString("jdbc_username");
    private static String password = ResourceUtil.getString("jdbc_password");
    private static String tables = ResourceUtil.getString("jdbc_table");
    private static String driver = "";
    private static String url = "";

    private static Connection conn = null;

    static {
        if ("oracle".equals(db.toLowerCase())){
            driver = "oracle.jdbc.driver.OracleDriver";
            url = "jdbc:oracle:thin:@" + host.replaceAll("/",":");
            sqlSuffix = " WHERE ROWNUM = 1 ";
        } else if ("mysql".equals(db)){
            driver = "com.mysql.jdbc.Driver";
            url = "jdbc:mysql://" + host;
            sqlSuffix =  " LIMIT 1";
            schema = host.split("/")[1];
        }else{
            throw new NoSuchElementException("config error:[jdbc_database] must be oracle or mysql!");
        }
    }

    /**
     * get db a reused connection
     * @return
     */
    private static Connection getConnection(){
        try {
            if(conn == null){
                Class.forName(driver);
                conn = DriverManager.getConnection(url,user,password);
            }
        } catch (Exception e) {
            logger.error("error:[get connection error]-",e);
        }
        return conn;
    }

    /**
     * get {@link Table} list from db <p>
     * use "select * from tb limit 1" query ResultSet,suggest better not connect product enviroment <p>
     * {@link DatabaseMetaData}.getColumns() is so bad so had not used;
     * @return tableList
     * @throws Exception
     */
    public static List<Table> getTableList() throws Exception{
        Statement st;
        ResultSet rs;
        conn = getConnection();
        List<Table> tableList =  getSqlList();
        if(tableList != null && tableList.size() != 0){
            for (Table table : tableList) {
                st = conn.createStatement();
                rs = st.executeQuery(table.getSql());
                ResultSetMetaData meta = rs.getMetaData();
                table.setColumnList(getColList(meta));
                rs.close();
                st.close();
            }
        }else {
            logger.info("config info:[tables] is null,get all tables in database");
            DatabaseMetaData metaData = conn.getMetaData();
            ResultSet tableRet = metaData.getTables(null,schema,"%",new String[]{"TABLE"});
            StringBuilder builder = new StringBuilder();
            while (tableRet.next()){
                builder.append(tableRet.getString("TABLE_NAME") + ",");
            }
            tables = builder.toString();
            getTableList();
        }
        conn.close();
        return tableList;
    }

    /**
     * get query sql by table name,wipe off the excess comma
     * @return
     */
    private static List<Table> getSqlList (){
        String[] tableArray = tables.split(",");
        List<Table> list = new ArrayList<Table>();
        for (String tbName : tableArray){
            if(tbName != null && !"".equals(tbName) && !",".equals(tbName)){
                Table table = new Table();
                table.setName(tbName);
                table.setSql(SQL_PREFIX + tbName.toUpperCase() + sqlSuffix);
                list.add(table);
            }
        }
        return list;
    }

    /**
     * get {@link Column} list from {@link ResultSetMetaData}
     * @param meta
     * @return colList
     * @throws Exception
     */
    private static List<Column> getColList(ResultSetMetaData meta) throws Exception{
        List<Column> colList = new ArrayList<Column>();
        for(int i = 1;i<=meta.getColumnCount();i++){
            Column column = new Column();
            column.setColName(meta.getColumnName(i).toLowerCase());
            column.setTypeName(meta.getColumnTypeName(i));
            column.setColSize(meta.getColumnDisplaySize(i));
            column.setScale(meta.getScale(i));
            colList.add(column);
        }
        return colList;
    }

}
