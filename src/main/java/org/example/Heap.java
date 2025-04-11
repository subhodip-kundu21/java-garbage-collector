package org.example;

import org.example.customExceptions.ObjectNotFound;

import java.util.*;

public class Heap {
    private final Map<Integer, SimulatedObject> simulatedHeap = new HashMap<>();
    List<SimulatedObject> rootObject = new ArrayList<>();
    private int objectId;

    Heap() {
        objectId = 0;
    }

    public int createObject() {
        // first create a simulated object then add it to heap
        SimulatedObject newObj = new SimulatedObject(++objectId);
        simulatedHeap.put(newObj.getId(), newObj);
        return newObj.getId();
    }

    public void addToRootObjects(int id) throws ObjectNotFound {
        if (simulatedHeap.containsKey(id)) {
            rootObject.add(simulatedHeap.get(id));
        } else {
            throw new ObjectNotFound(String.format("Object with id %d not found", id));
        }
    }

    public SimulatedObject getObjectById(Integer id) throws ObjectNotFound {
        if (simulatedHeap.containsKey(id)) {
            return simulatedHeap.get(id);
        } else {
            throw new ObjectNotFound(String.format("Object with id %d not found", id));
        }
    }

    public Collection<SimulatedObject> getAllObject() {
        return simulatedHeap.values();
    }

    public void markAll() {
        for (SimulatedObject obj : simulatedHeap.values()) {
            obj.mark();
        }
    }

    public void deleteObject(int id) throws ObjectNotFound {
        SimulatedObject obj = getObjectById(id);
        obj.mark();
    }

    public void removeMarked() {
        simulatedHeap.values().removeIf(obj -> {
            if (obj.isMarked()) {
                System.out.printf("Removed object id: %d%n", obj.getId());
                return true;
            } else {
                return false;
            }
        });
    }
}
