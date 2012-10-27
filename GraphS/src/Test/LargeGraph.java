/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Jatin
 */
public class LargeGraph 
{
    DirectedGraph<Node, Integer> graph;
    private DefaultVertex<Node> start;
    private  DefaultVertex<Node> end;
    
    public static void main(String args[]) {
        LargeGraph graph = new LargeGraph();
        graph.makeGraph();
        graph.findShortest();
    }

    private void findShortest()
    {
        System.out.println("Starting");
        DefaultVertex<Node> a = (DefaultVertex<Node>) graph.getAllVertex().iterator().next();
        Dijkshtra<Node,Integer> algo = new Dijkshtra<Node, Integer>(graph,start,end );
//        BellmanFord<Node,Integer> algo = new BellmanFord<Node, Integer>(graph, a, new Adder<Integer>(){
//            @Override
//            public Integer add(Integer add1, Integer add2) 
//            {
//                if(add1.equals(Integer.MAX_VALUE/2)||add1.equals(Integer.MAX_VALUE/2))
//                    return Integer.MAX_VALUE/2;
//                return add1+add2;
//            }
//
//        },0,Integer.MAX_VALUE/2);        
        algo.findShortestPath();
        System.out.println(algo.getShortestPathDistance(a));
        //System.out.println(algo.getShortestPathGraph());
    }
        
    private void makeGraph() {
        graph = new DirectedGraph<Node, Integer>(DefaultVertex.class);
        ArrayList<DefaultVertex<Node>> list = new ArrayList<DefaultVertex<Node>>();
        final int size = 50000;
        for(int i=0;i<size;i++)
        {
            DefaultVertex<Node> def = new DefaultVertex<Node>(new Node(i));
            list.add(def);
            graph.addVertex(def);
        }
        start = list.get((int)(Math.random()*size));
        end = list.get((int)(Math.random()*size));
        for(int i=0;i<size/15;i++)
        {
            DefaultVertex<Node> n = list.get((int)Math.random()*size);
//            System.out.println(n);
            DefaultVertex<Node> to =list.get((int)(Math.random()*size));
//            System.out.println(to);
            graph.connect(n, new Integer((int)(Math.random()*size)),to);
            for(int j=0;j<(Math.random()*5);j++)                
            {
                DefaultVertex<Node> toL = list.get((int)(Math.random()*size));
                //System.out.println(toL);
                graph.connect(n, new Integer((int)(Math.random()*100)),toL);
            }            
        }
        
        System.out.println("Number of Vertices: "+graph.getAllVertex().size());
        System.out.println("Number of Edges: "+graph.getAllEdges().size());
    }
}

class Node
{
    final int id;
    Node(int id)
    {
        this.id = id;
    }
    @Override
    public String toString()
    {
        return id+"";
    }
}