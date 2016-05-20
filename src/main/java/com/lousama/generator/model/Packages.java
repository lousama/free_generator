package com.lousama.generator.model;

import com.lousama.generator.util.ResourceUtil;

import java.util.List;
import java.util.Set;

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

    private String tableName;

    private String isInitQuery = ResourceUtil.getString("is_init_query");
    private String initQuery = ResourceUtil.getString("init_query_name");
    private String author = ResourceUtil.getString("author");
    private String isLombok = ResourceUtil.getString("is_lombok");

    /**
     * init sql in mapper.xml</p>
     * e.g.:select id,name from table
      */
    private String initSql;

    /**
     * priamry key condition in sql in mapper.xml</p>
     * if table has more primary keys, this reduce your repeat coding</p>
     * e.g.:id1=#{id1} and id2=#{id2}</p>
     * keyword:WHERE need in mapper.vm file
     */
    private String pkCondition;

    private List<Column> columnList;

    /**
     * import package set in model class
     */
    private Set<String> importSet;

    public String getPkCondition() {
        return pkCondition;
    }

    public void setPkCondition(String pkCondition) {
        this.pkCondition = pkCondition;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getIsLombok() {
		return isLombok;
	}

	public String getAuthor() {
		return author;
	}

	public String getInitSql() {
		return initSql;
	}

	public void setInitSql(String initSql) {
		this.initSql = initSql;
	}

	public String getIsInitQuery() {
		return isInitQuery;
	}

	public String getInitQuery() {
		return initQuery;
	}

	public Set<String> getImportSet() {
		return importSet;
	}

	public void setImportSet(Set<String> importSet) {
		this.importSet = importSet;
	}

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
                ", tableName='" + tableName + '\'' +
                ", isInitQuery='" + isInitQuery + '\'' +
                ", initQuery='" + initQuery + '\'' +
                ", author='" + author + '\'' +
                ", isLombok='" + isLombok + '\'' +
                ", initSql='" + initSql + '\'' +
                ", pkCondition='" + pkCondition + '\'' +
                ", columnList=" + columnList +
                ", importSet=" + importSet +
                '}';
    }
}
