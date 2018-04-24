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
import kr.blogspot.ovsoce.hotkey.framework.Log;

public class EditNameAlertDialogBuilder extends AlertDialog.Builder {
    private View view;
    public EditNameAlertDialogBuilder(Context context, int layoutResId, int position) {
        super(context);
        init(layoutResId, position);
    }
    private void init(int layoutResId, final int position) {
        view = LayoutInflater.from(getContext()).inflate(layoutResId, null);
        setView(view);
        setTitle(R.string.dialog_title_edit_name);
        setIcon(android.R.drawable.ic_menu_edit);
        setName(position);
        setPositiveButton(R.string.btn_ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

                if (onChangeTabTitleListener != null) {
                    EditText nameEt = (EditText) view.findViewById(R.id.et_edit_name);
                    String name = nameEt.getText().toString().trim();
                    if(name.length() > 0) {
                        onChangeTabTitleListener.onChangeTabTitle(name, position);

                        SharedPreferences.Editor editor = getSharedPreferences().edit();
                        editor.putString("tab_"+position, name);
                        editor.apply();
                    } else {
                        Log.d("empty");
                    }
                }
            }
        });
        setNegativeButton(R.string.btn_cancel, null);
    }
    private OnChangeTabTitleListener onChangeTabTitleListener;
    public EditNameAlertDialogBuilder setOnChangeTabTitleListener(OnChangeTabTitleListener listener) {
        onChangeTabTitleListener = listener;
        return this;
    }
    public interface OnChangeTabTitleListener {
        void onChangeTabTitle(String title, int position);
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
}
