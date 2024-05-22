import com.example.lapa12.PluginImplementation;
import org.example.archiveplugin.ZipperAdapter;

module zip_plugin {
    requires java.desktop;
    requires lapa12;

    provides PluginImplementation with ZipperAdapter;
}