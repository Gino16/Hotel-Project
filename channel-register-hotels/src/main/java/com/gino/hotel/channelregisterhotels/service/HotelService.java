package com.gino.hotel.channelregisterhotels.service;


import com.gino.hotel.channelregisterhotels.model.HotelCreateRequest;
import com.gino.hotel.channelregisterhotels.model.HotelCreateResponse;
import com.gino.hotel.channelregisterhotels.model.HotelDetailResponse;
import com.gino.hotel.channelregisterhotels.model.SasTokenRequest;
import com.gino.hotel.channelregisterhotels.model.SasTokenResponse;
import reactor.core.publisher.Mono;

public interface HotelService {

  Mono<SasTokenResponse> getSassToken(Mono<SasTokenRequest> tokenRequest);

  Mono<HotelCreateResponse> saveHotel(Mono<HotelCreateRequest> hotelCreateResponse);

  Mono<HotelDetailResponse> getHotelById(String hotelId);

  Mono<Void> deleteHotel(String hotelId);
}
