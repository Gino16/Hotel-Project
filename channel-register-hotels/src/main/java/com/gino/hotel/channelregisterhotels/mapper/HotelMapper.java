package com.gino.hotel.channelregisterhotels.mapper;


import com.gino.hotel.channelregisterhotels.dto.CredentialFileResponse;
import com.gino.hotel.channelregisterhotels.model.SasTokenResponse;

public interface HotelMapper {
  SasTokenResponse toSasTokenResponse(CredentialFileResponse credentialFileResponse);

}
