package com.lousama.generator.model;

/**
 * Created by lousama on 5/16/16
 *
 * @author lousama<me@lousama.com>
 */
public class Column {
    private String typeName;
    private String colName;
    private int colSize;
    private int scale;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getColName() {
        return colName;
    }

    public void setColName(String colName) {
        this.colName = colName;
    }

    public int getColSize() {
        return colSize;
    }

    public void setColSize(int colSize) {
        this.colSize = colSize;
    }

    public int getScale() {
        return scale;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    @Override
    public String toString() {
        return "Column{" +
                "typeName='" + typeName + '\'' +
                ", colName='" + colName + '\'' +
                ", colSize='" + colSize + '\'' +
                ", scale=" + scale +
                '}';
    }
}
