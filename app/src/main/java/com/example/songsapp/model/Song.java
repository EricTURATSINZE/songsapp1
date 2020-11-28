package com.example.songsapp.model;

public class Song {
    String songTitle, album, artist;

    public Song(String songTitle, String album, String artist) {
        this.songTitle = songTitle;
        this.album = album;
        this.artist = artist;
    }

    public String getSongTitle() {
        return songTitle;
    }

    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }
}
