package cn.donald.dp.Decorator;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by DonaldY on 2017/8/14.
 */
public class DecoratorTest {

    @Test
    public void test() {
        Email email = new EmailImpl("Hello world, hello DonaldY. ");
        email = new EnterpriseEmailImpl(email);
        Assert.assertEquals("Hello world, hello DonaldY. Only person, not enterprise!", email.getContent());
    }
}
