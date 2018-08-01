package com.ecar.ecarnetwork.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * 接口应答
 *
 * @author Administrator
 */
public class ResBase<T>  implements Serializable {

    @SerializedName("message")
    public String msg;// 错误信息

    @SerializedName("status")
    public String errorStatus;// 业务异常错误信息

    @SerializedName("sign")
    public String sign;// 簽名

    @SerializedName("code")
    public String code="200";// 返回编号


    @SerializedName("data")
    public T data;// 返回编号


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

    public String head;
    public int netCode;// 网络状态编号


}
