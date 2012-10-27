package Test;

import Graph.Algo.ShortestPath.Adder;
import Graph.Algo.ShortestPath.BellmanFord;
import Graph.Algo.ShortestPath.Dijkshtra;
import Graph.Algo.SpanningTree.KrushkalMST;
import Graph.Algo.SpanningTree.PrimsMST;
import Graph.DefaultVertex;
import Graph.DirectedGraph;
import Graph.Edge;
import Graph.Graphs;
import Graph.UnDirectedGraph;
import Graph.Vertex;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Test {
   
    public static void main(String args[])
    {
//        new Test().go();
//        new Test().go2();
//        new Test().go3();
//           new Test().go4();
//        new Test().testEquals();
//        new Test().testKrishkal();
//        new Test().test5();
//        new Test().testPrims();
           new Test().testBF();
           
    }
    
    
    void testBF()
    {
        DirectedGraph<String,Integer> graph = new DirectedGraph<String, Integer>(DefaultVertex.class);   
        DefaultVertex<String> a = new DefaultVertex<String>("a");
        DefaultVertex<String> b = new DefaultVertex<String>("b");
        DefaultVertex<String> c = new DefaultVertex<String>("c");
        DefaultVertex<String> d = new DefaultVertex<String>("d");
        DefaultVertex<String> e = new DefaultVertex<String>("e");
        graph.addVertex(a);      graph.addVertex(b);        graph.addVertex(c);        graph.addVertex(d);        graph.addVertex(e);
        graph.connect(a, 6, b);
        graph.connect(a, 7, d);
        graph.connect(b, 8, d);
        graph.connect(b, 5, c);
        graph.connect(c, -2, b);
        graph.connect(d, 3, b);
        graph.connect(b, -4, e);
        graph.connect(d, 9, e);
        graph.connect(e, 7, c);
        graph.connect(e, 2, a);
        graph.connect(d, -3, c);
        
        System.out.println(graph);
        BellmanFord<String,Integer> algo = new BellmanFord<String, Integer>(graph, a, new Adder<Integer>(){

            @Override
            public Integer add(Integer add1, Integer add2) 
            {
                if(add1.equals(Integer.MAX_VALUE/2)||add1.equals(Integer.MAX_VALUE/2))
                    return Integer.MAX_VALUE/2;
                return add1+add2;
            }

        },0,Integer.MAX_VALUE/2);
        boolean gf = algo.findShortestPath();
        System.out.println(gf);
        System.out.println(algo.getShortestPathGraph().printAllPaths());
        Vertex<String> bb= (Vertex<String>) graph.getAllVertex().iterator().next();
        Iterator<Edge<?>> iter = bb.getAllPaths().iterator();
        System.out.println(iter.next().getPassage(bb));
        
    }
    
    void testPrims()
    {
        UnDirectedGraph<String,Integer> graph = new UnDirectedGraph<String, Integer>(DefaultVertex.class);   
        DefaultVertex<String> a = new DefaultVertex<String>("a");
        DefaultVertex<String> b = new DefaultVertex<String>("b");
        DefaultVertex<String> c = new DefaultVertex<String>("c");
        DefaultVertex<String> d = new DefaultVertex<String>("d");
        DefaultVertex<String> e = new DefaultVertex<String>("e");
        DefaultVertex<String> f = new DefaultVertex<String>("f");
        DefaultVertex<String> g = new DefaultVertex<String>("g");
        DefaultVertex<String> h = new DefaultVertex<String>("h");
        DefaultVertex<String> i = new DefaultVertex<String>("i");
        
        graph.addVertex(b);graph.addVertex(c);
        graph.addVertex(d);graph.addVertex(e);graph.addVertex(f);
        graph.addVertex(g);graph.addVertex(h);graph.addVertex(i);graph.addVertex(a);
        
        graph.connect(a, 4, b);
        graph.connect(a, 8, h);
        graph.connect(b, 11, g);
        graph.connect(b, 8, c);graph.connect(c, 7, d);
        graph.connect(d, 9, e);graph.connect(d, 14, f);graph.connect(e, 10, f);
        graph.connect(c, 4, f);graph.connect(c, 2, i);graph.connect(i, 6, g);
        graph.connect(i, 7, h);graph.connect(h, 1, g);graph.connect(g, 2, f);
        
        PrimsMST<String,Integer> mst = new PrimsMST<String, Integer>(graph);
        System.out.println("ans \n"+mst.getMST().printAllPaths());
    }
    
    void test5()
    {
        UnDirectedGraph<String,Integer> graph = new UnDirectedGraph<String, Integer>(DefaultVertex.class);                     
        DefaultVertex[] aa = new DefaultVertex[5];
        for(int i=0;i<5;i++)
        {
            aa[i] = new DefaultVertex<String>(""+i);
            graph.addVertex(aa[i]);
        }
        for(int i=0;i<7;i++)
        {
            graph.connect(aa[(int)((Math.random()-0.000001)*5)], (int)((Math.random()-0.000001)*9), aa[(int)(Math.random()*5)]);            
        }
        
        System.out.println(graph);
        System.out.println("--------------------------------");
        KrushkalMST<String,Integer> mst = new KrushkalMST<String, Integer>(graph);
        System.out.println("ans \n"+mst.getMST().printAllPaths());
        System.out.println("--------------------------------");


    }
    
    void testKrishkal()
    {
        UnDirectedGraph<String,Integer> graph = new UnDirectedGraph<String, Integer>(DefaultVertex.class);   
        DefaultVertex<String> a = new DefaultVertex<String>("a");
        DefaultVertex<String> b = new DefaultVertex<String>("b");
        DefaultVertex<String> c = new DefaultVertex<String>("c");
        DefaultVertex<String> d = new DefaultVertex<String>("d");
        DefaultVertex<String> e = new DefaultVertex<String>("e");
        DefaultVertex<String> f = new DefaultVertex<String>("f");
        DefaultVertex<String> g = new DefaultVertex<String>("g");
        DefaultVertex<String> h = new DefaultVertex<String>("h");
        DefaultVertex<String> i = new DefaultVertex<String>("i");
        
        graph.addVertex(a);graph.addVertex(b);graph.addVertex(c);
        graph.addVertex(d);graph.addVertex(e);graph.addVertex(f);
        graph.addVertex(g);graph.addVertex(h);graph.addVertex(i);
        
        graph.connect(a, 4, b);
        graph.connect(a, 8, h);
        graph.connect(b, 11, g);graph.connect(b, 8, c);graph.connect(c, 7, d);
        graph.connect(d, 9, e);graph.connect(d, 14, f);graph.connect(e, 10, f);
        graph.connect(c, 4, f);graph.connect(c, 2, i);graph.connect(i, 6, g);
        graph.connect(i, 7, h);graph.connect(h, 1, g);graph.connect(g, 2, f);
        
        KrushkalMST<String,Integer> mst = new KrushkalMST<String, Integer>(graph);
        System.out.println("ans \n"+mst.getMST().printAllPaths());
    }
    
    void testEquals()
    {
        DirectedGraph<String,Integer> graph = new DirectedGraph<String, Integer>(DefaultVertex.class);   
        DefaultVertex<String> a = new DefaultVertex<String>("a");
        DefaultVertex<String> b = new DefaultVertex<String>("b");
        DefaultVertex<String> c = new DefaultVertex<String>("c");
        graph.addVertex(a);graph.addVertex(b);graph.addVertex(c);
        graph.connect(a, 3, b);
        graph.connect(a, 3, c);
        System.out.println(b.getPassageToVertice(a));
        for(Edge<?> g: graph.getAllEdges())
        {
            System.out.println(g);
        }
        //
    }
    
    void go4()
    {
        DirectedGraph<String,Integer> graph = new DirectedGraph<String, Integer>(DefaultVertex.class);                     
        DefaultVertex[] aa = new DefaultVertex[10];
        for(int i=0;i<10;i++)
        {
            aa[i] = new DefaultVertex<String>(""+i);
            graph.addVertex(aa[i]);
        }
        for(int i=0;i<10;i++)
        {
            graph.connect(aa[(int)(Math.random()*9)], (int)(Math.random()*9), aa[(int)(Math.random()*9)]);            
        }
        System.out.println(graph);
        System.out.println("--------------------------------");
        Dijkshtra<String,Integer> fg = new Dijkshtra<String, Integer>(graph, aa[0], aa[9]);
        System.out.println(fg.findShortestPath());
        System.out.println("--------------------------------");
        fg.printShortestPath();
        System.out.println("--------------------------------");
        System.out.println(fg.getShortestPathDistance(aa[6]));
    }
    
    void go3()
    {
        DirectedGraph<String,Integer> graph = new DirectedGraph<String, Integer>(DefaultVertex.class);   
        DefaultVertex<String> a = new DefaultVertex<String>("a");
        DefaultVertex<String> b = new DefaultVertex<String>("b");
        DefaultVertex<String> c = new DefaultVertex<String>("c");
        DefaultVertex<String> d = new DefaultVertex<String>("d");
        DefaultVertex<String> e = new DefaultVertex<String>("e");
        graph.addVertex(a);      graph.addVertex(b);        graph.addVertex(c);        graph.addVertex(d);        graph.addVertex(e);
        graph.connect(a, 10, b);
        graph.connect(a, 5, d);
        graph.connect(b, 2, d);
        graph.connect(b, 1, c);
        graph.connect(c, 4, e);
        graph.connect(d, 3, b);
        graph.connect(d, 2, e);
        graph.connect(d, 9, c);
        graph.connect(e, 6, c);
        graph.connect(e, 7, a);
        System.out.println(graph);
        Dijkshtra algo = new Dijkshtra(graph, a, d);
        Graphs<String,Integer> gf = algo.findShortestPath();
        System.out.println(gf);
        
        
    }
    void go2()
    {
        DirectedGraph<String,Integer> graph = new DirectedGraph<String, Integer>(DefaultVertex.class);   
        DefaultVertex<String> a = new DefaultVertex<String>("a");
        DefaultVertex<String> b = new DefaultVertex<String>("b");
        DefaultVertex<String> c = new DefaultVertex<String>("c");
        DefaultVertex<String> d = new DefaultVertex<String>("d");
        DefaultVertex<String> e = new DefaultVertex<String>("e");
        graph.addVertex(a);      graph.addVertex(b);        graph.addVertex(c);        graph.addVertex(d);        graph.addVertex(e);
        graph.connect(a, 1, b);
        graph.connect(a, 2, c);
        graph.connect(c, 4, d);
        graph.connect(c, 3, e);
        graph.connect(d, 1, e);
        System.out.println("A is connected to B "+a.isConnectedTo(b));
        System.out.println("A is connected to C "+a.isConnectedTo(b));
        System.out.println("B is connected to C "+b.isConnectedTo(c));
        System.out.println("B is connected to A "+b.isConnectedTo(a));
        for(Edge<?> g : a.getConnectedEdges())
        {
            System.out.println(g);
        }
        for(Edge<?> g : b.getConnectedEdges())
        {
            System.out.println(g);
        }
        for(Edge<?> g : c.getConnectedEdges())
        {
            System.out.println(g);
        }
        for(Edge<?> g : d.getConnectedEdges())
        {
            System.out.println(g);
        }
        for(Edge<?> g : e.getConnectedEdges())
        {
            System.out.println(g);
            System.out.println(g.getPassage(e));
        }
        graph.removeConnctivity(b, a);
        System.out.println("after removal");
        for(Edge<?> g : b.getConnectedEdges())
        {
            System.out.println(g);
        }
    }
    void go()
    {
        UnDirectedGraph<String,Integer> graph = new UnDirectedGraph<String, Integer>(DefaultVertex.class);
        DefaultVertex<String> a = new DefaultVertex<String>("a");
        DefaultVertex<String> b = new DefaultVertex<String>("b");
        DefaultVertex<String> c = new DefaultVertex<String>("c");
        DefaultVertex<String> d = new DefaultVertex<String>("d");
        DefaultVertex<String> e = new DefaultVertex<String>("e");
        graph.addVertex(a);      graph.addVertex(b);        graph.addVertex(c);        graph.addVertex(d);        graph.addVertex(e);
        graph.connect(a, 1, b);
        graph.connect(a, 2, c);
        graph.connect(c, 4, d);
        graph.connect(c, 3, e);
        graph.connect(d, 1, e);
        
        System.out.println("A is connected to B "+a.isConnectedTo(b));
        System.out.println("A is connected to C "+a.isConnectedTo(b));
        System.out.println("B is connected to C "+b.isConnectedTo(c));
        System.out.println("B is connected to A "+b.isConnectedTo(a));
        for(Edge<?> g : a.getConnectedEdges())
        {
            System.out.println(g);
        }
        for(Edge<?> g : b.getConnectedEdges())
        {
            System.out.println(g);
        }
        for(Edge<?> g : c.getConnectedEdges())
        {
            System.out.println(g);
        }
        for(Edge<?> g : d.getConnectedEdges())
        {
            System.out.println(g);
        }
        for(Edge<?> g : e.getConnectedEdges())
        {
            System.out.println(g);
            System.out.println(g.getPassage(e));
        }
        graph.removeConnctivity(a, b);
        System.out.println("after removal");
        for(Edge<?> g : a.getConnectedEdges())
        {
            System.out.println(g);
        }               
        
    }
}
