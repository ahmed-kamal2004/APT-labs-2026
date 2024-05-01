import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Vector;

public class doctor {
    private int id;
    private String name;
    private List<Boolean> timeslots;
    private List<String> patients;


    // contructor

    public doctor(int id,String name,int t){
        System.out.println(id+" "+name+" "+t);
        this.id = id;
        this.name = name;
        this.timeslots = new ArrayList<>();
        this.patients = new ArrayList<>();
        for(int i = 0; i<t;i++){
            this.timeslots.add(false);
            this.patients.add("");
        }
//        System.out.println(this.timeslots.size()+ "  " + this.patients.size());
    }

    public synchronized int add_appointment(String name,int timeslot){
        if(timeslot < 0 || timeslot >= this.timeslots.size())
            return 3;
        if(!this.timeslots.get(timeslot)){
            this.timeslots.set(timeslot,true);
            this.patients.set(timeslot,name);
            return 1;
        }
        return 4;
    }

    public synchronized int cancel_appointment(String name,int timeslot){
        if(timeslot < 0 || timeslot >= this.timeslots.size())
            return 3;
        if(this.timeslots.get(timeslot)){
            if(Objects.equals(this.patients.get(timeslot), name)){
                this.timeslots.set(timeslot,false);
                this.patients.set(timeslot,"");
                return 5;
            }
            return 7;
        }
        return 6;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void printData(){
        System.out.println(" Doctor Name : " + this.name + " Doctor ID "+this.id);
        for(int i = 0;i<this.timeslots.size();i++){
            System.out.println(i+"  "+this.timeslots.get(i) + "   "+this.patients.get(i));
        }
    }
}
