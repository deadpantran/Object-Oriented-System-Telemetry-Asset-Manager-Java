// Inheritance & Polymorphism: Subclass A
// 'extends' creates a subclass. 'implements' implements an interface.
public class RadarSensorAsset extends SystemAsset{
    // Data Fields: includes inherited (not shown)
    private double frequencyGHz;

    // Constructor
    public RadarSensorAsset(String id, String name, double frequencyGHz){
        // Because id and name are declared as private fields in the parent class (SystemAsset),
        // RadarSensorAsset does not have direct access to assign them itself.
        // Using super(id, name) passes those values up to the parent class' constructor.
        super(id, name);
        this.frequencyGHz = frequencyGHz;
    }

    // Override the abstract method and use the inherited methods
    // to modify private date in the superclass.
    @Override
    public void performSelfDiagnostics(){
        if (frequencyGHz > 1.0 && frequencyGHz < 10.0){
            setStatus("NOMINAL");
        } else{
            setStatus("DEGRADED");
        }
    }
}