package kr.blogspot.ovsoce.hotkey.fragment.family;

import android.view.View;

import kr.blogspot.ovsoce.hotkey.fragment.ContactsItem;
import kr.blogspot.ovsoce.hotkey.fragment.FragmentPresenter;

/**
 * Created by jaeho_oh on 2015-10-16.
 */
public interface FamilyPresenter extends FragmentPresenter {
    interface View extends FragmentPresenter.View {
        void initAd(String appCode);
    }

}
