//James Burlinson, this file reads log files and analyzes the contents, 4/11/26

package labs.example.fileOperations;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Logger {
    
    public static void main(String[] args) {
        // Open file and get BufferedReader handle
        BufferedReader file = openErrorLog();
        
        // Call getCountOfErrorTypes with file handle
        getCountOfErrorTypes(file);
        
        BufferedReader file2 = openErrorLog();
        
        getMemoryLimitExceededCount(file2);
        
        BufferedReader file3 = openErrorLog();
        
        getDiskSpaceErrorsWithIPAddress(file3);
        
        BufferedReader httpFile = openErrorLog("http_access_log");
        
        getGMTOffset(httpFile);
        
        BufferedReader httpFile2 = openErrorLog("http_access_log");
        
        getHTTPCodes(httpFile2);
        
        BufferedReader httpFile3 = openErrorLog("http_access_log");
        
        getResponseSizes(httpFile3);
        
        BufferedReader httpFile4 = openErrorLog("http_access_log");
        
        groupHTTPMethodsAndEndPoints(httpFile4);
    }
    
    // Open error log file and return a BufferedReader
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
    
    // Overloaded method to open http_access_log
    private static BufferedReader openErrorLog(String filename) {
        if (!filename.equals("http_access_log")) {
            System.err.println("Error: Invalid filename argument");
            return null;
        }
        try {
            FileReader fileReader = new FileReader("src/labs/example/fileOperations/logs/http_access.log");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            return bufferedReader;
        } catch (FileNotFoundException e) {
            System.err.println("Error: File not found - " + e.getMessage());
            return null;
        }
    }
    
    // Count number of issue entries in file
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
    
    // Count times memory limit is exceeded
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
    
    // Finds disk space errors and print line numbers and IP addresses
    private static void getDiskSpaceErrorsWithIPAddress(BufferedReader file) {
        if (file == null) {
            System.err.println("Error: File handle is null");
            return;
        }
        
        String line;
        int lineNumber = 1;
        
        try {
            while ((line = file.readLine()) != null) {
                if (line.contains("Disk space")) {
                    int ipStart = line.indexOf("] ") + 2;
                    int ipEnd = line.indexOf(" - ", ipStart);
                    String ip = line.substring(ipStart, ipEnd);
                    
                    System.out.println("Disk space error on line " + lineNumber + " for IP Address: " + ip);
                }
                lineNumber++;
            }
            
            file.close();
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
    
    // Count distinct GMT offsets in http_access.log
    private static void getGMTOffset(BufferedReader file) {
        if (file == null) {
            System.err.println("Error: File handle is null");
            return;
        }
        
        Map<String, Integer> offsetCounts = new HashMap<>();
        String line;
        
        try {
            while ((line = file.readLine()) != null) {
                // Find GMT offset
                int plusIndex = line.indexOf('+');
                if (plusIndex != -1) {
                    String offset = line.substring(plusIndex, plusIndex + 5); // +0000
                    offsetCounts.put(offset, offsetCounts.getOrDefault(offset, 0) + 1);
                }
            }
            
            System.out.println("=== GMT Offsets ===");
            for (Map.Entry<String, Integer> entry : offsetCounts.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
            System.out.println("===================\n");
            
            file.close();
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
    
    // Count HTTP status codes by category
    private static void getHTTPCodes(BufferedReader file) {
        if (file == null) {
            System.err.println("Error: File handle is null");
            return;
        }
        
        int count2xx = 0, count3xx = 0, count4xx = 0, count5xx = 0;
        String line;
        
        try {
            while ((line = file.readLine()) != null) {
                String[] parts = line.split("\"");
                if (parts.length > 2) {
                    String afterRequest = parts[2].trim();
                    String[] afterParts = afterRequest.split("\\s+");
                    if (afterParts.length > 0) {
                        String status = afterParts[0];
                        if (status.startsWith("2")) {
                            count2xx++;
                        } else if (status.startsWith("3")) {
                            count3xx++;
                        } else if (status.startsWith("4")) {
                            count4xx++;
                        } else if (status.startsWith("5")) {
                            count5xx++;
                        }
                    }
                }
            }
            
            System.out.println("5xx Errors: " + count5xx);
            System.out.println("2xx Errors: " + count2xx);
            System.out.println("3xx Errors: " + count3xx);
            System.out.println("4xx Errors: " + count4xx);
            System.out.println();
            
            file.close();
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
    
    private static void getResponseSizes(BufferedReader file) {
        if (file == null) {
            System.err.println("Error: File handle is null");
            return;
        }
        
        int count = 0;
        String line;
        
        try {
            while ((line = file.readLine()) != null) {
                String[] parts = line.split("\"");
                if (parts.length > 2) {
                    String afterRequest = parts[2].trim();
                    String[] afterParts = afterRequest.split("\\s+");
                    if (afterParts.length > 1) {
                        try {
                            int size = Integer.parseInt(afterParts[1]);
                            if (size > 3900) {
                                count++;
                            }
                        } catch (NumberFormatException e) {
                        }
                    }
                }
            }
            
            System.out.println("Number of log lines with response size > 3900: " + count);
            System.out.println();
            
            file.close();
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
    
    // Group and print unique HTTP methods
    private static void groupHTTPMethodsAndEndPoints(BufferedReader file) {
        if (file == null) {
            System.err.println("Error: File handle is null");
            return;
        }
        
        ArrayList<String> methods = new ArrayList<>();
        String line;
        
        try {
            while ((line = file.readLine()) != null) {
                String[] parts = line.split("\"");
                if (parts.length > 1) {
                    String request = parts[1].trim();
                    String[] requestParts = request.split("\\s+");
                    if (requestParts.length > 0) {
                        String method = requestParts[0];
                        if (!methods.contains(method)) {
                            methods.add(method);
                        }
                    }
                }
            }
            
            System.out.println("Unique HTTP Methods:");
            for (String method : methods) {
                System.out.println(method);
            }
            System.out.println();
            
            file.close();
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
}
