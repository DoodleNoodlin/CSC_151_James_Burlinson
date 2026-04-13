package labs.example.fileOperations;

import java.io.*;
import java.util.ArrayList;

public class Logger {
    
    public static void main(String[] args) {
        // Open file and get BufferedReader handle
        BufferedReader file = openErrorLog();
        
        // Call getCountOfErrorTypes with file handle
        getCountOfErrorTypes(file);
        
        BufferedReader file2 = openErrorLog();
        
        // Count memory limits exceeded
        getMemoryLimitExceededCount(file2);
    }
    
    // Opens error log file and returns a BufferedReader
    private static BufferedReader openErrorLog() {
        try {
            FileReader fileReader = new FileReader("src/labs/example/fileOperations/logs/api_error.log");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            return bufferedReader;
        } catch (FileNotFoundException e) {
            System.err.println("Error: File not found - " + e.getMessage());
            return null;
        }
    }
    
    // Counts number of issue entries in the file
    private static void getCountOfErrorTypes(BufferedReader file) {
        if (file == null) {
            System.err.println("Error: File handle is null");
            return;
        }
        
        int errorCount = 0;
        int warnCount = 0;
        int infoCount = 0;
        int debugCount = 0;
        String line;
        
        try {
            while ((line = file.readLine()) != null) {
                if (line.contains("[ERROR]")) {
                    errorCount++;
                } else if (line.contains("[WARN]")) {
                    warnCount++;
                } else if (line.contains("[INFO]")) {
                    infoCount++;
                } else if (line.contains("[DEBUG]")) {
                    debugCount++;
                }
            }
            
            System.out.println("=== Error Type Counts ===");
            System.out.println("[ERROR] count: " + errorCount);
            System.out.println("[WARN] count: " + warnCount);
            System.out.println("[INFO] count: " + infoCount);
            System.out.println("[DEBUG] count: " + debugCount);
            System.out.println("========================\n");
            
            file.close();
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
    
    // Counts times memory limit is exceeded and endpoints
    private static void getMemoryLimitExceededCount(BufferedReader file) {
        if (file == null) {
            System.err.println("Error: File handle is null");
            return;
        }
        
        ArrayList<String> endpoints = new ArrayList<>();
        int memoryLimitCount = 0;
        String line;
        
        try {
            while ((line = file.readLine()) != null) {
                if (line.contains("Memory limit exceeded")) {
                    memoryLimitCount++;
                    // Extract the endpoint
                    int endpointIndex = line.indexOf("Endpoint: ");
                    if (endpointIndex != -1) {
                        String endpoint = line.substring(endpointIndex + 10); // "Endpoint: ".length() = 10
                        endpoints.add(endpoint);
                    }
                }
            }
            
            // Print memory limit exceeded count and endpoints
            System.out.println("=== Memory Limit Exceeded ===");
            System.out.println("Total occurrences: " + memoryLimitCount);
            System.out.println("Affected Endpoints:");
            for (String endpoint : endpoints) {
                System.out.println("  - " + endpoint);
            }
            System.out.println("=============================\n");
            
            file.close();
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
}
