import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Program to train and test an artificial neural network to recognize moods
 * in faces in monochrome images.
 */
public class Faces {

    private ArrayList<String[][]> faces;
    private ArrayList<FaceMood> facit;
    private String trainingFile, trainingFacit, testFile;
    private NeuralNetwork network;

    /**
     * Constructor for the class that contains both the network and
     * necessary files.
     *
     * @param trainingFile Path to the file that contains images which the
     *                     network will use to train.
     *
     * @param trainingFacit Path to the file that contains correct answers
     *                      for the training images.
     *
     * @param testFile Path to the file that contains the image set that the
     *                 network will test itself against. Facit to these
     *                 images is not provided to the program, but will be
     *                 evaluated in a different program.
     */
    private Faces(String trainingFile, String trainingFacit, String testFile){

        this.trainingFile = trainingFile;
        this.trainingFacit = trainingFacit;
        this.testFile = testFile;
        this.network = new NeuralNetwork(20, 20);
    }

    /**
     * Tries to open and read a file containing face images. The images are
     * stored in an array list. Each image is stored as a String[][], with x
     * and y coordinates.
     * @param trainingFile Path to training file.
     */
    private void readFaces(String trainingFile){

        faces = new ArrayList<>();

        BufferedReader br = null;

        try {

            br = new BufferedReader(new FileReader(trainingFile));

            String line;

            int x = 0;
            int y = 0;

            String[][] faceImage = new String[20][20];

            while ((line = br.readLine()) != null) {

                if(!line.isEmpty()) {

                    if (line.charAt(0) != '#' && line.charAt(0) != 'I') {

                        for (int i = 0 ; i < line.length() ; i++) {

                            if(!Character.isWhitespace(line.charAt(i))){

                                try{

                                    if((i+1) < line.length() && !Character
                                            .isWhitespace(line.charAt(i+1))){

                                        faceImage[y][x] = (line.charAt(i)+""
                                                + line.charAt(i+1));

                                        i++;
                                        x++;
                                    }
                                    else{

                                        faceImage[y][x] = ""+line.charAt(i);
                                        x++;
                                    }

                                }catch(StringIndexOutOfBoundsException e){

                                    e.printStackTrace();
                                }
                            }
                        }

                        if(y >= 19){

                            faces.add(faceImage);

                            faceImage = new String[20][20];

                            y = 0;

                        } else {
                            y++;
                        }

                        x = 0;
                    }
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
    }

    /**
     * Tries to open and read a facit file. The correct answers are
     * stored in an array list. Each answer is stored as a FaceMood.
     *
     * @param facitFile Path to file containing the correct answers.
     */
    private void readFacit(String facitFile){

        facit = new ArrayList<>(faces.size());

        BufferedReader br = null;

        try {

            br = new BufferedReader(new FileReader(facitFile));

            String line;

            int i = 0;

            while ((line = br.readLine()) != null) {

                if(!line.isEmpty()) {
                    if (line.charAt(0) == 'I'
                            && Character.isDigit(line.charAt(
                                line.length()-1))){

                        facit.add(i, FaceMood.fromInteger(Character
                                .getNumericValue(line.charAt(line.length()-1))
                                - 1));

                        i++;
                    }
                }
            }

            if(i != faces.size()){

                System.out.println("Mismatch! Images: " + faces.size()
                + ". Facits: " + i);
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
    }

    /**
     * Main program that reads training file, training facit and a test file.
     * After the network has trained using the training file and facit, the
     * images are shuffled and trained with 300 additional times.
     *
     * After the network has been trained with the training file and facit,
     * it is tested with the test file. The test does not produce a log file,
     * instead the answers are printed to the terminal.
     *
     * @param args Training file, training facit, test file
     */
    public static void main(String[] args) {

        Faces faces = new Faces(args[0], args[1], args[2]);

        faces.readFaces(faces.trainingFile);
        faces.readFacit(faces.trainingFacit);

        long seed;

        for(int i = 0; i < 300; i++){

            faces.network.trainPerceptrons(faces.faces, faces.facit);;
            seed = System.nanoTime();
            Collections.shuffle(faces.faces, new Random(seed));
            Collections.shuffle(faces.facit, new Random(seed));
        }

        faces.readFaces(faces.testFile);
        faces.network.testPerceptrons(faces.faces);
    }
}
