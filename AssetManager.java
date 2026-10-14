import java.util.*; // List and ArrayList
import java.io.*; // IOException

// Step 3: Application Manager (Controlled)
public class AssetManager{
    // Data Fields: contains a list
    private final List<SystemAsset> assetRegistry = new ArrayList<>();
    private final TelemetryLogger logger;

    // Constructor: assign a physical file.
    public AssetManager(TelemetryLogger logger){
        this.logger = logger;
    }

    // Populate the list
    public void registerAsset(SystemAsset asset){
        assetRegistry.add(asset);
    }

    // pass the list to the file to record it.
    public void runSystemCheck(){
        // enhanced for loop to update each asset's status.
        for (SystemAsset asset : assetRegistry){
            asset.performSelfDiagnostics();
        }
        // pass the list of assets to the FileWriter to record it to a physical file.
        try{
            logger.exportLog(assetRegistry);
            System.out.println("System diagnostic cycle complete. Logs exported successfully.");
        } catch (IOException e){
            System.err.println("Diagnostic reporting failed.");
        }
    }
}