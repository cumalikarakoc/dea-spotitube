package nl.han.ica.dea.cumali.services;

import nl.han.ica.dea.cumali.dto.PlaylistCollectionDTO;
import nl.han.ica.dea.cumali.dto.PlaylistDTO;
import nl.han.ica.dea.cumali.dto.TrackDTO;

import java.util.ArrayList;
import java.util.List;

public class PlaylistRepository {
    private PlaylistCollectionDTO playlistCollection;

    public PlaylistRepository(){
        List<PlaylistDTO> playlists = new ArrayList<>();

        List<TrackDTO> tracks1 = new ArrayList<>();
        tracks1.add(new TrackDTO(1, "Song for someone", "The Frames", 350, "The cost", 0, null, null, false));
        tracks1.add(new TrackDTO(2, "The cost", "The Frames", 423,null, 37, "10-01-2005", "Title song from the Album The Cost", true));

        List<TrackDTO> tracks2 = new ArrayList<>();

        playlists.add(new PlaylistDTO(1, "Death metal", true, tracks1));
        playlists.add(new PlaylistDTO(2, "Pop", false, tracks2));

        playlistCollection = new PlaylistCollectionDTO(playlists, 21321);
    }

    public PlaylistCollectionDTO getAll() {
        return playlistCollection;
    }

    public PlaylistDTO find(int id){
        for (PlaylistDTO p: playlistCollection.getPlaylists()) {
            if (p.getId() == id){
                return p;
            }
        }
        return null;
    }
}
