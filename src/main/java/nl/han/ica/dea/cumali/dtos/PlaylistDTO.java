package nl.han.ica.dea.cumali.dtos;

import java.util.List;

public class PlaylistDTO {
    private int id;
    private String name;
    private boolean owner;

    private List<TrackDTO> tracks;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<TrackDTO> getTracks() {
        return tracks;
    }

    public void setTracks(List<TrackDTO> tracks){
        this.tracks = tracks;
    }

    public boolean getOwner() {
        return owner;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOwner(boolean owner) {
        this.owner = owner;
    }
}
