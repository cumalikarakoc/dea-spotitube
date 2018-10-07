package nl.han.ica.dea.cumali.services;

import nl.han.ica.dea.cumali.datasources.PlaylistDAO;
import nl.han.ica.dea.cumali.dto.PlaylistCollectionDTO;
import nl.han.ica.dea.cumali.dto.PlaylistDTO;
import nl.han.ica.dea.cumali.dto.TrackDTO;

public class PlaylistService {
    private PlaylistCollectionDTO playlistCollection;

    public PlaylistService(){
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

    public void setPlaylistCollection(PlaylistCollectionDTO playlistCollection) {
        this.playlistCollection = playlistCollection;
    }
}
