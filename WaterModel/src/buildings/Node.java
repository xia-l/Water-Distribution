package buildings;

public abstract class Node {
public String id;
public boolean visisted = false;
public Node parent; 

public boolean isSource = false;

 public abstract boolean removePipe(Pipe p);

}