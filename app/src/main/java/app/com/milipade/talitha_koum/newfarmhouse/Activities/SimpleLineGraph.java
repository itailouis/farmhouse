package app.com.milipade.talitha_koum.newfarmhouse.Activities;

import android.app.Activity;

import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import app.com.milipade.talitha_koum.newfarmhouse.Activities.Base.BaseExample;
import app.com.milipade.talitha_koum.newfarmhouse.R;

/**
 * Created by jonas on 10.09.16.
 */
public class SimpleLineGraph extends BaseExample {
    @Override
    public void onCreate(Activity activity) {
        GraphView graph = (GraphView) activity.findViewById(R.id.graph);
        initGraph(graph);
    }

    @Override
    public void initGraph(GraphView graph) {
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(0, 1),
                new DataPoint(1, 1.8),
                new DataPoint(2, 3),
                new DataPoint(3, 2),
                new DataPoint(4, 2.9)
        });
        graph.addSeries(series);
        graph.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter() {
            @Override
            public String formatLabel(double value, boolean isValueX) {
                if (isValueX) {
                    // show normal x values
                    return super.formatLabel(value, isValueX)+"Land ";
                } else {
                    // show currency for y values
                    return super.formatLabel(value, isValueX) + " return(USD$)";
                }
            }
        });
    }
}
