package com.lousama.generator.model;

import java.util.List;

/**
 * Created by lousama on 5/12/16.
 * @author lousama<me@lousama.com>
 */
public class Table {
    private String name;
    private String sql;
    private List<Column> columnList;
    private List<Column> pkColumnList;
    private List<Column> unPKColumnList;

    public List<Column> getPkColumnList() {
        return pkColumnList;
    }

    public void setPkColumnList(List<Column> pkColumnList) {
        this.pkColumnList = pkColumnList;
    }

    public List<Column> getUnPKColumnList() {
        return unPKColumnList;
    }

    public void setUnPKColumnList(List<Column> unPKColumnList) {
        this.unPKColumnList = unPKColumnList;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Column> getColumnList() {
        return columnList;
    }

    public void setColumnList(List<Column> columnList) {
        this.columnList = columnList;
    }

    @Override
    public String toString() {
        return "Table{" +
                "name='" + name + '\'' +
                ", sql='" + sql + '\'' +
                ", columnList=" + columnList +
                ", pkColumnList=" + pkColumnList +
                ", unPKColumnList=" + unPKColumnList +
                '}';
    }
}
