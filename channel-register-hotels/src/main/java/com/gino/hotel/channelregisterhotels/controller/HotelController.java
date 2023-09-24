package com.gino.hotel.channelregisterhotels.controller;


import com.gino.hotel.channelregisterhotels.api.HotelsApi;
import com.gino.hotel.channelregisterhotels.model.HotelCreateRequest;
import com.gino.hotel.channelregisterhotels.model.HotelCreateResponse;
import com.gino.hotel.channelregisterhotels.model.HotelDetailResponse;
import com.gino.hotel.channelregisterhotels.model.SasTokenRequest;
import com.gino.hotel.channelregisterhotels.model.SasTokenResponse;
import com.gino.hotel.channelregisterhotels.service.HotelService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Controller
@RequiredArgsConstructor
public class HotelController implements HotelsApi {

  private final HotelService hotelService;


  @Override
  public Mono<ResponseEntity<SasTokenResponse>> getSassToken(Mono<SasTokenRequest> sasTokenRequest,
      ServerWebExchange exchange) {
    return hotelService.getSassToken(sasTokenRequest).map(ResponseEntity::ok);
  }

  @Override
  public Mono<ResponseEntity<HotelCreateResponse>> createHotel(
      Mono<HotelCreateRequest> hotelCreateRequest, ServerWebExchange exchange) {
    return hotelService.saveHotel(hotelCreateRequest).map(ResponseEntity::ok);
  }

  @Override
  public Mono<ResponseEntity<HotelDetailResponse>> getHotelById(UUID hotelId,
      ServerWebExchange exchange) {
    return hotelService.getHotelById(hotelId.toString()).map(ResponseEntity::ok);
  }

  @Override
  public Mono<ResponseEntity<Void>> deleteHotel(UUID hotelId, ServerWebExchange exchange) {
    return hotelService.deleteHotel(hotelId.toString())
        .then(Mono.just(ResponseEntity.noContent().build()));
  }
}
