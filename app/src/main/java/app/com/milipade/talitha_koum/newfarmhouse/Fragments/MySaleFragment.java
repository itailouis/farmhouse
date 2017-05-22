package app.com.milipade.talitha_koum.newfarmhouse.Fragments;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
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

import app.com.milipade.talitha_koum.newfarmhouse.Adapters.BuyersAdapter;
import app.com.milipade.talitha_koum.newfarmhouse.Models.Investment;
import app.com.milipade.talitha_koum.newfarmhouse.R;
import app.com.milipade.talitha_koum.newfarmhouse.Utils.CustomRequest;
import app.com.milipade.talitha_koum.newfarmhouse.Utils.EndPoint;


/**
 * A placeholder fragment containing a simple view.
 */
public class MySaleFragment extends Fragment {
    private final static String TAG = MySaleFragment.class.getName();
    private ListView listview;
    private BuyersAdapter adapter;
    //private TextView ErrorMessage;
   // private View EmptyLayout ;
    private List<Investment> data = null;
    private Context context;
    public MySaleFragment() {
    }
    public static MySaleFragment newInstance() {
        MySaleFragment fragment = new MySaleFragment();

        return fragment;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View tabView =inflater.inflate(R.layout.investiment_content_main, container, false);
        if(isOnline()){
            //getJsonObect();
        }else{

        }
        //listview=(ListView) tabView.findViewById(R.id.list_buyer_holder);
        //listview.setAdapter(adapter);

        /*listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent= new Intent(getContext(),NewsActivity.class);
                //intent.putExtra("title",);
                startActivity(intent);
            }
        });*/
        return tabView;
    }
    public void updateDisplay(){
        if(data.isEmpty()){

        }else{
            //adapter = new BuyersAdapter(getContext(),data);
            //listview.setAdapter(adapter);
        }


    }

    public void getJsonObect(){

        CustomRequest req = new CustomRequest(Request.Method.GET, EndPoint.NEWS_URL,
                null ,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject jObj) {
                        JSONArray news;
                        String msg;
                        List<Investment> NewsList = new ArrayList<>();
                        try {
                            int error = jObj.getInt("error");

                            if(error==0){
                                //msg=jObj.getString("message");
                                news= jObj.getJSONArray("news");
                                //for (a:news);
                              for (int i =0;i< news.length();i++ ) {
                                  Log.e(TAG,news.length()+"");
                                  Investment newE = new Investment();
                                    Log.e(TAG,"data : "+i);
                                    /*newE.setContent("hhhhhhhhhhhhhhhhh");
                                    newE.setRate("4");
                                    newE.setId(i);
                                    newE.setSummary("kkk");
                                    newE.setPublicationDate("27-18-2017");
                                    newE.setStatus("yyryr");
                                    newE.setTitle("hie how are u: "+i);
                                    NewsList.add(newE);*/
                                  Log.e(TAG,NewsList.size()+"");
                                }

                            }
                            data= NewsList;
                            //adapter = new BuyersAdapter(getContext(),);
                            //listview.setAdapter(adapter);
                             updateDisplay();


                        } catch (JSONException e) {
                           // EmptyLayout.setVisibility(View.VISIBLE);
                           // ErrorMessage.setText(e.getMessage());
                            //ErrorMessage.setTextColor(Color.RED);
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

