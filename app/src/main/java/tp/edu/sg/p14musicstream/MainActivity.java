package tp.edu.sg.p14musicstream;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    SongCollection mySongCollection = new SongCollection();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    //myView represents the item that has been clicked on
    public void handleSelection(View myView){
        String resourceId = getResources().getResourceEntryName(myView.getId());
        int currentArrayIndex = mySongCollection.searchSongById(resourceId);
        Log.d("COMT", "The id of the clicked item is " + resourceId);
        sendDataToActivity(currentArrayIndex);
    }
    public void sendDataToActivity(int index){
        Intent nextPage = new Intent(this, PlaySongActivity.class);
        nextPage.putExtra("index", index);
        startActivity(nextPage);
    }
}