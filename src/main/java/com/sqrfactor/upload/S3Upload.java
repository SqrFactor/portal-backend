package com.sqrfactor.upload;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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

	public static void main(String[] args) throws IOException {
		S3Upload s3Upload = new S3Upload();

		String fileName = "mypic1.JPG";
		String filePath = "E://Office Party - Big Brewsky - 12 march 2016//IMG_0376.JPG";

		// s3Upload.upload(filePath, fileName);
	}
	
	private Map<String, String> populateFileTypes(){
		Map<String, String> map = new HashMap<>();
		map.put("jpg", "image/jpeg");
		map.put("pdf", "application/pdf");
		map.put("mp4", "video/mp4");
		return map;
	}
}