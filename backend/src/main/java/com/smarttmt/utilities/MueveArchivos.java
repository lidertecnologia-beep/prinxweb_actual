/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarttmt.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Leo
 */
public class MueveArchivos {

    static File creaDirectorio;//Objeto que permite crear directorio y verificar si existe
    static File archivoDestino;//Objeto que ubica archivo destino
    static InputStream entrada;//Objeto que permite leer archivo
    static FileOutputStream salida;//Objeto que permite escribir archivo
    static String file;

    public static void mover(InputStream is, String rutaMover, String subDirec, String archivo) {

        boolean booExiste;
        int lineaDeArchivo;
        try {
            System.out.println("=====Verifica si existe directorio -> " + rutaMover + " subDirec-> " + subDirec);
            entrada = is;
            System.out.println("paso 2");
            //verifica si existe directorio
            creaDirectorio = new File(rutaMover);
            booExiste = creaDirectorio.exists();

            if (!booExiste) {
                System.out.println("=====No existe directorio se crea -> " + creaDirectorio.getPath());
                if (creaDirectorio.mkdirs()) {
                    System.out.println("=====Directorio creado correctamente Ok.. -> " + creaDirectorio.getPath());
                } else {
                    System.out.println("=====Directorio no se creo  ERROR.... -> ");
                }
            }
            file = rutaMover;
            //verifica si existe el subDirectorio
            if (subDirec != null) {
                file = rutaMover + subDirec;
                System.out.println("=====Se crea subdirectorio -> " + file);
                creaDirectorio = new File(file);
                if (creaDirectorio.mkdirs()) {
                    System.out.println("=====Sub Directorio creado correctamente Ok.. -> " + creaDirectorio.getPath());
                } else {
                    System.out.println("=====Sub  Directorio no se creo  ERROR.... -> ");
                }
            }
            archivoDestino = new File(file + System.getProperty("file.separator") + archivo);
            System.out.println(" Ruta del archivo a mover -> " + archivoDestino);
            salida = new FileOutputStream(archivoDestino);
            System.out.println("=====Se inicia lectura del archivo para ser movido===");
            while ((lineaDeArchivo = entrada.read()) != -1) {
                salida.write(lineaDeArchivo);
            }
            entrada.close();
            salida.close();
            System.out.println("=====Se termina proceso correctamente===");
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
    }

    public static void main(String args[]) {
        InputStream targetStream = null;
        try {
            File initialFile = new File("D:\\Users\\Usuario\\Desktop\\013.pdf");
            targetStream = new FileInputStream(initialFile);
            MueveArchivos.mover(targetStream, "D:\\tmp\\archivoRestFulPrinx\\SMART\\4145", null, "013.pdf");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MueveArchivos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                targetStream.close();
            } catch (IOException ex) {
                Logger.getLogger(MueveArchivos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
