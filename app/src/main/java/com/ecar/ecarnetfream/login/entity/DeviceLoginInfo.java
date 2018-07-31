package com.ecar.ecarnetfream.login.entity;

import com.google.gson.annotations.SerializedName;

/*************************************
 功能：登录所需信息对象
 *************************************/

public class DeviceLoginInfo extends BaseEntity {
    @SerializedName("connectionPassword")
    public String passWord;//密码
    @SerializedName("sn")
    public String snNum;//序列号
    @SerializedName("connectionEncryptionKey")
    public String encriptKey;//秘钥
    @SerializedName("ip")
    public String host;//host
    @SerializedName("port")
    public String port;//ip

    @SerializedName("data")
    public String data;//ip


}
