package Graph.Algo.ShortestPath;

import Graph.Computation;
import Graph.DefaultVertex;
import Graph.DirectedGraph;
import Graph.Edge;
import Graph.Graphs;
import Graph.Vertex;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Set;

/**
 *
 * @author Jatin
 */
public class Dijkshtra<T, E extends Integer> {

    private final Graphs<T, Integer> graph;
    private final Vertex<T> start;
    private final Vertex<T> end;
    private final Graphs<T, Integer> ans;
    
    PriorityQueue<Vertex<? extends T>> mainQueue = new PriorityQueue<Vertex<? extends T>>(10, new Comparator<Vertex<? extends T>>() {

        @Override
        public int compare(Vertex<? extends T> o1, Vertex<? extends T> o2) {
            if (getDijkshtraVariable(o1).distance < getDijkshtraVariable(o2).distance) {
                return -1;
            } else if (getDijkshtraVariable(o1).distance == getDijkshtraVariable(o2).distance) {
                return 0;
            } else {
                return 1;
            }
        }
    });

    public <E extends Integer> Dijkshtra(Graphs<T, E> graph, Vertex<T> start, Vertex<T> end) {
        this.graph = (Graphs<T, Integer>) graph;
        this.start = start;
        this.end = end;
        this.ans = new DirectedGraph<T, Integer>(DefaultVertex.class) {

            @Override
            public Set<Edge<Integer>> getAllPaths() {
                throw new RuntimeException("USE IT AS A NEW GRAPH and not in path");
            }
        };
    }

    public Graphs<T, Integer> findShortestPath() {

        for (Vertex<? extends T> ver : graph.getAllVertex()) {
            ver.setComputation(new DijkshtraVariables(null));
            mainQueue.add(ver);
            DefaultVertex<T> ansNext = new DefaultVertex<T>(ver.getAttribute());
            ansNext.setComputation(new DijkshtraVariables(null));
            ans.addVertex(ansNext);
        }
        getDijkshtraVariable(start).distance = 0;
        mainQueue.remove(start);
        mainQueue.add(start);
        ans.addVertex(start);
        findPath();
        setPath();
        return ans;
    }

    private Graphs<T, Integer> findPath() {

        if (mainQueue.isEmpty()) {
            return ans;
        } else {
            //System.out.println(mainQueue);
            PriorityQueue<Vertex<T>> queue = new PriorityQueue<Vertex<T>>(10, new Comparator<Vertex<T>>() {

                @Override
                public int compare(Vertex<T> o1, Vertex<T> o2) {
                    if (getDijkshtraVariable(o1).distance < getDijkshtraVariable(o2).distance) {
                        return -1;
                    } else if (getDijkshtraVariable(o1).distance == getDijkshtraVariable(o2).distance) {
                        return 0;
                    } else {
                        return 1;
                    }
                }
            });
            HashMap<Vertex<T>, Boolean> map = new HashMap<Vertex<T>, Boolean>();

            Vertex<T> next = (Vertex<T>) mainQueue.poll();
            DefaultVertex<T> ansNext = (DefaultVertex<T>) ans.getVertex(next.getAttribute());
            getDijkshtraVariable(ansNext).distance = getDijkshtraVariable(next).distance;;
            boolean s = false;
            for (Edge<?> edge : next.getConnectedEdges()) {
                Vertex<T> ver = (Vertex<T>) edge.getPassage(next);
                if (mainQueue.contains(ver)) {
                    map.put(ver, relax(next, ver));
                    queue.add(ver);
                    s = true;
                }
                //    queue.add(ver); 
            }
            if (s) {
                for (Vertex<? extends T> h : queue) {
                    if (map.get(h)) {
                        getDijkshtraVariable(ans.getVertex(h.getAttribute())).setFromVertex(ansNext);
                        //System.out.println("$$$$ "+h+" and next "+ansNext);
                    }
                }
                Vertex<T> p = queue.poll();
                boolean relaxed = map.get(p);
                //System.out.println("Node selected to take path "+p);
                int j = (Integer) next.getPassageToVertice(p).getAttribute();
                DefaultVertex<T> ansP = (DefaultVertex<T>) ans.getVertex(p.getAttribute());
                if (relaxed) {
                    getDijkshtraVariable(ansP).setFromVertex(ansNext);
                }
                findPath();
            }

        }
        return ans;
    }

    private void setPath() {
        for (Vertex<? extends T> ver : ans.getAllVertex()) {
            if (getDijkshtraVariable(ver).getFromVertex() == null) {
                getDijkshtraVariable(ver).setFromVertex(start);
            }
            Vertex<T> from = (Vertex<T>) graph.getVertex(getDijkshtraVariable(ver).getFromVertex().getAttribute());
            Vertex<T> to = (Vertex<T>) graph.getVertex(ver.getAttribute());

            int j = 0;
            try {
                j = (Integer) from.getPassageToVertice(to).getAttribute();
            } catch (Exception g) {
                j = 0;
            }


            ans.connect(getDijkshtraVariable(ver).getFromVertex(), j, ver);
        }

    }

    private boolean relax(Vertex<T> main, Vertex<T> other) {
        boolean relaxed = false;
        //this is where the change is suppsoe to be made
        // to make it general
        Integer g = (Integer) main.getPassageToVertice(other).getAttribute();
        int var = getDijkshtraVariable(main).distance + g;
        if (var < getDijkshtraVariable(other).distance) {
            getDijkshtraVariable(other).distance = var;
            getDijkshtraVariable(other).setFromVertex(main);
            relaxed = true;
        }
        mainQueue.remove(other);
        mainQueue.add(other);
        return relaxed;
    }

    private DijkshtraVariables getDijkshtraVariable(Vertex<?> v) {
        return ((DijkshtraVariables) (v.getComputation()));
    }

    public void printShortestPath() {
        for (Vertex<? extends T> ver : ans.getAllVertex()) {
            if (getDijkshtraVariable(ver).distance != Integer.MAX_VALUE / 2) {
                System.out.println("Path Length from " + start + " to " + ver + " is " + getDijkshtraVariable(ver).distance);
            } else {
                System.out.println("No Path from " + start + " to " + ver);
            }
        }

    }
    /**
     * 
     * @param to
     * @return null if not path exists
     */
    public Integer getShortestPathDistance(Vertex<T> to)
    {
        if(!(ans.getAllVertex().contains(to)))
        {
            throw new RuntimeException("Vertex not in the ans graph");
        }
        int h = getDijkshtraVariable(to).distance;
        if(h==Integer.MAX_VALUE/2)
            return null;
        return h;
        
    }

    class DijkshtraVariables implements Comparator<DijkshtraVariables>, Computation 
    {

        int distance = Integer.MAX_VALUE / 2;
        private Vertex<T> fromVertex;

        public DijkshtraVariables(Object attr) {
        }

        void setFromVertex(Vertex<T> ver) {
            this.fromVertex = ver;
        }

        Vertex<T> getFromVertex() {
            return fromVertex;
        }

        @Override
        public int compare(DijkshtraVariables o1, DijkshtraVariables o2) {
            if (o1.distance < o2.distance) {
                return -1;
            } else if (o1.distance == o2.distance) {
                return 0;
            } else {
                return 1;
            }
        }
    }
}
