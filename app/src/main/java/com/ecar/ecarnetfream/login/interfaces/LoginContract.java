package com.ecar.ecarnetfream.login.interfaces;


import android.app.Activity;

import com.ecar.ecarnetfream.publics.base.BasePresenter;
import com.ecar.ecarnetfream.publics.base.IModel;
import com.ecar.ecarnetfream.publics.base.IView;
import com.ecar.ecarnetwork.bean.ResBase;


public interface LoginContract {
    interface Model extends IModel {
    }

    interface View extends IView {
        void loginSuccess(ResBase resBase);
        void showMsg(String msg);
    }

    /**
     * 可以实现P 的接口， 此处是可避免P 子实现类导包麻烦
     */
    abstract class Presenter extends BasePresenter<View, Model> {

        public Presenter(Activity context, View view, Model model) {
            super(context, view, model);
        }
        public abstract void login(String name, String pass);
        public abstract void sign(String name, String pass);
    }
    //    interface Presenter extends IPresenter {
//        void login(String name, String pass);
//        void sign(String name, String pass);
//    }
}
