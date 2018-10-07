package nl.han.ica.dea.cumali.services;

import nl.han.ica.dea.cumali.datasources.TrackDAO;
import nl.han.ica.dea.cumali.dto.TrackCollectionDTO;
import nl.han.ica.dea.cumali.dto.TrackDTO;

import java.util.ArrayList;
import java.util.List;

public class TrackService {
    private TrackCollectionDTO trackCollection;

    public TrackService(){
        trackCollection= new TrackCollectionDTO(new TrackDAO().findAll());
    }

    public TrackCollectionDTO all(){
        return trackCollection;
    }

    public TrackDTO find(int id) {
        for (TrackDTO t: trackCollection.getTracks()) {
            if(t.getId() == id){
                return t;
            }
        }
        return null;
    }

    public void setTrackCollection(TrackCollectionDTO trackCollection) {
        this.trackCollection = trackCollection;
    }
}
