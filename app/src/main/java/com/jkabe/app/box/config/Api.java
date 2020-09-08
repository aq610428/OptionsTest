package com.jkabe.app.box.config;

public interface Api {
    /******获取验证码******/
    String GET_MOBILE = Config.getOpenNewApi() + "/member/getregistermembermobilevcode";
    int GET_MOBILE_ID = 100001;

    /******注册******/
    String GET_REGISTER = Config.getOpenNewApi() + "/member/memberregister";
    int GET_REGISTER_ID = 100002;

    /******bannar******/
    String GET_ADVERT = Config.getOpenNewApi() + "/resource/getadvertlist";
    int GET_ADVERT_ID = 100003;

    /******车生活三方服务列表信息******/
    String GET_ADVERT_TAG = Config.getOpenNewApi() + "/resource/gettaglist";
    int GET_ADVERT_TAG_ID = 100004;

    String GET_UNMODEL_INFO = Config.getOpenNewApi() + "/store/getBandingStoreInfo";
    int GET_UNMODEL_INFO_ID = 100072;
    /******虚拟货币列表******/
    String GET_COINS_LIST = Config.getOpenNewApi() + "/integral/getcoins";
    int GET_COINS_LIST_ID = 100022;

    /******积分查询******/
    String GET_COINS_INTER = Config.getOpenNewApi() + "/integral/getmemberintegral";
    int GET_COINS_INTER_ID = 100023;

    /******登录******/
    String GET_LOGIN = Config.getOpenNewApi() + "/member/memberlogin";
    int GET_LOGIN_ID = 100003;


    /******获取验证码******/
    String GET_MOBILCODE = Config.getOpenNewApi() + "/member/getmembermobilevcode";
    int GET_MOBILCODE_ID = 100006;


    String GET_UPDATE = Config.getOpenNewApi() + "/member/forgotmemberpassword";
    int GET_UPDATE_ID = 100005;


    /******版本更新******/
    String GET_INTERGRAL_VERSION = Config.getOpenNewApi() + "/app/getandroidversioninfo";
    int GET_INTERGRAL_VERSION_ID = 100028;


    /******更新个人资料******/
    String GET_INTERGRAL_USER = Config.getOpenNewApi() + "/member/savememberdetail";
    int GET_INTERGRAL_USER_ID = 100029;


    /******查询个人资料******/
    String GET_MEID_USER = Config.getOpenNewApi() + "/member/getmemberdetail";
    int GET_MEID_USER_ID = 100030;


    /******更新头像******/
    String GET_UPLOAD_USER = Config.getOpenNewApi() + "/upload/uploaduserimg";
    int GET_UPLOAD_USER_ID = 100031;

    /******驾驶证******/
    String GET_UPLOAD_IMG = Config.getOpenNewApi() + "/upload/uploaddriveingimg";
    int GET_UPLOAD_IMG_ID = 100032;


    /******附近门店******/
    String GET_STORE_VERSION = Config.getOpenNewApi() + "/store/queryNearStoreInfoData";
    int GET_STORE_VERSION_ID = 100031;


    /******绑定门店******/
    String GET_BAND_VERSION = Config.getOpenNewApi() + "/store/bandingStore";
    int GET_BAND_VERSION_ID = 100032;


    /******门店相册******/
    String GET_IMAGE_VERSION = Config.getOpenNewApi() + "/store/queryStoreImageInfoData";
    int GET_IMAGE_VERSION_ID = 100034;

    /******门店相册******/
    String GET_REMOVE_VERSION = Config.getOpenNewApi() + "/store/removeBandingStore";
    int GET_REMOVE_VERSION_ID = 100035;

    /******获取会员车辆信息******/
    String GET_DECICE_VERSION = Config.getOpenNewApi() + "/device/getmemberdeviceinfo";
    int GET_DECICE_VERSION_ID = 100036;


    /******预约开单******/
    String GET_ORDER_VERSION = Config.getOpenNewApi() + "/store/saveStoreAdvanceInfo";
    int GET_ORDER_VERSION_ID = 100037;


    /******预约开单******/
    String GET_ADVANCE_VERSION = Config.getOpenNewApi() + "/store/queryMyAdvanceInfoData";
    int GET_ADVANCE_VERSION_ID = 100038;

    /******预约开单******/
    String GET_ADVANCE_ORDER = Config.getOpenNewApi() + "/store/receiveStoreAdvance";
    int GET_ADVANCE_ORDER_ID = 100039;


    /******电压变化******/
    String GET_ADVANCE_DEVICE = Config.getOpenNewApi() + "/device/getdevicevoltage";
    int GET_ADVANCE_DEVICE_ID = 100040;

    /******电子围栏******/
    String GET_ELECT_DEVICE = Config.getOpenNewApi() + "/device/getelecfenceList";
    int GET_ELECT_DEVICE_ID = 100041;


    /******车辆定位******/
    String GET_CAR_DEVICE = Config.getOpenNewApi() + "/device/getdevicelocation";
    int GET_CAR_DEVICE_ID = 100042;
    /******围栏保存******/
    String GET_SAV_DEVICE = Config.getOpenNewApi() + "/device/saveelecfence";
    int GET_SAV_DEVICE_ID = 100043;

    /******耗油分析******/
    String GET_OIL_DEVICE = Config.getOpenNewApi() + "/device/getdeviceoilanalyse";
    int GET_OIL_DEVICE_ID = 100044;

    /******预警信息******/
    String GET_AGE_DEVICE = Config.getOpenNewApi() + "/member/getmembermessagelist";
    int GET_AGE_DEVICE_ID = 100045;


    /******预警信息******/
    String GET_AGE_DEVICE1 = Config.getOpenNewApi() + "/device/getdevicelocationandobddata";
    int GET_AGE_DEVICE1_ID = 100066;


    /******预警信息******/
    String GET_AGE_MSG = Config.getOpenNewApi() + "/member/getmembermessagelist";
    int GET_AGE_MSGID = 100046;


    /******预警信息******/
    String GET_AGE_DEVICE_INFO = Config.getOpenNewApi() + "/device/getmemberdevicealarmpropertiesinfo";
    int GET_AGE_DEVICE_INFO_ID = 100046;

    /******设置预警信息******/
    String GET_AGE_DEVICE_MISS = Config.getOpenNewApi() + "/device/savememberdevicealarmpropertiesinfo";
    int GET_AGE_DEVICE_MISS_ID = 100047;

    /******轨迹******/
    String GET_TRACK_MISS = Config.getOpenNewApi() + "/device/getdevicetrack";
    int GET_TRACK_MISS_ID = 100048;
    /******轨迹******/
    String GET_TRACK_TRIP = Config.getOpenNewApi() + "/device/getdevicetrip";
    int GET_TRACK_TRIP_ID = 100049;


    /******违章查询******/
    String GET_TRACK_TRIPOL = Config.getOpenNewApi() + "/device/getcehicleviolation";
    int GET_TRACK_TRIPOL_ID = 100050;

    /******违章查询******/
    String GET_TRACK_RESULT = Config.getOpenNewApi() + "/device/getvehicletestresult";
    int GET_TRACK_RESULT_ID = 100051;

    /******品牌列表******/
    String GET_USER_BRAND = Config.getOpenNewApi() + "/resource/getcarbrandlist";
    int GET_USER_BRAND_ID = 100035;

    /******车型列表******/
    String GET_USER_LISR = Config.getOpenNewApi() + "/resource/getcarmodellist";
    int GET_USER_LISR_ID = 100036;


    /******年款车型******/
    String GET_YEAR_LISR = Config.getOpenNewApi() + "/resource/getcarmodelyearlist";
    int GET_YEAR_LISR_ID = 100037;


    /******注册成功******/
    String GET_PUSH_VERSION = Config.getOpenNewApi() + "/member/uploadjgandroidregisterid";
    int GET_PUSH_VERSION_ID = 100029;


    /******绑定车辆******/
    String GET_MODEL_BIND = Config.getOpenNewApi() + "/device/bindingdeviceinfo";
    int GET_MODEL_BIND_ID = 100039;

    /******绑定车辆******/
    String GET_UNMODEL_BIND = Config.getOpenNewApi() + "/device/unbindingdeviceinfo";
    int GET_UNMODEL_BIND_ID = 100071;

    /******挖矿******/
    String GET_UNMODEL_BOX = Config.getOpenNewApi() + "/box/getMemberMiningData";
    int GET_UNMODEL_BOX_ID = 100072;

    /******激活******/
    String GET_ACTIVE_BOX = Config.getOpenNewApi() + "/box/activeMemberMining";
    int GET_ACTIVE_BOX_ID = 100073;

    /******资产流水******/
    String GETRECORD_BOX = Config.getOpenNewApi() + "/box/getBoxAssetsRecordList";
    int GETRECORD_BOX_ID = 100074;

    /******挖矿明细******/
    String MINING_BOX = Config.getOpenNewApi() + "/box/getBoxMiningRecordList";
    int MINING_BOX_ID = 100075;


    /******地址******/
    String MINING_ADDRESS_BOX = Config.getOpenNewApi() + "/box/getMemberCoinTypeAddressData";
    int MINING_ADDRESS_ID = 100076;


    /******提币******/
    String MINING_WITH_BOX = Config.getOpenNewApi() + "/box/withdraw";
    int MINING_WITH_BOX_ID = 100078;


    /******余额******/
    String MINING_BAL_BOX = Config.getOpenNewApi() + "/box/getMemberBalance";
    int MINING_BAL_BOX_ID = 100079;


    /******余额******/
    String MINING_DDL_BOX = Config.getOpenNewApi() + "/box/getCoinTypeData";
    int MINING_DDL_BOX_ID = 100080;


    /******余额******/
    String MINING_PASS_BOX = Config.getOpenNewApi() + "/member/settingpaypassword";
    int MINING_PASS_BOX_ID = 100081;

    /******余额******/
    String FORGOETG_PASS_BOX = Config.getOpenNewApi() + "/member/forgotpaypassword";
    int FORGOETG_PASS_BOX_ID = 100082;


    /******余额******/
    String RESET_PASS_BOX = Config.getOpenNewApi() + "/member/resetpaypassword";
    int RESET_PASS_BOX_ID = 100083;


    /******余额******/
    String RESET_TOKEN = Config.getOpenNewApi() + "/member/getAuthToken";
    int RESET_TOKEN_ID = 100084;

    /******余额******/
    String RESET_TOKEN_DEVICE = Config.getOpenNewApi() + "/device/getdevicelocationandobddata";
    int RESET_TOKEN_DEVICE_ID = 100085;

    /******获取车辆行程数据******/
    String RESET_TOKEN_TRIP = Config.getOpenNewApi() + "/carchain/queryDataTripInfoData";
    String RESET_TOKEN_DATA = Config.getOpenNewApi() + "/carchain/queryDataMaintenanceInfoData";
    String RESET_TOKEN_INSURE = Config.getOpenNewApi() + "/carchain/queryDataInsuranceInfoData";
    String RESET_TOKEN_VIOLATE = Config.getOpenNewApi() + "/carchain/queryDataViolateInfoData";
    String RESET_TOKEN_OIL = Config.getOpenNewApi() + "/carchain/queryDataOilInfoData";

    int RESET_TOKEN_TRIP_ID = 100086;
    String RESET_FLOW_DEVICE = Config.getOpenNewApi() + "/flow/getTerminalDetail";
    int RESET_FLOW_DEVICEE_ID = 100087;

    String RESET_FLOW_List = Config.getOpenNewApi() + "/flow/getRenewalsPackageList";
    int RESET_FLOW_List_ID = 100088;

    String RESET_FLOW_CODE = Config.getOpenNewApi() + "/flow/getPackageQrcode";
    int RESET_FLOW_CODE_ID = 100089;



}
