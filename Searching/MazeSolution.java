package Searching;


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import Searching.MazeMap.Node;



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

    public static final String DEPTH_FIRST_SEARCH = "depth_first";
    public static final String BREADTH_FIRST_SEARCH = "breadth_first";
    public static final String GREEDY_FIRST_SEARCH = "greedy_first";
    public static final String A_SEARCH = "A*";


    public static String searchType;

    private Stack<MazeMap.Node> frontierStack;
    private ArrayDeque<MazeMap.Node> frontierQueue;
    private HashSet<MazeMap.Node> exploredSet;
    public Stack<MazeMap.Node> solutionStack;

    private MazeMap map;

    public MazeSolution(String fileName, String searchType){

        MazeSolution.searchType = searchType;
        map = new MazeMap(fileName);

        exploredSet = new LinkedHashSet<>(); // explored set

        solutionStack = new Stack<>(); // solution stack

        frontierStack = new Stack<>(); // for depth-first search

        frontierStack.push(map.initialPoint); // initial point is pushed

        frontierQueue = new ArrayDeque<>(); // for others search

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

    public boolean goalTest (Node currNode){

        MazeMap.Node up = map.new Node(currNode.mx+1,currNode.my);
        MazeMap.Node left = map.new Node(currNode.mx,currNode.my-1);
        MazeMap.Node down = map.new Node(currNode.mx-1,currNode.my);
        MazeMap.Node right = map.new Node(currNode.mx,currNode.my+1);

        return up.equals(map.goalPoint) || left.equals(map.goalPoint) || right.equals(map.goalPoint) || down.equals(map.goalPoint);

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

            if(goalTest(currentNode)){                                     

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

        int num_explored = 0;

        while(true){

            map.mapPanel.repaint();

            //If the frontier is empty, then no solution. 

            if(frontierQueue.isEmpty()){

                System.err.println("There is no solution in this map");

                return;

            }

            //Remove a node from the frontier.




            MazeMap.Node currentNode = frontierQueue.poll();

            num_explored++;

            if(currentNode != map.initialPoint && !currentNode.equals(map.goalPoint)) map.exploredList.add(currentNode);

            //If node contains goal state, return the solution.

            /*
            * goal test examine whether goal point is reached or not
            */

            if(goalTest(currentNode)){                                     

                while(currentNode != null){

                    solutionStack.push(currentNode);

                    currentNode = currentNode.parent;

                }

                System.out.println("Explored " +(num_explored-1));

                return;

            }

            //Add the node to the explored set.

           
            for(MazeMap.Node child : currentNode.childs){

                if(frontierQueue.contains(child) == false && exploredSet.contains(child) == false){

                    frontierQueue.add(child);

                    

                } 

            }

           
            try{

                Thread.sleep(100);

            }
            catch(Exception e){

            }

            

        }

    }

    public void solveWithGreedy_First_Search(){

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

            if(goalTest(currentNode)){                                     

                while(currentNode != null){

                    solutionStack.push(currentNode);

                    currentNode = currentNode.parent;

                }

                System.out.println("Explored " +(num_explored-1));

                return;

            }

            //Add the node to the explored set.

            Collections.sort(currentNode.childs);
           
            for(MazeMap.Node child : currentNode.childs){

                if(frontierStack.contains(child) == false &&  exploredSet.contains(child) == false ) {

                    frontierStack.push(child);

                }

            }

           
            try{

                Thread.sleep(100);

            }
            catch(Exception e){

            }

            

        }


    }

    public void solveWithA_search(){
        

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

            if(goalTest(currentNode)){                                     

                while(currentNode != null){

                    solutionStack.push(currentNode);

                    currentNode = currentNode.parent;

                }

                System.out.println("Explored " +(num_explored-1));

                return;

            }

            //Add the node to the explored set.

            Collections.sort(currentNode.childs);

           
            for(MazeMap.Node child : currentNode.childs){

                if(frontierStack.contains(child) == false &&  exploredSet.contains(child) == false ) {

                    frontierStack.push(child);
                    
                }

            }

            ArrayList<Node> tempList = new ArrayList<>(frontierStack);

            Collections.sort(tempList);

            frontierStack.clear();

            for(int i=0; i<tempList.size(); i++) frontierStack.push(tempList.get(i));


            try{

                Thread.sleep(100);

            }
            catch(Exception e){

            }

            

        }

    }


    public static void main(String[] args) {

        MazeSolution solution = new MazeSolution("searching_maps/maze2.txt",DEPTH_FIRST_SEARCH);
        solution.map.showMazeMap();
        solution.solveTheMaze();
        solution.showTheSolutionPath();

        
        
    }


    /*  # Adversarial Search :
     *  MiniMax approach :
     *  MAX (X) aims to maximize score
     *  MIN (O) aims to minimize score
     * 
     * S0 --> is initial state 
     * Player(s) --> returns which player to move in state se
     * Actions(s) --> returns legal moves in state s
     * Result(s,a) --> returns state after action a taken in state s
     * Terminal(s) --> checks if state s is a terminal state
     * Utılıty(s) : final numerical value for terminal state s
     * 
     * Given action (a) state (s) 
     * 
     * --> MAX picks action a in ACTIONS(s) that produces
     * highest value of MIN-VALUE(Result(s,a))
     * 
     * --> MIN picks action a in ACTIONS(s) that produces
     * smallest value of MAX-VALUE(Results(s,a))
     * 
     *  ALGORITHM for ADVERSARIAL Search :
     * 
     *  function MAX-VALUE(state):
     *  
     * ---> if TERMINAL(state) : return UTILITY(state)
     * 
     * v = minus infinite 
     * 
     * for action in ACTIONS(state): 
     * v = MAX(v,MIN-VALUE(RESULT(state,action)))
     * 
     * return v
     * 
     *   function MIN-VALUE(state):
     *  
     *   if TERMINAL(state): return UTILITY(state) 
     *   v = ∞
     *   for action in ACTIONS(state): 
     *   v = MIN(v, MAX-VALUE(RESULT(state, action))) 
     *   return v
     * 
     * total possible Tic-Tac-Toe games : 255168
     * 
     * EVALUATION FUNCTION : is a function that estimates the expected 
     * utılıty of the game from a given state and it is essential part of adversarial search
     * 
     */

    
}
