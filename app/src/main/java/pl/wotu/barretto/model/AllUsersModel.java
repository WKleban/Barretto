package pl.wotu.barretto.model;

public class AllUsersModel {
    private String id;
    private String name;
    private String image;
    private int collectionSize;

    public AllUsersModel() {
    }

    public AllUsersModel(String name, String image) {
        this.name = name;
        this.image = image;
    }

    public AllUsersModel(String id, String name, String image, int collectionSize) {
        this.id = id;
        this.name = name;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getSize(){
        return collectionSize;
    }

    public void setSize(int collectionSize) {
        this.collectionSize = collectionSize;
    }
}
