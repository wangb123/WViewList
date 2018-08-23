package org.wbing.view.app_list.data;

import org.wbing.view.app_list.R;
import org.wbing.view.list.IMultiItem;

/**
 * @author wangbing
 * @date 2018/8/23
 */
public class AdData implements IMultiItem {
    public String text;
    public int color;

    public AdData(int color, String text) {
        this.text = text;
        this.color = color;
    }

    @Override
    public int getItemType() {
        return R.layout.item_ad;
    }

    @Override
    public Object getData() {
        return this;
    }
}
