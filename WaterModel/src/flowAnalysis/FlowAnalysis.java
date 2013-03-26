package flowAnalysis;

import java.util.ArrayList;
import java.util.PriorityQueue;

import model.WaterGraph;

public class FlowAnalysis {

	private Graph graph;
	private float maxFlow = 0f;
	private float minCut = 0f;
	public FlowAnalysis(WaterGraph graph)
	{
		this.graph = new Graph(graph);
	}
	
	public float getMaxFlow()
	{
		return this.maxFlow;
	}
	
	public float getMinCut()
	{
		return this.minCut;
	}
	
	   public void FordFulkersonAlgorithm()
       {


           for(int i=0; i < this.graph.getEdges().size(); i++)
           {
               this.graph.getEdges().get(i).flow = 0;
           }
           ArrayList<Edge> path = this.graph.BFS();
           while (path != null)
           {
               this.maxFlow += Augment(path); 
               path = this.graph.BFS();
           }
           MinCut(this.graph.getVertices().get(0));
       }
	   
       private float Augment(ArrayList<Edge> path)
       {
           float bottleneck = Float.MAX_VALUE;
           for (Edge edge : path)
           {
               if ((edge.capacity - edge.flow) < bottleneck) 
                   bottleneck = (edge.capacity - edge.flow);
           }

           for (Edge edge : path)
           {
               String keyResidual = this.graph.GetKey(edge.to.id, edge.from.id); 
               Edge edgeResidual = null;
               for(Edge e : this.graph.getEdges())
               {
            	   if(e.GetKey().equals(keyResidual))
            	   {
            		   edgeResidual = e;
            	   }
               }
               edge.flow += bottleneck; 
               edgeResidual.flow = -edge.flow;  
               }
           return bottleneck;
       }
       
       private void MinCut(Vertex source)
       {
           //Almost identical to BFS.
           PriorityQueue<Vertex> queue = new PriorityQueue<Vertex>();
           ArrayList<Vertex> minCutVertices = new ArrayList<Vertex>();
           ArrayList<Edge> minCutEdges = new ArrayList<Edge>();
           queue.add(source);

           while (queue.size() > 0)
           {
               Vertex current = queue.poll();
               if (!current.visisted)
               {
                   minCutVertices.add(current);
                   current.visisted = true;
                   for (Edge edge : current.edges)
                   {
                       Vertex next = edge.to;
                       if (edge.flow < edge.capacity && !next.visisted)
                       {
                           queue.add(next);
                           minCutEdges.add(edge);
                       }
                  
                   }
               }
           }

           
          ArrayList<Edge> minCutResult = new ArrayList<Edge>(); 
          ArrayList<Integer> vertexid = new ArrayList<Integer>();
          
          for( Vertex v : minCutVertices)
          {
        	  vertexid.add(v.id);
          }

           ArrayList<Integer> keys = new ArrayList<Integer>();
           for(Vertex v : minCutVertices)
           {
               keys.add(v.id);
           }

           ArrayList<String> edgeKeys = new ArrayList<String>();
           for(Edge edge : minCutEdges)
           {
               edgeKeys.add(edge.name);
           }

           this.graph.Reset();
           this.graph.convertWaterGraph();
           
           for (int id : vertexid)
           {
               Vertex vertex = this.graph.getVertices().get(id);
               for (Edge edge : vertex.edges)
               {
                   if (keys.contains(edge.to.id))
                       continue;
                   if (edge.capacity > 0 && !edgeKeys.contains(edge.name))
                   {
                       minCutResult.add(edge);
                   }
               }
           }

           for (Edge edge : minCutResult)
           {
               this.minCut += edge.capacity;
           }
       }
}
