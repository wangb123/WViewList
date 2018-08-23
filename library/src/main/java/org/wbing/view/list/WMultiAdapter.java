package org.wbing.view.list;

import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewStub;

import org.wbing.view.list.databinding.WListItemMultiRootBinding;

/**
 * @author wangbing
 * @date 2018/8/23
 */
public class WMultiAdapter<Data extends IMultiItem>
        extends WAdapter<Data, WListItemMultiRootBinding>
        implements ViewStub.OnInflateListener {
    private int variableId;

    public WMultiAdapter(int variableId) {
        this.variableId = variableId;
    }

    @Override
    public void onViewHolderCreated(WHolder<WListItemMultiRootBinding> holder, int viewType) {
        holder.getBinding().content.setOnInflateListener(this);
        if (!holder.getBinding().content.isInflated()) {
            holder.getBinding().content.getViewStub().setLayoutResource(this.holderRealLayout(viewType));
            holder.getBinding().content.getViewStub().inflate();
        }
    }

    @Override
    public final void onBindViewHolder(@NonNull WHolder<WListItemMultiRootBinding> holder, int position) {
        if (holder.getBinding().content.isInflated()) {
            onBindRealViewHolder(holder, position);
        } else {
            holder.getBinding().content.setOnInflateListener((stub, inflated) -> {
                this.onInflate(stub, inflated);
                this.onBindViewHolder(holder, position);
            });
            holder.getBinding().content.getViewStub().setLayoutResource(this.holderRealLayout(holder.getItemViewType()));
            holder.getBinding().content.getViewStub().inflate();
        }
    }

    @Override
    public final int holderLayout(int viewType) {
        return R.layout.w_list_item_multi_root;
    }

    @Override
    public int getItemViewType(int position) {
        return this.getItem(position).getItemType();
    }

    @Override
    public void onInflate(ViewStub stub, View inflated) {
    }

    public void onBindRealViewHolder(@NonNull WHolder<WListItemMultiRootBinding> holder, int position) {
        Data item = this.getItem(position);
        holder.getBinding().content.getBinding().setVariable(variableId, item.getData());
    }

    public int holderRealLayout(int viewType) {
        return viewType;
    }
}
