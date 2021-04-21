package gfg;

import java.util.*;
import java.lang.*;
import java.io.*;

public class GFG {

    static final int M = 6;  //  M  Number of patients.
    static final int N = 6;  //  N  Number of Hospitals. 
    static boolean[] Visit = {false, false, false, false, false, false};
    
    // array of string  " name of hospital"
    static String[] Name_Of_Hospital = {"King AbdelazizUniversity","King Fahd", "East Jeddah",
                                        "King Fahad Armed Forces ","King Faisal Specialist",
                                        "Ministry of National Guard "};
    // array of string  " name of applicants"
    static String[] Name_Of_applicants = {"Ahme", "Mahmoud", "Ema", "Fatimah", "Kamel", "Nojoo"};

    
    //  recursive function that returns true if a matching for vertex u is possible
    boolean bpm(boolean bpGraph[][], int u, int matchR[]) {
        
        // Try every hosipitals N  one by one
        for (int v = 0; v < N; v++) {
            // If applicant u is interested in job v and v is not visited
            //System.out.println(">> before: "+v+", "+seen[v]);
            
            if (bpGraph[u][v] && !Visit[v]) {
                // Mark v as visited
                Visit[v] = true;
                
                // If job 'v' is not assigned to
                // an applicant OR previously
                // assigned applicant for job v (which
                // is matchR[v]) has an alternate job available.
                // Since v is marked as visited in the
                // above line, matchR[v] in the following
                // recursive call will not get job 'v' again

                if (matchR[v] < 0 || bpm(bpGraph, matchR[v], matchR)) {
                    //Maching every applicants and hospital 
                    matchR[v] = u;
                    return true;
                    
                }
            }
        }
        return false;
    }

    // Returns maximum number of matching from vertices M to vertices N
    int maxBPM(boolean bpGraph[][]) {

        // An array to keep track of the applicants assigned to hospital.
        // The value of matchR[i] is the applicant number assigned to hospital i,
        // the value -1 = nobody is assigned.
        int matchR[] = new int[N];

        // Initially all applicants not assign and all hospital are available
        for (int i = 0; i < N; ++i) {
            matchR[i] = -1;
        }

        // Count number of hospitals assigned to applicants
        int result = 0;
        // loop for applicants
        for (int u = 0; u < M; u++) {
            // Mark all hospitals as not visited for next applicant.
            boolean seen[] = new boolean[N];
            System.out.println("\n************************  itreation(" + u + ") *****************************");
            for (int i = 0; i < N; ++i) {

                seen[i] = false;
                if (matchR[i] > -1) {

                    System.out.printf("%d%shospital %-29s%s%s%s  \n", (i + 1), "- ", (Name_Of_Hospital[i]), "( Name of patient ", (Name_Of_applicants[matchR[i]]), ")");

                } else {

                    System.out.printf("%d%shospital %-30s%-30s   \n", (i + 1), "- ", (Name_Of_Hospital[i]), "==> no patient ");
                }

            }

            // Find if the applicant 'u' can get a hospital
            if (bpm(bpGraph, u, matchR)) {
                result++;
            }
        }
        return result;
    }

      //Main function 
    public static void main(String[] args) throws java.lang.Exception {
        
        // Boolean array that shows every patient's possible matches with available hospitals 
        boolean[][] Array = new boolean[][]{
            // Ahme mating with (King Abdelaziz University, King Abdelaziz University )
            {true, true, false, false, false, false},
            //Mahmoud mating with (Ministry of National Guard )
            {false, false, false, false, false, true},
            //Ema mating with (King Abdelaziz University, King Fahad Armed Forces )
            {true, false, false, true, false, false},
            //Fatima mating with (East Jeddah)
            {false, false, true, false, false, false},
            // keamel mating with (King Fahad Armed Forces,King Faisal Specialist )
            {false, false, false, true, true, false},
            //Nojoo mating with (Ministry of National Guard )
            {false, false, false, false, false, true}};
        
        GFG m = new GFG();
        System.out.println("\n" + "^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^\n"
                + "Maximum number of applicants that can" + " get job is " + m.maxBPM(Array)
                + "\n^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^\n");
    }

}
