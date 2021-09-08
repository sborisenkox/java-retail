/*
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: google/cloud/retail/v2/completion_service.proto

package com.google.cloud.retail.v2;

public interface CompleteQueryResponseOrBuilder
    extends
    // @@protoc_insertion_point(interface_extends:google.cloud.retail.v2.CompleteQueryResponse)
    com.google.protobuf.MessageOrBuilder {

  /**
   *
   *
   * <pre>
   * Results of the matching suggestions. The result list is ordered and the
   * first result is top suggestion.
   * </pre>
   *
   * <code>
   * repeated .google.cloud.retail.v2.CompleteQueryResponse.CompletionResult completion_results = 1;
   * </code>
   */
  java.util.List<com.google.cloud.retail.v2.CompleteQueryResponse.CompletionResult>
      getCompletionResultsList();
  /**
   *
   *
   * <pre>
   * Results of the matching suggestions. The result list is ordered and the
   * first result is top suggestion.
   * </pre>
   *
   * <code>
   * repeated .google.cloud.retail.v2.CompleteQueryResponse.CompletionResult completion_results = 1;
   * </code>
   */
  com.google.cloud.retail.v2.CompleteQueryResponse.CompletionResult getCompletionResults(int index);
  /**
   *
   *
   * <pre>
   * Results of the matching suggestions. The result list is ordered and the
   * first result is top suggestion.
   * </pre>
   *
   * <code>
   * repeated .google.cloud.retail.v2.CompleteQueryResponse.CompletionResult completion_results = 1;
   * </code>
   */
  int getCompletionResultsCount();
  /**
   *
   *
   * <pre>
   * Results of the matching suggestions. The result list is ordered and the
   * first result is top suggestion.
   * </pre>
   *
   * <code>
   * repeated .google.cloud.retail.v2.CompleteQueryResponse.CompletionResult completion_results = 1;
   * </code>
   */
  java.util.List<
          ? extends com.google.cloud.retail.v2.CompleteQueryResponse.CompletionResultOrBuilder>
      getCompletionResultsOrBuilderList();
  /**
   *
   *
   * <pre>
   * Results of the matching suggestions. The result list is ordered and the
   * first result is top suggestion.
   * </pre>
   *
   * <code>
   * repeated .google.cloud.retail.v2.CompleteQueryResponse.CompletionResult completion_results = 1;
   * </code>
   */
  com.google.cloud.retail.v2.CompleteQueryResponse.CompletionResultOrBuilder
      getCompletionResultsOrBuilder(int index);

  /**
   *
   *
   * <pre>
   * A unique complete token. This should be included in the
   * [SearchRequest][google.cloud.retail.v2.SearchRequest] resulting from this
   * completion, which enables accurate attribution of complete model
   * performance.
   * </pre>
   *
   * <code>string attribution_token = 2;</code>
   *
   * @return The attributionToken.
   */
  java.lang.String getAttributionToken();
  /**
   *
   *
   * <pre>
   * A unique complete token. This should be included in the
   * [SearchRequest][google.cloud.retail.v2.SearchRequest] resulting from this
   * completion, which enables accurate attribution of complete model
   * performance.
   * </pre>
   *
   * <code>string attribution_token = 2;</code>
   *
   * @return The bytes for attributionToken.
   */
  com.google.protobuf.ByteString getAttributionTokenBytes();

  /**
   *
   *
   * <pre>
   * Matched recent searches of this user. The maximum number of recent searches
   * is 10. This field is a restricted feature. Contact Retail Search support
   * team if you are interested in enabling it.
   * This feature is only available when
   * [CompleteQueryRequest.visitor_id][google.cloud.retail.v2.CompleteQueryRequest.visitor_id]
   * field is set and [UserEvent][google.cloud.retail.v2.UserEvent] is imported.
   * The recent searches satisfy the follow rules:
   *  * They are ordered from latest to oldest.
   *  * They are matched with
   *  [CompleteQueryRequest.query][google.cloud.retail.v2.CompleteQueryRequest.query]
   *  case insensitively.
   *  * They are transformed to lower cases.
   *  * They are UTF-8 safe.
   * Recent searches are deduplicated. More recent searches will be reserved
   * when duplication happens.
   * </pre>
   *
   * <code>
   * repeated .google.cloud.retail.v2.CompleteQueryResponse.RecentSearchResult recent_search_results = 3;
   * </code>
   */
  java.util.List<com.google.cloud.retail.v2.CompleteQueryResponse.RecentSearchResult>
      getRecentSearchResultsList();
  /**
   *
   *
   * <pre>
   * Matched recent searches of this user. The maximum number of recent searches
   * is 10. This field is a restricted feature. Contact Retail Search support
   * team if you are interested in enabling it.
   * This feature is only available when
   * [CompleteQueryRequest.visitor_id][google.cloud.retail.v2.CompleteQueryRequest.visitor_id]
   * field is set and [UserEvent][google.cloud.retail.v2.UserEvent] is imported.
   * The recent searches satisfy the follow rules:
   *  * They are ordered from latest to oldest.
   *  * They are matched with
   *  [CompleteQueryRequest.query][google.cloud.retail.v2.CompleteQueryRequest.query]
   *  case insensitively.
   *  * They are transformed to lower cases.
   *  * They are UTF-8 safe.
   * Recent searches are deduplicated. More recent searches will be reserved
   * when duplication happens.
   * </pre>
   *
   * <code>
   * repeated .google.cloud.retail.v2.CompleteQueryResponse.RecentSearchResult recent_search_results = 3;
   * </code>
   */
  com.google.cloud.retail.v2.CompleteQueryResponse.RecentSearchResult getRecentSearchResults(
      int index);
  /**
   *
   *
   * <pre>
   * Matched recent searches of this user. The maximum number of recent searches
   * is 10. This field is a restricted feature. Contact Retail Search support
   * team if you are interested in enabling it.
   * This feature is only available when
   * [CompleteQueryRequest.visitor_id][google.cloud.retail.v2.CompleteQueryRequest.visitor_id]
   * field is set and [UserEvent][google.cloud.retail.v2.UserEvent] is imported.
   * The recent searches satisfy the follow rules:
   *  * They are ordered from latest to oldest.
   *  * They are matched with
   *  [CompleteQueryRequest.query][google.cloud.retail.v2.CompleteQueryRequest.query]
   *  case insensitively.
   *  * They are transformed to lower cases.
   *  * They are UTF-8 safe.
   * Recent searches are deduplicated. More recent searches will be reserved
   * when duplication happens.
   * </pre>
   *
   * <code>
   * repeated .google.cloud.retail.v2.CompleteQueryResponse.RecentSearchResult recent_search_results = 3;
   * </code>
   */
  int getRecentSearchResultsCount();
  /**
   *
   *
   * <pre>
   * Matched recent searches of this user. The maximum number of recent searches
   * is 10. This field is a restricted feature. Contact Retail Search support
   * team if you are interested in enabling it.
   * This feature is only available when
   * [CompleteQueryRequest.visitor_id][google.cloud.retail.v2.CompleteQueryRequest.visitor_id]
   * field is set and [UserEvent][google.cloud.retail.v2.UserEvent] is imported.
   * The recent searches satisfy the follow rules:
   *  * They are ordered from latest to oldest.
   *  * They are matched with
   *  [CompleteQueryRequest.query][google.cloud.retail.v2.CompleteQueryRequest.query]
   *  case insensitively.
   *  * They are transformed to lower cases.
   *  * They are UTF-8 safe.
   * Recent searches are deduplicated. More recent searches will be reserved
   * when duplication happens.
   * </pre>
   *
   * <code>
   * repeated .google.cloud.retail.v2.CompleteQueryResponse.RecentSearchResult recent_search_results = 3;
   * </code>
   */
  java.util.List<
          ? extends com.google.cloud.retail.v2.CompleteQueryResponse.RecentSearchResultOrBuilder>
      getRecentSearchResultsOrBuilderList();
  /**
   *
   *
   * <pre>
   * Matched recent searches of this user. The maximum number of recent searches
   * is 10. This field is a restricted feature. Contact Retail Search support
   * team if you are interested in enabling it.
   * This feature is only available when
   * [CompleteQueryRequest.visitor_id][google.cloud.retail.v2.CompleteQueryRequest.visitor_id]
   * field is set and [UserEvent][google.cloud.retail.v2.UserEvent] is imported.
   * The recent searches satisfy the follow rules:
   *  * They are ordered from latest to oldest.
   *  * They are matched with
   *  [CompleteQueryRequest.query][google.cloud.retail.v2.CompleteQueryRequest.query]
   *  case insensitively.
   *  * They are transformed to lower cases.
   *  * They are UTF-8 safe.
   * Recent searches are deduplicated. More recent searches will be reserved
   * when duplication happens.
   * </pre>
   *
   * <code>
   * repeated .google.cloud.retail.v2.CompleteQueryResponse.RecentSearchResult recent_search_results = 3;
   * </code>
   */
  com.google.cloud.retail.v2.CompleteQueryResponse.RecentSearchResultOrBuilder
      getRecentSearchResultsOrBuilder(int index);
}
