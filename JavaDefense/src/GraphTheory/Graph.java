package GraphTheory;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashSet;


public class Graph {


    //The graph is defined normally by a set of vertices and a list of edges
    //It's in fact here a pseudo-multigraph
    protected HashSet<Vertex> vertices;
    protected HashSet<Edge> edges;

    Graph()
    {
        vertices = new HashSet<>();
        edges = new HashSet<>();
    }

    public boolean addVertex(int id)
    {
        return vertices.add(new Vertex(id));
    }



    public boolean addVertices(HashSet<Integer> list_of_ids)
    {
        //return true if every Vertices had been added, false otherwise
        //don't stop to add Vertices if one is not added

        boolean test = true;

        for(Integer i : list_of_ids)
        {
            if(!addVertex(i))
                test=false;
        }
        return test;
    }



    public boolean addEdge(int id_start, int id_end)
    {
        Vertex v1 = null;
        Vertex v2 = null;

        for(Vertex v : vertices)
        {
            if(id_start==v.getIdVertex())
                v1=v;

            if(id_end==v.getIdVertex())
                v2=v;
        }

        if(v1==null || v2==null)
            return false;
        else
            return addEdge(v1,v2);

    }
    
    private boolean addEdge(Vertex v1, Vertex v2)
    {
        return edges.add(new Edge(v1,v2));
    }
    
    

    public void displayVertices()
    {
        System.out.println("All vertices :");

        String temp="";
        for(Vertex v : this.vertices)
            temp+=v.toString()+';';

        System.out.print(temp);

    }

    public void displayEdges()
    {
        System.out.println("All edges :");

        String temp="";
        for(Edge e : this.edges)
            temp+=e.toString()+';';

        System.out.print(temp);
    }

    public Graph BreadthFirstSearch(Vertex start)
    {
        Graph search = new Graph();
        search.vertices=vertices;

        ArrayList<Vertex> queue = new ArrayList<>();

        queue.add(start);

        while(!queue.isEmpty())
        {
            Vertex temp = queue.get(0);
            //int position = file.size()-1;
            for(Edge e : edges)
            {
                if(e.getV1().equals(temp))
                {
                    Vertex neighbour = e.getV2();
                    queue.add(neighbour);
                    search.addEdge(temp,neighbour);
                }
            }

            queue.remove(0);
        }

        return search;
    }

    //pas sûr qu'on l'utilise
    public void saveGraph(String name)
    {
        File file = new File(name+".graph");
        try(FileOutputStream fop = new FileOutputStream(file))
        {

            for(Edge e : this.edges)
            {
                String edge_to_write="";
                edge_to_write += (e.getV1().toString()+e.getV2().toString()+'\n');
                System.out.println(edge_to_write);
                fop.write(edge_to_write.getBytes());
            }
            System.out.println("sauvegarde");
        }
        catch(Exception e)
        {

        }
    }
    
    public static Graph createGraphFromMatrix(int[][] matrix) 
    {
    	Graph foo = new Graph();
    	
    	//ajout des sommets inutile
    	for(int ligne=0; ligne!= matrix.length; ligne++)
    		for(int colonne=0; colonne!= matrix.length; colonne++)
    			if(matrix[ligne][colonne]>0)
    				foo.addVertex(matrix[ligne][colonne]);
    	
    	for(int ligne=0; ligne!= matrix.length; ligne++)
    		for(int colonne=0; colonne!= matrix.length; colonne++) 
    		{
    			int id1 = matrix[ligne][colonne];
    			if(id1>0)
    			{
    				//on regarde quels sont les cases adjacentes qui sont aussi des sommets
    				try 
    				{
    					int id2 = matrix[ligne+1][colonne];
    					if(id2>0) 
    					{
    						foo.addEdge(id1, id2);
    					}
    				}
    				catch(ArrayIndexOutOfBoundsException e) 
    				{
    					// dans le vide
    				}
    				
    				try 
    				{
    					int id2 = matrix[ligne-1][colonne];
    					if(id2>0) 
    					{
    						foo.addEdge(id1, id2);
    					}
    				}
    				catch(ArrayIndexOutOfBoundsException e) 
    				{
    					// dans le vide
    				}
    				
    				try 
    				{
    					int id2 = matrix[ligne][colonne+1];
    					if(id2>0) 
    					{
    						foo.addEdge(id1, id2);
    					}
    				}
    				catch(ArrayIndexOutOfBoundsException e) 
    				{
    					// dans le vide
    				}
    				
    				try 
    				{
    					int id2 = matrix[ligne][colonne-1];
    					if(id2>0) 
    					{
    						foo.addEdge(id1, id2);
    					}
    				}
    				catch(ArrayIndexOutOfBoundsException e) 
    				{
    					// dans le vide
    				}
    			}
    		}
    	
    	return null;
    }

    public static void main(String[] args)
    {
        Graph test = new Graph();
        test.addVertex(1);
        test.addVertex(2);
        test.addVertex(3);
        test.addVertex(4);
        test.addVertex(5);
        test.addVertex(6);
        test.addVertex(7);

        test.addEdge(1,2);
        test.addEdge(1,3);
        test.addEdge(2,3);
        test.addEdge(3,6);
        test.addEdge(4,6);
        test.addEdge(2,4);
        test.addEdge(2,7);
        test.addEdge(4,7);
        test.addEdge(4,5);
        test.addEdge(5,6);

        //test.saveGraph("sauvegarde");
    }

}
