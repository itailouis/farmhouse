package app.com.milipade.talitha_koum.newfarmhouse.Core;

import android.app.Application;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.squareup.otto.Bus;

import app.com.milipade.talitha_koum.newfarmhouse.Modules.Module;
import app.com.milipade.talitha_koum.newfarmhouse.Utils.MyPreferenceManager;


public class YoraApplication extends Application {

    //public static final Uri API_ENDPOINT = Uri.parse("http://yora-playground.3dbuzz.com");
    //public static final String STUDENT_TOKEN = "378a39794159458dbef0359b5b08510e";

    private Auth auth;
    private Bus bus;
    public static final String TAG = YoraApplication.class.getSimpleName();
    private MyPreferenceManager pref;
    private RequestQueue mRequestQueue;
   //private Picasso authPicasso;

    public YoraApplication() {
        bus = new Bus();
    }


    @Override
    public void onCreate() {
        super.onCreate();
        auth = new Auth(this);

        //createAuthedPicasso();

        Module.register(this);

    }


    public MyPreferenceManager getPrefManager() {
        if (pref == null) {
            pref = new MyPreferenceManager(this);
        }

        return pref;
    }
    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }
    public <T> void addToRequestQueue(Request<T> req, String tag) {
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }


    public Auth getAuth() {
        return auth;
    }

    public Bus getBus() {
        return bus;
    }

}
