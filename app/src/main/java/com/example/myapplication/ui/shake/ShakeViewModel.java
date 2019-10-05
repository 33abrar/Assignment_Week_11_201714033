package com.example.myapplication.ui.shake;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ShakeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ShakeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Shake fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}