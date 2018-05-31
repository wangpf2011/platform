package com.wf.retrofit;

import android.app.AlertDialog;
import android.app.Fragment;

import butterknife.OnClick;
import io.reactivex.disposables.Disposable;

public abstract class BaseFragment extends Fragment {
    protected Disposable disposable;

    @OnClick(R2.id.tipBt)
    void tip() {
        new AlertDialog.Builder(getActivity())
                .setTitle(getTitleRes())
                .setView(getActivity().getLayoutInflater().inflate(getDialogRes(), null))
                .show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unsubscribe();
    }

    protected void unsubscribe() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    protected abstract int getDialogRes();

    protected abstract int getTitleRes();
}
