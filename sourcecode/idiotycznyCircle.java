import javafx.geometry.Bounds;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;

/**
 * klasa ktora tworzy okrag
 */
public class idiotycznyCircle extends Circle{
    /**
     * podstawowy konstruktor okregu
     * @param x koordynaty polozenia na ekranie "x"
     * @param y koordynaty polozenia na ekranie "y"
     * @param radius promien okregu
     */
    public idiotycznyCircle(double x, double y, double radius){
        super(x,y,radius);
        setStroke(Color.BLACK);
        setFill(Color.TRANSPARENT);
        setOnMouseClicked(new idiotycznyCircleEventHandler());
        setOnMouseDragged(new idiotycznyCircleEventHandler());
        setOnMouseEntered(new idiotycznyCircleEventHandler());
        setOnMouseExited(new idiotycznyCircleEventHandler());
        setOnScroll(new idiotycznyCircleScrollHandler());
    }

    /**
     * funkcja sprawdzajaca czy dany punkt jest w figurze
     * @param x koordynata x punktu
     * @param y koordynata y punktu
     * @return wartosc typu bool, prawda jesli parametry x i y sa w obrebie figury
     */
    public boolean isHit(double x, double y) { 
        return getBoundsInLocal().contains(x,y);   
    }

    /**
     * klasa obslugujaca przesuwanie, najezdzanie na myszke 
     */
    class idiotycznyCircleEventHandler implements EventHandler<MouseEvent>{
        idiotycznyCircle circ;
        private double x;
        private double y;

         /**
         * funkcja przesuwajaca okrag na ekranie
         * @param e koncowe polozenie okregu
         */
        private void przesuwanie(MouseEvent e) {
            double dx = e.getX()-x;
            double dy = e.getY()-y;
            if (circ.isHit(x,y)){
                circ.setCenterX(circ.getCenterX()+dx);
                circ.setCenterY(circ.getCenterY()+dy);
            }
            x = x+dx;
            y = y+dy;
        }
        
        /**
         * funkcja implementujaca przesuwanie, zmiane koloru obwodki przy najezdzaniu na figure
         * @param e 
         */
        public void handle(MouseEvent e) {
            circ = (idiotycznyCircle) e.getSource();
            if (e.getEventType()==MouseEvent.MOUSE_DRAGGED){
                przesuwanie(e);
            }
            else if (e.getEventType() == MouseEvent.MOUSE_CLICKED){
                x = e.getX();
                y = e.getY();
            }
            if (e.getEventType()==MouseEvent.MOUSE_ENTERED){
                setStroke(Color.RED);
                setStrokeWidth(2);
            }
            else if (e.getEventType()==MouseEvent.MOUSE_EXITED){
                setStroke(Color.BLACK);
                setStrokeWidth(1);
            }
        }
    }
    /**
     * klasa obslugujaca zooma
     */
    class idiotycznyCircleScrollHandler implements EventHandler<ScrollEvent>{
        idiotycznyCircle circ;
        /**
         * funkcja skalujaca okrag
         * @param e jeden "scroll" w gore lub w dol
         */
        private void skaluj(ScrollEvent e) {
            double x = e.getX();
            double y = e.getY();
            if (circ.isHit(x,y)) {
                circ.setRadius(circ.getRadius()+e.getDeltaY()*0.1);
            }
        }

        /**
         * funkcja implementujaca skalowanie
         * @param e scroll
         */
        public void handle(ScrollEvent e) {
            circ = (idiotycznyCircle) e.getSource();
            if (e.getEventType()==ScrollEvent.SCROLL){
                skaluj(e);
            }
        }
    }
}