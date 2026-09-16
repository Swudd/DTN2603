package backend.service.impl;

import backend.repository.IQLPositionRepository;
import backend.repository.impl.QLPositionReposirotyImpl;
import backend.service.IQLPositionService;
import entity.Position;

import java.util.List;

public class QLPositionServiceImpl implements IQLPositionService {
    IQLPositionRepository repository;
    public QLPositionServiceImpl() {
        repository = new QLPositionReposirotyImpl();
    }

    @Override
    public List<Position> getAllPositions() {
        return repository.getAllPositions();
    }

    @Override
    public boolean CheckExists(int positionId) {
        return repository.CheckExists(positionId);
    }
}
