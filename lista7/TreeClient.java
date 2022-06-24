import java.net.*;
import java.io.*;
import java.util.InputMismatchException; 

/**
 * the client application to use the tree
 * @author Bartosz Trams
 */

public class TreeClient {
/**
 * main function of the program
 * @param args
 */ 
    public static void main(String[] args) {

    try  {
        /**
         * creation of the socket, general setup
         */
        Socket socket = new Socket("localhost", 3000); 
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        Console console = System.console();
        Integer actionTypeData; //integer, represents type of action a user takes on the tree (1: search, 2: insert, 3: delete, 4: draw, 0: exit)
        Integer treeData; //integer, represents which type of tree the user is working on (1: int, 2: double, 3: string, 0: exit)
        String data; //the data that the action is taken on
        System.out.println("Select the type of tree you'd want to work on:\n1. a tree of integers\n2. a tree of floating point numbers\n3. a tree of strings\n0. exit the program");
        try{
            //getting the initial info about the tree
            treeData = Integer.parseInt(console.readLine("Type in a number: "));
            //if treeData equals 0, the program exits
            if (treeData==0){ 
                System.out.println("Exiting the program.");
                socket.close();
            }
            //main portion of the program
            if (treeData>0 && treeData<4){
                //the server gets the information about the type of tree the user created
                out.writeObject(treeData);
                while (true) {
                    //resets to default value
                    actionTypeData = 0;
                    data = "";
                    //user selects action
                    System.out.println("------------------------------\nSelect what you'd want to do now.\n1. search whether an element is in the tree\n2. insert an element into the tree\n3. delete an element from the tree\n4. draw the whole tree\n0. exit the program.");
                    actionTypeData = Integer.parseInt(console.readLine("Type in a number: "));
                    //exiting the while loop and thus the program if the user typed in "0"
                    if (actionTypeData==0){
                        System.out.println("Exiting the program.");
                        break;
                    }
                    //if an action has an element attatched to it, the user inputs the element, program sends information to the server
                    if (actionTypeData>0 && actionTypeData<4){
                        data = (String)console.readLine("Type in the element: ");
                        out.writeObject(Integer.toString(actionTypeData)+"."+data);
                    }
                    else if (actionTypeData ==4)
                    {out.writeObject(Integer.toString(actionTypeData)+".");}
                    //handling user inputting a random number
                    else {out.writeObject("9.");}
                    //client recieves information about the action taken
                    try{
                        System.out.println(in.readObject());
                    } catch (Exception ex) {}
                }
            }
            else {
                System.out.println("number out of range, ending program");
            }
            } catch(InputMismatchException ex){
                System.out.println("you typed in a wrong data type");
            }
        socket.close();

        } catch (UnknownHostException ex) {
            System.out.println("Server not found: " + ex.getMessage());
 
        } catch (IOException ex) {
            System.out.println("I/O error: " + ex.getMessage());
        }

    }
}