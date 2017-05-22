package app.com.milipade.talitha_koum.newfarmhouse.Fragments.Base;

import android.app.Fragment;
import android.os.Bundle;

import com.squareup.otto.Bus;

import app.com.milipade.talitha_koum.newfarmhouse.Core.YoraApplication;


/**
 * Created by SAMAR on 2/20/2016.
 */
public abstract class BaseFragment extends Fragment {

    protected YoraApplication application;
    protected Bus bus;
    //protected ActionScheduler scheduler;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        application = (YoraApplication) getActivity().getApplication();
        //scheduler = new ActionScheduler(application);

        bus = application.getBus();
        bus.register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        bus.unregister(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        //scheduler.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        //scheduler.onPause();
    }
}
