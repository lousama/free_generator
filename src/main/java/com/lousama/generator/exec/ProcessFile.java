package com.lousama.generator.exec;

import com.lousama.generator.model.Packages;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.StringWriter;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.List;

/**
 * Created by lousama on 5/16/16
 *
 * @author lousama<me@lousama.com>
 */
public class ProcessFile {
    private static VelocityEngine ve = null;
    private static Logger logger = LoggerFactory.getLogger(ProcessFile.class);
    private static final String jarName = "free_generator";

    private static void init() throws Exception {
        ve = new VelocityEngine();
        ve.setProperty(RuntimeConstants.RESOURCE_LOADER,"classpath");
        ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        ve.init();
    }
    
    public static void process(List<Packages> pkgList) throws Exception {
        init();
        String[] vmFiles = {"model.vm","dao.vm","mapper.vm"};
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
        //get file path
        String filePath = getFilePath(vmFile,pkg);
        createFile(filePath,sw.toString());
    }

    private static String getFilePath(String vmFile,Packages pkg){
        String classPath = "";
        if("model.vm".equals(vmFile)){
            classPath = (pkg.getModel() + "." + pkg.getModelName()).replace('.',File.separatorChar) + ".java";
        }else if("dao.vm".equals(vmFile)){
            classPath = (pkg.getDao() + "." + pkg.getDaoName()).replace('.',File.separatorChar) + ".java";
        }else if("mapper.vm".equals(vmFile)){
            classPath = (pkg.getMapperXml() + "." + pkg.getMapperXmlName()).replace('.',File.separatorChar) + ".xml";
        }
        return classPath;

    }

    private static void createFile(String filePath,String text) throws Exception {
        File dir = new File(filePath.substring(0,filePath.lastIndexOf(File.separator)));
        File file = new File(filePath);
        //create dictory if not exists
        if(!dir.exists()){
            dir.mkdirs();
        }
        //try to delete file before create when file already exists
        if(file.exists()){
           file.delete();
        }
        if(!file.exists()){
            file.createNewFile();
        }else {
            throw new Exception("file:["+filePath+"] already exists and cannot delete!");
        }

        FileOutputStream fileOutputStream = new FileOutputStream(file);
        FileChannel channel = fileOutputStream.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(2048);
        buffer.put(text.getBytes());
        buffer.flip();
        channel.write(buffer);
        channel.close();
        fileOutputStream.close();
        logger.info("success generator file:-- " + filePath);
    }
}
