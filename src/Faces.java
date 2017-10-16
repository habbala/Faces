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
    private int[] facit;
    private String trainingFile, trainingFacit, testFile;


    private Faces(String trainingFile, String trainingFacit){

        this.trainingFile = trainingFile;
        this.trainingFacit = trainingFacit;
        //this.testFile = testFile;

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

        facit = new int[trainingFaces.size()];

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

                        facit[i] = Character.getNumericValue(line.charAt(line
                                .length()-1));

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

    public static void main(String[] args) {

        //String trainingFile = args[0];
        //String trainingFacit = args[1];
        //String testFile = args[2];

        Faces faces = new Faces("training.txt", "training-Facit.txt");

        faces.readTrainingFile();
        faces.readTrainingFacit();
        //faces.readTestFile();


        /*
         * Create network.
         */

        NeuralNetwork network = new NeuralNetwork(20, 20);
        FaceMood answer;

        int correctAnswers = 0;
        int i;

        for(i = 0 ; i < (2*faces.trainingFaces.size())/3; i++){

            network.readInput((String[][])faces.trainingFaces.toArray()[i],
                    faces.facit[i]);
        }

        while(i < faces.trainingFaces.size()){

            answer = network.readInput((String[][])
                    faces.trainingFaces.toArray()[i], faces.facit[i]);

            if(answer.equals(FaceMood.values()[faces.facit[i]-1])){
                System.out.println("Correct!");
                correctAnswers++;
            }else {
                System.out.println("Wrong!");
            }

            System.out.println("Image: " + i + ".\nAnswer: " + answer + ". " +
                    "Facit: " +
                    FaceMood.values()[faces.facit[i]-1] + "\n");
            i++;
        }

        System.out.println("Correct answers: " + correctAnswers + ", " +
                ((double)correctAnswers/faces.trainingFaces.size()) * 100 +
                "%");


        //network.calculateNetValues();
    }
}
