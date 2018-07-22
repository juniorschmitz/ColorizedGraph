package Graphs_coloridos;
import java.util.ArrayList;
import java.util.Iterator;

public class Vertex implements Iterable<Integer> {
	boolean visited;
	String color;
	String regname;
	String var_in_it;
	ArrayList<Integer> neighbors;
	ArrayList<Integer> used_line;


	public Vertex(){
		this.visited = false;
		this.neighbors = new ArrayList<>();
		this.color = "without_color";
		used_line = new ArrayList<>();
	}

	public Vertex(String color, String regname, String var_in_it){
		this.visited = false;
		this.neighbors = new ArrayList<>();
		this.color = color;
		this.regname = regname;
		this.var_in_it = var_in_it;
		used_line = new ArrayList<>();
	}

	public void addUsedLine(int line){
		this.used_line.add(line);
	}

	public void changeVarInIt(String var_in_it){
		this.var_in_it = var_in_it;
	}

	public String getVarInIt(){
		return this.var_in_it;
	}

	public void changeColor(String color){
		this.color = color;
	}

	public void descolorize(){
		this.color = "without_color";
	}

	public boolean wasVisited(){
		return visited;
	}

	public void removeNeighbor(int Vertex){
		this.neighbors.remove(Vertex);
	}

	public int ctNeighbors(){
		return this.neighbors.size();
	}

	public boolean isNeighbor(int Vertex){
		return this.neighbors.contains(Vertex);
	}

	public void addNeighbor(int Vertex){
		this.neighbors.add(Vertex);
	}

	public void setvisited(boolean visited){
		this.visited = visited;
	}

	public String getcolor(){
		return this.color;
	}

	public String getregname(){
		return this.regname;
	}

	@Override
	public Iterator<Integer> iterator(){
		return this.neighbors.iterator();
	}
}
