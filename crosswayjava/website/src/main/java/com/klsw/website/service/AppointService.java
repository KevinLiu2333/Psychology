package com.klsw.website.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klsw.common.mall.mapper.TMallAppointMapper;
import com.klsw.common.mall.model.TMallAppoint;

@Service
public class AppointService extends _BaseService<TMallAppoint> {
	@Autowired
	private TMallAppointMapper tMallAppointMapper;
}
