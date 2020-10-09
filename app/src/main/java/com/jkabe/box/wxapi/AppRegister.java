package com.jkabe.box.wxapi;



import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.jkabe.app.box.util.Constants;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

public class AppRegister extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		final IWXAPI msgApi = WXAPIFactory.createWXAPI(context, null);

		// 将该app注册到微信
		msgApi.registerApp(Constants.APP_ID);
	}
}
