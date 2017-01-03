package kr.blogspot.ovsoce.hotkey.emergency.fragment;

public class EmergencyFragmentPresenterImpl implements EmergencyFragmentPresenter {

    private EmergencyFragmentPresenter.View mView;
    private EmergencyFragmentContactModel mContactModel;
    EmergencyFragmentPresenterImpl(EmergencyFragmentPresenter.View view) {
        mView = view;
        mContactModel = new EmergencyFragmentContactModel(mView.getContext());
    }

    @Override
    public void onActivityCreate(int index) {
        mView.setRecyclerView(mContactModel.getContactList(index));
    }
}
