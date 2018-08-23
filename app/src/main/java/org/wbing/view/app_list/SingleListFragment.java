package org.wbing.view.app_list;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import org.wbing.base.ui.impl.WFrag;
import org.wbing.view.app_list.data.ContentData;
import org.wbing.view.app_list.databinding.FragmentListBinding;
import org.wbing.view.app_list.databinding.ItemContentBinding;
import org.wbing.view.list.LineItemDecoration;
import org.wbing.view.list.WAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangbing
 * @date 2018/8/23
 */
public class SingleListFragment extends WFrag<FragmentListBinding> {
    public static SingleListFragment newInstance() {

        Bundle args = new Bundle();

        SingleListFragment fragment = new SingleListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    WAdapter<ContentData, ItemContentBinding> adapter =
            new WAdapter.SimpleAdapter<>(BR.data, R.layout.item_content);

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
        List<ContentData> ls = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            ls.add(new ContentData(getRandomColor(), "item text " + i));
        }
        adapter.setList(ls);
    }

    @Override
    public void recycle() {

    }



    private int[] colors = {
            Color.BLACK,
            Color.DKGRAY,
            Color.GRAY,
            Color.LTGRAY,
            Color.WHITE,
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
