/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author firzanahzaidi
 */
public class Q4Vertex<T extends Comparable<T>, N extends Comparable<N>> {
    
    T vertexInfo; //holds the information associated with the vertex
    int indeg; //representing the indegree of the vertex
    int outdeg; //representing the outdegree of the vertex
    Q4Vertex<T, N> nextVertex; //next vertex in a data structure
    Q4Edge<T, N> firstEdge; //first edge connected to the vertex

    public Q4Vertex() { //default constructor 
        this.vertexInfo = null;
        this.nextVertex = null;
    }

    public Q4Vertex(T vertexInfo, Q4Vertex<T, N> nextVertex) {
        this.vertexInfo = vertexInfo;
        this.indeg = 0;
        this.outdeg = 0;
        this.nextVertex = nextVertex;
    }

}
