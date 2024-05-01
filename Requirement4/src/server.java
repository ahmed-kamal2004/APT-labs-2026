import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class server {
    public static final int MAKE_APPOINTMENT_PORT = 6666;
    public static final int CANCEL_APPOINTMENT_PORT = 6667;

    private static final hospital Hospital;

    static {
        try {
            Hospital = new hospital();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public server() throws FileNotFoundException {
    }

    public static void main(String args[]) throws FileNotFoundException  //static method
    {
        System.out.println("The server started .. ");

        new Thread() {
            public void run() {
                try {
                    ServerSocket ss = new ServerSocket(MAKE_APPOINTMENT_PORT);
                    while (true) {
                        new AppointmentControl(ss.accept()).start();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();



        new Thread() {
            public void run() {
                try {
                    ServerSocket ss = new ServerSocket(CANCEL_APPOINTMENT_PORT);
                    while (true) {
                        new AppointmentControl(ss.accept()).start();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private static class AppointmentControl extends Thread{

        private Socket socket;

        public AppointmentControl(Socket s) {
            this.socket = s;
        }

        public void run(){
            try {

                BufferedReader br = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
                PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);
                while(true){
                String[] message = br.readLine().split(",");

                String patient_name = message[0];
                int doctor_id = Integer.parseInt(message[1]);
                int timeslot = Integer.parseInt(message[2]);
                String OutputMessage = null;
                int done = 0;
                if (this.socket.getLocalPort() == MAKE_APPOINTMENT_PORT) {
                    done = Hospital.MakeAppointment(doctor_id, timeslot, patient_name);
                } else {
                    done = Hospital.CancelAppointment(doctor_id, timeslot, patient_name);
                }
                switch (done){
                    case 1:
                        OutputMessage = "Making the appointment is done successfully (Success)";
                        Hospital.printDoctors();
                        break;
                    case 2 :
                        OutputMessage = "the doctor id is not found in hospital (Failure)";
                        break;
                    case 3:
                        OutputMessage = "the timeslot index is out of boundary (Failure)";
                        break;
                    case 4:
                        OutputMessage = "the doctor is already busy at this timeslot (Failure)";
                        break;
                    case 5:
                        OutputMessage = "Cancelling the appointment is done successfully (Success)";
                        Hospital.printDoctors();
                        break;
                    case 6:
                        OutputMessage = "the doctor doesn’t have an appointment at this timeslot (Failure)";
                        break;
                    case 7:
                        OutputMessage = "the doctor has an appointment to a different patient name at this timeslot (Failure)";
                }
                out.println(OutputMessage);
            }
//                out.close();
//                br.close();
            } catch (IOException e) {
                System.out.println("Error handling client# ");
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    System.out.println("Couldn't close a socket, what's going on?");
                }
                System.out.println("Connection with client# closed");
            }
        }
    }
}
