/**
 * class that creates a tree, and defines all its functions
 *  */
public class Tree<T extends Comparable<T>>{
    private TreeElement<T> root;
    /**
     * constructor
     */
    public Tree() {
        root = null;
    }
    /**
     * defining the insert functionality
     * @param element element inserted into the tree
     */
    private void ins(T element){   
        TreeElement<T> z = new TreeElement<T>(element);     
        TreeElement<T> y = null;
        TreeElement<T> x = root;
        while (x!=null) {
            y=x;
            if (element.compareTo(x.value)<0){x = x.left;}
            else {x = x.right;}
        }
        z.parent = y;
        if (y==null) {root = z;}
        else if (element.compareTo(y.value)<0){
            y.left = z;
        }
        else {
            y.right = z;
        }
    }
    /**
     * public function that inserts an element into the tree
     * @param element element inserted into the tree
     */
    public void insert(T element) {ins(element);}
    /**
     * defines the searching functionality
     * @param element element searched for
     * @param x working element, initially the root
     * @return boolean, whether the element is in the tree
     */
    private boolean isElem(T element, TreeElement<T> x){
        if(x==null){
            return false;
        }
        if(element.compareTo(x.value)==0){
            return true;
        }
        if(element.compareTo(x.value)<0){
            return isElem(element, x.left);
        }
        else {
            return isElem(element, x.right);
        }
    }
    /**
     * boolean function that returns whether an element is in the tree or not
     * @param element searched element
     * @return whether element is in the tree or not
     */
    public boolean search(T element) {return isElem(element,root);}
    /**
     * function returning the minimum value attatched to an element
     * @param x given element
     * @return minimal value
     */
    private TreeElement<T> Tminimum (TreeElement<T> x){
        while (x.left!=null){
            x = x.left;
        }
        return x;
    }
    /**
     * function defining the drawing functionality
     * @param x element drawn on screen
     * @return string with the tree elements
     */
    private String toS(TreeElement<T> x){
        if (x!=null){
            return "("+x.value+" : "+toS(x.left)+" : "+toS(x.right) + ")";
        }
        return "()";
    }
    /**
     * function that draws the whole tree
     * @return string containing the tree elements
     */
    public String draw() {return toS(root);}

    /**
     * function defining the delete functionality
     * @param r tree
     * @param z value to be deleted
     * @return tree without the value
     */
    private TreeElement<T> TDelete (TreeElement<T> r, T z){
        if(r == null){
            return r;
        }
        if (z.compareTo(r.value)<0) {r.left = TDelete(r.left,z);}
        else if (z.compareTo(r.value)>0) {r.right = TDelete(r.right,z);}
        else {
            if (r.left == null && r.right == null){return null;}
            else if (r.left==null) {return r.right;}
            else if (r.right==null) {return r.left;}
            TreeElement<T> temp = Tminimum(r.right);
            r.value = temp.value;
            r.right = TDelete(r.right,temp.value);
        }
        return r;
    } 
    /**
     * function that removes an element from the tree
     * @param element element removed from tree
     */
    public void delete(T element) {root = TDelete(root,element);}
}