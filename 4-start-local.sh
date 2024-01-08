#!/bin/bash -eux

ACCESS_KEY_ID=cat ~/.aws/credentials | grep aws_access_key_id | cut -d '=' -f 2 | tr -d ' '
SECRET_ACCESS_KEY=cat ~/.aws/credentials | grep aws_secret_access_key | cut -d '=' -f 2 | tr -d ' '

docker run --platform linux/amd64 -p 9000:8080 \
  -e AWS_ACCESS_KEY_ID=${ACCESS_KEY_ID} \
  -e AWS_SECRET_ACCESS_KEY=${SECRET_ACCESS_KEY} \
  573128978443.dkr.ecr.ap-southeast-2.amazonaws.com/file-extractor-jib