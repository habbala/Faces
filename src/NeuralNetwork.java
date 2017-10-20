import java.util.ArrayList;

/**
 * This class creates a neural network with 4 perceptrons and takes the input
 * as a 2 dimensional string array which contain the grey levels and uses that
 * to give the input layer its values.
 */
public class NeuralNetwork {

    private Perceptron[] perceptrons;
    private double[] netArray;

    private int[]  testAnswers;

    /**
     *
     * @param ySize
     * @param xSize
     */
    public NeuralNetwork(int xSize, int ySize) {

        testAnswers = new int[4];

        netArray = new double[4];

        perceptrons = new Perceptron[4];


        for (int i = 0; i < 4; i++) {

            perceptrons[i] = new Perceptron(xSize,
                    ySize);
        }

    }

    private FaceMood getImageMood(String input[][]) {

        for (int i = 0; i < perceptrons.length; i++) {

            netArray[i] = perceptrons[i].output(input);

        }


        int b = 0;

        for (int i = 1; i < 4; i++) {

            if (netArray[b] < netArray[i]) {

                b = i;
            }
        }

        return FaceMood.fromInteger(b);
    }

    public void trainPerceptrons(ArrayList<String[][]> images,
                                 ArrayList<FaceMood> facit) {

        for (int i = 0; i < images.size(); i++) {

            getImageMood(images.get(i));

            for (int n = 0; n < perceptrons.length; n++) {

                if(n == facit.get(i).getValue()){

                    perceptrons[n].setWeights(netArray[n],
                            1);
                }

                else {

                    perceptrons[n].setWeights(netArray[n],0);
                }
            }
        }
    }

    public void testPerceptrons(ArrayList<String[][]> faceImages) {

        FaceMood answer;

        for (int i = 0; i < faceImages.size(); i++) {

            answer = getImageMood(faceImages.get(i));

            System.out.println("Image"+i+" "+(answer.getValue()+1));
            testAnswers[answer.getValue()]++;
        }
    }
}
