package com.cairone.im.ui.ctrl;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;

import com.cairone.im.ui.exception.BadRequestException;
import com.cairone.im.util.ImageUtil;

@Component
public class ConvertServiceImpl implements ConvertService {

	@Override
	public Response convertToPng(File file) {
		
		try (InputStream is = new FileInputStream(file)) {
			
			BufferedInputStream buf = new BufferedInputStream(is);
			String contentType = ImageUtil.detectMimeType(buf, file.getName());
			
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			byte[] bytes = IOUtils.toByteArray(buf);
			baos.write(bytes, 0, bytes.length);
			
			return Response.ok((StreamingOutput) outputStream -> {
					baos.writeTo(outputStream);
					baos.flush();
				})
				.type(contentType)
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
