package com.jkabe.app.android.util;

import com.jkabe.app.android.bean.BannerVo;
import com.jkabe.app.android.bean.Battery;
import com.jkabe.app.android.bean.Brand;
import com.jkabe.app.android.bean.BrandVo;
import com.jkabe.app.android.bean.CarInfo;
import com.jkabe.app.android.bean.CarRulesItemVO;
import com.jkabe.app.android.bean.CarRulesVO;
import com.jkabe.app.android.bean.CarSafeVO;
import com.jkabe.app.android.bean.CarVo;
import com.jkabe.app.android.bean.CommonalityModel;
import com.jkabe.app.android.bean.EarlyInfo;
import com.jkabe.app.android.bean.Electronic;
import com.jkabe.app.android.bean.HealthItemVO;
import com.jkabe.app.android.bean.ImageInfo;
import com.jkabe.app.android.bean.LatInfo;
import com.jkabe.app.android.bean.LeftVo;
import com.jkabe.app.android.bean.Money;
import com.jkabe.app.android.bean.OdbAndLocationVO;
import com.jkabe.app.android.bean.Oil;
import com.jkabe.app.android.bean.OrderInfo;
import com.jkabe.app.android.bean.StoreInfo;
import com.jkabe.app.android.bean.Travrt;
import com.jkabe.app.android.bean.UserInfo;
import com.jkabe.app.android.bean.Verison;
import com.jkabe.app.android.bean.YearCar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;


/**
 * Json解析
 *
 * @author Administrator
 */

public class JsonParse {
    private static JsonParse jsonParse = null;
    public CommonalityModel commonality;

    public static synchronized JsonParse getInstance() {
        if (jsonParse == null)
            jsonParse = new JsonParse();
        return jsonParse;
    }

    public static Verison getVerisonUserInfo(JSONObject object) {
        JSONObject jsonObject = object.optJSONObject("result");
        Verison verison = (Verison) JsonUtilComm.jsonToBean(jsonObject.toString(), Verison.class);
        return verison;
    }
    public static List<BrandVo> getBespokebrandsJson1(JSONObject object) {
        List<BrandVo> infos = new ArrayList<>();
        JSONArray jsonArray = object.optJSONArray("result");
        for (int i = 0; i < jsonArray.length(); i++) {
            BrandVo info = (BrandVo) JsonUtilComm.jsonToBean(jsonArray.optJSONObject(i).toString(), BrandVo.class);
            infos.add(info);
        }
        return infos;
    }

    public static List<YearCar> getbrandList(JSONObject object) {
        List<YearCar> infos = new ArrayList<>();
        JSONArray jsonArray = object.optJSONArray("result");
        for (int i = 0; i < jsonArray.length(); i++) {
            YearCar info = (YearCar) JsonUtilComm.jsonToBean(jsonArray.optJSONObject(i).toString(), YearCar.class);
            infos.add(info);
        }
        return infos;
    }



    public static List<Money> getBespokemoniesJson(JSONObject object) {
        List<Money> infos = new ArrayList<>();
        JSONObject jsonObject = object.optJSONObject("result");
        JSONArray jsonArray = jsonObject.optJSONArray("items");
        for (int i = 0; i < jsonArray.length(); i++) {
            Money info = (Money) JsonUtilComm.jsonToBean(jsonArray.optJSONObject(i).toString(), Money.class);
            infos.add(info);
        }
        return infos;
    }
    public static List<Brand> getBespokebrandsJson(JSONObject object) {
        List<Brand> infos = new ArrayList<>();
        JSONArray jsonArray = object.optJSONArray("result");
        for (int i = 0; i < jsonArray.length(); i++) {
            Brand info = (Brand) JsonUtilComm.jsonToBean(jsonArray.optJSONObject(i).toString(), Brand.class);
            infos.add(info);
        }
        return infos;
    }

    public static UserInfo getUserInfo(JSONObject object) {
        JSONObject jsonObject = object.optJSONObject("result");
        UserInfo userInfo = (UserInfo) JsonUtilComm.jsonToBean(jsonObject.toString(), UserInfo.class);
        return userInfo;
    }

    public static List<BannerVo> getBannerListJson(JSONObject object) {
        List<BannerVo> bannerVos = new ArrayList<>();
        JSONArray jsonArray = object.optJSONArray("result");
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.optJSONObject(i);
            BannerVo bean = (BannerVo) JsonUtilComm.jsonToBean(jsonObject.toString(), BannerVo.class);
            bannerVos.add(bean);
        }
        return bannerVos;
    }

    public static List<LeftVo> getTagJson(JSONObject object) {
        List<LeftVo> voList = new ArrayList<>();
        String result = object.optString("result");
        try {
            JSONArray array = new JSONArray(result);
            if (array != null && array.length() > 0) {
                for (int i = 0; i < array.length(); i++) {
                    JSONObject jsonObject = array.optJSONObject(i);
                    LeftVo bean = (LeftVo) JsonUtilComm.jsonToBean(jsonObject.toString(), LeftVo.class);
                    voList.add(bean);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return voList;
    }


    public static List<StoreInfo> getStoreJson(JSONObject object) {
        List<StoreInfo> infos = new ArrayList<>();
        JSONObject jsonObject = object.optJSONObject("result");
        JSONArray jsonArray = jsonObject.optJSONArray("items");
        for (int i = 0; i < jsonArray.length(); i++) {
            StoreInfo info = (StoreInfo) JsonUtilComm.jsonToBean(jsonArray.optJSONObject(i).toString(), StoreInfo.class);
            infos.add(info);
        }
        return infos;
    }

    public static List<ImageInfo> getImageInfoJson(JSONObject object) {
        List<ImageInfo> infos = new ArrayList<>();
        JSONObject jsonObject = object.optJSONObject("result");
        JSONArray jsonArray = jsonObject.optJSONArray("items");
        for (int i = 0; i < jsonArray.length(); i++) {
            ImageInfo info = (ImageInfo) JsonUtilComm.jsonToBean(jsonArray.optJSONObject(i).toString(), ImageInfo.class);
            infos.add(info);
        }
        return infos;
    }

    public static CarInfo getCarInfoJson(JSONObject object) {
        JSONObject jsonObject = object.optJSONObject("result");
        CarInfo info = (CarInfo) JsonUtilComm.jsonToBean(jsonObject.toString(), CarInfo.class);
        return info;
    }

    public static List<OrderInfo> getStoreOrderJson(JSONObject object) {
        List<OrderInfo> infos = new ArrayList<>();
        JSONObject jsonObject = object.optJSONObject("result");
        JSONArray jsonArray = jsonObject.optJSONArray("items");
        for (int i = 0; i < jsonArray.length(); i++) {
            OrderInfo info = (OrderInfo) JsonUtilComm.jsonToBean(jsonArray.optJSONObject(i).toString(), OrderInfo.class);
            infos.add(info);
        }
        return infos;
    }

    public static List<Battery> getBatterieJson(JSONObject object) {
        List<Battery> batteryList = new ArrayList<>();
        JSONArray jsonArray = object.optJSONArray("result");
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.optJSONObject(i);
            Battery bean = (Battery) JsonUtilComm.jsonToBean(jsonObject.toString(), Battery.class);
            batteryList.add(bean);
        }
        return batteryList;
    }

    public static List<Electronic> getElectronicJson(JSONObject object) {
        List<Electronic> infos = new ArrayList<>();
        JSONObject jsonObject = object.optJSONObject("result");
        JSONArray jsonArray = jsonObject.optJSONArray("items");
        for (int i = 0; i < jsonArray.length(); i++) {
            Electronic info = (Electronic) JsonUtilComm.jsonToBean(jsonArray.optJSONObject(i).toString(), Electronic.class);
            infos.add(info);
        }
        return infos;
    }


    public static CarVo getCarVoJson(JSONObject object) {
        JSONObject jsonObject = object.optJSONObject("result");
        CarVo carVo = (CarVo) JsonUtilComm.jsonToBean(jsonObject.toString(), CarVo.class);
        return carVo;
    }

    public static Oil getOilJson(JSONObject object) {
        JSONObject jsonObject = object.optJSONObject("result");
        Oil oil = (Oil) JsonUtilComm.jsonToBean(jsonObject.toString(), Oil.class);
        return oil;
    }

    public static List<EarlyInfo> getEarlyInfoJson(JSONObject object) {
        List<EarlyInfo> infos = new ArrayList<>();
        JSONObject jsonObject = object.optJSONObject("result");
        JSONArray jsonArray = jsonObject.optJSONArray("items");
        for (int i = 0; i < jsonArray.length(); i++) {
            EarlyInfo info = (EarlyInfo) JsonUtilComm.jsonToBean(jsonArray.optJSONObject(i).toString(), EarlyInfo.class);
            infos.add(info);
        }
        return infos;
    }

    public static List<CarSafeVO> getCarSafeVOJson(JSONObject object) {
        List<CarSafeVO> infos = new ArrayList<>();
        JSONObject jsonObject = object.optJSONObject("result");
        JSONArray jsonArray = jsonObject.optJSONArray("items");
        for (int i = 0; i < jsonArray.length(); i++) {
            CarSafeVO info = (CarSafeVO) JsonUtilComm.jsonToBean(jsonArray.optJSONObject(i).toString(), CarSafeVO.class);
            infos.add(info);
        }
        return infos;
    }

    public static List<Travrt> getTraverJson(JSONObject object) {
        List<Travrt> infos = new ArrayList<>();
        JSONArray jsonArray = object.optJSONArray("result");
        for (int i = 0; i < jsonArray.length(); i++) {
            Travrt info = (Travrt) JsonUtilComm.jsonToBean(jsonArray.optJSONObject(i).toString(), Travrt.class);
            infos.add(info);
        }
        return infos;
    }

    public static List<LatInfo> getBannerTrverJson(JSONObject object) {
        List<LatInfo> infos = new ArrayList<>();
        JSONArray jsonArray = object.optJSONArray("result");
        for (int i = 0; i < jsonArray.length(); i++) {
            LatInfo info = (LatInfo) JsonUtilComm.jsonToBean(jsonArray.optJSONObject(i).toString(), LatInfo.class);
            infos.add(info);
        }
        return infos;
    }

    public static CarRulesVO getCarRulesItemVOJson(JSONObject object) {
        JSONObject jsonObject = object.optJSONObject("result");
        CarRulesVO oil = (CarRulesVO) JsonUtilComm.jsonToBean(jsonObject.toString(), CarRulesVO.class);
        return oil;
    }

    public static HealthItemVO getHealthItemVOJson(JSONObject object) {
        JSONObject jsonObject = object.optJSONObject("result");
        HealthItemVO healthItemVO = (HealthItemVO) JsonUtilComm.jsonToBean(jsonObject.toString(), HealthItemVO.class);
        return healthItemVO;
    }

    public static OdbAndLocationVO buildNonDefaultMapper(JSONObject object) {
        JSONObject jsonObject = object.optJSONObject("result");
        OdbAndLocationVO healthItemVO = (OdbAndLocationVO) JsonUtilComm.jsonToBean(jsonObject.toString(), OdbAndLocationVO.class);
        return healthItemVO;
    }
}
