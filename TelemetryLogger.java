// Step 2: Persistence Layer via File I/O & Exception Handling
// * tells Java to import all classes inside of java.io package at once.
import java.io.*; // IO Exception, BufferedWriter, FileWriter
//import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*; // List, Date

public class TelemetryLogger{
    //Testing 12-hour time display.
    // LocalTime's constructors are marked private because it is designed
    // to be instantiated via static factory methods instead. This is for the sake
    // of a descriptive name that communicates how or what is being created, such as '.now()'.
    //LocalTime now = LocalTime.now();
    ZonedDateTime now = ZonedDateTime.now(); // using ZonedDateTime instead of LocalTime will provide the date and time zone as well.
    // DateTimeFormatter is a class that prints date-time objects as text.
    // The static method 'ofPattern()' parses the string pattern you pass in,
    // compiles it into internal formatting rules, and returns a fully configured DateTimeFormatter instance.
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, MMMM dd, yyyy hh:mm:ss a z");

    //---//
    
    // Data Field: contains a physical file to write to (specifically, a string
    // that will be used as the name for file created below).
    // 'final' means the variable cannot be reassigned.
    private final String logFilePath;

    // Constructor
    public TelemetryLogger(String logFilePath){
        this.logFilePath = logFilePath;
    }

    // Export system state with custom exception handling
    public void exportLog(List<SystemAsset> assets) throws IOException{
        // new FileWriter(logFilePath, true) opens a connection to a
        // physical file located at logFilePath. 'true' tells Java to
        // append new data to the end of the existing file. 'false' would
        // overwrite the file every time you ran the program.

        // Disk I/O operations are slow and expensive (writing directly to a hard drive or SSD).
        // new BufferedWriter(...) wraps around the FileWriter and creates an in-memory
        // buffer (a temporary holding area in RAM). Instead of writing characters to the disk
        // one by one, BufferedWriter stores characters in memory until the buffer fills up, then
        // writes them to the disk in one batch (better performance)

        // Any object declared within the try(...) parenthesis that implements the AutoCloseable
        // interface (such as BufferedWriter, FileWriter, etc.) close when the try block finishes.
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(logFilePath, true))){
            // Enhanced for Loop: "For each System Asset (which I'll name asset) in the assets list..."
            for (SystemAsset asset : assets){
                //writer.write(...) sends a formatted string to the file via the BufferedWriter object (writer).
                //String.format() builds a string by substituting '%s' in the first string
                // parameter with each of the following parameters in sequential order.
                // new Date() creates a new Java Date object that captures
                // the exact date and time that the code runs.
                writer.write(String.format("[%s] ID: %s | Name: %s | Status: %s%n",
                now.format(formatter), asset.getId(), asset.getName(), asset.getStatus()));
                // instead of using "new Date()" to simply display 24-hour time,
                // we can use "now.format(formatter)" to display month, date, year, 12-hour time, and time zone.
            }
        } catch (IOException e){
            // e.getMessage() retrieves the specific error string detailing why an exception occurred.
            System.err.println("CRITICAL: Failed to write telemetry logs: " + e.getMessage());
        }
    }
}