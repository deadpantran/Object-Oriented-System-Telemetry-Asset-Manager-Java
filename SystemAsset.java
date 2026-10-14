// Defense Resource & Telemetry Management System: simulates a real-time
// monitoring system using fundamental Java object-oriented principles.
// Step 1: Core Domain Model & Polymorphism
// Encapsulation: Abstract Base Class
// An abstract class cannot be instantiated; it represents a generic concept and
// allows the use of abstract methods (methods with no body).
public abstract class SystemAsset{
    // Data Fields: private data cannot be modified directly by subclasses.
    private final String id;
    private final String name;
    private String status;

    // Constructor
    public SystemAsset(String id, String name){
        this.id = id;
        this.name = name;
        this.status = "INITIALIZING";
    }

    // Methods
    public String getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public String getStatus(){
        return status;
    }
    public void setStatus(String status){
        this.status = status;
    }

    // Polymorphic method to be overriden by subclasses
    public abstract void performSelfDiagnostics();
}