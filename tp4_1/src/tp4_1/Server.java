package tp4_1;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Server {
    final static int port = 1778;
    private static byte[] buffer = new byte[1024];

    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket(port);

            while (true) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);

                // Traitement du paquet reçu (le message du client).
                String message = new String(packet.getData(), 0, packet.getLength());
                InetAddress clientAddress = packet.getAddress();
                int clientPort = packet.getPort();

                // Construction de la réponse du serveur.
                String[] parts = message.split(" ");
                if (parts.length == 2) {
                    String prenom = parts[0];
                    String nom = parts[1];
                    String reponse = "Bienvenue " + prenom + " " + nom;
                    byte[] reponseData = reponse.getBytes();
                    DatagramPacket reponsePacket = new DatagramPacket(reponseData, reponseData.length, clientAddress, clientPort);
                    socket.send(reponsePacket);

                    System.out.println("Message reçu du client : " + message);
                    System.out.println("Réponse envoyée au client : " + reponse);
                    System.out.println("Adresse du client : " + clientAddress.getHostAddress());
                    System.out.println("Numéro de port du client : " + clientPort);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
