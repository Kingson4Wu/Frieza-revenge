package com.google.guava.basic;

import com.kxw.bean.Kingson;
import org.junit.Test;

import java.util.Objects;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by kingsonwu on 15/12/27.
 */
public class TestPreconditions {

    @Test
    public void testCheckArgument(){
        int i = 5, j = 3;
        checkArgument(i >= 0, "Argument was %s but expected nonnegative", i);
        checkArgument(i < j, "Expected i < j, but %s > %s", i, j);

    }

    @Test
    public void testCheckNotNull(){

        Kingson kingson = null;
        Kingson test = checkNotNull(kingson);
        //在JDK7已经引入Objects.requireNonNull的情况下，我们仍然建议你使用checkNotNull。
        test = Objects.requireNonNull(kingson);
    }

/**
 * checkArgument(boolean)	检查boolean是否为true，用来检查传递给方法的参数。	IllegalArgumentException
 checkNotNull(T)	检查value是否为null，该方法直接返回value，因此可以内嵌使用checkNotNull。	NullPointerException
 checkState(boolean)	用来检查对象的某些状态。	IllegalStateException
 checkElementIndex(int index, int size)	检查index作为索引值对某个列表、字符串或数组是否有效。index>=0 && index<size *	IndexOutOfBoundsException
 checkPositionIndex(int index, int size)	检查index作为位置值对某个列表、字符串或数组是否有效。index>=0 && index<=size *	IndexOutOfBoundsException
 checkPositionIndexes(int start, int end, int size)	检查[start, end]表示的位置范围对某个列表、字符串或数组是否有效*	IndexOutOfBoundsException
 */
}
