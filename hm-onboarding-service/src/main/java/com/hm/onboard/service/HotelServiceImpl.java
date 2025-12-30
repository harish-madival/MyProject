package com.hm.onboard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hm.onboard.dao.HotelDao;
import com.hm.onboard.model.HotelEntity;

@Service
public class HotelServiceImpl implements HotelService {
	
	@Autowired
	HotelDao hotelDao;

	@Override
	public HotelEntity saveHotelData(HotelEntity hotelEntity, String userId) {
		hotelEntity.setUserId(userId);
		return hotelDao.saveHotel(hotelEntity);
	}

	@Override
	public List<HotelEntity> getHotelData(String userId, String hotelId) {
		return hotelDao.getHotelData(userId, hotelId);
	}

	@Override
	public void deleteHotelData(String userId, String hotelId) {
		hotelDao.deleteHotelData(userId, hotelId);
		
	}

	@Override
	public void updateHotelData(HotelEntity hotelEntity, String userId, String hotelId) {
		hotelDao.updateHotel(hotelEntity, userId, hotelId);
	}

}
