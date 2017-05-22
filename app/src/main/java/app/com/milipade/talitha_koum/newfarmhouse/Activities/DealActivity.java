package app.com.milipade.talitha_koum.newfarmhouse.Activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Hashtable;
import java.util.Map;

import app.com.milipade.talitha_koum.newfarmhouse.Models.Buyer;
import app.com.milipade.talitha_koum.newfarmhouse.R;
import app.com.milipade.talitha_koum.newfarmhouse.Utils.IPPreference;

/**
 * Created by TALITHA_KOUM on 24/2/2017.
 */

public class DealActivity extends AppCompatActivity {

    Button requestBtn;
    EditText quantity,delivaryDate,amount;
    Buyer buyer;
    TextView title,msg,email;
    String ip;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle b = getIntent().getExtras();
        if(!b.isEmpty()){
            buyer  = b.getParcelable("buyer");

        }
        ip = new IPPreference(this).getCity();
        setContentView(R.layout.buyer_info_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        title = (TextView) findViewById(R.id.buyer_info_name);
        msg = (TextView) findViewById(R.id.textView15);
        email = (TextView) findViewById(R.id.buyer_info_name);

        title.setText(buyer.getBuyerName());
        msg.setText(String.format("willing to offer wheet to %s",buyer.getBuyerName()));
        //email.setText(buyer.);
        requestBtn = (Button) findViewById(R.id.send_request);
        quantity = (EditText) findViewById(R.id.txt_offerQuantity);
        delivaryDate = (EditText) findViewById(R.id.date_delivery);
        amount = (EditText) findViewById(R.id.amountTotal);
        //quantity.setText(buyer.getQuantity()+"");
        requestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendData();
            }
        });

    }





    public void sendData() {
        String qu =quantity.getText().toString();
        String da =delivaryDate.getText().toString();
        String am =amount.getText().toString();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://"+ip+"/farmhouse/api/adddeal?buyer="+buyer.getId()+"&quantinty="+qu+"&amount="+am+"&date="+da,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                            Toast.makeText(DealActivity.this, "Data has been saved", Toast.LENGTH_LONG).show();

                    }},
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(DealActivity.this,volleyError.toString(), Toast.LENGTH_LONG).show();

                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params =  new Hashtable<String, String>();
                params.put("quantity",quantity.getText().toString());
                params.put("delivaryDate",delivaryDate.getText().toString());
                params.put("buyerId",buyer.getId()+"");
                return params;

            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(DealActivity.this);
        requestQueue.add(stringRequest);

    }
}