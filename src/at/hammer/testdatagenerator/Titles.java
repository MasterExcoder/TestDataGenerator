package at.hammer.testdatagenerator;

import java.time.LocalDate;

/**
 * Created by Samuel Hammer on 30.09.2014.
 */
public class Titles {
    private String titleId;
    private String name;
    private int duration;
    private String artist;
    private String album;
    private LocalDate release;

    public Titles(String titleId, String name, int duration, String artist, String album, LocalDate release) {
        super();
        this.titleId = titleId;
        this.name = name;
        this.duration = duration;
        this.artist = artist;
        this.album = album;
        this.release = release;
    }

    @Override
    public String toString() {
        String t = titleId + "\t" + name + "\t" + duration + "\t" + artist + "\t" + album + "\t" + release;
        return t;
    }

    public String getTitleId() {
        return titleId;
    }

    public void setTitleId(String titleId) {
        this.titleId = titleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public LocalDate getRelease() {
        return release;
    }

    public void setRelease(LocalDate release) {
        this.release = release;
    }
}
