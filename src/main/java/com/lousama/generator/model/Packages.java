package com.lousama.generator.model;

import java.util.List;

/**
 * Created by lousama on 5/12/16.
 * @author lousama<me@lousama.com>
 */
public class Packages {
    private String dao;
    private String model;
    private String mapperXml;

    private String daoName;
    private String modelName;
    private String mapperXmlName;

    private List<Column> columnList;

    public String getDao() {
        return dao;
    }

    public void setDao(String dao) {
        this.dao = dao;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }


    public String getMapperXml() {
        return mapperXml;
    }

    public void setMapperXml(String mapperXml) {
        this.mapperXml = mapperXml;
    }

    public String getDaoName() {
        return daoName;
    }

    public void setDaoName(String daoName) {
        this.daoName = daoName;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }


    public String getMapperXmlName() {
        return mapperXmlName;
    }

    public void setMapperXmlName(String mapperXmlName) {
        this.mapperXmlName = mapperXmlName;
    }

    public List<Column> getColumnList() {
        return columnList;
    }

    public void setColumnList(List<Column> columnList) {
        this.columnList = columnList;
    }

    @Override
    public String toString() {
        return "Packages{" +
                "dao='" + dao + '\'' +
                ", model='" + model + '\'' +
                ", mapperXml='" + mapperXml + '\'' +
                ", daoName='" + daoName + '\'' +
                ", modelName='" + modelName + '\'' +
                ", mapperXmlName='" + mapperXmlName + '\'' +
                ", columnList=" + columnList +
                '}';
    }
}
