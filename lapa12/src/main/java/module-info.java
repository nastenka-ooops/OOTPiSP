import com.example.lapa12.PluginRealisation;
import com.example.lapa12.BasicPluginRealisation;

module lapa12 {
    requires javafx.controls;
    requires com.fasterxml.jackson.databind;

    exports com.example.lapa12;

    uses PluginRealisation;

    provides PluginRealisation with BasicPluginRealisation;
}