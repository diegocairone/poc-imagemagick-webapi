package com.cairone.im.ui.ctrl;

import java.io.File;
import java.io.InputStream;

import org.springframework.stereotype.Component;

@Component
public class ConvertServiceImpl implements ConvertService {

	@Override
	public InputStream convertToPng(File file) {
		return null;
	}

	@Override
	public String echo(String message) {
		return message.toUpperCase();
	}
}
