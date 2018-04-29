package com.ecar.ecarnetwork.util.major;

import android.text.TextUtils;

import com.ecar.ecarnetwork.db.SettingPreferences;
import com.ecar.ecarnetwork.http.api.ApiBox;

import java.util.HashMap;
import java.util.TreeMap;


/**
 * ===============================================
 * <p>
 * 类描述:
 * <p>
 * 创建人:   happy
 * <p>
 * 创建时间: 2016/7/7 0007 上午 11:46
 * <p>
 * 修改人:   happy
 * <p>
 * 修改时间: 2016/7/7 0007 上午 11:46
 * <p>
 * 修改备注:
 * <p>
 * ===============================================
 */
public class Major {




    private static HashMap<String, String> tMap;

    public static HashMap<String, String> getDefaultMap() {
        synchronized (Major.class) {
            if (tMap == null) {
                tMap = new HashMap<String, String>();
            } else {
                tMap.clear();
            }
        }
        return tMap;
    }


}
