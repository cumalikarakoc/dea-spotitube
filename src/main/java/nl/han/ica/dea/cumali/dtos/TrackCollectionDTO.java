package nl.han.ica.dea.cumali.dtos;

import java.util.List;

public class TrackCollectionDTO {
    private List<TrackDTO> tracks;

    public TrackCollectionDTO(List<TrackDTO> tracks) {
        this.tracks = tracks;
    }

    public List<TrackDTO> getTracks() {
        return tracks;
    }
}
