import com.example.encryption.Encryption;
import com.example.lapa12.PluginImplementation;

module com.example.encryption {
    requires lapa12;
    provides PluginImplementation with Encryption;
}