package com.lousama.generator.model;

/**
 * Created by lousama on 5/16/16
 *
 * @author lousama<me@lousama.com>
 */
public class Column {
    private String className;
    private String importClass;
    private String dbColName;
    private String colName;
    private String getMethod;
    private String setMethod;
    private int colSize;
    private int scale;
    
    
    
    public String getDbColName() {
		return dbColName;
	}

	public void setDbColName(String dbColName) {
		this.dbColName = dbColName;
	}

	public String getGetMethod() {
		return getMethod;
	}

	public void setGetMethod(String getMethod) {
		this.getMethod = getMethod;
	}

	public String getSetMethod() {
		return setMethod;
	}

	public void setSetMethod(String setMethod) {
		this.setMethod = setMethod;
	}

	public String getImportClass() {
		return importClass;
	}

	public void setImportClass(String importClass) {
		this.importClass = importClass;
	}

	public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
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
                "typeName='" + className + '\'' +
                ", colName='" + colName + '\'' +
                ", colSize='" + colSize + '\'' +
                ", scale=" + scale +
                '}';
    }
}
