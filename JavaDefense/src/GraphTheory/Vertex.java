package GraphTheory;

class Vertex {
    //this class should only be used by Graph and Edge


    private Integer idVertex; //must be unique

    Vertex(int id)
    {
        idVertex=id;
    }

    @Override
    public int hashCode() {
        return idVertex.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        Vertex temp = (Vertex)obj;
        return idVertex.equals(temp.idVertex);
    }

    @Override
    public String toString() {
        return idVertex.toString();
    }

    public int getIdVertex() {
        return idVertex;
    }
}