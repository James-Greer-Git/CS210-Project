import java.io.File;
import java.util.*;
import java.security.*;
public class CS210Project{
    public static void main (String[] args) throws Exception{
        
        File file = new File("");                                                   
        //words.txt path goes here
        
        List<String> words = readWordFromFile(file);
        //Reading words.txt into a list of strings
        
        String bestfirstSentence = "";
        String bestsecondSentence = "";
        //Variables to keep track of the current best pair
        
        int maxCount = 20;
        //Reducing maxCount or even setting it to zero will increase the amount of print statements used immediately after running the code.
        //I set it to 20 just to reduce the clutter in my console.
        
        while(maxCount < 25){
            
            String firstSentence = "My favourite words are '" + words.get((int) (Math.random()*words.size())) + "', '"
                                    + words.get((int) (Math.random()*words.size())) + "', '" + words.get((int) (Math.random()*words.size())) + "', '"
                                    + words.get((int) (Math.random()*words.size())) + "' and '" + words.get((int) (Math.random()*words.size())) + "'.";
            
            String secondSentence = "My favourite words are '" + words.get((int) (Math.random()*words.size())) + "', '"
                                    + words.get((int) (Math.random()*words.size())) + "', '" + words.get((int) (Math.random()*words.size())) + "', '"
                                    + words.get((int) (Math.random()*words.size())) + "' and '" + words.get((int) (Math.random()*words.size())) + "'.";
            //Generating the pair of sentences that will be tested in -this- loop.
            
            String firstHash = sha256(firstSentence);
            String secondHash = sha256(secondSentence);
            
            int count = 0;

            for(int i = 0; i < 64; i++){
                if(firstHash.charAt(i) == secondHash.charAt(i)){
                    count++;
                }
            }
            //Simple loop to compare all the characters in each string.
  
            if(count >= maxCount){  //>= statement is optional, I wanted to see any equivalent pairs if they were found
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
        //Method for reading a .txt file using Scanner
        List<String> strings = new ArrayList<String>();
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()){
            strings.add(sc.nextLine());
        }
        sc.close();
        return strings;
    }
}
