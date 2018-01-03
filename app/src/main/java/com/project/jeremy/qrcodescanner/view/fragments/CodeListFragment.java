package com.project.jeremy.qrcodescanner.view.fragments;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.project.jeremy.qrcodescanner.viewModel.CodeScanListViewModel;
import com.project.jeremy.qrcodescanner.view.adapters.CodesAdapter;
import com.project.jeremy.qrcodescanner.R;
import com.project.jeremy.qrcodescanner.model.Code;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import static com.project.jeremy.qrcodescanner.utils.SharedPrefUtilsKt.counterFormat;

@EFragment(R.layout.fragment_code_list)
public class CodeListFragment extends Fragment {

    @Bean
    CodesAdapter codesAdapter;

    @ViewById
    RecyclerView recyclerCodeList;

    @ViewById
    TextView counter;

    CodeScanListViewModel codeScanListViewModel;

    public CodeListFragment() {}

    @AfterViews
    public void bindAdapter() {
        recyclerCodeList.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerCodeList.setAdapter(codesAdapter);
    }

    @AfterViews
    public void init() {
        codeScanListViewModel = ViewModelProviders.of(getActivity()).get(CodeScanListViewModel.class);
        observeCodeScanListViewModel();
    }

    private void observeCodeScanListViewModel() {
        codeScanListViewModel.getCodesList().observe(this, new Observer<List<Code>>() {
            @Override
            public void onChanged(@Nullable List<Code> codes) {
                if (codes != null) {
                    codesAdapter.setCodesList(codes);
                }
            }
        });

        codeScanListViewModel.getCodeCounter().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer count) {
                if(count != null) {
                    counter.setText(counterFormat(count));
                }
            }
        });
    }

}
