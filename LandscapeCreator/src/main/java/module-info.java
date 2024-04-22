import com.example.landscapecreator.LandscapeCreator;
import com.example.lapa12.PluginImplementation;

module com.example.landscapecreator {
    requires lapa12;

    provides PluginImplementation with LandscapeCreator;
}