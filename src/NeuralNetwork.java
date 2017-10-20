import java.util.ArrayList;

/**
 * This class creates a neural network with 4 perceptrons and takes the input
 * as a 2 dimensional string array which contain the grey levels and uses that
 * to give the input layer its values.
 */
public class NeuralNetwork {

    private Perceptron[] perceptrons;
    private double[] netValues;

    /**
     * The constructor creates 2 kinds of arrays. The "netValues" which will
     * store the activatiion-values from each perceptron, and then
     * "perceptrons" which stores each perceptron.
     *
     * @param xSize the x-axis-size of the 2 dimensional array created in the
     *              Perceptron-constructor.
     * @param ySize the y-axis-size of the 2 dimensional array created in the
     *              Perceptron-constructor.
     */
    public NeuralNetwork(int xSize, int ySize) {

        netValues = new double[4];

        perceptrons = new Perceptron[4];


        for (int i = 0; i < 4; i++) {

            perceptrons[i] = new Perceptron(xSize,
                    ySize);
        }

    }

    /**
     * This method takes the image and sends it to the output()-method in the
     * class Perceptron and gets back the activation-value which has been run
     * through the Sigmoid-function and stores that in a array as big as
     * there are perceptrons. Then it gets which one of these values is the
     * biggest and this array "netValues" is synced in the same order as the
     * enum FaceMood. So to get the corresponding FaceMood to the biggest
     * activation-value it simply has to return the FaceMood with the same
     * value as the index on which the value was in the array netValues.
     *
     * @param input the current image to analyze, i.e. all the grey levels in
     *              a String-array.
     * @return the guess of FaceMood that the program makes from the current
     * image.
     */
    private FaceMood getImageMood(String input[][]) {

        for (int i = 0; i < perceptrons.length; i++) {

            netValues[i] = perceptrons[i].output(input);

        }


        int b = 0;

        for (int i = 1; i < 4; i++) {

            if (netValues[b] < netValues[i]) {

                b = i;
            }
        }

        return FaceMood.fromInteger(b);
    }

    /**
     * Trains the program by getting the FaceMood the program guesses on in
     * each particular image and comparing it to the facit file. It then uses
     * the method setWeights() from the class Perceptron to change the
     * weights of each perceptron. It sends the correct answer with the
     * method so it may be used to calculate the error if there was any. That
     * value will be 1 for the perceptron that is right and 0 for the wrong
     * ones and important to notice is that it only changes each specific
     * perceptron by itself, i.e. it does not change anything on the other
     * perceptrons when changing the weights on a perceptron.
     *
     * @param images all the training images stored as String-arrays in an
     *               ArrayList.
     * @param facit all the correct values translated to FaceMood enum values
     *             stored in an ArrayList.
     */
    public void trainPerceptrons(ArrayList<String[][]> images,
                                 ArrayList<FaceMood> facit) {

        for (int i = 0; i < images.size(); i++) {

            getImageMood(images.get(i));

            for (int n = 0; n < perceptrons.length; n++) {

                if(n == facit.get(i).getValue()){

                    perceptrons[n].setWeights(netValues[n],
                            1);
                }

                else {

                    perceptrons[n].setWeights(netValues[n],0);
                }
            }
        }
    }

    /**
     * Tests the perceptrons accuracy by doing the exact same thing as the
     * method trainPerceptrons() except for setting any new weights. It then
     * prints "Image", which number of image it is and what value the program
     * guessed on.
     *
     * @param images all the test images stored as String-arrays in an
     *               ArrayList.
     */
    public void testPerceptrons(ArrayList<String[][]> images) {

        FaceMood answer;

        for (int i = 0; i < images.size(); i++) {

            answer = getImageMood(images.get(i));

            System.out.println("Image"+i+" "+(answer.getValue()+1));
        }
    }
}
