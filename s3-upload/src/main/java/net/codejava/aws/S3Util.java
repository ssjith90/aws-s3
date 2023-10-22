package net.codejava.aws;


import java.io.IOException;
import java.io.InputStream;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;

public class S3Util {
    private static final String BUCKET = "your-bucket-name";

    public static void uploadFile(String fileName, InputStream inputStream){
        try {

            final AwsBasicCredentials credentials = AwsBasicCredentials.create("test", "test");
            final StaticCredentialsProvider credentialsProvider = StaticCredentialsProvider.create(credentials);
//        S3Client client = S3Client.builder().build();

            S3Client client = S3Client
                    .builder()
                    .region(Region.US_EAST_1)
                    .credentialsProvider(credentialsProvider)
                    .build();


            PutObjectRequest request = PutObjectRequest.builder()
                    .bucket(BUCKET)
                    .key(fileName)
                    .build();

            client.putObject(request,
                    RequestBody.fromInputStream(inputStream, inputStream.available()));
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}