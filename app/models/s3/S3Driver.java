package models.s3;

import com.amazonaws.auth.ClasspathPropertiesFileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.*;
import java.io.File;

public class S3Driver {
	AmazonS3 s3 = new AmazonS3Client(new ClasspathPropertiesFileCredentialsProvider());
	String bucketName = "openshade";

	public S3Driver(File file, String key) {
		s3.putObject(new PutObjectRequest(bucketName, key, file));

	}
}