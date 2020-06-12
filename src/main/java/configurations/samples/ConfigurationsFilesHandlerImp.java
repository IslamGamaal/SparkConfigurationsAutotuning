package configurations.samples;

import configurations.utilites.Configuration;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ConfigurationsFilesHandlerImp implements ConfigurationsFilesHandler {
    public void writeConfigurationsSamplesInFiles(String configurationsFolderPath, List<List<Float>> samples) {
        //TODO this function need to just given one List<Configurations> write it in a file then return the path of the written file.
    }

    @Override
    public String writeConfigurationsInFile(List<Configuration> configurations, List<String> sparkhistory) {
        String configsFilePath = System.getProperty("user.dir") + "/resources/configurationsFiles/configs.conf";
        try {
            FileWriter myWriter = new FileWriter(configsFilePath);
            for (int i = 0; i < configurations.size(); i++) {
                myWriter.write(configurations.get(i).getName());
                if (configurations.get(i).isBoolean()){
                    if (configurations.get(i).getValue() == 1)
                        myWriter.write(" true");
                    else
                        myWriter.write(" false");
                }
                else{
                    myWriter.write(" " + configurations.get(i).getValue());
                }
                if (i<configurations.size() - 1)
                    myWriter.write("\n");
            }
            for (int i = 0; i < sparkhistory.size(); i++) {
                myWriter.write(sparkhistory.get(i));
                if (i < sparkhistory.size() - 1)
                    myWriter.write("\n");
            }
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return configsFilePath;
    }
}
