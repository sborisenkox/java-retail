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

// [START retail_import_user_events_from_big_query]

/*
 * Import user events into a catalog from GCS using Retail API
 */


package events;

import com.google.cloud.retail.v2.BigQuerySource;
import com.google.cloud.retail.v2.ImportMetadata;
import com.google.cloud.retail.v2.ImportUserEventsRequest;
import com.google.cloud.retail.v2.ImportUserEventsResponse;
import com.google.cloud.retail.v2.UserEventInputConfig;
import com.google.cloud.retail.v2.UserEventServiceClient;

import com.google.longrunning.Operation;
import com.google.longrunning.OperationsClient;
import java.io.IOException;

public class ImportUserEventsBigQuery {

  private static final String PROJECT_ID = System.getenv("PROJECT_ID");
  private static final String DEFAULT_CATALOG = String.format(
      "projects/%s/locations/global/catalogs/default_catalog", PROJECT_ID);
  /*
  TO CHECK ERROR HANDLING PASTE THE INVALID CATALOG NAME HERE:
  DEFAULT_CATALOG = "invalid_catalog_name"
  */
  private static final String DATASET_ID = "user_events";
  private static final String TABLE_ID = "events";
  /*
  TO CHECK ERROR HANDLING USE THE TABLE OF INVALID USER EVENTS:
  TABLE_ID = "events_some_invalid"
  */
  private static final String DATA_SCHEMA = "user_event";

  public static void main(String[] args)
      throws IOException, InterruptedException {
    importUserEventsFromBigQuery();
  }

  public static void importUserEventsFromBigQuery()
      throws IOException, InterruptedException {
    ImportUserEventsRequest importBigQueryRequest =
        getImportEventsBigQueryRequest();

    UserEventServiceClient serviceClient = UserEventServiceClient.create();

    String operationName = serviceClient
        .importUserEventsCallable()
        .call(importBigQueryRequest)
        .getName();

    System.out.printf("OperationName = %s\n", operationName);
    OperationsClient operationsClient = serviceClient.getOperationsClient();
    Operation operation = operationsClient.getOperation(operationName);

    while (!operation.getDone()) {
      // Keep polling the operation periodically until the import task is done.
      int awaitDuration = 30000;
      Thread.sleep(awaitDuration);
      operation = operationsClient.getOperation(operationName);
    }

    if (operation.hasMetadata()) {
      ImportMetadata metadata = operation.getMetadata()
          .unpack(ImportMetadata.class);
      System.out.printf("Number of successfully imported events: %s\n",
          metadata.getSuccessCount());
      System.out.printf("Number of failures during the importing: %s\n",
          metadata.getFailureCount());
    }

    if (operation.hasResponse()) {
      ImportUserEventsResponse response = operation.getResponse()
          .unpack(ImportUserEventsResponse.class);
      System.out.printf("Operation result: %s%n", response);
    }
  }

  public static ImportUserEventsRequest getImportEventsBigQueryRequest() {
    BigQuerySource bigQuerySource = BigQuerySource.newBuilder()
        .setProjectId(PROJECT_ID)
        .setDatasetId(DATASET_ID)
        .setTableId(TABLE_ID)
        .setDataSchema(DATA_SCHEMA)
        .build();

    UserEventInputConfig inputConfig = UserEventInputConfig.newBuilder()
        .setBigQuerySource(bigQuerySource)
        .build();

    ImportUserEventsRequest importRequest = ImportUserEventsRequest.newBuilder()
        .setParent(DEFAULT_CATALOG)
        .setInputConfig(inputConfig)
        .build();

    System.out.printf("Import user events from BigQuery source request: %s%n",
        importRequest);

    return importRequest;
  }
}

// [END retail_import_user_events_from_big_query]
