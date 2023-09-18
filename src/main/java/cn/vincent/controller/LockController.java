package cn.vincent.controller;

import cn.vincent.server.LockServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lock")
public class LockController {

    /* Java 线程安全
     * Java中保证线程安全有两个方案
     * 1. 同步 - 共享变量
     *   1.1 互斥同步 - 悲观锁
     *   1.2 非阻塞同步 - 乐观锁
     * 2. 不同步 - 不共享变量
     * */



    
    @Autowired
    LockServer lockServer;

    //region 悲观锁 synchronized - lock
    @GetMapping("/getSynchronizedLock/{userName}")
    public String getSynchronizedLock(@PathVariable String userName) {
        //lockServer.synchronizedMethodLock("getLock_" + userName); // 锁住方法
        //lockServer.synchronizedClssLock("getLock_" + userName); // 锁住当前实例
        lockServer.synchronizedObjLock("getLock_" + userName); // 通过当前实例中的变量加锁
        return "get Lock";
    }

    @GetMapping("/getReentrantLock/{userName}")
    public String getReentrantLock(@PathVariable String userName) {
        lockServer.getReentrantLock("getLock_" + userName); // 通过当前实例中的变量加锁
        return "get Lock";
    }
    //endregion

    //region 乐观锁 Atomic

    @GetMapping("/getAtomic/{userName}")
    public String getAtomic(@PathVariable String userName) {
        lockServer.getAtomic("getLock_" + userName); // 通过当前实例中的变量加锁
        return "get Lock";
    }
    //endregion
}
