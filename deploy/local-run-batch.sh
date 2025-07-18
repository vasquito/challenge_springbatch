#!/bin/bash

# Ir desde .../springbatch/deploy al .../springbatch/
cd ..

# No se necesita agregar variables de entorno porque en application.yml ya esta preparada y ya puede ejecutar

echo
echo "-----------------------------------------------------------------------"
echo "                            Run BATCH                                  "
echo "-----------------------------------------------------------------------"
java -jar acc-batch/build/libs/acc-batch.jar