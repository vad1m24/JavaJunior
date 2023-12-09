package petProject2.hw;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TestProcessor {

    /**
     * Данный метод находит все void методы без аргументов в классе, и запускеет их.
     * <p>
     * Для запуска создается тестовый объект с помощью конструткора без аргументов.
     */
    public static void runTest(Class<?> testClass) {
        final Constructor<?> declaredConstructor;
        try {
            declaredConstructor = testClass.getDeclaredConstructor();
        } catch (NoSuchMethodException e) {
            throw new IllegalStateException("Для класса \"" + testClass.getName() + "\" не найден конструктор без аргументов");
        }

        final Object testObj;
        try {
            testObj = declaredConstructor.newInstance();
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException("Не удалось создать объект класса \"" + testClass.getName() + "\"");
        }

        List<Method> methods = new ArrayList<>();
        List<Method> methodsBefore = new ArrayList<>();
        List<Method> methodsAfter = new ArrayList<>();

        for (Method method : testClass.getDeclaredMethods()) {
            if (checkTestMethod(method)) {
                if (method.isAnnotationPresent(Test.class)&& !method.isAnnotationPresent(Skip.class)) {
                    methods.add(method);
                } else if (method.isAnnotationPresent(BeforeEach.class)) {
                    methodsBefore.add(method);
                } else if (method.isAnnotationPresent(AfterEach.class)) {
                    methodsAfter.add(method);
                }
            }
        }
        methods.sort(Comparator.comparingInt(method -> method.getAnnotation(Test.class).order()));
        methods.forEach(it -> runTest(it, testObj, methodsBefore, methodsAfter));
    }

    private static boolean checkTestMethod(Method method) {
        if (!method.getReturnType().isAssignableFrom(void.class) || method.getParameterCount() != 0) {
            System.out.println("Метод \"" + method.getName() + "\" должен быть void и не иметь аргументов");
            return false;
        }
        return true;
    }

    private static void runTest(Method testMethod, Object testObj, List<Method> methodsBefore, List<Method> methodsAfter) {
        try {
            runBefore(testObj, methodsBefore);
            testMethod.invoke(testObj);
            runAfter(testObj, methodsAfter);

        } catch (InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException("Не удалось запустить тестовый метод \"" + testMethod.getName() + "\"");
        }
    }

    private static void runAfter(Object testObj, List<Method> methodsAfter) {
        methodsAfter.forEach(method -> {
            try {
                method.invoke(testObj);
            } catch (InvocationTargetException | IllegalAccessException e) {
                throw new RuntimeException("Не удалось запустить тестовый метод \"" + method.getName() + "\"");
            }
        });
    }

    private static void runBefore(Object testObj, List<Method> methodsBefore) {
        methodsBefore.forEach(method -> {
            try {
                method.invoke(testObj);
            } catch (InvocationTargetException | IllegalAccessException e) {
                throw new RuntimeException("Не удалось запустить тестовый метод \"" + method.getName() + "\"");
            }
        });
    }

}
