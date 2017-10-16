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

        facit = new LinkedList<>();

        BufferedReader br = null;

        try {

            br = new BufferedReader(new FileReader(trainingFile));

            String line;

            String[][] faceImage = new String[20][20];

            int x = 0;
            int y = 0;

            while ((line = br.readLine()) != null) {

                if(!line.isEmpty()) {

                    if (line.charAt(0) == 'I') {

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

        for(int i = 0 ; i < faces.trainingFaces.size() ; i++){

            answer = network.readInput(faces.trainingFaces.pollFirst(), faces
                    .facit[i]);

            System.out.println(answer);

            //network.calculateError(answer, faces.facit.pollFirst());
        }


        //network.calculateNetValues();
    }
}
