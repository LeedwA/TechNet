package com.ecar.ecarnetfream.publics.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


import com.ecar.ecarnetfream.publics.util.TagUtil;
import com.ecar.ecarnetfream.publics.view.loading.ProgressDialogBar;
import com.ecar.ecarnetwork.interfaces.view.ILoading;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/4/5.
 */
public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements ILoading {
    protected T presenter;
    protected Activity context;
    private ProgressDialogBar progressBar = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView();
        App.getInstance().addActivity(this);
        ButterKnife.bind(this);
        context = this;
        this.initData();
        this.initView();
        this.initPresenter();
        this.initLoading();
    }


    /**
     * 生命周期在onResume之后，view附着到Window 调用一次
     * （存在一个问题：第一次在onResume里头想做操作然而没有p）
     * 上来就发请求的 情况可以在这里做处理
     */
//    @Override
//    public void onAttachedToWindow() {
//        super.onAttachedToWindow();
////        this.initPresenter();//不合适
//    }

    /**
     * 统计操作
     */
    @Override
    protected void onResume() {
        super.onResume();
    }

    /**
     * 统计操作
     */
    @Override
    protected void onPause() {
        super.onPause();
    }

    /**
     * 设置布局
     */
    public void setContentView() {
        View layoutView = getLayoutView();
        if (null != layoutView) {
            super.setContentView(layoutView);
        } else {
            super.setContentView(getLayoutId());
        }
    }


    /**
     * 销毁
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null)
            presenter.onDestroy();
        ButterKnife.unbind(this);
        //是否要一个activity 栈，移除当前activity
        App.getInstance().removeActivity(this);
        //是否需要处理progressBar 的上下文，目测不要吧
    }

    /*************************
     * loading部分
     ******************************/

    private void initLoading() {
        progressBar = new ProgressDialogBar(context);
    }


    @Override
    public void showLoading() {
        if (context != null && progressBar != null) {//&& !progressBar.isShowing() progrssBar 重写处理
            progressBar.show();
        }
    }

    @Override
    public void dismissLoading() {
        if (context != null && progressBar != null) { //&& progressBar.isShowing()  progrssBar 重写处理
            progressBar.dismiss();
        }
    }

    @Override
    public void showMsg(String msg) {
        TagUtil.showToast(this, msg);
    }

    /************************* loading部分 ******************************/

    /*************************抽象方法*******************************/
    /**
     * 布局layoutId
     *
     * @return
     */
    public abstract int getLayoutId();

    public View getLayoutView() {
        return null;
    }

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 数据展示在view上
     */
    public abstract void initView();

    /**
     * 简单页面无需mvp就不用管此方法即可
     */
    public abstract void initPresenter();
    /*************************抽象方法*******************************/
}
