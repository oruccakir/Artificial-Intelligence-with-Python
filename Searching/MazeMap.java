package Searching;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;;

public class MazeMap extends JFrame {

    private MapPanel mapPanel;

    public Node initialPoint;
    public Node goalPoint;

    private int [][] mazeMatrix;

    private class Node{

        private Node parent;

        private int x,y;
        private int costToReach, costToReachGoal;

        public Node(int x, int y, Node parent){

            this.x = x;
            this.y = y;
            this.parent = parent;

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

            drawTheLines(g);

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



    }

    public MazeMap(String fileName){
    
        mazeMatrix = createMazeMap(fileName);

        this.setLocationRelativeTo(this);

        this.setResizable(false);

        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        this.setSize(mazeMatrix[0].length*50, mazeMatrix.length*50);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        int x = (dim.width - this.getWidth()) / 2;
        int y = (dim.height - this.getHeight()) / 2;
        this.setLocation(x, y-40);

        this.setVisible(true);

        mapPanel = new MapPanel();

        mapPanel.setBackground(Color.DARK_GRAY);

        mapPanel.setPreferredSize(new Dimension(mazeMatrix[0].length*50, mazeMatrix.length*50));

        this.add(mapPanel);

        this.pack();
    }

    public int [][] createMazeMap(String fileName){

        int rowNumber = 0, colNumber = 0;

        int matrix[][] = null;

        try{

            Scanner scan = new Scanner(new FileInputStream(fileName));

            while(scan.hasNextLine()){

                String inputString = scan.nextLine();

                colNumber = inputString.length();

                rowNumber++;
            }

            scan.close();

            matrix = new int[rowNumber][colNumber];

            scan = new Scanner(new FileInputStream(fileName));

            int i = 0;

            while(scan.hasNextLine()){

                String inputString = scan.nextLine();

                for(int k = 0; k < inputString.length(); k++){

                    char chr = inputString.charAt(k);

                    if(chr == '#') matrix[i][k] = 0;

                    else if(chr == ' ') matrix[i][k] = 1;

                    else if(chr == 'A') matrix[i][k] = 65;

                    else if(chr == 'B') matrix[i][k] = 66;

                }

                i++;

            }

            scan.close();

        }
        catch(Exception e){

            System.out.println(e.getMessage());

        }

        return matrix;
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

    public static void main(String[] args) {

    
        MazeMap map = new MazeMap("map1.txt.txt");

        map.showMazeMatrix();


        
    }



    
}
