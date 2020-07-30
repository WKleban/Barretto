package pl.wotu.barretto.model;

public class CategoryModel {
    public String name,idCategory,hexColor;
    public int bgColor;
    public boolean isChosen;

    public CategoryModel() {
    }

    public CategoryModel(String name, String idCategory, int bgColor, String hexColor, boolean isChosen) {
        this.name = name;
        this.idCategory = idCategory;
        this.bgColor = bgColor;
        this.hexColor = hexColor;
        this.isChosen = isChosen;
    }
    public CategoryModel(String name, String idCategory, int bgColor, String hexColor ) {
        this.name = name;
        this.idCategory = idCategory;
        this.bgColor = bgColor;
        this.hexColor = hexColor;
        this.isChosen = false;

    }

    public boolean getChosen() {
        return isChosen;
    }

    public void setChosen(boolean chosen) {
        isChosen = chosen;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(String idCategory) {
        this.idCategory = idCategory;
    }

    public int getBgColor() {
        return bgColor;
    }

    public String getHexColor() {
        return hexColor;
    }

    public void setHexColor(String hexColor) {
        this.hexColor = hexColor;
    }

    public void setBgColor(int bgColor) {
        this.bgColor = bgColor;
    }
}

