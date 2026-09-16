package backend.service;

import entity.Position;

import java.util.List;

public interface IQLPositionService {
    List<Position> getAllPositions();
    boolean CheckExists(int positionId);
}
