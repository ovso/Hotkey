package kr.blogspot.ovsoce.hotkey.fragment.family;

import android.content.Context;

import kr.blogspot.ovsoce.hotkey.R;
import kr.blogspot.ovsoce.hotkey.fragment.FragmentModel;

/**
 * Created by ovso on 2015. 10. 24..
 */
public class FamilyModel extends FragmentModel {

    @Override
    public int getMenuId() {
        return R.id.nav_family;
    }

    public String getCaulyAdId(Context context) {
        return context.getString(R.string.ad_id_cauly);
    }
}
