import com.example.hierarchyextension.HierarchyExtension;
import com.example.lapa12.PluginImplementation;

module com.example.hierarchyextension {
    requires lapa12;

    exports com.example.hierarchyextension;
    exports com.example.hierarchyextension.heros;

    provides PluginImplementation with HierarchyExtension;
}