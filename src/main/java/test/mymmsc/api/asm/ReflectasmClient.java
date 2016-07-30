package test.mymmsc.api.asm;

import org.mymmsc.api.asm.MethodAccess;

import java.lang.reflect.Method;

public class ReflectasmClient {
    private static int count = 10000000;

    public static void main(String[] args) throws Exception {
        testJdkReflect();
        testReflectAsm();
    }

    public static void testJdkReflect() throws Exception {
        System.out.println("JDK....");
        SomeClass someObject = new SomeClass();
        Method method = SomeClass.class.getMethod("foo", String.class);
        for (int i = 0; i < 5; i++) {
            long begin = System.currentTimeMillis();
            for (int j = 0; j < count; j++) {
                method.invoke(someObject, "Unmi");
            }
            System.out.print(System.currentTimeMillis() - begin + " ");
        }
    }

    public static void testReflectAsm() {
        System.out.println("");
        System.out.println("ASM....");
        SomeClass someObject = new SomeClass();
        MethodAccess access = MethodAccess.get(SomeClass.class);
        for (int i = 0; i < 5; i++) {
            long begin = System.currentTimeMillis();
            for (int j = 0; j < count; j++) {
                access.invoke(someObject, "foo", "Unmi");
            }
            System.out.print(System.currentTimeMillis() - begin + " ");
        }
    }
}
