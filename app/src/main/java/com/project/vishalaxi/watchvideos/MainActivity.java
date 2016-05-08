package com.project.vishalaxi.watchvideos;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //Create an ArrayList of Video
    private List<video> myVideos = new ArrayList<video>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        addVideosToList();// populate the videos
        populateListView(); // populate the listview
        registerListItemClick(); // setup on item click listener
    }

    /*Function to add the list of videos to the ArrayList of type video*/
    private void addVideosToList() {
        // add the videos with title, artist and the thumbnail ID to each of the items in myVideos ArrayList
        myVideos.add(new video("Up town Funk", "Bruno Mars", R.drawable.icon1));
        myVideos.add(new video("Roar", "Katy Perry", R.drawable.icon2));
        myVideos.add(new video("All of Me", "John Legend", R.drawable.icon3));
        myVideos.add(new video("What Do You Mean", "Justin Bieber", R.drawable.icon4));
        myVideos.add(new video("Papi", "Jennifer Lopez", R.drawable.icon5));
        myVideos.add(new video("Hips Don't Lie", "Shakira", R.drawable.icon6));
        myVideos.add(new video("Sorry", "Justin Bieber", R.drawable.icon7));
        myVideos.add(new video("Comfortably Numb", "Pink FLoyd", R.drawable.icon8));
        myVideos.add(new video("Lean On", " Major Lazer", R.drawable.icon9));
        myVideos.add(new video("Ain't Nobody", " Felix Jaehn", R.drawable.icon10));
        myVideos.add(new video("Style", "Taylor Swift", R.drawable.icon11));
        myVideos.add(new video("Dance of Death", "heavy metal", R.drawable.icon12));
        myVideos.add(new video("Lose Myself", "OneRepublic", R.drawable.icon13));
        myVideos.add(new video("November rain", "Guns N' Roses", R.drawable.icon14));
        myVideos.add(new video("Dont you worry Child", "Swedish House Mafia", R.drawable.icon15));

    }

    private void populateListView() {
        //Create the adapter to convert the array to views
        ArrayAdapter<video> adapter = new myListAdapter();
        //Obtain the listview defined in the layout file
        ListView list=(ListView)findViewById(R.id.videosListView);
        //Attach the adapter to the listview
        list.setAdapter(adapter);
        registerForContextMenu(list);
    }

    //Define a inner class that implements as array adapter
    private class myListAdapter extends ArrayAdapter<video>{

        public myListAdapter(){
            super(MainActivity.this, R.layout.item_view, myVideos);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // to ensure that we have a view to work with
            View item_view =convertView;
            // Check if existing view is reused
            if(item_view == null)
            {
                item_view=getLayoutInflater().inflate( R.layout.item_view,parent,false);
            }

            // find the video
            video currentVideo = myVideos.get(position);

            // fill the view
            ImageView imageView= (ImageView)item_view.findViewById(R.id.item_icon);
            imageView.setImageResource(currentVideo.getIconID());

            //Title
            TextView titleText = (TextView)item_view.findViewById(R.id.item_txtTitle);
            titleText.setText(currentVideo.getTitle());

            //Artist
            TextView artistText = (TextView)item_view.findViewById(R.id.item_txtArtist);
            artistText.setText(currentVideo.getArtist());

            //return the view to display on the screen
            return item_view;
        }

    }
    /*Function to register the click events of the ListView*/
    private void registerListItemClick() {
        ListView list =(ListView)findViewById(R.id.videosListView);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Get the URLs of the videos present in the list from the string array
                String[] videoURLs=getResources().getStringArray(R.array.videoURLs);
                //Define an intent to Launch the video in a browser by parsing the corresponding URLs
                Intent launchBrowser = new Intent(Intent.ACTION_VIEW, Uri.parse(videoURLs[position]));
                startActivity(launchBrowser);
            }

        });
    }

    /*Inflate the menu by loading the Menu defined with three items(video,Song,artist*/
    @Override
    public void onCreateContextMenu(ContextMenu menu,View v,ContextMenu.ContextMenuInfo menuInfo ) {
        super.onCreateContextMenu(menu,v,menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_context_menu, menu);
    }

    /*Overriden function that launches appropriate URLs when user selects a item from the menu */
    @Override
    public boolean onContextItemSelected(MenuItem mitem) {
        //get the URLs of the video and  wiki page for Song and Artist
        String[] videoURLs=getResources().getStringArray(R.array.videoURLs);
        String[] artistInfoURLs=getResources().getStringArray(R.array.artistInfoURLs);
        String[] songInfoURLs=getResources().getStringArray(R.array.songInfoURLs);
        AdapterView.AdapterContextMenuInfo info =(AdapterView.AdapterContextMenuInfo)mitem.getMenuInfo();
        //Launch appropriate URL by getting the ID of the menu items
        switch(mitem.getItemId())
        {
            case R.id.watch_id:
                                Intent launchVideo = new Intent(Intent.ACTION_VIEW, Uri.parse(videoURLs[info.position]));
                                startActivity(launchVideo);
                                break;
            case R.id.song_id:
                                Intent launchSongInfo = new Intent(Intent.ACTION_VIEW, Uri.parse(songInfoURLs[info.position]));
                                startActivity(launchSongInfo);
                                break;
            case R.id.artist_id:
                                Intent launchArtistInfo = new Intent(Intent.ACTION_VIEW, Uri.parse(artistInfoURLs[info.position]));
                                startActivity(launchArtistInfo);
                                break;

        }
        return super.onContextItemSelected(mitem);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
