package kr.blogspot.ovsoce.hotkey.navigation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import kr.blogspot.ovsoce.hotkey.R;

public class NavMenuEditActivity extends AppCompatActivity implements NavMenuEditPresenter.View {
    public static final int REQUEST_CODE = 1;
    private NavMenuEditPresenter mPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_edit);

        mPresenter = new NavMenuEditPresenterImpl(this);
        mPresenter.onCreate();
    }

    @Override
    public void onInit() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.activity_name_nav_menu_edit);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void showToast(int resId) {
        Toast.makeText(getApplicationContext(), resId, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void activityFinish() {
        onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        mPresenter.onOptionsItemSelected(item.getItemId());
        return super.onOptionsItemSelected(item);
    }
}
