package Java8DurgaSir;

// Without Lambda

class MyRunnable implements Runnable{

    @Override
    public void run() {
        for (int i = 0; i < 100000; i++) {
            System.out.println("Child Thread..");
        }
    }
}
public class ThreadsDemo {
    public static void main(String[] args) {
        //  [Without Lambda]
            Runnable mr = new MyRunnable();
            Thread t = new Thread(mr);
            t.start();

        //With Lambda
        /*Runnable mr = () -> {
            for (int i = 0; i < 100; i++) {
                System.out.println("Child Thread..");
            }
        };*/
        //Thread t = new Thread(mr);
        t.start();
        for (int i = 0; i < 100; i++) {
            System.out.println("Main Thread..!!");
        }
    }
}
