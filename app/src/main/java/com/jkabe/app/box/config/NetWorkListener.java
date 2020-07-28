package com.jkabe.app.box.config;

import com.jkabe.app.box.bean.CommonalityModel;
import org.json.JSONObject;

/**
 * Created by lenovo on 2016/9/18.
 */
public interface NetWorkListener {
    void onSucceed(JSONObject object, int id, CommonalityModel commonality);//成功
    void onFail();//失败
    void onError(Exception e);//错误
}
