package kr.blogspot.ovsoce.hotkey.fragment.help;

import android.content.Context;

/**
 * Created by jaeho_oh on 2015-11-12.
 */
public class HelpPresenterImpl implements HelpPresenter {
    private View mView;
    private HelpModel mModel;
    public HelpPresenterImpl(HelpPresenter.View view) {
        mView = view;
        mModel = new HelpModel();
    }


    @Override
    public void init(Context context) {

    }
}
