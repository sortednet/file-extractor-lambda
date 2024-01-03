#!/bin/bash -eux

aws cloudformation deploy --template-file cfn-template.yaml --stack-name file-extractor --capabilities CAPABILITY_NAMED_IAM