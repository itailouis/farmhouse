package app.com.milipade.talitha_koum.newfarmhouse.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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
import app.com.milipade.talitha_koum.newfarmhouse.Adapters.CropViewAdapter;
import app.com.milipade.talitha_koum.newfarmhouse.Models.CropProduce;
import app.com.milipade.talitha_koum.newfarmhouse.R;
import app.com.milipade.talitha_koum.newfarmhouse.Utils.EndPoint;
import app.com.milipade.talitha_koum.newfarmhouse.Utils.IPPreference;
import app.com.milipade.talitha_koum.newfarmhouse.Views.MainNavDrawer;


public class CropActivity extends BaseAuthenticatedActivity {

    private RecyclerView recyclerView;
    private CropViewAdapter adapter;
    private List<CropProduce> albumList;
    String ip;
    private static final String TAG = CropActivity.class.getSimpleName();

    @Override
    protected void onSocialCreate(Bundle savedInstanceState) {
        //super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crop);
        ip = new IPPreference(this).getCity();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setNavDrawer(new MainNavDrawer(this));
        //initCollapsingToolbar();
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        albumList = new ArrayList<>();

        adapter = new CropViewAdapter(this, albumList);
        adapter.setFragManager(getSupportFragmentManager());
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        //DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
       /* ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();*/

       // NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        //navigationView.setNavigationItemSelectedListener(this);
        /*recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this,
                new RecyclerItemClickListener.OnItemClickListener() {

                    @Override
                    public void onItemClick(View view, int position) {

                        //Intent intent = new Intent(this, DetailActivity.class);
                        //intent.putParcelableArrayListExtra("data", data);
                        //intent.putExtra("pos", position);
                        //startActivity(intent);
                        Snackbar me =Snackbar.make(view ,"Add to farm list",Snackbar.LENGTH_LONG);
                        me.setAction("Add", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(CropActivity.this,"ADDED",Toast.LENGTH_LONG).show();
                            }
                        });
                        me.setActionTextColor(Color.WHITE);
                        me.show();
                    }
                }));*/
        prepareAlbums();

        /*try {
           // Glide.with(this).load(R.drawable.cover).into((ImageView) findViewById(R.id.backdrop));
            ImageView image=   (ImageView) findViewById(R.id.backdrop);
            image.setImageResource(R.drawable.quila);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Cache cache = FarmApp.getInstance().getRequestQueue().getCache();
        Cache.Entry entry = cache.get(EndPoints.CHAT_ROOMS);
        if (entry != null) {
            // fetch the data from cache
            try {
                String data = new String(entry.data, "UTF-8");
                try {
                    parseJsonFeed(new JSONObject(data));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

        } else {
            // making fresh volley request and getting json
            JsonObjectRequest jsonReq = new JsonObjectRequest(Request.Method.GET,
                    EndPoints.CHAT_ROOMS, null, new Response.Listener<JSONObject>() {

                @Override
                public void onResponse(JSONObject response) {
                    VolleyLog.d(TAG, "Response: " + response.toString());
                    if (response != null) {
                        parseJsonFeed(response);
                    }
                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    VolleyLog.d(TAG, "Error: " + error.getMessage());
                }
            });

            // Adding request to volley request queue
            //FarmApp.getInstance().addToRequestQueue(jsonReq);
        }
*/


    }

    /**
     * Initializing collapsing toolbar
     * Will show and hide the toolbar title on scroll
     */


    /**
     * Adding few albums for testing
     */
    private void prepareAlbums() {
        //CropProduce a = new CropProduce();
        //a.setName("Chibage");
        //a.setPic(R.drawable.quila);
        //a.setId(23);
        //albumList.add(a);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, String.format(EndPoint.CROP_URL,ip),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                           // ArrayList<Notification> data=new ArrayList<>();
                            albumList = new ArrayList<>();
                            JSONObject  obj= new JSONObject(response);
                            if(obj.getInt("error")==1){

                            }
                            JSONArray array= obj.getJSONArray("crops");

                            for(int i =0;i<array.length();i++){
                                JSONObject json_data =array.getJSONObject(i);
                                CropProduce fishData = new CropProduce();

                                fishData.setName(json_data.getString("crop_name"));
                                fishData.setCropType(json_data.getString("crop_type"));
                                fishData.setFieldType(json_data.getString("crop_fieldType"));
                                fishData.setAvatearImage(json_data.getString("crop_avatar"));
                                fishData.setCropReturn(json_data.getString("crop_return"));
                                fishData.setSeed(json_data.getString("crop_seed"));
                                fishData.setPrice(json_data.getDouble("crop_price"));
                                fishData.setOutput(json_data.getString("crop_output"));

                                //fishData.setFertilizers(ne);new User(json_data.getInt("user_id"),json_data.getString("user_name")));
                                Log.i("TAG","");
                                albumList.add(fishData);
                            }

                            // Setup and Handover data to recyclerview
                            adapter =new  CropViewAdapter(CropActivity.this,albumList);
                            recyclerView.setAdapter(adapter);
                            recyclerView.getAdapter().notifyDataSetChanged();
                            //recyclerView.setLayoutManager(new LinearLayoutManager(CropActivity.this));

                        } catch (JSONException e) {
                            Toast.makeText(CropActivity.this, e.toString(), Toast.LENGTH_LONG).show();
                        }

                    }},
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(CropActivity.this,volleyError.toString(), Toast.LENGTH_LONG).show();

                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params =  new Hashtable<String, String>();
                return params;

            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(CropActivity.this);
        requestQueue.add(stringRequest);



    }

    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
    private void parseJsonFeed(JSONObject response) {
        try {
            JSONArray feedArray = response.getJSONArray("feed");

            for (int i = 0; i < feedArray.length(); i++) {
                JSONObject feedObj = (JSONObject) feedArray.get(i);

                CropProduce item = new CropProduce();
                item.setId(feedObj.getInt("id"));
                item.setName(feedObj.getString("name"));

                // Image might be null sometimes
                String image = feedObj.isNull("image") ? null : feedObj.getString("image");
                item.setAvatearImage(image);
                //item.setPic(image);
                item.setName(feedObj.getString("status"));

                item.setCropType(feedObj.getString("timeStamp"));

                // url might be null sometimes
                String feedUrl = feedObj.isNull("url") ? null : feedObj.getString("url");


                albumList.add(item);
            }

            // notify data changes to list adapater
            adapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public static void start(Context c) {

        c.startActivity(new Intent(c, CropActivity.class));
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
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
