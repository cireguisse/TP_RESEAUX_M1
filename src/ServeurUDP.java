/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mamad
 */
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;

public class ServeurUDP {
    
    

	public static void main(String[] args) throws Exception {
		// Création d'un socket UDP sur le port 28414
		DatagramSocket socket = new DatagramSocket(28414);
		
		// tampon pour recevoir les données des datagrammes UDP
		final byte[] tampon = new byte[1024];
		
		// objet Java permettant de recevoir un datagramme UDP
		DatagramPacket dgram = new DatagramPacket(tampon, tampon.length);	
                
                ListeAuth list= new ListeAuth();
                ServerSocket socketserver = new ServerSocket(28414);

		while(true) {
			// attente et réception d'un datagramme UDP
			socket.receive(dgram);
			
			// extraction des données
			String chaine = new String(dgram.getData(), 0, dgram.getLength());
			
			System.out.println("Chaine reçue : "+chaine);
                        String[] splitArray = null; //tableau de chaînes
                        // On découpe la chaîne "str" à traiter et on récupère le résultat dans le tableau "splitArray"
                        splitArray = chaine.split(" ");
                        System.out.println("Type de requetes : "+splitArray[0]+" login :"+splitArray[1]+" password : "+splitArray[2] );
                        if(splitArray[0].equals("CHK")){
                            if(list.tester(splitArray[1], splitArray[2])==true){
                                System.out.println(" GOOD !!!");
                            }
                            else{
                                System.out.println(" BAD !!!");
                                
                            }
                        }
                        
                         
                                
			// on renvoie le message au client
			socket.send(dgram);
			
			// on replace la taille du tampon au max
			// elle a été modifiée lors de la réception
			dgram.setLength(tampon.length);
		}

	}

}