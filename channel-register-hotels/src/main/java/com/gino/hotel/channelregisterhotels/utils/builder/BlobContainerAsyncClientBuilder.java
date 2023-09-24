package com.gino.hotel.channelregisterhotels.utils.builder;

import com.azure.storage.blob.BlobContainerAsyncClient;
import com.azure.storage.blob.BlobServiceAsyncClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.azure.storage.common.StorageSharedKeyCredential;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BlobContainerAsyncClientBuilder {

  private String storageUrlPrefix;
  private String storageUrlSuffix;
  private String accountName;
  private String accountKey;
  private String containerName;

  public BlobContainerAsyncClientBuilder storageUrlPrefix(String storageUrlPrefix) {
    this.storageUrlPrefix = storageUrlPrefix;
    return this;
  }

  public BlobContainerAsyncClientBuilder storageUrlSuffix(String storageUrlSuffix) {
    this.storageUrlSuffix = storageUrlSuffix;
    return this;
  }

  public BlobContainerAsyncClientBuilder accountName(String accountName) {
    this.accountName = accountName;
    return this;
  }

  public BlobContainerAsyncClientBuilder accountKey(String accountKey) {
    this.accountKey = accountKey;
    return this;
  }

  public BlobContainerAsyncClientBuilder containerName(String containerName) {
    this.containerName = containerName;
    return this;
  }

  public static BlobContainerAsyncClientBuilder builder() {
    return new BlobContainerAsyncClientBuilder();
  }

  public BlobContainerAsyncClient build() {
    BlobServiceAsyncClient storageClient = new BlobServiceClientBuilder()
        .endpoint(getUrl(storageUrlPrefix, storageUrlSuffix, accountName))
        .credential(new StorageSharedKeyCredential(accountName, accountKey))
        .buildAsyncClient();
    return storageClient.getBlobContainerAsyncClient(containerName);
  }

  private String getUrl(String storageUrlPrefix, String storageUrlSuffix, String accountName) {
    return storageUrlPrefix.concat(accountName).concat(storageUrlSuffix);
  }
}
