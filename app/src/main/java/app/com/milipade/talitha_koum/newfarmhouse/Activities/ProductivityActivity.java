package app.com.milipade.talitha_koum.newfarmhouse.Activities;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import app.com.milipade.talitha_koum.newfarmhouse.Adapters.GraphAdapter;
import app.com.milipade.talitha_koum.newfarmhouse.Models.CropProduce;
import app.com.milipade.talitha_koum.newfarmhouse.R;
import app.com.milipade.talitha_koum.newfarmhouse.Utils.EndPoint;
import app.com.milipade.talitha_koum.newfarmhouse.Utils.IPPreference;

public class ProductivityActivity extends AppCompatActivity {

    String region="";
    String season="";
    RecyclerView table;
    TextView cname,fert,lobour,output,creturn,CropName,land;
    //CheckBox ;
    //EditText land;
    TextView price;
    TableLayout tl;
    TableRow tr;
    Map cropprice;
    ArrayAdapter<String> adapter;
    ArrayList<CropProduce> cropID;
    GraphAdapter ga;
    String ip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_productivity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //GraphView graph = (GraphView) findViewById(R.id.graph);
        //SimpleLineGraph sl= new SimpleLineGraph();
        ip = new IPPreference(this).getCity();
        //sl.initGraph(graph);
        //table = (TableLayout)findViewById(R.id.reporttable);
        //loadable();
        //addData();
        Bundle b = getIntent().getExtras();
        if(!b.isEmpty()) {
           cropID = b.getParcelableArrayList("cropsselected");
            Toast.makeText(this,cropID.size()+"nnn",Toast.LENGTH_LONG).show();
        }
         ga = new GraphAdapter(this,cropID);
        table = (RecyclerView)findViewById(R.id.item_list);
        table.setAdapter(ga);
        table.setLayoutManager(new LinearLayoutManager(this));
        //Button fab = (Button) findViewById(R.id.createReport);
        //addData(cropID);
        //fab.setOnClickListener(new View.OnClickListener() {
           // @Override
           // public void onClick(View view) {
             // Savereport();
           // }
        //});
        cropprice = new Hashtable<>();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void Savereport() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, EndPoint.CROP_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }},
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        //Toast.makeText(ProductivityActivity.this,volleyError.toString(), Toast.LENGTH_LONG).show();

                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params =  new Hashtable<String, String>();
                params.put("season",season);
                params.put("region",region);

                return params;

            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(ProductivityActivity.this);
        requestQueue.add(stringRequest);




    }

    void loadable(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, String.format(EndPoint.CROP_URL,ip),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }},
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        //Toast.makeText(ProductivityActivity.this,volleyError.toString(), Toast.LENGTH_LONG).show();

                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params =  new Hashtable<String, String>();
                params.put("season",season);
                params.put("region",region);

                return params;

            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(ProductivityActivity.this);
        requestQueue.add(stringRequest);



    }

    //public void	addData(JSONArray array){
    public void	addData(List<CropProduce> crops) {

       for (int i = 0; i < crops.size(); i++) {
           CropProduce crop = crops.get(i);
            cname = new TextView(this);
            cname.setText(crop.getId()+"");
            cname.setVisibility(View.INVISIBLE);
            tr = new TableRow(this);
            tr.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
            CropName = new TextView(this);
            CropName.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
            CropName.setText(crop.getName());
            CropName.setPadding(5, 5, 5, 5);
            CropName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int id = Integer.parseInt(cname.getText().toString());

                }
            });
            tr.addView(CropName);
            land = new TextView(this);
            land.setText("");
            land.setTextColor(Color.RED);
            land.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
            land.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
            land.setPadding(5, 5, 5, 5);
            tr.addView(land);
            fert = new TextView(this);
            fert.setText(crop.getFertilizer());
            fert.setTextColor(Color.RED);
            fert.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
            fert.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
            fert.setPadding(5, 5, 5, 5);
            tr.addView(fert);
            lobour = new TextView(this);
            lobour.setText(crop.getLobor());
            lobour.setTextColor(Color.RED);
            lobour.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
            lobour.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
            lobour.setPadding(5, 5, 5, 5);
            tr.addView(lobour);
            output = new TextView(this);
            output.setText(crop.getOutput());
            output.setTextColor(Color.RED);
            output.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
            output.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
            output.setPadding(5, 5, 5, 5);
            tr.addView(output);
            //price = new Spinner(this);
           price = new TextView(this);
           price.setId(crop.getId());
            price.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
            price.setPadding(5, 5, 5, 5);
           price.setText(crop.getPrice()+"");
           //loadSpinner();

            tr.addView(price);
            creturn = new TextView(this);
            creturn.setText(crop.getCropReturn());
            creturn.setTextColor(Color.RED);
            creturn.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
            creturn.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
            creturn.setPadding(5, 5, 5, 5);
            tr.addView(creturn);
            tr.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent p2) {
                    // TODO: Implement this method
                    v.getId();
                    tr.setBackgroundColor(Color.WHITE);
                    return false;
                }
            });
           table.addView(tr);
    }
    }
    private void loadSpinner(String crop) {
        String[] season = {"WINTER","SUMMER","SPRING"};
        Set<String> set = new HashSet<String>();
        for(int t =0;t<season.length;t++) {
            set.add(season[t]);

        }
        List<String> list = new ArrayList<String>(set);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

       // price.setAdapter(adapter);
        price.setWillNotDraw(false);
        JsonArrayRequest stringRequest = new JsonArrayRequest(String.format(EndPoint.AVARAGE_URL+"?crop="+crop,ip),
                //StringRequest stringRequest = new StringRequest(Request.Method.GET, EndPoint.URL_PUMP,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        //Toast.makeText(MainActivity.this,response+": this from thee server ", Toast.LENGTH_LONG).show();
                        Set<String> set = new HashSet<String>();
                        try {
                            //response
                            for(int i =0;i<response.length();i++) {
                                JSONObject fuel = response.getJSONObject(i);
                                set.add(fuel.getString("name"));
                                cropprice.put(fuel.getString("name"),fuel.getString("rate"));
                                //rate.setText(""+response);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                        List<String> list = new ArrayList<String>(set);
                        adapter = new ArrayAdapter<String>(ProductivityActivity.this, android.R.layout.simple_spinner_item, list);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                        //price.setAdapter(adapter);
                        //price.setWillNotDraw(false);

                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(ProductivityActivity.this,volleyError.toString(), Toast.LENGTH_LONG).show();

                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params =  new Hashtable<String, String>();
                params.put("","");
                params.put("","");
                return params;

            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(ProductivityActivity.this);
        requestQueue.add(stringRequest);


    }


}
