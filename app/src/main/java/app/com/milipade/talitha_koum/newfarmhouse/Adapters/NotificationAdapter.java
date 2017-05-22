package app.com.milipade.talitha_koum.newfarmhouse.Adapters;

import android.content.Context;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import app.com.milipade.talitha_koum.newfarmhouse.Models.Notification;
import app.com.milipade.talitha_koum.newfarmhouse.R;
import de.hdodenhof.circleimageview.CircleImageView;


public class NotificationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static String TAG = NotificationAdapter.class.getSimpleName();

    private static String today;

    private Context mContext;
    private ArrayList<Notification> messageArrayList;
    Notification current;

    public Notification getCurrent() {
        return current;
    }

    public void setCurrent(Notification current) {
        this.current = current;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView brif_message, timestamp,subject,sender;
        CircleImageView imageView;
        ImageView overflow;

        public ViewHolder(View view) {
            super(view);
            brif_message = (TextView) itemView.findViewById(R.id.brify_notice);
            timestamp = (TextView) itemView.findViewById(R.id.time_notify);
            subject = (TextView) itemView.findViewById(R.id.subject_notice);
            sender = (TextView) itemView.findViewById(R.id.sender_name);
            imageView = (CircleImageView) itemView.findViewById(R.id.ic_notification);
            overflow = (ImageView) itemView.findViewById(R.id.action_ic);
        }
    }


    public NotificationAdapter(Context mContext, ArrayList<Notification> messageArrayList) {
        this.mContext = mContext;
        this.messageArrayList = messageArrayList;

        Calendar calendar = Calendar.getInstance();
        today = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;

            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.notification_item, parent, false);

        return new ViewHolder(itemView);
    }


    @Override
    public int getItemViewType(int position) {

        return position;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        Notification message = messageArrayList.get(position);
        String timestamp = getTimeStamp(message.getCreatedAt());
        //if (message.getUser().getUname() != null)
        // timestamp = message.getUser().getUname() + ", " + timestamp;


        ((ViewHolder) holder).brif_message.setText(message.getBrif_message());
        //((ViewHolder) holder).sender.setText(message.getUser().getUsername());
        ((ViewHolder) holder).subject.setText(message.getSubject());
        ((ViewHolder) holder).timestamp.setText(timestamp);
        ((ViewHolder) holder).overflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(((ViewHolder) holder).overflow, ((ViewHolder) holder));
            }
        });
        // load image into imageview using glide
        setCurrent(message);


       /*Picasso.with(mContext).lod("http://192.168.1.7/test/images/" +message.getSubject() )
                .placeholder(R.drawable.ic_img_error)
                .error(R.drawable.ic_img_error)
                .into(myHolder.ivFish);*/
    }

    @Override
    public int getItemCount() {
        return messageArrayList.size();
    }

    public static String getTimeStamp(String dateStr) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timestamp = "";

        today = today.length() < 2 ? "0" + today : today;

        try {
            Date date = format.parse(dateStr);
            SimpleDateFormat todayFormat = new SimpleDateFormat("dd");
            String dateToday = todayFormat.format(date);
            format = dateToday.equals(today) ? new SimpleDateFormat("hh:mm a") : new SimpleDateFormat("dd LLL, hh:mm a");
            String date1 = format.format(date);
            timestamp = date1.toString();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return timestamp;
    }


    private void showPopupMenu(View view,ViewHolder holder) {
        // inflate menu
        PopupMenu popup = new PopupMenu(mContext, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_market_item, popup.getMenu());
        popup.setOnMenuItemClickListener(new MyMenuItemClickListener(holder));
        popup.show();
    }
    void remove(Notification object ){
      messageArrayList.remove(object);
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
                case R.id.action_view_more:
                    Toast.makeText(mContext, "Deleted", Toast.LENGTH_SHORT).show();
                    remove(getCurrent());
                    Snackbar bar = Snackbar.make(holder.itemView, "Item Deleted", Snackbar.LENGTH_LONG);
                            bar.setAction("UNDO", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                 messageArrayList.add(getCurrent());
                                    notifyDataSetChanged();
                                }
                            });
                    bar.show();
                    return true;
                case R.id.action_view_graph:
                    Snackbar bar1 = Snackbar.make(holder.itemView, "Item Achieved ", Snackbar.LENGTH_LONG);
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

