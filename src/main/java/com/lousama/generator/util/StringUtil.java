package com.lousama.generator.util;

/**
 * Created by lousama on 5/16/16
 *
 * @author lousama<me@lousama.com>
 */
public class StringUtil {
    /**
     * parse str to hump type<p>
     * if class name ,param isFirstUpper should be true,else false
     * @param str
     * @param isFirstUpper is first word upper
     * @param isHump
     * @return
     */
    public static String parseHumpName(String str,boolean isFirstUpper,boolean isHump){
        str = str.toLowerCase();
        if(isHump && str.indexOf("_") != -1) {
            boolean isUpper = false;
            StringBuilder builder = new StringBuilder();
            for (char c : str.toCharArray()) {
                if (isUpper) {
                    c = Character.toUpperCase(c);
                    isUpper = false;
                }
                if (c == '_') {
                    isUpper = true;
                    continue;
                }
                builder.append(c);
            }
            str = builder.toString();
        }
        //check the first char,avoid first one upper
        if(isFirstUpper){
            return upperFirst(str);
        }
        return lowerFirst(str);
    }
    
    public static String upperFirst(String str){
    	return String.valueOf(str.charAt(0)).toUpperCase() + str.substring(1);
    }

    public static String lowerFirst(String str){
    	return String.valueOf(str.charAt(0)).toLowerCase() + str.substring(1);
    }
}
