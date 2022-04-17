package sk.uniza.fri;

import java.io.File;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Arrays;


public class Main {
    public static final int[][] edges = new int[][]{
            {1, 3, 30},
            {2, 4, 75},
            {3, 2, 10},
            {3, 5, 60},
            {4, 3, 25},
            {5, 1, 30},
            {5, 2, 110},
    };

    public static int[][] edges2;


    public static final int verticesCount = 6;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int i = 0;


        try {
            File myObj = new File("C:\\Users\\admin\\Downloads\\LableSet\\src\\sk\\uniza\\fri\\TEST_mini.hrn");
            Scanner myReader = new Scanner(myObj);

            //System.out.println("som v try");
            while (myReader.hasNextLine()) {

                i++;
                String number = myReader.nextLine();
                String[] riadok = number.split(" ");
                int n1 = Integer.parseInt(riadok[0]);


                //System.out.println(n1);
                //System.out.println(number);
                // System.out.println(i);
            }


            int[][] mojeedges = new int[i][3];
            //System.out.println( "skoncil som prvy while " + i);
            Scanner myReader2 = new Scanner(myObj);

            while (myReader2.hasNextLine()) {


                for (int x = 0; x < i; x++) {
                    String number = myReader2.nextLine();

                    //  System.out.println(number);

                    String[] riadok = number.split(" ");


                    for (int y = 0; y < 3; y++) {
                        int n1 = Integer.parseInt(riadok[y]);
                        // System.out.println(n1);

                        mojeedges[x][y] = n1;

                    }

                }

            }


            edges2 = mojeedges;
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        // System.out.println("bbbbbbbbbbbbbbbbbbb");

        LabelSet basicShortestPath = new LabelSet(i, edges2);

        while (true) {
            int from, to;
            System.out.print("from: ");
            from = scanner.nextInt();
            System.out.print("to: ");
            to = scanner.nextInt();

            if (from == -1 || to == -1) break;

            int[] shortestPath = basicShortestPath.getShortestPath(from, to, LabelSet.LabelAlgorithmForm.LABEL_CORRECT);
            if (shortestPath == null) System.out.println("unreachable");
            else System.out.println("vertices sequence: " + Arrays.toString(shortestPath));
        }
    }
}
