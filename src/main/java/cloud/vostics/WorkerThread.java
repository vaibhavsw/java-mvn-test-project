package cloud.vostics;


import okhttp3.*;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

public class WorkerThread implements Runnable {

    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    private int mIndex;
    private Request mRequest;
    private OkHttpClient mOkHttpClient;

    public WorkerThread(int index) {
        mIndex = index;
        mOkHttpClient = getClient();
        mRequest = getRequest();
    }

    public void run() {
        System.out.println("Starting thread " + mIndex);
        try {
            Response response = mOkHttpClient.newCall(mRequest).execute();
            if (!response.isSuccessful()) {
                System.out.println("Error on thread " + mIndex + " " + new String(response.body().bytes()));
            } else {
                System.out.println("Response for thread " + mIndex + " " + new String(response.body().bytes()));
            }

        } catch (Exception e) {
            System.out.println("Error occurred while performing request " + e.getMessage());
        }
    }

    public OkHttpClient getClient() {
        return new OkHttpClient();
    }

    public Request getRequest() {
        Request.Builder request = new Request.Builder();
        try {
            RequestBody requestBody = getRequestBody();
            request.url(new URL("http://localhost:8080/v1/datastore/author"));
            request.header("Secret", "43e1849c8004432dbefb1d64787be506");
            request.post(requestBody);
        } catch (MalformedURLException e) {
            System.out.println("MalformedURL");
        } catch (URISyntaxException e) {
            System.out.println("URI syntax error");
        }

        return request.build();
    }

    public RequestBody getRequestBody() throws URISyntaxException {
        return RequestBody.create(JSON, getFile());
    }

    public File getFile() throws URISyntaxException {
        URL url = getClass().getResource("/body.json");
        return new File(url.toURI());
    }
}
