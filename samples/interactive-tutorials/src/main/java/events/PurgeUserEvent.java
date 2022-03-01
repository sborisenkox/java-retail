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

// [START retail_purge_user_event]

/*
 * Purge user events into a catalog from inline source using Retail API
 */

package events;

import static setup.SetupCleanup.writeUserEvent;

import com.google.api.gax.longrunning.OperationFuture;
import com.google.cloud.retail.v2.PurgeMetadata;
import com.google.cloud.retail.v2.PurgeUserEventsRequest;
import com.google.cloud.retail.v2.PurgeUserEventsResponse;
import com.google.cloud.retail.v2.UserEventServiceClient;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class PurgeUserEvent {

  public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
    String projectId = System.getenv("PROJECT_ID");
    String defaultCatalog = String.format("projects/%s/locations/global/catalogs/default_catalog", projectId);
    String visitorId = "test_visitor_id";

    writeUserEvent(visitorId);
    callPurgeUserEvents(visitorId, defaultCatalog);
  }

  public static void callPurgeUserEvents(String visitorId, String defaultCatalog)
      throws IOException, ExecutionException, InterruptedException {
    try (UserEventServiceClient userEventServiceClient = UserEventServiceClient.create()) {
      OperationFuture<PurgeUserEventsResponse, PurgeMetadata> purgeOperation =
          userEventServiceClient.purgeUserEventsAsync(
              getPurgeUserEventRequest(visitorId, defaultCatalog));

      System.out.printf("The purge operation was started: %s%n",
          purgeOperation.getName());
    }
  }

  private static PurgeUserEventsRequest getPurgeUserEventRequest(String visitorId, String defaultCatalog) {
    PurgeUserEventsRequest purgeUserEventsRequest =
        PurgeUserEventsRequest.newBuilder()
            // TO CHECK ERROR HANDLING SET INVALID FILTER HERE:
            .setFilter(String.format("visitorId=\"%s\"", visitorId))
            .setParent(defaultCatalog)
            .setForce(true)
            .build();

    System.out.printf("Purge user events request: %s%n", purgeUserEventsRequest);

    return purgeUserEventsRequest;
  }
}

// [END retail_purge_user_event]
