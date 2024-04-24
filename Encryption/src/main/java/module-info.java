import com.example.encryption.Encryption;
import com.example.lapa12.PluginImplementation;

module com.example.encryption {
    requires lapa12;
    requires com.example.hierarchyextension;
    provides PluginImplementation with Encryption;
}