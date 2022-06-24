import java.io.*;
import java.net.*;

/**
 * thread used for the tree
 * @author Bartosz Trams
 */
public class TreeThread extends Thread {
    private Socket socket;
    /**
     * constructor
     * @param socket
     */
    public TreeThread(Socket socket){
        this.socket = socket;
    }
    /**
     * main function
     */
    public void run() {
        try {
            //general setup
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            Integer treeData;
            String clientData = "";
            
            treeData = (Integer) in.readObject(); //integer representing which data type the tree is based upon
            //functionality of the integer tree
            if(treeData==1){
                //creation of a new tree
                Tree<Integer> intTree = new Tree<Integer>();
                //while loop that is true while the client doesn't exit the program
                while(clientData!="0"){
                    //parsing the client data into actionData - which action the program takes, and data - the elements inserted/searched/deleted
                    clientData = (String) in.readObject();
                    char actionData = clientData.charAt(0);
                    Integer data = 0;
                    if (clientData.substring(2)!=""){
                        data = Integer.parseInt(clientData.substring(2));
                    }
                    //searching an element in the tree
                    if(actionData == '1'){
                        if(intTree.search(data)){
                            out.writeObject("Tree contains "+data);
                        } else {out.writeObject("Tree doesn't contain "+data);}
                    }
                    //inserting an element into the tree
                    else if(actionData == '2'){
                        if (intTree.search(data)){ out.writeObject(data+" was already in the tree");}
                        else {
                        intTree.insert(data);
                        out.writeObject(data+" inserted into the tree");}
                    }
                    //removing an element from the tree
                    else if(actionData == '3'){
                        if (intTree.search(data)){
                            intTree.delete(data);
                            out.writeObject(data+" deleted from the tree");
                        }
                        else {
                            out.writeObject(data+" not in tree in the first place");}
                    }
                    //drawing the tree
                    else if(actionData == '4'){
                        String tree = intTree.draw();
                        out.writeObject("drawing the tree: \n"+tree);
                    }
                    //if the user inputs a number beyond 4, the process repeats
                    else {
                        out.writeObject("wrong number, try again.");
                    }
                }
            }
            //functionality of the tree for the double type, see int tree for details
            else if (treeData==2){
                Tree<Double> doubleTree = new Tree<Double>();
                while(clientData!="0"){
                    clientData = (String) in.readObject();
                    char actionData = clientData.charAt(0);
                    Double data = 0.0;
                    if (clientData.substring(2)!=""){
                        data = Double.parseDouble(clientData.substring(2));
                    }
                    if(actionData == '1'){
                        if(doubleTree.search(data)){
                            out.writeObject("Tree contains "+data);
                        } else {out.writeObject("Tree doesn't contain "+data);}
                    }
                    else if(actionData == '2'){
                        doubleTree.insert(data);
                        out.writeObject(data+" inserted into the tree");
                    }
                    else if(actionData == '3'){
                        if (doubleTree.search(data)){
                            doubleTree.delete(data);
                            out.writeObject(data+" deleted from the tree");
                        }
                        else {
                            out.writeObject(data+" not in tree in the first place");}
                    }
                    else if(actionData == '4'){
                        String tree = doubleTree.draw();
                        out.writeObject("drawing the tree: \n"+tree);
                    }
                    else {
                        out.writeObject("wrong number, try again.");
                    }
                }
            }
            //functionality of the tree for the string type, see int tree for details
            else if (treeData==3){
                Tree<String> stringTree = new Tree<String>();
                while(clientData!="0"){
                    clientData = (String) in.readObject();
                    char actionData = clientData.charAt(0);
                    String data = clientData.substring(2);
                    if(actionData == '1'){
                        if(stringTree.search(data)){
                            out.writeObject("Tree contains "+data);
                        } else {out.writeObject("Tree doesn't contain "+data);}
                    }
                    else if(actionData == '2'){
                        stringTree.insert(data);
                        out.writeObject(data+" inserted into the tree");
                    }
                    else if(actionData == '3'){
                        if (stringTree.search(data)){
                            stringTree.delete(data);
                            out.writeObject(data+" deleted from the tree");
                        }
                        else {
                            out.writeObject(data+" not in tree in the first place");}
                    }
                    else if(actionData == '4'){
                        String tree = stringTree.draw();
                        out.writeObject("drawing the tree: \n"+tree);
                    }
                    else {
                        out.writeObject("wrong number, try again.");
                    }
                }
            }
            //closing the socket after the program
            socket.close();
        } catch(Exception e){}
    }
}
