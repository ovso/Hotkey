package kr.blogspot.ovsoce.hotkey.main;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;

public class EditNameAlertDialogBuilder extends AlertDialog.Builder {
    private View view;
    public EditNameAlertDialogBuilder(Context context, int layoutResId) {
        super(context);
        view = LayoutInflater.from(getContext()).inflate(layoutResId, null);
        super.setView(view);
    }

    public View getView() {
        return view;
    }
}
