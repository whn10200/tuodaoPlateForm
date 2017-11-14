package com.tuodao.bp.api.facade;

import com.netflix.ribbon.proxy.annotation.Http;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }



    public static void main(String[] args) throws IOException {

        Logger  logger = LoggerFactory.getLogger("test");
        String url = "/product/getProductPageList";
        String params = "{id:1}";

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost post = new HttpPost(url);
        StringEntity entity = new StringEntity( params , ContentType.create("text/json", "UTF-8"));
        post.setEntity(entity);
        CloseableHttpResponse httpresponse = httpClient.execute(post);

        httpClient.close();
        httpresponse.close();

        String responseStr = EntityUtils.toString(httpresponse.getEntity());
        logger.debug(responseStr);


    }
}
