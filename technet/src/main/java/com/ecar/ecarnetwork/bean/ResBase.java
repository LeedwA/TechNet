package com.ecar.ecarnetwork.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * 接口应答
 *
 * @author Administrator
 */
public class ResBase implements Serializable {

    @SerializedName("message")
    public String msg;// 错误信息

    @SerializedName("sign")
    public String sign;// 簽名

    @SerializedName("code")
    public String code;// 返回编号



    @SerializedName("pageNum")
    public String pageNum;// 当前页

    @SerializedName("size")
    public int size;//当前页数量

    @SerializedName("pageSize")
    public int pageSize;//每页的数量

    @SerializedName("pages")
    public int totalPages;//总页数
    
    @SerializedName("total")
    public int totalCount;//总记录数

}
