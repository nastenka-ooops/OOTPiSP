import com.example.custompictures.CustomPictures;
import com.example.lapa12.PluginImplementation;

module com.example.custompictures {
    requires lapa12;

    provides PluginImplementation with CustomPictures;
}