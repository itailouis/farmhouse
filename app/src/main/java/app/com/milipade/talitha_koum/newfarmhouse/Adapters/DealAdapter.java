package app.com.milipade.talitha_koum.newfarmhouse.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import app.com.milipade.talitha_koum.newfarmhouse.Activities.DealActivity;
import app.com.milipade.talitha_koum.newfarmhouse.Models.Buyer;
import app.com.milipade.talitha_koum.newfarmhouse.Models.Notification;
import app.com.milipade.talitha_koum.newfarmhouse.R;


/**
 * Created by TALITHA_KOUM on 19/11/2016.
 */
public class DealAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final static String TAG=  DealAdapter.class.getName();
    private Context context;
    private List<Buyer> buyerList;
    private LayoutInflater layoutInflater;

    public DealAdapter(Context context, List<Buyer> buyerList) {
        this.context = context;
        this.buyerList = buyerList;
        layoutInflater = LayoutInflater.from(context);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView overflow;
        TextView buyerSeaerName;
        TextView cropName;
        TextView quantity;
        TextView timeStamp;
        ImageView profile;
        public ViewHolder(View view) {
            super(view);
            overflow = (ImageView) view.findViewById(R.id.overflowDeal);
            buyerSeaerName = (TextView) view.findViewById(R.id.buyer_seler_tv);
            cropName = (TextView) view.findViewById(R.id.cropNameDeal);
            quantity = (TextView) view.findViewById(R.id.quantityDeal);
            profile = (ImageView) view.findViewById(R.id.profileDeal);
            timeStamp = (TextView) view.findViewById(R.id.timestampDeal);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition(); // gets item position
            if (position != RecyclerView.NO_POSITION) { // Check if an item was deleted, but the user clicked it before the UI removed it
                  Buyer buyer = buyerList.get(position);
                // We can access the data within the views
                Intent b = new Intent(context,DealActivity.class);
                b.putExtra("buyer",buyer);
                context.startActivity(b);
            }

        }
    }



    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.deal_item, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder) holder).buyerSeaerName.setText(buyerList.get(position).getBuyerName());
        //((ViewHolder) holder).cropName.setText(buyerList.get(position).getCrop().getName());
        ((ViewHolder) holder).timeStamp.setText(buyerList.get(position).getBrefMassage());
        ((ViewHolder) holder).quantity.setText(buyerList.get(position).getQuantity()+"per tone");
        ((ViewHolder) holder).overflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupMenu(v,((ViewHolder) holder));
            }
        });
        /*listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent= new Intent(getContext(),NewsActivity.class);
                //intent.putExtra("title",);
                startActivity(intent);
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return buyerList.size();
    }




    @Override
    public long getItemId(int i) {
        return i;
    }


    private void showPopupMenu(View view,ViewHolder holder) {
        // inflate menu
        PopupMenu popup = new PopupMenu(context, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_buyer_item, popup.getMenu());
        popup.setOnMenuItemClickListener(new MyMenuItemClickListener(holder));
        popup.show();
    }
    void remove(Notification object){
        //messageArrayList.remove(object);
        notifyDataSetChanged();

    }
    /**
     * Click listener for popup menu items
     */
    class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {
        ViewHolder holder;
        //View view;
        public MyMenuItemClickListener(ViewHolder holder) {
            this.holder=  holder;
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.buyer_menu_item_delete:
                    Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();
                    //remove(getCurrent());
                    Snackbar bar =Snackbar.make(holder.itemView, "Item Deleted", Snackbar.LENGTH_LONG);
                    bar.setAction("UNDO", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //messageArrayList.add(getCurrent());
                            notifyDataSetChanged();
                        }
                    });
                    bar.show();
                    return true;
                case R.id.buyer_menu_item_more:
                    Snackbar bar1 =Snackbar.make(holder.itemView, "Item Achieved ", Snackbar.LENGTH_LONG);
                    bar1.setAction("UNDO", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    });
                    View layout = bar1.getView();
                    layout.setBackgroundColor(Color.BLUE);
                    bar1.setActionTextColor(Color.YELLOW);
                    TextView tv = (TextView) layout.findViewById(android.support.design.R.id.snackbar_text);
                    tv.setTextColor(Color.WHITE);
                    //Button btn = (Button) layout.findViewById(android.support.design.R.id.snackbar_action);
                    //btn.setTextColor(Color.WHITE);

                    bar1.show();

                    return true;
                default:
            }
            return false;
        }
    }
}
