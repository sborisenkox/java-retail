/*
 * Copyright 2022 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package init;

import static com.google.common.truth.Truth.assertThat;
import static events.setup.EventsCreateBigQueryTable.createBqTableWithEvents;
import static events.setup.EventsCreateGcsBucket.eventsCreateGcsBucketAndUploadJsonFiles;
import static product.setup.ProductsCreateBigqueryTable.createBqTableWithProducts;
import static product.setup.ProductsCreateGcsBucket.productsCreateGcsBucketAndUploadJsonFiles;

import com.google.cloud.ServiceOptions;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.concurrent.ExecutionException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CreateTestResourcesTest {

  private ByteArrayOutputStream bout;
  private PrintStream originalPrintStream;

  @Before
  public void setUp() throws IOException, InterruptedException, ExecutionException {
    String projectId = ServiceOptions.getDefaultProjectId();
    String bucketName = System.getenv("BUCKET_NAME");
    String gcsBucket = String.format("gs://%s", System.getenv("BUCKET_NAME"));
    String gcsErrorBucket = String.format("%s/errors", gcsBucket);
    String branchName = String.format(
        "projects/%s/locations/global/catalogs/default_catalog/branches/0",
        projectId);
    bout = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bout);
    originalPrintStream = System.out;
    System.setOut(out);

    productsCreateGcsBucketAndUploadJsonFiles();
    eventsCreateGcsBucketAndUploadJsonFiles();
    CreateTestResources.importProductsFromGcs(bucketName, gcsErrorBucket,
        branchName);
    createBqTableWithProducts();
    createBqTableWithEvents();
  }

  @Test
  public void testCreateTestResources() {
    String got = bout.toString();

    assertThat(got).contains("Import products operation is completed");
    assertThat(got).contains("Number of successfully imported products");
    assertThat(got).contains("Number of failures during the importing");
    assertThat(got).contains(
        "Json from GCS successfully loaded in a table 'products'");
    assertThat(got).contains(
        "Json from GCS successfully loaded in a table 'products_some_invalid'");
    assertThat(got).contains(
        "Json from GCS successfully loaded in a table 'events'");
    assertThat(got).contains(
        "Json from GCS successfully loaded in a table 'events_some_invalid'");
  }

  @After
  public void tearDown() {
    System.out.flush();
    System.setOut(originalPrintStream);
  }
}
