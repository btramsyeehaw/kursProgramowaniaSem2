import java.awt.*;
import java.awt.event.*;

class myWindowAdapter extends WindowAdapter {
  public void windowClosing(WindowEvent e) { System.exit(0); }
}

class myButton extends Button {
  myButton(myProgram p){
    super("Zatwierdz");
    addActionListener(new myButtonAdapter(p));
  }
}
class myButtonAdapter implements ActionListener {
  myProgram p;
  myButtonAdapter(myProgram p) {this.p = p;}
  public void actionPerformed(ActionEvent e) {p.action();}
}

class myFrame extends Frame {
  myFrame(myProgram p){
    super("Lista4");
    setBounds(100,100,1280,720);
    addWindowListener(new myWindowAdapter());
    setFont(new Font(Font.MONOSPACED, Font.BOLD,20));
    setLayout(new BorderLayout());
    myButton action = new myButton(p);
    p.result = new TextArea(30,100);
    p.programdata = new TextField(100);
    add(p.programdata,BorderLayout.NORTH);
    add(action,BorderLayout.SOUTH);
    add(p.result,BorderLayout.CENTER);
    setResizable(true);
  }
}