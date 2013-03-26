package flowAnalysis;

public class Edge {
    
	public Vertex from;
    public Vertex to;
    public float capacity;    
    public String name;
    public float flow;

    public String ToString()
    {
        return from.id +"," + to.id + "," + capacity;
    }

    public String GetKey()
    {
    	return this.from + "|" + this.to;
    }
    public int CompareTo(Object obj)
    {
        Edge compare = ((Edge)obj);
        if (compare.from.id < this.from.id)
        {
            return 1;
        }
        else if (compare.from.id > this.from.id)
        {
            return -1;
        }
        return 0;
        
    }
}
