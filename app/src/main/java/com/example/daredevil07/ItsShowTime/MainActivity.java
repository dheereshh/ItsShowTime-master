package com.example.daredevil07.ItsShowTime;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements FilmIDCallback, FilmAvailable {
    private static final String TAG = "MainActivity";
    private ArrayList<Integer> allID;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private ArrayList<Film> films;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        com.example.daredevil07.ItsShowTime.NowPlayingFilmID nowPlayingFilmID = new com.example.daredevil07.ItsShowTime.NowPlayingFilmID(this);

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        films = new ArrayList<>();

        mAdapter = new FilmAdapter(films, this);
        mRecyclerView.setAdapter(mAdapter);





    }

    private void fetchFilm(String url) {

        FilmTask task = new FilmTask(this);
        String[] urls = new String[] {url};
        task.execute(urls);

    }

    @Override
    public void filmIDCallback(ArrayList<Integer> moviesID) {
        Log.d(TAG, "filmIDCallback: all movies ids" + moviesID);
        allID = moviesID;

        for(Integer id: allID){
            String url = "https://api.themoviedb.org/3/movie/" + id + "?api_key=9356cb1f42f053e63a72c6bf6ca12171&append_" +
                    "to_response=credits,images,keywords,lists,releases,reviews,videos";

            fetchFilm(url);
        }
    }

    @Override
    public void filmAvailable(Film film) {
        films.add(film);
        mAdapter.notifyDataSetChanged();
    }


}

