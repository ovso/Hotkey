package kr.blogspot.ovsoce.hotkey.emergency;

public class EmergencyPresenterImpl implements EmergencyPresenter {
    private EmergencyPresenterModel mModel;
    private View mView;
    EmergencyPresenterImpl(EmergencyPresenter.View view) {
        mView = view;
        mModel = new EmergencyPresenterModel(mView.getContext());
    }

    @Override
    public void onCreate() {
        mView.setRootView();
        mView.setToolbar();
        mView.setViewPager(mModel.getTabCount());
        mView.setTabLayout();
        mModel.setScreenTracker("Emergency");
    }
}
