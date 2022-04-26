package com.huangjunqiang.www.Util;

/**
 * Create with IntelliJ IDEA
 *
 * @author: hjq
 * @Date:
 * @Description: 判断字符串是否为空的工具类
 */
public class StringUtil {
    public static boolean isEmpty(String string){
        if(string==null||"".equals(string.trim())){
            return true;
        }else{
            return false;
        }
    }
    public static boolean isNotEmpty(String string){
        if(string!=null||"".equals(string.trim())){
            return true;
        }else{
            return false;
        }
    }
}
