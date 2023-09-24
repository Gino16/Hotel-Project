package com.gino.hotel.channelregisterhotels.clients;

import com.gino.hotel.bshotels.ApiClient;
import com.gino.hotel.bshotels.api.HotelApi;
import com.gino.hotel.bshotels.model.BsHotelCreateRequest;
import com.gino.hotel.bshotels.model.BsHotelCreateResponse;
import com.gino.hotel.bshotels.model.BsHotelDetailResponse;
import com.gino.hotel.bshotels.model.BsHotelListResponse;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Component
public class BsHotelClient {

  private final HotelApi hotelApi;

  public BsHotelClient(
      @Value("${api.clients.bs-hotels.base-path}")
      String basePath) {
    ApiClient apiClient = new ApiClient()
        .setBasePath(basePath)
        .addDefaultHeader("example", "something");
    hotelApi = new HotelApi(apiClient);
  }

  public Mono<BsHotelCreateResponse> saveHotel(BsHotelCreateRequest hotelCreateRequest) {
    return hotelApi.createHotel(hotelCreateRequest)
        .subscribeOn(Schedulers.boundedElastic());
  }

  public Mono<BsHotelListResponse> getHotels(Integer limit, Integer offset, String sort,
      String order) {
    return hotelApi.getHotels(limit, offset, sort, order)
        .subscribeOn(Schedulers.boundedElastic());
  }

  public Mono<BsHotelDetailResponse> getHotelById(UUID hotelId) {
    return hotelApi.getHotelById(hotelId)
        .subscribeOn(Schedulers.boundedElastic());
  }

  public Mono<Void> deleteHotel(UUID hotelId) {
    return hotelApi.deleteHotel(hotelId)
        .subscribeOn(Schedulers.boundedElastic());
  }

}
