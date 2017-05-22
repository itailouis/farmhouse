package app.com.milipade.talitha_koum.newfarmhouse.Activities;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import app.com.milipade.talitha_koum.newfarmhouse.Activities.Base.BaseAuthenticatedActivity;
import app.com.milipade.talitha_koum.newfarmhouse.Fragments.WeatherFragment;
import app.com.milipade.talitha_koum.newfarmhouse.R;
import app.com.milipade.talitha_koum.newfarmhouse.Utils.IPPreference;
import app.com.milipade.talitha_koum.newfarmhouse.Views.MainNavDrawer;


/**
 * Created by SAMAR on 2/18/2016.
 */


public class MainActivity extends BaseAuthenticatedActivity implements View.OnClickListener {
    private static final int REQUEST_SHOW_MESSAGE = 1;


    @Override
    protected void onSocialCreate(Bundle savedState) {
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Home");
        setNavDrawer(new MainNavDrawer(this));
        showInputDialog();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.weatherapp, new WeatherFragment())
                .commit();
        //findViewById(R.id.activity_main_newMessageButton).setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {


    }

    @Override
    public void onRefresh() {
        //swipeRefresh.setRefreshing(true);
       // bus.post(new Messages.SearchMessagesRequest(false, true));
        //bus.post(new Contacts.GetContactRequestsRequest(false));
    }


    private void showInputDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Change IP Address");

        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);
        builder.setPositiveButton("Go", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                changeCity(input.getText().toString());
            }
        });
        builder.show();
    }
    public void changeCity(String city){
        Log.i("city",city);
        new IPPreference(this).setCity(city);
    }
}
