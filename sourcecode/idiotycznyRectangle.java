import javafx.geometry.Bounds;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.ScrollEvent;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.event.EventHandler;
import javafx.stage.WindowEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;

/**
 * klasa ktora tworzy prostokat
 */
public class idiotycznyRectangle extends Rectangle{
    /**
     * podstawowy konstruktor prostokata
     * @param x koordynaty polozenia na ekranie "x"
     * @param y koordynaty polozenia na ekranie "y"
     * @param width szerokosc prostokata
     * @param height wysokosc prostokata
     */
    public idiotycznyRectangle(double x, double y, double width, double height){
        super(x,y,width,height);
        setStroke(Color.BLACK);
        setFill(Color.TRANSPARENT);
        setStrokeWidth(1);
        setOnMouseClicked(new idiotycznyRectangleEventHandler());
        setOnMouseDragged(new idiotycznyRectangleEventHandler());
        setOnMouseEntered(new idiotycznyRectangleEventHandler());
        setOnMouseExited(new idiotycznyRectangleEventHandler());
        setOnScroll(new idiotycznyRectangleScrollHandler());
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
    class idiotycznyRectangleEventHandler implements EventHandler<MouseEvent>{
        idiotycznyRectangle rect;
        private double x;
        private double y;
        
        /**
         * funkcja przesuwajaca prostokat na ekranie
         * @param e koncowe polozenie prostokata
         */
 
         private void przesuwanie(MouseEvent e) {
            double dx = e.getX()-x;
            double dy = e.getY()-y;
            if (rect.isHit(x,y)){
                rect.setX(dx+rect.getX());
                rect.setY(dy+rect.getY());
            }
            x = x+dx;
            y = y+dy;
        }


        /**
         * funkcja implementujaca przesuwanie, zmiane koloru obwodki przy najezdzaniu na figure
         * @param e 
         */

        public void handle(MouseEvent e) {
            rect = (idiotycznyRectangle) e.getSource();
            if (e.getEventType()==MouseEvent.MOUSE_DRAGGED && e.getButton()==MouseButton.PRIMARY){
                przesuwanie(e);
            }
            else if (e.getEventType() == MouseEvent.MOUSE_CLICKED && e.getButton()==MouseButton.PRIMARY){
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
    class idiotycznyRectangleScrollHandler implements EventHandler<ScrollEvent>{
        idiotycznyRectangle rect;
        /**
         * funkcja skalujaca prostokat
         * @param e jeden "scroll" w gore lub w dol
         */
        private void skaluj(ScrollEvent e) {
            double x = e.getX();
            double y = e.getY();
            if (rect.isHit(x,y)) {
                rect.setX(rect.getX()-e.getDeltaY()*0.05);
                rect.setY(rect.getY()-e.getDeltaY()*0.05);
                rect.setWidth(rect.getWidth()+e.getDeltaY()*0.1);
                rect.setHeight(rect.getHeight()+e.getDeltaY()*0.1);
            }
        }
        /**
         * funkcja implementujaca skalowanie
         * @param e scroll
         */
        public void handle(ScrollEvent e) {
            rect = (idiotycznyRectangle) e.getSource();
            if (e.getEventType()==ScrollEvent.SCROLL){
                skaluj(e);
            }
        }
    }
}