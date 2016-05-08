package com.project.vishalaxi.watchvideos;

/**
 * Created by Vishalaxi on 2/16/2016.
 * This class is used to hold the information of a video
 * member variables are:
 * miconID: id of the thumbnail images placed in drawable
 * mtitle: name of the song
 * mtitle: name of the artist or the band
 * member functions:
 * getter methods getTitle(), getArtist() and getIconID()
 * get the corresponding values of the member variables
 */

public class video {

    /*Member variables*/
    private String mtitle;
    private String martist;
    private int miconID;

    /*Constructor to initialize the member variables*/
    public video(String title, String artist, int iconID) {
        this.mtitle = title;
        this.martist = artist;
        this.miconID = iconID;
    }
    /*Getter methods to obtain the member variables values*/
    public String getTitle() {
        return mtitle;
    }

    public String getArtist() {
        return martist;
    }

    public int getIconID() {
        return miconID;
    }
}
