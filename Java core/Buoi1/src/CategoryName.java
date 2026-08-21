public enum CategoryName {
    JAVA("JAVA"),
    SQL("SQL"),
    POSTMAN("POSTMAN"),
    RUBY("RUBY"),
    DOT_NET(".NET");

    private final String value;

    CategoryName(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
