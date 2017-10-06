package com.dafian.android.rssfeeder.ui.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dafian.android.rssfeeder.R;
import com.dafian.android.rssfeeder.config.ApiConstants;
import com.dafian.android.rssfeeder.data.entity.ItemEntity;
import com.dafian.android.rssfeeder.presenter.MainPresenter;
import com.dafian.android.rssfeeder.ui.BaseFragment;
import com.dafian.android.rssfeeder.ui.adapter.MainAdapter;
import com.dafian.android.rssfeeder.ui.extension.RecyclerTouchListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author Dafian on 10/6/17
 */

public class MainFragment extends BaseFragment implements MainView {

    @BindView(R.id.sw_rss)
    SwipeRefreshLayout swRss;
    @BindView(R.id.rv_rss)
    RecyclerView rvRss;

    private Unbinder unbinder;
    private MainPresenter presenter;

    private List<ItemEntity> itemList;
    private MainAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new MainPresenter(getManager());
        presenter.attachView(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView();
        initEvent();
        loadingData();
    }

    @Override
    public void showRssItem(List<ItemEntity> items) {

        if (itemList.size() > 0) {
            itemList.clear();
        }

        itemList.addAll(items);
        adapter.notifyDataSetChanged();

        rvRss.setVisibility(View.VISIBLE);
        swRss.setRefreshing(false);
    }

    @Override
    public void showEmpty() {

        swRss.setRefreshing(false);
    }

    @Override
    public void showError(String error) {

        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();

        swRss.setRefreshing(false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (swRss != null) {
            swRss.setRefreshing(false);
            swRss.destroyDrawingCache();
            swRss.clearAnimation();
        }
        unbinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    private void initView() {

        itemList = new ArrayList<>();
        adapter = new MainAdapter(getActivity(), itemList);

        rvRss.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvRss.setItemAnimator(new DefaultItemAnimator());
        rvRss.setAdapter(adapter);
    }

    private void initEvent() {

        swRss.setOnRefreshListener(this::loadingData);

        rvRss.addOnItemTouchListener(new RecyclerTouchListener(
                getActivity(), rvRss, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {

                showError(itemList.get(position).getTitle());
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        rvRss.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                int topRowVerticalPosition = (rvRss == null
                        || rvRss.getChildCount() == 0) ? 0 : rvRss.getChildAt(0)
                        .getTop();
                swRss.setEnabled(dx == 0 && topRowVerticalPosition >= 0);
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    private void loadingData() {

        rvRss.setVisibility(View.GONE);
        swRss.setRefreshing(true);

        presenter.getRss(ApiConstants.URL_RSS);
    }
}
