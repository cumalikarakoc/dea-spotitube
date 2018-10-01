package nl.han.ica.dea.cumali.services;

import nl.han.ica.dea.cumali.dto.TrackCollectionDTO;
import nl.han.ica.dea.cumali.dto.TrackDTO;

import java.util.ArrayList;
import java.util.List;

public class TrackService {
    private TrackCollectionDTO trackCollection;

    public TrackService(){
        List<TrackDTO> tracks = new ArrayList<>();
        tracks.add(new TrackDTO(1, "Ocean and a rock", "Lisa Hannigan", 337, "Sea sew", 0, null, null, false));
        tracks.add(new TrackDTO(2, "So Long, Marianne", "Leonard Cohen", 546, "Songs of Leonard Cohen", 0, null, null, false));
        tracks.add(new TrackDTO(3, "One", "Metallica", 423, null, 37, "1-11-2001", "Long version", true));
        tracks.add(new TrackDTO(4, "Song for someone", "The Frames", 350, "The cost", 0, null, null, false));
        tracks.add(new TrackDTO(5, "The cost", "The Frames", 423,null, 37, "10-01-2005", "Title song from the Album The Cost", true));


        trackCollection= new TrackCollectionDTO(tracks);
    }

    public TrackCollectionDTO getAll(){
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
}
