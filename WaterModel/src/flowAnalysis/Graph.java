package flowAnalysis;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;

import model.WaterGraph;

public class Graph {

	private WaterGraph waterGraph;
	private ArrayList<Edge> edges;
	private ArrayList<Vertex> vertices;
	
	public Graph(WaterGraph waterGraph)
	{
		this.waterGraph = waterGraph;
		this.vertices = new ArrayList<Vertex>();
		this.edges = new ArrayList<Edge>();
		convertWaterGraph();
	}
	
	public void convertWaterGraph()
	{
		//Remember, using PrimodialSource and Sink to connect the multiple sources and sinks with edges of infinite capacity. 
		//Convert WaterGraph to normal graph. 
	}
	
	public ArrayList<Edge> getEdges()
	{
		return this.edges;
	}
	
	public ArrayList<Vertex> getVertices()
	{
		return this.vertices;
	}
	
    public ArrayList<Edge> BFS()
    {
    	Vertex source = new Vertex();
    	source.id = 0;
    	source.name = "PrimodialSource";
    	source.parent = null;
    	
    	Vertex sink = new Vertex();
    	sink.id=1;
    	sink.name = "PrimodialSink";
    	sink.parent = null;
    	
    	PriorityQueue<Vertex> queue = new PriorityQueue<Vertex>(); 
        queue.add(source);
        while (queue.size() > 0) //While queue is not empty
        {           
           Vertex current = queue.poll();
           current.visisted = true;
           if (current.id == sink.id) 
           { 
               ResetVisisted();
               return GetPath(current); 
           }
           for(Edge e : current.edges)
           {
        	   Vertex next = e.to;
               if (e.flow < e.capacity && !next.visisted) 
               {
                   next.parent = current;
                   queue.add(next);
               }
           }
        }
        ResetVisisted();
        return null;
    }
    
    private void ResetVisisted()
    {
        for(Vertex v : this.vertices)
        {
            v.visisted = false;
        }
    }
    private ArrayList<Edge> GetPath(Vertex v)
    {
        ArrayList<Edge> path = new ArrayList<Edge>();
        Vertex current = v;
        while (current.parent != null) 
        {
            String key = GetKey(current.parent.id, current.id); 
            for(Edge e : this.edges)
            {
            	if(e.GetKey().equals(key))
            	{
                    path.add(e); 
                    current = current.parent; 
            	}
            }
        }
        return path;
    }
    
    public String GetKey(int id1, int id2)
    {
        return id1 + "|" + id2;
    }
    
    public void Reset()
    {
        for (Vertex v : this.vertices)
        {
            v.ResetCounter();
        }
        this.vertices = null;
        this.edges = null;
        
    }
	
}
