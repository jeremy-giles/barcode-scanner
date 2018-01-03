package com.project.jeremy.qrcodescanner.view.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.project.jeremy.qrcodescanner.view.fragments.CodeListFragment_;
import com.project.jeremy.qrcodescanner.view.fragments.CounterFragment_;
import com.project.jeremy.qrcodescanner.view.fragments.ScannerFragment_;

public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {

    private ScannerFragment_ scannerFragment_;
    private CodeListFragment_ codeListFragment_;
    private CounterFragment_ counterFragment_;

    public SimpleFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
        scannerFragment_ = new ScannerFragment_();
        codeListFragment_ = new CodeListFragment_();
        counterFragment_ = new CounterFragment_();
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return scannerFragment_;
            case 1:
                return codeListFragment_;
            case 2:
                return counterFragment_;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Scanner";
            case 1:
                return "List";
            case 2:
                return "Counter";
            default:
                return null;
        }
    }
}
