package app.com.milipade.talitha_koum.newfarmhouse.Modules;


import app.com.milipade.talitha_koum.newfarmhouse.Core.YoraApplication;
import app.com.milipade.talitha_koum.newfarmhouse.Services.LiveService;


public class Module {

    public static void register(YoraApplication application) {
        new LiveService(application);

    }




    /*private static class AuthInterceptor implements Interceptor {

        private final Auth auth;

        public AuthInterceptor(Auth auth) {
            this.auth = auth;
        }


    }

    private static final String[] DATE_FORMAT = new String[]{
            "yyy-MM-dd'T'HH:mm:ss.SSSZ",
            "yyy-MM-dd'T'HH:mm:ss.SSS",
            "yyy-MM-dd'T'HH:mmZ",
            "yyy-MM-dd'T'HH:mm",
            "yyy-MM-dd",
    };*/



}