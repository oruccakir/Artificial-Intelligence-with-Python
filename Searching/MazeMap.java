package Searching;

import java.io.File;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

public class MazeMap {

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

    public MazeMap(String fileName){

        mazeMatrix = createMazeMap(fileName);



    }

    public int [][] createMazeMap(String fileName){

        int rowNumber = 0, colNumber = 0;

        int matrix[][] = null;

        try{

            Scanner scan = new Scanner(new File(fileName));

            while(scan.hasNextLine()){

                String inputString = scan.nextLine();

                colNumber = inputString.length();

                rowNumber++;
            }

            scan.close();

            matrix = new int[rowNumber][colNumber];

            scan = new Scanner(new File(fileName));

            int i = 0;

            while(scan.hasNextLine()){

                String inputString = scan.nextLine();

                for(int k = 0; k < inputString.length(); i++){

                    char chr = inputString.charAt(i);

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

        MazeMap map = new MazeMap("map1.txt");

        map.showMazeMatrix();
        
    }



    
}
