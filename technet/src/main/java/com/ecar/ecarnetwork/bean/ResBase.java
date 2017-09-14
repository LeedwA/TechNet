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

    @SerializedName("totalpage")
    public String totalpage;// 总页数

    @SerializedName("result")
    public boolean isSucess;// 是否请求成功

}
