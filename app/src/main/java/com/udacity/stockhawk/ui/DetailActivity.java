package com.udacity.stockhawk.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.udacity.stockhawk.R;
import com.udacity.stockhawk.data.Contract;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.line_chart)
    LineChart lineChart;


    ArrayList<Float> historicalQuotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        String symbolQuote = intent.getStringExtra(Contract.Quote.SymbolKey);
        String historyQuote = intent.getStringExtra(Contract.Quote.HistoryKey);
        String[] temp = historyQuote.split("\n");
        historicalQuotes = new ArrayList<Float>();
        List<Entry> stockValues = new ArrayList<Entry>();

        final String[] weeks = new String[temp.length];

        for (int i = 0; i < temp.length; i++) {
            StringBuilder sb = new StringBuilder();
            sb.append("week");
            sb.append(i);
            historicalQuotes.add(Float.parseFloat(temp[i]));
            weeks[i] =  sb.toString();
        }

        IAxisValueFormatter formatter = new IAxisValueFormatter() {

            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return weeks[(int) value];
            }


        };

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setValueFormatter(formatter);
        xAxis.setTextColor(Color.WHITE);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        YAxis yAxisL = lineChart.getAxisLeft();
        yAxisL.setTextColor(Color.WHITE);

        YAxis yAxisR = lineChart.getAxisRight();
        yAxisR.setTextColor(Color.WHITE);


        for (int i = 0; i < historicalQuotes.size(); i++) {
            Entry c = new Entry(i, historicalQuotes.get(i));
            stockValues.add(c);
        }


        LineDataSet lineDataSet = new LineDataSet(stockValues, "price");
        lineDataSet.setColors(Color.parseColor("#2E7D32"));
        lineDataSet.setAxisDependency(YAxis.AxisDependency.LEFT);

        LineData data = new LineData(lineDataSet);
        lineChart.setData(data);
        lineChart.setVisibleXRangeMaximum(8);
        data.setValueTextColor(Color.WHITE);
        data.setValueTextSize(10f);
        lineChart.invalidate();


    }
}
