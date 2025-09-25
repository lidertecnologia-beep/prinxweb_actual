


### *Despliegue a produccion*

    - Comando docker para compilar la imagen no olvidar crear un version nueva a latest:
      
        sudo docker build -t dmmlpaz/wsrestprinxclientes-springboot:latest --network=host . 
      sudo docker push dmmlpaz/wsrestprinxclientes-springboot:latest

    - Temporalmente la imagen esta almacenada en dockerhub dmmlpaz debido que el sistema esta en el ambiente cloud y no se tiene el acceso   
      para  hacer push a la imagen