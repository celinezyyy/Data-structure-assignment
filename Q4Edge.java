/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author firzanahzaidi
 * @param <T>
 * @param <N>
 */

public class Q4Edge<T extends Comparable<T>, N extends Comparable<N>> {
    
    Q4Vertex<T, N> toVertex; //reference to the vertex the edge is directed to
    N weight;
    String terrain;
    Q4Edge<T, N> nextEdge; //reference to the next edge

    public Q4Edge() {
        this.toVertex = null;
        this.weight = null;
        this.terrain = null;
        this.nextEdge = null;
    }

    public Q4Edge(Q4Vertex<T, N> toVertex, N weight, Q4Edge<T, N> nextEdge, String terrain) {
        this.toVertex = toVertex;
        this.weight = weight;
        this.terrain = terrain;
        this.nextEdge = nextEdge;
    }
}
