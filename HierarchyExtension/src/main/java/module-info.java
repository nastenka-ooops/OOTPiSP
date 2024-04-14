import com.example.hierarchyextension.HierarchyExtension;
import com.example.lapa12.PluginImplementation;

module com.example.hierarchyextension {
    requires lapa12;

    provides PluginImplementation with HierarchyExtension;
}