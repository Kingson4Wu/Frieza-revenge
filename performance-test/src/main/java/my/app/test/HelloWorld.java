package my.app.test;

import java.util.Random;

public class HelloWorld {
    public static void main(String[] args) throws Exception {
        //CaseObject object = new CaseObject();
        while (true) {
            Random random = new Random();
            execute(random.nextInt(4000));

            //object.execute(random.nextInt(4000));
        }


    }

    public static Integer execute(int sleepTime) {
        try {
            Thread.sleep(sleepTime);
        } catch (Exception e) {
        }
        System.out.println("sleep time is=>" + sleepTime);
        return 0;
    }
}