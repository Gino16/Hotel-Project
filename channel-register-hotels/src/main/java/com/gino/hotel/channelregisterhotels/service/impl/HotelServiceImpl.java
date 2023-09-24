package com.gino.hotel.channelregisterhotels.service.impl;


import static com.gino.hotel.channelregisterhotels.utils.Constants.KEY_PERMISSION_UPLOAD;

import com.gino.hotel.channelregisterhotels.clients.BsHotelClient;
import com.gino.hotel.channelregisterhotels.mapper.HotelMapper;
import com.gino.hotel.channelregisterhotels.mapper.MapStruct;
import com.gino.hotel.channelregisterhotels.model.HotelCreateRequest;
import com.gino.hotel.channelregisterhotels.model.HotelCreateResponse;
import com.gino.hotel.channelregisterhotels.model.HotelDetailResponse;
import com.gino.hotel.channelregisterhotels.model.SasTokenRequest;
import com.gino.hotel.channelregisterhotels.model.SasTokenResponse;
import com.gino.hotel.channelregisterhotels.service.HotelService;
import com.gino.hotel.channelregisterhotels.utils.AzureStorageUtils;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {

  private final BsHotelClient bsHotelClient;
  private final AzureStorageUtils azureStorageUtils;
  private final HotelMapper hotelMapper;
  private final MapStruct mapStruct;

  @Override
  public Mono<SasTokenResponse> getSassToken(Mono<SasTokenRequest> tokenRequest) {
    return tokenRequest
        .map(sasTokenRequest -> azureStorageUtils.generateToken(KEY_PERMISSION_UPLOAD))
        .map(hotelMapper::toSasTokenResponse);
  }

  @Override
  public Mono<HotelCreateResponse> saveHotel(Mono<HotelCreateRequest> hotelCreateResponse) {
    return hotelCreateResponse
        .map(mapStruct::toHotelCreateRequest)
        .flatMap(bsHotelClient::saveHotel)
        .map(mapStruct::toHotelCreateResponse);
  }

  @Override
  public Mono<HotelDetailResponse> getHotelById(String hotelId) {
    return bsHotelClient.getHotelById(UUID.fromString(hotelId))
        .map(mapStruct::toHotelDetailResponse);
  }

  @Override
  public Mono<Void> deleteHotel(String hotelId) {
    return bsHotelClient.deleteHotel(UUID.fromString(hotelId));
  }
}
