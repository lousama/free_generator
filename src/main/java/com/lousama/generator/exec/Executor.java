package com.lousama.generator.exec;

import com.lousama.generator.jdbc.MyJdbc;
import com.lousama.generator.model.Packages;
import com.lousama.generator.model.Table;

import java.util.List;

/**
 * Created by lousama on 5/12/16
 * @author lousama<me@lousama.com>
 */
public class Executor {

    public static void excute() throws Exception {
        //get table info from db
        List<Table> tableList = MyJdbc.getTableList();
        //process data
        List<Packages> pkgList = ProcessData.process(tableList);
        //process file
        ProcessFile.process(pkgList);

    }

    public static void main(String[] args) throws Exception {
        excute();
    }
}
