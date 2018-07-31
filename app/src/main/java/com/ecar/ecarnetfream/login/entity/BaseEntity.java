package com.ecar.ecarnetfream.login.entity;

import com.ecar.ecarnetwork.bean.ResBase;
import com.google.gson.annotations.SerializedName;

/**
 * +----------------------------------------------------------------------
 * |  说明     ：
 * +----------------------------------------------------------------------
 * | 创建者   :  kim_tony
 * +----------------------------------------------------------------------
 * | 时　　间 ：2017/9/6 17:03
 * +----------------------------------------------------------------------
 * | 版权所有: 北京市车位管家科技有限公司
 * +----------------------------------------------------------------------
 **/

public class BaseEntity extends ResBase {
    @SerializedName("imei")
    public String imei;

    @SerializedName("terminaltype")
    public String terminaltype;

    @SerializedName("userId")
    public String userid;

    @SerializedName("loginname")
    public String loginname;

    @SerializedName("connectionAccount")
    public String username;

    @SerializedName("parkId")
    public String parkId;


}
