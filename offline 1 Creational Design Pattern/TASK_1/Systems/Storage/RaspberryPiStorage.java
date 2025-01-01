package TASK_1.Systems.Storage;

public class RaspberryPiStorage implements Storage {
    @Override
    public void show() {
        System.out.println("Storing data on Raspberry Pi's storage.");
    }
}