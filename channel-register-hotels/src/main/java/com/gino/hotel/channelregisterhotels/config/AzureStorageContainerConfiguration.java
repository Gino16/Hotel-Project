package com.gino.hotel.channelregisterhotels.config;

import com.azure.storage.blob.BlobContainerAsyncClient;
import com.gino.hotel.channelregisterhotels.utils.builder.BlobContainerAsyncClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AzureStorageContainerConfiguration {

  @Bean(name = "blobContainerAsyncClient")
  BlobContainerAsyncClient blobContainerAsyncClient(
      @Value("${azure.storage.url.prefix}")
      String storageUrlPrefix,
      @Value("${azure.storage.url.suffix}")
      String storageUrlSuffix,
      @Value("${azure.storage.container.hotel-photos.account.name}")
      String accountName,
      @Value("${azure.storage.container.hotel-photos.account.key}")
      String accountKey,
      @Value("${azure.storage.container.hotel-photos.container-name}")
      String containerName
  ) {
    return BlobContainerAsyncClientBuilder
        .builder()
        .storageUrlPrefix(storageUrlPrefix)
        .storageUrlSuffix(storageUrlSuffix)
        .accountName(accountName)
        .accountKey(accountKey)
        .containerName(containerName)
        .build();
  }
}
