package com.lnint.frame;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

/**
 * android studio 2.2单元测试
 * http://blog.csdn.net/da_caoyuan/article/details/53841977?fps=1&locationNum=5
 *
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        assertEquals("com.lnint.frame.test", appContext.getPackageName());
    }

    @Test
    public void testAddActivity() {
        assertEquals(0, 1);//方法第一个参数的意思是，我们预期的值；第二个参数的意思是，实际结果值。很显然它会报错。
        assertEquals(1, 1);//这个就不会报错啦。
    }
}
