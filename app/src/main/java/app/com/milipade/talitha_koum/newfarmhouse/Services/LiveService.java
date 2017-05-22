package app.com.milipade.talitha_koum.newfarmhouse.Services;

import android.util.Log;

import com.squareup.otto.Subscribe;

import app.com.milipade.talitha_koum.newfarmhouse.Core.Auth;
import app.com.milipade.talitha_koum.newfarmhouse.Core.User;
import app.com.milipade.talitha_koum.newfarmhouse.Core.YoraApplication;

/**
 * Created by TALITHA_KOUM on 30/4/2017.
 */

public class LiveService extends BaseLiveService {
    private static final String TAG = LiveService.class.getName();
    private final Auth auth;
    YoraApplication application;

    public LiveService(YoraApplication application) {
        super(application);
        auth = application.getAuth();
        this.application =application;
    }

    @Subscribe
    public void LoginWithUsername(final Account.LoginWithUsernameRequest request) {
        Log.i(TAG,""+request.userName+ " "+request.password);
        /*StringRequest stringRequest = new StringRequest(Request.Method.GET, EndPoint.LOGIN_URL+"?email="+request.userName+ "&password="+request.password,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String responseString) {
                        //Toast.makeText(application.getBaseContext(),responseString, Toast.LENGTH_LONG).show();
                        Log.i(TAG,"onResponse(String responseString)");
                        Account.LoginWithUsernameResponse response = new Account.LoginWithUsernameResponse();
                        try {
                            JSONObject userResponse = new JSONObject(responseString);
                            if(userResponse.getInt("error")==1){
                                response.setPropertyError("username",userResponse.getString("message"));
                                bus.post(response);
                            }
                            loginUser(new Account.UserResponse());
                           // login(new Account.UserResponse());
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.i(TAG,"JSONException"+e.toString());
                           loginUser(new Account.UserResponse());
                        }

                    }},
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(application.getBaseContext(),volleyError.toString(), Toast.LENGTH_LONG).show();
                        Log.i(TAG,"onErrorResponse(VolleyError volleyError)"+volleyError.toString());
                        Account.LoginWithUsernameResponse response = new Account.LoginWithUsernameResponse();
                        response.setPropertyError("username", volleyError.toString());
                        bus.post(response);


                    }
                }){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("name", request.userName);
                params.put("email", request.password);
                Log.e(TAG, "params: " + params.toString());
                return params;
            }

        };
        application.getRequestQueue();
        application.addToRequestQueue(stringRequest);*/

       invokeDelayed(new Runnable() {
            @Override
            public void run() {
                Log.i(TAG,"public void run()");
                Account.LoginWithUsernameResponse response = new Account.LoginWithUsernameResponse();

                if (request.userName.equals("username"))
                    response.setPropertyError("username", "invalid username or password");

                loginUser(new Account.UserResponse());
                bus.post(response);


            }
        }, 3000, 4000);
    }
    @Subscribe
    public void loginWithLocalToken(Account.LoginWithLocalTokenRequest request) {

      invokeDelayed(new Runnable() {
           @Override
            public void run() {
                Account.LoginWithLocalTokenResponse response = new Account.LoginWithLocalTokenResponse();
               loginUser(response);
                bus.post(response);
           }
        }, 2000, 3000);
        Log.i(TAG,"loginWithLocalToken");

    }
    private void loginUser(final Account.UserResponse response) {
                        Auth auth = application.getAuth();
                        User user = auth.getUser();

                        user.setDisplayName(response.displayName);
                        user.setUsername(response.userName);
                        user.setEmail(response.email);
                        user.setAvatarUrl("http://www.gravatar.com/avatar/1?id=identicon");
                        user.setLoggedIn(true);
                        user.setId(123);

                        bus.post(new Account.UserDetailUpdatesEvent(user));

                        auth.setAuthToke("fakeAuthToken");

                        response.displayName = user.getDisplayName();
                        response.userName = user.getUsername();
                        response.email = user.getEmail();
                        response.id = user.getId();
                        response.avatarUri = user.getAvatarUrl();
                        response.authToken = auth.getAuthToken();
        //StringRequest s = new StringRequest()
    }
    private void login(Account.UserResponse response) {
        if (response.authToken != null && !response.authToken.isEmpty()) {
            auth.setAuthToke(response.authToken);
        }

        User user = auth.getUser();
        user.setId(response.id);
        user.setDisplayName(response.displayName);
        user.setUsername(response.userName);
        user.setEmail(response.email);
        user.setAvatarUrl(response.avatarUri);
        user.setHasPassword(response.hasPassword);
        user.setHasPassword(true);

        bus.post(new Account.UserDetailUpdatesEvent(user));
    }

}
