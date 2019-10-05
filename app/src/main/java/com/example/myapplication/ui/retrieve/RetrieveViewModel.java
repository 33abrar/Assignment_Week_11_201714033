package com.example.myapplication.ui.retrieve;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RetrieveViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public RetrieveViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Retrieve fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}