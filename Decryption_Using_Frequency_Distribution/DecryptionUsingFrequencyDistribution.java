import java.io.*;
import java.util.*;



class WordFrequency implements Comparable<WordFrequency>{

    String s;
    Integer o;

    public WordFrequency(String s, Integer o) {
        this.s = s;
        this.o = o;
    }

    public int compareTo(WordFrequency o) {
        return o.o.compareTo(this.o);
    }
}
public class DecryptionUsingFrequencyDistribution {
    static void analyzeText () throws IOException
    {
       File fin = new File("data.txt");
       if(!fin.exists())
       {
           fin.createNewFile();
       }
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fin)));
        String line = null;
        Map<String, Integer> map = new TreeMap<String, Integer>();
        StringTokenizer st;
        String temp = null;
        while ((line = br.readLine())!=null)
        {
            st = new StringTokenizer(line," ,.:;\"()");

            while(st.hasMoreTokens())
            {
                temp = st.nextToken();
                if(!map.containsKey(temp)){
                    map.put(temp,1);
                }
                else {
                    int count = map.get(temp);
                    map.put(temp, count + 1);
                }
            }
        }
        List<WordFrequency> list = new ArrayList<WordFrequency>();

        for(Map.Entry entry : map.entrySet())
        {
           list.add(new WordFrequency((String) entry.getKey(),((Integer) entry.getValue()).intValue())) ;
        }
        Collections.sort(list);
        for(WordFrequency l: list ){
            System.out.println(l.s+" "+l.o);
        }
        br.close();
    }
    static void decrypt() throws IOException {
        String keyString = "bxicl yo dthngavukfwerm ps";
        char[] key = keyString.toCharArray();
        File fin = new File("data.txt");
        File fout = new File("decrypted_data.txt");
        if(!fout.exists())
            fout.createNewFile();
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fin)));
        BufferedWriter bw = new BufferedWriter(new FileWriter(fout));
        String line = null;
        while ((line = br.readLine())!=null)
        {
            char[] temp = line.toCharArray();
            for(int i=0;i<temp.length;i++) {
                if(temp[i]>='a' && temp[i]<='z') {
                    temp[i] = key[temp[i]-'a'];
                }
            }


            System.out.println(temp);
            bw.write(temp);
            bw.newLine();
        }
        br.close();
        bw.close();


    }
    public static void main(String[] args) throws IOException {

        analyzeText();
        System.out.println("=============================================================");
        decrypt();

    }
}
