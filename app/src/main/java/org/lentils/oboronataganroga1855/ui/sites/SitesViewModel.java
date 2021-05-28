package org.lentils.oboronataganroga1855.ui.sites;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SitesViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public SitesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}