package coe318.lab8;
import java.util.ArrayList;
import java.util.Scanner;

public class Circuit {
    private CircuitInterface ui;
    private boolean userDone;
    private ArrayList<Resistor> resistors = new ArrayList<>();
    private ArrayList<Voltage> voltages = new ArrayList<>();
    
    
    public Circuit (CircuitInterface ui){
        this.ui = ui;
        ui.setCircuit(this);
        this.userDone = false;
        
    }
    
    public void start(){
        //print instructions to the user
        System.out.println("Enter your values with a space in between each");
        System.out.println("Enter 'end' if you are done entering values");
        System.out.println("Enter 'spice' if you would like to see the values you entered so far in spice format\n");
        System.out.println("-----------------------------------------\n");
        
        //the program will continue to run until the user ends the program
        while (!userDone){
            //read the user input and save it to the variable "input"
            String input = ui.getUserInput();
            //check if the user has ended the program
            if ("end".equals(input)){
                //set the userDone variable to false so it exits the while loop
                userDone = true;
                //output the all done message to the user
                 end();
            }
            //check if user has enter spice
            else if ("spice".equals(input)){
                ui.spiceDisplay();
            //if not, the user has entered values for a resistor or voltage
            } else{
                decompose(input);
            }
        }
    }
    
    //when called, will out put the "All done" message to the user
    public void end(){
        ui.userDone();
    }
    
    //method that will split the user input into each element
    public void decompose(String line){
        //if the user enters nothing, exit the method because there is nothing to decompose
        if (line.isEmpty()) return;
        Scanner sc = new Scanner(line);
        //reads the first character the user entered in the line
        String type = sc.next();    
        //reads the next character and converts it to int
        int n1 = sc.nextInt();      
        int n2 = sc.nextInt();  
        //reads the next character and converts it to double
        double value = sc.nextDouble();

        //check if the element entered is a resistor or a voltage source
        if ("r".equals(type)) {
            //add a new resistor element to the array list
            resistors.add(new Resistor(n1, n2, value));
        } else if ("v".equals(type)) {
            //add a new voltage element to the array list
            voltages.add(new Voltage(n1, n2, value));
        }

    }
    
    public String getSpice() {
    String result = "";
    
    //iterate throught the array list of voltage sources
    //for each voltage source it will use the toStirng method from the resistor class to output the proper format for spice
    for (Voltage v : voltages) {
        result += v.toString() + "\n";
    }
    for (Resistor r : resistors) {
        result += r.toString() + "\n";
    }

    return result;
    }
    
    
    

}
