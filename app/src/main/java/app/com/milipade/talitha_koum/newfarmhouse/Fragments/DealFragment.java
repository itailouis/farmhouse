package app.com.milipade.talitha_koum.newfarmhouse.Fragments;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import app.com.milipade.talitha_koum.newfarmhouse.Adapters.DealAdapter;
import app.com.milipade.talitha_koum.newfarmhouse.Models.Buyer;
import app.com.milipade.talitha_koum.newfarmhouse.R;
import app.com.milipade.talitha_koum.newfarmhouse.Utils.CustomRequest;
import app.com.milipade.talitha_koum.newfarmhouse.Utils.EndPoint;
import app.com.milipade.talitha_koum.newfarmhouse.Utils.IPPreference;


/**
 * A placeholder fragment containing a simple view.
 */
public class DealFragment extends Fragment {
    private final static String TAG = DealFragment.class.getName();
    private RecyclerView listview;
    private DealAdapter adapter;
    //private TextView ErrorMessage;
   // private View EmptyLayout ;
    private List<Buyer> data = null;
    private Context context;
    String ip;
    public DealFragment() {
    }
    public static DealFragment newInstance() {
        DealFragment fragment = new DealFragment();

        return fragment;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        ip = new IPPreference(context).getCity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View tabView =inflater.inflate(R.layout.investiment_content_main, container, false);
         listview=(RecyclerView) tabView.findViewById(R.id.list_buyer_holder);
    if(isOnline()){
        getJsonObect();
    }else{

    }

    listview.setAdapter(adapter);
    listview.setLayoutManager(new LinearLayoutManager(getContext()));


    return tabView;
}
    public void updateDisplay(){
        if(data.isEmpty()){
            adapter = new DealAdapter(getContext(),data);
            listview.setAdapter(adapter);
        }else{
            adapter.notifyDataSetChanged();
        }


    }

    public void getJsonObect(){

        CustomRequest req = new CustomRequest(Request.Method.GET,String.format(EndPoint.DEAL_URL,ip) ,
                null ,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject jObj) {
                        JSONArray buy;
                        String msg;
                        List<Buyer> buyersList = new ArrayList<>();
                        try {
                            int error = jObj.getInt("error");

                            if(error==0){
                                //msg=jObj.getString("message");
                                buy= jObj.getJSONArray("buyer");

                                Log.e(TAG,buy.length()+"  "+buy.toString());
                                for (int i =0;i<buy.length();i++ ) {
                                    JSONObject buyOB = buy.getJSONObject(i);
                                    Buyer buyer = new Buyer();
                                    Log.e(TAG,"data : "+buy.getString(0));
                                    buyer.setBuyerName(buyOB.getString("buyerName"));
                                    buyer.setBrefMassage(buyOB.getString("delidate"));
                                   // buyer.describeContents(buyOB.getString("amount"));
                                    buyer.setQuantity(buyOB.getDouble("quantinty"));
                                   // buyer.setCrop(new CropProduce(buyOB.getString("crop")));
                                    buyersList.add(buyer);
                                    //Log.e(TAG,buyersList.size()+"");
                                }

                            }
                            data= buyersList;
                            adapter = new DealAdapter(getContext(),data);
                            //Log.e(TAG,"data : "+data.get(1).getBrefMassage());
                            updateDisplay();
                            listview.setAdapter(adapter);


                        } catch (JSONException e) {
                            // EmptyLayout.setVisibility(View.VISIBLE);
                            // ErrorMessage.setText(e.getMessage());
                            //ErrorMessage.setTextColor(Color.RED);
                            Toast.makeText(getContext(),e.toString(),Toast.LENGTH_LONG).show();
                        }
                        //finish();

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError volleyError) {
                //EmptyLayout.setVisibility(View.VISIBLE);
                if (volleyError instanceof TimeoutError ){
                    Toast.makeText(getContext(), "error 0 e" + volleyError.getMessage(), Toast.LENGTH_SHORT).show();
                } else if (volleyError instanceof NoConnectionError) {
                    Toast.makeText(getContext(), "error 0" + volleyError.getMessage(), Toast.LENGTH_SHORT).show();
                } else if (volleyError instanceof AuthFailureError) {
                    Toast.makeText(getContext(), "error 1" + volleyError.getMessage(), Toast.LENGTH_SHORT).show();
                } else if (volleyError instanceof ServerError) {
                    Toast.makeText(getContext(), "error 2" + volleyError.getMessage(), Toast.LENGTH_SHORT).show();
                } else if (volleyError instanceof NetworkError) {
                    Toast.makeText(getContext(), "error 3" + volleyError.getMessage(), Toast.LENGTH_SHORT).show();
                } else if (volleyError instanceof ParseError) {
                    Toast.makeText(getContext(), "error 4" + volleyError.getMessage(), Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(getContext(),volleyError.toString(),Toast.LENGTH_LONG).show();
                //ErrorMessage.setText(volleyError.getMessage());
                //ErrorMessage.setTextColor(Color.RED);

            }
        });
        RequestQueue queue = Volley.newRequestQueue(getContext());
        queue.add(req);
    }



    public boolean isOnline(){

        ConnectivityManager cm = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        if(info != null&& info.isConnectedOrConnecting()){
            return true;
        } else{
            return false;
        }

    }
}

