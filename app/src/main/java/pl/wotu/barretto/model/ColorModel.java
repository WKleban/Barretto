package pl.wotu.barretto.model;

public class ColorModel {
    int colorID;
    String name;
    String hexColor;
//    String colorHex;

    public ColorModel() {
    }

    public ColorModel(int colorID, String name, String hexColor) {
        this.colorID = colorID;
        this.name = name;
        this.hexColor = hexColor;
    }


    public int getColorID() {
        return colorID;
    }

    public void setColorID(int colorID) {
        this.colorID = colorID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHexColor() {
        return hexColor;
    }

    public void setHexColor(String hexColor) {
        this.hexColor = hexColor;
    }
}




