//Given an array of strings, group anagrams together.

//For example, given: ["eat", "tea", "tan", "ate", "nat", "bat"], 
//Return:

//[
//  ["ate", "eat","tea"],
//  ["nat","tan"],
//  ["bat"]
//]

//将各个string转换成char[]后排序后再转回string，并存入map中。map中由一个打头的stirng为key，和它的anagrams为value
public List<List<String>> groupAnagrams(String[] strs) {
    if (strs == null || strs.length == 0) return new ArrayList<List<String>>();
    Map<String, List<String>> map = new HashMap<String, List<String>>();
    for (String s : strs) {
        char[] ca = s.toCharArray();
        Arrays.sort(ca);
        String keyStr = String.valueOf(ca);
        if (!map.containsKey(keyStr)) map.put(keyStr, new ArrayList<String>());
        map.get(keyStr).add(s);
    }
    return new ArrayList<List<String>>(map.values());
}