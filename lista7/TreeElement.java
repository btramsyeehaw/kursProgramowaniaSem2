/**
 * the element from which a tree is made up of
 */
public class TreeElement<T extends Comparable<T>>{
    //value of the element
    T value;
    //the left subelement
    TreeElement<T> left;
    //the right subelement
    TreeElement<T> right;
    //the parent element
    TreeElement<T> parent;
    //constructor
    TreeElement(T value){
        this.value = value;
        left = null;
        right = null;
        parent = null;
    }
}

