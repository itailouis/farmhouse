package app.com.milipade.talitha_koum.newfarmhouse.Views;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.otto.Subscribe;
import com.squareup.picasso.Picasso;

import app.com.milipade.talitha_koum.newfarmhouse.Activities.Base.BaseActivity;
import app.com.milipade.talitha_koum.newfarmhouse.Activities.MainActivity;
import app.com.milipade.talitha_koum.newfarmhouse.Activities.NotificationActivity;
import app.com.milipade.talitha_koum.newfarmhouse.Activities.SponserActivity;
import app.com.milipade.talitha_koum.newfarmhouse.Activities.SuitableCropActivity;
import app.com.milipade.talitha_koum.newfarmhouse.Core.User;
import app.com.milipade.talitha_koum.newfarmhouse.R;
import app.com.milipade.talitha_koum.newfarmhouse.Services.Account;


public class MainNavDrawer extends NavDrawer {

    private final TextView displayNameText;
    private final ImageView avatarImage;


    public MainNavDrawer(final BaseActivity activity) {
        super(activity);

        addItem(new ActivityNavDrawerItem(MainActivity.class, "Home", null, R.drawable.ic_home_white, R.id.include_main_nav_drawer_topItems));
        //addItem(new ActivityNavDrawerItem(CropActivity.class, "Crop Info", null, R.drawable.ic_spa_white_18dp, R.id.include_main_nav_drawer_topItems));
        addItem(new ActivityNavDrawerItem(SuitableCropActivity.class, "Get Suiable Grops", null, R.drawable.ic_graphic_eq_white, R.id.include_main_nav_drawer_topItems));
        //addItem(new ActivityNavDrawerItem(NewsActivity.class, "News", null, R.drawable.ic_feedback_white_18pt, R.id.include_main_nav_drawer_topItems));
        addItem(new ActivityNavDrawerItem(NotificationActivity.class, "Notifications", null, R.drawable.ic_notifications_none_white_18dp, R.id.include_main_nav_drawer_topItems));

        addItem(new ActivityNavDrawerItem(SponserActivity.class, "Buyers", null, R.drawable.ic_store_white_18dp, R.id.include_main_nav_drawer_topItems));
       /// addItem(new ActivityNavDrawerItem(ReportsActivity.class, "Reports", null, R.drawable.ic_graphic_eq_white, R.id.include_main_nav_drawer_topItems));

        addItem(new BasicNavDrawerItem("Logout", null, R.drawable.ic_exit_to_app_white, R.id.include_main_nav_drawer_bottomItems) {
            @Override
            public void onClick(View v) {
                super.onClick(v);
                activity.getMyApplication().getAuth().logout();

            }
        });


        displayNameText = (TextView) navDrawerView.findViewById(R.id.include_main_nav_drawer_displayName);
        avatarImage = (ImageView) navDrawerView.findViewById(R.id.include_main_nav_drawer_avatar);

        User loggedInUser = activity.getMyApplication().getAuth().getUser();
        displayNameText.setText(loggedInUser.getDisplayName());

        Picasso.with(activity).load(loggedInUser.getAvatarUrl()).into(avatarImage);

    }

    @Subscribe
    public void userDetialChangeEvent(Account.UserDetailUpdatesEvent event) {
        Picasso.with(activity).load(event.user.getAvatarUrl()).into(avatarImage);
        displayNameText.setText(event.user.getDisplayName());
    }


}
