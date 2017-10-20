import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Faces {

    private ArrayList<String[][]> faces;
    private ArrayList<FaceMood> facit;
    private String trainingFile, trainingFacit, testFile;
    private NeuralNetwork network;

    private Faces(String trainingFile, String trainingFacit, String testFile){

        this.trainingFile = trainingFile;
        this.trainingFacit = trainingFacit;
        this.testFile = testFile;

        this.network = new NeuralNetwork(20, 20);
    }

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
        faces.network.testPerceptrons(faces.faces, faces.facit);
    }
}
