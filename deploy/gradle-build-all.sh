#!/bin/bash

# Ir desde .../springbatch/deploy al .../springbatch/
cd ..

echo "-----------------------------------------------------------------------"
#Preparar el ambiente local
echo "Prepare and Build"
mkdir "source"
mkdir "target"
./gradlew clean build
echo
echo
echo "-----------------------------------------------------------------------"
echo "                    Local Batch Build Completed                        "
echo "-----------------------------------------------------------------------"