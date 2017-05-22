package app.com.milipade.talitha_koum.newfarmhouse.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import app.com.milipade.talitha_koum.newfarmhouse.Activities.Base.BaseAuthenticatedActivity;
import app.com.milipade.talitha_koum.newfarmhouse.R;
import app.com.milipade.talitha_koum.newfarmhouse.Views.MainNavDrawer;


public class SuitableCropActivity extends BaseAuthenticatedActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    @Override
    public void onClick(View v) {

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String name = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private Spinner spinner,spinner2;
    List<String> list;
    ArrayAdapter<String> adapter,adapter2;
    String district="";
    String season="";
    @Override
    protected void onSocialCreate(Bundle savedInstanceState) {
        //super.onCreate(savedInstanceState);
        setContentView(R.layout.suitablecrop);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setNavDrawer(new MainNavDrawer(this));
        spinner = (Spinner) findViewById(R.id.spinner3);
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        loadSpinner();
        loadSpinner3();
        Button fab = (Button) findViewById(R.id.get_grops);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                      //  .setAction("Action", null).show();
                Intent intent = new Intent(SuitableCropActivity.this,ShowActivity.class);
                //intent.putParcelableArrayListExtra("data", data);
                intent.putExtra("season", season);
                intent.putExtra("region", district);
                startActivity(intent);

            }
        });


    }
    private void loadSpinner() {
        String[] season = {"WINTER","SUMMER"};
        Set<String> set = new HashSet<String>();
        for(int i =0;i<season.length;i++) {
               set.add(season[i]);

        }
        List<String> list = new ArrayList<String>(set);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
        spinner.setWillNotDraw(false);



    }
    private void loadSpinner3() {
        String[] season = {"REGION 1","REGION 2","REGION 3","REGION 4"};
        Set<String> set = new HashSet<String>();
        for(int i =0;i<season.length;i++) {
            set.add(season[i]);

        }
        List<String> list = new ArrayList<String>(set);
        adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner2.setAdapter(adapter2);
        spinner2.setWillNotDraw(false);



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


}
