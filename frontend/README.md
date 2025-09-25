### *Dependencias*

   Lib para cargar imagenes
       
       npm install react-filepond filepond --save
       npm install filepond-plugin-image-preview filepond-plugin-image-exif-orientation --save
       npm install filepond-plugin-get-file
       https://pqina.nl/filepond/
       https://pqina.nl/filepond/#adapters
       https://pqina.nl/filepond/docs/api/instance/properties/#disabling-credits
       https://github.com/nielsboogaard/filepond-plugin-get-file?tab=readme-ov-file -> plugin para la descar de archivos
       
       -- Para el boton de descarga de archivos import 'filepond-plugin-get-file/dist/filepond-plugin-get-file.min.css' registrar el pugin
          FilePond.registerPlugin(FilePondPluginGetFile);

### *Css con sass*
    
    - Para sass configurar  la extension  Live Sass Compiler

### *Despliegue a produccion*
    
    - Comando docker para compilar la imagen sudo docker build -t dmmlpaz/app-prinx-clientes:latest --network=host . 

    - Temporalmente la imagen esta almacenada en dockerhub dmmlpaz debido que el sistema esta en el ambiente cloud y no se tiene el acceso   
      para  hacer push a la imagen

    - Comando docker para genera la imagen del backedn spring boot sudo docker build -t dmmlpaz/wsrestprinxclientes-springboot:latest .

