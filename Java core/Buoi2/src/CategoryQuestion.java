public class CategoryQuestion {
    int categoryId;
    CategoryName categoryName;

    public CategoryQuestion(int categoryId, CategoryName categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    public void showInfo()
    {
        System.out.println("Category ID: " + categoryId);
        System.out.println("Category Name: " + categoryName);
    }
}
