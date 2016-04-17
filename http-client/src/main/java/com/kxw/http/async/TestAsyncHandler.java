package com.kxw.http.async;

/**
 * {<a href='https://github.com/AsyncHttpClient/async-http-client#usage'>@link</a>}
 */
public class TestAsyncHandler {
    public static void main(String[] args) {
    /*    AsyncHttpClient c =  new DefaultAsyncHttpClient();
        Future<String> f = c.prepareGet("http://www.ning.com/").execute(new AsyncHandler<String>() {
            private ByteArrayOutputStream bytes = new ByteArrayOutputStream();

            @Override
            public STATE onStatusReceived(HttpResponseStatus status) throws Exception {
                int statusCode = status.getStatusCode();
                // The Status have been read
                // If you don't want to read the headers,body or stop processing the response
                if (statusCode >= 500) {
                    return STATE.ABORT;
                }
            }

            @Override
            public STATE onHeadersReceived(HttpResponseHeaders h) throws Exception {
                Headers headers = h.getHeaders();
                // The headers have been read
                // If you don't want to read the body, or stop processing the response
                return STATE.ABORT;
            }

            @Override
            public STATE onBodyPartReceived(HttpResponseBodyPart bodyPart) throws Exception {
                bytes.write(bodyPart.getBodyPartBytes());
                return STATE.CONTINUE;
            }

            @Override
            public String onCompleted() throws Exception {
                // Will be invoked once the response has been fully read or a ResponseComplete exception
                // has been thrown.
                // NOTE: should probably use Content-Encoding from headers
                return bytes.toString("UTF-8");
            }

            @Override
            public void onThrowable(Throwable t) {
            }
        });

        String bodyResponse = f.get();*/
    }
}
