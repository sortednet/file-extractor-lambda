#!/bin/bash

echo "Nothing to setup"

# could setup the ECR here too ???
TEMPLATE="ecr-cfn-template"
#aws cloudformation package --template-file $TEMPLATE --output-template-file out.yaml
#aws cloudformation deploy --template-file out.yaml --stack-name file-extractor-ecr --capabilities CAPABILITY_NAMED_IAM