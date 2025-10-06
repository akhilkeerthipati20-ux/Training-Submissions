class MyThread extends Thread {
    private String threadName;

    MyThread(String name) {
        this.threadName = name;
    }

    @Override
    public void run() {
        // This code runs in a separate thread
        for (int i = 1; i <= 5; i++) {
            System.out.println(threadName + " - Count: " + i);
            try {
                Thread.sleep(500); // pause for half a second
            } catch (InterruptedException e) {
                System.out.println(threadName + " interrupted.");
            }
        }
        System.out.println(threadName + " has finished executing.");
    }
}

public class MultiThreadProgram {
    public static void main(String[] args) {
        System.out.println("Main thread starts.");

        // Create two threads
        MyThread t1 = new MyThread("Thread 1");
        MyThread t2 = new MyThread("Thread 2");

        // Start both threads
        t1.start();
        t2.start();

        System.out.println("Main thread ends.");
    }
}
