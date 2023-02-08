import java.util.*;
import java.util.stream.*;
/*

Scaffold out an object representing a measurement.  A measurement contains the following information: A value representing the value of the measurement and a value representing the weight of the measurement.  
Create class with a main method and a method called "calculateWeightedAverage".  This method takes in a list of Measurement and returns the weighted average of all the measurement values.  (Note: negative weights are not allowed and should be thrown out)
A weighted average is calculated as follows.  SUM(value * weight)/SUM(weight).  Example: ((10 * 2) + (50 * 5) + (40 * 3)) / (2 + 5 + 3) = 390/10 = 39.

Modify the Measurement class to have a name, return the weighted averages grouped by each measurement name as Map<String, double>.
Example:
[
Measurement("speed", 1,2)
Measurement("speed", 3,4)
Measurement("fuel", 5,6)
Measurement("fuel", 7,8)
]
=
Map(
"speed" -> 2.3333
"fuel" -> 6.14
)
*/
public class MyClass {
    public static void main(String args[]) {
    List<Measurement> mList = prepareData();
    double result = calculateWeightedAverage(mList);
    Map<String, Double> map = mList.stream()
    .collect(Collectors.groupingBy(Measurement::getName))
    .stream()
    .collect(Collectors.toMap(entry -> entry.getKey(), calculateWeightedAverage(entry.getValue())));
    Map<String, Double> resultMap = new HashMap<>();
    // for(Map.Entry<String, List<Measurement>> entry : map.entrySet()) {
    //     resultMap.put(entry.getKey(), calculateWeightedAverage(entry.getValue()));
    // }
    
      System.out.println(map);
    }
    
    private static List<Measurement> prepareData() {
        Measurement m1 = new Measurement("speed", 1, -2);
        Measurement m2 = new Measurement("speed", 3, 4); //250 + 120 = 370/8 = 46.
        Measurement m3 = new Measurement("fuel", 5, 6);
        Measurement m4 = new Measurement("fuel", 7, 8);
        List<Measurement> list = new ArrayList<>();
        list.add(m1);
        list.add(m2);
        list.add(m3);
        list.add(m4);
        return list;
    }
    private static final double calculateWeightedAverage(List<Measurement> list) {
        double sum = 0d;
        double weightSum = 0d;
        for(Measurement m : list) {
            if(m.getValue() < 0 || m.getWeight() < 0) {
                continue;
            }
            sum += (m.getValue() * m.getWeight());
            weightSum += m.getWeight();
        }
        return sum / weightSum;
        
    }
}
class Measurement {
    private String name;
    private int value;
    private double weight;
    
    public Measurement(String name, int value, double weight) {
        this.name = name;
        this.value = value;
        this.weight = weight;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setValue(int value) {
        this.value = value;
    }
    public int getValue() {
        return this.value;
    }
    
    public void setWeight(double weight) {
        this.weight = weight;
    }
    public double getWeight() {
        return this.weight;
    }
}
