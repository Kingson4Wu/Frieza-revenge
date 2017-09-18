package com.kxw.sync;

import java.io.IOException;

/**
 * Created by kingsonwu on 17/9/17.
 */
public class LockObject {

    public void fuckone() throws IOException {
        LockSelfObjectTest.one();
    }

    public void fucktwo() throws IOException {
        LockSelfObjectTest.two();
    }
}
