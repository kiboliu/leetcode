//There are N gas stations along a circular route, where the amount of gas at station i is gas[i].

//You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1). 
//You begin the journey with an empty tank at one of the gas stations.

//Return the starting gas station's index if you can travel around the circuit once, otherwise return -1.

//设置total和sum两个变量，total判断有没有解，sum判断当前指针的有效性，即是否因为没有油了会重置。
public int canCompleteCircuit(int[] gas, int[] cost) {
    int total = 0;
    int j = -1;
    for(int i = 0, sum = 0; i < gas.length; i++){
        sum += gas[i] - cost[i];
        total += gas[i] - cost[i];
        if(sum < 0){
            j = i;
            sum = 0;
        }
    }
    return total >= 0? j + 1: -1;
}