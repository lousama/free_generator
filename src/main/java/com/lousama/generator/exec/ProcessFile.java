package com.lousama.generator.exec;

import com.lousama.generator.model.Packages;
import com.lousama.generator.util.ResourceUtil;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.StringWriter;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.List;

/**
 * Created by lousama on 5/16/16
 *
 * @author lousama<me@lousama.com>
 */
public class ProcessFile {
    private static VelocityEngine ve = null;
    private static Logger logger = LoggerFactory.getLogger(ProcessFile.class);
    private static final String templates = ResourceUtil.getString("templates");
    private static final int bufferCapacity = ResourceUtil.getInt("buffer_capacity");

    /**
     * init velocity
     * @throws Exception
     */
    private static void init() throws Exception {
        ve = new VelocityEngine();
        ve.setProperty(RuntimeConstants.RESOURCE_LOADER,"classpath");
        ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        ve.setProperty(Velocity.ENCODING_DEFAULT, "UTF-8");
        ve.setProperty(Velocity.INPUT_ENCODING, "UTF-8");
        ve.setProperty(Velocity.OUTPUT_ENCODING, "UTF-8");
        ve.init();
    }

    /**
     * process main logic for create file
     * @param pkgList dataset to velocity
     * @throws Exception
     */
    public static void process(List<Packages> pkgList) throws Exception {
        init();
        //String[] vmFiles = {"model.vm","dao.vm","mapper.vm"};
        String[] temps = templates.split(",");
        String[] vmFiles = new String[temps.length];
        for (int i = 0; i < temps.length; i++) {
            vmFiles[i] = temps[i] + ".vm";
        }
        for(Packages pkg : pkgList){
            for(String vmFile : vmFiles){
                generator(vmFile,pkg);
            }
        }
    }

    /**
     * generator file
     * @param vmFile
     * @param pkg
     * @throws Exception
     */
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

    /**
     * get file path from data and config
     * @param vmFile name list for velocity model
     * @param pkg Packages
     * @return
     */
    private static String getFilePath(String vmFile,Packages pkg){
        String classPath = "";
        if(vmFile.startsWith("model")){
            classPath = (pkg.getModel() + "." + pkg.getModelName()).replace('.',File.separatorChar) + ".java";
        }else if(vmFile.startsWith("dao")){
            classPath = (pkg.getDao() + "." + pkg.getDaoName()).replace('.',File.separatorChar) + ".java";
        }else if(vmFile.startsWith("mapper")){
            classPath = (pkg.getMapperXml() + "." + pkg.getMapperXmlName()).replace('.',File.separatorChar) + ".xml";
        }
        return classPath;

    }

    /**
     * create file here</p>
     * try to delete dictory or file if exists
     * @param filePath file path
     * @param text text for file
     * @throws Exception
     */
    private static void createFile(String filePath,String text) throws Exception {
        File dir = new File(filePath.substring(0,filePath.lastIndexOf(File.separator)));
        File file = new File(filePath);
        //try to delete dictory before create when file already exists
        if(dir.exists()){
            dir.delete();
        }
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
        CharBuffer charBuffer = CharBuffer.allocate(text.length());
        charBuffer.put(text);
        charBuffer.flip();
        Charset charset=Charset.defaultCharset();
        ByteBuffer byteBuffer =charset.encode(charBuffer);
        while (byteBuffer.hasRemaining()) {
            channel.write(byteBuffer);
        }

        channel.close();
        fileOutputStream.close();
        logger.info("success generator file:-- " + filePath);
    }
}
