package com.jkabe.app.box.box.fragement;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jkabe.app.box.base.BaseFragment;
import com.jkabe.app.box.bean.CommonalityModel;
import com.jkabe.app.box.config.Api;
import com.jkabe.app.box.config.NetWorkListener;
import com.jkabe.app.box.config.okHttpModel;
import com.jkabe.app.box.util.Constants;
import com.jkabe.app.box.util.Md5Util;
import com.jkabe.app.box.util.SaveUtils;
import com.jkabe.app.box.util.Utility;
import com.jkabe.box.R;
import org.json.JSONObject;
import java.util.Map;

/**
 * @author: zt
 * @date: 2020/10/20
 * @name:TabFragment1
 */
public class TabFragment5 extends BaseFragment implements NetWorkListener {
    private View rootView;
    private TextView text_tab1, text_tab2, text_tab3, text_tab4, text_tab5, text_tab6;
    private TextView text_tab_direct, text_tab1_direct, text_tab2_direct;
    private TextView text_tab_usdt, text_tab1_usdt, text_tab1_box, text_tab2_box;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_tab5, container, false);
            initView();
        }
        return rootView;
    }

    private void initView() {
        text_tab1 = getView(rootView, R.id.text_tab1);
        text_tab2 = getView(rootView, R.id.text_tab2);
        text_tab3 = getView(rootView, R.id.text_tab3);
        text_tab4 = getView(rootView, R.id.text_tab4);
        text_tab5 = getView(rootView, R.id.text_tab5);
        text_tab6 = getView(rootView, R.id.text_tab6);

        text_tab_direct = getView(rootView, R.id.text_tab_direct);
        text_tab1_direct = getView(rootView, R.id.text_tab1_direct);
        text_tab2_direct = getView(rootView, R.id.text_tab2_direct);

        text_tab_usdt = getView(rootView, R.id.text_tab_usdt);
        text_tab1_usdt = getView(rootView, R.id.text_tab1_usdt);
        text_tab1_box = getView(rootView, R.id.text_tab1_box);
        text_tab2_box = getView(rootView, R.id.text_tab2_box);
    }


    @Override
    public void onResume() {
        super.onResume();
        query();
    }

    public void query() {
        String sign = "memberid=" + SaveUtils.getSaveInfo().getId() + "&partnerid=" + Constants.PARTNERID + Constants.SECREKEY;
        showProgressDialog(getActivity(), false);
        Map<String, String> params = okHttpModel.getParams();
        params.put("apptype", Constants.TYPE);
        params.put("memberid", SaveUtils.getSaveInfo().getId());
        params.put("partnerid", Constants.PARTNERID);
        params.put("sign", Md5Util.encode(sign));
        okHttpModel.get(Api.MINING_BAL_BOX, params, Api.GETRECORD_BOX_ID, this);
    }


    @Override
    public void onSucceed(JSONObject object, int id, CommonalityModel commonality) {
        if (object != null && commonality != null && !Utility.isEmpty(commonality.getStatusCode())) {
            if (Constants.SUCESSCODE.equals(commonality.getStatusCode())) {
                switch (id) {
                    case Api.MINING_BOX_ID:

                        break;

                }
            }
        }
        stopProgressDialog();
    }

    @Override
    public void onFail() {
        stopProgressDialog();
    }

    @Override
    public void onError(Exception e) {
        stopProgressDialog();
    }
}
