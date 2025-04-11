package org.example;

import java.util.ArrayList;
import java.util.List;

public class SimulatedObject {
    private final int id;
    private final List<SimulatedObject> references = new ArrayList<>();
    private boolean marked = false;

    public SimulatedObject(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public List<SimulatedObject> getReferences() {
        return references;
    }

    public boolean isMarked() {
        return marked;
    }

    public void mark() {
        if (!marked) {
            marked = true;
            for (SimulatedObject obj : references) {
                obj.mark();
            }
        }
    }

    public void unmark() {
        if (marked) {
            this.marked = false;
            for (SimulatedObject obj : references) {
                obj.unmark();
            }
        }
    }

    public void addReference(SimulatedObject obj) {
        references.add(obj);
    }
}
