package kr.blogspot.ovsoce.hotkey.donate;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;
import kr.blogspot.ovsoce.hotkey.R;
import kr.blogspot.ovsoce.hotkey.donate.adapter.DonateAdapter;
import kr.blogspot.ovsoce.hotkey.donate.adapter.DonateAdapterDataModel;
import kr.blogspot.ovsoce.hotkey.donate.adapter.DonateAdapterView;

public class DonateActivity extends AppCompatActivity implements DonatePresenter.View {
  private DonatePresenter mPresenter;
  private DonateAdapterView mAdapterView;
  private DonateAdapter mAdapter;
  @BindView(R.id.loading_progressbar) ProgressBar mLoadingProgressbar;

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
    mToolbar.setTitle(R.string.sponsorship_status);
    setSupportActionBar(mToolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
  }

  @BindView(R.id.recyclerview) RecyclerView mRecyclerView;

  @Override public void setRecyclerView() {
    LinearLayoutManager layout = new LinearLayoutManager(getApplicationContext());
    mRecyclerView.setLayoutManager(layout);
    mRecyclerView.addItemDecoration(
        new HorizontalDividerItemDecoration.Builder(this)
            .color(Color.TRANSPARENT)
            .sizeResId(R.dimen.dp_20)
            .build());
    mRecyclerView.setAdapter(mAdapter);
  }

  @Override public void refresh() {
    mAdapterView.refresh();
  }

  @Override public void showLoading() {
    mLoadingProgressbar.setVisibility(View.VISIBLE);
  }

  @Override public void hideLoading() {
    mLoadingProgressbar.setVisibility(View.GONE);
  }

  @BindView(R.id.desc_textview) TextView mDescTextview;
  @Override public void setDescription(String description) {
    if (mDescTextview != null) {
      mDescTextview.setText(description);
    }
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
