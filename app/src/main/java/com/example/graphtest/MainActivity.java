package com.example.graphtest;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button addPoint;
    private EditText editX,editY;
    private LineGraphSeries<DataPoint> series;
    private GraphView graph;
    private DataPoint[] dataPoints;
    private Random mRand = new Random();
    private static double lastX = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addPoint = (Button)  findViewById(R.id.startProcess);
        //editX = (EditText) findViewById(R.id.editX);
        //editY = (EditText) findViewById(R.id.editY);
        graph = (GraphView) findViewById(R.id.graph);
        //dataPoints = generateData();
        //series = new LineGraphSeries<DataPoint>(dataPoints);
        series = new LineGraphSeries<DataPoint>();

        series.setColor(Color.RED);
        series.setTitle("sin(x)");



        graph.addSeries(series);

        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setScrollable(true);
        graph.getViewport().setScrollableY(true);
        graph.getViewport().setScalableY(true);
        graph.getViewport().setScalable(true);

        addPoint.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.open_settings:

                Intent intent = new Intent(this, PrefActivity.class);
                startActivity(intent);
                return true;


        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

        for (int i= 0; i<100; i++) {
            series.appendData(generateDataPoint(), true, 100);

            //series.
        }


//
//
//                            series.appendData(generateDataPoint(),true,100);
//
//

//        DataPoint[] dataPoints2 = generateData();
//        BarGraphSeries<DataPoint> series2 = new BarGraphSeries<>(dataPoints2);
//        graph.addSeries(series2);
//        series2.setColor(Color.GREEN);

        /*DataPoint tmpPoint = new DataPoint(Double.parseDouble(editX.getText().toString()),Double.parseDouble(editY.getText().toString()));
        series.appendData(tmpPoint,true,60,true);
        graph.onDataChanged(false,false);*/

    }
    private DataPoint[] generateData() {
        int count = 100;
        double x = -5;
        DataPoint[] values = new DataPoint[count];
        for (int i=0; i<count; i++) {
            x = x + 0.1;
            double f = mRand.nextDouble()*0.15+0.3;
            double y = Math.sin(i*f+2) + mRand.nextDouble()*0.3;
            //double y = Math.sin(x);
            DataPoint v = new DataPoint(x, y);
            values[i] = v;
        }
        return values;
    }

    private DataPoint generateDataPoint() {
        double f = mRand.nextDouble() * 0.15 + 0.3;
        double y = Math.sin(lastX * f + 2) + mRand.nextDouble() * 0.3;

        DataPoint value = new DataPoint(lastX, y);
        lastX = lastX + 0.1;
        return value;
    }
}
