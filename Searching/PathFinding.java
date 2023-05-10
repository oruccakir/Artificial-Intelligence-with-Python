package Searching;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

public class PathFinding{

    public int charRepresentation=66;

    private Node initialPoint;

    private Node goalPoint;

    private String searchType;

    private class Node{

        public static int totalProducedNode = 0;

        private String state;
        private Node parent;
        private ArrayList <Node> childs;
        private int pathCost;

        public Node(String state, Node parent){

            this.state = state;
            this.parent = parent;
            this.pathCost = 0;
            childs = new ArrayList<>();

            totalProducedNode++;

        }

        public String toString(){

            String str = "State : "+state+" Path cost : "+pathCost;

            if(parent != null) str+= " Parent state : "+parent.state;

            return str;

        }


    }

    private ArrayDeque <Node> frontier = null;

    private ArrayDeque<Node> solution = null;

    private Set<Node> exploredSet = null;

    private ArrayList<Node> pathList = null;

    public PathFinding(String searchType ,int length){

        this.searchType = searchType;

        initialPoint = new Node("A", null);

        frontier = new ArrayDeque<>();

        pathList = new ArrayList<>();

        exploredSet = new LinkedHashSet<>();

        solution = new ArrayDeque<>();

        makePath(initialPoint, length);

        goalPoint = pathList.get((int) ( (Math.random()*pathList.size()-1)+1));

        if(searchType.equals("depth"))
           frontier.push(initialPoint);
        else
           frontier.offer(initialPoint);

    }


    /*
     * Make random path from initial point to end point
     */

    public void makePath(Node start,int length){ 

        if(Node.totalProducedNode == length) return;

        int numberOfChilds = (int) (Math.random()*3+1);

        for(int i=0; i<numberOfChilds; i++){

            if(Node.totalProducedNode == length) return;

            Node childNode = new Node(""+(char)(charRepresentation++),start);

            start.childs.add(childNode);

            pathList.add(childNode);

        }

        for(Node child : start.childs) makePath(child, length);
  
    }

    /*
     *  shows map in path list 
     */

    public void showPathList(){

        System.out.println("Initial point : "+initialPoint.state);

        for(int i=0; i<pathList.size(); i++){

            Node parent = pathList.get(i);

            System.out.println("Parent : "+parent.state);

            for(int k=0; k<parent.childs.size(); k++){

                Node child = parent.childs.get(k);

                System.out.print("Child "+(k+1)+" "+child.state+"   ");

            }

            System.out.println();

        }

        

    }


    /*
     * draws the path from initial point to end point
     */

    public void drawPath(Node head){

        if(head.childs.size() == 0){

            System.out.println(head);

            return;

        }

        System.out.println(head);

        for(Node child : head.childs)
           drawPath(child);

        

    }


    /*
     * draws the path that gives the solution way
     */

    public void drawSolutionPath(){

        Object arr[] = solution.toArray();

        for(int i=0; i<arr.length; i++) System.out.println(((Node)arr[i]).state);

    }

    /*
     * shows the explored places until finding the solution end point
     */

    public void showExploredPlaces(){

        for(Node node : exploredSet) System.out.print(node.state+" ");

        System.out.println();


    }

    /*
     * Start with a frontier that contains the initial state. 
     • Start with an empty explored set. 
     • Repeat: 
     • If the frontier is empty, then no solution. 
     • Remove a node from the frontier. 
     • If node contains goal state, return the solution. 
     • Add the node to the explored set. 
     • Expand node, add resulting nodes to the frontier if they 
       aren't already in the frontier or the explored set
     */

    /*
     * solve the path by reaching end point
     * 
     * depth-first search is a searching algorithm that always expands
     * the deepest node in the frontier
     * In depth-first search algorithm stack data structure is used 
     * Stack : last-in first-out data structure
     * 
     * breadth-first search is a search algorithm that always expands
     * the shallowest node in the frontier
     * In breadth-first search algorithm queue data structure is used
     * Queue : first-in first-out data structure
     */


     
    public void solvePath(){

        int num_explored = 0;

        while(true){

            if(frontier.isEmpty()){

                System.out.println("There is no solution");

                return;
                
            } 

            Node currentNode = null;

            if(searchType.equals("depth"))
               currentNode = frontier.pop();
            else
               currentNode = frontier.poll();

            num_explored++;

            if(currentNode == goalPoint){

                while(currentNode != null){

                    solution.push(currentNode);

                    currentNode = currentNode.parent;

                }

                if(searchType.equals("depth"))
                   System.out.println("Searching method : depth-first");
                else
                   System.out.println("Searching method : breadth-first");

                System.out.println("Explored : "+(num_explored-1));

                drawSolutionPath();

                return;

            }

            exploredSet.add(currentNode);

            for(Node child : currentNode.childs){

                if(frontier.contains(currentNode) == false && exploredSet.contains(currentNode)){

                    if(searchType.equals("depth"))
                        frontier.push(child);
                    else
                        frontier.offer(child);

                }
                    

            }


        }



    }

    /*
     * ## uninformed search :
     *  is a search strategy that uses no problem specific knowledge
     * 
     * ## informed search :
     *   is a search strategy that uses problem-specific knowledge
     *   to find solutions more efficiently
     * 
     * ## greedy best-first search : 
     *    is a search algorithm that expands the node that is closest
     *    to the goal, as estimated by a heuristic function h(n)
     * 
     * ## A* search :
     *   is a search algorithm that expands node with lowest
     *   value of g(n) + h(n)
     *   
     *   g(n) : cost to reach node
     *   
     *   h(n) : estimated cost to goal
     * 
     *   the success of an algorithm depends on the implementation of the two method
     * 
     *   A* search is optimal if 
     * 
     *   - h(n) is admissible (never overestimates the true cost), and 
     *   - h(n) is consistent (for every node n and uccessor n' with step cost c, h(n) ≤ h(n') + c)
     */















    public static void main(String[] args) {

        PathFinding p = new PathFinding("breadth",10);

        p.drawPath(p.initialPoint);

        p.showPathList();

        System.out.println(p.initialPoint.state+"  "+p.goalPoint.state);

        p.solvePath();

        p.showExploredPlaces();


        

        
    }













}