public class Position {
    int positionId;
    PositionName positionName;

    public Position(int positionId, PositionName positionName) {
        this.positionId = positionId;
        this.positionName = positionName;
    }



    public void showInfo(){
        System.out.println("Position Id: " + positionId);
        System.out.println("Position Name: " + positionName);
    }
}

