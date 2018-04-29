package com.ecar.ecarnetfream.publics.view.prompt;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ecar.ecarnetfream.R;
import com.ecar.ecarnetfream.publics.util.AppUiUtil;
import com.ecar.ecarnetfream.publics.util.DataFormatUtil;


public class UpdateDialog extends Dialog {
	Context context;
	private View onlyOneView;
	private Prompt_Button btnSub;
	private Prompt_Button btnCal;
	private TextView tvMsg;
	private View updateLineView;
	private View update_line2;

	public UpdateDialog(Context context) {
		this(context, R.style.CustomProgressDialog);
		// TODO Auto-generated constructor stub
	}

	public UpdateDialog(Context context, int theme) {
		super(context, R.style.CustomProgressDialog);
		this.context = context;
	}
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_update);
		initView();
	}

	private void initView() {
		View promptView = findViewById(R.id.update_layout);
		AppUiUtil.setViewsWith(context, 0.78, promptView);//设置宽度
		onlyOneView = findViewById(R.id.only_one_button);
		tvMsg = (TextView) findViewById(R.id.prompt_text_update);
		updateLineView = findViewById(R.id.update_up_line);
		update_line2 = findViewById(R.id.update_line2);
		initBtn();
		//设置里顶部距离
//		Window dialogWindow = getWindow();
//		WindowSize winSize = AppUiUtil.getWindowSize(context);
////		dialogWindow.getDecorView().setPadding(0, 0, 0, winSize[0]/8);
//		dialogWindow.setGravity(Gravity.TOP);
//		WindowManager.LayoutParams p = dialogWindow.getAttributes(); // 获取对话框当前的参数值
//		p.y = winSize.winHeight/4;
//		dialogWindow.setAttributes(p);
	}

	private void initBtn() {
		btnSub = (Prompt_Button)findViewById(R.id.prompt_sub);
		btnCal = (Prompt_Button)findViewById(R.id.prompt_cal);
		btnCal.setButtonGoneListener(new Prompt_Button.ButtonGoneListener() {
			@Override
			public void cancelGoneCallBack() {
				onlyOneView.setVisibility(View.VISIBLE);
				tvMsg.setGravity(Gravity.LEFT);
				tvMsg.setTextColor(getContext().getResources().getColor(R.color.gray1));
				updateLineView.setVisibility(View.GONE);
				LinearLayout.LayoutParams line2Params = (LinearLayout.LayoutParams) update_line2.getLayoutParams();
				line2Params.topMargin = 0;
				update_line2.setLayoutParams(line2Params);
				tvMsg.setTextSize(16);
				LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) tvMsg.getLayoutParams();
				layoutParams.gravity = Gravity.LEFT;
				layoutParams.topMargin = DataFormatUtil.setDip2px(getContext(), 0f);
				layoutParams.leftMargin = DataFormatUtil.setDip2px(getContext(), 66f);
				layoutParams.bottomMargin = DataFormatUtil.setDip2px(getContext(), 20f);
				tvMsg.setLayoutParams(layoutParams);
			}
		});
	}

	public void setButton1OnClickListener(
			View.OnClickListener onClickListener) {
		btnCal.setText("以后再说");
		btnCal.setOnClickListener(onClickListener);
	}

	public void setButton2OnClickListener(
			View.OnClickListener onClickListener, String text,
			int BackId) {
		btnSub.setText(text);
		btnSub.setBackgroundResource(BackId);
		btnSub.setOnClickListener(onClickListener);
	}

	public void hideCustomBtn1() {
		// findViewById(R.id.view_divider).setVisibility(View.GONE);
		btnCal.setVisibility(View.GONE);
	}

	/**
	 * 
	 * [Summary] setMessage 提示内容
	 * 
	 * @param strMessage
	 * @return
	 * 
	 */
	public void setProgressMsg(String strMessage) {

		if (tvMsg != null)
			tvMsg.setText(strMessage);
	}
	//提示内容描述
	public void setProgressMsgDes(String strMessageContent) {
		TextView tvMsgDes = (TextView) findViewById(R.id.prompt_text_update_desc);
		if (tvMsgDes != null){
			if(TextUtils.isEmpty(strMessageContent)){
				tvMsgDes.setVisibility(View.GONE);
//				findViewById(R.id.prompt_img).setVisibility(View.VISIBLE);
			}else{
				//设置view margin
				TextView tvMsg = (TextView) findViewById(R.id.prompt_text_update);
				AppUiUtil.setViewMargin(context,tvMsg,15,15,15,15);
				AppUiUtil.setViewMargin(context,tvMsgDes,15,15,15,10);
				tvMsgDes.setText(strMessageContent);
				updateLineView.setVisibility(View.VISIBLE);
			}
		}
	}

	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			return true;
		}
		return super.onKeyUp(keyCode, event);
	}
}
