/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Graph;

import java.util.Set;

/**
 * E is of the type: the kind of vertex I want to make. For example String
 * @author Jatin
 */
public interface Vertice<E>
{
      public void setAttribute(E ob);
      public E getAttribute();
//      public Set<Edge<?>> getConnectedVertices();
      public Set<Edge<?>> getConnectedEdges();       
      public Edge<?> getPassageToVertice(Vertice<?> vertice);
}
