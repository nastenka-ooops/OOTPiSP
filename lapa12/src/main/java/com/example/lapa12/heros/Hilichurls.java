package com.example.lapa12.heros;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class Hilichurls {
    @JsonProperty
    public List<Hilichurl> hilichurls = new ArrayList<>();

}
