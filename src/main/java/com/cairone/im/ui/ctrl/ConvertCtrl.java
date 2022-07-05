package com.cairone.im.ui.ctrl;

import java.io.InputStream;

import org.springframework.stereotype.Component;

@Component
public class ConvertCtrl implements ConvertService {

	@Override
	public InputStream convert(String input, String output, InputStream is) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String echo(String message) {
		return message.toUpperCase();
	}

}
