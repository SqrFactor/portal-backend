package com.sqrfactor.upload;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.AccessControlList;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GroupGrantee;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.Permission;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;

public class S3Upload {

	private Map<String, String> allowedFileTypes = new HashMap<>();
	
	public S3Upload() {
		allowedFileTypes = populateFileTypes();
	}

	public Boolean upload(FileInputStream stream, long fileSize, String destinationFilePath, String destinationFileName,
			String fileType) {
		if(!allowedFileTypes.containsKey(fileType)){
			System.out.println("File Type not supported");
			return false;
		}
		String keyName = destinationFileName;

		String existingBucketName = "sqrfactor" + destinationFilePath;

		String amazonFileUploadLocationOriginal = existingBucketName;

		AmazonS3 s3Client = null;
		try {
			s3Client = new AmazonS3Client(new PropertiesCredentials(
					S3Upload.class.getClassLoader().getResourceAsStream("/AwsCredentials.properties")));
		} catch (IllegalArgumentException | IOException e) {
			e.printStackTrace();
		}
		AccessControlList acl = new AccessControlList();
		acl.grantPermission(GroupGrantee.AllUsers, Permission.Read);

		ObjectMetadata objectMetadata = new ObjectMetadata();
		objectMetadata.setContentType(allowedFileTypes.get(fileType));
		objectMetadata.setContentLength(fileSize);
		PutObjectRequest putObjectRequest = new PutObjectRequest(amazonFileUploadLocationOriginal, keyName, stream,
				objectMetadata);
		putObjectRequest.getRequestClientOptions().setReadLimit(10000000);
		putObjectRequest.withAccessControlList(acl);
		putObjectRequest.withCannedAcl(CannedAccessControlList.PublicRead);

		PutObjectResult result = s3Client.putObject(putObjectRequest);
		System.out.println("Etag:" + result.getETag() + "-->" + result);
		return true;

	}

	public Boolean upload(String inputFilePath, String destinationFilePath, String destinationFileName,
			String fileType) {

		//TODO Refactor
		FileInputStream fis = null; 
		File outputFileName = null;
		try {
			InputStream in = null;
			ByteArrayInputStream bin = null;
			URL url = new URL(inputFilePath);
		
		    in = url.openStream();
		    ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		    IOUtils.copy(in, buffer);

		    bin = new ByteArrayInputStream(buffer.toByteArray());
		    outputFileName = new File("/tmp/" + destinationFileName);
			outputFileName.createNewFile();
			IOUtils.copy(bin, new FileOutputStream(outputFileName));
			
			fis = new FileInputStream(outputFileName);

		}catch(Exception e){
			return false;
		}
		
		if(!allowedFileTypes.containsKey(fileType)){
			System.out.println("File Type not supported");
			return false;
		}
		String keyName = destinationFileName;

		String existingBucketName = "sqrfactor" + destinationFilePath;

		String amazonFileUploadLocationOriginal = existingBucketName;

		AmazonS3 s3Client = null;
		try {
			s3Client = new AmazonS3Client(new PropertiesCredentials(
					S3Upload.class.getClassLoader().getResourceAsStream("/AwsCredentials.properties")));
		} catch (IllegalArgumentException | IOException e) {
			return false;
		}
		AccessControlList acl = new AccessControlList();
		acl.grantPermission(GroupGrantee.AllUsers, Permission.Read);

		ObjectMetadata objectMetadata = new ObjectMetadata();
		objectMetadata.setContentType(allowedFileTypes.get(fileType));
		PutObjectRequest putObjectRequest = new PutObjectRequest(amazonFileUploadLocationOriginal, keyName, fis,
				objectMetadata);
		putObjectRequest.getRequestClientOptions().setReadLimit(10000000);
		putObjectRequest.withAccessControlList(acl);
		putObjectRequest.withCannedAcl(CannedAccessControlList.PublicRead);

		PutObjectResult result = s3Client.putObject(putObjectRequest);
		System.out.println("Etag:" + result.getETag() + "-->" + result);
		
		//Cleanup
		try {
			FileUtils.forceDelete(outputFileName);
		} catch (IOException e) {
			return true;
		}
		
		return true;
	}


	public Boolean uploadImage(byte[] uploadImage, String destinationFilePath, String destinationFileName,
			String fileType) {

		//TODO Refactor
		FileInputStream fis = null; 
		File outputFileName = null;
		try {
			InputStream in = null;
			ByteArrayInputStream bin = null;
		
		    bin = new ByteArrayInputStream(uploadImage);
		    outputFileName = new File("/tmp/" + destinationFileName);
			outputFileName.createNewFile();
			IOUtils.copy(bin, new FileOutputStream(outputFileName));
			
			fis = new FileInputStream(outputFileName);

		}catch(Exception e){
			return false;
		}
		
		if(!allowedFileTypes.containsKey(fileType)){
			System.out.println("File Type not supported");
			return false;
		}
		String keyName = destinationFileName;

		String existingBucketName = "sqrfactor" + destinationFilePath;

		String amazonFileUploadLocationOriginal = existingBucketName;

		AmazonS3 s3Client = null;
		try {
			s3Client = new AmazonS3Client(new PropertiesCredentials(
					S3Upload.class.getClassLoader().getResourceAsStream("/AwsCredentials.properties")));
		} catch (IllegalArgumentException | IOException e) {
			return false;
		}
		AccessControlList acl = new AccessControlList();
		acl.grantPermission(GroupGrantee.AllUsers, Permission.Read);

		ObjectMetadata objectMetadata = new ObjectMetadata();
		objectMetadata.setContentType(allowedFileTypes.get(fileType));
		PutObjectRequest putObjectRequest = new PutObjectRequest(amazonFileUploadLocationOriginal, keyName, fis,
				objectMetadata);
		putObjectRequest.getRequestClientOptions().setReadLimit(10000000);
		putObjectRequest.withAccessControlList(acl);
		putObjectRequest.withCannedAcl(CannedAccessControlList.PublicRead);

		PutObjectResult result = s3Client.putObject(putObjectRequest);
		System.out.println("Etag:" + result.getETag() + "-->" + result);
		
		//Cleanup
		try {
			FileUtils.forceDelete(outputFileName);
		} catch (IOException e) {
			return true;
		}
		
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		
		S3Upload s3Upload = new S3Upload();
		String data = "data:image/gif;base64,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";
		String base64Image = data.split(",")[1];
		byte[] imageBytes = javax.xml.bind.DatatypeConverter.parseBase64Binary(base64Image);
		
		//String imageUrl = "https://lh4.googleusercontent.com/-5Gi7JkcE9eQ/AAAAAAAAAAI/AAAAAAAAAII/IHd2uGv6nbE/s96-c/photo.jpg";
		String destinationFileName = "abc" + ".JPG";
		String destinationFilePath = "/angad";
		String fileType = "jpg";
		
		boolean uploaded = s3Upload.uploadImage(imageBytes,  destinationFilePath, destinationFileName, fileType);
				
		 //s3Upload.upload(imageUrl, filePath, fileName, fileType);
	}
	
	private Map<String, String> populateFileTypes(){
		Map<String, String> map = new HashMap<>();
		
		//image
		map.put("jpg", "image/jpeg");
		map.put("png", "image/png");
		map.put("bmp", "image/bmp");
		map.put("tiff", "image/tiff");
		map.put("ico", "image/x-icon");
		
		//application
		map.put("pdf", "application/pdf");
		map.put("doc", "application/msword");
		map.put("docx", "application/vnd.openxmlformats-officedocument.wordprocessingml.document");
		map.put("csv", "application/vnd.ms-excel");
		
		//video
		map.put("mp4", "video/mp4");
		map.put("avi", "video/avi");
		map.put("mpeg", "video/mpeg");
		
		map.put("zip", "application/zip");
		
		return map;
	}
}