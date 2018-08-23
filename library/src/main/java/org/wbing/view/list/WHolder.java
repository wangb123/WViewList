package org.wbing.view.list;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * @author wangbing
 * @date 2018/8/21
 */
public class WHolder<Binding extends ViewDataBinding> extends RecyclerView.ViewHolder {
    Binding binding;

    public WHolder(View itemView) {
        super(itemView);
        this.binding = DataBindingUtil.bind(itemView);
    }

    public Binding getBinding() {
        return this.binding;
    }

    public void fill(int variableId, Object data) {
        this.binding.setVariable(variableId, data);
    }
}