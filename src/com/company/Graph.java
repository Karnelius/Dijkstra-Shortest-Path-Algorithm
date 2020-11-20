package com.company;

import java.util.ArrayList;

public class Graph {

    public Node[] nodes;
    public int noOfNodes;
    public Edge[] edges;
    public int noOfEdges;


    public Graph(Edge[] edges) {
        this.edges = edges;
        this.noOfNodes = calculateNoOfNodes(edges);
        this.nodes = new Node[this.noOfNodes];

        for (int n = 0; n < this.noOfNodes; n++) {
            this.nodes[n] = new Node();

        }

        this.noOfEdges = edges.length;

        for (int edgetoAdd = 0; edgetoAdd < this.noOfEdges; edgetoAdd++) {
            this.nodes[edges[edgetoAdd].getFromNodeIndex()].getEdges().add(edges[edgetoAdd]);
            this.nodes[edges[edgetoAdd].getToNodeIndex()].getEdges().add(edges[edgetoAdd]);
        }
    }


    private int calculateNoOfNodes(Edge[] edges) {

        for (Edge e : edges) {
            if (e.getToNodeIndex() > noOfNodes)
                noOfNodes = e.getToNodeIndex();
            if (e.getFromNodeIndex() > noOfNodes)
                noOfNodes = e.getFromNodeIndex();
        }
        noOfNodes++;

        return noOfNodes;
    }

    public void calculateShortestDistance() {
        this.nodes[0].setDistanceFromSource(0);
        int nextNode = 0;

        for (int i = 0; i < this.nodes.length; i++) {

            ArrayList<Edge> currentNodeEdges = this.nodes[nextNode].getEdges();

            for (int joinedEdge = 0; joinedEdge < currentNodeEdges.size(); joinedEdge++) {
                int neighbourIndex = currentNodeEdges.get(joinedEdge).getNeighbourIndex(nextNode);

                if (!this.nodes[neighbourIndex].isVisited()) {
                    int tenative = this.nodes[nextNode].getDistanceFromSource() + currentNodeEdges.get(joinedEdge).getLength();

                    if (tenative < nodes[neighbourIndex].getDistanceFromSource()) {
                        nodes[neighbourIndex].setDistanceFromSource(tenative);
                    }
                }

            }
            nodes[nextNode].setVisited(true);
            nextNode = getNodesShortestDistanced();

        }
    }

    public void printResult() {
        String output = "number of nodes = " + this.noOfNodes;
        output += "\nNumber of edges = " + this.noOfEdges;

        for (int i = 0; i < this.nodes.length; i++) {
            output += ("\nThe shortest distance from node 0 to node " + i + " is " + nodes[i].getDistanceFromSource());
        }
        System.out.println(output);

    }

    private int getNodesShortestDistanced() {
        int storedNodeIndex = 0;
        int storedDist = Integer.MAX_VALUE;

        for (int i = 0; i < this.nodes.length; i++) {
            int currentDist = this.nodes[i].getDistanceFromSource();

            if (!this.nodes[i].isVisited() && currentDist < storedDist) {
                storedDist = currentDist;
                storedNodeIndex = i;
            }
        }
        return storedNodeIndex;
    }

    public Node[] getNodes() {
        return nodes;
    }

    public int getNoOfNodes() {
        return noOfNodes;
    }

    public int getNoOfEdges() {
        return noOfEdges;
    }
}




