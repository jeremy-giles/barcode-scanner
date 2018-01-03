package com.project.jeremy.qrcodescanner.view.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.project.jeremy.qrcodescanner.R;
import com.project.jeremy.qrcodescanner.model.Code;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.ArrayList;
import java.util.Collection;

@EBean
public class CodesAdapter extends RecyclerView.Adapter<CodesAdapter.CodeViewHolder> {

    @RootContext
    Context context;

    private ArrayList<Code> codes;

    public CodesAdapter() {
        codes = new ArrayList<>();
    }

    @Override
    public CodeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View view = layoutInflater.inflate(R.layout.code_item, parent, false);
        return new CodeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CodeViewHolder holder, int position) {
        Code code = this.codes.get(position);
        holder.data.setText(code.getData());
        holder.counter.setText(code.getCount().toString());
    }

    @Override
    public int getItemCount() {
        return codes.size();
    }

    public void setCodesList(Collection<Code> codes) {
        this.codes.clear();
        this.codes.addAll(codes);
        this.notifyDataSetChanged();
    }

    static class CodeViewHolder extends RecyclerView.ViewHolder {
        TextView data;
        TextView counter;

        CodeViewHolder(View itemView) {
            super(itemView);
            data = itemView.findViewById(R.id.data);
            counter = itemView.findViewById(R.id.counter);
        }
    }
}
