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

    private FaceMood getImageMood(String input[][]){

        for(int i = 0 ; i < perceptrons.length ; i++) {

            netArray[i] = perceptrons[i].output(input);

            if(netArray[i] == 1){
                return perceptrons[i].getFaceMood();
            }
        }

        int b = 0;

        for(int i = 1; i < 4; i++){

            if(netArray[b] < netArray[i]){

                b = i;
            }
        }
        //calculateError(activationArray, desiredOutput);
            //if(output[i] == 1){return output[i]}
        return FaceMood.fromInteger(b);
    }

    public void trainPerceptrons(LinkedList<String[][]> faceImages, FaceMood[]
            facit, int trainingSampleSize){

        FaceMood answer;

        for(int i = 0 ; i < trainingSampleSize ; i++){

            answer = getImageMood(faceImages.get(i));

            //if(!answer.equals(facit[i])){
                for(int n = 0 ; n < perceptrons.length ; n++){
                    perceptrons[n].setWeights(answer.getValue(), facit[i]);
                }
            //}
        }
    }

    public int testPerceptrons(LinkedList<String[][]> faceImages, FaceMood[] facit,
                               int testingSampleSize){
        FaceMood answer;
        int correctAnswers = 0;

        //Testing
        System.out.println("Testing!");
        for(int i = faceImages.size() - testingSampleSize ;
            i < faceImages.size() ; i++){

            answer = getImageMood((String[][]) faceImages.toArray()[i]);

            if(answer.equals(facit[i])){
                System.out.println("Correct!");
                correctAnswers++;
            }else {
                System.out.println("Wrong!");
            }

            System.out.println("Image: " + i + ".\nAnswer: "
                    + answer + ". " + "Facit: " + facit[i] + "\n");
        }

        return correctAnswers;
    }
}
