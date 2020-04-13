package configurations.picker;

import java.util.List;

public interface BestConfigurationPicker {

    /**
     *
     * @param configurations List<List<String>> : Containing configurations samples.
     * @return List<String> : Containing best configurations.
     */
    List<String> pickBestConfigurationsForApplication(List<List<String>> configurations);
}
