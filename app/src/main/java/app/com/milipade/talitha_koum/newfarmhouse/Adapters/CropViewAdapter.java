package app.com.milipade.talitha_koum.newfarmhouse.Adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import app.com.milipade.talitha_koum.newfarmhouse.Models.CropProduce;
import app.com.milipade.talitha_koum.newfarmhouse.R;


/**
 * Created by Ravi Tamada on 18/05/16.
 */
public class CropViewAdapter extends RecyclerView.Adapter<CropViewAdapter.MyViewHolder> {

    private Context mContext;
    private List<CropProduce> albumList;
    SparseBooleanArray selected;
    private FragmentManager fragManager;

    public void setFragManager(FragmentManager fragManager) {
        this.fragManager = fragManager;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView title, count;
        public ImageView thumbnail, overflow;

        public MyViewHolder(View view,Context mContext) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            count = (TextView) view.findViewById(R.id.count);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
            overflow = (ImageView) view.findViewById(R.id.overflow);
            view.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            LayoutInflater li = LayoutInflater.from(mContext);
            View promptsView = li.inflate(R.layout.crop_info_dialog, null);

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mContext);

            // set prompts.xml to alertdialog builder
            alertDialogBuilder.setView(promptsView);

            final TextView emailInput = (TextView) promptsView.findViewById(R.id.crop_info_name);

            // set dialog message
            alertDialogBuilder
                    .setCancelable(false)
                    .setPositiveButton("ADD",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    //Snackbar.make(v,"crop item",Snackbar.LENGTH_LONG).show();
                                }
                            })
                    .setNegativeButton("Cancel",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int id) {
                                    dialog.cancel();
                                }
                            });

            // create alert dialog
            AlertDialog alertDialog = alertDialogBuilder.create();

            // show it
            alertDialog.show();

        }

    }


    public CropViewAdapter(Context mContext, List<CropProduce> albumList) {
        this.mContext = mContext;
        this.albumList = albumList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.crop_view_card, parent, false);

        return new MyViewHolder(itemView,mContext);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        CropProduce album = albumList.get(position);
        holder.title.setText(album.getName());
        holder.count.setText("Output/HA :"+album.getOutput() + " \n"+"Price/HA :"+album.getPrice());
        holder.thumbnail.setImageResource(album.getPic());

        // loading album cover using Glide library
       Picasso.with(mContext).load(album.getAvatearImage()).into(holder.thumbnail);

        holder.overflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(holder.overflow);
            }
        });
    }

    /**
     * Showing popup menu when tapping on 3 dots
     */
    private void showPopupMenu(View view) {
        // inflate menu
        PopupMenu popup = new PopupMenu(mContext, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_market_item, popup.getMenu());
        popup.setOnMenuItemClickListener(new MyMenuItemClickListener());
        popup.show();
    }

    /**
     * Click listener for popup menu items
     */
    class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

        public MyMenuItemClickListener() {
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.action_view_graph:
                   LayoutInflater li = LayoutInflater.from(mContext);
                    View promptsView = li.inflate(R.layout.crop_info_dialog,null);
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mContext);
                    // set prompts.xml to alertdialog builder
                    alertDialogBuilder.setView(promptsView);
                    /*
                    //final EditText emailInput = (EditText) promptsView.findViewById(R.id.editTextDialogUserInput);
                    // set dialog message
                    alertDialogBuilder
                            .setCancelable(false)
                            .setPositiveButton("OK",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog,
                                                            int id) {
                                        }
                                    })
                            .setNegativeButton("Cancel",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog,
                                                            int id) {
                                            dialog.cancel();
                                        }
                                    });

                    // create alert dialog
                    AlertDialog alertDialog = alertDialogBuilder.create();

                    // show it
                    alertDialog.show();*/
                    Toast.makeText(mContext, "Add to favourite", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.action_view_more:
                    Toast.makeText(mContext, "Play next", Toast.LENGTH_SHORT).show();
                    return true;
                default:
            }
            return false;
        }
    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }

    public void selecteView(int position, boolean value){
        if(value){
            selected.put(position,value);
        }else{
            selected.delete(position);
        }
        notifyDataSetChanged();
    }
public List<CropProduce> getListCrop(){
    return albumList;
}
    public void removeSelection(){
        selected = new SparseBooleanArray();
        notifyDataSetChanged();
    }
    public  void TaggleSelection(int pos){
        selecteView(pos,!selected.get(pos));
    }
    public  int getSelectedCount(){
        return selected.size();
    }
    public  SparseBooleanArray getSelectedIds(){
        return selected;
    }
}
