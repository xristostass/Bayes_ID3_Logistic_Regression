//Marianthi Mindrinou 3150110
//Michalis Roussos 3150148
//Christos Tasiopoulos 3150170

import java.io.*;
import java.util.*;


public class main {
    static Hashtable<String, Integer> spam = new Hashtable<String, Integer>();
    static Hashtable<String, Integer> ham = new Hashtable<String, Integer>();


    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Press either 1 or 2 or 3 or 4. \n1 is for ID3,2 is for Bayes ,3 is for Logistic Regression and 4 is for exit.");
        int n=input.nextInt();
        //the correct path to the dataset must be set.


        while(true){
            //each time depending to the input we carry out executing either the ID3 method or the Bayes method
            if(n==1){

                loadFile("C:\\Users\\Chris Tasiopoulos\\Downloads\\lingspam_public\\lemm");
                StringTokenizer tok = new StringTokenizer(reader.loadFile("C:\\Users\\Chris Tasiopoulos\\Desktop\\Texniti_ergasia2\\output.txt"),",");
                Examples exs1= new Examples();
                Example ex1;
                String s1;
                Properties prop= new Properties(reader.loadFile("Properties.txt"));
                while(tok.hasMoreTokens()){
                    s1=tok.nextToken();
                    ex1=new Example(s1);
                    exs1.add(ex1);
                }
                ID3.ID3method(exs1,prop).print1();
            }else if(n==2){
                loadFile("C:\\Users\\Chris Tasiopoulos\\Downloads\\lingspam_public\\lemm");
                StringTokenizer tok = new StringTokenizer(reader.loadFile("C:\\Users\\Chris Tasiopoulos\\Desktop\\Texniti_ergasia2\\output.txt"),",");
                Examples exs1= new Examples();
                Example ex1;
                String s1;
                Properties prop= new Properties(reader.loadFile("Properties.txt"));
                while(tok.hasMoreTokens()){
                    s1=tok.nextToken();
                    ex1=new Example(s1);
                    exs1.add(ex1);
                }
                //the correct path to the dataset must be set.
                Example ex2=readExample("C:\\Users\\Chris Tasiopoulos\\Downloads\\lingspam_public\\lemm\\part1\\spmsga11.txt");
                System.out.println("Bayes Method returns " + Bayes.h_method(ex2,exs1));

            }else if(n==3){
                loadFile("C:\\Users\\Chris Tasiopoulos\\Downloads\\lingspam_public\\lemm");
                StringTokenizer tok = new StringTokenizer(reader.loadFile("C:\\Users\\Chris Tasiopoulos\\Desktop\\Texniti_ergasia2\\output.txt"),",");
                Examples exs1= new Examples();
                Example ex1;
                String s1;
                Properties prop= new Properties(reader.loadFile("Properties.txt"));
                while(tok.hasMoreTokens()){
                    s1=tok.nextToken();
                    ex1=new Example(s1);
                    exs1.add(ex1);
                }
                Example ex2=readExample("C:\\Users\\Chris Tasiopoulos\\Downloads\\lingspam_public\\lemm\\part1\\3-405msg1.txt");
                List<RegressionInstance> instances = LogisticRegression.read("dataset.txt");
                LogisticRegression logistic = new LogisticRegression(spam.size());
                logistic.train(instances);
                int[] x = new int[spam.size()];
                for(int i=0; i<ex2.numofatts(); i++){
                    x[i]=Integer.parseInt(ex2.getat(i));

                }
                System.out.println("Logistic Regression returns: "+ logistic.test(x));
            }else if(n==4){
                break;
            }else{
                System.err.println("This number does not match any function.Please try again.");
            }
            System.out.print("Press either 1 or 2 or 3 or 4. \n1 is for ID3,2 is for Bayes ,3 is for Logistic Regression and 4 is for exit.");
            n=input.nextInt();

        }


    }

    public static void loadFile(String data) {

        int counter = 0;
        BufferedReader reader = null;
        String line = " ";
        StringTokenizer tok;

        //try and catch block for successfully opening the file,reading each word and storing it in the hashtables we have created.
        try {
            File dir = new File(data);
            File dir1 = null;

            //in this for loops we are checking every file of every folder of the dataset
            for (File file : dir.listFiles()) {
                dir1 = new File(file.getPath());
                for (File file1 : dir1.listFiles()) {

                   //here we are creating the spam and ham hashtables respectively.
                    int temp=0;
                    if (file1.getAbsolutePath().substring((file1.getAbsolutePath().lastIndexOf('\\')) + 1).startsWith("s")) {//in this case we are checking a spam message
                        counter = 0;
                        reader = new BufferedReader(new FileReader(file1));
                        line = reader.readLine();
                        //System.out.println("else!!");
                        while (line != null) {
                            String word;
                            tok = new StringTokenizer(line);

                            while (tok.hasMoreTokens()) {
                                word = tok.nextToken().trim();
                                if (!ham.containsKey(word)) {
                                    ham.put(word, 0);
                                }

                                if (!spam.containsKey(word)) {
                                    spam.put(word, 0);

                                }
                                else {
                                    temp = spam.get(word) + 1;
                                    spam.replace(word, temp);
                                }
                            }
                            line = reader.readLine();
                            counter++;
                        }

                    } else {//in this case we are checking a message that is not spam
                        counter = 0;
                        reader = new BufferedReader(new FileReader(file1));
                        line = reader.readLine();
                        while (line != null) {
                            String word;
                            tok = new StringTokenizer(line);
                            while (tok.hasMoreTokens()) {
                                word = tok.nextToken().trim();
                                if (!spam.containsKey(word)) {
                                    spam.put(word, 0);
                                }
                                if (!ham.containsKey(word))
                                    ham.put(word, 0);
                                else {
                                    temp = ham.get(word) + 1;
                                    ham.replace(word, temp);
                                }
                            }
                            line = reader.readLine();
                            counter++;
                        }
                    }

                }

            }


        } catch (FileNotFoundException e) {
            System.err.println("Error opening file!");
        } catch (IOException e) {
            e.printStackTrace();
        }
        String key;
        List<String> remove_keys = new ArrayList<String>();
        for (Map.Entry<String, Integer> entry : spam.entrySet()) {
             key = entry.getKey();

             //here we check if a certain key appears in either in the ham or spam dictionary respectively.
             if(ham.get(key)<10 || spam.get(key)<10){
               remove_keys.add(key);
             }

        }
            //We disregard the words that have a low appearance rate.
             for(int i=0; i<remove_keys.size(); i++){
                    ham.remove(remove_keys.get(i));
                    spam.remove(remove_keys.get(i));
             }
        //in this try and catch block we create vectors that show whether we found a word from our dictionary or not.
        try {
            File output = new File("output.txt");
            BufferedWriter writer = new BufferedWriter(new FileWriter(output));
            File dir = new File(data);
            int[] vector = new int[spam.size() + 1];
            writer.flush();
            File dir1 = null;
            for (File file : dir.listFiles()) {
                dir1 = new File(file.getPath());
                for (File file1 : dir1.listFiles()) {
                    if (file1.getAbsolutePath().substring((file1.getAbsolutePath().lastIndexOf('\\')) + 1).startsWith("s")) {//in this case we are checking a spam message
                        System.out.println("Reading: "+ file1.getAbsolutePath());
                        counter = 0;
                        reader = new BufferedReader(new FileReader(file1));
                        line = reader.readLine();
                        //here we read every line in the mail and check whether each word is contained in the dataset or not.
                        while (line != null) {
                            String word;
                            tok = new StringTokenizer(line);
                            while (tok.hasMoreTokens()) {
                                word = tok.nextToken();
                                int i = 0;
                                for (Map.Entry<String, Integer> entry : spam.entrySet()) {
                                    key = entry.getKey();
                                    if (word.equals(key)) {
                                        vector[i] = 1;
                                    }
                                    i++;
                                }
                                vector[vector.length - 1] = 0; //spam message
                            }
                            line = reader.readLine();
                            counter++;
                        }

                    } else {//in this case we are checking a message that is not spam
                        System.out.println("Reading: "+ file1.getAbsolutePath());
                        counter = 0;
                        reader = new BufferedReader(new FileReader(file1));
                        line = reader.readLine();
                        //here we read every line in the mail and check whether each word is contained in the dataset or not.
                        while (line != null) {
                            String word;
                            tok = new StringTokenizer(line);
                            while (tok.hasMoreTokens()) {
                                word = tok.nextToken();
                                int i = 0;
                                for (Map.Entry<String, Integer> entry : spam.entrySet()) {
                                    key = entry.getKey();
                                    if (word.equals(key)) {
                                        vector[i] = 1;
                                    }
                                    i++;

                                }
                                vector[vector.length - 1] = 1;
                            }
                            line = reader.readLine();
                            counter++;
                        }
                    }

                    for (int i = 1; i < vector.length;i++){
                        writer.write(vector[i]+" ");
                        writer.flush();
                    }
                    writer.write(",");
                    writer.flush();
                }

            }
            writer.flush();
            writer.close();

            output = new File("Properties.txt"); // here we are creating the txt that contains the words we use
            writer = new BufferedWriter(new FileWriter(output));
            writer.flush();

            for (Map.Entry<String, Integer> entry : spam.entrySet()) {
                key = entry.getKey();
                writer.write( key + " ,");
                writer.flush();
            }

            writer.flush();
            writer.close();

        } catch (FileNotFoundException e) {
            System.err.println("Error opening file!");
        } catch (IOException e) {
            e.printStackTrace();
        }




    }

    public static Example readExample(String path){//this method  reads a mail and returns an Example object.

        BufferedReader reader = null;
        String line = " ";
        StringTokenizer tok;
        String example="";
        int[] vector = new int[spam.size() + 1];
        try {
            reader = new BufferedReader(new FileReader(new File(path)));
            line = reader.readLine();
            File file2=new File(path);
            boolean c =!(file2.getAbsolutePath().substring((file2.getAbsolutePath().lastIndexOf('\\')) + 1).startsWith("s"));
            while (line != null) {
                String word;
                tok = new StringTokenizer(line);
                while (tok.hasMoreTokens()) {
                    word = tok.nextToken();
                    int i = 0;
                    //System.out.println(word);
                    for (Map.Entry<String, Integer> entry : spam.entrySet()) {
                        String key = entry.getKey();
                        //int value = entry.getValue();
                        if (word.equals(key)) {
                            vector[i] = 1;
                        }
                        example.concat(" "+vector[i]);
                        i++;
                    }
                    if(c==true){
                        vector[vector.length - 1] = 1;
                    }else{
                        vector[vector.length - 1] = 0;
                    }


                }

                line = reader.readLine();


            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Example(example);
    }
}
