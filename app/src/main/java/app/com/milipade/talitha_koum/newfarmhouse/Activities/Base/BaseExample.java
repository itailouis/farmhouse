package app.com.milipade.talitha_koum.newfarmhouse.Activities.Base;

import android.app.Activity;

import com.jjoe64.graphview.GraphView;

/**
 * Created by jonas on 10.09.16.
 */
public abstract class BaseExample {
    public abstract void onCreate(Activity Activity);
    public abstract void initGraph(GraphView graph);

    public void onPause() {}
    public void onResume() {}
}
