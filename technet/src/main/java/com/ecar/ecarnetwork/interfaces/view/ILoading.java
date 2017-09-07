package com.ecar.ecarnetwork.interfaces.view;


import com.ecar.ecarnetwork.interfaces.security.IInvalid;

/**
 * ===============================================
 * <p>
 * 类描述: Loading 接口
 * <p>
 * 创建人:   happy
 * <p>
 * 创建时间: 2016/6/22 0022 下午 14:22
 * <p>
 * 修改人:   happy
 * <p>
 * 修改时间: 2016/6/22 0022 下午 14:22
 * <p>
 * 修改备注:
 * <p>
 * ===============================================
 */
public interface ILoading extends IInvalid {
    void showLoading();
    void dismissLoading();
    void showMsg(String msg);

/*    void showContent();
    void showNotdata();
    */
}


