package model;

import java.util.ArrayList;

import com.google.gson.Gson;

import buildings.Node;
import buildings.Pipe;

public class WaterGraph {
private ArrayList<Node> nodes;
private ArrayList<Pipe> pipes;

public WaterGraph()
{
	
}

public ArrayList<Node> getNodes()
{
	return this.nodes;
}

public ArrayList<Pipe> getPipes()
{
	return this.pipes;
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
	for(int i=0; i < nodes.size(); i++)
	{
		Node n = nodes.get(i);
		if(n.id.equals(pipe.getFrom()) || n.id.equals(pipe.getTo()))
		{
			n.removePipe(pipe);
		}
	}
	this.pipes.remove(pipe);
}

public void removePipe(Node node)
{
	for(int i=0; i < this.pipes.size(); i++)
	{
		if(this.pipes.get(i).getFrom().equals(node.id) || this.pipes.get(i).getTo().equals(node.id))
		{
			this.pipes.remove(i);
			this.pipes.trimToSize();
		}
	}
}

public String toJSON()
{
Gson gson = new Gson();
	return gson.toJson(this);
}
}
