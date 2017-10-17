import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

public class Faces {

    //training-file.txt
    // training-facit.txt
    // test-file.txt
    private ArrayList<String[][]> trainingFaces;
    private ArrayList facit;
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

        trainingFaces = new ArrayList<>();

        BufferedReader br = null;

        try {

            br = new BufferedReader(new FileReader(trainingFile));

            String line;

            int x = 0;
            int y = 0;

            while ((line = br.readLine()) != null) {

                if(!line.isEmpty()) {

                    if (line.charAt(0) != '#' && line.charAt(0) != 'I') {

                        String[][] faceImage = new String[20][20];

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

        facit = new ArrayList<FaceMood>(trainingFaces.size());

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

                        facit.add(i, FaceMood.fromInteger(Character
                                .getNumericValue(line.charAt(line.length()-1))
                                - 1));

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

        network.trainPerceptrons(trainingFaces, facit, trainingSampleSize);
    }

    public void testNetwork(int testingSampleSize){

        int correctAnswers = network.testPerceptrons(trainingFaces, facit,
                testingSampleSize);

        System.out.println("Correct answers: " + correctAnswers + ", " +
                ((double)correctAnswers/(testingSampleSize) * 100 + "%"));
    }

    public static void main(String[] args) {

        //String trainingFile = args[0];
        //String trainingFacit = args[1];
        //String testFile = args[2];

        Faces faces = new Faces("training.txt", "training-facit.txt");

        faces.readTrainingFile();
        faces.readTrainingFacit();

        long seed;
        //faces.readTestFile();

        int trainingSampleSize = 2 * faces.trainingFaces.size() / 3;

        faces.trainNetwork(trainingSampleSize);

        for(int i = 0; i < 300; i++){

            seed = System.nanoTime();
            Collections.shuffle(faces.trainingFaces, new Random(seed));
            Collections.shuffle(faces.facit, new Random(seed));
            faces.trainNetwork(trainingSampleSize);
        }

        int testingSampleSize = faces.trainingFaces.size() - trainingSampleSize;

        faces.testNetwork(testingSampleSize);

        for(int i = 0 ; i < 4 ; i++){

            System.out.println(FaceMood.fromInteger(i) + " : " +
                    faces.network.getTotalAnswers()[i]);
        }

    }
}
