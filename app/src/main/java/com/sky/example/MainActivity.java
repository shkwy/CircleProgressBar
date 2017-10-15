package com.sky.example;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.sky.library.BubbleProgressBar;
import com.sky.example.test.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static boolean NEED_BACKGROUND = false;
    private static boolean NEED_DIFFERENT_COLOR = false;
    private String[] colors = {"#DC143C", "#FF8C00", "#FFFF00", "#00FF00", "#00FFFF", "#0000FF", "#800080", "#00BFFF"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final BubbleProgressBar bar = (BubbleProgressBar) findViewById(R.id.bar);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        List<String> lists = new ArrayList<>();
        lists.add("原始样式");
        lists.add("设置单色变色");
        lists.add("设置背景开关");
        lists.add("设置彩色开关");
        lists.add("自定义彩色");
        lists.add("停止");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        MyAdapter adapter = new MyAdapter(this, lists);
        recyclerView.setAdapter(adapter);
        adapter.setListener(new MyAdapter.ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                switch (position) {
                    case 0:
                        bar.start(MainActivity.this);
                        break;

                    case 1:
                        bar.setBollColor(Color.YELLOW);
                        break;

                    case 2:
                        if (NEED_BACKGROUND) {
                            bar.needBackground(false);
                            NEED_BACKGROUND = false;
                        } else {
                            bar.needBackground(true);
                            NEED_BACKGROUND = true;
                        }
                        break;

                    case 3:
                        if (NEED_DIFFERENT_COLOR) {
                            bar.needDifferentColor(false);
                            NEED_DIFFERENT_COLOR = false;
                        } else {
                            bar.needDifferentColor(true);
                            NEED_DIFFERENT_COLOR = true;
                        }
                        break;

                    case 4:
                        bar.setDifferentColors(colors);
                        break;

                    case 5:
                        bar.stop();
                        break;

                    default:
                        break;
                }
            }
        });
    }
}
