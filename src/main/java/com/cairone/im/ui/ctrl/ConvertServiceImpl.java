package com.cairone.im.ui.ctrl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;

import org.springframework.stereotype.Component;

import com.cairone.im.service.ImageMagickService;
import com.cairone.im.ui.exception.BadRequestException;

@Component
public class ConvertServiceImpl implements ConvertService {

	private final ImageMagickService imageMagickService;

	@Inject
	public ConvertServiceImpl(ImageMagickService imageMagickService) {
		this.imageMagickService = imageMagickService;
	}

	@Override
	public Response convertToPng(File file) {
		
		try (InputStream is = new FileInputStream(file)) {
			
			ByteArrayOutputStream baos = imageMagickService.mogrify(file, "png");
			
			return Response.ok((StreamingOutput) outputStream -> {
					baos.writeTo(outputStream);
					baos.flush();
				})
				.type("image/png")
				.build();
			
		} catch (IOException e) {
			throw new BadRequestException(e, e.getMessage());
		}
	}

	@Override
	public String echo(String message) {
		return message.toUpperCase();
	}
}
