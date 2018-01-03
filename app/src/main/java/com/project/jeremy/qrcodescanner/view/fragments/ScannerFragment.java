package com.project.jeremy.qrcodescanner.view.fragments;


import android.Manifest;
import android.arch.lifecycle.ViewModelProviders;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v4.app.Fragment;
import android.transition.TransitionManager;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Switch;

import com.google.zxing.Result;
import com.project.jeremy.qrcodescanner.viewModel.CodeScanListViewModel;
import com.project.jeremy.qrcodescanner.R;
import com.tbruyelle.rxpermissions2.RxPermissions;


import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

@EFragment(R.layout.fragment_scanner)
public class ScannerFragment extends Fragment implements ZXingScannerView.ResultHandler {

    @ViewById(R.id.scanner)
    ZXingScannerView scannerView;

    @ViewById
    RadioButton matchCode;

    @ViewById
    ConstraintLayout scanModeLayout;

    @ViewById
    Switch switchScannerMode;

    private CodeScanListViewModel codeScanListViewModel;

    private boolean continueScan;

    public ScannerFragment() {}

    @AfterViews
    public void init() {
        new RxPermissions(getActivity())
                .request(Manifest.permission.CAMERA)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Boolean>() {

                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        // Permissions accepted
                        startScanner();
                    }

                }, new Consumer<Throwable>() {

                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        // Permissions denied
                    }

                });

        codeScanListViewModel = ViewModelProviders.of(getActivity()).get(CodeScanListViewModel.class);
        continueScan = true;
    }

    @AfterViews
    public void initSwitchMode() {

        final ConstraintSet csOn = new ConstraintSet();
        csOn.clone(getActivity(), R.layout.scanner_on_mode_layout);
        final ConstraintSet csOff = new ConstraintSet();
        csOff.clone(getActivity(), R.layout.scanner_off_mode_layout);

        switchScannerMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ConstraintSet playCs = isChecked ? csOff : csOn;
                TransitionManager.beginDelayedTransition(scanModeLayout);
                playCs.applyTo(scanModeLayout);

                continueScan = isChecked;
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        scannerView.resumeCameraPreview(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        scannerView.stopCameraPreview();
    }

    @Override
    public void onStop() {
        super.onStop();
        scannerView.stopCamera();
    }

    @Override
    public void handleResult(Result result) {
        if (matchCode != null)
            matchCode.setChecked(true); // TODO Check, this line produces a NullPointerException
        String scanResult = result.getText();
        if(scannerView != null & continueScan) {
            scannerView.resumeCameraPreview(this);
            matchCode.setChecked(false);
        }
        codeScanListViewModel.saveOrUpdateCode(scanResult);
    }

    private void startScanner() {
        scannerView.setResultHandler(this);
        scannerView.startCamera();
    }

    @Click(R.id.btnScanner)
    public void handleReloadScanner() {
        scannerView.resumeCameraPreview(this);
        matchCode.setChecked(false);
    }
}
