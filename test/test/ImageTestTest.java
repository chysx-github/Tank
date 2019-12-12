package test;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import junit.framework.TestCase;

import static org.junit.Assert.*;

public class ImageTestTest extends TestCase {


    @Test

    public void test1() {
        assertEquals(0,ImageTest.test());
        assertEquals(1,ImageTest.test());
    }
    public void test2() {
        assertEquals(0,ImageTest.test());
        assertEquals(1,ImageTest.test());
    }
    public void test3() {
        assertEquals(0,ImageTest.test());
        assertEquals(1,ImageTest.test());
    }

}
