package kr.blogspot.ovsoce.hotkey.fragment.family;

import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import kr.blogspot.ovsoce.hotkey.R;
import kr.blogspot.ovsoce.hotkey.common.Log;
import kr.blogspot.ovsoce.hotkey.dialog.BlurDialogEngine;
import kr.blogspot.ovsoce.hotkey.dialog.BlurDialogFragment;
import kr.blogspot.ovsoce.hotkey.fragment.BaseFragment;
import kr.blogspot.ovsoce.hotkey.fragment.ContactsItem;
import kr.blogspot.ovsoce.hotkey.fragment.MyAdapter;

/**
 * Created by jaeho_oh on 2015-10-16.
 */
public class FamilyFragment extends BaseFragment implements FamilyPresenter.View{

    protected FamilyPresenter mPresenter;
    protected RecyclerView mRecyclerView;
    protected View mView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        mView = inflater.inflate(R.layout.fragment_family, null);
        return mView;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mRecyclerView = (RecyclerView) mView.findViewById(R.id.recyclerView);

        mPresenter = new FamilyPresenterImpl(this);
        mPresenter.init(getActivity(), mRecyclerView);
    }

    @Override
    public void initRecyclerView(MyAdapter adapter,RecyclerView.LayoutManager layoutManager) {
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void showItemSetDialog(ContactsItem item) {
        Log.d("name = " + item.getName());
        MyDialogFragment a= new MyDialogFragment();
        a.show(getFragmentManager(), "show");
    }

    @Override
    public void hideItemSetDialog() {

    }

    @Override
    public void makeACall(Intent intent) {
        startActivity(intent);
    }

    private class MyDialogFragment extends BlurDialogFragment {


        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            //return super.onCreateView(inflater, container, savedInstanceState);
            View view;
            return view = inflater.inflate(R.layout.dialog_custom, null);
        }

        @Override
        protected float getDownScaleFactor() {
            return 8f;//super.getDownScaleFactor();
        }

        @Override
        protected int getBlurRadius() {
            return 8;//super.getBlurRadius();
        }

        @Override
        protected boolean isDimmingEnable() {
            return super.isDimmingEnable();
        }

        @Override
        protected boolean isActionBarBlurred() {
            return true;
        }

        @Override
        protected boolean isRenderScriptEnable() {
            return super.isRenderScriptEnable();
        }
    }
}
