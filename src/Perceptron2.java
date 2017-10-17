import java.util.Random;

public class Perceptron2 {

    private FaceMood faceMood;
    private double activationThreshold, trainRate, error, learningRate;
    private double[][] weights;

    public Perceptron2(FaceMood faceMood, int ySize, int xSize){

        this.activationThreshold = 0.5;

        this.trainRate = 0.5;

        this.faceMood = faceMood;

        weights = new double[ySize][xSize];

        for(int y = 0 ; y < ySize ; y++){
            for(int x = 0 ; x < xSize ; x++){
                weights[y][x] = new Random().nextDouble();
            }
        }
    }

    public FaceMood getFaceMood(){
        return faceMood;
    }

    public double output(String input[][]){

        double sum = 0;

        for(int y = 0 ; y < weights[0].length ; y++){
            for(int x = 0 ; x < weights[0].length ; x++){
                sum += weights[y][x] * Integer.parseInt(input[y][x]);
            }
        }

        return sum;
    }

    public void setWeights(){
        //calculateError()
    }

    double calculateError(double activationValue, int desiredOutput, int input){

        error = activationValue - desiredOutput;

        return learningRate * error * input;
    }

    /*
    public void backPropagationTraining(FaceMood faceMood){

        if(this.faceMood != faceMood){

            switch(this.faceMood){

                case SAD:
                    sadFace += trainRate;
                    happyFace -= trainRate;
                    mFace -= trainRate;
                    angryFace -= trainRate;
                    break;

                case HAPPY:
                    sadFace -= trainRate;
                    happyFace += trainRate;
                    mFace -= trainRate;
                    angryFace -= trainRate;
                    break;

                case MISCHIEVOUS:
                    sadFace -= trainRate;
                    happyFace -= trainRate;
                    mFace += trainRate;
                    angryFace -= trainRate;
                    break;

                case ANGRY:
                    sadFace -= trainRate;
                    happyFace -= trainRate;
                    mFace -= trainRate;
                    angryFace += trainRate;
                    break;
            }
        }
    }
    */
}
