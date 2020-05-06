/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See LICENSE in the project root for
 * license information.
 */

package sample.storage;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Currently only /download endpoint works if running the app from .jar
 * In this case first manually upload a sample storageTestFile.txt to your chosen container (e.g. through Azure Portal)
 *
 * To use different application.properties when running a JAR:
 * java -jar .\target\azure-storage-spring-boot-sample-0.0.1-SNAPSHOT.jar --spring.config.location=<path/to/application.properties>
 */
@SuppressFBWarnings({"RV_RETURN_VALUE_IGNORED"})
@SpringBootApplication
public class StorageSampleApplication {
    public static void main(String[] args) {
        SpringApplication.run(StorageSampleApplication.class, args);
    }
}
