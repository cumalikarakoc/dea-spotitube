package nl.han.ica.dea.cumali.services;

import nl.han.ica.dea.cumali.dto.TrackCollectionDTO;
import nl.han.ica.dea.cumali.dto.TrackDTO;

import java.util.ArrayList;
import java.util.List;

public class TrackRepository {
    private TrackCollectionDTO trackCollection;

    public TrackRepository(){
        List<TrackDTO> tracks = new ArrayList<>();
        tracks.add(new TrackDTO(3, "Ocean and a rock", "Lisa Hannigan", 337, "Sea sew", 0, null, null, false));
        tracks.add(new TrackDTO(4, "So Long, Marianne", "Leonard Cohen", 546, "Songs of Leonard Cohen", 0, null, null, false));
        tracks.add(new TrackDTO(5, "One", "Metallica", 423, null, 37, "1-11-2001", "Long version", true));

        trackCollection= new TrackCollectionDTO(tracks);
    }

    public TrackCollectionDTO getAll(){
        return trackCollection;
    }
}
