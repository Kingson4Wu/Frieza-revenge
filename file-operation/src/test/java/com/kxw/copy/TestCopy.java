package com.kxw.copy;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * Created by kxw on 2015/9/18.
 */
public class TestCopy {

    @Test
    public void test() throws IOException {

        DirectoryCopy.copyFolder(new File("F:\\test"),new File("F:\\test2"));
    }

    @Test
    public void testUtils() throws IOException {

        DirectoryCopy.copyFolderUtils(new File("F:\\test"),new File("F:\\test2"));
    }
}
