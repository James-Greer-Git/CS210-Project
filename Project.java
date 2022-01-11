import java.io.File;
import java.util.*;
import java.security.*;
import java.util.ArrayList;

public class Project{
    public static void main (String[] args) throws Exception{
        File file = new File("");
        List<String> strings = readWordFromFile(file);
        String[] hashCodes = new String[strings.size()];
        int maxCount = 0;
        int firstWordIndex = 0;
        int secondWordIndex = 0;
        for(int i = 0; i < strings.size(); i++){
            hashCodes[i] = sha256(strings.get(i));
        }
        for(int i = 0; i < hashCodes.length; i++){
            int count = 0;
            for(int j = i + 1; j < hashCodes.length; j++){
                for(int k = 0; k < hashCodes[i].length(); k++){
                    if(hashCodes[i].charAt(k) == hashCodes[j].charAt(k)){
                        count++;
                    }
                }
                if(count > maxCount){
                    maxCount = count;
                    firstWordIndex = i;
                    secondWordIndex = j;
                    System.out.println("Setting Max Count to: " + count);
                }
                count = 0;
            }
        }
        System.out.println("Max Count: " + maxCount);
        System.out.println("First Word:" + strings.get(firstWordIndex));
        System.out.println("Second Word: " + strings.get(secondWordIndex));
        System.out.println(hashCodes[firstWordIndex] + " - First word hashCode");
        System.out.println(hashCodes[secondWordIndex] + " - Second word hashCode");
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
