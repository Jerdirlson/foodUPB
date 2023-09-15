package client;

import java.util.Scanner;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Properties;

public class MainClient {
    public static void main(String[] args) throws RemoteException {
        Properties properties = new Properties();
        try {
            /*properties.load(new FileInputStream(new File("client.properties")));
            Client client = new Client(
                (String) properties.get("IP"),
                (String) properties.get("PORT"),
                (String) properties.get("SERVICENAME")
            );*/
            Client client = new Client("localhost","5000","service");
            Scanner scn = new Scanner(System.in);
            String nUser;
            String contra;
            System.out.println("HOLIII"+client.getUsuarioCompleto().getNombre()+client.getUsuarioCompleto().getContrasena());
            char op;
            boolean ini = false;
            int num;
                do{
                    System.out.println("1. Ingresar");
                    System.out.println("2. Retirar");
                    System.out.println("3. Ver Saldo");
                    System.out.println("4. Registrar Usuario");
                    System.out.println("0. Cerrar Sesion");
                    op = (scn.next()).charAt(0);
                    switch (op){
                        case '1': System.out.print("Digite saldo a ingresar: ");
                            num= scn.nextInt();
                            System.out.print("Ingrese usuario: ");
                            nUser = scn.next();
                            System.out.print("Ingrese contrasena: ");
                            contra = scn.next();
                            ini = client.inicioDeSesion(nUser,contra);
                            if(ini){}else{System.out.println("Sesion no iniciada, nombre de usuario o contrasena incorrectos");}
                            client.ingresar(num,nUser,contra);
                        break;
                        case '2': System.out.print("Digite saldo a retirar: ");
                            num= scn.nextInt();
                            System.out.print("Ingrese usuario: ");
                            nUser = scn.next();
                            System.out.print("Ingrese contrasena: ");
                            contra = scn.next();
                            ini = client.inicioDeSesion(nUser,contra);
                            if(ini){}else{System.out.println("Sesion no iniciada, nombre de usuario o contrasena incorrectos");}
                            if( !(client.retirar(num,nUser,contra)) ){ System.out.println("Saldo insuficiente"); };
                        break;
                        case '3': 
                            System.out.print("Ingrese usuario: ");
                            nUser = scn.next();
                            System.out.print("Ingrese contrasena: ");
                            contra = scn.next();
                            ini = client.inicioDeSesion(nUser,contra);
                            if(ini){}else{System.out.println("Sesion no iniciada, nombre de usuario o contrasena incorrectos");}
                            System.out.println("Su saldo es: "+client.getSaldo(nUser,contra)); break;
                        case '4':
                            System.out.print("Ingrese usuario a registrar: ");
                            nUser = scn.next();
                            System.out.print("Ingrese una contrasena para este usuario: ");
                            contra = scn.next();
                            if(!(client.setUser(nUser, contra))){
                                System.out.println("El usuario ya existe.");
                            }
                        break;
                        case '5':
                            String nser = scn.next();
                            Object user = client.getUsuario(nser);
                            System.out.println(user);
                            user = client.getUsuario(nser);
                            System.out.println(user);
                            user = client.getUsuario(nser);
                            System.out.println(user);
                        break;
                        case '0': client.cerrarSesion(); System.out.println("Sesion Cerrada"); break;
                    }
                }while(op!='0');
            
        } /*catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }*/ catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Muchas gracias por usar nuestros servicios, vuelva pronto.");
    }
}