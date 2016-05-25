package kr.blogspot.ovsoce.hotkey.main;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import kr.blogspot.ovsoce.hotkey.R;

public class EditNameAlertDialogBuilder extends AlertDialog.Builder {
    private View view;
    public EditNameAlertDialogBuilder(Context context, int layoutResId, int position) {
        super(context);
        init(layoutResId, position);
    }
    DialogInterface.OnClickListener onChangeTitleListener;
    public EditNameAlertDialogBuilder setOnClickListener(DialogInterface.OnClickListener listener) {
        onChangeTitleListener = listener;
        return EditNameAlertDialogBuilder.this;
    }
    private void init(int layoutResId, final int position) {
        view = LayoutInflater.from(getContext()).inflate(layoutResId, null);
        super.setView(view);
        setName(position);
        setNegativeButton(R.string.btn_ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SharedPreferences.Editor editor = getSharedPreferences().edit();
                EditText nameEt = (EditText) view.findViewById(R.id.et_edit_name);
                editor.putString("tab_"+position, nameEt.getText().toString().trim());
                editor.apply();

                if (onChangeTitleListener != null) {
                    // 메인 타이틀 바꿀 리스너구현해야함..
                }
            }
        });
        setNegativeButton(R.string.btn_cancel, null);
    }
    public void setOnChangeTitleListener(OnChangeTitleListener listener) {

    }
    public interface OnChangeTitleListener {
        void onChange(String title);
    }
    private void setName(int position) {
        EditText nameEt = (EditText) view.findViewById(R.id.et_edit_name);
        SharedPreferences prefs = getSharedPreferences();
        String nameTxt = prefs.getString("tab_"+position,
                getContext().getString(MainActivity.DEFAULT_TITLE_RES_ID[position]));
        nameEt.setText(nameTxt);
    }

    private SharedPreferences getSharedPreferences(){
        return PreferenceManager.getDefaultSharedPreferences(getContext());
    }

    public View getView() {
        return view;
    }
}
