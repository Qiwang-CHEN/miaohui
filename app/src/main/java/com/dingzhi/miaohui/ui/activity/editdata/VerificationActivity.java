package com.dingzhi.miaohui.ui.activity.editdata;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.dingzhi.miaohui.BaseActivity;
import com.dingzhi.miaohui.R;
import com.dingzhi.miaohui.util.C;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
/**
 * 文件名：VerificationActivity.
 * 版权所有：SRDZ
 * 创建人：TANXIN
 * 创建日期:2016/10/13 15:38.
 * 功能描述: 验证手机
 * 函数/方法说明:
 */
public class VerificationActivity extends BaseActivity  {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.ed_phone)
    TextView edPhone;
    @BindView(R.id.btn_next)
    Button btnNext;
    private boolean isFlag = false;
    private String phone;


    @Override
    protected int setLayout() {
        return R.layout.activity_verification;
    }

    protected void initView() {
        toolbar.setTitle("验证手机");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        edPhone.setText(intent.getStringExtra("phone"));
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: //返回
                if (isFlag) {
                    Intent intent = new Intent();
                    intent.putExtra("phone", phone);
                    VerificationActivity.this.setResult(RESULT_OK, intent);
                    VerificationActivity.this.finish();
                } else {
                    finish();
                }

                break;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case C.REQUEST_CODE_SETPHONE: //新手机号码回调
                if (resultCode == RESULT_OK) {
                    phone = data.getExtras().getString("tv_phone");
                    isFlag = true;
                }
                break;
        }
    }


    @OnClick(R.id.btn_next)
    public void onClick() {
        //跳转至设置手机
        Intent intent = new Intent(this, SetPhoneActivity.class);
        startActivityForResult(intent, C.REQUEST_CODE_SETPHONE);
    }
}
