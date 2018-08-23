package org.wbing.view.app_list;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import org.wbing.base.ui.impl.WFrag;
import org.wbing.view.app_list.data.AdData;
import org.wbing.view.app_list.data.ContentData;
import org.wbing.view.app_list.data.NewsData;
import org.wbing.view.app_list.databinding.FragmentListBinding;
import org.wbing.view.list.IMultiItem;
import org.wbing.view.list.LineItemDecoration;
import org.wbing.view.list.WAdapter;
import org.wbing.view.list.WMultiAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangbing
 * @date 2018/8/23
 */
public class MultiListFragment extends WFrag<FragmentListBinding> {
    public static MultiListFragment newInstance() {

        Bundle args = new Bundle();

        MultiListFragment fragment = new MultiListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    WMultiAdapter<IMultiItem> adapter = new WMultiAdapter<>(BR.data);


    @Override
    public int layoutId() {
        return R.layout.fragment_list;
    }

    @Override
    public void onPostViewCreate() {
        super.onPostViewCreate();
        getBinding().list.setLayoutManager(new LinearLayoutManager(getContext()));
        getBinding().list.addItemDecoration(new LineItemDecoration());
        getBinding().list.setAdapter(adapter);
    }

    @Override
    public void loadData() {
        ArrayList<IMultiItem> ls = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            ls.add(createData(i));
        }
        adapter.setList(ls);
    }

    @Override
    public void recycle() {

    }

    private IMultiItem createData(int i) {
        int random = (int) Math.round(Math.random() * 3);
        switch (random) {
            case 1:
                return new AdData(getRandomColor(), "广告：" + i);
            case 2:
                return new NewsData(getRandomColor(), "新闻：" + i);
            default:
                return new ContentData(getRandomColor(), "内容：" + i);
        }
    }

    private int[] colors = {
            Color.BLACK,
            Color.DKGRAY,
            Color.GRAY,
            Color.LTGRAY,
            Color.GRAY,
            Color.RED,
            Color.GREEN,
            Color.BLUE,
            Color.YELLOW,
            Color.CYAN,
            Color.MAGENTA
    };

    private int getRandomColor() {
        int random = (int) (Math.random() * 9);
        return colors[random];
    }
}
