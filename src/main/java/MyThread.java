public class MyThread implements Runnable {
    private final Message msg;
    private final String str;
    private final Thread thread;

    public MyThread(Message msg,
                    String str) {
        this.msg = msg;
        this.str = str;
        thread = new Thread(this);
    }

    public static void main(String[] args) {
        Thread tr = Thread.currentThread();
        System.out.println("Current Thread: " + tr);
        tr.setName("My New Thread");
        System.out.println("After name thread: " + tr);

        Message msg = new Message();
        MyThread thread1 = new MyThread(msg, "1");
        MyThread thread2 = new MyThread(msg, "2");
        MyThread thread3 = new MyThread(msg, "3");

        thread1.thread.start();
        try {
            thread1.thread.join();
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        }

        thread2.thread.start();
        try {
            thread2.thread.join();
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        }

        thread3.thread.start();
        try {
            thread3.thread.join();
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        }
    }

    @Override
    public void run() {
        synchronized (msg) {
            msg.message(str);
        }
    }
}
