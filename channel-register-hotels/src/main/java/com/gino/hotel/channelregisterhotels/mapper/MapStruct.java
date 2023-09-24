package com.gino.hotel.channelregisterhotels.mapper;

import com.gino.hotel.bshotels.model.BsHotelCreateRequest;
import com.gino.hotel.bshotels.model.BsHotelCreateResponse;
import com.gino.hotel.bshotels.model.BsHotelDetailResponse;
import com.gino.hotel.bshotels.model.BsHotelListResponse;
import com.gino.hotel.channelregisterhotels.model.HotelCreateRequest;
import com.gino.hotel.channelregisterhotels.model.HotelCreateResponse;
import com.gino.hotel.channelregisterhotels.model.HotelDetailResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MapStruct {

  BsHotelCreateRequest toHotelCreateRequest(
      HotelCreateRequest hotelCreateRequest);

  HotelCreateResponse toHotelCreateResponse(
      BsHotelCreateResponse hotelCreateResponse);

  HotelDetailResponse toHotelDetailResponse(
      BsHotelDetailResponse bsHotelDetailResponse);
}
