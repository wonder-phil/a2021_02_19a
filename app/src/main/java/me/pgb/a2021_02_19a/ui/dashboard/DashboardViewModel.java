package me.pgb.a2021_02_19a.ui.dashboard;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DashboardViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private MutableLiveData<Integer> mInt;

    public DashboardViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");

        mInt = new MutableLiveData<>();
        mInt.setValue(0);
    }

    public LiveData<String> getText() {
        return mText;
    }
    public LiveData<Integer> getInt() { return mInt; }
    public void setInt(int value)
    {
        mInt.postValue(new Integer(value));
    }

    public void increment() {
        int val = mInt.getValue().intValue();
        mInt.postValue(Integer.valueOf(val+1));
    }
}