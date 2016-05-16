package com.lousama.generator.exec;

import com.lousama.generator.model.Column;
import com.lousama.generator.model.Packages;
import com.lousama.generator.model.Table;
import com.lousama.generator.util.ColumnUtil;
import com.lousama.generator.util.ResourceUtil;
import com.lousama.generator.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lousama on 5/16/16
 *
 * @author lousama<me@lousama.com>
 */
public class ProcessData {
    private static boolean isHumpModelClass = ResourceUtil.getBoolean("is_hump_model_class");
    private static boolean isHumpColumn = ResourceUtil.getBoolean("is_hump_column");
    private static String packagePrefix = ResourceUtil.getString("package_prefix");

    private static String packageModel = ResourceUtil.getString("package_model");
    private static String packageMapperXml = ResourceUtil.getString("package_mapper_xml");
    private static String packageDao = ResourceUtil.getString("package_dao");

    private static String modelSuffix = ResourceUtil.getString("model_suffix");
    private static String mapperXmlSuffix = ResourceUtil.getString("mapper_xml_suffix");
    private static String daoSuffix = ResourceUtil.getString("dao_suffix");


    public static List<Packages> process(List<Table> tableList){
        List<Packages> pkgList = new ArrayList<Packages>(tableList.size());
        for (Table table : tableList) {
            Packages pkg = new Packages();
            //process package and fileName
            processPackage(table.getName(),pkg);
            //process column,parse column name to hump
            processColumn(table.getColumnList(),pkg);
            pkgList.add(pkg);
        }
        return pkgList;
    }

    /**
     * process package and fileName
     * @param tbName
     * @param pkg
     */
    private static void processPackage(String tbName,Packages pkg){
        String classNamePrefix = StringUtil.parseHumpName(tbName,true,isHumpModelClass);
        pkg.setDao(packagePrefix + packageDao);
        pkg.setDaoName(classNamePrefix+ daoSuffix);
        pkg.setModel(packagePrefix + packageModel);
        pkg.setModelName(classNamePrefix + modelSuffix);
        pkg.setMapperXml(packagePrefix + packageMapperXml);
        pkg.setMapperXmlName(classNamePrefix+ mapperXmlSuffix);
    }

    /**
     * process column name and type
     * @param colList
     * @param pkg
     */
    private static void processColumn(List<Column> colList,Packages pkg){
        for(Column col : colList){
            col.setColName(StringUtil.parseHumpName(col.getColName(),false,isHumpColumn));
            col.setTypeName(ColumnUtil.parseColumnType(col.getTypeName(),col.getColSize(),col.getScale()));
        }
        pkg.setColumnList(colList);
    }
}
