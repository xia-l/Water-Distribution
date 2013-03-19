package model;

import java.util.ArrayList;

import buildings.Node;
import buildings.Pipe;

public class WaterGraph {
private ArrayList<Node> nodes;
private ArrayList<Pipe> pipes;

public WaterGraph()
{
	
}

public void addNode(Node node)
{
	this.nodes.add(node);
}

public void addPipe(Pipe pipe)
{
	this.pipes.add(pipe);
}

public void removeNode(Node node)
{
	this.nodes.remove(node);
	
}

public void removePipe(Pipe pipe)
{
	pipe.getFrom().removePipe(pipe);
	pipe.getTo().removePipe(pipe);
	this.pipes.remove(pipe);
}

public void removePipe(Node node)
{
	for(int i=0; i < this.pipes.size(); i++)
	{
		if(this.pipes.get(i).getFrom().id.equals(node.id) || this.pipes.get(i).getTo().id.equals(node.id))
		{
			this.pipes.remove(i);
			this.pipes.trimToSize();
		}
	}
}
}
