package GraphTheory;

class Edge{

    private Vertex v1; 
    private Vertex v2;

    Edge(Vertex v1, Vertex v2)
    {
        this.v1 = v1;
        this.v2 = v2;
    }

    public Vertex getV2() {
        return v2;
    }

    public Vertex getV1() {
        return v1;
    }

    @Override
    public String toString() {
        return '('+v1.toString()+','+v2.toString()+')';
    }
}
