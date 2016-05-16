package com.lousama.generator;

import com.lousama.generator.model.Packages;
import com.lousama.generator.model.Table;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zff on 5/12/16.
 */
public class HelloVelocity {
    public static void main(String[] args) throws Exception {
        VelocityEngine ve = new VelocityEngine();
        ve.setProperty(RuntimeConstants.RESOURCE_LOADER,"classpath");
        ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        ve.init();

        Template t = ve.getTemplate("dao.vm");
        VelocityContext vctx = new VelocityContext();

        Packages pkg = new Packages();
        pkg.setModel("com.lousama.generator.model");
        pkg.setModelName("Test");
        vctx.put("package",pkg);
        List<Table> temp = new ArrayList();
        //for (int i = 0; i < 3 ;i++) {
        //    Table column = new Table();
        //    column.setName("name"+i);
        //    if(i/2==0){
        //        column.setType("int");
        //    }else {
        //        column.setType("String");
        //    }
        //    temp.add(column);
        //    System.out.println(column);
        //
        //}
        vctx.put("list", temp);

        StringWriter sw = new StringWriter();

        t.merge(vctx, sw);

        System.out.println(sw.toString());

    }



}
