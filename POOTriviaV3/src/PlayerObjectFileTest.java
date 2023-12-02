public class PlayerObjectFileTest {
    public static void main(String[] args) {
        // Create a sample Player object
        Player player1 = new Player("John Doe", 100);
        Player player2 = new Player("Orest Martyn", 90);
        Player player3 = new Player("Joao Gabriel Cordes", 80);
        Player player4 = new Player("", 70);


        // Create an object file for the Player
        player1.createObjectFile();
        player2.createObjectFile();
        player3.createObjectFile();
        player4.createObjectFile();


     }
}