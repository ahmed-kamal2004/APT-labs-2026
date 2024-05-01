//package mpj_lab4;

import mpi.MPI;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class requirement {

    public static void main(String[] args) throws FileNotFoundException {
        MPI.Init(args);
        // everything between
        int world_rank = MPI.COMM_WORLD.Rank();
        if(world_rank == 0){
            // Root
            File myObj1 = new File("src/A1.txt");
            Scanner myReader1 = new Scanner(myObj1);
            String dataA1 = myReader1.nextLine();
            myReader1.close();
            File myObj2 = new File("src/A2.txt");
            Scanner myReader2 = new Scanner(myObj2);
            String dataA2 = myReader2.nextLine();
            myReader2.close();

            String[] stringA1 = dataA1.split(",");
            String[] stringA2  = dataA2.split(",");

            int[] A1 = new int[stringA1.length];
            int[] A2 = new int[stringA2.length];
            int[] Result = new int[stringA2.length];
            for(int i = 0;i<stringA1.length;i++){
                A1[i] = Integer.parseInt(stringA1[i]);
                A2[i] = Integer.parseInt(stringA2[i]);
            }

            int total_size = A1.length;
            int[] process_size = {total_size/4};
            //broadcast the process_size
            MPI.COMM_WORLD.Bcast(process_size, 0, 1, MPI.INT, 0) ;
            int[] dummy = new int [process_size[0]];
            // Scatter
            MPI.COMM_WORLD.Scatter(A1,0, process_size[0], MPI.INT, dummy, 0, process_size[0], MPI.INT, 0);
            MPI.COMM_WORLD.Scatter(A2,0, process_size[0], MPI.INT, dummy, 0, process_size[0], MPI.INT, 0);
            // Gather
            MPI.COMM_WORLD.Gather(dummy,0,process_size[0],MPI.INT,Result,0,2,MPI.INT,0);

            // Sum
            for(int i = 0;i<process_size[0];i++){
                Result[i] = A1[i] + A2[i];
            }

            // Print Final Result
            System.out.println(Arrays.toString(Result));
        }
        else{

            //recive size
            int[] message = new int[1];
            MPI.COMM_WORLD.Bcast(message, 0, 1, MPI.INT, 0) ;
            int size = message[0];
            //recieve scatter
            int []  dummy = new int [size*4];
            int []	A1 = new int[size];
            int []	A2 = new int[size];
            MPI.COMM_WORLD.Scatter(dummy,0, size, MPI.INT, A1, 0, size, MPI.INT, 0);
            MPI.COMM_WORLD.Scatter(dummy,0, size, MPI.INT, A2, 0, size, MPI.INT, 0);
            //sum
            int [] result = new int[size];
            for(int i = 0;i<size;i++){
                result[i] = A1[i] + A2[i];
            }
            // send gather
            MPI.COMM_WORLD.Gather(result,0,size,MPI.INT,dummy,0,size,MPI.INT,0);


        }

        // everything between
        MPI.Finalize();
    }

}
