package com.project.jeremy.qrcodescanner.view.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.project.jeremy.qrcodescanner.R;
import com.project.jeremy.qrcodescanner.model.Code;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@EBean
public class CodeFormatsAdapter extends RecyclerView.Adapter<CodeFormatsAdapter.CodeViewHolder> {

    @RootContext
    Context context;

    private ArrayList<BarcodeFormat> barcodeFormats;

    public CodeFormatsAdapter() {
        barcodeFormats = new ArrayList<>();
    }

    @Override
    public CodeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View view = layoutInflater.inflate(R.layout.code_format_item, parent, false);
        return new CodeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CodeViewHolder holder, int position) {
        BarcodeFormat format = barcodeFormats.get(position);
        holder.name.setText(format.name());
        holder.select.setChecked(true);
    }

    @Override
    public int getItemCount() {
        return barcodeFormats.size();
    }

    public void setBarcodeFormatsList(Collection<BarcodeFormat> barcodeFormats) {
        this.barcodeFormats.clear();
        this.barcodeFormats.addAll(barcodeFormats);
        this.notifyDataSetChanged();
    }

    static class CodeViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        CheckBox select;

        CodeViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            select = itemView.findViewById(R.id.select);
        }
    }
}
