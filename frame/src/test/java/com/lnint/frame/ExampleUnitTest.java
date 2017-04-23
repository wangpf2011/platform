package com.lnint.frame;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * 如果我们要需要测试一段java代码，而这段java代码跟android没关系，
 * 也就是不用到android的资源，如context，activity 等，说白了就是简单的 java 测试，
 * 当然android studio也是可以做java代码测试的。
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }
}