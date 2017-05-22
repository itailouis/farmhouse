package app.com.milipade.talitha_koum.newfarmhouse.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;
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
import java.util.Map;

import app.com.milipade.talitha_koum.newfarmhouse.Models.CropProduce;
import app.com.milipade.talitha_koum.newfarmhouse.R;
import app.com.milipade.talitha_koum.newfarmhouse.Utils.EndPoint;
import app.com.milipade.talitha_koum.newfarmhouse.Utils.IPPreference;

public class ShowActivity extends AppCompatActivity  implements View.OnClickListener{
    String region="";
    String season="";
    TableLayout table;
    TextView cname,seed,fert,lobour,output,price,creturn;
    CheckBox CropName;
    TableLayout tl;
    TableRow tr;
    ArrayList<CropProduce> selectedId;
String ip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle b = getIntent().getExtras();
        if(!b.isEmpty()){
            region  = b.getString("region");
            season  = b.getString("season");
        }
        setContentView(R.layout.activity_show);
        ip = new IPPreference(this).getCity();
        selectedId = new ArrayList<>();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        table = (TableLayout)findViewById(R.id.maintable);
        loadable();
        //addData();



        Button fab = (Button) findViewById(R.id.analyse);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent i =new Intent(ShowActivity.this,ProductivityActivity.class);
                //i.p
                i.putExtra("cropsselected",selectedId);
                //i.putStringArrayListExtra("cropsselected",selectedId);
                startActivity(i);
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    void loadable(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, String.format(EndPoint.CROP_URL+"?region="+region+"&season="+season,ip),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Snackbar.make(findViewById(R.id.analyse),response,Snackbar.LENGTH_LONG).show();
                        try {
                            JSONObject  obj= new JSONObject(response);
                            if(obj.getInt("error")==1){

                            }
                            JSONArray array= obj.getJSONArray("crops");

                            for(int i =0;i<array.length();i++){
                                JSONObject data =array.getJSONObject(i);
                                CropProduce crop= new CropProduce();
                                crop.setId(i);
                                crop.setName(data.getString("crop_name"));
                                crop.setFieldCoverage(data.getDouble("crop_coverage"));
                                crop.setLobor(data.getString("crop_lobor"));
                                crop.setCropReturn(data.getString("crop_return"));
                                crop.setSeed(data.getString("crop_seed"));
                                crop.setFertilizer(data.getString("crop_seed"));
                                crop.setPrice(data.getDouble("crop_price"));
                                crop.setOutput(data.getString("crop_output"));
                                crop.setAvarage(78);
                                addData(crop);
                            }
                            //addData(array);
                        } catch (JSONException e) {
                            Log.i("JSONException e",e.getMessage());
                            e.printStackTrace();
                        }


                    }},
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(ShowActivity.this,volleyError.toString(), Toast.LENGTH_LONG).show();

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
        RequestQueue requestQueue = Volley.newRequestQueue(ShowActivity.this);
        requestQueue.add(stringRequest);



    }
    @Override
    public void onClick(final View view) {



    }
    //public void	addData(JSONArray array){
       public void	addData(final CropProduce crop) {
           //for ( int i =0 ; i < array.length();i++){
           cname = new TextView(this);
           cname.setText(""+1);
           cname.setVisibility(View.INVISIBLE);
           tr = new TableRow(this);
           tr.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
           CropName = new CheckBox(this);
           CropName.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
           CropName.setText(crop.getName());
           CropName.setPadding(5, 5, 5, 5);
           CropName.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
               @Override
               public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                   //Toast.makeText(FeedBackActivity.this ,isChecked+"",Toast.LENGTH_LONG).show();
                   //int id = crop.getId();
                   if(isChecked){
                       selectedId.add(crop);
                   }else{
                       selectedId.remove(crop);
                   }
               }
           });
           CropName.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {

               }
           });
           tr.addView(CropName);
           seed = new TextView(this);
           seed.setText(crop.getSeed());
           seed.setTextColor(Color.RED);
           seed.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
           seed.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
           seed.setPadding(5, 5, 5, 5);
           tr.addView(seed);
           fert = new TextView(this);
           fert.setText(crop.getFertilizer());
           fert.setTextColor(Color.RED);
           fert.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
           fert.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
           fert.setPadding(5, 5, 5, 5);
           tr.addView(fert);
           lobour = new TextView(this);
           lobour.setText(crop.getLobor());
           lobour.setTextColor(Color.RED);
           lobour.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
           lobour.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
           lobour.setPadding(5, 5, 5, 5);
           tr.addView(lobour);
           output = new TextView(this);
           output.setText(crop.getOutput());
           output.setTextColor(Color.RED);
           output.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
           output.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
           output.setPadding(5, 5, 5, 5);
           tr.addView(output);
           price = new TextView(this);
           price.setText(crop.getPrice()+"");
           price.setTextColor(Color.RED);
           price.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
           price.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
           price.setPadding(5, 5, 5, 5);
           tr.addView(price);
           creturn = new TextView(this);
           creturn.setText(crop.getCropReturn());
           creturn.setTextColor(Color.RED);
           creturn.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
           creturn.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
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
