import java.util.ArrayList;
import java.util.List;

public class DataProcessor {

    private String mainTitle;
    private int maxDataPoints = 50;
    private double averageValue;
    private boolean isProcessingComplete;
    private List<String> dataEntries;
    private List<Integer> processedValues;

    public DataProcessor(String mTitle) {
        this.mainTitle = mTitle;
        this.dataEntries = new ArrayList<>();
        this.processedValues = new ArrayList<>();
        this.isProcessingComplete = false;
    }

    public void addDataEntry(String entry) {
        if (dataEntries.size() < maxDataPoints) {
            dataEntries.add(entry);
        } else {
            System.out.println("Maximum data entry limit reached.");
        }
    }

    public void processData() {
        if (dataEntries.isEmpty()) {
            System.out.println("No data entries to process.");
            return;
        }

        int sum = 0;
        int count = 0;

        System.out.println("Processing data entries:");
        for (String data : dataEntries) {
            String[] parts = data.split(",");
            for (String part : parts) {
                try {
                    int value = Integer.parseInt(part.trim());
                    processedValues.add(value);
                    sum += value;
                    count++;
                    System.out.println("Processed value: " + value);
                } catch (NumberFormatException e) {
                    System.out.println("Warning: Invalid number format - " + part);
                }
            }
        }

        if (count > 0) {
            averageValue = (double) sum / count;
            System.out.println("Average processed value: " + averageValue);
        } else {
            averageValue = 0;
            System.out.println("No valid numbers found in the data entries.");
        }
        isProcessingComplete = true;
    }

    public void displayProcessedValues() {
        if (isProcessingComplete) {
            System.out.println("\n--- Processed Values ---");
            int index = 0;
            while (index < processedValues.size()) {
                System.out.println("Value at index " + index + ": " + processedValues.get(index));
                index++;
            }
        } else {
            System.out.println("Data processing has not been completed yet.");
        }
    }

    public String getMainTitle() {
        return mainTitle;
    }

    public int getMaxDataPoints() {
        return maxDataPoints;
    }

    public double getAverageValue() {
        return averageValue;
    }

    public boolean isProcessingComplete() {
        return isProcessingComplete;
    }

    public List<String> getDataEntries() {
        return dataEntries;
    }

    public List<Integer> getProcessedValues() {
        return processedValues;
    }

    public static void main(String[] args) {
        DataProcessor processor = new DataProcessor("Sample Data Analysis");
        processor.addDataEntry("10, 20,30");
        processor.addDataEntry("40, 50, 60");
        processor.addDataEntry("70,80, 90");
        processor.processData();
        processor.displayProcessedValues();
        System.out.println("Main Title: " + processor.getMainTitle());
        System.out.println("Max Data Points: " + processor.getMaxDataPoints());
        System.out.println("Processing Complete: " + processor.isProcessingComplete());
        System.out.println("Average: " + processor.getAverageValue());
    }
}