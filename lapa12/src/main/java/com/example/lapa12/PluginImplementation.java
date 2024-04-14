package com.example.lapa12;

import java.util.List;
import java.util.ServiceLoader;
import java.util.ServiceLoader.Provider;
import java.util.stream.Collectors;

public interface PluginImplementation {
    void doSmth();

    static List<PluginImplementation> getServices(ModuleLayer layer) {
        return ServiceLoader
                .load(layer, PluginImplementation.class)
                .stream()
                .map(Provider::get)
                .collect(Collectors.toList());
    }
}
