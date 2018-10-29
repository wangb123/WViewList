package org.wbing.view.list;

import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;

import org.wbing.view.list.databinding.WListItemMultiRootBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangbing
 * @date 2018/8/21
 */

public abstract class WAdapter<Data, Binding extends ViewDataBinding> extends RecyclerView.Adapter<WHolder<Binding>> {
    private List<Data> list;
    private OnWHolderCreateListener<Binding> onWHolderCreateListener;

    WAdapter() {
    }

    @NonNull
    public WHolder<Binding> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        WHolder<Binding> holder = new WHolder<>(
                LayoutInflater.
                        from(parent.getContext())
                        .inflate(this.holderLayout(viewType), parent, false)
        );
        this.onViewHolderCreated(holder, viewType);
        if (onWHolderCreateListener != null) {
            onWHolderCreateListener.onCreate(parent, holder, viewType);
        }
        return holder;
    }

    public void setOnWHolderCreateListener(OnWHolderCreateListener<Binding> onWHolderCreateListener) {
        this.onWHolderCreateListener = onWHolderCreateListener;
    }

    public OnWHolderCreateListener<Binding> getOnWHolderCreateListener() {
        return onWHolderCreateListener;
    }

    public int getItemCount() {
        return this.list == null ? 0 : this.list.size();
    }

    public Data getItem(int position) {
        return this.list != null && this.list.size() > position ? this.list.get(position) : null;
    }

    public void setList(List<Data> list) {
        this.list = list;
        this.notifyDataSetChanged();
    }

    public List<Data> getList() {
        return this.list;
    }

    public void addItem(Data data) {
        if (this.list == null) {
            this.list = new ArrayList();
        }

        this.list.add(data);
        this.notifyItemInserted(this.list.size() - 1);
    }

    public void addItem(Data data, int position) {
        if (this.list == null) {
            this.list = new ArrayList();
        }

        this.list.add(position, data);
        this.notifyItemInserted(position);
    }

    public void addItems(List<Data> datas) {
        if (datas != null && !datas.isEmpty()) {
            if (this.list != null && !this.list.isEmpty()) {
                this.list.addAll(datas);
                this.notifyItemRangeChanged(this.list.size() - datas.size(), datas.size());
            } else {
                this.setList(datas);
            }
        }
    }

    public void addItems(List<Data> datas, int position) {
        if (datas != null && !datas.isEmpty()) {
            if (this.list != null && !this.list.isEmpty()) {
                if (this.list.size() - 1 < position) {
                    this.addItems(datas);
                } else {
                    this.list.addAll(position, datas);
                    this.notifyItemRangeChanged(position, datas.size());
                }
            } else {
                this.setList(datas);
            }
        }
    }

    public void remove(int position) {
        if (this.getItemCount() != 0 && position >= 0 && position <= this.getItemCount() - 1) {
            this.list.remove(position);
            this.notifyItemRemoved(position);
        }
    }

    public void remove(Data data) {
        if (this.getItemCount() != 0) {
            this.remove(this.list.indexOf(data));
        }
    }

    public void removeAll() {
        if (this.getItemCount() != 0) {
            this.list.clear();
            this.notifyDataSetChanged();
        }
    }

    public abstract void onViewHolderCreated(WHolder<Binding> holder, int viewType);

    public abstract int holderLayout(int viewType);

//    public static class MultiAdapter<Data extends IMultiItem> extends WAdapter<Data, WListItemMultiRootBinding> implements ViewStub.OnInflateListener {
//        private WAdapter.MultiAdapter.IProvider provider;
//
//        public MultiAdapter(WAdapter.MultiAdapter.IProvider provider) {
//            this.provider = provider;
//            if (provider == null) {
//                throw new RuntimeException("IProvider can't be null");
//            }
//        }
//
//        public void onBindViewHolder(@NonNull WHolder<WListItemMultiRootBinding> holder, int position) {
//            if (holder.getBinding().content.isInflated()) {
//                holder.getBinding().content.getBinding().setVariable(this.getVariableId(holder.getItemViewType()), ((IMultiItem) this.getItem(position)).getData());
//            } else {
//                holder.getBinding().content.setOnInflateListener((stub, inflated) -> {
//                    this.onInflate(stub, inflated);
//                    this.onBindViewHolder(holder, position);
//                });
//                holder.getBinding().content.getViewStub().setLayoutResource(this.provider.holderLayout(holder.getItemViewType()));
//                holder.getBinding().content.getViewStub().inflate();
//            }
//
//        }
//
//        public int getItemViewType(int position) {
//            return this.getItem(position).getItemType();
//        }
//
//        public void onViewHolderCreated(WHolder<WListItemMultiRootBinding> holder, int viewType) {
//            holder.getBinding().content.setOnInflateListener(this);
//            if (!holder.getBinding().content.isInflated()) {
//                holder.getBinding().content.getViewStub().setLayoutResource(this.provider.holderLayout(viewType));
//                holder.getBinding().content.getViewStub().inflate();
//            }
//
//        }
//
//        public int holderLayout(int viewType) {
//            return R.layout.w_list_item_multi_root;
//        }
//
//        private int getVariableId(int viewType) {
//            return this.provider.variableId(viewType);
//        }
//
//        public void onInflate(ViewStub stub, View inflated) {
//        }
//
//        public interface IProvider {
//            int holderLayout(int viewType);
//
//            int variableId(int position);
//        }
//    }

    public static class SimpleAdapter<Data, Binding extends ViewDataBinding> extends WAdapter<Data, Binding> {
        int holderLayout;
        int variableId;

        public SimpleAdapter(int variableId, int holderLayout) {
            this.variableId = variableId;
            this.holderLayout = holderLayout;
        }

        public void onViewHolderCreated(WHolder<Binding> holder, int viewType) {
        }

        public int holderLayout(int viewType) {
            return this.holderLayout;
        }

        public void onBindViewHolder(WHolder<Binding> holder, int position) {
            holder.fill(this.variableId, this.getItem(position));
        }
    }

    public interface OnWHolderCreateListener<Binding extends ViewDataBinding> {
        public void onCreate(@NonNull ViewGroup parent, WHolder<Binding> holder, int viewType);
    }
}

