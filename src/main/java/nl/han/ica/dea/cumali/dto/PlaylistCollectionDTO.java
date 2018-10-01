package nl.han.ica.dea.cumali.dto;

import java.util.List;

public class PlaylistCollectionDTO {

    private List<PlaylistDTO> playlists;
    private int length;

    public PlaylistCollectionDTO(List<PlaylistDTO> playlists, int length){
        this.playlists = playlists;
        this.length = length;
    }

    public List<PlaylistDTO> getPlaylists() {
        return playlists;
    }

    public int getLength() {
        return length;
    }
}

