package app.com.milipade.talitha_koum.newfarmhouse.Services;

import android.os.Handler;
import android.util.Log;

import com.squareup.otto.Bus;

import java.util.Random;

import app.com.milipade.talitha_koum.newfarmhouse.Core.YoraApplication;


/**
 * Created by SAMAR on 5/3/2016.
 */
public class BaseLiveService {
    protected final Bus bus;
    protected Handler handler;
    protected Random random;
    //protected final YoraWebService api;

    protected final YoraApplication application;
    private static final  String TAG = BaseLiveService.class.getName();
    /*public BaseLiveService(YoraWebService api, YoraApplication application) {
        //this.api = api;
        this.application = application;
        this.bus = application.getBus();
        bus.register(this);
    }*/

    public BaseLiveService(YoraApplication application) {
        this.application = application;
        this.bus = application.getBus();
        handler = new Handler();
        random = new Random();
        bus.register(this);
        Log.i(TAG,"contractor");
    }


    protected void invokeDelayed(Runnable runnable, long milliSecondMin, long milliSecondMax) {

        if (milliSecondMin > milliSecondMax)
            throw new IllegalArgumentException("Min should be smaller than max");

        long delay = ((long) random.nextDouble() * (milliSecondMax - milliSecondMin) + milliSecondMin);

        handler.postDelayed(runnable, delay);


    }

    protected void postDelayed(final Object event, long milliSecondMin, long milliSecondMax) {
        Log.i(TAG,"postDelayed(final Object event,");
        invokeDelayed(new Runnable() {
            @Override
            public void run() {

                bus.post(event);

            }
        }, milliSecondMin, milliSecondMax);
    }

    protected void postDelayed(Object event, long millisecond) {

        postDelayed(event, millisecond, millisecond);
    }
}
