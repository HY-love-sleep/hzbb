package com.hy.handler;

import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Description:
 *
 * @author: yhong
 * Date: 2024/9/26
 */
@Component
public class HttpContentHandler implements HttpStreamHandler{

    @Override
    public void handleHttpStream(Object message, String outputDir) throws IOException {

    }
}
