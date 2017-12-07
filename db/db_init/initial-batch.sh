#!/bin/sh

#Set environment variables 
export INPUT_PATH='./input/'
export OUTPUT_PATH='./output/'
export TOOLSET_PATH='./modules/'
export PGUSER='postgres'
export PGHOST='localhost'
export PGPASSWORD='root'
export PGDATABASE='robots'
export PGPORT='5432'

echo "*** Initial Batch Workflow Runner ***"
echo ""
echo ""
echo "Starting Group 1..."
node init-1.js
echo "Starting Group 2..."
node init-2.js