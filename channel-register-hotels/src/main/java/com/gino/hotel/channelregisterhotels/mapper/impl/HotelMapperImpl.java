package com.gino.hotel.channelregisterhotels.mapper.impl;


import com.gino.hotel.channelregisterhotels.dto.CredentialFileResponse;
import com.gino.hotel.channelregisterhotels.mapper.HotelMapper;
import com.gino.hotel.channelregisterhotels.model.SasTokenResponse;
import org.springframework.stereotype.Component;

@Component
public class HotelMapperImpl implements HotelMapper {

  @Override
  public SasTokenResponse toSasTokenResponse(CredentialFileResponse credentialFileResponse) {
    return new SasTokenResponse()
        .url(credentialFileResponse.getSasFileProperties().getUrl()
            + "/" + credentialFileResponse.getSasFileProperties().getContainerName()
            + "/" + credentialFileResponse.getSasFileProperties().getFileName()
            + "?")
        .sassToken(credentialFileResponse.getSasToken())
        .fileName(credentialFileResponse.getSasFileProperties().getProcessingId());
  }
}
