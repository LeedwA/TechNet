package com.ecar.ecarnetwork.http.exception;


import com.ecar.ecarnetwork.bean.ResBase;

/**
 * 自定义异常：
 * 1.自定义处理失败逻辑
 */
public class UserException extends BaseException {

//    public UserException(String code, String msg) {
//        super(code, msg);
//    }

    public UserException(String code, String msg, ResBase resObj) {
        super(code, msg, resObj);
    }

    public UserException(String msg) {
        super(msg);
    }
}
