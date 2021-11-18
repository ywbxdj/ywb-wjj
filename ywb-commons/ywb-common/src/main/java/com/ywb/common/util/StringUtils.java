/**
 * 文件名：StringUtil.java
 * <p>
 * 版本信息：v1.0
 * 日期：2012-10-10
 * Copyright  Corporation 2012
 * 版权所有 E-vada
 */
package com.ywb.common.util;

import java.io.BufferedReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @version 2012-10-10 下午4:53:47
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {

    /**
     * @param obj
     * @return
     */
    public static String asString(Object obj) {
        return obj != null ? obj.toString() : "";
    }

    /**
     * 字符串转Set 集合
     *
     * @param str
     * @return
     */
    public static Set<String> str2Set(String str) {
        if (str == null) return null;
        List<String> list = Arrays.asList(str.split(",")).stream().map(e -> e.trim()).collect(Collectors.toList());
        Set<String> result = new HashSet<>(list);
        return result;
    }

    /**
     * 字符串转List集合
     *
     * @param str
     * @return
     */
    public static List<String> str2ListStr(String str) {
        if (str == null) return null;
        List<String> result = Arrays.asList(str.split(",")).stream().map(e -> e.trim()).collect(Collectors.toList());
        return result;
    }


    /**
     *
     * @param list
     * @param joinStr
     * @return
     */
    public static String join(String[] list, String joinStr) {
        StringBuffer s = new StringBuffer();
        for (int i = 0; list != null && i < list.length; i++) {
            if ((i + 1) == list.length) {
                s.append(list[i]);
            } else {
                s.append(list[i]).append(joinStr);
            }
        }
        return s.toString();
    }

    /**
     * firstCharLowerCase
     *
     * @param s
     *            String
     * @return String
     */
    public static String firstCharLowerCase(String s) {
        if (s == null || "".equals(s))
            return ("");
        return s.substring(0, 1).toLowerCase() + s.substring(1);
    }

    /**
     * firstCharUpperCase
     *
     * @param s
     *            String
     * @return String
     */
    public static String firstCharUpperCase(String s) {
        if (s == null || "".equals(s))
            return ("");
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }

    /**
     *
     * @param src
     * @return
     */
    public static String toBeanPatternStr(String src) {
        String dist = src.toLowerCase();
        Pattern pattern = Pattern.compile("_([a-z0-9])");
        Matcher matcher = pattern.matcher(dist);
        while (matcher.find()) {
            dist = dist.replaceFirst(matcher.group(0), matcher.group(1)
                    .toUpperCase());
        }
        return dist;
    }

    /**
     *
     * @param src
     * @return
     */
    public static String toJSLineSeparateStr(String src) {
        if (src == null) {
            return "";
        }
        String dist = src;
        dist = dist.replaceAll("\r\n", "\\\\n");
        dist = dist.replaceAll("\r", "\\\\n");
        dist = dist.replaceAll("\n", "\\\\n");
        return dist;
    }

    /**
     *
     * @return
     */
    public static String getBytesString(String input, String code) {
        try {
            byte[] b = input.getBytes(code);
            return Arrays.toString(b);
        } catch (UnsupportedEncodingException e) {
            return String.valueOf(code.hashCode());
        }
    }

    /**
     *
     * @param clob
     * @return
     */
    public static String getStringFromClob(java.sql.Clob clob) {
        String result = "";
        try {
            if (clob == null) {
                return null;
            }
            Reader reader = clob.getCharacterStream();
            BufferedReader br = new BufferedReader(reader);
            String line = br.readLine();
            StringBuffer sb = new StringBuffer(1024);
            while (line != null) {
                sb.append(line);
                line = br.readLine();
            }
            result = sb.toString();
        } catch (Exception ex) {

        }
        return result;
    }

    public static String formatParamMsg(String message, Object[] args) {
        for (int i = 0; i < args.length; i++) {
            message = message.replace("{" + i + "}", args[i].toString());
        }
        return message;
    }

    /**
     * 去掉前缀
     *
     * @param toTrim  待处理的字符串
     * @param trimStr 前缀
     * @return 去掉前缀的字符串
     */
    public static String trimPrefix(String toTrim, String trimStr) {
        while (toTrim.startsWith(trimStr)) {
            toTrim = toTrim.substring(trimStr.length());
        }
        return toTrim;
    }

    /**
     * 去掉后缀
     *
     * @param toTrim  代理的字符串
     * @param trimStr 后缀
     * @return 去掉后缀的字符串
     */
    public static String trimSufffix(String toTrim, String trimStr) {
        while (toTrim.endsWith(trimStr)) {
            toTrim = toTrim.substring(0, toTrim.length() - trimStr.length());
        }
        return toTrim;
    }


    public static String capitalize(String str) {
        int strLen;
        return str != null && (strLen = str.length()) != 0 ? (new StringBuffer(strLen)).append(Character.toTitleCase(str.charAt(0))).append(str.substring(1)).toString() : str;
    }

    /** @deprecated */
    public static String capitalise(String str) {
        return capitalize(str);
    }

    public static String uncapitalize(String str) {
        int strLen;
        return str != null && (strLen = str.length()) != 0 ? (new StringBuffer(strLen)).append(Character.toLowerCase(str.charAt(0))).append(str.substring(1)).toString() : str;
    }

    /** @deprecated */
    public static String uncapitalise(String str) {
        return uncapitalize(str);
    }


    private static final String getMethodName = "get";

    /**
     * 获取属性的get方法名
     *
     * @param dateFieldName 属性名称
     * @return 获取属性的get方法名
     */
    public static String getFieldMethodName(String dateFieldName) {
        return getMethodName + firstLetterToUpper(dateFieldName);
    }

    /**
     * 转换首字母大写
     *
     * @param dateFieldName 字段名称
     * @return 首字母大写后字串
     */
    public static String firstLetterToUpper(String dateFieldName) {
        char[] cs = dateFieldName.toCharArray();
        cs[0] -= 32;
        return String.valueOf(cs);
    }
    //region 判空

    /**
     * 对象是否为空
     *
     * @param obj String,Collection,Map,Object[],int[],long[],short[],double[],float[],byte[]
     */
    public static boolean isEmpty(Object obj) {
        if (obj == null) return true;

        if (obj instanceof CharSequence) return (obj.toString().trim().isEmpty());
        else if (obj instanceof Collection) return (((Collection) obj).size() == 0); // 包含List和Set
        else if (obj instanceof Map) return (((Map) obj).size() == 0);
        else if (obj instanceof Object[]) return (((Object[]) obj).length == 0);
        else if (obj instanceof int[]) return (((int[]) obj).length == 0);
        else if (obj instanceof long[]) return (((long[]) obj).length == 0);
        else if (obj instanceof short[]) return (((short[]) obj).length == 0);
        else if (obj instanceof double[]) return (((double[]) obj).length == 0);
        else if (obj instanceof float[]) return (((float[]) obj).length == 0);
        else if (obj instanceof byte[]) return (((byte[]) obj).length == 0);
        else if (obj instanceof char[]) return (((char[]) obj).length == 0);
        else if (obj instanceof boolean[]) return (((boolean[]) obj).length == 0);

        return false;
    }

    public static boolean isEmpty(CharSequence obj) {
        if (obj == null) return true;
        return obj.toString().trim().isEmpty();
    }

    public static boolean isEmpty(Collection obj) {
        if (obj == null) return true;
        return (obj.size() == 0); // 包含List和Set
    }

    public static boolean isEmpty(Map obj) {
        if (obj == null) return true;
        return (obj.size() == 0);
    }

    public static boolean isEmpty(Object[] obj) {
        if (obj == null) return true;
        return (obj.length == 0);
    }

    public static boolean isEmpty(boolean[] obj) {
        if (obj == null) return true;
        return (obj.length == 0);
    }

    public static boolean isEmpty(byte[] obj) {
        if (obj == null) return true;
        return (obj.length == 0);
    }

    public static boolean isEmpty(char[] obj) {
        if (obj == null) return true;
        return (obj.length == 0);
    }

    public static boolean isEmpty(double[] obj) {
        if (obj == null) return true;
        return (obj.length == 0);
    }

    public static boolean isEmpty(float[] obj) {
        if (obj == null) return true;
        return (obj.length == 0);
    }

    public static boolean isEmpty(int[] obj) {
        if (obj == null) return true;
        return (obj.length == 0);
    }

    public static boolean isEmpty(long[] obj) {
        if (obj == null) return true;
        return (obj.length == 0);
    }

    public static boolean isEmpty(short[] obj) {
        if (obj == null) return true;
        return (obj.length == 0);
    }

    public static boolean isEmpty(Boolean obj) {
        return (obj == null);
    }

    public static boolean isEmpty(Byte obj) {
        return (obj == null);
    }

    public static boolean isEmpty(Character obj) {
        return (obj == null);
    }

    public static boolean isEmpty(Date obj) {
        return (obj == null);
    }

    public static boolean isEmpty(Double obj) {
        return (obj == null);
    }

    public static boolean isEmpty(Float obj) {
        return (obj == null);
    }

    public static boolean isEmpty(Integer obj) {
        return (obj == null);
    }

    public static boolean isEmpty(Long obj) {
        return (obj == null);
    }

    public static boolean isEmpty(Short obj) {
        return (obj == null);
    }

    /**
     * 对象是否不为空(新增)
     *
     * @param obj String,Collection,Map,Object[],int[],long[],short[],double[],float[],byte[]
     */
    public static boolean isNotEmpty(Object obj) {
        return !isEmpty(obj);
    }

    public static boolean isNotEmpty(CharSequence obj) {
        return !isEmpty(obj);
    }

    public static boolean isNotEmpty(Collection obj) {
        return !isEmpty(obj);
    }

    public static boolean isNotEmpty(Map obj) {
        return !isEmpty(obj);
    }

    public static boolean isNotEmpty(Object[] obj) {
        return !isEmpty(obj);
    }

    public static boolean isNotEmpty(boolean[] obj) {
        return !isEmpty(obj);
    }

    public static boolean isNotEmpty(byte[] obj) {
        return !isEmpty(obj);
    }

    public static boolean isNotEmpty(char[] obj) {
        return !isEmpty(obj);
    }

    public static boolean isNotEmpty(double[] obj) {
        return !isEmpty(obj);
    }

    public static boolean isNotEmpty(float[] obj) {
        return !isEmpty(obj);
    }

    public static boolean isNotEmpty(int[] obj) {
        return !isEmpty(obj);
    }

    public static boolean isNotEmpty(long[] obj) {
        return !isEmpty(obj);
    }

    public static boolean isNotEmpty(short[] obj) {
        return !isEmpty(obj);
    }

    public static boolean isNotEmpty(Boolean obj) {
        return !isEmpty(obj);
    }

    public static boolean isNotEmpty(Byte obj) {
        return !isEmpty(obj);
    }

    public static boolean isNotEmpty(Character obj) {
        return !isEmpty(obj);
    }

    public static boolean isNotEmpty(Date obj) {
        return !isEmpty(obj);
    }

    public static boolean isNotEmpty(Double obj) {
        return !isEmpty(obj);
    }

    public static boolean isNotEmpty(Float obj) {
        return !isEmpty(obj);
    }

    public static boolean isNotEmpty(Integer obj) {
        return !isEmpty(obj);
    }

    public static boolean isNotEmpty(Long obj) {
        return !isEmpty(obj);
    }

    public static boolean isNotEmpty(Short obj) {
        return !isEmpty(obj);
    }

    /**
     * 对象组中是否存在 Empty Object
     *
     * @param os 对象组
     */
    public static boolean isOneEmpty(Object... os) {
        for (Object o : os) {
            if (isEmpty(o)) return true;
        }
        return false;
    }

    /**
     * 对象组中是否全是 Empty Object
     */
    public static boolean isAllEmpty(Object... os) {
        for (Object o : os) {
            if (!isEmpty(o)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断当前值是否在数组范围内
     *
     * @param value
     * @param arr
     * @param <T>
     * @return
     */
    public static <T> boolean in(T value, T... arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("arr参数不能为空");
        }

        if (value == null) {
            for (T v : arr) {
                if (v == null) return true; // 同样为null
            }
        } else {
            for (T v : arr) {
                if (value.equals(v)) return true; // 值相同
            }
        }

        return false;
    }

    /**
     * 判断当前值是否不在数组范围内
     *
     * @param value
     * @param arr
     * @param <T>
     * @return
     */
    public static <T> boolean notIn(T value, T... arr) {
        return !in(value, arr);
    }


    //endregion
}
