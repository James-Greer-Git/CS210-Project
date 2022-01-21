import java.io.File;
import java.util.*;
import java.security.*;
public class CS210Project{
    public static void main (String[] args) throws Exception{
        File file = new File("");
        List<String> words = readWordFromFile(file);
        String bestfirstSentence = "";
        String bestsecondSentence = "";
        int maxCount = 23;
        while(maxCount < 24){
            String firstSentence = "My favourite words are '" + words.get((int) (Math.random()*words.size())) + "', '"
                + words.get((int) (Math.random()*words.size())) + "', '" + words.get((int) (Math.random()*words.size())) + "', '"
                + words.get((int) (Math.random()*words.size())) + "' and '" + words.get((int) (Math.random()*words.size())) + "'.";
            String secondSentence = "My favourite words are '" + words.get((int) (Math.random()*words.size())) + "', '"
                + words.get((int) (Math.random()*words.size())) + "', '" + words.get((int) (Math.random()*words.size())) + "', '"
                + words.get((int) (Math.random()*words.size())) + "' and '" + words.get((int) (Math.random()*words.size())) + "'.";
            String firstHash = sha256(firstSentence);
            String secondHash = sha256(secondSentence);
            int count = 0;

            for(int i = 0; i < 64; i++){
                if(firstHash.charAt(i) == secondHash.charAt(i)){
                    count++;
                }
            }
            if(count >= maxCount){
                maxCount = count;
                bestfirstSentence = firstSentence;
                bestsecondSentence = secondSentence;
                System.out.println("Max count : " + maxCount + " \nFirst Sentence: " + bestfirstSentence + " \nSecond Sentence: " + bestsecondSentence);
            }
        }
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
