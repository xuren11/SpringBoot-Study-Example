/*
 * All rights Reserved, Designed By 农金圈 2016年11月26日 上午11:21:17
 */

package com.xuren.study.enums;


/**
 * 上传附件文件类型 OSS (对应阿里Oss的Content-type)
 * 
 * @author: rayping 2016年11月26日
 */
public enum FileTypeEnums {

    BMP_IMAGE("IMAGE", "image/bmp", "图片"),
    GIF_IMAGE("IMAGE", "image/gif", "图片"),
    JPEG_IMAGE("IMAGE", "image/jpeg", "图片"),
    HTML("HTML", "text/html", "html文件"),
    TEXT("TEXT", "text/plain", "text文本"),
    VSD("VSD", "application/vnd.visio", "visio"),
    PPT("PPT", "application/vnd.ms-powerpoint", "ppt"),
    DOC("DOCUMENT", "application/msword", "doc文档"),
    DOCX("DOCUMENT", "application/vnd.openxmlformats-officedocument.wordprocessingml.document", "doc文档"),
    PDF("PDF", "application/pdf", "pdf文件"),
    XLS("EXCEL", "application/vnd.ms-excel", "excel"),
    XLSX("EXCEL", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", "excel"),
    XML("XML", "text/xml", "XML"),
    MP3("VIDEO", "video/mpeg", "音视频"),
    MP4("VIDEO", "video/mp4", "音视频"),
    MOF("VIDEO", "application/x-yumekara", "音视频"),
    MOVIE("VIDEO", "video/x-sgi-movie", "音视频"),
    ZIP("ZIP", "application/zip", "压缩文件"),
    OTHER("OTHER", "", "");


    private String code;

    private String contentType;

    private String desc;

    private FileTypeEnums(String code, String contentType, String desc) {
        this.code = code;
        this.contentType = contentType;
        this.desc = desc;
    }


    /**
     * @return: String <br>
     */
    public String getCode() {
        return code;
    }


    /**
     * @return: String <br>
     */
    public String getContentType() {
        return contentType;
    }



    /**
     * @return: String <br>
     */
    public String getDesc() {
        return desc;
    }



}
