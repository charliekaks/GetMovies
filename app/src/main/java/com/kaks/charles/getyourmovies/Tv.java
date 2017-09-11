package com.kaks.charles.getyourmovies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;

public class Tv extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv);

        final RecyclerView mTvSeries = (RecyclerView) findViewById(R.id.tv_series);

        final LinearLayoutManager tvSeries = new LinearLayoutManager(this);
        mTvSeries.setLayoutManager(tvSeries);
        List<String> mTitles = new ArrayList<>(Arrays.asList("the Jungle Book", "The Happening","house","narcos","sops","Arrow","Game Of Thrones"));
         Integer[] mpics = {R.drawable.jungle,R.drawable.cut,R.drawable.house,R.drawable.narcos,R.drawable.sops,R.drawable.rsz_arrow,R.drawable.got};
        seriesAdapter newSeriesAdapter = new seriesAdapter(this,mTitles,mpics);
        mTvSeries.setAdapter(newSeriesAdapter);
    }
}
