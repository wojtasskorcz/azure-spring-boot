/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See LICENSE in the project root for
 * license information.
 */

package sample.storage;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SuppressFBWarnings({"RV_RETURN_VALUE_IGNORED"})
@SpringBootApplication
public class StorageSampleApplication {
    public static void main(String[] args) {
        SpringApplication.run(StorageSampleApplication.class);
    }
}
