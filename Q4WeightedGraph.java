
import java.util.ArrayList;
import java.util.PriorityQueue;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author firzanahzaidi
 */
public class Q4WeightedGraph<T extends Comparable<T>, N extends Comparable<N>> {
    
    Q4Vertex<T, N> head; //head vertex of the graph
    int size; //number of vertices in the graph.
    

    public Q4WeightedGraph() {
        this.head = null;
        this.size = 0;
    }

    public int getSize() {
        return size;
    }

    //checking if vertex exist in data structure
    public boolean hasVertex(T vertexInfo) {
        if (head == null) {
            return false;
        }
        Q4Vertex<T, N> current = head;
        while (current != null) {
            if (current.vertexInfo.compareTo(vertexInfo) == 0) {
                return true;
            }
            current = current.nextVertex;
        }
        return false;
    }

    public int getIndeg(T vertexInfo) {
        if (hasVertex(vertexInfo)) {
            Q4Vertex<T, N> current = head;
            while (current != null) {
                if (current.vertexInfo.compareTo(vertexInfo) == 0) {
                    return current.indeg;
                }
                current = current.nextVertex;
            }
        }
        return -1;
    }

    public int getOutdeg(T vertexInfo) {
        if (hasVertex(vertexInfo)) {
            Q4Vertex<T, N> current = head;
            while (current != null) {
                if (current.vertexInfo.compareTo(vertexInfo) == 0) {
                    return current.outdeg;
                }
                current = current.nextVertex;
            }
        }
        return -1;
    }

    public boolean addVertex(T vertexInfo) {
        if (hasVertex(vertexInfo)) {
            return false;
        }
        Q4Vertex<T, N> current = head;
        Q4Vertex<T, N> newVertex = new Q4Vertex<>(vertexInfo, null);
        if (current == null) {
            head = newVertex;
        }
        else {
            while (current.nextVertex != null) {
                current = current.nextVertex;
            }
            current.nextVertex = newVertex;
        }
        size++;
        return true;
    }

    public int getIndex(T vertexInfo) {
        Q4Vertex<T, N> current = head;
        for (int i = 0; i < size; i++) {
            if (current.vertexInfo.compareTo(vertexInfo) == 0) {
                return i;
            }
            current = current.nextVertex;
        }
        return -1;
    }

    public ArrayList<T> getAllVertexObjects() {
        ArrayList<T> list = new ArrayList<>();
        Q4Vertex<T, N> current = head;
        while (current != null) {
            list.add(current.vertexInfo);
            current = current.nextVertex;
        }
        return list;
    }

    public T getVertex(int index) {
        if (index >= size || index < 0) {
            return null;
        }
        Q4Vertex<T, N> current = head;
        for (int i = 0; i < index; i++) {
            current = current.nextVertex;
        }
        return current.vertexInfo;
    }

    public boolean hasEdge(T source, T destination) {
        if (head == null) {
            return false;
        }
        if (!hasVertex(source) || !hasVertex(destination)) {
            return false;
        }

        Q4Vertex<T, N> sourceVertex = head;
        while (sourceVertex != null) {
            if (sourceVertex.vertexInfo.compareTo(source) == 0) {

                Q4Edge<T, N> currentEdge = sourceVertex.firstEdge;
                while (currentEdge != null) {
                    if (currentEdge.toVertex.vertexInfo.compareTo(destination) == 0) {
                        return true;
                    }
                    currentEdge = currentEdge.nextEdge;
                }
            }
            sourceVertex = sourceVertex.nextVertex;
        }
        return false;
    }

    public boolean addEdge(T source, T destination, N weight, String terrain)   {
      if (head==null)
         return false;
      if (!hasVertex(source) || !hasVertex(destination)) 
         return false;
      Q4Vertex<T,N> sourceVertex = head;
      while (sourceVertex!=null)	{
         if ( sourceVertex.vertexInfo.compareTo( source ) == 0 )   {
            // Reached source vertex, look for destination now
            Q4Vertex<T,N> destinationVertex = head;
            while (destinationVertex!=null)	{
               if ( destinationVertex.vertexInfo.compareTo( destination ) == 0 )   {
                  // Reached destination vertex, add edge here
                  Q4Edge<T,N> currentEdge = sourceVertex.firstEdge;
                  Q4Edge<T,N> newEdge = new Q4Edge<>(destinationVertex , weight , currentEdge, terrain);
                  sourceVertex.firstEdge=newEdge;
                  sourceVertex.outdeg++;
                  destinationVertex.indeg++;
                  return true;
               }
               destinationVertex=destinationVertex.nextVertex;
            }
         }
         sourceVertex=sourceVertex.nextVertex;
      }
      return false;
   }

    public Integer getEdgeWeight(T source, T destination) {
    if (head == null) {
        return null;
    }
    if (!hasVertex(source) || !hasVertex(destination)) {
        return null;
    }

    Q4Vertex<T, N> sourceVertex = head;
    while (sourceVertex != null) {
        if (sourceVertex.vertexInfo.compareTo(source) == 0) {
            Q4Edge<T, N> currentEdge = sourceVertex.firstEdge;
            while (currentEdge != null) {
                if (currentEdge.toVertex.vertexInfo.compareTo(destination) == 0) {
                    return Integer.valueOf(currentEdge.weight.toString());
                }
                currentEdge = currentEdge.nextEdge;
            }
        }
        sourceVertex = sourceVertex.nextVertex;
    }
    return null;
}
    
    public String getEdgeLabel(T source, T destination) {
    if (head == null) {
        return null;
    }
    if (!hasVertex(source) || !hasVertex(destination)) {
        return null;
    }

    Q4Vertex<T, N> sourceVertex = head;
    while (sourceVertex != null) {
        if (sourceVertex.vertexInfo.compareTo(source) == 0) {
            Q4Edge<T, N> currentEdge = sourceVertex.firstEdge;
            while (currentEdge != null) {
                if (currentEdge.toVertex.vertexInfo.compareTo(destination) == 0) {
                    return String.valueOf(currentEdge.terrain.toString());
                }
                currentEdge = currentEdge.nextEdge;
            }
        }
        sourceVertex = sourceVertex.nextVertex;
    }
    return null;
}


    public ArrayList<T> getNeighbours(T vertex) {
        if (!hasVertex(vertex)) {
            return null;
        }
        ArrayList<T> list = new ArrayList<>();
        Q4Vertex<T, N> current = head;
        while (current != null) {
            if (current.vertexInfo.compareTo(vertex) == 0) {
                Q4Edge<T, N> currentEdge = current.firstEdge;
                while (currentEdge != null) {
                    list.add(currentEdge.toVertex.vertexInfo);
                    currentEdge = currentEdge.nextEdge;
                }
            }
            current = current.nextVertex;
        }
        return list;
    }

    public void printEdges() {
        Q4Vertex<T, N> current = head;
        while (current != null) {
            System.out.printf("# %s : ", current.vertexInfo);
            Q4Edge<T, N> currentEdge = current.firstEdge;
            while (currentEdge != null) {
                System.out.printf("[%s, %s] ", current.vertexInfo, currentEdge.toVertex.vertexInfo);
                currentEdge = currentEdge.nextEdge;
            }
            System.out.println();
            current = current.nextVertex;
        }
    }

   
    public boolean addUndirectedEdge(T source, T destination, N weight, String terrain) {
        return (addEdge(source, destination, weight, terrain) && addEdge(destination, source, weight, terrain));
    }

    
    public Q4Edge<T, N> removeEdge(T source, T destination) {
        if (head == null) {
            return null;
        }
        if (!hasVertex(source) || !hasVertex(destination)) {
            return null;
        }

        Q4Vertex<T, N> currentSource = head;
        while (currentSource != null) {
            if (currentSource.vertexInfo.compareTo(source) == 0) {
                Q4Edge<T, N> previousEdge = currentSource.firstEdge;
                Q4Edge<T, N> currentEdge = currentSource.firstEdge;
                while (currentEdge != null) {
                    if (currentEdge.toVertex.vertexInfo.compareTo(destination) == 0) {
                        previousEdge.nextEdge = currentEdge.nextEdge;
                        currentEdge.nextEdge = null;
                        currentSource.outdeg--;
                        currentEdge.toVertex.indeg--;
                        currentEdge.toVertex = null;
                        size--;
                        return currentEdge;
                    }
                    previousEdge = currentEdge;
                    currentEdge = currentEdge.nextEdge;
                }
            }
            currentSource = currentSource.nextVertex;
        }
        return null;
    }
    }

