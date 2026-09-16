package backend.controller;

import backend.service.IQLPositionService;
import backend.service.impl.QLPositionServiceImpl;
import entity.Position;

import java.util.List;

public class QLPositionController {
    IQLPositionService service;
    public QLPositionController(){ service = new QLPositionServiceImpl(); }

    public List<Position> GetAllPosition(){
        List<Position> positions = service.getAllPositions();
        return positions;
    }

    public boolean CheckExists(int positionId){
        return service.CheckExists(positionId);
    }
}
