// Step 4: Execution Entry Point
public class ObjectOrientedSystemDriver{
    public static void main(String[] args){
        //Data Fields: create the physical file and its name, and give the file to
        // the asset list (to be filled out when runSystemCheck() is called).
        TelemetryLogger logger = new TelemetryLogger("telemetry_output.log");
        AssetManager manager = new AssetManager(logger);

        // populate the list with polymorphic assets.
        manager.registerAsset(new RadarSensorAsset("RAD-01", "Primary Aegis Array", 5.5));
        manager.registerAsset(new RadarSensorAsset("RAD-02", "Auxiliary Targeter", 0.5));

        // Execute processing loop.
        // Fill out the physical file with data from each asset in the list.
        manager.runSystemCheck();
    }
}