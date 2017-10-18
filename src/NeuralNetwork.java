import java.util.ArrayList;
import java.util.LinkedList;

public class NeuralNetwork {

    private Perceptron2[] perceptrons;
    private double[] netArray;

    private int[] trainingAnswers, testAnswers;

    public NeuralNetwork(int ySize, int xSize) {

        trainingAnswers = new int[4];
        testAnswers = new int[4];

        netArray = new double[4];

        perceptrons = new Perceptron2[4];


        for (int i = 0; i < 4; i++) {
            perceptrons[i] =
                    new Perceptron2(FaceMood.fromInteger(i), ySize, xSize);
        }

    }

    private FaceMood getImageMood(String input[][]) {

        for (int i = 0; i < perceptrons.length; i++) {

            //System.out.println("input: " + input);
            netArray[i] = perceptrons[i].output(input);

        }


        int b = 0;

        for (int i = 1; i < 4; i++) {

            if (netArray[b] < netArray[i]) {

                b = i;
            }
        }

        //trainingAnswers[b]++;
        return FaceMood.fromInteger(b);
    }

    private FaceMood getImageMood2(String input[][]) {

        for (int i = 0; i < perceptrons.length; i++) {
            netArray[i] = 0;
            netArray[i] = perceptrons[i].output(input);
        }

        int b = 0;

        for (int i = 1; i < 4; i++) {

            if (netArray[b] < netArray[i]) {

                b = i;
            }
        }

        //trainingAnswers[b]++;
        return FaceMood.fromInteger(b);
    }

    public void trainPerceptrons(ArrayList<String[][]> images,
                                 ArrayList<FaceMood> facit, int
                                         trainingSampleSize) {

        FaceMood answer;

        for (int i = 0; i < trainingSampleSize; i++) {

            answer = getImageMood(images.get(i));

            //System.out.println(answer);

            for (int n = 0; n < perceptrons.length; n++) {

                if(n == facit.get(i).getValue()){
                    perceptrons[n].setWeights(netArray[answer.getValue()],
                            1);
                }


                /*if(perceptrons[n].getFaceMood().equals(facit.get(i))){

                    perceptrons[n].setWeights(netArray[answer.getValue()],
                            1);
                }*/
                else {
                    perceptrons[n].setWeights(netArray[answer.getValue()],0);
                }
            }
        }
    }

    public int testPerceptrons(ArrayList<String[][]> faceImages,
                               ArrayList<FaceMood> facit, int
                                       testingSampleSize) {

        FaceMood answer;
        int correctAnswers = 0;

        //Testing
        //System.out.println("Testing!");
        for (int i = faceImages.size() - testingSampleSize;
             i < faceImages.size(); i++) {

            answer = getImageMood2(faceImages.get(i));

            testAnswers[answer.getValue()]++;

            if (answer.equals(facit.get(i))) {
                //System.out.println("Correct!");
                correctAnswers++;
            } else {
                //System.out.println("Wrong!");
            }

            /*
            System.out.println("Image: " + i + ".\nAnswer: "
                    + answer + ". " + "Facit: " + facit.get(i) + "\n");
        }*/
        }
        return correctAnswers;
    }

    public int[] getTotalAnswers(){

        return testAnswers;
    }
}
