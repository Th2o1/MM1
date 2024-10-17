import java.util.Random;

public class Utils {

    static double duration(double lambda, double time) {
        double x = Math.random();
        if(x==0){
            x = 1e-10; // to not have log(0)
        }
        // Expression of the inter arrival following an exponential law of parameter lambda
        return time + (-Math.log(1-x) /lambda );
    }

}
