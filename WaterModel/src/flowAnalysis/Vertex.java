package flowAnalysis;

import java.util.ArrayList;

public class Vertex {
    private static int counter;
    public int id;
    public String name;
    public ArrayList<Edge> edges;
    public Vertex parent;
    public boolean visisted = false;
    
    public Vertex()
    {
        this.id = counter++;
        edges = new ArrayList<Edge>();
    }

    public void ResetCounter()
    {
        counter = 0;
    }

    public String ToString()
    {
        return this.id +","+this.name;
    }
}
