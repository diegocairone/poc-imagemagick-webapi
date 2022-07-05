package com.cairone.im.service;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.stereotype.Service;

import com.cairone.im.AppException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ImageMagickService {

	private static final String MOGRIFY_PATH = "/usr/local/bin/mogrify";
	
	public ByteArrayOutputStream mogrify(File file, String outFormat) {
		
		// command: mogrify -format [output format] path/to/file
		
		String bashCmd = String.format(
				"%s -format %s %s", MOGRIFY_PATH, outFormat, file.getAbsolutePath());
		log.info("Command to be executed: {}", bashCmd);
		
		ProcessBuilder processBuilder = new ProcessBuilder("bash", "-c", bashCmd);
		
		try {

			Process process = processBuilder.start();
	
			StringBuilder output = new StringBuilder();
	
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(process.getInputStream()));
			
			String line;
			while ((line = reader.readLine()) != null) {
				output.append(line + "\n");
			}

			if (!output.toString().isBlank()) {
				log.info(output.toString());
			}
			int rv = process.waitFor();
		
			if (rv == 0) {
				ByteArrayOutputStream baos = toBaos(file.getAbsolutePath() + "." + outFormat);
				return baos;
			} else {
				throw new AppException("Command could not be executed! Exit code: " + rv);
			}
			
		} catch (IOException | InterruptedException ex) {
			log.error(ex.getMessage(), ex);
			throw new AppException(ex, ex.getMessage());
		}
	}
	
	private ByteArrayOutputStream toBaos(String path) {
		try {
			byte[] buffer = new byte[4096];
		    BufferedInputStream bis = new BufferedInputStream(
		    		new FileInputStream(path));
		    ByteArrayOutputStream baos = new ByteArrayOutputStream();
		    int bytes = 0;
		    while ((bytes = bis.read(buffer, 0, buffer.length)) > 0) {
		        baos.write(buffer, 0, bytes);
		    }
		    baos.close();
		    bis.close();
		    return baos;
		} catch (IOException e) {
			log.error(e.getMessage(), e);
			throw new AppException(e, e.getMessage());
		}
	}
}
