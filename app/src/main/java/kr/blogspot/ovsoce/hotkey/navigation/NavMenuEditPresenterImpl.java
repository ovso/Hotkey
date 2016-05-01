package kr.blogspot.ovsoce.hotkey.navigation;

public class NavMenuEditPresenterImpl implements NavMenuEditPresenter {
    private NavMenuEditPresenter.View mView;
    private NavMenuEditModel mModel;
    NavMenuEditPresenterImpl(NavMenuEditPresenter.View view) {
        mView = view;
        mModel = new NavMenuEditModel();
    }
    @Override
    public void onClick(android.view.View view) {

    }

    @Override
    public void onCreate() {
        mView.onInit();
    }

    @Override
    public void onOptionsItemSelected(int itemId) {
        if(itemId == android.R.id.home) {
            mView.activityFinish();
        }
    }
}
