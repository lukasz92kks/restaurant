package pl.krawczyk.restaurant.model;

// klasa wątku, który jest rysowany na mapie
public abstract class DrawableThread extends Drawable implements Runnable {
    
    private boolean active = true;  // flaga okraślająca czy wątek jest aktywny
    
    public DrawableThread(int x, int y) {
        super(x, y);
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    
    public void stop() {
        active = false;
    }
}
