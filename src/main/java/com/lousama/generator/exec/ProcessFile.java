package com.lousama.generator.exec;

import com.lousama.generator.model.Packages;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import java.io.StringWriter;
import java.util.List;

/**
 * Created by lousama on 5/16/16
 *
 * @author lousama<me@lousama.com>
 */
public class ProcessFile {
    private static VelocityEngine ve = null;

    public static void process(List<Packages> pkgList) throws Exception {
        init();
        String[] vmFiles = {"model.vm","dao.vm"};
        for(Packages pkg : pkgList){
            for(String vmFile : vmFiles){
                generator(vmFile,pkg);
            }
        }
    }

    private static void generator(String vmFile,Packages pkg) throws Exception {
        Template template = ve.getTemplate(vmFile);
        VelocityContext vctx = new VelocityContext();
        vctx.put("package",pkg);
        StringWriter sw = new StringWriter();
        template.merge(vctx, sw);
        //TODO
        System.out.println(sw.toString());
    }

    private static void init() throws Exception {
        ve = new VelocityEngine();
        ve.setProperty(RuntimeConstants.RESOURCE_LOADER,"classpath");
        ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        ve.init();
    }

}
