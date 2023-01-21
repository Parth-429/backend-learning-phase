import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Version1 {
    private static int noOfGuess = 0;
    private ArrayList<Integer> location;

    void addLocation(int loc){
        location.add(loc);
        location.add(loc+1);
        location.add(loc+2);
    }
    boolean checkDotCom(int loc){
        for(int cell : location){
            if(loc == cell){
                location.remove(Integer.valueOf(cell));
//                System.out.println(location.size());
                if (location.size() != 0) {
                    System.out.println("hit");
                    return false;
                } else {
                    System.out.println("kill");
                    return true;
                }
            }
        }
        System.out.println("miss");
        return false;
    }

    void incrementNoOfGuess(){
        noOfGuess++;
    }

    int getNoOgGuess(){
        return noOfGuess;
    }
    public static void main(String[] args){
        int lenOfArray;
        int locOfShip;
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter length of cells (Minimum length should be 3): ");
        lenOfArray = sc.nextInt();

        Random rt = new Random();
        locOfShip = rt.nextInt(0,lenOfArray-2);

        Version1 obj = new Version1();
        obj.location = new ArrayList<Integer>(lenOfArray);
        obj.addLocation(locOfShip);

        while(true){
            System.out.println("Enter a location: ");
            int cellLocation = sc.nextInt();
            obj.incrementNoOfGuess();
            if(obj.checkDotCom(cellLocation)){
                System.out.println("You took " + obj.getNoOgGuess() + " guesses.");
                return;
            }
        }
    }
}