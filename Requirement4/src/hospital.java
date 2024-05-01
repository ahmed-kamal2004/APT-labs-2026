import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class hospital {
        private static final String  FILEPATH = "doctors.txt";
        private ArrayList<doctor>doctors;

    public hospital() throws FileNotFoundException {
        this.doctors = new ArrayList<>();
        // ID NAME TIMEslots
        File myfile = new File(FILEPATH);
        Scanner scanner = new Scanner(myfile);
        while(scanner.hasNextLine()){
            String[] doctor_data = scanner.nextLine().split(" ");
            this.doctors.add(new doctor(Integer.parseInt(doctor_data[0]),doctor_data[1],Integer.parseInt(doctor_data[2])));
        }
        scanner.close();
    }

    public int MakeAppointment(int id,int timeslot,String patient){
        for(doctor d : this.doctors){
            if(d.getId() == id){
                return d.add_appointment(patient,timeslot);
            }
        }
        return 2;
    }

    public int CancelAppointment(int id,int timeslot,String patient){
        for(doctor d : this.doctors){
            if(d.getId() == id){
                return d.cancel_appointment(patient,timeslot);
            }
        }
        return 2;
    }
    public void printDoctors(){
        for(doctor Doc : this.doctors){
            Doc.printData();
        }
    }
}
