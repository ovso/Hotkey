package kr.blogspot.ovsoce.hotkey.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
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
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;

import kr.blogspot.ovsoce.hotkey.R;
import kr.blogspot.ovsoce.hotkey.common.Log;
import kr.blogspot.ovsoce.hotkey.dialog.lib.BlurDialogFragment;
import kr.blogspot.ovsoce.hotkey.fragment.ContactsItem;
import kr.blogspot.ovsoce.hotkey.fragment.ContactsItemImpl;

/**
 * Created by jaeho_oh on 2015-10-27.
 */
public class MyBlurDialogFragment extends BlurDialogFragment implements DialogPresenter.View, View.OnClickListener {

    public static MyBlurDialogFragment getInstance(ContactsItem item) {
        MyBlurDialogFragment fragment = new MyBlurDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("item", item);
        fragment.setArguments(bundle);

        return fragment;
    }
    private View mView;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //return super.onCreateDialog(savedInstanceState);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        mView = getActivity().getLayoutInflater().inflate(R.layout.dialog_custom, null);
        builder.setView(mView);
        builder.setTitle("AlertDialog");
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String name = ((EditText) mView.findViewById(R.id.et_name)).getText().toString().trim();
                String number = ((EditText) mView.findViewById(R.id.et_number)).getText().toString().trim();

                Log.d("tag = " + mView.findViewById(R.id.scroll_container).getTag());

                int colorPosition = (int) mView.findViewById(R.id.scroll_container).getTag();
                ContactsItem item = (ContactsItem) getArguments().getSerializable("item");

                mPresenter.setContacts(getActivity(), item.getId(), name, number, colorPosition, item.getMenuType());

                dismiss();
            }
        });
        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dismiss();
            }
        });
        setCancelable(false);
        return builder.create();
    }

    private GridView mColorGridView;
    private DialogPresenter mPresenter;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //mColorGridView = (GridView) mView.findViewById(R.id.grid_color);

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
        mPresenter.setColorSelected((int) v.getTag(), mView.findViewById(R.id.scroll_container));
    }

}