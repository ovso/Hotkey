package kr.blogspot.ovsoce.hotkey.help;

public class HelpPresenterImpl implements HelpPresenter {
    private final static String URL = "";
    private HelpPresenter.View mView;
    private HelpModel mModel;
    HelpPresenterImpl(HelpPresenter.View view) {
        mView = view;
        mModel = new HelpModel();
    }
    @Override
    public void onClick(android.view.View view) {

    }

    @Override
    public void onCreate() {
        mView.onInit();
        mView.initWebView("http://m.blog.naver.com/share_oneone/220701321810");
    }

    @Override
    public void onOptionsItemSelected(int itemId) {
        if(itemId == android.R.id.home) {
            mView.activityFinish();
        }
    }
}
