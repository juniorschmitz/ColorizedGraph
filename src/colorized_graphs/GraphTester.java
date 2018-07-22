package Graphs_coloridos;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class GraphTester {

	public static void main(String[] args) throws NumberFormatException, IOException {
		Graph Graph = new Graph();

		int physical_registers = 0;
		BufferedReader buff = new BufferedReader(new FileReader
				("src\\colorized_graphs\\entry.txt"));
		int ct_line = 0;
		while(buff.ready()){
			String line = buff.readLine();
			if(line.startsWith("#")){
				String[] reg_registers = line.split(" ");
				physical_registers = Integer.parseInt(reg_registers[2]);
				ct_line++;
			}
			else{
				String[] wordsf = line.split("\\t");
				if(wordsf[1].startsWith("ADD")){
					wordsf[1] = wordsf[1].replaceAll("\\s","");
					char[] aux = wordsf[1].toCharArray();
					String newword = "";
					for(int h = 0; h < aux.length; h++){
						newword += aux[h];
						if(h == 2) newword += " ";
					}
					wordsf[1] = newword;
				}
				String[] words = wordsf[1].split(" ");
				String reg_name, var_in_it;
				for(int i = 0; i < words.length; i++){
					if(words[i].startsWith("LOA")){
						var_in_it = words[i+ 1].substring(0, 1);
						reg_name = words[i+ 2];
						Vertex v = new Vertex("without_color", reg_name, var_in_it);
						Graph.addSoloVertex(v);
					}
					else if(words[i].equals("ADD")){
						String var_1 = words[i+1].substring(0, 1);
						String var_2 = words[i+1].substring(2,3);
						String var_3 = words[i+1].substring(4, 5);
						for(int k = 0; k < Graph.nVertexs; k++){
							Vertex vhelper = new Vertex();
							vhelper = Graph.getVertex(k);
							if(vhelper.getVarInIt().equals(var_1) || vhelper.getVarInIt().equals(var_2) ||
									vhelper.getVarInIt().equals(var_3)){
								vhelper.used_line.add(ct_line);
							}
						}
					}
				}
				ct_line++;
			}
		}
		buff.close();

		System.out.println("Quatidade maxima de registradores fisicos: " + physical_registers);
		System.out.println();
		ArrayList<String> colores = new ArrayList<>();
		ArrayList<String> coloresaux = new ArrayList<>();
		coloresaux.add("blue");
		coloresaux.add("pink");
		coloresaux.add("yellow");
		coloresaux.add("red");
		coloresaux.add("white");
		coloresaux.add("darkblue");
		for(int i = 0; i < physical_registers; i++) {
			colores.add(coloresaux.get(i));
		}

		boolean used_at_same_time = false;
		for(int i = 0; i < Graph.nVertexs; i++){
			Vertex vhelper = new Vertex();
			vhelper = Graph.getVertex(i);
			for(int j = 0; j < Graph.nVertexs; j++){
				Vertex vhelpermore = new Vertex();
				vhelpermore = Graph.getVertex(j);
				for(int k = 0; k < vhelper.used_line.size(); k++){
					if(vhelpermore.used_line.contains(vhelper.used_line.get(k))){
						used_at_same_time = true;
						break;
					}
				}
				if(used_at_same_time){
					used_at_same_time = false;
					if(i != j) vhelper.addNeighbor(j);
				}
			}
		}

		Graph.print();
		Graph.colorize(colores);
		Graph.printWithColors();
	}
}
