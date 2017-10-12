package cloud.vostics;


import com.google.cloud.Page;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

import java.util.Iterator;

public class TestApp {


    public static void main(String[] args) {

        Storage storage = StorageOptions.getDefaultInstance().getService();

        Storage.BlobSourceOption.decryptionKey("");


        Storage.BlobListOption option = Storage.BlobListOption
                .prefix("af1f87419d864a9f88b4c37800b158cb");

//        boolean deleted = storage.delete(BlobId.of("object-cloud-storage",
//                "e61d2d5edf3e4e42af693e4ebe60d43d"));
//
//        System.out.println(deleted);

        Page<Blob> pages = storage.list("object-cloud-storage", option);

        Iterator<Blob> iterator = pages.iterateAll();
        while (iterator.hasNext()) {
            Blob bucket = iterator.next();
            System.out.println(bucket.getName());
            bucket.delete();
        }

        int requestCount = 0;

//        int currentThread = 1;
//        ExecutorService executorService = Executors.newFixedThreadPool(1000);
////
//        while (requestCount < 20000) {
//            for (int i = 0; i < 1000; i++) {
////                executorService.execute(new WorkerThread(currentThread));
//                executorService.execute(new GetWorkerThread(currentThread));
//                ++currentThread;
//            }
//            requestCount = requestCount + 1000;
//            try {
//                Thread.sleep(10000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }

//        PubSub pubSub = PubSubOptions.getDefaultInstance().getService();
//
//        pubSub.pullAsync("test-subscription", new PubSub.MessageProcessor() {
//            public void process(Message message) throws Exception {
//                System.out.println(message.getPayloadAsString());
//            }
//        });

//        Storage storage = StorageOptions.getDefaultInstance().getService();
//
////        Bucket bucket = storage.get("object-cloud-storage");
//
//
//        Storage.BlobListOption prefix = Storage.BlobListOption.prefix("1f197b9df01b4251a7e3489754f86ef2");
//        Storage.BlobListOption projections = Storage.BlobListOption.fields(Storage.BlobField.SIZE);

////        Page<Blob> blobPage = bucket.list(prefix);
//        Page<Blob> blobPage = storage.list("object-cloud-storage", prefix, projections);
//
//        Iterator<Blob> blobIterator = blobPage.getValues().iterator();
//
//        Long size = 0L;
//
//        while (blobIterator.hasNext()) {
//            Blob blob = blobIterator.next();
//            System.out.println(blob.getSize());
//        }
    }

}
