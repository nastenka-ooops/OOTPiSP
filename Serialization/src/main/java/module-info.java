import com.example.lapa12.PluginImplementation;
import com.example.serialization.Serialization;

module com.example.serialization {
    requires lapa12;
    requires com.example.hierarchyextension;

    exports com.example.serialization;

    provides PluginImplementation with Serialization;
}