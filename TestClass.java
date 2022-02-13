import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class TestClass {

    public static void start(Class aClass) throws InvocationTargetException, IllegalAccessException, InstantiationException {

        ClassForTesting clazz = (ClassForTesting)aClass.newInstance();
        Method[] methods = aClass.getDeclaredMethods();
        int numOfBeforeSuiteAnnotation = 1;
        int numOfAfterSuiteAnnotation = 1;

        for (Method m : methods) {
            if (numOfBeforeSuiteAnnotation >= 0) {
                if (m.getAnnotation(BeforeSuite.class) != null) {
                    int modifier = m.getModifiers();
                    if (Modifier.isPrivate(modifier)) {
                        m.setAccessible(true);
                        System.out.println("Тест метода: " + m.getName() + " с аннотацией BeforeSuite: " + m.invoke(clazz));
                        m.setAccessible(false);
                        numOfBeforeSuiteAnnotation -= 1;
                    } else {
                        System.out.println("Тест метода: " + m.getName() + " с аннотацией BeforeSuite: " + m.invoke(clazz));
                        numOfBeforeSuiteAnnotation -= 1;
                    }
                }
            }else {
                throw new RuntimeException("Количество методов с аннотацией BeforeSuite не соответствует требованиям");
            }
        }
        if(numOfBeforeSuiteAnnotation==1){
            throw new RuntimeException("Количество методов с аннотацией BeforeSuite не соответствует требованиям");
        }


        for (int i = 1; i<11; i++) {
            for (Method m : methods) {
                int modifier = m.getModifiers();
                if (m.getAnnotation(Test.class) != null) {
                    if(m.getAnnotation(Test.class).priority()==i){
                        if(Modifier.isPrivate(modifier)){
                            m.setAccessible(true);
                            if(m.getParameterCount()>0) {
                                System.out.println("Тест метода " + m.getName() + " с аннотацией Test: " + m.invoke(clazz, 2));
                            }else {
                                System.out.println("Тест метода " + m.getName() + " с аннотацией Test: " + m.invoke(clazz));
                            }
                            m.setAccessible(false);
                        }else {
                            if(m.getParameterCount()>0) {
                                System.out.println("Тест метода " + m.getName() + " с аннотацией Test: " + m.invoke(clazz, 2));
                            }else {
                                System.out.println("Тест метода " + m.getName() + " с аннотацией Test: " + m.invoke(clazz));
                            }
                        }
                    }

                }
            }
        }

        for (Method m : methods) {
            if (numOfAfterSuiteAnnotation >= 0) {
                if (m.getAnnotation(AfterSuite.class) != null) {
                    int modifier = m.getModifiers();
                    if (Modifier.isPrivate(modifier)) {
                        m.setAccessible(true);
                        System.out.println("Тест метода " + m.getName() + " с аннотацией AfterSuite: " + m.invoke(clazz));
                        m.setAccessible(false);
                        numOfAfterSuiteAnnotation -= 1;
                    } else {
                        System.out.println("Тест метода " + m.getName() + " с аннотацией AfterSuite: " + m.invoke(clazz));
                        numOfAfterSuiteAnnotation -= 1;
                    }
                }
            }else {
                throw new RuntimeException("Количество методов с аннотацией AfterSuite не соответствует требованиям");
            }
        }

        if (numOfAfterSuiteAnnotation==1){
            throw new RuntimeException("Количество методов с аннотацией AfterSuite не соответствует требованиям");
        }

    }
}
