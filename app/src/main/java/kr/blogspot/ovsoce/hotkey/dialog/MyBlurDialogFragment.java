package kr.blogspot.ovsoce.hotkey.dialog;

import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;

import kr.blogspot.ovsoce.hotkey.R;
import kr.blogspot.ovsoce.hotkey.common.Log;
import kr.blogspot.ovsoce.hotkey.dialog.lib.BlurDialogFragment;

/**
 * Created by jaeho_oh on 2015-10-27.
 */
public class MyBlurDialogFragment extends BlurDialogFragment implements DialogPresenter.View, View.OnClickListener {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private View mView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        mView = inflater.inflate(R.layout.dialog_custom, container);
        return mView;
    }

    private GridView mColorGridView;
    private DialogPresenter mPresenter;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //mColorGridView = (GridView) mView.findViewById(R.id.grid_color);

        mView.findViewById(R.id.btn_cancel).setOnClickListener(this);
        mView.findViewById(R.id.btn_ok).setOnClickListener(this);

        mPresenter = new DialogPresenterImpl(this);
        mPresenter.init(getActivity());
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

    @Override
    public void initScrollView(String[] colors) {
        ViewGroup scrollContainer = (ViewGroup) mView.findViewById(R.id.scroll_container);

        for (int i = 0; i < colors.length; i++) {
            View scrollItem = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_color_item, null);
            View line = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_color_item_line, null);
            scrollItem.setTag(i);
            scrollItem.findViewById(R.id.item_rect).setBackgroundColor(Color.parseColor(colors[i]));
            scrollItem.setOnClickListener(this);
            scrollContainer.addView(scrollItem);
        }
    }

    @Override
    public void setVisible(View v, int visible) {
        v.setVisibility(visible);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_cancel) {
            dismiss();
        } else if(v.getId() == R.id.btn_ok) {
            dismiss();
        } else {
            mPresenter.setSelected((int) v.getTag(), mView.findViewById(R.id.scroll_container));
        }
    }
}