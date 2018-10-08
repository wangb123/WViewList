package org.wbing.view.list;

import java.util.List;

/**
 * @author wangbing
 * @date 2018/9/11
 */
public interface IItemGroup<Item extends IMultiItem> extends IMultiItem {
    public List<Item> getList();
}
