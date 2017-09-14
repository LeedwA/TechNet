package com.ecar.ecarnetfream.login.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


import com.ecar.ecarnetfream.R;
import com.ecar.ecarnetfream.login.entity.ResLogin;
import com.ecar.ecarnetfream.login.interfaces.LoginContract;
import com.ecar.ecarnetfream.login.model.LoginModel;
import com.ecar.ecarnetfream.login.presenter.LoginPresenter;
import com.ecar.ecarnetfream.publics.base.App;
import com.ecar.ecarnetfream.publics.base.BaseActivity;
import com.ecar.ecarnetfream.publics.util.TagUtil;
import com.ecar.ecarnetfream.publics.view.prompt.UpdateDialog;
import com.ecar.ecarnetwork.bean.ResBase;
import com.ecar.ecarnetwork.db.SettingPreferences;
import com.google.gson.Gson;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/1/14.
 */
public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.View {


    @Bind(R.id.et_name)
    EditText etName;
    @Bind(R.id.et_pwd)
    EditText etPwd;
    @Bind(R.id.btn_submit)
    TextView btnSubmit;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initData() {

    }

    @Override
    public void initView() {
        Log.d("tagutil", "initView: " + new Gson().toJson((new ResLogin()).build()));
    }

    @Override
    public void initPresenter() {
        presenter = new LoginPresenter(context, this, new LoginModel());
    }


    @Override
    public void loginSuccess(ResBase resBase) {
        showMsg(resBase.msg);
    }

    @Override
    public void showMsg(String msg) {
        TagUtil.showToast(this, msg);
    }


    @OnClick(R.id.btn_submit)
    public void onClick() {
//        TagUtil.showToast(this,"click");
        presenter.login(etName.getText().toString(), etPwd.getText().toString());
    }

    @Override
    public void reLogin(Context context, String msg) {
        if (context != null) {
            TagUtil.showLogDebug("显示登出dialog" + (context instanceof Activity));
            final UpdateDialog dialog = new UpdateDialog(context);
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
            dialog.setProgressMsg(msg);
            dialog.hideCustomBtn1();
            dialog.setProgressMsgDes("");
            dialog.findViewById(R.id.prompt_sub).setBackgroundResource(
                    R.drawable.pub_btn_submit_selector);
            dialog.setButton2OnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
//                    Intent intent = new Intent(context, UpdateService.class);
//                    context.stopService(intent);// 关闭服务
                    App.getInstance().quit();
                    SettingPreferences.getDefault(context).clearUserMsg();
                    context.startActivity(new Intent(context,
                            LoginActivity.class));
                    dialog.dismiss();
                }
            }, "确定", R.drawable.btn_yellow_selector);

        } else {
            TagUtil.showLogDebug("Subscriber 上下文为空");
        }

    }

    private void isEquels(String method, String str1, String str2) {
        Log.d("tagutil", method + "标准值=" + str1);
        Log.d("tagutil", method + "加密后=" + str2);

        if (!TextUtils.isEmpty(str1) && !TextUtils.isEmpty(str2)) {
            Log.d("tagutil", method + "  result: " + str1.trim().equals(str2.trim()));
        } else {
            Log.d("tagutil", method + "  result: " + false);
        }
    }
}
