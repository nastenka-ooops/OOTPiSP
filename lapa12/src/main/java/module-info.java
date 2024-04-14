import com.example.lapa12.BasicPluginRealisation;
import com.example.lapa12.PluginImplementation;

module lapa12 {
    requires transitive javafx.controls;
    requires com.fasterxml.jackson.databind;

    exports com.example.lapa12;
    exports com.example.lapa12.heros;

    uses PluginImplementation;

    provides PluginImplementation with BasicPluginRealisation;
}