import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
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
    private FaceMood[] facit;
    private String trainingFile, trainingFacit, testFile;
    private NeuralNetwork network;

    private Faces(String trainingFile, String trainingFacit){

        this.trainingFile = trainingFile;
        this.trainingFacit = trainingFacit;
        //this.testFile = testFile;

        this.network = new NeuralNetwork(20, 20);

        //readTestFile(testFile);
    }

    private void readTrainingFile(){

        trainingFaces = new LinkedList<>();

        BufferedReader br = null;

        try {

            br = new BufferedReader(new FileReader(trainingFile));

            String line;

            String[][] faceImage = new String[20][20];

            int x = 0;
            int y = 0;

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
                            trainingFaces.add(faceImage);
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

    private void readTrainingFacit(){

        facit = new FaceMood[trainingFaces.size()];

        BufferedReader br = null;

        try {

            br = new BufferedReader(new FileReader(trainingFacit));

            String line;

            int i = 0;

            while ((line = br.readLine()) != null) {

                if(!line.isEmpty()) {
                    if (line.charAt(0) == 'I'
                            && Character.isDigit(line.charAt(
                                line.length()-1))){

                        facit[i] = FaceMood.fromInteger(Character
                                .getNumericValue(line.charAt(line.length()-1))
                                - 1);

                        i++;
                    }
                }
            }

            if(i != trainingFaces.size()){
                System.out.println("Mismatch! Images: " + trainingFaces.size()
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
    public void readTestFile(){


    }
    public void trainNetwork(int trainingSampleSize){

        //Training
        System.out.println("Training!");
        network.trainNodes(trainingFaces, facit);
    }

    public void testNetwork(int testingSampleSize){

        int correctAnswers = network.testNodes(trainingFaces, facit,
                testingSampleSize);

        System.out.println("Correct answers: " + correctAnswers + ", " +
                ((double)correctAnswers/(testingSampleSize) * 100 + "%"));
    }

    public static void main(String[] args) {

        //String trainingFile = args[0];
        //String trainingFacit = args[1];
        //String testFile = args[2];

        Faces faces = new Faces("training.txt", "training-Facit.txt");

        faces.readTrainingFile();
        faces.readTrainingFacit();
        //faces.readTestFile();

        int trainingSampleSize = 2 * faces.trainingFaces.size() / 3;

        faces.trainNetwork(trainingSampleSize);

        for(int i = 0; i < 300; i++){

            Collections.shuffle(faces.trainingFaces);
            faces.trainNetwork(trainingSampleSize);
        }

        int testingSampleSize = faces.trainingFaces.size()/3;

        faces.testNetwork(testingSampleSize);

        //faces.testNetwork(faces.trainingFaces.size() - trainingSampleSize);

    }
}
