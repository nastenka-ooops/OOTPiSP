import com.example.lapa12.PluginImplementation;
import com.example.transformingxmlintojson.TransformingXMLintoJSON;

module com.example.transformingxmlintojson {
    requires lapa12;

    provides PluginImplementation with TransformingXMLintoJSON;
}