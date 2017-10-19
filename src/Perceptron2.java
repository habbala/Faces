import java.util.Random;

public class Perceptron2 {

    private final FaceMood faceMood;
    private double learningRate;
    private double[][] weights, greyLevels;

    public Perceptron2(FaceMood faceMood, int ySize, int xSize){

        this.learningRate = 0.01;

        this.faceMood = faceMood;

        weights = new double[ySize][xSize];
        greyLevels = new double[ySize][xSize];

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


                greyLevels[y][x] = (Double.parseDouble(image[y][x]) /32);

                sum += weights[y][x] * greyLevels[y][x];
            }
        }
        //System.out.println("sum"+sum);
        //System.out.println("Sum is: " + sum);
        //System.out.println("ActivationValue is: " + ActivationFunction
        //        .Sigmoid(sum));
        sum = ActivationFunction.Sigmoid(sum);

        return sum;
    }

    public void setWeights(double activationValue, int desiredOutput){
        //System.out.println();
        //System.out.println("actiovationvalue: " + activationValue);
        //System.out.println("desiredoutput:  " + desiredOutput);
        double newWeight = 0;

        for(int y = 0 ; y < weights[0].length ; y++){
            for(int x = 0 ; x < weights[0].length ; x++) {

                try {

                    newWeight = calculateError(activationValue,
                            desiredOutput, greyLevels[y][x]);

                } catch (NumberFormatException e) {

                    e.printStackTrace();
                }

                /*if(weights[y][x] < 0){

                    weights[y][x] = 0;
                }
                else if(weights[y][x] > 1){

                    weights[y][x] = 1;
                }*/

                weights[y][x] += newWeight;
            }
        }
    }

    private double calculateError(double activationValue, int desiredOutput,
                                double
            input){

        double error = desiredOutput - activationValue;

        return learningRate * error * input;
    }
}
