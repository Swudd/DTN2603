package backend.repository;

import entity.Position;

import java.util.List;

public interface IQLPositionRepository {
    List<Position> getAllPositions();
}
