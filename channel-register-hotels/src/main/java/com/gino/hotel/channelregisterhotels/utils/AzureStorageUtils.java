package com.gino.hotel.channelregisterhotels.utils;

import com.azure.storage.blob.BlobContainerAsyncClient;
import com.azure.storage.blob.sas.BlobContainerSasPermission;
import com.azure.storage.blob.sas.BlobServiceSasSignatureValues;
import com.gino.hotel.channelregisterhotels.dto.CredentialFileResponse;
import com.gino.hotel.channelregisterhotels.dto.SasFileProperties;
import java.time.Clock;
import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AzureStorageUtils {

  private final BlobContainerAsyncClient blobContainerAsyncClient;

  @Value("${azure.storage.sas.durationTokenSas}")
  String durationTokenSas;

  @Value("${azure.storage.properties.maxSizeFile}")
  Long maxSizeFile;

  public AzureStorageUtils(
      @Qualifier("blobContainerAsyncClient")
      BlobContainerAsyncClient blobContainerAsyncClient) {
    this.blobContainerAsyncClient = blobContainerAsyncClient;
  }

  public CredentialFileResponse generateToken(String permission) {
    var newFileName = UUID.randomUUID() + ".jpg";
    return CredentialFileResponse.builder()
        .sasToken(generateSassToken(newFileName, permission))
        .sasFileProperties(buildSasFileProperties(newFileName))
        .build();
  }

  private String generateSassToken(String newFileName, String permission) {
    var currentTime = OffsetDateTime.now(Clock.systemDefaultZone());
    return blobContainerAsyncClient
        .getBlobAsyncClient(newFileName)
        .generateSas(new BlobServiceSasSignatureValues(
            currentTime.plus(Integer.parseInt(durationTokenSas), ChronoUnit.MILLIS),
            BlobContainerSasPermission.parse(permission))
            .setStartTime(currentTime));
  }

  private SasFileProperties buildSasFileProperties(String fileName) {
    return SasFileProperties.builder()
        .url(blobContainerAsyncClient.getAccountUrl())
        .containerName(blobContainerAsyncClient.getBlobContainerName())
        .fileName(fileName)
        .processingId(fileName.substring(0, fileName.length() - 4))
        .build();
  }
}
