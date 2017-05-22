package app.com.milipade.talitha_koum.newfarmhouse.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;

import app.com.milipade.talitha_koum.newfarmhouse.Activities.DealActivity;
import app.com.milipade.talitha_koum.newfarmhouse.Models.CropProduce;
import app.com.milipade.talitha_koum.newfarmhouse.R;


/**
 * Created by TALITHA_KOUM on 19/11/2016.
 */
public class GraphAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final static String TAG=  GraphAdapter.class.getName();
    private Context context;
    private ArrayList<CropProduce>  buyerList;
    private LayoutInflater layoutInflater;

    public GraphAdapter(Context context, ArrayList<CropProduce>  buyerList) {
        this.context = context;
        this.buyerList = buyerList;
        layoutInflater = LayoutInflater.from(context);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView title;
        TextView subtitle;
        GraphView graph;
        DataPoint[] points;
        public ViewHolder(View view) {
            super(view);
            graph  = (GraphView) view.findViewById(R.id.graph);
            title = (TextView) view.findViewById(R.id.title);
            subtitle= (TextView) view.findViewById(R.id.subtitle);
            //view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition(); // gets item position
            if (position != RecyclerView.NO_POSITION) { // Check if an item was deleted, but the user clicked it before the UI removed it
                  //Buyer buyer = buyerList.get(position);
                // We can access the data within the views
                Intent b = new Intent(context,DealActivity.class);
                //b.putExtra("buyer",buyer);
               // context.startActivity(b);
            }

        }
    }



    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_productions, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder) holder).title.setText(buyerList.get(position).getName()+"");
        //((ViewHolder) holder).subtitle.setText(buyerList.get(position).getName()+":");
        //((ViewHolder) holder).subtitle;
        DataPoint[] points;
        points = new DataPoint[15];

        for (int i = 0; i < 15; i++) {
            points[i] = new DataPoint(i, Math.sin(i*0.2) * buyerList.get(position).getAvarage()*(1*(buyerList.get(position).getPrice()/2)+1));
        }
        LineGraphSeries<DataPoint> series2 = new LineGraphSeries<>(points);

        ((ViewHolder) holder).graph.addSeries(series2);

        ((ViewHolder) holder).graph.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter() {
            @Override
            public String formatLabel(double value, boolean isValueX) {
                if (isValueX) {
                    // show normal x values
                    return super.formatLabel(value, isValueX)+"hA ";
                } else {
                    // show currency for y values
                    return super.formatLabel(value, isValueX) + "$";
                }
            }
        });
        //SimpleLineGraph sl= new SimpleLineGraph();
        //sl.initGraph(((ViewHolder) holder).graph);
    }

    @Override
    public int getItemCount() {
        return buyerList.size();
    }


    @Override
    public long getItemId(int i) {
        return i;
    }


}
