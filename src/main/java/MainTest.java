import spark.utilites.SparkApplication;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MainTest {
    public static void main(String[] args) {
        SystemTrainer systemTrainer = new SystemTrainerImpl();
        List<SparkApplication> list = new ArrayList<>();
        ArrayList<String> params = new ArrayList<>();


        System.out.println("testtttttttttt");
        try {
            File file = new File(System.getProperty("user.dir")+ "/resources/applicationsparams");

            BufferedReader br = new BufferedReader(new FileReader(file));

            String st;
            while ((st = br.readLine()) != null) {
                    if (st.contains("SPLIT")){
                        String[] splitted = st.split("SPLIT");
                        params.add(splitted[0]);
                        params.add(splitted[1]);
                    }
                    else
                        params.add(st);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 1; i <= 3; i++) {
            SparkApplication sparkApplication = new SparkApplication();
            sparkApplication.setMainClassName("Main");
            sparkApplication.setJarPathWithParamsCommand(params.get(0) +  i +  params.get(1));
            sparkApplication.setName("tpch-" + i );
            list.add(sparkApplication);
        }

//        for (int i = 1; i <= 3; i++) {
//            if( i!= 18 && i!= 28 && i!= 49 && i!= 61 && i!= 67 && i!= 77 && i!= 88 && i!= 90) {
//                SparkApplication sparkApplication = new SparkApplication();
//                sparkApplication.setMainClassName("Main");
//                sparkApplication.setJarPathWithParamsCommand(params.get(2) + i + params.get(3));
//                sparkApplication.setName("tpcds-" + i);
//                list.add(sparkApplication);
//            }
//        }
//
//        SparkApplication sparkApplication = new SparkApplication();
//        sparkApplication.setMainClassName("PageRank");
//        sparkApplication.setJarPathWithParamsCommand(params.get(4));
//        sparkApplication.setName("PageRank");
//        list.add(sparkApplication);
//
//        SparkApplication sparkApplication1 = new SparkApplication();
//        sparkApplication.setMainClassName("ConnectedComponents");
//        sparkApplication.setJarPathWithParamsCommand(params.get(5));
//        sparkApplication.setName("cc");
//        list.add(sparkApplication1);

        systemTrainer.trainSystem(list , "runapplication");



    }
    
}

