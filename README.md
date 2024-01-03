
# Build
run 
```shell
./2-build.sh version push
```
This will build 'version' (eg 0.0.9) and push to ECR

# Deploy
Edit `cfn-template.yaml` to make sure the image version matches the one built
run 
```shell
./3-deploy.sh
```

# Testing
Copy a file to s3 - output should be made to cloud watch
aws s3 cp test-file.txt s3://file-input-sorted-net/test-file3.txt