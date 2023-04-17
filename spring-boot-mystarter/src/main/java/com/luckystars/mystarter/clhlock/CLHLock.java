package com.luckystars.mystarter.clhlock;

import java.util.concurrent.atomic.AtomicReference;

public class CLHLock {

    private final  ThreadLocal<CLHNode> threadLocalNode = ThreadLocal.withInitial(CLHNode::new);

    //CLH 锁是一种隐式的链表队列，没有显式的维护前驱或后继指针。
    // 因为每个等待获取锁的线程只需要轮询前一个节点的状态就够了，而不需要遍历整个队列。
    // 在这种情况下，只需要使用一个局部变量保存前驱节点，而不需要显式的维护前驱或后继指针。
    private final AtomicReference<CLHNode> tail = new AtomicReference<>(new CLHNode());

    private static final class CLHNode{
        // 使用 volatile 修饰状态变量不是为了利用 volatile 的内存可见性，
        // 因为这个状态变量只会被持有该状态变量的线程写入，只会被队列中该线程的后驱节点对应的线程读，
        // 而且后者会轮询读取。因此，可见性问题不会影响锁的正确性。
        // 以上面的例子为例，T2 会不断轮询T1的状态变量，
        // T1 将它的状态变更为 False 时 T2 没有立即感知也没有关系。
        // 该状态变量最终会写回内存并被 T2 终感知到变更后的值。
        private volatile boolean locked;
    }

    public void lock(){
        CLHNode curNode = this.threadLocalNode.get();
        curNode.locked = true;
        //Atomically sets to the given value and returns the old value.
        CLHNode pre = this.tail.getAndSet(curNode);
        //自旋等待前一个节点释放
        while (pre.locked);
    }

    public void unlock(){
        final CLHNode curNode = this.threadLocalNode.get();
        curNode.locked = false;
        //如果没有这行代码，Node 可能被复用，导致死锁，如下图所示：
        this.threadLocalNode.set(new CLHNode());
    }


}
