import java.util.Random;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

/**
 * a color based simulation
 * @author Bartosz Trams
 * @version 5.0
 */

public class symulacja extends Application {
    /**
     * the seed used in the rest of the program
     */
    public Random rnm = new Random();

    //GridPane gridPane = new GridPane();
    /**
     * the class which makes the squares change their colors
     */
    class pole extends Thread {
        /** rectangle representing the central square, and the surrounding ones */
        Rectangle square, left, right, top, bot;
        /** i,j - coordinates of square on the board, k - frequency of the delay of new simulations */
        int i,j,k;
        /** probability of the change */
        double p;
        /** boolean values, true if surrounding squares are active*/
        boolean leftPoleRunning = true;
        boolean rightPoleRunning = true;
        boolean topPoleRunning = true;
        boolean botPoleRunning = true;;
        /** boolean value, true if square is active */
        public boolean running = true;
        GridPane gridPane;
        /**
         * constructor of the square function
         * @param rect rectangle representing the central square
         * @param i x coordinate of central square on the board
         * @param j y coordinate of central square on the board
         * @param left the square on the left of the central square on the board
         * @param right the square on the right of the central square on the board
         * @param top the square on the top of the central square on the board
         * @param bot the square on the bottom of the central square on the board
         * @param k the frequency of the delay in new simulations
         * @param p the probability of change to random color, 1 = always changing to random colors
         */
        pole(Rectangle rect, int i, int j, Rectangle left, Rectangle right, Rectangle top, Rectangle bot, int k, double p, GridPane gridPane){
            this.square = rect;
            this.left = left;
            this.right = right;
            this.top = top;
            this.bot = bot;
            this.i = i;
            this.j = j;
            this.k = k;
            this.p = p;
            this.gridPane = gridPane;
        }
        /**
         * the function that randomly changes the color of the central square
         * @param rect central square
         */
        public synchronized void zmienkolor(Rectangle rect){
            System.out.println("["+Integer.toString(i)+"] ["+Integer.toString(j)+"] starting thread");
            /** random number between 0 and 1, compared with p to determine what to do next*/
            double wart = rnm.nextDouble();
            /** changing the color of the central square to a random color if random number<=p */
            if (wart<=p){
                Platform.runLater(() -> { rect.setFill(Color.color(rnm.nextDouble(),rnm.nextDouble(),rnm.nextDouble())); });
            }
            /** changing the color of the central square to a mix of the active surrounding ones */
            else {
                /** number of active squares surrounding the central one*/
                int las=0;
                /** if a surrounding square is active, it's added to a mix of the target color */
                double red = 0;
                double green = 0;
                double blue = 0;
                if (leftPoleRunning){
                    las++;
                    Color cl = (Color)left.getFill();
                    red = red +cl.getRed();
                    green+=cl.getGreen();
                    blue+=cl.getBlue();
                }
                if (rightPoleRunning){
                    las++;
                    Color cl = (Color)right.getFill();
                    red+=cl.getRed();
                    green+=cl.getGreen();
                    blue+=cl.getBlue();
                }
                if (topPoleRunning){
                    las++;
                    Color cl = (Color)top.getFill();
                    red+=cl.getRed();
                    green+=cl.getGreen();
                    blue+=cl.getBlue();
                }
                if (botPoleRunning){
                    las++;
                    Color cl = (Color)bot.getFill();
                    red+=cl.getRed();
                    green+=cl.getGreen();
                    blue+=cl.getBlue();
                }
                /** changing the central square color to a mix of the surrounding ones */
                if (las>0){
                    double redFinal = red/las;
                    double greenFinal = green/las;
                    double blueFinal = blue/las;

                    Platform.runLater(() -> { rect.setFill(Color.color(redFinal,greenFinal,blueFinal)); });
                }
                /** if there are no active squares around the central square, the central square's color stays the same */
                else {}
            }
            System.out.println("["+Integer.toString(i)+"] ["+Integer.toString(j)+"] ending thread");
        }
        /**
         * function that always runs. if the central square is set to active it does the random color change, but if not, the thread is slept
         */
        public void run() {
            while (true){
                try {
                    if (running){
                        synchronized(this.gridPane){
                            zmienkolor(square);
                        }
                    }
                    if (!running){
                        this.wait();
                    }
                    /** the random amount of millisecond the thread of the central square is slept for */
                    double sleeper = 0.5*k + (1.5*k-0.5*k)*rnm.nextDouble();
                    //System.out.println("["+Integer.toString(i)+"] ["+Integer.toString(j)+"] going to sleep for "+(long)sleeper+" miliseconds");
                    try{ Thread.sleep((long)sleeper);} catch (Exception e){}
                }
                catch (Exception ex){}
            }
        }
        /**
         * function that stops the thread from being active
         */
        public void stopThread() {
            running = false;
            System.out.println("["+Integer.toString(i)+"] ["+Integer.toString(j)+"] thread stopped");
        }
        /**
         * function that restarts the thread
         */
        public void startThread() {
            running = true;
            System.out.println("["+Integer.toString(i)+"] ["+Integer.toString(j)+"] thread restarted");
        }

    }

    /**
     * function which makes the visual part of the program
     * @param stage
     */
    public void start(Stage stage){
        /**
         * par - parameters given at launch
         * n - width of the board
         * m - height of the board
         * k - delay in the simulations
         * p - probability of change to random color
         */
        Parameters par = getParameters();
        int n = Integer.parseInt(par.getNamed().get("arg[0]"));
        int m = Integer.parseInt(par.getNamed().get("arg[1]"));
        int k = Integer.parseInt(par.getNamed().get("arg[2]"));
        double p = Double.parseDouble(par.getNamed().get("arg[3]"));

        /** adding rectangles to the grid pane */
        Rectangle[][] rectArray = new Rectangle[n][m];
        GridPane gridPane = new GridPane();
        for (int i = 0; i<n; i++){
            for (int j = 0; j<m; j++){
                Rectangle r = new Rectangle(50,50);
                r.setFill(Color.color(rnm.nextDouble(),rnm.nextDouble(),rnm.nextDouble()));
                rectArray[i][j] = r;
                gridPane.add(r,i,j,1,1);
            }
        }

        /** setting up the activity of the squares on the board */
        pole[][] poleArray = new pole[n][m];
        for (int i = 0; i<n; i++){
            for (int j = 0; j<m; j++){
                //the corners
                if (i==0 && j==0){
                    pole pol = new pole(rectArray[i][j],i,j,rectArray[n-1][j],rectArray[1][j],rectArray[i][m-1],rectArray[i][1],k,p,gridPane);
                    poleArray[i][j]=pol;
                }
                else if (i==0 && j==m-1){
                    pole pol = new pole(rectArray[i][j],i,j,rectArray[n-1][j],rectArray[1][j],rectArray[i][m-2],rectArray[i][0],k,p,gridPane);
                    poleArray[i][j]=pol;
                }
                else if (i==n-1 && j==0){
                    pole pol = new pole(rectArray[i][j],i,j,rectArray[n-2][j],rectArray[0][j],rectArray[i][m-1],rectArray[i][1],k,p,gridPane);
                    poleArray[i][j]=pol;
                }
                else if (i==n-1 && j==m-1){
                    pole pol = new pole(rectArray[i][j],i,j,rectArray[n-2][j],rectArray[0][j],rectArray[i][m-2],rectArray[i][0],k,p,gridPane);
                    poleArray[i][j]=pol;
                }
                //the edges
                else if (i==0 && j!=0 && j!=m-1){
                    pole pol = new pole(rectArray[i][j],i,j,rectArray[n-1][j],rectArray[1][j],rectArray[i][j-1],rectArray[i][j+1],k,p,gridPane);
                    poleArray[i][j]=pol;
                }
                else if (i==n-1 && j!=0 && j!=m-1){
                    pole pol = new pole(rectArray[i][j],i,j,rectArray[n-2][j],rectArray[0][j],rectArray[i][j-1],rectArray[i][j+1],k,p,gridPane);
                    poleArray[i][j]=pol;
                }
                else if (j==0 && i!=0 && i!=n-1){
                    pole pol = new pole(rectArray[i][j],i,j,rectArray[i-1][j],rectArray[i+1][j],rectArray[i][m-1],rectArray[i][1],k,p,gridPane);
                    poleArray[i][j]=pol;
                }
                else if (j==m-1 && i!=0 && i!=n-1){
                    pole pol = new pole(rectArray[i][j],i,j,rectArray[i-1][j],rectArray[i+1][j],rectArray[i][m-2],rectArray[i][0],k,p,gridPane);
                    poleArray[i][j]=pol;
                }
                //the fill
                else if (j>0 && j<m-1 && i>0 && i<n-1){
                    pole pol = new pole(rectArray[i][j],i,j,rectArray[i-1][j],rectArray[i+1][j],rectArray[i][j-1],rectArray[i][j+1],k,p,gridPane);
                    poleArray[i][j]=pol;
                }

            }
        }
        /** setting up the stage */
        Scene scene = new Scene(gridPane,n*50, m*50);
        stage.setScene(scene);
        stage.setTitle("Simulation");
        stage.show();

        /** adding the pausing function */
        scene.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                int eventI = (int)(event.getSceneX()/50);
                int eventJ = (int)(event.getSceneY()/50);
                pole x = poleArray[eventI][eventJ];
                if(x.running){
                    x.stopThread();
                    if(eventI==0){
                        poleArray[n-1][eventJ].topPoleRunning = false;
                    }
                    else {
                        poleArray[eventI-1][eventJ].topPoleRunning = false;
                    }
                    if(eventI==n-1){
                        poleArray[0][eventJ].botPoleRunning = false;
                    }
                    else {
                        poleArray[eventI+1][eventJ].botPoleRunning = false;
                    }
                    if(eventJ==0){
                        poleArray[eventI][m-1].leftPoleRunning = false;
                    }
                    else {
                        poleArray[eventI][eventJ-1].leftPoleRunning = false;
                    }
                    if (eventJ==m-1){
                        poleArray[eventI][0].rightPoleRunning = false;
                    }
                    else {
                        poleArray[eventI][eventJ+1].rightPoleRunning = false;
                    }
                }
                else{
                    x.startThread();
                    if(eventI==0){
                        poleArray[n-1][eventJ].topPoleRunning = true;
                    }
                    else {
                        poleArray[eventI-1][eventJ].topPoleRunning = true;
                    }
                    if(eventI==n-1){
                        poleArray[0][eventJ].botPoleRunning = true;
                    }
                    else {
                        poleArray[eventI+1][eventJ].botPoleRunning = true;
                    }
                    if(eventJ==0){
                        poleArray[eventI][m-1].leftPoleRunning = true;
                    }
                    else {
                        poleArray[eventI][eventJ-1].leftPoleRunning = true;
                    }
                    if (eventJ==m-1){
                        poleArray[eventI][0].rightPoleRunning = true;
                    }
                    else {
                        poleArray[eventI][eventJ+1].rightPoleRunning = true;
                    }
                }
            }
        });
        /** starting the threads */
        for (int i = 0; i<n; i++){
            for (int j = 0; j<m; j++){
                poleArray[i][j].setDaemon(true);
                poleArray[i][j].start();
            }
        }
    }
    /**
     * the main function that launches the program
     */
    public static void main(String[] args){
        if (args.length==4){
            try {launch("--arg[0]="+args[0], "--arg[1]="+args[1],"--arg[2]="+args[2],"--arg[3]="+args[3]);}catch (Exception e){}
        }
        else {
            System.out.println("you should enter 4 arguments");
            System.exit(0);
        }
    }
}