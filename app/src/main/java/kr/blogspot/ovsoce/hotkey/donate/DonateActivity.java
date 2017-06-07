package kr.blogspot.ovsoce.hotkey.donate;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import kr.blogspot.ovsoce.hotkey.R;
import kr.blogspot.ovsoce.hotkey.donate.adapter.DonateAdapter;
import kr.blogspot.ovsoce.hotkey.donate.adapter.DonateAdapterDataModel;
import kr.blogspot.ovsoce.hotkey.donate.adapter.DonateAdapterView;

public class DonateActivity extends AppCompatActivity implements DonatePresenter.View {
  private DonatePresenter mPresenter;
  private DonateAdapterView mAdapterView;
  private DonateAdapter mAdapter;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    mAdapter = new DonateAdapter(getApplicationContext());
    DonateAdapterDataModel adapterDataModel = mAdapter;
    mAdapterView = mAdapter;

    mPresenter = new DonatePresenterImpl(this, adapterDataModel);
    mPresenter.onCreate(getApplicationContext());
  }

  private Unbinder mUnbinder;

  @Override public void setRootView() {
    setContentView(R.layout.activity_donate);
    mUnbinder = ButterKnife.bind(this);
  }

  @BindView(R.id.toolbar) Toolbar mToolbar;

  @Override public void setToolbar() {
    mToolbar.setTitle(R.string.activity_donate_label);
    setSupportActionBar(mToolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
  }

  @BindView(R.id.recyclerview) RecyclerView mRecyclerView;

  @Override public void setRecyclerView() {
    LinearLayoutManager layout = new LinearLayoutManager(getApplicationContext());
    mRecyclerView.setLayoutManager(layout);
    mRecyclerView.setAdapter(mAdapter);
  }

  @Override public void refresh() {
    mAdapterView.refresh();
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    mUnbinder.unbind();
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId() == android.R.id.home) {
      finish();
    }
    return super.onOptionsItemSelected(item);
  }

  @Override public Context getContext() {
    return this;
  }
}
