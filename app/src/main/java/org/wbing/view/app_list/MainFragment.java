package org.wbing.view.app_list;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.wbing.main.TabButton;
import org.wbing.main.TabModel;
import org.wbing.main.WMainFrag;
import org.wbing.main.databinding.WMainPageBinding;

/**
 * @author wangbing
 * @date 2018/8/23
 */
public class MainFragment extends WMainFrag {

    @Override
    public void onPostViewCreate() {
        super.onPostViewCreate();
        getBinding().pages.setNoScroll(false);
        getBinding().line.setBackgroundColor(getResources().getColor(R.color.color_line));
    }

    @NotNull
    @Override
    public TabButton getTabView() {
        return new TabButton(getContext());
    }

    @Nullable
    @Override
    public TabModel[] tabs() {
        TabModel[] tabs = new TabModel[2];
        tabs[0] = new TabModel(R.id.app_tab1, R.drawable.app_selector_tab1, "单项", SingleListFragment.newInstance());
        tabs[1] = new TabModel(R.id.app_tab2, R.drawable.app_selector_tab2, "多项", MultiListFragment.newInstance());
        return tabs;
    }

    @Override
    public void loadData() {

    }

    @Override
    public void recycle() {

    }
}
