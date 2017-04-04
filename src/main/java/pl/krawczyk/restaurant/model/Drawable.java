package pl.krawczyk.restaurant.model;

// klasa podstawowa dla rysowanych obiektów, zawiera współrzędne
public class Drawable {
    
    private int x;
    private int y;
    
    public Drawable(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
