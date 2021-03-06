package com.dingzhi.miaohui.ui.activity.editdata;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.dingzhi.miaohui.BaseActivity;
import com.dingzhi.miaohui.R;
import com.dingzhi.miaohui.adapter.SelectAdapter;
import com.dingzhi.miaohui.util.C;
import com.dingzhi.miaohui.widget.DividerItemDecoration;
import com.dingzhi.miaohui.widget.DragSelectRecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Description:选择行业 <br>
 *
 * @auther TX <br>
 * created at 2016/9/6 16:27
 */
public class IndustryActivity extends BaseActivity implements SelectAdapter.ClickListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.list)
    DragSelectRecyclerView recycleview;
    private final static String[] ALPHABET = {"IT/互联网/通信", "学生", "媒体/公关", "金融", "法律", "咨询", "文化/艺术",
            "影视/娱乐", "教育/科研", "房地产/建筑", "医疗/健康", "能源环保", "政府机构", "其他"};
    private SelectAdapter mAdapter;
    private List<String> list;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter.restoreInstanceState(savedInstanceState);
    }

    @Override
    protected int setLayout() {
        return R.layout.activity_industry;
    }

    @Override
    protected void initView() {
        toolbar.setTitle("选择行业");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // 安装适配器和回调
        list = new ArrayList<>();
        Collections.addAll(list, ALPHABET);
        mAdapter = new SelectAdapter(this, list);
        // 安装程序RecyclerView
        recycleview.setLayoutManager(new LinearLayoutManager(this));
        recycleview.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        recycleview.setAdapter(mAdapter);
    }
    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        mAdapter.saveInstanceState(outState);

    }

    @Override
    public void onClick(int index) {
        Intent intent = new Intent();
        intent.putExtra("tv", mAdapter.getItem(index));
        IndustryActivity.this.setResult(RESULT_OK, intent);
        IndustryActivity.this.finish();
    }


    @Override
    public void onBackPressed() {
        if (mAdapter.getSelectedCount() > 0)
            mAdapter.clearSelected();
        else super.onBackPressed();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
//                StringBuilder sb = new StringBuilder();
//                int traverse = 0;
//                for (Integer index : mAdapter.getSelectedIndices()) {
//                    if (traverse > 0) sb.append(", ");
//                    sb.append(mAdapter.getItem(index));
//                    traverse++;
//                }
//                Toast.makeText(this,
//                        mAdapter.getSelectedCount()+"___"+sb.toString()+"``",
//                        Toast.LENGTH_LONG).show();
//                mAdapter.clearSelected();
                break;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case C.REQUEST_CODE_WEIXIN:
                if (resultCode == RESULT_OK) {
                    list.add(data.getExtras().getString("tv"));
                }

        }
    }

    @OnClick(R.id.tv_title)
    public void onClick() {
        Intent intent = new Intent(this, WeiXinActivity.class);
        intent.putExtra("tv", "");
        intent.putExtra("title", "行业");
        startActivityForResult(intent, C.REQUEST_CODE_WEIXIN);
    }
}
