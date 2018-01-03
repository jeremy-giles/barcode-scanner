package com.project.jeremy.qrcodescanner.viewModel;


import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.project.jeremy.qrcodescanner.model.Code;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class CodeScanListViewModel extends AndroidViewModel {

    private MutableLiveData<List<Code>> codesList;
    private HashMap<String, Code> codes = new HashMap<>();

    private MutableLiveData<Integer> codeCounter;
    private int counter;

    public CodeScanListViewModel(@NonNull Application application) {
        super(application);
        codeCounter = new MutableLiveData<>();
        codesList = new MutableLiveData<>();
    }

    public MutableLiveData<List<Code>> getCodesList() {
        return codesList;
    }

    public MutableLiveData<Integer> getCodeCounter() {
        return codeCounter;
    }

    public void saveOrUpdateCode(String code) {
        if(!codes.containsKey(code)) {
            codes.put(code, new Code(code, new AtomicInteger(0)));
        }
        codes.get(code).getCount().incrementAndGet();
        List<Code> l = new ArrayList<>(codes.values());

        codesList.postValue(l);
        codeCounter.postValue(counter++);
    }

}
