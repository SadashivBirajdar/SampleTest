package com.example.sampletest.view;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sampletest.R;
import com.example.sampletest.utils.Constants;

import butterknife.ButterKnife;

/**
 * Created by emb-sadabir on 2/2/18.
 */

public abstract class BaseFragment extends Fragment implements BaseView {

    private static final String TAG = BaseFragment.class.getSimpleName();

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    protected View rootView;
    private ProgressDialog mProgressDialog;
    private AlertDialog.Builder mAlertDialogBuilder;
    private AlertDialog mAlertDialog;
    private View layout;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(getLayoutResId(), container, false);
        layout = inflater.inflate(R.layout.custom_toast, (ViewGroup) rootView.findViewById(R.id.toast_root));
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    protected abstract int getLayoutResId();

    protected void showProgressDialog(boolean isCancelAble) {
        try {
            if (mProgressDialog == null) {
                mProgressDialog = ProgressDialog.show(getActivity(), null, null);
                mProgressDialog.setContentView(R.layout.include_progress_dialog);
                mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                mProgressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                mProgressDialog.setCancelable(isCancelAble);
            }
            if (!mProgressDialog.isShowing()) {
                mProgressDialog.setCancelable(isCancelAble);
                mProgressDialog.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void hideDialog() {
        try {
            if (mProgressDialog != null && mProgressDialog.isShowing()) {
                mProgressDialog.dismiss();
            }
        } catch (Exception e) {
            Log.i(TAG, "hideDialog: " + e.getMessage());
        }

    }

    public void showNetworkNotAvailable() {
        if (!isAlertDialogShowing(mAlertDialog) && this.isVisible()) {
            mAlertDialogBuilder = new AlertDialog.Builder(getActivity(), R.style.AppAlertDialogTheme);
            mAlertDialogBuilder.setMessage(R.string.no_internet_error).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    hideDialog();
                }
            });
            mAlertDialog = mAlertDialogBuilder.create();
            mAlertDialog.setCancelable(false);
            mAlertDialog.show();
            if (mProgressDialog != null) {
                mProgressDialog.dismiss();
            }
        }
    }

    public void showErrorDialog(String message, final boolean hideWaiting) {
        if (!isAlertDialogShowing(mAlertDialog)) {
            mAlertDialogBuilder = new AlertDialog.Builder(getActivity(), R.style.AppAlertDialogTheme);
            mAlertDialogBuilder.setTitle(Constants.ALERT_TITLE).setMessage(message).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    if (hideWaiting) {
                        hideDialog();
                    }
                }
            });
            mAlertDialog = mAlertDialogBuilder.create();
            mAlertDialog.setCancelable(false);
            mAlertDialog.show();
        }
    }

    public boolean isAlertDialogShowing(AlertDialog thisAlertDialog) {
        if (thisAlertDialog != null) {
            return thisAlertDialog.isShowing();
        }
        return false;
    }

    public void showQuickViewMessage(String message, int imageBehaviour, int toastLength) {

        // get the reference of TextView and ImageVIew from inflated layout
        TextView toastTextView = (TextView) layout.findViewById(R.id.toast_text);
        ImageView toastImageView = (ImageView) layout.findViewById(R.id.toast_image);
        // set the text in the TextView
        toastTextView.setText(message);
        switch (imageBehaviour) {
            case Constants.TOAST_HIDE_IMAGE:
                toastImageView.setVisibility(View.GONE);
                toastTextView.setGravity(Gravity.CENTER);
                break;
            case Constants.TOAST_SHOW_CROSS_IN_IMAGE:
                toastImageView.setVisibility(View.VISIBLE);
                toastImageView.setImageDrawable(VectorDrawableCompat.create(getActivity().getResources(), R.drawable.vector_drawable_circle_cross_white, null));
                break;
            case Constants.TOAST_SHOW_TICK_IN_IMAGE:
                toastImageView.setVisibility(View.VISIBLE);
                toastImageView.setImageDrawable(VectorDrawableCompat.create(getActivity().getResources(), R.drawable.ic_circle_ticked, null));
                break;
        }
        // create a new Toast using context
        Toast toast = new Toast(getActivity());
        if (toastLength == 0) {
            toast.setDuration(Toast.LENGTH_SHORT); // set the duration for the Toast
        } else {
            toast.setDuration(toastLength); // set the duration for the Toast
        }

        toast.setView(layout); // set the inflated layout
        toast.show(); // display the custom Toast
    }
}
