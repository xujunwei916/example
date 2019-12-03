import java.util.concurrent.atomic.AtomicBoolean;

public class TestAtomicBoolean {
    public static void main(String[] args) {


        AtomicBoolean atomicBoolean = new AtomicBoolean(true);
        System.out.println(atomicBoolean);
        System.out.println(atomicBoolean.compareAndSet(true,false));
        System.out.println(atomicBoolean);
    }
}
