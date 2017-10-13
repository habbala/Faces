import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class Faces {
    //Read 300 files.
    //Store as 2/3 as learning images and 1/3 as test images.
    //Don't info that's not an image.
    //Read 20x20 pixel file
    //Pixel has 32 grey levels.

    //training-file.txt
    // training-facit.txt
    // test-file.txt
    public static void main(String[] args) {

        //String trainingFile = args[0];
        //String trainingFacit = args[1];
        //String testFile = args[2];

        String trainingFile = "training.txt";

        /*
         * Read faces from file.
         */
        LinkedList<char[][]> testFaces = new LinkedList<>();

        BufferedReader br = null;

        try {

            br = new BufferedReader(new FileReader(trainingFile));

            String line;

            char[][] face = new char[20][20];

            int y = 0;

            while ((line = br.readLine()) != null) {

                if(!line.isEmpty()) {
                    if (line.charAt(0) != '#' && line.charAt(0) != 'I') {

                        for (int x = 0 ; x < line.length() ; x++) {
                            if(!Character.isWhitespace(line.charAt(x))){
                                face[y][x] = line.charAt(x);
                                System.out.print(face[y][x]);

                                if(!Character.isWhitespace(line.charAt(x+1))){
                                    x++;
                                    face[y][x] = line.charAt(x);
                                    System.out.print(face[y][x]);
                                }
                            }
                        }
                        System.out.println("");
                    }
                }

                if(y >= 19){
                    testFaces.add(face);
                    System.out.println("\n");
                    y = 0;
                } else {
                    y++;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        /*
         * Read facit.
         */

        /*
        br = null;

        try {

            br = new BufferedReader(new FileReader(trainingFacit));

            String line;

            while ((line = br.readLine()) != null) {
                if(line.charAt(0) == 'I'){
                    char[][] face = new char[20][20];

                    for(int y = 0 ; y < 20 ; y++){
                        for(int x = 0 ; x < 20 ; x++){
                            face[y][x] = line.charAt(x);
                        }
                    }
                    testFaces.add(face);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        */

        /*
         * Create network.
         */
        NeuralNetwork network = new NeuralNetwork(20, 20,4);

        network.calculateNetValues();
    }

    public void readTrainingFile(String trainingFile){

    }
}
