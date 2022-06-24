import java.lang.constant.DynamicCallSiteDesc;
import java.util.List;
import javafx.geometry.Bounds;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.shape.Polygon;
import javafx.scene.paint.Color;
import javafx.scene.transform.Scale;  

/**
 * klasa ktory tworzy trojkat (polygon)
 */
public class idiotycznyPolygon extends Polygon{
    /**
     * podstawowy konstruktor trojkata
     */
    public idiotycznyPolygon(){
        setStroke(Color.BLACK);
        setFill(Color.TRANSPARENT);
        
        setStrokeWidth(1);
        setOnMouseClicked(new idiotycznyPolygonEventHandler());
        setOnMouseDragged(new idiotycznyPolygonEventHandler());
        setOnMouseEntered(new idiotycznyPolygonEventHandler());
        setOnMouseExited(new idiotycznyPolygonEventHandler());
        setOnScroll(new idiotycznyPolygonScrollHandler());
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

    class idiotycznyPolygonEventHandler implements EventHandler<MouseEvent>{
        idiotycznyPolygon pol;
        private double x;
        private double y;

        /**
         * funkcja przesuwajaca trojkat na ekranie
         * @param e koncowe polozenie trojkata
         */
        private void przesuwanie(MouseEvent e) {
            double dx = e.getX()-x;
            double dy = e.getY()-y;
            if (pol.isHit(x,y)){
                for (int i = 0; i<6; i++){
                    if (i%2==0){
                        double prevx = pol.getPoints().get(i);
                        pol.getPoints().set(i,prevx+dx);
                    }
                    else {
                        double prevy = pol.getPoints().get(i);
                        pol.getPoints().set(i,prevy+dy);
                    }
                }
            }
            x = x+dx;
            y = y+dy;
        }

        /**
         * funkcja implementujaca przesuwanie, zmiane koloru obwodki przy najezdzaniu na figure
         * @param e 
         */
        public void handle(MouseEvent e) {
            pol = (idiotycznyPolygon) e.getSource();
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
    class idiotycznyPolygonScrollHandler implements EventHandler<ScrollEvent>{
        idiotycznyPolygon pol;
        /**
         * funkcja skalujaca trojkat
         * @param e jeden "scroll" w gore lub w dol
         */
        private void skaluj(ScrollEvent e) {
            double x = e.getX();
            double y = e.getY();
            double wx = (pol.getPoints().get(0)+pol.getPoints().get(2)+pol.getPoints().get(4))/3;
            double wy = (pol.getPoints().get(1)+pol.getPoints().get(3)+pol.getPoints().get(5))/3;
            if (pol.isHit(x,y)) {
                if (e.getDeltaY()>0){
                    for (int i = 0; i<6;i++) {
                        double t = pol.getPoints().get(i);
                        if (i%2==0){
                            double dx = (t-wx)/10;
                            pol.getPoints().set(i,t+dx);
                        }
                        else{
                            double dy = (t-wy)/10;
                            pol.getPoints().set(i,t+dy); 
                        }    
                    }    
                }
                else if (e.getDeltaY()<0){
                    for (int i = 0; i<6;i++) {
                        double t = pol.getPoints().get(i);
                        if (i%2==0){
                            double dx = (t-wx)/10;
                            pol.getPoints().set(i,t-dx);
                        }
                        else{
                            double dy = (t-wy)/10;
                            pol.getPoints().set(i,t-dy); 
                        }    
                    } 
                }
            }
        }
        /**
         * funkcja implementujaca skalowanie
         * @param e scroll
         */
        public void handle(ScrollEvent e) {
            pol = (idiotycznyPolygon) e.getSource();
            if (e.getEventType()==ScrollEvent.SCROLL){
                skaluj(e);
            }
        }
    } 
}