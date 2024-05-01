import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class client {
    public static final int MAKE_APPOINTMENT_PORT = 6666;
    public static final int CANCEL_APPOINTMENT_PORT = 6667;

    public static void main(String args[]) throws IOException  //static method
    {

            System.out.println("Client started");
            Socket s_make = new Socket("localhost", MAKE_APPOINTMENT_PORT);
            Socket s_cancel = new Socket("localhost", CANCEL_APPOINTMENT_PORT);
            PrintWriter out_make = new PrintWriter(s_make.getOutputStream(), true);
            BufferedReader socketReader_make = new BufferedReader(new InputStreamReader(s_make.getInputStream()));
            PrintWriter out_cancel = new PrintWriter(s_cancel.getOutputStream(), true);
            BufferedReader socketReader_cancel = new BufferedReader(new InputStreamReader(s_cancel.getInputStream()));
            Scanner scanner = new Scanner(System.in);

            System.out.println("Enter Your name :");
            String patient_name = scanner.nextLine();
            while(true){
                System.out.println("How can I help you : (1) Make appointment (2) Cancel appointment\n");

                int c = Integer.parseInt(scanner.nextLine());

                System.out.println("Enter Your Doctor ID :");
                int doctor_id = Integer.parseInt(scanner.nextLine()) ;
                // time slot
                System.out.println("Enter Time Slot :");
                int timeslot = Integer.parseInt(scanner.nextLine()) ;

                String output = null;
                if(c == 1){
                    out_make.println(patient_name+","+doctor_id+","+timeslot);
                    output = socketReader_make.readLine();
                }else{
                    out_cancel.println(patient_name+","+doctor_id+","+timeslot);
                    output = socketReader_cancel.readLine();
                }

                System.out.println("The Recieved message is " + output);
            }

//            scanner.close();
//            out_make.close();
//            out_cancel.close();
//            socketReader_cancel.close();
//            socketReader_make.close();
//            s_cancel.close();
//            s_make.close();

    }
}
