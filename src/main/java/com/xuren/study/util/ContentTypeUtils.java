/*
 * All rights Reserved, Designed By 农金圈 2016年11月15日 下午6:51:56
 */

package com.xuren.study.util;

import com.xuren.study.enums.FileTypeEnums;
import org.apache.commons.lang3.StringUtils;

/**
 * ContentType工具类
 * 
 * 参考：https://helpcdn.aliyun.com/knowledge_detail/39522.html
 * 
 * @author: rayping
 */
public class ContentTypeUtils {


    /**
     * Description: 判断服务文件上传时文件的contentType
     * 
     * @param fileExt 文件后缀
     * @return String 文件ContentType
     */
    public static FileTypeEnums contentType(String fileExt) {
        if (StringUtils.isBlank(fileExt)) {
            return null;
        }

        if (fileExt.equalsIgnoreCase("bmp")) {
            return FileTypeEnums.BMP_IMAGE;
        } else if (fileExt.equalsIgnoreCase("gif")) {
            return FileTypeEnums.GIF_IMAGE;
        } else if (fileExt.equalsIgnoreCase("jpeg") || fileExt.equalsIgnoreCase("jpg") || fileExt.equalsIgnoreCase("jpe")
            || fileExt.equalsIgnoreCase("jpz")
            || fileExt.equalsIgnoreCase("png")) {
            return FileTypeEnums.JPEG_IMAGE;
        } else if (fileExt.equalsIgnoreCase("html") || fileExt.equalsIgnoreCase("htm") || fileExt.equalsIgnoreCase("dhtml")
            || fileExt.equalsIgnoreCase("hts")) {
            return FileTypeEnums.HTML;
        } else if (fileExt.equalsIgnoreCase("txt") || fileExt.equalsIgnoreCase("java")) {
            return FileTypeEnums.TEXT;
        } else if (fileExt.equalsIgnoreCase("vsd")) {
            return FileTypeEnums.VSD;
        } else if (fileExt.equalsIgnoreCase("pptx") || fileExt.equalsIgnoreCase("ppt")) {
            return FileTypeEnums.PPT;
        } else if (fileExt.equalsIgnoreCase("doc") || fileExt.equalsIgnoreCase("dot")) {
            return FileTypeEnums.DOC;
        } else if (fileExt.equalsIgnoreCase("docx")) {
            return FileTypeEnums.DOCX;
        } else if (fileExt.equalsIgnoreCase("pdf")) {
            return FileTypeEnums.PDF;
        } else if (fileExt.equalsIgnoreCase("xls") || fileExt.equalsIgnoreCase("xlt") || fileExt.equalsIgnoreCase("xlw")
            || fileExt.equalsIgnoreCase("xla") || fileExt.equalsIgnoreCase("xlsm") || fileExt.equalsIgnoreCase("xlc")
            || fileExt.equalsIgnoreCase("xlm")) {
            return FileTypeEnums.XLS;
        } else if (fileExt.equalsIgnoreCase("xlsx")) {
            return FileTypeEnums.XLSX;
        } else if (fileExt.equalsIgnoreCase("xml")) {
            return FileTypeEnums.XML;
        } else if (fileExt.equalsIgnoreCase("mp2") || fileExt.equalsIgnoreCase("mp3") || fileExt.equalsIgnoreCase("mpa")
            || fileExt.equalsIgnoreCase("mpe") || fileExt.equalsIgnoreCase("mpeg") || fileExt.equalsIgnoreCase("mpg")
            || fileExt.equalsIgnoreCase("mpv2")) {
            return FileTypeEnums.MP3;
        } else if (fileExt.equalsIgnoreCase("mp4") || fileExt.equalsIgnoreCase("mpg4")) {
            return FileTypeEnums.MP4;
        } else if (fileExt.equalsIgnoreCase("mof")) {
            return FileTypeEnums.MOF;
        } else if (fileExt.equalsIgnoreCase("movie")) {
            return FileTypeEnums.MOVIE;
        }
        return FileTypeEnums.HTML;
    }

}
