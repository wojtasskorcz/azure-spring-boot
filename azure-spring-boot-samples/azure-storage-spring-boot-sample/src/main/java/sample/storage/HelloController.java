package sample.storage;

import com.microsoft.azure.spring.autoconfigure.storage.StorageProperties;
import com.microsoft.azure.storage.blob.BlockBlobURL;
import com.microsoft.azure.storage.blob.ContainerURL;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@RestController
public class HelloController {

    @Autowired
    private ContainerURL containerURL;

    @Autowired
    private StorageProperties properties;

    private static final String SOURCE_FILE = "storageTestFile.txt";

    private File sourceFile;
    private File downloadFile;
    private BlockBlobURL blockBlobURL;

    @PostConstruct
    public void init() throws IOException {
        sourceFile = new File(this.getClass().getClassLoader().getResource(SOURCE_FILE).getFile());
        downloadFile = Files.createTempFile("azure-storage-test", null).toFile();

        StorageService.createContainer(containerURL,  properties.getContainerName());
        blockBlobURL = containerURL.createBlockBlobURL(SOURCE_FILE);
    }

    @RequestMapping("/upload")
    public String upload() throws IOException {
        StorageService.uploadFile(blockBlobURL, sourceFile);
        return String.format("Uploaded file %s to %s", sourceFile, blockBlobURL);
    }

    @RequestMapping("/download")
    public String download() throws IOException {
//        StorageService.downloadBlob(blockBlobURL, downloadFile); // this was the original way but it's an asynchronous operation and Dynatrace cannot trace it properly
        FileUtils.copyURLToFile(blockBlobURL.toURL(), downloadFile);

        return String.format("Downloaded %s to file %s", blockBlobURL, downloadFile);
    }

    @RequestMapping("/delete")
    public String delete() throws IOException {
        StorageService.deleteBlob(blockBlobURL);
        return String.format("Deleted %s", blockBlobURL);
    }

}
