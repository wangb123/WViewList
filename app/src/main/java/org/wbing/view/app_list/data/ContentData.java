package org.wbing.view.app_list.data;

import org.wbing.view.app_list.R;
import org.wbing.view.list.IMultiItem;

/**
 * @author wangbing
 * @date 2018/8/23
 */
public class ContentData implements IMultiItem {
    public int color;
    public String text;

    public ContentData(int color, String text) {
        this.color = color;
        this.text = text;
    }

    @Override
    public int getItemType() {
        return R.layout.item_content;
    }

    @Override
    public Object getData() {
        return this;
    }
}
