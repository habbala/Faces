public class NeuralNetwork {

    private Perceptron[][] perceptrons;
    private double moodArray;

    private int ySize, xSize, inputs, sadCount, happyCount, mischievousCount,
            angryCount, moodCount;

    public NeuralNetwork(int ySize, int xSize, int inputs) {

        this.ySize = ySize;
        this.xSize = xSize;
        this.inputs = inputs;
        this.sadCount = 0;
        this.happyCount = 0;
        this.mischievousCount = 0;
        this.angryCount = 0;
        this.moodCount = 0;

        this.perceptrons = new Perceptron[ySize][xSize];

        for(int y = 0 ; y < ySize ; y++) {
            for (int x = 0; x < xSize; x++) {
                perceptrons[y][x] = new Perceptron(inputs);
            }
        }
    }

    public FaceMood moodDetector(){

        for(int y = 0 ; y < ySize ; y++) {
            for (int x = 0; x < xSize; x++) {

                if(perceptrons[y][x].isActivated()){

                    switch(perceptrons[y][x].getFaceMood()) {

                        case SAD:
                            sadCount++;
                            break;

                        case HAPPY:
                            happyCount++;
                            break;

                        case MISCHIEVOUS:
                            mischievousCount++;
                            break;

                        case ANGRY:
                            angryCount++;
                            break;
                    }
                }
            }
        }

        for(int i = 0; i < 4 ; i++){

        }

        return FaceMood.SAD;
    }

    public void trainNetwork(){


    }
}
