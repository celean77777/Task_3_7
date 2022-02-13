import java.lang.reflect.InvocationTargetException;

public class TestApp {
    public static void main(String[] args) {

        Class aClass = ClassForTesting.class;

        try {
            TestClass.start(aClass);
        } catch (InvocationTargetException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }

}
