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

/*
 * [START retail_search_for_products_with_page_size]
 * Call Retail API to search for a products in a catalog,
 * limit the number of the products per page and go to the next page
 * using "next_page_token" or jump to chosen page using "offset".
 */

package search;

import com.google.cloud.retail.v2.SearchRequest;
import com.google.cloud.retail.v2.SearchResponse;
import com.google.cloud.retail.v2.SearchServiceClient;
import java.io.IOException;
import java.util.UUID;

public class SearchWithPagination {

  public static void main(String[] args) throws IOException {
    // TODO(developer): Replace these variables before running the sample.
    String projectNumber = System.getenv("PROJECT_NUMBER");
    String defaultCatalogName =
        String.format("projects/%s/locations/global/catalogs/default_catalog", projectNumber);
    String defaultSearchPlacementName = defaultCatalogName + "/placements/default_search";

    search(defaultSearchPlacementName);
  }

  public static SearchResponse search(String defaultSearchPlacementName) throws IOException {
    // TRY DIFFERENT PAGINATION PARAMETERS HERE:
    int pageSize = 6;
    String queryPhrase = "Hoodie";
    int offset = 0;
    String pageToken = "";
    String visitorId = UUID.randomUUID().toString();

    SearchRequest searchRequest =
        SearchRequest.newBuilder()
            .setPlacement(defaultSearchPlacementName)
            .setVisitorId(visitorId)
            .setQuery(queryPhrase)
            .setPageSize(pageSize)
            .setOffset(offset)
            .setPageToken(pageToken)
            .build();
    System.out.println("Search request: " + searchRequest);

    try (SearchServiceClient client = SearchServiceClient.create()) {
      SearchResponse searchResponseFirstPage = client.search(searchRequest).getPage().getResponse();
      System.out.println("Search response: " + searchResponseFirstPage);

      // PASTE CALL WITH NEXT PAGE TOKEN HERE:

      // PASTE CALL WITH OFFSET HERE:

      return searchResponseFirstPage;
    }
  }
}

// [END retail_search_for_products_with_page_size]
