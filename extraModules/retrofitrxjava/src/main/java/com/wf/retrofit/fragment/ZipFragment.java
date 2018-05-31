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
import com.wf.retrofit.model.GankBeauty;
import com.wf.retrofit.model.GankBeautyResult;
import com.wf.retrofit.model.Item;
import com.wf.retrofit.network.Network;
import com.wf.retrofit.utils.GankBeautyResultToItemsMapper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ZipFragment extends BaseFragment {
    @BindView(R2.id.gridRv) RecyclerView gridRv;
    @BindView(R2.id.swipeRefreshLayout) SwipeRefreshLayout swipeRefreshLayout;
    ItemListAdapter adapter = new ItemListAdapter();

    @OnClick(R2.id.zipLoadBt)
    void load() {
        swipeRefreshLayout.setRefreshing(true);
        unsubscribe();
        disposable = Observable.zip(Network.getGankApi().getBeauties(20, 1).map(GankBeautyResultToItemsMapper.getInstance()),
                Network.getGankApi().getBeauties(10, 2),
                new BiFunction<List<Item>, GankBeautyResult, List<Item>>() {
                    @Override
                    public List<Item> apply(List<Item> gankItems, GankBeautyResult beautyResult) {
                        List<GankBeauty> beauties = beautyResult.beauties;
                        List<Item> items = new ArrayList<Item>();
                        for (int i = 0; i < gankItems.size() / 2 && i < beauties.size(); i++) {
                            items.add(gankItems.get(i * 2));
                            items.add(gankItems.get(i * 2 + 1));

                            Item otherItem = new Item();
                            otherItem.description = beauties.get(i).createdAt;
                            otherItem.imageUrl = beauties.get(i).url;
                            items.add(otherItem);
                        }
                        return items;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Item>>() {
                    @Override
                    public void accept(@NonNull List<Item> items) throws Exception {
                        swipeRefreshLayout.setRefreshing(false);
                        adapter.setItems(items);
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
        View view = inflater.inflate(R.layout.rxjava_fragment_zip, container, false);
        ButterKnife.bind(this, view);

        gridRv.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        gridRv.setAdapter(adapter);
        swipeRefreshLayout.setColorSchemeColors(Color.BLUE, Color.GREEN, Color.RED, Color.YELLOW);
        swipeRefreshLayout.setEnabled(false);
        return view;
    }


    @Override
    protected int getDialogRes() {
        return R.layout.rxjava_dialog_zip;
    }

    @Override
    protected int getTitleRes() {
        return R.string.title_zip;
    }
}
