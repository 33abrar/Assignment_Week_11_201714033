package com.example.myapplication.ui.proximity;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ProximityViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ProximityViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Proximity fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}