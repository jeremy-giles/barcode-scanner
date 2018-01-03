package com.project.jeremy.qrcodescanner.view.activities;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.project.jeremy.qrcodescanner.R;
import com.project.jeremy.qrcodescanner.view.adapters.CodeFormatsAdapter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.ViewById;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

@EActivity(R.layout.activity_code_selection)
public class CodeSelectionActivity extends AppCompatActivity {

    @Bean
    CodeFormatsAdapter codeFormatsAdapter;

    @ViewById
    Toolbar toolbar;

    @ViewById
    RecyclerView codeFormatsList;

    @AfterViews
    public void initToolbar() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        @SuppressLint("PrivateResource")
        final Drawable upArrow = this.getDrawable(R.drawable.abc_ic_ab_back_material);
        upArrow.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);

    }

    @AfterViews
    public void initBarcodeFormatsList() {
        ZXingScannerView codes = new ZXingScannerView(this);
        codeFormatsAdapter.setBarcodeFormatsList(codes.getFormats());
        codeFormatsList.setLayoutManager(new LinearLayoutManager(this));
        codeFormatsList.setAdapter(codeFormatsAdapter);
    }

    @OptionsItem(android.R.id.home)
    void homeSelected() {
        onBackPressed();
    }
}
