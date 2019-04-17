package kotlintraining.lesson5;

import javax.inject.Named;

@Named
public class IntegerService {

    public int getInteger(int id) {
        return id * id;
    }
}
