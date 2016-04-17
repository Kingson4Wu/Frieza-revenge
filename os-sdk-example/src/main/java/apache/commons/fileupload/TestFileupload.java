package apache.commons.fileupload;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import java.util.Iterator;

/**
 * Created by kingsonwu on 15/12/26.
 */
public class TestFileupload {

    public static void main(String[] args) {
      /*  //官方示例：
/*//* 检查请求是否含有上传文件
        // Check that we have a file upload request
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);

        //现在我们得到了items的列表

        //如果你的应用近于最简单的情况，上面的处理就够了。但我们有时候还是需要更多的控制。
        //下面提供了几种控制选择：
        // Create a factory for disk-based file items
        DiskFileItemFactory factory = new DiskFileItemFactory();

        // Set factory constraints
        factory.setSizeThreshold(yourMaxMemorySize);
        factory.setRepository(yourTempDirectory);

        // Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload(factory);

        // 设置最大上传大小
        upload.setSizeMax(yourMaxRequestSize);

        // 解析所有请求
        List *//* FileItem *//* items = upload.parseRequest(request);

        // Create a factory for disk-based file items
        DiskFileItemFactory factory = new DiskFileItemFactory(
                yourMaxMemorySize, yourTempDirectory);

        //一旦解析完成，你需要进一步处理item的列表。
        // Process the uploaded items
        Iterator iter = items.iterator();
        while (iter.hasNext()) {
            FileItem item = (FileItem) iter.next();

            if (item.isFormField()) {
                processFormField(item);
            } else {
                processUploadedFile(item);
            }
        }

        //区分数据是否为简单的表单数据，如果是简单的数据：
        // processFormField
        if (item.isFormField()) {
            String name = item.getFieldName();
            String value = item.getString();
            //...省略步骤
        }

        //如果是提交的文件：
        // processUploadedFile
        if (!item.isFormField()) {
            String fieldName = item.getFieldName();
            String fileName = item.getName();
            String contentType = item.getContentType();
            boolean isInMemory = item.isInMemory();
            long sizeInBytes = item.getSize();
            //...省略步骤
        }

        //对于这些item，我们通常要把它们写入文件，或转为一个流
        // Process a file upload
        if (writeToFile) {
            File uploadedFile = new File(...);
            item.write(uploadedFile);
        } else {
            InputStream uploadedStream = item.getInputStream();
            //...省略步骤
            uploadedStream.close();
        }

        //或转为字节数组保存在内存中：
        // Process a file upload in memory
        byte[] data = item.get();
        //...省略步骤
        //如果这个文件真的很大，你可能会希望向用户报告到底传了多少到服务端，让用户了解上传的过程
        //Create a progress listener
        ProgressListener progressListener = new ProgressListener(){
            public void update(long pBytesRead, long pContentLength, int pItems) {
                System.out.println("We are currently reading item " + pItems);
                if (pContentLength == -1) {
                    System.out.println("So far, " + pBytesRead + " bytes have been read.");
                } else {
                    System.out.println("So far, " + pBytesRead + " of " + pContentLength
                            + " bytes have been read.");
                }
            }
        };
        upload.setProgressListener(progressListener);*/
    }
}
