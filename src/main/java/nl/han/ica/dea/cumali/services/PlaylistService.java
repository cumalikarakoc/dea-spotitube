package nl.han.ica.dea.cumali.services;

import nl.han.ica.dea.cumali.datasources.PlaylistDAO;
import nl.han.ica.dea.cumali.dto.PlaylistCollectionDTO;
import nl.han.ica.dea.cumali.dto.PlaylistDTO;
import nl.han.ica.dea.cumali.dto.TrackDTO;

import java.util.ArrayList;
import java.util.List;

public class PlaylistService {
    private PlaylistCollectionDTO playlistCollection;

    public PlaylistService(){
//        TrackService trackService = new TrackService();

//        List<TrackDTO> tracksDeathMetal = new ArrayList<>();
//        tracksDeathMetal.add(trackService.find(4));
//        tracksDeathMetal.add(trackService.find(5));
//
//        List<TrackDTO> tracksPop = new ArrayList<>();
//        tracksPop.add(trackService.find(1));
//        tracksPop.add(trackService.find(2));

//        List<PlaylistDTO> playlists = new ArrayList<>();
//        playlists.add(new PlaylistDTO(1, "Death metal", true, tracksDeathMetal));
//        playlists.add(new PlaylistDTO(2, "Pop", false, tracksPop));

        playlistCollection = new PlaylistCollectionDTO(new PlaylistDAO().findAll(), 21321);

    }

    public PlaylistCollectionDTO all() {
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

    public TrackDTO findTrack(PlaylistDTO playlist, int track_id) {
        for (TrackDTO t : playlist.getTracks()) {
            if (t.getId() == track_id){
                return t;
            }
        }
        return null;
    }
}
