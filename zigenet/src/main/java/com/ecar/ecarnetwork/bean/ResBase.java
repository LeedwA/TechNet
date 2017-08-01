package com.ecar.ecarnetwork.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * 接口应答
 *
 * @author Administrator
 */
public class ResBase implements Serializable {

    public String message;// 错误信息

    public String code;// 返回的结果code

}
