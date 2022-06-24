import java.util.Vector;
import java.util.List;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Dialog;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.canvas.Canvas;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.transform.Rotate;
import javafx.scene.layout.Region;

/** 
* Program typu paint
* @author Bartosz Tramś
* @version 4.0
* */ 

public class PAINt extends Application {
    
    /** funkcja definiujaca dzialanie programu 
     * @param stage okno w którym wyświetlany jest program
    */
    public void start(Stage stage){

        /** BorderPane na którym będą rozmieszczone elementy */
        BorderPane root = new BorderPane();

        /** Canvas na którym będzie można rysować */
        Canvas canvas = new Canvas(600, 400);

        /** Klasa służąca do rysowania figur*/
        GraphicsContext gc;
        gc = canvas.getGraphicsContext2D();
        gc.setLineWidth(1);
        
        /** tworzenie Menu */
        MenuBar myMenu = new MenuBar();
        
        /** przycisk opcje */
        Menu menu = new Menu("Options");
        
        /** obsługa przycisku z informacja */
        Dialog<String> dialog = new Dialog<String>();
        dialog.setTitle("Program info");
        ButtonType type = new ButtonType("OK", ButtonData.OK_DONE);
        dialog.setContentText("PAINt\nauthor: Bartosz Trams\nPress one of the buttons in the bottom left corner to draw a shape.\nWARNING! After drawing a shape once, you need to RESELECT a button from the menu, to draw another one\nEnter the mouse over a shape, and use the scroll on your mouse in order to resize the shape.\nClick on a shape then drag the mouse while the left button is pressed, in order to move the shape.\nRight click a shape and select one of the menus in order to rotate the shape or change its color");
        dialog.setResizable(true);
        dialog.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        dialog.getDialogPane().getButtonTypes().add(type);
      

        /** definiowanie akcji wykonywanych przez przyciski na menu */
        EventHandler<ActionEvent> evHand = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                MenuItem m = (MenuItem) event.getSource();      
                if (m.getText().equals("Exit")) {
                    System.exit(0);
                }
                else if (m.getText().equals("Info")) {
                    dialog.showAndWait();
                }
            }
        };
        
        /** podpięcie akcji pod przyciski na menu */
        MenuItem exit = new MenuItem("Exit");
        MenuItem info = new MenuItem("Info");
        exit.setOnAction(evHand);
        info.setOnAction(evHand);

        /** stworzenie przyciskow obslugujacych rysunek figur */
        ToggleButton tk = new ToggleButton("Triangle");
        ToggleButton ok = new ToggleButton("Circle");
        ToggleButton pr = new ToggleButton("Rectangle");
        
        /** dodanie przyciskow do grupy toggle */
        ToggleGroup tgr = new ToggleGroup();
        tk.setToggleGroup(tgr);
        ok.setToggleGroup(tgr);
        pr.setToggleGroup(tgr);

        /** dodanie przyciskow do wszystkich menu */
        HBox hbox = new HBox(tk,ok,pr);
        menu.getItems().addAll(info,exit);
        myMenu.getMenus().addAll(menu);


        //xy: pomocniczy Array z koordynatami punktów     
        double[] xy = new double[6];
        xy[0]=-99;
        xy[2]=-99;

        //obsługa rysowania figur
        root.setOnMouseClicked((new EventHandler<MouseEvent>() { 
            public void handle(MouseEvent e) { 
                //rysowanie figur
                if (e.getEventType() == MouseEvent.MOUSE_CLICKED && e.getButton()==MouseButton.PRIMARY){
                    //rysowanie prostokąta
                    if (pr.isSelected()){
                        if (xy[0]==-99){
                            xy[0]=e.getX();
                            xy[1]=e.getY();
                        }
                        else {
                            xy[2]=e.getX();
                            xy[3]=e.getY();
                            idiotycznyRectangle rect = new idiotycznyRectangle(Math.min(xy[0],xy[2]),Math.min(xy[1],xy[3]),Math.abs(xy[2]-xy[0]),Math.abs(xy[3]-xy[1]));
                            root.getChildren().add(rect);
                            tgr.selectToggle(null);
                            xy[0]=-99;
                            xy[2]=-99;
                        }
                    }
                    //rysowanie okręgu
                    else if (ok.isSelected()){
                        if(xy[0]==-99){
                            xy[0]=e.getX();
                            xy[1]=e.getY();
                        }
                        else {
                            xy[2]=e.getX();
                            xy[3]=e.getY();
                            idiotycznyCircle circ = new idiotycznyCircle(Math.min(xy[0],xy[2]),Math.min(xy[1],xy[3]),Math.sqrt((xy[2]-xy[0])*(xy[2]-xy[0])+(xy[3]-xy[1])*(xy[3]-xy[1])));
                            root.getChildren().add(circ);
                            tgr.selectToggle(null);
                            xy[0]=-99;
                            xy[2]=-99;
                        }
                    }
                    //rysowanie trójkąta
                    else if (tk.isSelected()){
                        if(xy[0]==-99 && xy[2]==-99){
                            xy[0]=e.getX();
                            xy[1]=e.getY();
                        }
                        else if(xy[0]!=-99 && xy[2]==-99){
                            xy[2]=e.getX();
                            xy[3]=e.getY();
                        }
                        else if(xy[0]!=-99 && xy[2]!=-99){
                            xy[4]=e.getX();
                            xy[5]=e.getY();
                            idiotycznyPolygon polygon = new idiotycznyPolygon();
                            polygon.getPoints().addAll(new Double[]{
                                xy[0], xy[1],
                                xy[2], xy[3],
                                xy[4], xy[5] });
                            root.getChildren().add(polygon);
                            tgr.selectToggle(null);
                            xy[0]=-99;
                            xy[2]=-99;
                        }
                    }
                }
                //implementacja menu
                if(e.getEventType() == MouseEvent.MOUSE_CLICKED && e.getButton()==MouseButton.SECONDARY){
                    Object[] test = root.getChildren().toArray();
                    for (int i = 3; i<test.length;i++){
                        //menu dla prostokąta
                        if (test[i] instanceof idiotycznyRectangle){
                            idiotycznyRectangle rect = (idiotycznyRectangle)test[i];
                            //jeśli najeżdżamy na prostokąt, to wyświetli się menu
                            if (rect.isHit(e.getSceneX(),e.getSceneY())){
                                //tworzenie menu
                                ContextMenu contextMenu = new ContextMenu();
                                MenuItem colp = new MenuItem("color picker");
                                MenuItem rot = new MenuItem("rotate");
                                TextInputDialog dialog = new TextInputDialog();
                                //obsługa rotacji
                                dialog.setContentText("Name the degree you want to rotate the shape: ");
                                dialog.setTitle("Rotating menu");
                                dialog.setHeaderText(null);
                                dialog.setGraphic(null);
                                rot.setOnAction(new EventHandler <ActionEvent>() {
                                    public void handle (ActionEvent ev){
                                        Double val = Double.parseDouble(dialog.showAndWait().get());
                                        Rotate rotate = new Rotate();
                                        rotate.setPivotX(rect.getX()+(rect.getWidth()/2));
                                        rotate.setPivotY(rect.getY()+(rect.getHeight()/2));
                                        rotate.setAngle(val);
                                        rect.getTransforms().addAll(rotate);
                                    }
                                });
                                //obsługa wybierania koloru
                                ColorPicker colorPicker = new ColorPicker();
                                colp.setGraphic(colorPicker);
                                colp.setOnAction(new EventHandler <ActionEvent>() {
                                    public void handle(ActionEvent ev){
                                        rect.setFill(colorPicker.getValue());
                                    }
                                });
                                contextMenu.getItems().addAll(colp,rot);
                                contextMenu.show(root,e.getScreenX(),e.getScreenY());
                            }
                        }
                        //menu dla okregu
                        if(test[i] instanceof idiotycznyCircle){
                            idiotycznyCircle circ = (idiotycznyCircle)test[i];
                            if (circ.isHit(e.getSceneX(),e.getSceneY())){
                                //tworzenie menu, obsluga wybierania koloru
                                ContextMenu contextMenu = new ContextMenu();
                                MenuItem colp = new MenuItem("color picker");
                                ColorPicker colorPicker = new ColorPicker();
                                colp.setGraphic(colorPicker);
                                colp.setOnAction(new EventHandler <ActionEvent>() {
                                    public void handle(ActionEvent ev){
                                        circ.setFill(colorPicker.getValue());
                                    }
                                });
                                contextMenu.getItems().addAll(colp);
                                contextMenu.show(root,e.getScreenX(),e.getScreenY());
                            }
                        }
                        //menu dla polygonu
                        if(test[i] instanceof idiotycznyPolygon){
                            idiotycznyPolygon pol = (idiotycznyPolygon)test[i];
                            if (pol.isHit(e.getSceneX(),e.getSceneY())){
                                //tworzenie menu
                                ContextMenu contextMenu = new ContextMenu();
                                MenuItem colp = new MenuItem("color picker");
                                MenuItem rot = new MenuItem("rotate");
                                TextInputDialog dialog = new TextInputDialog();
                                dialog.setContentText("Name the degree you want to rotate the shape: ");
                                dialog.setTitle("Rotating menu");
                                dialog.setHeaderText(null);
                                dialog.setGraphic(null);
                                rot.setOnAction(new EventHandler <ActionEvent>() {
                                    public void handle (ActionEvent ev){
                                        Double val = Double.parseDouble(dialog.showAndWait().get());
                                        Rotate rotate = new Rotate();
                                        rotate.setPivotX((pol.getPoints().get(0)+pol.getPoints().get(2)+pol.getPoints().get(4))/3);
                                        rotate.setPivotY((pol.getPoints().get(1)+pol.getPoints().get(3)+pol.getPoints().get(5))/3);
                                        rotate.setAngle(val);
                                        pol.getTransforms().addAll(rotate);
                                    }
                                });
                                ColorPicker colorPicker = new ColorPicker();
                                colp.setGraphic(colorPicker);
                                colp.setOnAction(new EventHandler <ActionEvent>() {
                                    public void handle(ActionEvent ev){
                                        pol.setFill(colorPicker.getValue());
                                    }
                                });
                                contextMenu.getItems().addAll(colp,rot);
                                contextMenu.show(root,e.getScreenX(),e.getScreenY());
                            }
                        }
                    }
                }
            }
        }));

        //dodawanie elementów do roota/sceny
        root.setTop(myMenu);
        root.setBottom(hbox);
        root.setCenter(canvas);

        //tworzenie sceny i wyswietlenie stage'a
        Scene scene = new Scene(root,600, 450);
        stage.setScene(scene);
        stage.setTitle("PAINt v4.0");
        stage.show();
        dialog.show();
    }

    /** funkcja glowna 
     * @param args argumenty przy wywolaniu programu
    */
    public static void main(String[] args){
        launch(args);
    }
}
