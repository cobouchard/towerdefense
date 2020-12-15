package GraphTheory;

class Edge{

    private Vertex v1; 
    private Vertex v2;

    Edge(Vertex v1, Vertex v2)
    {
        this.v1 = v1;
        this.v2 = v2;
    }

    @Override
	public int hashCode() {
		return (v1.hashCode()+v2.hashCode())*97;
	}

	@Override
	public boolean equals(Object obj) {
		Edge other = (Edge) obj;
		return ( other.v1.equals(v1) && other.v2.equals(v2) ) || ( other.v1.equals(v2) && other.v2.equals(v1) );
		
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
