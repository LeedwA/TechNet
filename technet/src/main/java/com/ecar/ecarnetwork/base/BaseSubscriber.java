package com.ecar.ecarnetwork.base;

import android.content.Context;
import android.net.ParseException;
import android.text.TextUtils;

import com.ecar.ecarnetwork.bean.ResBase;
import com.ecar.ecarnetwork.http.api.ApiBox;
import com.ecar.ecarnetwork.http.exception.CommonException;
import com.ecar.ecarnetwork.http.exception.InvalidException;
import com.ecar.ecarnetwork.http.exception.UserException;
import com.ecar.ecarnetwork.http.util.ConstantsLib;
import com.ecar.ecarnetwork.http.util.TagLibUtil;
import com.ecar.ecarnetwork.interfaces.security.IInvalid;
import com.google.gson.JsonParseException;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;
import rxbus.ecaray.com.rxbuslib.rxbus.RxBus;

import static com.ecar.ecarnetwork.http.exception.CommonException.USER_REFRESHTOKEN_ERORR;
import static com.ecar.ecarnetwork.http.exception.CommonException.USER_RELOGIN;
import static com.ecar.ecarnetwork.http.exception.CommonException.USER_ACESSTOKEN_ERORR;
import static com.ecar.ecarnetwork.http.util.ConstantsLib.IS_UNCHECK;


/**
 * 订阅者基类：对异常统一处理
 * 目测权限处理可以放这里做
 */
public abstract class BaseSubscriber<T extends ResBase> extends Subscriber<T> {

    private Context context;
    private IInvalid iInvalid;

    public BaseSubscriber(Context context, IInvalid iInvalid) {
        this.context = context;
        this.iInvalid = iInvalid;
    }

    @Override
    public void onNext(T t) {
        if (t instanceof ResBase) {
            ResBase base = (ResBase) t;
            if (base != null) {
                if (IS_UNCHECK) {
                    this.onUserSuccess(t);
                    return;
                }
                String nMsg = ConstantsLib.serverCodeMap.get(base.code);
                if (!TextUtils.isEmpty(nMsg)) {
                    base.msg = nMsg;
                }

                if (!"200".equals(base.code)) {//非成功
                    this.onUserError(new CommonException(new UserException(base.code, base.msg, base)));
                } else {//if(base.state == 1)
                    this.onUserSuccess(t);
                }
                if ("60016".equals(base.code)) {  //重复登录
                    RxBus.getDefault().post(USER_RELOGIN);
                }
                if ("401".equals(base.code)) {  //accessToken失效
                    RxBus.getDefault().post(USER_ACESSTOKEN_ERORR);
                }
                if ("60024".equals(base.code)) {  //refreshToken失效
                    RxBus.getDefault().post(USER_REFRESHTOKEN_ERORR);
                }
            } else {
                this.onUserError(new CommonException(new UserException("请求服务器失败")));
            }
        } else {
            this.onOtherNext(t);
        }
    }

    protected void onOtherNext(T t) {

    }

    protected abstract void onUserSuccess(T t);

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        CommonException ex = null;
        try {
            if (e instanceof UserException) {   // 用户自定义需要处理的异常
                /**
                 * 1.自定义异常处理
                 */
                UserException resultException = (UserException) e;
                ex = new CommonException(resultException);
                onUserError(ex);
            } else if (e instanceof InvalidException) {
                /**
                 * 2.非法异常处理：2.1 强制重新登录 2.2 校验错误 2.3 so on
                 */
            } else if (e instanceof JsonParseException
                    || e instanceof JSONException
                    || e instanceof ParseException) {
                /**
                 * 2.解析错误
                 */
                ex = new CommonException(e, CommonException.FLAG_PARSE_ERROR);
                onUnifiedError(ex);   //
            } else if (
                    e instanceof ConnectException
                            || e instanceof SocketTimeoutException
                            || e instanceof SocketException || e instanceof UnknownHostException) {
                /**
                 * 3.网络错误
                 */
                if (e instanceof SocketTimeoutException) {
                    ex = new CommonException(e, CommonException.FLAG_NET_TIME_OUT);
                } else {
                    ex = new CommonException(e, CommonException.FLAG_NET_ERROR);
                }
                onUnifiedError(ex);
            } else if (e instanceof SecurityException) {
                /**
                 * 4.权限未许可
                 */
                ex = new CommonException(e, CommonException.FLAG_PERMISSION_ERROR);
                onUnifiedError(ex);
            } else {
                if (e instanceof HttpException && (
                        "401".equals(String.valueOf(((HttpException) e).code())) ||
                                "404".equals(String.valueOf(((HttpException) e).code())) ||
                                "500".equals(String.valueOf(((HttpException) e).code())))) {
                    if ("401".equals(String.valueOf(((HttpException) e).code()))) {
                        RxBus.getDefault().post(USER_ACESSTOKEN_ERORR);
                    }
                    /**
                     * 4.网络错误
                     */
                    String code = String.valueOf(((HttpException) e).code());
                    ex = new CommonException(e, code);

                } else {
                    /**
                     * 5.未知错误
                     */
                    ex = new CommonException(e, CommonException.FLAG_UNKNOWN);
                }
                if (!ConstantsLib.DEBUG) {
                    ex.setDoNothing(true);
                    ex.setMsg("");
                }
                onUnifiedError(ex);   //未知错误

            }
            resetContext();
            if (ConstantsLib.DEBUG && ex != null) {
                e.printStackTrace();
                String errorMsg = ex.getMessage().toString();
                TagLibUtil.showLogError(BaseSubscriber.class.getSimpleName() + ": " + errorMsg);
                //bug写入日志
//            FileUtil.writeDebugTextFile(errorMsg);//不能简单的这么写，要考虑弄线程池处理
            }
        } catch (Exception exception) {
//            ex = new CommonException(exception, CommonException.FLAG_UNKNOWN);
////            onUnifiedError(ex);   //未知错误 这一句需删掉，网络出错会陷入无线循环。
            resetContext();
            if (ConstantsLib.DEBUG) {
                if (e != null) {
                    e.printStackTrace();
                }
                if (exception != null) {
                    exception.printStackTrace();
                }
            }
////            else if(ConstantsLib.DEBUG && exception==null){//这一句不应该执行到
////                ex.printStackTrace();
////            }
        }
    }

    @Override
    public void onCompleted() {
        resetContext();
    }

    //    protected abstract void onError(ApiException ex);
//    protected abstract void onResult1Error(ApiException ex);

    /**
     * 统一的 默认处理
     * 默认自动处理网络、解析、未知异常；弹提示
     * 有需要子类可以重写，不走super.onError 就可以屏蔽掉弹提示
     *
     * @param ex
     */
    protected void onUnifiedError(CommonException ex) {
        if (!ex.isDoNothing()) {
            showMsg(context, ex.getMsg());
        }
    }

    /**
     * 自定义处理：
     * 接口返回 state 失败、自定义处理返回失败
     * 需要处理时 子类重写
     */
    protected void onUserError(CommonException ex) {
        if (!ex.isDoNothing()) {
            showMsg(context, ex.getMsg());
        }
    }


    /**
     * 上下文置空
     */
    private void resetContext() {
        context = null;
    }

    /**
     * 提示msg
     *
     * @param context
     * @param msg
     */
    private void showMsg(Context context, String msg) {
        if (context != null) {
            String reMsg = TextUtils.isEmpty(msg) ? "" : msg;
            TagLibUtil.showToast(context, reMsg);
        } else {
            TagLibUtil.showLogDebug("Subscriber 上下文为空");
        }
    }


    /**
     * 校验码失败
     */
    protected void onCheckNgisFailed(Context context, InvalidException commonException) {
        if (context != null || commonException != null) {
            String reMsg = TextUtils.isEmpty(commonException.getMsg()) ? "" : commonException.getMsg();
            TagLibUtil.showToast(context, reMsg);
        } else {
            TagLibUtil.showLogDebug("Subscriber 上下文为空");
        }
    }


    private void showDialog(Context context, String msg) {
        if (iInvalid != null) {
            iInvalid.reLogin(context, msg);
        }
    }
}
