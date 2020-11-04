package com.jkabe.app.box.util;

import com.jkabe.app.box.bean.ActiveBean;
import com.jkabe.app.box.bean.AddressBean;
import com.jkabe.app.box.bean.AddressInfo;
import com.jkabe.app.box.bean.AssetsBean;
import com.jkabe.app.box.bean.BannerVo;
import com.jkabe.app.box.bean.Battery;
import com.jkabe.app.box.bean.BoxInfo;
import com.jkabe.app.box.bean.Brand;
import com.jkabe.app.box.bean.BrandVo;
import com.jkabe.app.box.bean.CarInfo;
import com.jkabe.app.box.bean.CarRulesVO;
import com.jkabe.app.box.bean.CarSafeVO;
import com.jkabe.app.box.bean.CarVo;
import com.jkabe.app.box.bean.CartBean;
import com.jkabe.app.box.bean.CommonalityModel;
import com.jkabe.app.box.bean.EarlyInfo;
import com.jkabe.app.box.bean.Electronic;
import com.jkabe.app.box.bean.EnceInfo;
import com.jkabe.app.box.bean.GoodBean;
import com.jkabe.app.box.bean.HealthItemVO;
import com.jkabe.app.box.bean.ImageInfo;
import com.jkabe.app.box.bean.InsureInfo;
import com.jkabe.app.box.bean.LatInfo;
import com.jkabe.app.box.bean.LeftVo;
import com.jkabe.app.box.bean.LocgistBean;
import com.jkabe.app.box.bean.Massage;
import com.jkabe.app.box.bean.Money;
import com.jkabe.app.box.bean.OdbAndLocationVO;
import com.jkabe.app.box.bean.Oil;
import com.jkabe.app.box.bean.OilInfo;
import com.jkabe.app.box.bean.OrderBean;
import com.jkabe.app.box.bean.OrderInfo;
import com.jkabe.app.box.bean.OrderVo;
import com.jkabe.app.box.bean.OreInfo;
import com.jkabe.app.box.bean.PackageBean;
import com.jkabe.app.box.bean.PayBean;
import com.jkabe.app.box.bean.StoreInfo;
import com.jkabe.app.box.bean.TabBean;
import com.jkabe.app.box.bean.Travrt;
import com.jkabe.app.box.bean.TripVo;
import com.jkabe.app.box.bean.Typeitems;
import com.jkabe.app.box.bean.Usdinfo;
import com.jkabe.app.box.bean.UsdtBean;
import com.jkabe.app.box.bean.UserInfo;
import com.jkabe.app.box.bean.Verison;
import com.jkabe.app.box.bean.Violation;
import com.jkabe.app.box.bean.YearCar;
import com.jkabe.app.box.bean.icadBean;

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


    public static StoreInfo getStoreDeilJson(JSONObject object) {
        List<StoreInfo> infos = new ArrayList<>();
        JSONObject jsonObject = object.optJSONObject("result");
        JSONObject jsonArray = jsonObject.optJSONObject("storeInfo");
        StoreInfo info = (StoreInfo) JsonUtilComm.jsonToBean(jsonArray.toString(), StoreInfo.class);
        return info;
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

    public static List<OreInfo> getBespokemoniesJson1(JSONObject object) {
        List<OreInfo> infos = new ArrayList<>();
        JSONArray jsonArray = object.optJSONArray("result");
        for (int i = 0; i < jsonArray.length(); i++) {
            OreInfo info = (OreInfo) JsonUtilComm.jsonToBean(jsonArray.optJSONObject(i).toString(), OreInfo.class);
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

    public static List<Massage> getEarlyInfoJson1(JSONObject object) {
        List<Massage> infos = new ArrayList<>();
        JSONObject jsonObject = object.optJSONObject("result");
        JSONArray jsonArray = jsonObject.optJSONArray("items");
        for (int i = 0; i < jsonArray.length(); i++) {
            Massage info = (Massage) JsonUtilComm.jsonToBean(jsonArray.optJSONObject(i).toString(), Massage.class);
            infos.add(info);
        }
        return infos;
    }


    public static Massage getEarlyInfoJson2(JSONObject object) {
        JSONObject jsonObject = object.optJSONObject("result");
        Massage info = (Massage) JsonUtilComm.jsonToBean(jsonObject.toString(), Massage.class);
        return info;
    }

    public static GoodBean getgoodBean(JSONObject object) {
        JSONObject jsonObject = object.optJSONObject("result");
        GoodBean info = (GoodBean) JsonUtilComm.jsonToBean(jsonObject.toString(), GoodBean.class);
        return info;
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


    public static List<TripVo> getCarSafeVOJson1(JSONObject object) {
        List<TripVo> infos = new ArrayList<>();
        JSONObject jsonObject = object.optJSONObject("result");
        JSONArray jsonArray = jsonObject.optJSONArray("items");
        for (int i = 0; i < jsonArray.length(); i++) {
            TripVo info = (TripVo) JsonUtilComm.jsonToBean(jsonArray.optJSONObject(i).toString(), TripVo.class);
            infos.add(info);
        }
        return infos;
    }

    public static List<EnceInfo> getCarSafeVOJson2(JSONObject object) {
        List<EnceInfo> infos = new ArrayList<>();
        JSONObject jsonObject = object.optJSONObject("result");
        JSONArray jsonArray = jsonObject.optJSONArray("items");
        for (int i = 0; i < jsonArray.length(); i++) {
            EnceInfo info = (EnceInfo) JsonUtilComm.jsonToBean(jsonArray.optJSONObject(i).toString(), EnceInfo.class);
            infos.add(info);
        }
        return infos;
    }

    public static List<InsureInfo> getInsureInfoJson2(JSONObject object) {
        List<InsureInfo> infos = new ArrayList<>();
        JSONObject jsonObject = object.optJSONObject("result");
        JSONArray jsonArray = jsonObject.optJSONArray("items");
        for (int i = 0; i < jsonArray.length(); i++) {
            InsureInfo info = (InsureInfo) JsonUtilComm.jsonToBean(jsonArray.optJSONObject(i).toString(), InsureInfo.class);
            infos.add(info);
        }
        return infos;
    }


    public static List<Violation> getViolationJson2(JSONObject object) {
        List<Violation> infos = new ArrayList<>();
        JSONObject jsonObject = object.optJSONObject("result");
        JSONArray jsonArray = jsonObject.optJSONArray("items");
        for (int i = 0; i < jsonArray.length(); i++) {
            Violation info = (Violation) JsonUtilComm.jsonToBean(jsonArray.optJSONObject(i).toString(), Violation.class);
            infos.add(info);
        }
        return infos;
    }

    public static List<OilInfo> getOilInfoJson2(JSONObject object) {
        List<OilInfo> infos = new ArrayList<>();
        JSONObject jsonObject = object.optJSONObject("result");
        JSONArray jsonArray = jsonObject.optJSONArray("items");
        for (int i = 0; i < jsonArray.length(); i++) {
            OilInfo info = (OilInfo) JsonUtilComm.jsonToBean(jsonArray.optJSONObject(i).toString(), OilInfo.class);
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

    public static List<BoxInfo> getBoxJson(JSONObject object) {
        List<BoxInfo> infos = new ArrayList<>();
        JSONObject jsonObject = object.optJSONObject("result");
        JSONArray jsonArray = jsonObject.optJSONArray("items");
        for (int i = 0; i < jsonArray.length(); i++) {
            BoxInfo info = (BoxInfo) JsonUtilComm.jsonToBean(jsonArray.optJSONObject(i).toString(), BoxInfo.class);
            infos.add(info);
        }
        return infos;
    }

    public static List<AssetsBean> getJSONObjectAssetsBean(JSONObject object) {
        List<AssetsBean> infos = new ArrayList<>();
        JSONObject jsonObject = object.optJSONObject("result");
        JSONArray jsonArray = jsonObject.optJSONArray("items");
        for (int i = 0; i < jsonArray.length(); i++) {
            AssetsBean info = (AssetsBean) JsonUtilComm.jsonToBean(jsonArray.optJSONObject(i).toString(), AssetsBean.class);
            infos.add(info);
        }
        return infos;
    }

    public static UsdtBean getJSONObjectUsdtBean(JSONObject object) {
        JSONObject jsonObject = object.optJSONObject("result");
        UsdtBean healthItemVO = (UsdtBean) JsonUtilComm.jsonToBean(jsonObject.toString(), UsdtBean.class);
        return healthItemVO;
    }

    public static List<AddressInfo> getAddressInfo(JSONObject object) {
        List<AddressInfo> infos = new ArrayList<>();
        JSONArray jsonArray = object.optJSONArray("result");
        for (int i = 0; i < jsonArray.length(); i++) {
            AddressInfo info = (AddressInfo) JsonUtilComm.jsonToBean(jsonArray.optJSONObject(i).toString(), AddressInfo.class);
            infos.add(info);
        }
        return infos;
    }

    public static List<Typeitems> getATypeitems(JSONObject object) {
        List<Typeitems> infos = new ArrayList<>();
        JSONObject jsonObject = object.optJSONObject("result");
        JSONArray jsonArray = jsonObject.optJSONArray("typeitems");
        for (int i = 0; i < jsonArray.length(); i++) {
            Typeitems info = (Typeitems) JsonUtilComm.jsonToBean(jsonArray.optJSONObject(i).toString(), Typeitems.class);
            infos.add(info);
        }
        return infos;
    }

    public static List<Usdinfo> getATypeitemsUsdt(JSONObject object) {
        List<Usdinfo> infos = new ArrayList<>();
        JSONObject jsonObject = object.optJSONObject("result");
        JSONArray jsonArray = jsonObject.optJSONArray("balanceitems");
        for (int i = 0; i < jsonArray.length(); i++) {
            Usdinfo info = (Usdinfo) JsonUtilComm.jsonToBean(jsonArray.optJSONObject(i).toString(), Usdinfo.class);
            infos.add(info);
        }
        return infos;
    }

    public static icadBean getJSONicon(JSONObject object) {
        JSONObject jsonObject = object.optJSONObject("result");
        icadBean info = (icadBean) JsonUtilComm.jsonToBean(jsonObject.toString(), icadBean.class);
        return info;
    }

    public static List<PackageBean> getStoreListJson(JSONObject object) {
        List<PackageBean> infos = new ArrayList<>();
        JSONArray jsonArray = object.optJSONArray("result");
        for (int i = 0; i < jsonArray.length(); i++) {
            PackageBean info = (PackageBean) JsonUtilComm.jsonToBean(jsonArray.optJSONObject(i).toString(), PackageBean.class);
            infos.add(info);
        }
        return infos;
    }

    public static PayBean getPayJson(JSONObject object) {
        JSONObject jsonObject = object.optJSONObject("result");
        PayBean info = (PayBean) JsonUtilComm.jsonToBean(jsonObject.toString(), PayBean.class);
        return info;
    }

    public static List<GoodBean> getGoodBeanJson(JSONObject object) {
        List<GoodBean> infos = new ArrayList<>();
        JSONObject jsonObject = object.optJSONObject("result");
        JSONArray jsonArray = jsonObject.optJSONArray("items");
        for (int i = 0; i < jsonArray.length(); i++) {
            GoodBean info = (GoodBean) JsonUtilComm.jsonToBean(jsonArray.optJSONObject(i).toString(), GoodBean.class);
            infos.add(info);
        }
        return infos;
    }

    public static List<CartBean> getCartBeanJson(JSONObject object) {

        List<CartBean> infos = new ArrayList<>();
        JSONObject jsonObject = object.optJSONObject("result");
        JSONArray jsonArray = jsonObject.optJSONArray("items");
        for (int i = 0; i < jsonArray.length(); i++) {
            CartBean info = (CartBean) JsonUtilComm.jsonToBean(jsonArray.optJSONObject(i).toString(), CartBean.class);
            infos.add(info);
        }
        return infos;
    }

    public static List<AddressBean> getAddressBeanJson(JSONObject object) {
        List<AddressBean> infos = new ArrayList<>();
        JSONObject jsonObject = object.optJSONObject("result");
        JSONArray jsonArray = jsonObject.optJSONArray("items");
        for (int i = 0; i < jsonArray.length(); i++) {
            AddressBean info = (AddressBean) JsonUtilComm.jsonToBean(jsonArray.optJSONObject(i).toString(), AddressBean.class);
            infos.add(info);
        }
        return infos;
    }

    public static List<OrderBean> getOrderBeanJSON(JSONObject object) {
        List<OrderBean> infos = new ArrayList<>();
        JSONArray jsonArray = object.optJSONArray("result");
        for (int i = 0; i < jsonArray.length(); i++) {
            OrderBean info = (OrderBean) JsonUtilComm.jsonToBean(jsonArray.optJSONObject(i).toString(), OrderBean.class);
            infos.add(info);
        }

        return infos;
    }

    public static OrderVo getorderBean(JSONObject object) {
        JSONObject jsonObject = object.optJSONObject("result");
        OrderVo info = (OrderVo) JsonUtilComm.jsonToBean(jsonObject.toString(), OrderVo.class);
        return info;
    }

    public static List<LocgistBean> getLocgistBeanJSON(JSONObject object) {
        List<LocgistBean> infos = new ArrayList<>();
        JSONArray jsonArray = object.optJSONArray("result");
        for (int i = 0; i < jsonArray.length(); i++) {
            LocgistBean info = (LocgistBean) JsonUtilComm.jsonToBean(jsonArray.optJSONObject(i).toString(), LocgistBean.class);
            infos.add(info);
        }
        return infos;
    }

    public static AddressBean getAddressBeanJSON(JSONObject object) {
        JSONObject jsonObject = object.optJSONObject("result");
        AddressBean info = (AddressBean) JsonUtilComm.jsonToBean(jsonObject.toString(), AddressBean.class);
        return info;
    }

    public static List<ActiveBean> getactiveBeans(JSONObject object) {
        List<ActiveBean> infos = new ArrayList<>();
        JSONArray jsonArray = object.optJSONArray("result");
        for (int i = 0; i < jsonArray.length(); i++) {
            ActiveBean info = (ActiveBean) JsonUtilComm.jsonToBean(jsonArray.optJSONObject(i).toString(), ActiveBean.class);
            infos.add(info);
        }
        return infos;
    }

    public static TabBean getTabBeanJSON(JSONObject object) {
        JSONObject jsonObject = object.optJSONObject("result");
        TabBean info = (TabBean) JsonUtilComm.jsonToBean(jsonObject.toString(), TabBean.class);
        return info;
    }
}
