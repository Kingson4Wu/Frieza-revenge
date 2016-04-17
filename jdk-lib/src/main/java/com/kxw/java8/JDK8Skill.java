package com.kxw.java8;

import java.util.Optional;

/**
 * <a href='http://www.infoq.com/cn/articles/Java-8-Quiet-Features'>@link</a>
 */
public class JDK8Skill {

    public static void main(String[] args) {

        //1.时间戳锁  -----------------
        /**
         * 时间戳锁有一种“乐观”模式，在这种模式下每次加锁操作都会返回一个时间戳作为某种权限凭证；
         * 每次解锁操作都需要提供它对应的时间戳。如果一个线程在请求一个写操作锁的时候，这个锁碰巧已经被一个读操作持有，
         * 那么这个读操作的解锁将会失效（因为时间戳已经失效）。这个时候应用程序需要从头再来，
         * 也许要使用悲观模式的锁（时间戳锁也有实现）。你需要自己搞定这一切，并且一个时间戳只能解锁它对应的锁
         */
        //long stamp = lock.tryOptimisticRead();


        //2.并发加法器  -----------------
        /**
         * Java 8的LongAdder。这一系列类为大量并行读写数值的代码提供了方便的解决办法。
         * 使用超级简单。只要初始化一个LongAdder对象并使用它的add()和intValue()方法来累加和采样计数器。
         * 这和旧的Atomic类的区别在于，当CAS指令因为竞争而失败时，Adder不会一直占着CPU，
         * 而是为当前线程分配一个内部cell对象来存储计数器的增量。然后这个值和其他待处理的cell对象一起
         * 被加到intValue()的结果上。这减少了反复使用CAS指令或阻塞其他线程的可能性。
         */



        //3.并行排序  -----------------
        /**Java 8还实现了一种简洁的方法来加速排序。这个秘诀很简单。你不再这么做：
         * Array.sort(myArray);
         * 而是这么做：
         * Arrays.parallelSort(myArray);
         */
        /**
         * 这会自动把目标数组分割成几个部分，这些部分会被放到独立的CPU核上去运行，再把结果合并起来。
         * 这里唯一需要注意的是，在一个大量使用多线程的环境中，比如一个繁忙的Web容器，
         * 这种方法的好处就会减弱（降低90%以上），因为越来越多的CPU上下文切换增加了开销。
         */

        //4.切换到新的日期接口  -----------------
        /**
         * 为了衔接新旧接口，历史悠久的Date类新增了toInstant()方法，用于把Date转换成新的表示形式。
         * 当你既要享受新接口带来的好处，又要兼顾那些只接受旧的日期表示形式的接口时，这个方法会显得尤其高效。
         */

        //5. 控制操作系统进程  -----------------
        /**
         * Java 8在Process类中引入了三个新的方法
         * 1. destroyForcibly——结束一个进程，成功率比以前高很多。
         * 2. isAlive——查询你启动的进程是否还活着。
         * 3. 重载了waitFor()，你现在可以指定等待进程结束的时间了。进程成功退出后这个接口会返回，超时的话也会返回，因为你有可能要手动终止它。
         */
        //1. 如果进程没有在规定时间内退出，终止它并继续往前走。
           /* if (process.wait(MY_TIMEOUT, TimeUnit.MILLISECONDS)){
            //成功 }
            else {
                process.destroyForcibly();
            }*/
            //2.在你的代码结束前，确保所有的进程都已退出。僵尸进程会逐渐耗尽系统资源。
           /* for (Process p : processes) {
                if (p.isAlive()) {
                    p.destroyForcibly();
                }
            }*/

        //6.精确的数字运算  -----------------
        /**
         * Java 8为Math类添加了几个新的“精确型”方法，以便保护重要的代码不受溢出的影响，它的做法是当运算超过它的精度范围的时候，抛出一个未检查的ArithmeticException异常。
         * int safeC = Math.multiplyExact(bigA, bigB);
         // 如果结果超出+-2^31，就会抛出ArithmeticException异常
         */

        //7.安全的随机数发生器  -----------------
        /**
         * ava 8添加了一个新的方法叫SecureRandom.getInstanceStrong()，
         * 它的目标是让虚拟机为你选择一个安全的随机数发生器。如果你的代码无法完全掌控操作系统、硬件、虚拟机（
         * 如果你的程序部署到云或者PaaS上，这是很常见的），我建议你认真考虑一下使用这个接口。
         */

        //8.可选引用  -----------------
        /**
         * Java 8引入了一个新模板叫Optional<T>。
         */
        //用新的Lambda语法打印Optional值：

        Optional<String> value = Optional.of("kxw");
        value.ifPresent(System.out::print);

    }
}
