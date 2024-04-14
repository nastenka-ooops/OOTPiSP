package com.example.lapa12;

import java.util.List;
import java.util.ServiceLoader;
import java.util.ServiceLoader.Provider;
import java.util.stream.Collectors;

public interface PluginRealisation {
    void doSmth();

    static List<PluginRealisation> getServices(ModuleLayer layer) {
        return ServiceLoader
                .load(layer, PluginRealisation.class)
                .stream()
                .map(Provider::get)
                .collect(Collectors.toList());
    }
}
