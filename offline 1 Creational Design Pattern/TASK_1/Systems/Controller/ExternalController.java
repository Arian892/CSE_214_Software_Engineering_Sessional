package TASK_1.Systems.Controller;

public class ExternalController implements Controller {
    @Override
    public void show() {
        System.out.println("System is controlled using an external controller.");
    }
}
