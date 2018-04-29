package com.ecar.ecarnetfream.login.entity;


import com.ecar.ecarnetwork.bean.ResBase;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 登陆应答解析
 *
 * @author aa
 */
public class ResLogin extends ResBase implements Serializable {
    /*
    *       {
	"username": <String | 手机号>,
	"deviceId": <String | 设备号>,
	"robotDeviceId" <String | 机器人设备号>

	"homeworkId": <Number | 作业id |可选>
	"subjectId": <Number | 科目id>,
	"isDone": <Boolean | 是否完成, 新建为false>
	"content": <String | 作业内容>,
	"startTime": <Number | 开始时间（秒级时间戳）>,
	"endTime" <Number | 结束时间（秒级时间戳）>,
	"leaveMessages": [{
			"duration": <Number | 留言时常 | 可选，类型为语音时上传>
			"messageType": <Number | 留言类型 | 1:文本,2:语音,3: 视频>
			"messageContent": <String | 留言内容 | 语音和视频为url>
		}...]
}
    *
    *
    * */

    @SerializedName("username")
    public String username;
    @SerializedName("deviceId")
    public String deviceId;
    @SerializedName("robotDeviceId")
    public String robotDeviceId;
    @SerializedName("homeworkId")
    public int homeworkId;
    @SerializedName("subjectId")
    public int subjectId;
    @SerializedName("subjectName")
    public String subjectName;
    @SerializedName("icon")
    public String icon;
    @SerializedName("isDone")
    public boolean isDone;
    @SerializedName("content")
    public String content;// 登陆时获取的sessionId
    @SerializedName("startTime")
    public long startTime;// 登陆时获取的sessionId
    @SerializedName("endTime")
    public long endTime;// 登陆时获取的sessionId
    public ArrayList<DataBean> leaveMessages = getData();

    public ArrayList<DataBean> getData() {
        ArrayList<DataBean> list = new ArrayList<DataBean>();
        for (int i = 0; i < 5; i++) {
            DataBean dataBean = new DataBean();
            dataBean.duration = 100;
            dataBean.messageType = 1;
            dataBean.messageContent = "你好你好";
        }
        return list;
    }

    private class DataBean {
        public long duration;
        public int messageType;
        public String messageContent;
    }

    public ResLogin build() {
        username = "18923729010";
        deviceId = "xxxxxx";
        robotDeviceId = "xxxxxx";
        homeworkId = 2000;
        subjectId = 800;
        startTime = 1000000;
        endTime = 1000000;
        subjectName="12321";
        icon="12321321";
        leaveMessages = getData();
        return this;
    }
}
