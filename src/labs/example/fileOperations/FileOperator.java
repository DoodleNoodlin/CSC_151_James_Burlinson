//James Burlinson, this program reads a users.csv file to print a value, 4/2/26

package labs.example.fileOperations;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class FileOperator {
    
    private static final String CSV_FILE_PATH = "src/labs/example/fileOperations/files/users.csv";
    private static final String ERROR_LOG_PATH = "src/labs/example/fileOperations/logs/csv_error.log";
    private static final DateTimeFormatter TIMESTAMP_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    //Read CSV file and process student grades
    public static void main(String[] args) {
        ensureErrorLogExists();
        
        try {
            List<StudentGrade> students = readStudentData();
            
            // Print results
            if (students.isEmpty()) {
                System.out.println("No student data found in the CSV file.");
            } else {
                System.out.println("=== Student Grade Averages ===");
                System.out.println();
                for (StudentGrade student : students) {
                    System.out.printf("%s: %.2f%n", student.getName(), student.getAverageGrade());
                }
            }
            
        } catch (IOException e) {
            String errorMessage = "Error reading CSV file: " + e.getMessage();
            System.err.println(errorMessage);
            logError(errorMessage, e);
        }
    }
    
    // Reads student data from the CSV file
    private static List<StudentGrade> readStudentData() throws IOException {
        List<StudentGrade> students = new ArrayList<>();
        File csvFile = new File(CSV_FILE_PATH);
        
        if (!csvFile.exists()) {
            String errorMessage = "CSV file not found at: " + CSV_FILE_PATH;
            System.err.println(errorMessage);
            logError(errorMessage, null);
            return students;
        }
        
        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
            String line;
            boolean isHeader = true;
            
            while ((line = reader.readLine()) != null) {
                if (isHeader) {
                    isHeader = false;
                    continue;
                }
                
                StudentGrade student = parseStudentLine(line);
                if (student != null) {
                    students.add(student);
                }
            }
        }
        
        return students;
    }
    
    //Parse a CSV line to create a grade object
    private static StudentGrade parseStudentLine(String line) {
        try {
            String[] parts = line.split(",");
            
            if (parts.length < 2) {
                logError("Invalid CSV line format: " + line, null);
                return null;
            }
            
            String name = parts[0].trim();
            double[] grades = new double[parts.length - 1];
            
            for (int i = 1; i < parts.length; i++) {
                try {
                    grades[i - 1] = Double.parseDouble(parts[i].trim());
                } catch (NumberFormatException e) {
                    logError("Invalid grade value for " + name + ": " + parts[i], e);
                    return null;
                }
            }
            
            return new StudentGrade(name, grades);
            
        } catch (Exception e) {
            logError("Error parsing CSV line: " + line, e);
            return null;
        }
    }
    
    //Ensure error log file exists, create if it doesn't
    private static void ensureErrorLogExists() {
        File errorLogFile = new File(ERROR_LOG_PATH);
        
        try {
            // Create parent directories if they don't exist
            File parentDir = errorLogFile.getParentFile();
            if (parentDir != null && !parentDir.exists()) {
                parentDir.mkdirs();
            }
            
            // Create file if it doesn't exist
            if (!errorLogFile.exists()) {
                errorLogFile.createNewFile();
                System.out.println("Created error log file: " + ERROR_LOG_PATH);
            }
        } catch (IOException e) {
            System.err.println("Failed to create error log file: " + e.getMessage());
        }
    }
    
    //Logs error message to the error log file
    private static void logError(String message, Exception e) {
        try (FileWriter fw = new FileWriter(ERROR_LOG_PATH, true);
             BufferedWriter writer = new BufferedWriter(fw)) {
            
            String timestamp = LocalDateTime.now().format(TIMESTAMP_FORMAT);
            writer.write("[" + timestamp + "] " + message);
            
            if (e != null) {
                writer.write(" - " + e.getClass().getSimpleName() + ": " + e.getMessage());
            }
            
            writer.newLine();
            
        } catch (IOException ioException) {
            System.err.println("Failed to write to error log: " + ioException.getMessage());
        }
    }
    
    // Class to represent student and grades
    private static class StudentGrade {
        private String name;
        private double[] grades;
        
        public StudentGrade(String name, double[] grades) {
            this.name = name;
            this.grades = grades;
        }
        
        public String getName() {
            return name;
        }
        
        public double getAverageGrade() {
            if (grades.length == 0) {
                return 0.0;
            }
            
            double sum = 0;
            for (double grade : grades) {
                sum += grade;
            }
            
            return sum / grades.length;
        }
    }
}
