package com.ecar.ecarnetfream.publics.view.prompt;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

import com.ecar.ecarnetfream.R;


/**
 * Created by Administrator on 2015/11/25 0025.
 */
public class Prompt_Button extends Button {

    private boolean canTxChange;

    public Prompt_Button(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initParams(context, attrs, defStyleAttr);
    }

    private void initParams(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.Prompt_Button, defStyleAttr, 0);
        canTxChange = typedArray.getBoolean(R.styleable.Prompt_Button_canTxChange, true);
        typedArray.recycle();
    }

    public Prompt_Button(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public Prompt_Button(Context context) {
        this(context,null);
    }

    /**
     * 重写设置背景方法，不让其设置背景
     * @param resid
     */
    @Override
    public void setBackgroundResource(int resid) {
//        super.setBackgroundResource(resid);
        if(canTxChange&&!"更新".equals(this.getText())&&!"立即更新".equals(this.getText())){
            this.setText("知道了");
        }
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        super.setText(text, type);
    }

    @Override
    public void setVisibility(int visibility) {
        super.setVisibility(visibility);
        if(visibility== View.GONE&&buttonGoneListener!=null){
            buttonGoneListener.cancelGoneCallBack();
        }

    }
    public interface ButtonGoneListener{
        void cancelGoneCallBack();
    }
    private ButtonGoneListener buttonGoneListener;

    public void setButtonGoneListener(ButtonGoneListener listener){
        this.buttonGoneListener = listener;
    }
}
