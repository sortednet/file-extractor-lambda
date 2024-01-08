#!/bin/sh

# Jib puts the classes in /app
# /app/classes
# /apps/libs
# /app/resources

# aws-lambda-rie uses $LAMBDA_TASK_ROOT
# ${LAMBDA_TASK_ROOT}/   - classes
# ${LAMBDA_TASK_ROOT}/lib - dependencies

#ln -s /app/classes/* ${LAMBDA_TASK_ROOT}/
#ln -s /app/resources/* ${LAMBDA_TASK_ROOT}/
#ln -s /app/libs ${LAMBDA_TASK_ROOT}/lib

# copy / mv from the jib structure to the lambda structure
cp -R /app/resources/* ${LAMBDA_TASK_ROOT}/
cp -R /app/classes/* ${LAMBDA_TASK_ROOT}/
mkdir -p ${LAMBDA_TASK_ROOT}/lib
mv /app/libs/* ${LAMBDA_TASK_ROOT}/lib/

ls -lR ${LAMBDA_TASK_ROOT}

/lambda-entrypoint.sh $*