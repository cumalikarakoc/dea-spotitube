package nl.han.ica.dea.cumali.dto;

public class TrackDTO {
    private int id;
    private String title;
    private String performer;
    private int duration;
    private String album;
    private int playcount;
    private String publicationDate;
    private String description;
    private boolean offlineAvailable;
    private int playlist_id;

    public TrackDTO(int id, String title, String performer, int duration, String album, int playcount, String publicationDate, String description, boolean offlineAvailable, int playlist_id) {
        this.id = id;
        this.title = title;
        this.performer = performer;
        this.duration = duration;
        this.album = album;
        this.playcount = playcount;
        this.publicationDate = publicationDate;
        this.description = description;
        this.offlineAvailable = offlineAvailable;
        this.playlist_id = playlist_id;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getPerformer() {
        return performer;
    }

    public int getDuration() {
        return duration;
    }

    public String getAlbum() {
        return album;
    }

    public int getPlaycount() {
        return playcount;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public String getDescription() {
        return description;
    }

    public boolean isOfflineAvailable() {
        return offlineAvailable;
    }

    public int getPlaylist_id() {
        return playlist_id;
    }
}
