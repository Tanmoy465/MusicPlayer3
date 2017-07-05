package com.epita.fr.musicplayer;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import java.io.File;
import java.util.ArrayList;

public class Player extends AppCompatActivity implements View.OnClickListener{

    MediaPlayer mp;
    ArrayList<File> mySongs;
    int position;
    Uri u;

    SeekBar sb;
    Button btPlay , btFF, btFB, btNxt, btPv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        btPlay = (Button) findViewById(R.id.btPlay);
        btFF = (Button) findViewById(R.id.btFF);
        btFB = (Button) findViewById(R.id.btFB);
        btNxt = (Button) findViewById(R.id.btNxt);
        btPv = (Button) findViewById(R.id.btPv);

        btPlay.setOnClickListener(this);
        btFF.setOnClickListener(this);
        btFB.setOnClickListener(this);
        btNxt.setOnClickListener(this);
        btPv.setOnClickListener(this);

        Intent i = getIntent();
        Bundle b = i.getExtras();
        mySongs = (ArrayList) b.getParcelableArrayList("songlist");
        position = b.getInt("pos" ,0);

         u = Uri.parse( mySongs.get(position).toString() );
        mp = MediaPlayer.create(getApplicationContext(),u);
        mp.start();


    }

    @Override
    public void onClick(View v) {
int id = v.getId();
        switch (id){
            case R.id.btPlay:
                if(mp.isPlaying()){
                    mp.pause();
                }
        else mp.start();
                break;
            case R.id.btFF:
                mp.seekTo(mp.getCurrentPosition()+5000);
                break;
            case R.id.btFB:
                mp.seekTo(mp.getCurrentPosition()-5000);
                break;
            case R.id.btNxt:
                mp.stop();
                mp.release();
              position =  (position+1)%mySongs.size();
                u = Uri.parse( mySongs.get((position+1)%mySongs.size()).toString() );
                mp = MediaPlayer.create(getApplicationContext(),u);
                mp.start();
                break;
            case R.id.btPv:
                mp.stop();
                mp.release();
                position = (position-1<0) ? mySongs.size()-1: position-1;
                /*if(position-1 < 0){
                    position = mySongs.size()-1;
                }
                else{
                    position = position-1;
                }*/
                u = Uri.parse( mySongs.get(position).toString() );
                mp = MediaPlayer.create(getApplicationContext(),u);
                mp.start();
                break;

        }
    }
}
