package app.com.milipade.talitha_koum.newfarmhouse.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import app.com.milipade.talitha_koum.newfarmhouse.Activities.Base.BaseAuthenticatedActivity;
import app.com.milipade.talitha_koum.newfarmhouse.Adapters.NotificationAdapter;
import app.com.milipade.talitha_koum.newfarmhouse.Models.Notification;
import app.com.milipade.talitha_koum.newfarmhouse.R;
import app.com.milipade.talitha_koum.newfarmhouse.Utils.EndPoint;
import app.com.milipade.talitha_koum.newfarmhouse.Utils.IPPreference;
import app.com.milipade.talitha_koum.newfarmhouse.Views.MainNavDrawer;


/**
 * Created by TALITHA_KOUM on 24/2/2017.
 */

public class NotificationActivity extends BaseAuthenticatedActivity {
    NotificationAdapter adapter;
    List<Notification> notices;
    RecyclerView noticelist;
String ip;
    @Override
    protected void onSocialCreate(@Nullable Bundle savedInstanceState) {
        //super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setNavDrawer(new MainNavDrawer(this));
        ip = new IPPreference(this).getCity();
        //getSupportActionBar().setDisplayShowHomeEnabled(true);
        noticelist = (RecyclerView) findViewById(R.id.notification_list);
        getData();

        /*DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);*/
    }

    public static void start(Context c) {

        c.startActivity(new Intent(c, NotificationActivity.class));
    }

    public void getData() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, String.format(EndPoint.NOTIFICATIONS_URL,ip),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            ArrayList<Notification> data=new ArrayList<>();
                            JSONObject _data = new JSONObject(response);
                            if(_data.getInt("error")==1){

                                Toast.makeText(NotificationActivity.this,_data.getInt("error")+":"+_data.getString("message"), Toast.LENGTH_LONG).show();
                            }
                            JSONArray jArray = _data.getJSONArray("notification");

                            // Extract data from json and store into ArrayList as class objects
                            for(int i=0;i<jArray.length();i++){
                                JSONObject json_data = jArray.getJSONObject(i);
                                Notification fishData = new Notification();

                                fishData.setBrif_message(json_data.getString("brif_message"));
                                fishData.setCreatedAt(json_data.getString("createdAt"));
                                fishData.setImageUri(json_data.getString("imageUri"));
                                fishData.setSubject(json_data.getString("subject"));
                                //fishData.setUser(new User(json_data.getInt("user_id"),json_data.getString("user_name")));

                                data.add(fishData);
                            }

                            // Setup and Handover data to recyclerview
                            adapter = new NotificationAdapter(NotificationActivity.this,data);
                            noticelist.setAdapter(adapter);
                            noticelist.setLayoutManager(new LinearLayoutManager(NotificationActivity.this));

                        } catch (JSONException e) {
                            Toast.makeText(NotificationActivity.this, e.toString(), Toast.LENGTH_LONG).show();
                        }

                    }},
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(NotificationActivity.this,volleyError.toString(), Toast.LENGTH_LONG).show();

                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params =  new Hashtable<String, String>();
                return params;

            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(NotificationActivity.this);
        requestQueue.add(stringRequest);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



}