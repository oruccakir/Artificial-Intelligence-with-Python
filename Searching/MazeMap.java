package Searching;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;;

public class MazeMap extends JFrame {

    public MapPanel mapPanel;

    public Node initialPoint;
    public Node goalPoint;

    public int [][] mazeMatrix;
    public int [][] copyMatrix;
    public ArrayList<Node> exploredList;

    private int totalSpace = 0;

    public HashSet<Node> nodeSet;

    public class Node implements Comparable<Node>{

        public Node parent;

        public ArrayList<Node> childs;

        public int x,y;
        private int costToReach, costToReachGoal;
        public int mx,my;

        public Node(int x, int y, Node parent,int mx,int my){

            this.childs = new ArrayList<>();
            this.x = x;
            this.y = y;
            this.parent = parent;
            this.mx = mx;
            this.my = my;

        }

        public Node(int mx,int my){
            this.mx = mx;
            this.my = my;
        }

        public boolean equals(Object o){

            if(o == null) return false;

            if(o.getClass() != this.getClass()) return false;

            Node tempNode = (Node)o;

            return tempNode.mx == this.mx && tempNode.my == this.my;

        }

        /*
         * that method returns cost to reach this node
         */                                                                                   

        public int g(){

            return Math.abs(mx - initialPoint.mx) + Math.abs(my - initialPoint.my);                      
                                                                                                                                
        } 

        /*
         * that method returns cost to reach goal point
         */

        public int h(){

            return Math.abs(mx - goalPoint.mx) + Math.abs(my - goalPoint.my);

        }

        /*
         * above two method is valuable for informed search such as greedy-first ans A* search algorithms
         */

        public void drawNodeToMap(Graphics g){

            g.setColor(Color.YELLOW);

            g.fill3DRect(x,y, 50, 50,true);
            
        }

        public String toString(){
            return "MapX : "+x+"\n"+"MapY : "+y+"\n"+"MatrixX : "+mx+"\n"+"MatrixY : "+my+"\n"+"Reach Cost : "+costToReach+"\n"+"Goal Cost : "+costToReachGoal+"\n";
        }

        @Override
        public int compareTo(Node o) {
            if(this.h() < o.h()) return -1;
            else if(this.h() > o.h()) return 1;
            else return 0;
        }

    }

    public void connectAllNodestoEachOther(Node currNode){

        if(nodeSet.size() == totalSpace) return;

        if(currNode.mx+1 < copyMatrix.length && (copyMatrix[currNode.mx+1][currNode.my] == 1 || copyMatrix[currNode.mx+1][currNode.my] == 66) ) {

    
                Node tempNode = new Node(currNode.my*50, (currNode.mx+1)*50,currNode, currNode.mx+1, currNode.my);

                currNode.childs.add(tempNode);

                copyMatrix[currNode.mx+1][currNode.my] = 0;

                nodeSet.add(tempNode);

        }

        if(currNode.mx-1 >=0 && (copyMatrix[currNode.mx-1][currNode.my] == 1 || copyMatrix[currNode.mx-1][currNode.my] == 66) ) {

            

                Node tempNode = new Node(currNode.my*50, (currNode.mx-1)*50,currNode, currNode.mx-1, currNode.my);

                currNode.childs.add(tempNode);

                copyMatrix[currNode.mx-1][currNode.my] = 0;

                nodeSet.add(tempNode);

            

            

            

        } 

        if(currNode.my+1 < copyMatrix[0].length && (copyMatrix[currNode.mx][currNode.my+1] == 1 || copyMatrix[currNode.mx][currNode.my+1] == 66) ) {

            

                Node tempNode = new Node((currNode.my+1)*50, (currNode.mx)*50,currNode, currNode.mx, currNode.my+1);

                currNode.childs.add(tempNode);

                copyMatrix[currNode.mx][currNode.my+1] = 0;

                nodeSet.add(tempNode);

            

        }

        if(currNode.my-1 >=0 && (copyMatrix[currNode.mx][currNode.my-1] == 1 || copyMatrix[currNode.mx][currNode.my-1] == 66)) {

            

            

                Node tempNode = new Node((currNode.my-1)*50, (currNode.mx)*50,currNode, currNode.mx, currNode.my-1);

                currNode.childs.add(tempNode);

                copyMatrix[currNode.mx][currNode.my-1] = 0;

                nodeSet.add(tempNode);

            

        }

        for(Node child : currNode.childs){

            connectAllNodestoEachOther(child);

        }

    }

    public class MapPanel extends JPanel{

        public MapPanel (){

            

        }

        public void paintComponent(Graphics g){

            super.paintComponent(g);

            int startX = 0, startY =0;

            for(int i=0; i<mazeMatrix.length; i++){

                startX = 0;

                for(int k=0; k<mazeMatrix[0].length; k++){

                    g.setColor(Color.BLACK);

                    if(mazeMatrix[i][k] == 0) g.fill3DRect(startX, startY, 50,50,true);

                    else if(mazeMatrix[i][k] == 65){

                        g.setColor(Color.RED);

                        g.fill3DRect(startX, startY, 50,50,true);

                    }

                    else if(mazeMatrix[i][k] == 66){

                        g.setColor(Color.GREEN);

                        g.fill3DRect(startX, startY, 50,50,true);

                    }

                    startX +=50;

                }

                startY+=50;

            }

            for(int i=0; i<exploredList.size(); i++) exploredList.get(i).drawNodeToMap(g);

            drawTheLines(g);


            if(MazeSolution.searchType.equals("greedy_first") || MazeSolution.searchType.equals("A*"))
                writeToMapManhattanDistance(g);

        }

        public void drawTheLines(Graphics g){

            g.setColor(Color.GRAY);

            int width = 50;

            for(int i=0; i<mazeMatrix[0].length; i++){

                g.drawLine(width,0,width,mazeMatrix.length*50);

                width+=50;

                
            }

            width = 50;

            for(int i=0; i<=mazeMatrix.length; i++){

                g.drawLine(0,width,mazeMatrix[0].length*50,width);

                width+=50;

                
            }

        }

        public void drawAllNodes(Graphics g,Node currNode){

            if(currNode.equals(initialPoint) == false) currNode.drawNodeToMap(g);

            for(Node child : currNode.childs) drawAllNodes(g, child);

            if(currNode.childs.size() == 0) return;

        }

        public void writeToMapManhattanDistance(Graphics g){

            for(Node node : nodeSet){

                node.costToReachGoal = node.h();

                g.setColor(Color.white);

                if(!node.equals(goalPoint)) g.drawString(""+node.costToReachGoal,node.x+15,node.y+15);

            }


        }

    }

    public MazeMap(String fileName){
    
        createMazeMap(fileName);

        nodeSet = new LinkedHashSet<>();

        exploredList = new ArrayList<>();

        this.setLocationRelativeTo(this);

        this.setResizable(false);

        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        this.setSize(mazeMatrix[0].length*50, mazeMatrix.length*50);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        int x = (dim.width - this.getWidth()) / 2;
        int y = (dim.height - this.getHeight()) / 2;
        this.setLocation(x, y-40);

        mapPanel = new MapPanel();

        mapPanel.setBackground(Color.DARK_GRAY);

        mapPanel.setPreferredSize(new Dimension(mazeMatrix[0].length*50, mazeMatrix.length*50));

        this.add(mapPanel);

        this.pack();

        connectAllNodestoEachOther(initialPoint);

    }

    public void createMazeMap(String fileName){

        int rowNumber = 0, colNumber = 0;

        try{

            Scanner scan = new Scanner(new FileInputStream(fileName));

            while(scan.hasNextLine()){

                String inputString = scan.nextLine();

                colNumber = inputString.length();

                rowNumber++;
            }

            scan.close();

            mazeMatrix = new int[rowNumber][colNumber];

            copyMatrix = new int[rowNumber][colNumber];

            scan = new Scanner(new FileInputStream(fileName));

            int i = 0;

            while(scan.hasNextLine()){

                String inputString = scan.nextLine();

                for(int k = 0; k < inputString.length(); k++){

                    char chr = inputString.charAt(k);

                    if(chr == '#') mazeMatrix[i][k] = 0;

                    else if(chr == ' ') { mazeMatrix[i][k] = 1; totalSpace++; }

                    else if(chr == 'A'){

                        mazeMatrix[i][k] = 65;

                        initialPoint = new Node(k*50,i*50,null,i,k);

                    } 

                    else if(chr == 'B') {

                        mazeMatrix[i][k] = 66;

                        totalSpace++;

                        goalPoint = new Node(k*50,i*50,null,i,k);

                    }

                    copyMatrix[i][k] = mazeMatrix[i][k];

                }

                i++;

            }

            scan.close();

        }
        catch(Exception e){

            System.out.println(e.getMessage());

        }

    }


    public void showMazeMatrix(){

        for(int i=0; i<mazeMatrix.length; i++){

            for(int k=0; k<mazeMatrix[0].length; k++){

                if(mazeMatrix[i][k] == 0) System.out.print("#");

                else if(mazeMatrix[i][k] == 1) System.out.print(" ");

                else if(mazeMatrix[i][k] == 65) System.out.print("A");

                else if(mazeMatrix[i][k] == 66) System.out.print("B");
                
            }

            System.out.println();
        }

    }

    public void showCopyMatrix(){

        for(int i=0; i<copyMatrix.length; i++){

            for(int k=0; k<copyMatrix[0].length; k++){

                if(copyMatrix[i][k] == 0) System.out.print("#");

                else if(copyMatrix[i][k] == 1) System.out.print(" ");

                else if(copyMatrix[i][k] == 65) System.out.print("A");

                else if(copyMatrix[i][k] == 66) System.out.print("B");
                
            }

            System.out.println();
        }

    }

    public void showMazeMap(){
        this.setVisible(true);
    }

    public static void main(String[] args) {

        
        

        
    }



    
}
