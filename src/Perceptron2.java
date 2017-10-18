import java.util.Random;

public class Perceptron2 {

    private final FaceMood faceMood;
    private double error, learningRate;
    private double[][] weights;
    private String[][] greyLevels;

    public Perceptron2(FaceMood faceMood, int ySize, int xSize){

        this.learningRate = 0.01;

        this.faceMood = faceMood;

        weights = new double[ySize][xSize];
        greyLevels = new String[ySize][xSize];

        greyLevels[0][0] = "0";

        for(int y = 0 ; y < ySize ; y++){
            for(int x = 0 ; x < xSize ; x++){
                weights[y][x] = new Random().nextDouble();
            }
        }
    }

    public FaceMood getFaceMood(){

        return faceMood;
    }

    public double output(String image[][]){

        double sum = 0;

        for(int y = 0 ; y < weights[0].length ; y++){
            for(int x = 0 ; x < weights[0].length ; x++){


                greyLevels[y][x] = image[y][x];

                sum += weights[y][x] * (Double.parseDouble(greyLevels[y][x])
                        /32);
            }
        }
        //System.out.println("sum"+sum);

        return ActivationFunction.Sigmoid(sum);
    }

    public void setWeights(double activationValue, int desiredOutput){
        //System.out.println();
        //System.out.println("actiovationvalue: " + activationValue);
        //System.out.println("desiredoutput:  " + desiredOutput);
        double newWeight = 0;

        for(int y = 0 ; y < weights[0].length ; y++){
            for(int x = 0 ; x < weights[0].length ; x++) {

                try {

                    newWeight = calculateError(activationValue, desiredOutput,
                            Double.parseDouble(greyLevels[y][x])/32);

                } catch (NumberFormatException e) {

                    e.printStackTrace();
                }

                weights[y][x] += newWeight;
            }
        }
    }

    double calculateError(double activationValue, int desiredOutput, double
            input){

        error = desiredOutput - activationValue;
        //System.out.println("error;  " + error);
        double i = learningRate * error * input;

        return i;
    }
}
