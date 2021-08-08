package tp.edu.sg.p14musicstream;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PlaySongActivity extends AppCompatActivity {
    private String title = "";
    private String artiste = "";
    private String fileLink = "";
    private int drawable;
    private int currentIndex = -1;
    private MediaPlayer player = new MediaPlayer();
    private Button btnPlayPause = null;
    private SongCollection mySongCollection = new SongCollection();
    SeekBar seekbar;
    Handler handler = new Handler();
    SongCollection originalSongCollection = new SongCollection();

    Button repeatBtn;

    List<Song> shuffleList = Arrays.asList(mySongCollection.allSongs);


    Button shuffleBtn;
    Boolean shuffleFlag = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_song);
        Bundle songData = this.getIntent().getExtras();
        currentIndex = songData.getInt("index");
        btnPlayPause = (Button)findViewById(R.id.btnPlayPause);
        displaySongBasedOnIndex(currentIndex);
        playSong(fileLink);
        seekbar = findViewById(R.id.seekBar);
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if (player != null && player.isPlaying()) {
                    player.seekTo(seekBar.getProgress());
                }
            }
        });

        shuffleBtn = findViewById(R.id.shuffleBtn);

    }


    Boolean repeatFlag = false;

    Runnable p_bar = new Runnable() {
        @Override
        public void run() {
            seekbar.setProgress(player.getCurrentPosition());
            handler.postDelayed(this, 1000);
        }
    };





    //method to display song information
    public void displaySongBasedOnIndex(int selectedIndex){
        Song selectedSong = mySongCollection.getCurrentSong(selectedIndex);
        title = selectedSong.getTitle();
        artiste = selectedSong.getArtiste();
        fileLink = selectedSong.getFileLink();
        drawable = selectedSong.getDrawable();
        TextView txtTitle = findViewById(R.id.txtSongTile);
        txtTitle.setText(title);
        TextView txtArtiste = findViewById(R.id.txtArtiste);
        txtArtiste.setText(artiste);
        ImageView iCoverArt = findViewById(R.id.imgCoverArt);
        iCoverArt.setImageResource(drawable);
        
    }
    //create method to play song
    public void playSong(String songURL){
        //try to play music, if it fails, we will catch the error and display the error msg
        try{
            player.reset();
            player.setDataSource(songURL);
            player.prepare();
            player.start();
            stopWhenMusicEnds();
            btnPlayPause.setText("PAUSE");
            setTitle(title);//set the title of the page to display the song title
        }
        catch(IOException e){
            e.printStackTrace();//print the error message
        }
    }
    //create a method that will be triggered when the play button is pressed
    public void playOrPauseMusic(View myView){
        if(player.isPlaying()){ //if the player is playing the song
            player.pause();
            btnPlayPause.setText("PLAY");
            seekbar.setMax(player.getDuration());
            handler.removeCallbacks(p_bar);
            handler.postDelayed(p_bar, 1000);
        }
        else{
            player.start();
            btnPlayPause.setText("PAUSE");
        }


    }
        @Override
        public void onBackPressed(){
            super.onBackPressed();
            if (player != null){
                handler.removeCallbacks(p_bar);
                player.stop();
                player.release();
                player = null;
            }
        }
        //create method to detect when the song has finished playing]
        private void stopWhenMusicEnds(){
            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    Toast.makeText(getBaseContext(), "The song has finished playing", Toast.LENGTH_LONG).show();
                    btnPlayPause.setText("PLAY");
                }
            });
        }
        //create method for the next button
    public void playNext(View myView){
        currentIndex = mySongCollection.getNextSong(currentIndex);
        displaySongBasedOnIndex(currentIndex);
        playSong(fileLink);
    }
    //current method for the prev button
    public void playPrev(View myView){
        currentIndex = mySongCollection.getPrevSong(currentIndex);
        displaySongBasedOnIndex(currentIndex);
        playSong(fileLink);
    }

    public void shuffleSong(View view) {
        if (shuffleFlag) {
            shuffleBtn.setBackgroundResource(R.drawable.shuffle_off);
            mySongCollection = new SongCollection();
            Log.d("SHUFFLE", "OFF");
        }else
        {
            Log.d("SHUFFLE", "ON");
            shuffleBtn.setBackgroundResource(R.drawable.shuffle);
            Collections.shuffle(shuffleList);
            shuffleList.toArray(mySongCollection.allSongs);

        }
        shuffleFlag = !shuffleFlag;
    }

   



}
