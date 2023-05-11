package Searching;

import java.awt.Color;
import java.awt.Graphics;
import java.io.EOFException;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;



/*
 * four different solution will be implemented
 * 
 * 1-Depth-First search algorithm which is uninformed search 
 * 2-Breadth-First search algorithm which is uninfomed search
 * 3-Greedy-First search algorithm which is informed serarch with h(n) function
 * 4-A* search algorithm which is informed and optimal solution with h(n) and g(n)
 */

 /*
  * for first solutioın stack will be used despite that stack and arrayDeque works in same way
  * In solution stack will be used because solution includes thread and stack is more suitiable 
  * in that regard.
  *
  * for remaining solution queue data stracture will be used
  */

public class MazeSolution {

    private String searchType;

    private Stack<MazeMap.Node> frontierStack;
    private Queue<MazeMap.Node> frontierQueue;
    private HashSet<MazeMap.Node> exploredSet;
    public Stack<MazeMap.Node> solutionStack;

    private MazeMap map;

    public MazeSolution(String fileName, String searchType){

        this.searchType = searchType;
        map = new MazeMap(fileName);

        exploredSet = new LinkedHashSet<>(); // explored set

        solutionStack = new Stack<>(); // solution stack

        frontierStack = new Stack<>(); // for depth-first search

        frontierStack.push(map.initialPoint); // initial point is pushed

        frontierQueue = new LinkedList<>(); // for others search

        frontierQueue.add(map.initialPoint); // initial point is pushed

    }

    public void showTheSolutionPath(){

        int[][] matrix =  new int[map.copyMatrix.length][map.copyMatrix[0].length];

        for(int i=0; i<matrix.length; i++){
            for(int k=0; k<matrix[0].length; k++){
                matrix[i][k] = map.mazeMatrix[i][k];
            }
        }

        MazeMap.Node [] solutionArray = new MazeMap.Node[solutionStack.size()];

        solutionArray = solutionStack.toArray(solutionArray);

        for(int i=0; i<solutionArray.length; i++){

            MazeMap.Node currNode = solutionArray[i];

            matrix[currNode.mx][currNode.my] = -1;

        }

        for(int i=0; i<matrix.length; i++){

            for(int k=0; k<matrix[0].length; k++){
                
                if(matrix[i][k] == 0) System.out.print("#");

                else if(matrix[i][k] == 1) System.out.print(" ");

                else if(matrix[i][k] == 65) System.out.print("A");

                else if(matrix[i][k] == 66) System.out.print("B");

                else System.out.print("^");

            }

            System.out.println();
        }
        

        

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

    public void solveTheMaze(){

        if(searchType.equals("depth_first")){
            solveWithDepth_First_Search();
        }
        else if(searchType.equals("breadth_first")){
            solveWithBreadth_First_Search();
        }
        else if(searchType.equals("greedy_first")){
            solveWithGreedy_First_Search();
        }
        else if(searchType.equals("A*")){
            solveWithA_search();
        }

    }


    public void solveWithDepth_First_Search(){

        int num_explored = 0;

        while(true){

            map.mapPanel.repaint();

            //If the frontier is empty, then no solution. 

            if(frontierStack.isEmpty()){

                System.err.println("There is no solution in this map");

                return;

            }

            //Remove a node from the frontier.

            MazeMap.Node currentNode = frontierStack.pop();
            num_explored++;

            if(currentNode != map.initialPoint && !currentNode.equals(map.goalPoint)) map.exploredList.add(currentNode);

            //If node contains goal state, return the solution.

            /*
            * goal test examine whether goal point is reached or not
            */

            if(currentNode.equals(map.goalPoint)){                                     

                while(currentNode != null){

                    solutionStack.push(currentNode);

                    currentNode = currentNode.parent;

                }

                System.out.println("Explored " +(num_explored-1));

                return;

            }

            //Add the node to the explored set.

           
            for(MazeMap.Node child : currentNode.childs){

                if(frontierStack.contains(child) == false && exploredSet.contains(child) == false) frontierStack.push(child);

            }

           
            try{

                Thread.sleep(100);

            }
            catch(Exception e){

            }

            

        }

    }

    public void solveWithBreadth_First_Search(){

    }

    public void solveWithGreedy_First_Search(){

    }

    public void solveWithA_search(){

    }


    






    


    public static void main(String[] args) {

        MazeSolution solution = new MazeSolution("maze4.txt", "depth_first");
        solution.map.showMazeMap();
        solution.solveTheMaze();
        solution.showTheSolutionPath();

        
        
    }

    
}
