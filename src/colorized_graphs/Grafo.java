package Graphs_coloridos;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Graph {
	int nVertexs;
	int narcs;
	Map<Integer, Vertex> adjagencymatrix;
	ArrayList<Vertex> arrayVertexs;

	public Graph(int number_Vertexs){
		this.nVertexs = number_Vertexs;
		this.adjagencymatrix = new HashMap<>();
		for(int i = 0; i < number_Vertexs; i++){
			adjagencymatrix.put(i, new Vertex());
		}
	}

	public Graph(){
		this.adjagencymatrix = new HashMap<>();
		this.arrayVertexs = new ArrayList<>();
	}

	public void addSoloVertex(Vertex Vertex){
		int tamanho = adjagencymatrix.size();
		adjagencymatrix.put(tamanho, Vertex);
		arrayVertexs.add(Vertex);
		nVertexs++;
	}

	public Vertex getVertex(int posicao){
		return this.arrayVertexs.get(posicao);
	}

	public void addArc(int Vertex1, int Vertex2){
		adjagencymatrix.get(Vertex1).addNeighbor(Vertex2);
		adjagencymatrix.get(Vertex2).addNeighbor(Vertex1);
	}

	public void removeArc(int Vertex1, int Vertex2){
		adjagencymatrix.get(Vertex1).removeNeighbor(Vertex2);
		adjagencymatrix.get(Vertex2).removeNeighbor(Vertex1);
	}

	public int ctArcs(){
		int ct = 0;
		for(Vertex Vertex: adjagencymatrix.values()){
			ct += Vertex.ctNeighbors();
		}
		return ct/2;
	}

	public int getnarcs(){
		return narcs;
	}

	public int getnVertexs(){
		return nVertexs;
	}

	public void print(){
		System.out.println("Printing the graph: ");
		for(Map.Entry<Integer, Vertex> ent : adjagencymatrix.entrySet()){
			System.out.println(this.getVertex(ent.getKey()).var_in_it + "->	");
			Vertex a = ent.getValue();
			for(Integer i : ent.getValue()){
				System.out.println(this.getVertex(i).var_in_it + " ");
			}
			System.out.println();
		}
	}

	public void printWithColors(){
		ArrayList<String> colores_dif = new ArrayList<>();
		System.out.println("Graph with colors (after painted): ");
		for(Map.Entry<Integer, Vertex> ent : adjagencymatrix.entrySet()){
			System.out.print(ent.getKey() + " color: ");
			Vertex a = ent.getValue();
			if(colores_dif.size() == 0){
				colores_dif.add(a.getcolor());
			}
			else{
				if(!colores_dif.contains(a.getcolor())){
					colores_dif.add(a.getcolor());
				}
			}
			System.out.println(a.getcolor() + " | name(virtual): " + a.getregname() + " | variable: " + a.getVarInIt());
			System.out.println();
		}
		System.out.println("Minimal quantity of physical registers needed: " + colores_dif.size());
	}

	public void colorize(ArrayList<String> colores) {
		ArrayList<String> colores_perto = new ArrayList<>();
		ArrayList<String> possibly_colors = new ArrayList<>();
		for(int i = 0; i < this.nVertexs; i++) {
			Vertex vhelper = new Vertex();
			vhelper = this.getVertex(i);
			for(int j = 0; j < this.nVertexs; j++){
				Vertex vhelpermore = new Vertex();
				vhelpermore = this.getVertex(j);
				if(vhelper.isNeighbor(j)){
					colores_perto.add(vhelpermore.getcolor());
				}
			}
			if(colores_perto.size() == 0){
				vhelper.changeColor(colores.get(0));
			}
			else{
				for(int j = 0; j < colores_perto.size(); j++){
					for(int k = 0; k < colores.size(); k++){
						if(colores_perto.get(j) != colores.get(k)){
							possibly_colors.add(colores.get(k));
						}
					}
				}
				if(possibly_colors.size() >= 1){
					possibly_colors.removeAll(colores_perto);
				}
				if(possibly_colors.size() >= 1){
					vhelper.changeColor(possibly_colors.get(0));
				}
				else{
					vhelper.changeColor("without_color");
				}
			}
			possibly_colors.removeAll(possibly_colors);
			colores_perto.removeAll(colores_perto);
		}
	}

}
