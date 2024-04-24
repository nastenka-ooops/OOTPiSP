import com.example.lapa12.BasicPluginRealisation;
import com.example.lapa12.PluginImplementation;

module lapa12 {
    requires transitive javafx.controls;
    requires transitive javafx.base;
    requires transitive com.fasterxml.jackson.databind;
    requires transitive org.json;
    requires transitive com.fasterxml.jackson.dataformat.xml;


    exports com.example.lapa12;
    exports com.example.lapa12.heros;
    exports com.example.lapa12.factories;

    uses PluginImplementation;

    provides PluginImplementation with BasicPluginRealisation;
}