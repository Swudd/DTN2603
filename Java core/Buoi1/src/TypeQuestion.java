public class TypeQuestion {
    int typeId;
    TypeName typeName;

    public TypeQuestion(int typeId, TypeName typeName) {
        this.typeId = typeId;
        this.typeName = typeName;
    }

    public void showInfo(){
        System.out.println("Type Id: " + typeId);
        System.out.println("Type Name: " + typeName);
    }
}
