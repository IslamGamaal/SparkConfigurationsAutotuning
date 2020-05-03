package configurations.utilites;

import java.util.ArrayList;
import java.util.List;

public class utils {

    /**
     * Note: It's assumed that that the configurations and the list of values are in correct order
     *
     * takes a list of configurations and adjust float values according to their type
     * if the config type is boolean we set value to 1.0 for True and 0.0 for False
     *
     * @param newValues
     * @return
     */
    public static List<Configuration> getConfigurationsList( List<Float> newValues) {
        List<Configuration> configs = new ArrayList<>();

//        if(configs.size() != newValues.size()) {
//            throw new RuntimeException("configuration and new values lists not in the same size");
//        }
        //TODO this function should be generalized and just take the values and automatically transfer them to list of configurations and do all the needed mapping

//        for (int i = 0; i < configs.size(); i++) {
//            if(configs.get(i).isBoolean()) {
//                if(newValues.get(i) > 0.5) {
//                    configs.get(i).setValue(1.0f);
//                } else {
//                    configs.get(i).setValue(0.0f);
//                }
//            } else if(configs.get(i).isInteger()) {
//                configs.get(i).setValue(Math.round(newValues.get(i)));
//            } else {
//                configs.get(i).setValue(newValues.get(i));
//            }
//        }
//
        return configs;

    }

}
