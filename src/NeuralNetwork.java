import java.util.LinkedList;

public class NeuralNetwork {

    private Perceptron2[] perceptrons;
    private double[] netArray;

    public NeuralNetwork(int ySize, int xSize) {

        netArray = new double[4];

        perceptrons = new Perceptron2[4];

        for(int i = 0 ; i < 4 ; i++){
            perceptrons[i] =
                    new Perceptron2(FaceMood.fromInteger(i), ySize, xSize);
        }

    }

    private FaceMood readInput(String input[][]){

        double[] activationArray = new double[perceptrons.length];

        for(int i = 0 ; i < perceptrons.length ; i++) {

            netArray[i] = perceptrons[i].output(input);
            perceptrons[i].setWeights();

        }

        int b = 0;

        for(int i = 1; i < 4; i++){

            if(activationArray[b] < activationArray[i]){

                b = i;
            }
        }
        //calculateError(activationArray, desiredOutput);
            //if(output[i] == 1){return output[i]}
        return FaceMood.fromInteger(b);
    }

    public void trainNodes(LinkedList<String[][]> faceImages, FaceMood[] facit){

        FaceMood[] answers = new FaceMood[perceptrons.length];

        for(int i = 0 ; i < faceImages.size() ; i++){
            answers[i] = readInput((String[][])faceImages.toArray()[i]);
        }


    }

    public int testNodes(LinkedList<String[][]> faceImages, FaceMood[] facit,
                         int testingSampleSize){
        FaceMood answer;
        int correctAnswers = 0;

        //Testing
        System.out.println("Testing!");
        for(int i = faceImages.size() - testingSampleSize ;
            i < faceImages.size() ; i++){

            answer = readInput((String[][]) faceImages.toArray()[i]);

            if(answer.equals(facit[i])){
                System.out.println("Correct!");
                correctAnswers++;
            }else {
                System.out.println("Wrong!");
            }

            System.out.println("Image: " + i + 1 + ".\nAnswer: "
                    + answer + ". " + "Facit: " + facit[i] + "\n");
        }

        return correctAnswers;
    }
}
