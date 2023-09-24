package com.gino.hotel.channelregisterhotels.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CredentialFileResponse {

  private String sasToken;

  private SasFileProperties sasFileProperties;

}
