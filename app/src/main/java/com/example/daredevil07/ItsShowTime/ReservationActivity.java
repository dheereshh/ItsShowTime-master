package com.example.daredevil07.ItsShowTime;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;


public class ReservationActivity extends AppCompatActivity{

    private static final String TAG = "ReservationActivity";
    private FilmDatabase filmDatabase;
    private ArrayList<com.example.daredevil07.ItsShowTime.Show> showArrayList;
    private com.example.daredevil07.ItsShowTime.Show show;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);

        getIntent().getSerializableExtra("MyFilmTitle");
        final Film film = (Film) getIntent().getSerializableExtra("MyFilmTitle");
        System.out.println(film.getTitle());

        ImageView image = findViewById(R.id.backdropImg);
        Picasso.with(this).load(film.getBackDropPath()).into(image);

        TextView filmTitle = findViewById(R.id.titleFilmReservation);
        filmTitle.setText(film.getTitle());

        filmDatabase = new FilmDatabase(this);

        filmDatabase.insertDataToShow(film.getTitle(), "10:45", "07-04-18");
        filmDatabase.insertDataToShow(film.getTitle(), "13:45", "12-04-18");
        filmDatabase.insertDataToShow(film.getTitle(), "21:50", "18-04-18");

        showArrayList = filmDatabase.getShows(film.getTitle());


        for(com.example.daredevil07.ItsShowTime.Show show: showArrayList){
            System.out.println(show.getDate());
        }


        String buttonDate = "Date: " + showArrayList.get(0).getDate() + " Time: " + showArrayList.get(0).getTime();
        Button button = findViewById(R.id.showDate0);
        final int showId = showArrayList.get(0).getShow_id();
        button.setText(buttonDate);

        String buttonDate1 = "Date: " + showArrayList.get(1).getDate() + " Time: " + showArrayList.get(1).getTime();
        Button button1 = findViewById(R.id.showDate1);
        final int showId1 = showArrayList.get(1).getShow_id();
        button1.setText(buttonDate1);

        String buttonDate2 = "Date: " + showArrayList.get(2).getDate() + " Time: " + showArrayList.get(2).getTime();
        Button button2 = findViewById(R.id.showDate2);
        final int showId2 = showArrayList.get(2).getShow_id();
        button2.setText(buttonDate2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent itemDetailIntent = new Intent(view.getContext().getApplicationContext(), com.example.daredevil07.ItsShowTime.SeatSelectionActivity.class);

                itemDetailIntent.putExtra("MyFilmTitle", film);
                itemDetailIntent.putExtra("ShowID", showId);


                Log.d(TAG, "onClick: passing show id to intent" + showArrayList.get(0).getShow_id());

                view.getContext().startActivity(itemDetailIntent);
            }


        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent itemDetailIntent = new Intent(view.getContext().getApplicationContext(), com.example.daredevil07.ItsShowTime.SeatSelectionActivity.class);

                itemDetailIntent.putExtra("MyFilmTitle", film);
                itemDetailIntent.putExtra("ShowID", showId1);


                Log.d(TAG, "onClick: passing show id to intent" + showArrayList.get(1).getShow_id());

                view.getContext().startActivity(itemDetailIntent);
            }


        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent itemDetailIntent = new Intent(view.getContext().getApplicationContext(), com.example.daredevil07.ItsShowTime.SeatSelectionActivity.class);

                itemDetailIntent.putExtra("MyFilmTitle", film);
                itemDetailIntent.putExtra("ShowID", showId2);


                Log.d(TAG, "onClick: passing show id to intent" + showArrayList.get(2).getShow_id());

                view.getContext().startActivity(itemDetailIntent);
            }


        });


    }

}
