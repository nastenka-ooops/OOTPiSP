import com.example.custompictures.Main;
import com.example.lapa12.PluginRealisation;

module com.example.custompictures {
    requires lapa12;

    provides PluginRealisation with Main;
}