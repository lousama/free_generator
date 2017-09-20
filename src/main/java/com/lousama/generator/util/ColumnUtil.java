package com.lousama.generator.util;

/**
 * Created by lousama on 5/16/16
 *
 * @author lousama<me@lousama.com>
 */
public class ColumnUtil {

    private static boolean iswrapper = ResourceUtil.getBoolean("is_wrapper");
    private static final String STRING = "String";
    private static final String INT = "int";
    private static final String INT_WRAPPER = "Integer";
    private static final String LONG = "long";
    private static final String LONG_WRAPPER = "Long";
    private static final String TIMESTAMP = "java.sql.Timestamp";
    private static final String DATE = "java.util.Date";
    private static final String DECIMAL="java.math.BigDecimal";


    /**
     * parse columnTypeName to corresponding java class
     * @param typeName columnTypeName
     * @param colSize columnDisplaySize
     * @param scale scale of the column
     * @return
     */
    public static String parseColumnType(String typeName,int colSize,int scale){
        typeName = typeName.toUpperCase();
        if ((typeName.startsWith("INT") || typeName.startsWith("SMALLINT") ||
                typeName.startsWith("TINYINT") || typeName.startsWith("INTEGER") ||
                (typeName.startsWith("NUMBER") && scale <= 0)) && colSize < 10){
            return iswrapper ? INT_WRAPPER : INT;
        } else if ((typeName.startsWith("BIGINT") || typeName.startsWith("LONG") ||
                (typeName.startsWith("NUMBER") && scale <= 0) || typeName.startsWith("INT")) && colSize >= 10){
            return iswrapper ? LONG_WRAPPER : LONG;
        } else if (typeName.startsWith("VARCHAR")){
            return STRING;
        } else if ((typeName.startsWith("NUMBER") || typeName.startsWith("DECIMAL") || typeName.startsWith("NUMERIC")
                || typeName.startsWith("DOUBLE") || typeName.startsWith("FLOAT")) && scale > 0){
            return DECIMAL;
        } else if (typeName.startsWith("TIMESTAMP") || typeName.startsWith("DATETIME")){
            return DATE;
        } else if (typeName.startsWith("DATE")){
            return DATE;
        } else {
            return STRING;
        }
    }
}
