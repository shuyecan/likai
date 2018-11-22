package sy.yuanlixiaoduzi.com;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.transition.Slide;
import android.view.MenuItem;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ItemTongji extends AppCompatActivity {

    private MaterialCalendarView calendarView;
    private PieChart mPieChart;
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setEnterTransition(new Slide());
        getWindow().setExitTransition(new Slide());
        setContentView(R.layout.activity_tongji);
        initView();
    }

    private void initView() {
        Toolbar toolbar = findViewById(R.id.activity_item_tongji);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        mPieChart = findViewById(R.id.mypiechart);
        calendarView = findViewById(R.id.calendarView);
        mPieChart.setUsePercentValues(true);
        getPiedata();
        calendarView.setSelectedDate(Calendar.getInstance().getTime());
    }

    public void getPiedata() {
        List<PieEntry> strings = new ArrayList<>();
        strings.add(new PieEntry(5f,"5天"));
        strings.add(new PieEntry(25f,"25天"));
        PieDataSet dataSet = new PieDataSet(strings,"总天数为30天");
        ArrayList<Integer> colors = new ArrayList<Integer>();
        colors.add(getResources().getColor(R.color.red));
        colors.add(getResources().getColor(R.color.blue));
        dataSet.setColors(colors);

        PieData pieData = new PieData(dataSet);
        pieData.setDrawValues(true);
        pieData.setValueFormatter(new PercentFormatter());
        pieData.setValueTextSize(12f);
        mPieChart.setData(pieData);
        mPieChart.invalidate();
        Description description = new Description();
        description.setText("");
        mPieChart.setDescription(description);
        mPieChart.setHoleRadius(0f);
        mPieChart.setTransparentCircleRadius(0f);
    }
}
