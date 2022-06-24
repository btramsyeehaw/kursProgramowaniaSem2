import java.awt.*;
import java.io.*;

public class myProgram {
    myFrame frame;
    TextArea result;
    TextField programdata;
    TextArea error;
    void action() {
      try {
        Process child = Runtime.getRuntime().exec("java TP "+programdata.getText());
        BufferedReader in = new BufferedReader(new InputStreamReader(child.getInputStream()));
        BufferedReader inErr = new BufferedReader(new InputStreamReader(child.getErrorStream()));
        String c;
        result.setText("");
        while ((c = in.readLine())!=null) result.append(c+"\n");
        in.close();
        while ((c = inErr.readLine())!=null) result.append("Error: "+c+"\n");
        inErr.close();
      }
      catch(IOException e){
        result.setText(e.getMessage());
      }
      catch(IllegalArgumentException e){
        result.setText(e.getMessage());
      }
    
    }
    
    public static void main(String[] args){
      myProgram p = new myProgram();
      p.frame = new myFrame(p);
      p.frame.setVisible(true);
    }
  }
