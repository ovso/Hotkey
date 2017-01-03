package kr.blogspot.ovsoce.hotkey.emergency;

public class EmergencyPresenterImpl implements EmergencyPresenter {

    private View mView;
    EmergencyPresenterImpl(EmergencyPresenter.View view) {
        mView = view;
    }

    @Override
    public void onCreate() {
        mView.setRootView();
        mView.setToolbar();
        mView.setViewPager();
        mView.setTabLayout();
    }
}
