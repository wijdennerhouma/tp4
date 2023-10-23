package tp4_1;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Entrez votre message (Prénom Nom) : ");
            String message = scanner.nextLine();

            DatagramSocket socket = new DatagramSocket();
            InetAddress serverAddress = InetAddress.getByName("localhost"); // Mettez l'adresse du serveur ici

            // Envoie le message au serveur sous la forme "Prénom Nom".
            byte[] messageData = message.getBytes();
            DatagramPacket packet = new DatagramPacket(messageData, messageData.length, serverAddress, Server.port);
            socket.send(packet);

            // Recevoir la réponse du serveur.
            byte[] buffer = new byte[1024];
            DatagramPacket reponsePacket = new DatagramPacket(buffer, buffer.length);
            socket.receive(reponsePacket);
            String reponse = new String(reponsePacket.getData(), 0, reponsePacket.getLength());
            System.out.println("Réponse du serveur : " + reponse);

            socket.close();
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
