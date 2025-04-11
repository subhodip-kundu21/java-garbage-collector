package org.example;

import org.example.customExceptions.ObjectNotFound;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        try {
            File file = new File("GC-Output.txt");
            PrintStream fileOut = new PrintStream(file);

            System.setOut(fileOut);


            System.out.println("Testing GC!");

            Heap heap = new Heap();

            for (int i=0; i<7; i++) System.out.println("Object created with id: " + heap.createObject());

            try {
                SimulatedObject obj = heap.getObjectById(2);
                obj.addReference(heap.getObjectById(3));
                obj.addReference(heap.getObjectById(4));
                obj.addReference(heap.getObjectById(9));
            } catch (ObjectNotFound e) {
                System.out.println(e.getMessage());
            }

            try {
                SimulatedObject obj = heap.getObjectById(4);
                obj.addReference(heap.getObjectById(5));
                obj.addReference(heap.getObjectById(7));
            } catch (ObjectNotFound e) {
                System.out.println(e.getMessage());
            }


            try {
                heap.addToRootObjects(1);
                heap.addToRootObjects(2);
            } catch (ObjectNotFound e) {
                System.out.println(e.getMessage());
            }


            Collection<SimulatedObject> allObjectsBefore = heap.getAllObject();
            for (SimulatedObject obj : allObjectsBefore) {
                System.out.print(obj.getId());
                List<SimulatedObject> references = obj.getReferences();
                if (!references.isEmpty()) {
                    System.out.print(" Referenced Objects are: ");
                    references.forEach(ref -> System.out.print(ref.getId() + " "));
                }
                System.out.println();
            }

            GarbageCollector gc = new GarbageCollector(heap);

            gc.mark();

            // trying to delete some objects
            try {
                heap.deleteObject(5);
                heap.deleteObject(7);
                heap.deleteObject(10);
            } catch (ObjectNotFound e) {
                System.out.println(e.getMessage());
            }

            gc.sweep();

            Collection<SimulatedObject> allObjectsAfter = heap.getAllObject();
            for (SimulatedObject obj : allObjectsAfter) {
                System.out.println(obj.getId());
                List<SimulatedObject> references = obj.getReferences();
                if (!references.isEmpty()) {
                    System.out.print("Referenced Objects are: ");
                    references.forEach(ref -> System.out.println(ref.getId() + " "));
                }
            }

            fileOut.flush();
            fileOut.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}