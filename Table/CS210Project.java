import java.io.File;
import java.util.*;
import java.security.*;
public class CS210Project{
    public static void main (String[] args) throws Exception{
        File file = new File("");
        List<String> strings = readWordFromFile(file);
        List<String> hashCodes = new ArrayList<>();
        List<String> sentences = new ArrayList<>();
        long startTime = System.nanoTime();
        for(int i = 0; i < 100; i++){
            for(int j = 1000; j < 1010; j++){
                for(int k = 2000; k < 2010; k++){
                    if(strings.get(j).endsWith("sh") || strings.get(j).endsWith("s") || strings.get(j).endsWith("x") || strings.get(j).endsWith("ch")){
                        sentences.add(strings.get(i) + " " + strings.get(j) + "es " + strings.get(k) + ".");
                    }
                    else{
                        sentences.add(strings.get(i) + " " + strings.get(j) + "s " + strings.get(k) + ".");
                    }
                }
            }
        }
        int maxCount = 0;
        int firstWordIndex = 0;
        int secondWordIndex = 0;;
        for(int i = 0; i < sentences.size(); i++){
            hashCodes.add(sha256(sentences.get(i)));
        }
        for(int i = 0; i < sentences.size(); i++){
            int count = 0;
            for(int j = i + 1; j < sentences.size(); j++){
                for(int k = 0; k < hashCodes.get(i).length(); k++){
                    if(hashCodes.get(i).charAt(k) == hashCodes.get(j).charAt(k)){
                        count++;
                    }
                }
                if(count > maxCount){
                    maxCount = count;
                    firstWordIndex = i;
                    secondWordIndex = j;
                    System.out.println("Setting max count to: " + maxCount);
                    System.out.println("First Sentence: " + sentences.get(i));
                    System.out.println("Second Sentence: " + sentences.get(j));
                }
                else if(count == maxCount){
                    System.out.println("Matching max count: " + maxCount);
                    System.out.println("First Sentence: " + sentences.get(i));
                    System.out.println("Second Sentence: " + sentences.get(j));
                }
                count = 0;
            }
        }
        long finishTime = System.nanoTime();
        double time = (double)(finishTime - startTime)/1000000000;
        System.out.println("Max Count: " + maxCount);
        System.out.println("First Word: " + sentences.get(firstWordIndex));
        System.out.println("Second Word: " + sentences.get(secondWordIndex));
        System.out.println(hashCodes.get(firstWordIndex) + " - First word hashCode");
        System.out.println(hashCodes.get(secondWordIndex) + " - Second word hashCode");
        System.out.println(time);
    }

    public static String sha256(String input){
        try{
            MessageDigest mDigest = MessageDigest.getInstance("SHA-256");
            byte[] salt = "CS210+".getBytes("UTF-8");
            mDigest.update(salt);
            byte[] data = mDigest.digest(input.getBytes("UTF-8"));
            StringBuffer sb = new StringBuffer();
            for (int i=0;i<data.length;i++){
                sb.append(Integer.toString((data[i]&0xff)+0x100,16).substring(1));
            }
            return sb.toString();
        }catch(Exception e){
            return(e.toString());
        }
    }

    public static List<String> readWordFromFile(File file)throws Exception{
        List<String> strings = new ArrayList<String>();
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()){
            strings.add(sc.nextLine());
        }
        sc.close();
        return strings;
    }
}
