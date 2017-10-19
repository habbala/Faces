import java.util.ArrayList;

public class NeuralNetwork {

    private Perceptron[] perceptrons;
    private double[] netArray;

    private int[]  testAnswers;

    public NeuralNetwork(int ySize, int xSize) {

        testAnswers = new int[4];

        netArray = new double[4];

        perceptrons = new Perceptron[4];


        for (int i = 0; i < 4; i++) {

            perceptrons[i] = new Perceptron(FaceMood.fromInteger(i), ySize,
                    xSize);
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
                                 ArrayList<FaceMood> facit, int
                                         trainingSampleSize) {

        for (int i = 0; i < trainingSampleSize; i++) {

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

    public int testPerceptrons(ArrayList<String[][]> faceImages,
                               ArrayList<FaceMood> facit, int
                                       testingSampleSize) {

        FaceMood answer;
        int correctAnswers = 0;


        for (int i = 0;
             i < faceImages.size(); i++) {

            answer = getImageMood(faceImages.get(i));

            System.out.println("Image"+i+" "+(answer.getValue()+1));
            testAnswers[answer.getValue()]++;

            if (answer.equals(facit.get(i))) {

                correctAnswers++;
            }
        }
        return correctAnswers;
    }
}
