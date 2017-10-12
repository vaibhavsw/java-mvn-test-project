package cloud.vostics;


import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.net.MalformedURLException;
import java.net.URL;

public class GetWorkerThread implements Runnable {


    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    private int mIndex;
    private Request mRequest;
    private OkHttpClient mOkHttpClient;

    public GetWorkerThread(int index) {
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
            request.url(new URL("http://localhost:8080/v1/datastore/author/58a7fd9f8d44b304188d8769"));
            request.header("Secret", "43e1849c8004432dbefb1d64787be506");
            request.header("Content-Type", "application/json");
            request.get();
        } catch (MalformedURLException e) {
            System.out.println("MalformedURL");
        }

        return request.build();
    }
}
