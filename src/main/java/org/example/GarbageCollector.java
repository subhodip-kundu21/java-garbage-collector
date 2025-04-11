package org.example;

public class GarbageCollector {

    private final Heap heap;

    public GarbageCollector(Heap heap) {
        this.heap = heap;
    }

    public void mark() {
        System.out.println("Marking un-referenced objects");

        heap.markAll();

        // we have to unmark all the root objects, shouldn't remove those
        heap.rootObject.forEach(SimulatedObject::unmark);
    }

    public void sweep() {

        heap.removeMarked();

        System.out.println("GC sweep completed");
    }
}
