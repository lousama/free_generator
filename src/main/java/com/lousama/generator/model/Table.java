package com.lousama.generator.model;

import com.lousama.generator.util.ResourceUtil;

import java.util.List;

/**
 * Created by lousama on 5/12/16.
 * @author lousama<me@lousama.com>
 */
public class Table {
    private String name;
    private String sql;
    private List<Column> columnList;


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
                ", name='" + name + '\'' +
                ", sql='" + sql + '\'' +
                ", columnList=" + columnList +
                '}';
    }
}
