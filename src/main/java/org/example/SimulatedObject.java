package org.example;

import java.util.ArrayList;
import java.util.List;

public class SimulatedObject {
    int id;
    List<SimulatedObject> references = new ArrayList<>();
    boolean marked = false;
}
