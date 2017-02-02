/**
 * 
 */
package com.sqrfactor.upload;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.annotation.MultipartConfig;

import org.apache.commons.codec.binary.Base64;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Angad Gill
 *
 */
@RestController
@RequestMapping(value = "/uploadservice")
// Max uploaded file size (here it is 20 MB)
@MultipartConfig(fileSizeThreshold = 20971520)
public class UploadService {

	@RequestMapping(value = "/upload")
	public boolean uploadFile(@RequestParam("uploadedFile") MultipartFile uploadedFileRef,
			@RequestParam("filePath") String filePath, @RequestParam("fileName") String fileName,@RequestParam("fileType")String fileType) {

		FileInputStream reader = null;
		// Create the input stream to uploaded file to read data from it.
		try {
			reader = (FileInputStream) uploadedFileRef.getInputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		S3Upload s3Upload = new S3Upload();
		String destinationFileName = fileName;
		String destinationFilePath = filePath;
		
		boolean uploaded = s3Upload.upload(reader, uploadedFileRef.getSize(), destinationFilePath, destinationFileName, fileType);
		
		if (!uploaded) {
			System.out.println("File Could not be uploaded");
		}

		return true;
	}

	@RequestMapping(value = "/uploadimage", method = RequestMethod.POST)
	public boolean uploadFile(@RequestParam("uploadedFile") String uploadedFile,
			@RequestParam("filePath") String filePath, @RequestParam("fileName") String fileName,@RequestParam("fileType")String fileType) {

		
		String encodingPrefix = "base64,";
		int contentStartIndex = uploadedFile.indexOf(encodingPrefix) + encodingPrefix.length();
		byte[] imageData = Base64.decodeBase64(uploadedFile.substring(contentStartIndex));
		
		S3Upload s3Upload = new S3Upload();
		String destinationFileName = fileName;
		String destinationFilePath = filePath;
		
		boolean uploaded = s3Upload.uploadImage(imageData, destinationFilePath, destinationFileName, fileType);
		
		if (!uploaded) {
			System.out.println("File Could not be uploaded");
		}

		return true;
	}

}