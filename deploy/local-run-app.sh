#!/bin/bash

# Ir desde .../springbatch/deploy al .../springbatch/
cd ..

# No se necesita agregar variables de entorno porque en application.yml ya esta preparada y ya puede ejecutar

echo
echo "-----------------------------------------------------------------------"
echo "                            Run APP                                    "
echo "-----------------------------------------------------------------------"
java -jar acc-app/build/libs/acc-app.jar
