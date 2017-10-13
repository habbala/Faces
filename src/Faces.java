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
    private LinkedList<String[][]> trainingFaces;
    private String trainingFile, trainingFacit, testFile;


    public Faces(String trainingFile, String trainingFacit){

        this.trainingFile = trainingFile;
        this.trainingFacit = trainingFacit;
        //this.testFile = testFile;

        //readTestFile(testFile);
    }

    public void readTrainingFile(){

        trainingFaces = new LinkedList<>();

        BufferedReader br = null;

        try {

            br = new BufferedReader(new FileReader(trainingFile));

            String line;

            String[][] faceImage = new String[20][20];

            int z = 0;
            int y = 0;
            int x;

            while ((line = br.readLine()) != null) {

                if(!line.isEmpty()) {

                    if (line.charAt(0) != '#' && line.charAt(0) != 'I') {

                        for (x = 0 ; x < line.length() ; x++) {

                            if(!Character.isWhitespace(line.charAt(x))){

                                try{

                                    if((x+1)!= line.length() && !Character
                                            .isWhitespace(line.charAt(x+1))){

                                        faceImage[y][z] = (line.charAt(x)+""
                                                + line.charAt(x+1));
                                        x++;
                                        z++;
                                    }
                                    else{

                                        faceImage[y][z] = ""+line.charAt(x);
                                        z++;
                                    }

                                }catch(StringIndexOutOfBoundsException e){

                                    e.printStackTrace();
                                }

                                if(y >= 19){

                                    trainingFaces.add(faceImage);
                                    y = 0;
                                    z = 0;

                                } else {

                                    y++;
                                }
                            }
                        }
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

    public void readTrainingFacit(){


    }
    public void readTestFile(){


    }

    public static void main(String[] args) {

        //String trainingFile = args[0];
        //String trainingFacit = args[1];
        //String testFile = args[2];

        Faces faces = new Faces("training.txt", "training-Facit.txt");

        faces.readTrainingFile();
        faces.readTrainingFacit();
        //faces.readTestFile();

        /*
         * Read faces from file.
         */

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
        /*NeuralNetwork network = new NeuralNetwork(20, 20,4);

        network.calculateNetValues();*/
    }
}
