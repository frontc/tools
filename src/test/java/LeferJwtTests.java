import cn.lefer.tools.Net.LeferNet;
import cn.lefer.tools.Token.LeferJwt;
import cn.lefer.tools.Token.LeferJwtStatus;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;

/**
 * @author : lefer
 * @version : V1.0
 * @date :   2020/6/29
 * @Description :
 */
public class LeferJwtTests {
    @Test
    public void testCreateToken() {
        String token = LeferJwt.createToken("tomu", "admin", "12345", 180000);
        System.out.println(token);
        Assert.assertNotNull(token);
    }

    @Test
    public void testCheckToken() {
        String token = LeferJwt.createToken("tomu", "admin", "12345", 180000);
        LeferJwtStatus trueTest = LeferJwt.checkToken(token, "12345");
        LeferJwtStatus falseTest = LeferJwt.checkToken(token, "1245");
        Assert.assertTrue(trueTest.isValid());
        Assert.assertFalse(falseTest.isValid());
    }

    @Test
    public void testRefreshToken() {
        String oldToken = LeferJwt.createToken("tomu", "admin", "12345", 80000);
        String newToken = LeferJwt.refreshToken(oldToken, "12345", 180000);
        Assert.assertTrue(LeferJwt.getExpirationDate(newToken, "123456").after(LeferJwt.getExpirationDate(oldToken, "123456")));
    }

    @Test
    public void testValidToken() {
        String token = LeferJwt.createToken("tomu", "admin", "12345", 80000);
        Assert.assertTrue(LeferJwt.isValid(token, "12345"));
    }


    @Test
    public void testURL() throws MalformedURLException, IOException, SocketTimeoutException {
        Assert.assertTrue(LeferNet.isValid("https://lefer.cn"));
        Assert.assertTrue(LeferNet.isValid("http://lefer.cn"));
    }
}
