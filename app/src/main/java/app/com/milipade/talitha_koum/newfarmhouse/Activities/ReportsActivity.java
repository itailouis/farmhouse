package app.com.milipade.talitha_koum.newfarmhouse.Activities;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import app.com.milipade.talitha_koum.newfarmhouse.Activities.Base.BaseAuthenticatedActivity;
import app.com.milipade.talitha_koum.newfarmhouse.R;
import app.com.milipade.talitha_koum.newfarmhouse.Utils.CityPreference;
import app.com.milipade.talitha_koum.newfarmhouse.Views.MainNavDrawer;


public class ReportsActivity extends BaseAuthenticatedActivity
         {

    @Override
    protected void onSocialCreate(Bundle savedInstanceState) {
        //super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setNavDrawer(new MainNavDrawer(this));
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    private void showInputDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Change city");
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);
        builder.setPositiveButton("Go", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //changeCity(input.getText().toString());
                new CityPreference(ReportsActivity.this).setCity(input.getText().toString());
            }
        });
        builder.show();
    }
}
