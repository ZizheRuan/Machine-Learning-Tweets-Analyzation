import java.io.*;  
import java.util.*;  
import java.util.Map.Entry;  
public class CountOccurrenceOfWords {  
	public static final int NoSelect = 10000;
    public static void main(String[] args) {  
    	doIt("countPositive","countPositiveResult");
        doIt("countNeutral","countNeutralResult");
        doIt("countNegative","countNegativeResult");
        
//        String fileName="train-tweets";
//        String fileNameResult="frequency";
        
//        String fileName="countPositive";
//        String fileNameResult="countPositiveResult";
        
//        String fileName="countNeutral";
//        String fileNameResult="countNeutralResult";
        
//        String fileName="countNegative";
//        String fileNameResult="countNegativeResult";
        

}
    
    
    
    
    public static void doIt(String fileName, String fileNameResult){
    	        long t1 = System.currentTimeMillis();  
        String s;  
        String fileName1 = "/Users/zizheruan/OneDrive - The University of Melbourne/Knowledge Technology/Project 2/2017S1-KTproj2-data/"+fileName+".txt";  
        String fileName2 = "/Users/zizheruan/OneDrive - The University of Melbourne/Knowledge Technology/Project 2/2017S1-KTproj2-data/"+fileNameResult+".txt";  
        try {  
            BufferedReader br = new BufferedReader(new FileReader(fileName1));  
            BufferedWriter bw = new BufferedWriter(new FileWriter(fileName2));  
            StringBuffer sb = new StringBuffer();  
            //将文件内容存入StringBuffer中  
            while((s = br.readLine()) != null) {  
                sb.append(s);  
            }  
            String str = sb.toString().toLowerCase();  
            //分隔字符串并存入数组  
            String[] elements = str.split("[^a-zA-Z0-9]+");  
            int count = 0;  
            Map<String, Integer> myTreeMap = new TreeMap<String, Integer>();  
            //遍历数组将其存入Map<String, Integer>中  
            for(int i = 0; i < elements.length; i++) {  
                if(myTreeMap.containsKey(elements[i])) {  
                    count = myTreeMap.get(elements[i]);  
                    myTreeMap.put(elements[i], count + 1);  
                }  
                else {  
                    myTreeMap.put(elements[i], 1);  
                }  
            }                                            
//            System.out.println("单词统计的结果请见当前目录result.txt文件");  
            //将map.entrySet()转换成list  
            List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(myTreeMap.entrySet());  
            //通过比较器实现排序  
            Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {  
                //降序排序  
                public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {  
                    return o2.getValue().compareTo(o1.getValue());  
                }  
            });  
            int num = 1;  
            //将结果写入文件  
            for(Map.Entry<String, Integer> map : list) {  
                if(num <= NoSelect) {  
//                    bw.write("出现次数第" + num + "的单词为：" + map.getKey() + "，出现频率为" + map.getValue() + "次");  
                	bw.write(map.getKey() + " " + map.getValue());
                    bw.newLine();  
                    System.out.println(map.getKey() + " " + map.getValue());  
                    num++;  
                }  
                else break;  
            }  
            bw.write("耗时：" + (System.currentTimeMillis() - t1) + "ms");  
            br.close();  
            bw.close();  
            System.out.println("耗时：" + (System.currentTimeMillis() - t1) + "ms");  
        } catch (FileNotFoundException e) {  
            System.out.println("找不到指定文件！");  
        } catch (IOException e) {                                      
            System.out.println("文件读取错误！");  
        }  
    }
    
    
    
    
    
    
}