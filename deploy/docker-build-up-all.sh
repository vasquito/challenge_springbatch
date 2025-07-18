#!/bin/bash

# Ir desde .../springbatch/deploy al .../springbatch/
cd ..

echo "-----------------------------------------------------------------------"
echo "Construir las imágenes"
docker-compose build
echo "Levantar los servicios"
docker-compose up -d
echo "Verificar que todos estén corriendo"
docker ps

echo "-----------------------------------------------------------------------"
echo "                    Docker Build and UP Completed                      "
echo "-----------------------------------------------------------------------"