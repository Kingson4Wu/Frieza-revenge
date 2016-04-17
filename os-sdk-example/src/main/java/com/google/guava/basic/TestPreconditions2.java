package com.google.guava.basic;

import com.kxw.bean.Kingson;

import static com.google.common.base.Preconditions.*;

/**
 * Created by kingsonwu on 15/12/27.
 */
public class TestPreconditions2 {

    public static void main(String[] args) {
        int i = 5, j = 3;
        checkArgument(i >= 0, "Argument was %s but expected nonnegative", i);
        checkArgument(i < j, "Expected i < j, but %s > %s", i, j);


        Kingson kingson = new Kingson();
        kingson = null;
        checkNotNull(kingson);

    }
}
