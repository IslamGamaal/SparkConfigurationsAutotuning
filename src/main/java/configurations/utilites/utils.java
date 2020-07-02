package configurations.utilites;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class utils {

    /**
     * Note: It's assumed that that the configurations and the list of values are in correct order
     *
     * takes a list of configurations and adjust float values according to their type
     * if the config type is boolean we set value to 1.0 for True and 0.0 for False
     *
     * @param Values
     * @param confsSpecs
     * @return
     */
    public static List<Configuration> getConfigurationsList(List<Float> Values, Map<String, Object> confsSpecs) {
        List<Configuration> configs = new ArrayList<>();
        prepareConfigs(configs , confsSpecs);
        if(configs.size() != Values.size()) {
            return null;
        }

        for (int i = 0; i < configs.size(); i++) {
            if(configs.get(i).isBoolean()) {
                if(Values.get(i) > 0.5) {
                    configs.get(i).setValue(1.0f);
                } else {
                    configs.get(i).setValue(0.0f);
                }
            } else if(configs.get(i).isInteger()) {
                configs.get(i).setValue(Math.round(Values.get(i)));
            } else {
                configs.get(i).setValue(Values.get(i));
            }
        }

        return configs;

    }

    private static void prepareConfigs(List<Configuration> configs, Map<String, Object> confsSpecs) {
        List<String> configurations = (List<String>)confsSpecs.get("configurations");
        List<String> confUnit = (List<String>)confsSpecs.get("confunit");
        List<String> isboolean = (List<String>)confsSpecs.get("isboolean");
        List<String> isint = (List<String>)confsSpecs.get("isint");

        for (int i = 0; i < configurations.size(); i++) {
            Configuration newConfiguration = new Configuration();
            newConfiguration.setName(configurations.get(i));
            newConfiguration.setUnit(confUnit.get(i));
            if (isboolean.get(i).equalsIgnoreCase("t")){
                newConfiguration.setBoolean(true);
            }
            else
                newConfiguration.setBoolean(false);
            if (isint.get(i).equalsIgnoreCase("t")){
                newConfiguration.setInteger(true);
            }
            else
                newConfiguration.setInteger(false);
            configs.add(newConfiguration);
        }
    }
    public static void loadSpecs(Map<String, Object> confsSpecs) {
        //System.out.println(System.getProperty("user.dir")+ "/resources/confsSpecsFile");
        File file = new File(System.getProperty("user.dir")+ "/resources/confsSpecsFile");

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null){
                String[] lineSplitted = line.split(" ");
                if (lineSplitted[0].equalsIgnoreCase("ub")){
                    List<Float> ub = new ArrayList<>();
                    for (int i = 1; i < lineSplitted.length; i++) {
                        ub.add(Float.parseFloat(lineSplitted[i]));
                    }
                    confsSpecs.put("ub" , ub);

                }
                else if(lineSplitted[0].equalsIgnoreCase("lb")){
                    List<Float> lb = new ArrayList<>();
                    for (int i = 1; i < lineSplitted.length; i++) {
                        lb.add(Float.parseFloat(lineSplitted[i]));
                    }
                    confsSpecs.put("lb" , lb);
                }
                else if(lineSplitted[0].equalsIgnoreCase("configurations")){
                    List<String> configurations = new ArrayList<>();
                    for (int i = 1; i < lineSplitted.length; i++) {
                        configurations.add(lineSplitted[i]);
                    }
                    confsSpecs.put("configurations" , configurations);

                }
                else if(lineSplitted[0].equalsIgnoreCase("confunit")){
                    List<String> confunit = new ArrayList<>();
                    for (int i = 1; i < lineSplitted.length; i++) {
                        confunit.add(lineSplitted[i]);
                    }
                    confsSpecs.put("confunit" , confunit);

                }
                else if(lineSplitted[0].equalsIgnoreCase("isboolean")){
                    List<String> isboolean = new ArrayList<>();
                    for (int i = 1; i < lineSplitted.length; i++) {
                        isboolean.add(lineSplitted[i]);
                    }
                    confsSpecs.put("isboolean" , isboolean);
                }
                else if(lineSplitted[0].equalsIgnoreCase("isint")){
                    List<String> isint = new ArrayList<>();
                    for (int i = 1; i < lineSplitted.length; i++) {
                        isint.add(lineSplitted[i]);
                    }
                    confsSpecs.put("isint" , isint);
                }
                else if(lineSplitted[0].equalsIgnoreCase("sparkhistory")){
                    List<String> sparkhistory = new ArrayList<>();
                    for (int i = 1; i < lineSplitted.length; i+=2) {
                        sparkhistory.add(lineSplitted[i] + " " + lineSplitted[i+1]);
                    }
                    confsSpecs.put("sparkhistory" , sparkhistory);
                }
                else if (lineSplitted[0].equalsIgnoreCase("sparkdirectory")){
                    if (lineSplitted.length > 1)
                        confsSpecs.put("sparkdirectory" , lineSplitted[1]);
                }
                else if (lineSplitted[0].equalsIgnoreCase("sparkmaster")){
                    if (lineSplitted.length > 1)
                        confsSpecs.put("sparkmaster" , lineSplitted[1]);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
