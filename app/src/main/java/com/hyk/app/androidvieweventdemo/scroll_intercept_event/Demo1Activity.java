package com.hyk.app.androidvieweventdemo.scroll_intercept_event;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.hyk.app.androidvieweventdemo.R;
import com.hyk.app.androidvieweventdemo.utils.MyUtils;

import java.util.ArrayList;

public class Demo1Activity extends AppCompatActivity {
    private static final String TAG = "Demo1Activity";
    private HorizontalScrollViewEx mListContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo1);

        initView();
    }

    private void initView() {
        LayoutInflater inflater = getLayoutInflater();
        mListContainer = findViewById(R.id.container);

        final int screenWidth = MyUtils.getScreenMetrics(this).widthPixels;
        final int screenHeight = MyUtils.getScreenMetrics(this).heightPixels;
        for (int i = 0; i < 3; i++) {
            ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.content_layout, mListContainer, false);

            layout.getLayoutParams().width = screenWidth;
            TextView textView = layout.findViewById(R.id.title);
            textView.setText("page" + (i + 1));
            layout.setBackgroundColor(Color.rgb(255 / (i + 1), 255 / (i + 1), 0));
            createList(layout);
            mListContainer.addView(layout);
        }
    }

    private void createList(ViewGroup layout) {
        ListView listView = layout.findViewById(R.id.list);
        ArrayList<String> datas = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            datas.add("name" + i);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                R.layout.content_list_item, R.id.name, datas);
        listView.setAdapter(adapter);
    }
}
