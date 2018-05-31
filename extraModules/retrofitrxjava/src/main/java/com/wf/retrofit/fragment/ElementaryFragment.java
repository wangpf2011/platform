package com.wf.retrofit.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.wf.retrofit.BaseFragment;
import com.wf.retrofit.R;
import com.wf.retrofit.R2;
import com.wf.retrofit.adapter.ItemListAdapter;
import com.wf.retrofit.model.Item;
import com.wf.retrofit.network.Network;
import com.wf.retrofit.utils.GankBeautyResultToItemsMapper;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ElementaryFragment extends BaseFragment {
    @BindView(R2.id.swipeRefreshLayout) SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R2.id.gridRv) RecyclerView gridRv;

    ItemListAdapter adapter = new ItemListAdapter();

    private void getBeauties() {
        disposable = Network.getGankApi()
                .getBeauties(1,10)
                .map(GankBeautyResultToItemsMapper.getInstance())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Item>>() {
                    @Override
                    public void accept(@NonNull List<Item> images) throws Exception {
                        swipeRefreshLayout.setRefreshing(false);
                        adapter.setItems(images);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        swipeRefreshLayout.setRefreshing(false);
                        Toast.makeText(getActivity(), R.string.loading_failed, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.rxjava_fragment_elementary, container, false);
        ButterKnife.bind(this, view);

        gridRv.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        gridRv.setAdapter(adapter);
        swipeRefreshLayout.setColorSchemeColors(Color.BLUE, Color.GREEN, Color.RED, Color.YELLOW);
        swipeRefreshLayout.setEnabled(false);

        unsubscribe();
        adapter.setItems(null);
        swipeRefreshLayout.setRefreshing(true);
        getBeauties();
        return view;
    }

    @Override
    protected int getDialogRes() {
        return R.layout.rxjava_dialog_elementary;
    }

    @Override
    protected int getTitleRes() {
        return R.string.title_elementary;
    }
}
