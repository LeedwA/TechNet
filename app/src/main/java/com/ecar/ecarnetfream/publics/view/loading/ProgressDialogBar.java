package com.ecar.ecarnetfream.publics.view.loading;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.widget.TextView;

import com.ecar.ecarnetfream.R;


/**
 * 自定义进度条
 *
 * @author Administrator
 */
public class ProgressDialogBar extends Dialog{
    private  AnimationDrawable animationDrawable = null;
    private  ImageView im;
    private LoadingHelper loadingHelper = null;
    /**
     * 退出时间
     */
    private long mExitTime;
    /**
     * 退出间隔
     */
    private final int INTERVAL = 1500;

//    public ProgressDialogBar(Context context) {
//        super(context);
//    }

    public ProgressDialogBar(Context context, int theme) {
        super(context, theme);
        loadingHelper = new LoadingHelper();
        this.setContentView(R.layout.progress_dialog);
        this.setCanceledOnTouchOutside(false);
    }

    public ProgressDialogBar(Context context) {
        this(context,R.style.ProgressDialog);
//        loadingHelper = new LoadingHelper();
//        ProgressDialogBar = new ProgressDialogBar(context, R.style.ProgressDialog);
//        ProgressDialogBar.setContentView(R.layout.progress_dialog);
//        ProgressDialogBar.setCanceledOnTouchOutside(false);
//        ImageView im1 = (ImageView) ProgressDialogBar.findViewById(R.id.progress_img2);
//        ImageView im2 = (ImageView) ProgressDialogBar.findViewById(R.id.progress_img3);
//        Animation anim1 = AnimationUtils.loadAnimation(context, R.anim.rotate_left);
//        Animation anim2 = AnimationUtils.loadAnimation(context, R.anim.rotate_right);
//        LinearInterpolator lir = new LinearInterpolator();
//        anim1.setInterpolator(lir);
//        anim2.setInterpolator(lir);
//        im1.startAnimation(anim1);
//        im2.startAnimation(anim2);
//        return ProgressDialogBar;
    }

    @Override
    public void show() {
        loadingHelper.addCount();
        if(!isShowing()){
            super.show();
        }
    }

    @Override
    public void dismiss() {
        loadingHelper.subCount();
        if(loadingHelper.isZero()){
            super.dismiss();
        }
    }

    public void animchange(Context context) {
        animationDrawable = (AnimationDrawable) im.getBackground();
        animationDrawable.setOneShot(false);
        animationDrawable.start();
    }

//    public ProgressDialogBar setTitile(String strTitle) {
//        return ProgressDialogBar;
//    }


//    public void dissmissPro() {
//        animationDrawable.stop();
//        ProgressDialogBar.dismiss();
//    }

    /**
     * [Summary]
     * setMessage 提示内容
     *
     * @param strMessage
     * @return
     */
    public void setProgressMsg(String strMessage) {
        TextView tvMsg = (TextView) findViewById(R.id.text_progress);
        if (tvMsg != null)
            tvMsg.setText(strMessage);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis() - mExitTime > INTERVAL) {
                mExitTime = System.currentTimeMillis();
                return true;
            } else
                return super.onKeyUp(keyCode, event);
        }
        return super.onKeyUp(keyCode, event);
    }
}
