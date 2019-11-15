package encryption;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.Runtime;
import java.util.Scanner;

public class ProcessBuilder {

    public static void main(String[] args) {
    	Scanner myObj = new Scanner(System.in);
    	
    	System.out.println("Inserire un valore: ");
    	String answer =myObj.nextLine();
    	
    	String obj = "Cassandra";
		if(answer.equals(obj)) {
    	try
        {    
            String target = new String("../ProgettoCassandra/avvio.sh");
            Runtime rt = Runtime.getRuntime();
            Process proc = rt.exec(target);

        } catch (Throwable t)
        {
            t.printStackTrace();
        } 
    	}else {
    		System.out.println("FAIL");
    	}
    }


}