package com.ecar.ecarnetfream.login.entity;

/*************************************
 功能：登录信息请求类
 *************************************/

public class SnNumEntity {
    public String sn;

    public SnNumEntity(String sn) {
        this.sn = sn;
    }

    public static class request {
        public String params;

        public request(String params) {
            this.params = params;
        }

    }

    public static class response {
    }

}

