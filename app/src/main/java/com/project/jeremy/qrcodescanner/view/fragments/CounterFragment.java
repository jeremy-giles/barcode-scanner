package com.project.jeremy.qrcodescanner.view.fragments;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.widget.TextView;

import com.project.jeremy.qrcodescanner.viewModel.CodeScanListViewModel;
import com.project.jeremy.qrcodescanner.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import static com.project.jeremy.qrcodescanner.utils.SharedPrefUtilsKt.counterFormat;

@EFragment(R.layout.fragment_counter)
public class CounterFragment extends Fragment {

    @ViewById
    TextView counter;

    public CounterFragment() {}

    @AfterViews
    public void init() {
        CodeScanListViewModel vModel = ViewModelProviders.of(getActivity()).get(CodeScanListViewModel.class);
        vModel.getCodeCounter().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer count) {
                if(count != null) {
                    counter.setText(counterFormat(count));
                }
            }
        });
    }
}
