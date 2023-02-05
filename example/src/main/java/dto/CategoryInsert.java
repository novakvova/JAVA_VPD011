package dto;

public class CategoryInsert {
    private String name;

    public CategoryInsert() {
    }

    public CategoryInsert(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
