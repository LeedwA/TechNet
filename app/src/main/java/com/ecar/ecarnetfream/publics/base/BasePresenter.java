package com.ecar.ecarnetfream.publics.base;

import android.app.Activity;

import com.ecar.ecarnetwork.base.manage.RxManage;


/**
 * ===============================================
 * <p>
 * 类描述: presenter基类
 * <p>
 * 创建人:   happy
 * <p>
 * 创建时间: 2016/6/22 0022 上午 11:52
 * <p>
 * 修改人:   happy
 * <p>
 * 修改时间: 2016/6/22 0022 上午 11:52
 * <p>
 * 修改备注:
 * <p>
 * ===============================================
 */
public abstract class BasePresenter<T,E> {

    protected Activity context;
    protected RxManage rxManage = new RxManage();

    protected T view;
    protected E model;

    protected void setIView(T view){
        this.view = view;
    }

    /**
     * 单元测试 采用依赖参数 构造时 一起注入，方便mockito
     */
    public BasePresenter(Activity context,T view,E model) {
        this.context = context;
        this.view = view;
        this.model = model;
    }

    public BasePresenter() {
    }

    protected void onDestroy(){
        rxManage.clear();
        context =null;
    }

//    public BasePresenter(Activity context,T view) {
//        this.context = context;
//        this.view = view;
//        this.model = initModel();
//    }

//    protected abstract E initModel();

}
